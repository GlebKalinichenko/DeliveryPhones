package com.example.gleb.deliveryphones.fragments.phones;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.adapters.PhonesAdapter;
import com.example.gleb.deliveryphones.fragments.base.BasePhoneFragment;
import com.example.gleb.deliveryphones.mvp.implementations.ReceivePhonesPresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesPresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesView;

import java.util.List;

public class ReceivePhonesFragment extends BasePhoneFragment implements IReceivePhonesView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IReceivePhonesPresenter presenter = new ReceivePhonesPresenter(this);
    private RecyclerView phoneList;
    private FloatingActionButton syncButton;
    private ProgressBar progressBar;
    private PhonesAdapter adapter;

    public static ReceivePhonesFragment getInstance() {
        ReceivePhonesFragment fragment = new ReceivePhonesFragment();
        return fragment;
    }

    @Override
    public void initWidgets(View view) {
        phoneList = (RecyclerView) view.findViewById(R.id.phone_list);
        syncButton = (FloatingActionButton) view.findViewById(R.id.sync_button);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        setButtonDrawable();

        syncButton.setOnClickListener(i -> {List<PhoneEntity> entities = adapter.getEntities();
            Context context = getActivity(); presenter.savePhones(context, entities);});
    }

    @Override
    protected void setButtonDrawable() {
        syncButton.setImageResource(R.drawable.save);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.receivePhones();
    }

    @Override
    public void receivePhoneSuccess(List<PhoneEntity> entityList) {
        Context context = getActivity();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        phoneList.setLayoutManager(layoutManager);
        adapter = new PhonesAdapter(context, entityList);
        phoneList.setAdapter(adapter);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void receivePhoneUnsuccess() {

    }
}