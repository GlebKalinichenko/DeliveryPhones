package com.example.gleb.deliveryphones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements IPhoneView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IPhonePresenter presenter = new PhonePresenter(this);
    private ListView phoneList;
    private Observable<PhoneEntity> phoneObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
    }

    @Override
    public void initWidgets() {
        phoneList = (ListView) findViewById(R.id.phone_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<PhoneEntity> entities = presenter.getPhones(this);
        phoneObservable = Observable.from(entities);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();

        phoneObservable.subscribe(i -> i.getPhones());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
