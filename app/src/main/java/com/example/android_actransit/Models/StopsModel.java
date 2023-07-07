package com.example.android_actransit.Models;

public class StopsModel {
    public int StopId;
    public String Name;
    public double Latitude;
    public double Longitude;
    public String ScheduledTime;

    public StopsModel(int stopId, String name, double latitude, double longitude, String scheduledTime) {
        StopId = stopId;
        Name = name;
        Latitude = latitude;
        Longitude = longitude;
        ScheduledTime = scheduledTime;
    }

    public void setStopId(int stopId) {
       StopId = stopId;
    }

    public int getStopId() {
        return StopId;
    }

    public String getName() {
        return Name;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public String getScheduledTime() {
        return ScheduledTime;
    }
}