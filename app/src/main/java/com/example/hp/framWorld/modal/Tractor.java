package com.example.hp.framWorld.modal;

import com.google.android.gms.maps.model.LatLng;

public class Tractor {
    private String teactorName;
    private LatLng location;
    private String locationName;

    public Tractor(String teactorName, LatLng location, String locationName) {
        this.teactorName = teactorName;
        this.location = location;
        this.locationName = locationName;
    }

    public String getTeactorName() {
        return teactorName;
    }

    public void setTeactorName(String teactorName) {
        this.teactorName = teactorName;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
