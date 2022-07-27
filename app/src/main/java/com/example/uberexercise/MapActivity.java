package com.example.uberexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.shapes.Shape;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
//import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.mavsdk.MavsdkEventQueue;
import io.mavsdk.System;
import io.mavsdk.mavsdkserver.MavsdkServer;
import io.reactivex.disposables.Disposable;

public class MapActivity extends AppCompatActivity {
    private static final Logger logger = LoggerFactory.getLogger(MapActivity.class);
    private MavsdkServer mavsdkServer = new MavsdkServer();
    private System drone;
    private final List<Disposable> disposables = new ArrayList<>();
    public static final String BACKEND_IP_ADDRESS = "192.168.0.146";

//    //Start the server.
//    MavsdkEventQueue.executor().execute(() -> server.run(SYSTEM_ADDRESS, MAVSDK_SERVER_PORT));
//    //Stop the server.
//    MavsdkEventQueue.executor().execute(() -> server.stop());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_map);

//        mapView = findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(this);
//
//        missionPlanner = ViewModelProviders.of(this).get(missionPlannerModel.class);
        missionPlannerModel missionPlanner = new missionPlannerModel();

        FlightPathStorage flightPaths = new FlightPathStorage();



        Location a = new Location("Essau");
        a.setLatitude(13.483864d);
        a.setLongitude(-16.532146d);
        flightPaths.setLocation("Essau", a);
        Location b = new Location("Brikama");
        b.setLatitude(13.2748d);
        b.setLongitude(-16.647912d);
        flightPaths.setLocation("Brikama", b);
        //

        // get Origin and Destination
//        HashMap<String, Object> landmarkList = missionPlanner.getFlightPathFromId(Integer tripId, PendingRequests.tripsCallback callback);
        Location originLocation = flightPaths.getLocation( "Essau");
        Location destinationLocation = flightPaths.getLocation("Brikama");
        logger.info(String.valueOf(destinationLocation));

        //Get Path (currently inputs raw into FlightPathStorage)

        //Start Mission.
        Button startMissionButton = findViewById(R.id.start_mission);
        startMissionButton.setOnClickListener(v -> missionPlanner.localMission(drone,  originLocation, destinationLocation));
        //Arm Drone.
    }

    @Override
    public void onStart() {
        super.onStart();
//        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
//        mapView.onResume();
//        viewModel.currentPositionLiveData.observe(this, currentPositionObserver);
//        viewModel.currentMissionPlanLiveData.observe(this, currentMissionPlanObserver);

//        int mavsdkServerPort = mavsdkServer.run(); //local: 50051
        drone = new System(BACKEND_IP_ADDRESS, 50051);
        logger.info("drone onlline");
        disposables.add(drone.getTelemetry().getFlightMode().distinctUntilChanged()
                .subscribe(flightMode -> logger.debug("flight mode: " + flightMode)));

        disposables.add(drone.getTelemetry().getArmed().distinctUntilChanged()
                .subscribe(armed ->
                        logger.debug("armed: " + armed)
                        // And update on Firebase too. ));

//        disposables.add(drone.getTelemetry().getFlightMode().distinctUntilChanged().doOnError(throwable -> callback.onError(throwable.getLocalizedMessage()))
//                .subscribe((flightMode)->{ logger.debug("flight mode: " + flightMode); }, (exception) -> { logger.error("Failed and here's why: "+ exception); })); //flightMode -> logger.debug("flight mode: " + flightMode)
//
//
//        disposables.add(drone.getTelemetry().getArmed().distinctUntilChanged()
//                .subscribe(armed -> logger.debug("armed: " + armed)));
//        disposables.add(drone.getTelemetry().getPosition().subscribe(position -> {
//            LatLng latLng = new LatLng(position.getLatitudeDeg(), position.getLongitudeDeg());
//            viewModel.currentPositionLiveData.postValue(latLng);
//        }));
    }

    @Override
    public void onPause() {
        super.onPause();
//        mapView.onPause();
//        viewModel.currentPositionLiveData.removeObserver(currentPositionObserver);
//        viewModel.currentMissionPlanLiveData.removeObserver(currentMissionPlanObserver);

        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
        disposables.clear();


        drone.dispose();
        drone = null;
        mavsdkServer.stop();
    }

    @Override
    public void onStop() {
        super.onStop();
//        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
//        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.arm_drone:
                drone.getAction().arm().subscribe();
                break;
//            case R.id.disarm:
//                drone.getAction().kill().subscribe();
//                break;
//            case R.id.land:
//                drone.getAction().land().subscribe();
//                break;
//            case R.id.return_home:
//                drone.getAction().returnToLaunch().subscribe();
//                break;
//            case R.id.takeoff:
//                drone.getAction().arm().andThen(drone.getAction().takeoff()).subscribe();
//                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }



}