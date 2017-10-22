package id.erictriawan.taskcermati.network;

import id.erictriawan.taskcermati.model.UserModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by erictriawan on 10/20/17.
 */

public interface RestAPI {

    //Saeacrh
    @Headers("Content-Type: application/json")
    @GET("search/users")
    Observable<UserModel> apiSearch(@Query("q") String q,@Query("per_page")  int limit);






}

