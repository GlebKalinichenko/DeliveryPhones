package com.example.gleb.deliveryphones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import rx.Observable;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private FragmentHelper helper = FragmentHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPhones();
    }

    private void loadPhones(){
        PhonesFragment fragment = PhonesFragment.getInstance();
        helper.loadFragment(this, R.id.container_phones, fragment);
    }
}
