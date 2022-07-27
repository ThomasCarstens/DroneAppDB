package com.example.uberexercise;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FlightPathStorage {
    HashMap<String, Location> locationStorage;
    private static final Logger logger = LoggerFactory.getLogger(missionPlannerModel.class);

    // This is the constructor for Storage-objects.
    // It's called with the `new` keyword and creates itself
    // a new HashMap.
    // Each Storage-object will have its own HashMap `locationStorage`
    public FlightPathStorage() {
        this.locationStorage = new HashMap<>();
    }

    // This is a so-called Getter-Method, that exposes the HashMap
    // to other classes in a defined way; it will retrieve the Location-Object
    // associated with the `key` in the Storage's `locationStorage`.
    public Location getLocation(String healthCenter) {
        return this.locationStorage.get(healthCenter);
    }
    // This is a so-called Setter-Method, that exposes a way to add
    // a value to our HashMap `locationStorage`
    public void setLocation(String healthCenter, Location loc) {
        this.locationStorage.put(healthCenter, loc);
    }

//    public void findMissionId(Integer mission_id, PendingRequests.tripsCallback callback) {
//        List<HashMap> out = new ArrayList<HashMap>();
//        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference finalReference = root.child("Airspace Management").child("Mission Plans");
//
//        finalReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot key : snapshot.getChildren()) {
//                    HashMap<String, Object> t = (HashMap<String, Object>) key.getValue();
//
//                    String id = (String) t.get("missionId");
//                    String status = (String) t.get("status");
//                    String origin = (String) t.get("tripOriginId");
//
//                    //Only trips with status requested are displayed on the pending request page.
//                    if (Objects.equals(destination, "REQUESTED") && Objects.equals(origin, to_find)) {
//                        String id = (String) t.get("missionId");
//                        Object waypoints = (Object) t.get("Waypoints");
//                        //Checking if the trip has not been there for more than 72 hours, as the procedure is to mark
//                        //a trip as incomplete if it has been requested and not fulfilled withing 72 horus
//                        finalReference.child(id).child("status").setValue(INCOMPLETE);
//
//                        out.add(t);
//
//                    }
//                }
//
//                callback.onCallback(out);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//
//    public void getFlightPathFromId(Integer tripId, PendingRequests.tripsCallback callback) {
//        List<HashMap> out = new ArrayList<HashMap>();
//        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference finalReference = root.child("Airspace Management").child("Mission Plans");
//
//        finalReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot key : snapshot.getChildren()) {
//                    HashMap<String, Object> t = (HashMap<String, Object>) key.getValue();
//
//                    String id = (String) t.get("missionId");
//                    String status = (String) t.get("status");
//                    String origin = (String) t.get("tripOriginId");
//
//                    //Only trips with status requested are displayed on the pending request page.
//                    if (Objects.equals(id, tripId)) {
//                        Object waypoints = (Object) t.get("Waypoints");
//                        // finalReference.child(id).child("missionStatus").setValue(INCOMPLETE);
//
//                        out.add(t);
//
//                    }
//                }
//                callback.onCallback(out);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }





}

