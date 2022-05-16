package com.geofence.testDTO;

import com.geofence.model.GeoFenceResponse;

import java.math.BigDecimal;

public class GeofenceResponseData {

    public GeoFenceResponse getGepResponseDTO() {
        GeoFenceResponse res = new GeoFenceResponse();
        res.setId(101L);
        res.setLatitude(BigDecimal.valueOf(10000.3434));
        res.setLongitude(BigDecimal.valueOf(2222.3444));
        res.setRadius(BigDecimal.valueOf(12.334));

        return res;
    }

}
