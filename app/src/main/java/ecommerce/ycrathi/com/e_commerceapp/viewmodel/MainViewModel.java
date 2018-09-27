package ecommerce.ycrathi.com.e_commerceapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.util.Log;
import android.view.View;

import ecommerce.ycrathi.com.e_commerceapp.di.Restapi;
import ecommerce.ycrathi.com.e_commerceapp.model.Categories;
import ecommerce.ycrathi.com.e_commerceapp.model.ECommPojo;
import ecommerce.ycrathi.com.e_commerceapp.model.Products;
import ecommerce.ycrathi.com.e_commerceapp.model.SingleTonModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainViewModel extends ViewModel {

    public ObservableBoolean isVisible = new ObservableBoolean();
    private MutableLiveData<ECommPojo> eCommPojoMutableLiveData;
    private Retrofit retrofit;
    private ActionListener listener;

    public LiveData<ECommPojo> getEcommData(Retrofit retrofit) {
        isVisible.set(false);
        //if (eCommPojoMutableLiveData == null) {
        this.retrofit = retrofit;
        eCommPojoMutableLiveData = new MutableLiveData<ECommPojo>();
        loadUsers();
        //}
        return eCommPojoMutableLiveData;
    }

    public void onClick(final View view) {
        listener.onClick(view);
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.


        Call<ECommPojo> data = retrofit.create(Restapi.class).getE_comData();

        //Enque the call
        data.enqueue(new Callback<ECommPojo>() {
            @Override
            public void onResponse(Call<ECommPojo> call, Response<ECommPojo> response) {
                Log.d("Yogesh", "Success response " + response.body());

                for (Categories categories : response.body().getCategories()) {
                    for (Products products : categories.getProducts()) {
                        SingleTonModel.getInstance().addProduct(products.getId(), products);
                    }
                }

                eCommPojoMutableLiveData.setValue(response.body());
                isVisible.set(true);
            }

            @Override
            public void onFailure(Call<ECommPojo> call, Throwable t) {
                isVisible.set(true);
                Log.d("Yogesh", "failure response " + t.toString());
                eCommPojoMutableLiveData.setValue(null);
            }
        });

    }

    public MutableLiveData<ECommPojo> getData() {
        return eCommPojoMutableLiveData;
    }

    public void configureActionListener(ActionListener listener) {
        this.listener = listener;
    }
}
