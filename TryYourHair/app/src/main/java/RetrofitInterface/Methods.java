package RetrofitInterface;

import Models.HairStyle;
import Models.TestAPIHairstyle;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {
//    @GET("ver1/hairstyle")
//    Call <TestAPIHairstyle> getAllData();

    @GET("ver1/hairstyle")
    Call <TestAPIHairstyle> getAllData();
}
