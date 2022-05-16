package com.geofence.testDTO;

import com.geofence.model.GeoFenceRequest;

import java.math.BigDecimal;

public class GeofenceRequestData {

    public GeoFenceRequest getGepRequestData() {
        GeoFenceRequest req = new GeoFenceRequest();
        req.setLongitude(BigDecimal.valueOf(2222.3444));
        req.setLatitude(BigDecimal.valueOf(10000.3434));
        req.setRadius(BigDecimal.valueOf(12.334));

        return req;
    }
}
