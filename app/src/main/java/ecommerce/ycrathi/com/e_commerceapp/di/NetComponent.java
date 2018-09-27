package ecommerce.ycrathi.com.e_commerceapp.di;

import javax.inject.Singleton;

import dagger.Component;
import ecommerce.ycrathi.com.e_commerceapp.ui.BaseActivity;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(BaseActivity activity);
}