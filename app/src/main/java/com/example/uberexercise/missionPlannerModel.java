package com.example.uberexercise;

import android.annotation.SuppressLint;
import android.location.Location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.mavsdk.System;
import io.mavsdk.mission.Mission;

public class missionPlannerModel {

    private static final Logger logger = LoggerFactory.getLogger(missionPlannerModel.class);

    private static final float MISSION_HEIGHT = 5.0f;
    private static final float MISSION_SPEED = 1.0f;
    // Load FlightPath storage (onCreate checks Firebase).
    FlightPathStorage flightPaths = new FlightPathStorage();





//    final MutableLiveData<LatLng> currentPositionLiveData = new MutableLiveData<>();
//    final MutableLiveData<List<LatLng>> currentMissionPlanLiveData = new MutableLiveData<>();

//    public missionPlannerModel() {
//        currentMissionPlanLiveData.postValue(new ArrayList<>());
//    }
//
//    @Override
//    protected void onCleared() {
//        super.onCleared();
//    }

    void getWaypoints(System drone) {
        // Custom code to set a health center in Essau.

        Location EssauHC = new Location("Essau");
        EssauHC.setLatitude(13.483864d);
        EssauHC.setLongitude(-16.532146d);
        flightPaths.setLocation("Essau", EssauHC);
        Location BrikamaHC = new Location("Brikama");
        BrikamaHC.setLatitude(13.2748d);
        BrikamaHC.setLongitude(-16.647912d);
        flightPaths.setLocation("Brikama", BrikamaHC);
        //    databaseReference.child("Users").child(GlobalInfo.PhoneNumber).
        //    child("Updates").addValueEventListener(new ValueEventListener() {
        //        @Override
        //        public void onDataChange(DataSnapshot dataSnapshot) {
        logger.debug("Flight Paths loaded in FlightPathStorage.");

        // Derive the flightPath from Storage.
        // retrieving the Location of the HealthCenter

    }
    void localMission(System drone, Location originLocation, Location destinationLocation) {

        logger.debug("local mission go");

        // Temp: further work will extend this to complex flight paths within FlightPathStorage.
        ////////////////////////////////////////////////////
        // Multi-waypoint based on distance: cannot have a distance further than 900m (MAVLink standard?)
        float distance = originLocation.distanceTo(destinationLocation);
        logger.debug("DISTANCE IS", distance);
        double nb_waypoints = Math.ceil(distance/800);
//        double nb_waypoints = 40;
        double latDistance = Math.abs(destinationLocation.getLatitude() - originLocation.getLatitude());
        double lonDistance = Math.abs(destinationLocation.getLongitude() - originLocation.getLongitude());
        List<Mission.MissionItem> cycle = new ArrayList<>();
        for (int i = 0; i < nb_waypoints-1; i++) {
            cycle.add(generateMissionItem((originLocation.getLatitude() + i * latDistance/nb_waypoints), (originLocation.getLongitude() + i * lonDistance/nb_waypoints)));
//            cycle.add(generateMissionItem((originLocation.getLatitude() ), (originLocation.getLongitude() )));
            // To avoid rounding errors, last waypoint is the location:

            if (i == nb_waypoints-2){
                cycle.add(generateMissionItem(destinationLocation.getLatitude(), destinationLocation.getLongitude()));
            }
        };
        // Health Center to be later taken from Request.



        logger.debug("Starting example: mission...");

        drone.getTelemetry().getFlightMode()
                .doOnNext(flightMode -> logger.debug("flight mode: " + flightMode))
                .subscribe();



        List<Mission.MissionItem> missionItems = new ArrayList<>();
        missionItems.addAll(cycle);

        Mission.MissionPlan missionPlan = new Mission.MissionPlan(missionItems);
        logger.debug("About to upload " + missionItems.size() + " mission items");

        CountDownLatch latch = new CountDownLatch(1);
        drone.getMission()
                .setReturnToLaunchAfterMission(true)
                .andThen(drone.getMission().uploadMission(missionPlan)
                        .doOnComplete(() -> logger.debug("Upload succeeded")))
                        .doOnError(throwable -> logger.error("Issue uploading"))
                .andThen(drone.getAction().arm()
                        .onErrorComplete())
                .andThen(drone.getMission().startMission()
                        .doOnComplete(() -> logger.debug("Mission started"))
                        .doOnError(throwable -> logger.error("Failed to start the mission", throwable)))
//            .toCompletable()
//            .andThen((CompletableSource) cs -> latch.countDown())
                .subscribe();

        try {
            latch.await();
        } catch (InterruptedException ignored) {
            // This is expected
        }

        logger.debug("Uploading and starting mission...");
    }

public static Mission.MissionItem generateMissionItem(double latitudeDeg, double longitudeDeg) {
        return new Mission.MissionItem(
                latitudeDeg,
                longitudeDeg,
                10f,
                10f,
                false,
                Float.NaN,
                Float.NaN,
                Mission.MissionItem.CameraAction.NONE,
                Float.NaN,
                Double.NaN,
                Float.NaN,
                Float.NaN,
                Float.NaN);
    }
}