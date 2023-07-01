package Models;

public class StopsModel {
    public String StopId;
    public String Name;
    public String Latitude;
    public String Longitude;
    public String ScheduledTime;

    public StopsModel(String stopId, String name, String latitude, String longitude, String scheduledTime) {
        this.StopId = StopId;
        this.Name = name;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.ScheduledTime = scheduledTime;
    }

    public String getStopId() {
        return StopId;
    }

    public String getName() {
        return Name;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getScheduledTime() {
        return ScheduledTime;
    }

    // models 7:23
}
