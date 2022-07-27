//package com.example.uberexercise;
//
//import com.google.android.gms.maps.model.LatLng;
//
//import java.util.ArrayList;
//import java.util.Dictionary;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.List;
//import java.util.Map;
//
//public class CentersDictionary {
//
//    public static void main(String[] args) {
//        //Single Value not great for coordinates.
////        Dictionary HCLocations = new Hashtable();
////        Multimap<String, String> mm = HashMultimap.create();
//        //I'm a sucker for libraries, I could go with this if need be:
////        Map<Object, List<Object>> multiMap = new HashMap<Object, List<Object>>();
//
//        Map<Object, List<LatLng>> HCLocations = new HashMap<Object, List<LatLng>>();
//
//        // put() method
//        final List<LatLng> Essau_Destination = new ArrayList<>(new LatLng(13.483864, -16.532146));
//        HCLocations.put("Essau", Essau_Destination);
//
//        final List<LatLng> waypointExample = new ArrayList<>();
//        {
//            waypointExample.add(new LatLng(28.6139, 77.2090));//delhi
//            waypointExample.add(new LatLng(22.2587, 71.1924));//gujarat
//            waypointExample.add(new LatLng(18.5204, 73.8567));//pune
//            waypointExample.add(new LatLng(12.9716, 77.5946));//banglore
//            waypointExample.add(new LatLng(25.5941, 85.1376));//patna
//            //this is needed to completed a covered area, without this it would not work
//            waypointExample.add(new LatLng(28.6139, 77.2090));//delhi
//        }
//        HCLocations.put("Example", waypointExample);
//        //print out Hashtable out
//        System.out.println(HCLocations);
//
//        //let's get the value using the key
//        System.out.println(HCLocations.get("Essau"));
//
//        //Is there a record with the "Johnny Walker" key?
//        System.out.println(((Hashtable) HCLocations).containsKey("Johnny Walker"));
//        //all keys of the Hashtable
//        System.out.println(((Hashtable) HCLocations).keySet());
//        //values from Hashtable
//        System.out.println(((Hashtable) HCLocations).values());
//
//        //the quantity of records
//        System.out.println(HCLocations.size());
//        //removing one record
//        HCLocations.remove("Andrew Arnold");
//        System.out.println(HCLocations);
//    }
//}
