package ecommerce.ycrathi.com.e_commerceapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import ecommerce.ycrathi.com.e_commerceapp.model.ECommPojo;
import ecommerce.ycrathi.com.e_commerceapp.model.SingleTonModel;
import retrofit2.Retrofit;

public class ShowProductViewModel extends ViewModel {

    private MutableLiveData<ECommPojo> eCommPojoMutableLiveData;

    public LiveData<ECommPojo> getEcommData() {
        if (eCommPojoMutableLiveData == null) {
            eCommPojoMutableLiveData = new MutableLiveData<ECommPojo>();
            eCommPojoMutableLiveData.setValue(SingleTonModel.getInstance().geteCommPojo());
        }
        return eCommPojoMutableLiveData;
    }
}
