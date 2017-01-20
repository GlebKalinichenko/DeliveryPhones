package com.develop.gleb.deliveryphones.fragments.phones;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.adapters.PhonesAdapter;
import com.develop.gleb.deliveryphones.fragments.base.BasePhoneFragment;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.develop.gleb.deliveryphones.mvp.implementations.ReceivePhonesPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesView;

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

        actionButton.setOnClickListener(i -> savePhones());
    }

    private void savePhones(){
        progressBarReceive.setVisibility(View.VISIBLE);
        progressBarReceive.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<PhoneEntity> entities = adapter.getEntities();
                Context context = getActivity(); presenter.savePhones(context, entities);
            }
        }, 3000);
    }

    @Override
    protected void setButtonDrawable() {
        actionButton.setImageResource(android.R.drawable.ic_menu_save);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause(sharedPreferencesHelper);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy(sharedPreferencesHelper);
    }

    @Override
    public void receivePhoneSuccess(List<PhoneEntity> entityList) {
        progressBarReceive.setVisibility(View.GONE);
        Context context = getActivity();

        if (entityList.size() > 0)
            initializeAdapter(context, entityList);
    }

    /**
     * Initializing adapter for display contacts
     * @param context            Context of activity
     * @param entityList         List of phones
     * */
    private void initializeAdapter(Context context, List<PhoneEntity> entityList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        phoneList.setLayoutManager(layoutManager);
        adapter = new PhonesAdapter(context, entityList);
        phoneList.setAdapter(adapter);
    }

    @Override
    public void receivePhoneUnsuccess() {

    }

    @Override
    public void savePhonesFinish() {
        Context context = getActivity();
        Toast.makeText(context, R.string.save_finish, Toast.LENGTH_SHORT).show();

        progressBarReceive.setVisibility(View.GONE);
    }

    @Override
    public void clearSuccess() {
        Context context = getActivity();
        Toast.makeText(context, R.string.clean_finish, Toast.LENGTH_SHORT).show();
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

    @Override
    public void initInject() {

    }
}
