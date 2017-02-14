package com.develop.gleb.deliveryphones.fragments.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.develop.gleb.deliveryphones.IBaseView;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.adapters.PhotoAdapter;
import com.develop.gleb.deliveryphones.mvp.base.IBaseLogicView;

public abstract class BasePhotoFragment extends Fragment implements IBaseLogicView, IBaseView {
    protected final int VERTICAL_LAYOUT = 1;
    protected final int GRID_LAYOUT = 2;
    protected boolean isViewList = true;
    protected FloatingActionButton actionButton;
    protected ProgressBar progressBar;
    protected PhotoAdapter adapter;
    protected Handler handler;
    protected RecyclerView photoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        initWidgets(view);
        initInject();
        return view;
    }

    @Override
    public void initWidgets(View view) {
        photoList = (RecyclerView) view.findViewById(R.id.photo_list);
    }

    protected abstract void setButtonDrawable();

    protected abstract void clearPhotos();

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.photo_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.view_of_list_photos:
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) photoList.getLayoutManager();
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
                photoList.setLayoutManager(layoutManager);
                break;

            case R.id.clear_photos:
                clearPhotos();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
