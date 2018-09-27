package ecommerce.ycrathi.com.e_commerceapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import javax.inject.Inject;

import ecommerce.ycrathi.com.e_commerceapp.ApplicationClass;
import retrofit2.Retrofit;

public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ApplicationClass) getApplication()).getNetComponent().inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);;
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //Home/back button
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
