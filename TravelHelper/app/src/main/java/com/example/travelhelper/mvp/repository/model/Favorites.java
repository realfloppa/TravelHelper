package com.example.travelhelper.mvp.repository.model;

import com.google.gson.annotations.SerializedName;

public class Favorites {
    @SerializedName("id")
    public String Id;
    @SerializedName("userId")
    public String UserId;
    @SerializedName("hotelId")
    public String HotelId;

    public Favorites(String id, String userId, String hotelId) {
        Id = id;
        UserId = userId;
        HotelId = hotelId;
    }

    public String getId() {
        return Id;
    }

    public String getUserId() {
        return UserId;
    }

    public String getHotelId() {
        return HotelId;
    }
}
