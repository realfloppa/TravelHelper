package com.example.travelhelper.mvp.repository.remote;

import com.example.travelhelper.mvp.repository.model.Favorites;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Managers;
import com.example.travelhelper.mvp.repository.model.ManagerResponse;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.mvp.repository.model.ReservationsResponse;
import com.example.travelhelper.mvp.repository.model.Rooms;
import com.example.travelhelper.mvp.repository.model.Users;
import com.example.travelhelper.mvp.repository.model.UsersResponse;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TravelHelperApi {
    // TODO: 18.05.2021 Migrate to rxjava
    @GET("api/user")
    Observable<String> searchUser(@Query("login") String login, @Query("password") String password);
    @GET("api/hotel/")
    Observable<String> searchHotel(@Query("title") String title, @Query("city") String city);
    @GET("api/hotel/")
    Call<List<Hotels>> getHotelList();
    @GET("api/room/{id}")
    Call<List<Rooms>> getRoomList(@Path("id") String hotelId);
    @GET("api/reservation/{userId}")
    Call<List<Reservations>> getReservationList(@Path("userId") String userId);
    @GET("api/reservationdetails")
    Call<ReservationsResponse> getReservationDetails(@Query("roomId") String roomId);
    @GET("api/user/{id}")
    Call<UsersResponse> getUserById(@Path("id") String id);
    @GET("api/favorite/{userId}")
    Call<List<Hotels>> getFavoritesByUser(@Path("userId") String userId);
    @GET("api/favorite/")
    Observable<String> searchFavorite(@Query("userId") String userId, @Query("hotelId") String hotelId);
    @GET("api/city/")
    Call<List<String>> getCityList();
    @GET("api/hotel/{city}")
    Call<List<Hotels>> getHotelsByFilters(@Path("city")String city, @Query("pattern")String pattern);
    @GET("api/helper/{hotelId}")
    Call<List<Reservations>> getReservationsByHotel(@Path("hotelId")String hotelId);
    @GET("api/manager/{userId}")
    Observable<String> getHotelByManagerId(@Path("userId")String userId);
    @GET("api/managerExtension/{reservationId}")
    Observable<ManagerResponse> getDetailsForManager(@Path("reservationId")String reservationId);

    @POST("api/user")
    Observable<String> createUser(@Body Users user);
    @POST("api/hotel")
    Observable<String> createHotel(@Body Hotels hotel);
    @POST("api/room")
    Observable<String> createRoom(@Body Rooms room);
    @POST("api/reservation")
    Observable<String> createReservation(@Body Reservations reservation);
    @POST("api/favorite")
    Observable<String> addFavorite(@Body Favorites favorite);
    @POST("api/manager")
    Observable<String> createManager(@Body Managers manager);
    @POST("api/reservationsByDates")
    Observable<String> checkReservationByDates(@Body Reservations reservation);

    @PUT("api/hotel/{id}")
    Call<String> updateHotel(@Path("id") String id, @Body Hotels hotel);
    @PUT("api/room/{id}")
    Call<String> updateRoom(@Path("id") String id, @Body Rooms room);
    @PUT("api/user/{id}")
    Call<String> updateUser(@Path("id") String id, @Body Users user);
    @PUT("api/reservation/{id}")
    Call<String> updateReservation(@Path("id") String id, @Body Reservations reservation);

    @DELETE("api/hotel/{id}")
    Call<String> deleteHotel(@Path("id") String id);
    @DELETE("api/room/{id}")
    Call<String> deleteRoom(@Path("id") String id);
    @DELETE("api/reservation/{id}")
    Call<String> deleteReservation(@Path("id") String id);
    @DELETE("api/favorite")
    Observable<String> deleteFavorite(@Query("userId") String userId, @Query("hotelId") String hotelId);
}
