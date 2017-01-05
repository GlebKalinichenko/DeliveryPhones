package com.example.gleb.deliveryphones.fragments.phones;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.adapters.PhonesAdapter;
import com.example.gleb.deliveryphones.fragments.base.BasePhoneFragment;
import com.example.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.example.gleb.deliveryphones.mvp.implementations.ReceivePhonesPresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesPresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesView;

import java.util.List;

public class ReceivePhonesFragment extends BasePhoneFragment implements IReceivePhonesView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IReceivePhonesPresenter presenter = new ReceivePhonesPresenter(this);
    private RecyclerView phoneList;
    private FloatingActionButton actionButton;
    private ProgressBar progressBarReceive;
    private PhonesAdapter adapter;
    private SharedPreferencesHelper sharedPreferencesHelper;

    public static ReceivePhonesFragment getInstance() {
        ReceivePhonesFragment fragment = new ReceivePhonesFragment();
        return fragment;
    }

    @Override
    public void initWidgets(View view) {
        phoneList = (RecyclerView) view.findViewById(R.id.phone_list);
        actionButton = (FloatingActionButton) view.findViewById(R.id.action_button);
        progressBarReceive = (ProgressBar) view.findViewById(R.id.progressBarReceive);

        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(null);

        setButtonDrawable();

        actionButton.setOnClickListener(i -> {
            List<PhoneEntity> entities = adapter.getEntities();
            Context context = getActivity(); presenter.savePhones(context, entities);});
    }

    @Override
    protected void setButtonDrawable() {
        actionButton.setImageResource(android.R.drawable.ic_menu_save);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.receivePhones();
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferencesHelper.saveDisplayDialogOnChangeOrientation(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sharedPreferencesHelper.saveDisplayDialogOnChangeOrientation(true);

        presenter = null;
    }

    @Override
    public void receivePhoneSuccess(List<PhoneEntity> entityList) {
        Context context = getActivity();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        phoneList.setLayoutManager(layoutManager);
        adapter = new PhonesAdapter(context, entityList);
        phoneList.setAdapter(adapter);

        progressBarReceive.setVisibility(View.GONE);
    }

    @Override
    public void receivePhoneUnsuccess() {

    }

    @Override
    public void savePhonesFinish() {
        Context context = getActivity();
        Toast.makeText(context, "Saved was finished", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearSuccess() {
        Context context = getActivity();
        Toast.makeText(context, "Clear phones is finished", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.clear_phones:
                Log.d(LOG_TAG, "Clear phones button is clicked");
                presenter.clearPhones();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
