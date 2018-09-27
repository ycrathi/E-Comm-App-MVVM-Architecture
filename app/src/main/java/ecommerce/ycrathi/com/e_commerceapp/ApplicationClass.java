package ecommerce.ycrathi.com.e_commerceapp;

import android.app.Application;

import ecommerce.ycrathi.com.e_commerceapp.di.AppModule;
import ecommerce.ycrathi.com.e_commerceapp.di.DaggerNetComponent;
import ecommerce.ycrathi.com.e_commerceapp.di.NetComponent;
import ecommerce.ycrathi.com.e_commerceapp.di.NetModule;

public class ApplicationClass extends Application {

    private NetComponent mNetComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://stark-spire-93433.herokuapp.com/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
