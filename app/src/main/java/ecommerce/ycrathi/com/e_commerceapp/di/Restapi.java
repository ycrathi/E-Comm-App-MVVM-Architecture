package ecommerce.ycrathi.com.e_commerceapp.di;

import ecommerce.ycrathi.com.e_commerceapp.model.ECommPojo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Restapi {

    @GET("/json")
    Call<ECommPojo> getE_comData();
}