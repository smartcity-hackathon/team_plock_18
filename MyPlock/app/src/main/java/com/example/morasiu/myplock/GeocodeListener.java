package com.example.morasiu.myplock;

import android.util.Log;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.Location;
import com.here.android.mpa.search.ResultListener;

import org.locationtech.jts.operation.distance.GeometryLocation;

import java.util.List;

class GeocodeListener implements ResultListener<List<Location>> {

    public GeoCoordinate geoCoordinate;
    @Override
    public void onCompleted(List<Location> data, ErrorCode error) {
        if (error != ErrorCode.NONE) {
            Log.e("GEO_LISTENER", "Error:" + error.toString());
        } else {
            geoCoordinate = new GeoCoordinate(data.get(0).getCoordinate());
        }
    }

    public GeoCoordinate getGeoCoordinate() {
        return geoCoordinate;
    }
}