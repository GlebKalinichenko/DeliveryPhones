package com.develop.gleb.deliveryphones.fragments.photo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.develop.gleb.deliveryphones.BaseApplication;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.adapters.PhotoAdapter;
import com.develop.gleb.deliveryphones.di.component.SendPhotosFragmentComponent;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.fragments.base.BaseFragment;
import com.develop.gleb.deliveryphones.mvp.base.IBaseLogicView;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoView;

import java.util.List;

import javax.inject.*;

public class SendPhotoFragment extends BaseFragment implements IBaseLogicView, ISendPhotoView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private final int VERTICAL_LAYOUT = 1;
    private final int GRID_LAYOUT = 2;
    private RecyclerView sendPhotoList;
    @Inject
    public ISendPhotoPresenter presenter;
    private FloatingActionButton actionButton;
    private PhotoAdapter adapter;
    private boolean isViewList = true;

    public static SendPhotoFragment getInstance() {
        SendPhotoFragment fragment = new SendPhotoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_photo, container, false);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        initWidgets(view);
        initInject();
        return view;
    }

    @Override
    protected void setButtonDrawable() {

    }

    @Override
    public void initInject() {
        Activity context = getActivity();
        SendPhotosFragmentComponent component = ((BaseApplication) getActivity().getApplication())
                .getSendPhotosFragmentComponent(context, this);
        component.inject(this);
    }

    @Override
    public void initWidgets(View view) {
        sendPhotoList = (RecyclerView) view.findViewById(R.id.send_photo_list);
        actionButton = (FloatingActionButton) view.findViewById(R.id.action_button);
    }

    @Override
    public void initAdapter(List<PhotoEntity> photos) {
        Context context = getActivity();
        adapter = new PhotoAdapter(photos, context);
        StaggeredGridLayoutManager staggeredLayoutManager = new StaggeredGridLayoutManager(VERTICAL_LAYOUT, StaggeredGridLayoutManager.VERTICAL);
        sendPhotoList.setLayoutManager(staggeredLayoutManager);
        sendPhotoList.setHasFixedSize(true);
        sendPhotoList.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.photo_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.view_of_list_photos:
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) sendPhotoList.getLayoutManager();
                if (isViewList) {
                    item.setIcon(R.drawable.list);
                    layoutManager.setSpanCount(GRID_LAYOUT);
                    isViewList = false;
                }
                else {
                    item.setIcon(R.drawable.grid);
                    layoutManager.setSpanCount(VERTICAL_LAYOUT);
                    isViewList = true;
                }
                sendPhotoList.setLayoutManager(layoutManager);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}