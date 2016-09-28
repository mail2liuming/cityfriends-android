package nz.co.liuming.cityfriends.common.rest;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import nz.co.liuming.cityfriends.common.rest.model.BaseModel;
import nz.co.liuming.cityfriends.common.rest.model.ResultResponse;
import nz.co.liuming.cityfriends.home.model.CalendarFeed;
import nz.co.liuming.cityfriends.home.model.Feed;
import nz.co.liuming.cityfriends.home.model.FeedEntry;
import nz.co.liuming.cityfriends.home.model.MessageFeed;
import nz.co.liuming.cityfriends.users.model.User;
import nz.co.liuming.cityfriends.users.model.UserRequest;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liuming on 15/06/16.
 */
public interface RestApis {
    @GET("feeds/entries")
    Observable<List<Feed>> getEntries(
            @Query("page") int aPage);

    @GET("calendars")
    Observable<List<CalendarFeed>> getCalendarFeeds(
            @Query("page") int aPage);

    @GET("in_site_messages")
    Observable<List<MessageFeed>> getMessageFeeds(
            @Query("page") int aPage);

    @POST("login")
    Observable<User> doLogin(@Body JsonObject body);

    @POST("signup")
    Observable<User> doSignup(@Body UserRequest userRequest);

    @POST("feeds")
    Observable<ResultResponse> createFeed(@Body Feed body);

    @POST("calendars")
    Observable<ResultResponse> createCalendar(@Body CalendarFeed body);

    @POST("in_site_messages")
    Observable<ResultResponse> createCalendar(@Body MessageFeed body);
}
