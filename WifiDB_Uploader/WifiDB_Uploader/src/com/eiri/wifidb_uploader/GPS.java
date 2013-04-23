package com.eiri.wifidb_uploader;

import java.util.Iterator;

import android.content.Context;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GPS {
	private static final String TAG = "GPS";
    //private static final int min_gps_sat_count = 5; //If GPS is enabled,Use minimal connected satellites count.
	public static Context context;
    private static LocationManager myLocationManager;
    //private static int sat_count = 0;
    LocationResult locationResult;
    
	public static void start(Context ctx){
        context = ctx;

        myLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // turning on location updates
        myLocationManager.requestLocationUpdates("network", 0, 0, networkLocationListener);
        myLocationManager.requestLocationUpdates("gps", 0, 0, gpsLocationListener);
        myLocationManager.addGpsStatusListener(gpsStatusListener);
	} 
	
	public static void stop(Context ctx){
        context = ctx;
        
        myLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        
        // removing all updates and listeners
        myLocationManager.removeUpdates(gpsLocationListener);
        myLocationManager.removeUpdates(networkLocationListener);    
        myLocationManager.removeGpsStatusListener(gpsStatusListener);
	}
	
	public void updateLocation(Context ctx){
        context = ctx;
        
        myLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        
        // removing all updates and listeners
        myLocationManager.removeUpdates(gpsLocationListener);
        myLocationManager.removeUpdates(networkLocationListener);    
        myLocationManager.removeGpsStatusListener(gpsStatusListener);
	}
    /**
     * GpsStatus listener. OnChainged counts connected satellites count.
     */
	
    public final static GpsStatus.Listener gpsStatusListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {
        	/*
             if(event == GpsStatus.GPS_EVENT_SATELLITE_STATUS){
                try {
                    // Check number of satellites in list to determine fix state
                     GpsStatus status = myLocationManager.getGpsStatus(null);
                     Iterable<GpsSatellite>satellites = status.getSatellites();
                     sat_count = 0;
                     Iterator<GpsSatellite>satI = satellites.iterator();
                     while(satI.hasNext()) {
                         //GpsSatellite satellite = satI.next();
                         //Log.d(TAG, "Satellite: snr=" + satellite.getSnr() + ", elevation=" + satellite.getElevation());                       
                         satI.next();
                         sat_count++;
                     }
                } catch (Exception e) {
                    e.printStackTrace();
                    sat_count = min_gps_sat_count + 1;
                }
                //Log.d(TAG, "#### sat_count = " + sat_count);
             }
             */
         }
    };
    
    
    public static Location getLocation(Context context){
    	Log.d(TAG, "getLocation()");

        // fetch last known location and update it
        try {
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
             criteria.setAltitudeRequired(false);
             criteria.setBearingRequired(false);
             criteria.setCostAllowed(true);
             String strLocationProvider = lm.getBestProvider(criteria, true);

             Log.d(TAG, "strLocationProvider=" + strLocationProvider);
             Location location = lm.getLastKnownLocation(strLocationProvider);
             if(location != null){
                return location;
             }
             return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Integer getSats(Context context){
    	Log.d(TAG, "getGpsStatus()");
    	myLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // fetch last known location and update it
        try {
             GpsStatus status = myLocationManager.getGpsStatus(null);
             Iterable<GpsSatellite>satellites = status.getSatellites();
             //Log.d(TAG, satellites.toString());
             int sats = 0;
             Iterator<GpsSatellite>satI = satellites.iterator();
             //Log.d(TAG, satI.toString());
             while(satI.hasNext()) {
                 //GpsSatellite satellite = satI.next();
                 //Log.d(TAG, "Satellite: snr=" + satellite.getSnr() + ", elevation=" + satellite.getElevation());                       
            	 satI.next();
            	 sats++;
             }
             return sats;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Gps location listener.
     */
    public final static LocationListener gpsLocationListener = new LocationListener(){
        @Override
         public void onLocationChanged(Location location){

        }
         public void onProviderDisabled(String provider){}
         public void onProviderEnabled(String provider){}
         public void onStatusChanged(String provider, int status, Bundle extras){}
    }; 

    /**
     * Network location listener.
     */
    public final static LocationListener networkLocationListener = new LocationListener(){
        @Override
         public void onLocationChanged(Location location){

        }
         public void onProviderDisabled(String provider){}
         public void onProviderEnabled(String provider){}
         public void onStatusChanged(String provider, int status, Bundle extras){}
    }; 
    /**
     * Best location abstract result class
     */
    public static abstract class LocationResult{
         public abstract void gotLocation(Location location);
         public abstract void gotSatellites(GpsStatus gpsStatus);
     }
}