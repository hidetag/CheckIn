package cn.shield.checkin.model;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-19 14:15<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class LocationModel {

    private String location;
    private double longitude;
    private double latitude;

    public LocationModel() {
    }

    public LocationModel(String location, double longitude, double latitude) {
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
