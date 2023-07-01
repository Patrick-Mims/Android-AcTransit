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


}
