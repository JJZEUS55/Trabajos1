package net.gshp.p3;

/**
 * Created by leo on 20/06/18.
 */

public class Geo {
    private int id;
    private String lat;
    private String lon;
    private String time;

    public int getId() {
        return id;
    }

    public Geo setId(int id) {
        this.id = id;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public Geo setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLon() {
        return lon;
    }

    public Geo setLon(String lon) {
        this.lon = lon;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Geo setTime(String time) {
        this.time = time;
        return this;
    }
}
