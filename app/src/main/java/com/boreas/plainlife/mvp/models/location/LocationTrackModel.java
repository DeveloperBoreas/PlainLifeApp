package com.boreas.plainlife.mvp.models.location;

import com.boreas.plainlife.base.BaseResponse;


public class LocationTrackModel extends BaseResponse{
    private String data;

    public LocationTrackModel() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static class  LatLng{
        private Double latitude;
        private Double longitude;

        public LatLng() {
        }

        public LatLng(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Double getLat() {
            return latitude;
        }

        public void setLat(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLng() {
            return longitude;
        }

        public void setLng(Double longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "LatLng{" +
                    "lat='" + latitude + '\'' +
                    ", lng='" + longitude + '\'' +
                    '}';
        }
    }

}
