package com.develop.gleb.deliveryphones.fragments.photo;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.develop.gleb.deliveryphones.BaseApplication;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.adapters.PhotoAdapter;
import com.develop.gleb.deliveryphones.di.component.ReceivePhotosFragmentComponent;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.fragments.base.BasePhotoFragment;
import com.develop.gleb.deliveryphones.mvp.base.IBaseLogicView;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos.IReceivePhotosPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos.IReceivePhotosView;
import java.util.List;
import javax.inject.*;

public class ReceivePhotoFragment extends BasePhotoFragment implements IBaseLogicView, IReceivePhotosView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    @Inject
    public IReceivePhotosPresenter presenter;
    private PhotoAdapter adapter;

    public static ReceivePhotoFragment getInstance() {
        ReceivePhotoFragment fragment = new ReceivePhotoFragment();

        return fragment;
    }

    @Override
    protected void setButtonDrawable() {

    }

    @Override
    protected void clearPhotos() {
        presenter.clearPhotos();
    }

    @Override
    public void initInject() {
        Activity context = getActivity();
        ReceivePhotosFragmentComponent component = ((BaseApplication) getActivity().getApplication())
                .getReceivePhotosFragmentComponent(context, this);
        component.inject(this);
    }

    @Override
    public void initWidgets(View view) {
        super.initWidgets(view);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        handler = new Handler();
        actionButton.setVisibility(View.GONE);
    }

    @Override
    public void initAdapter(List<PhotoEntity> photos) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        Context context = getActivity();
                        adapter = new PhotoAdapter(photos, context);
                        photoList.setAdapter(adapter);
                        StaggeredGridLayoutManager staggeredLayoutManager = new StaggeredGridLayoutManager(VERTICAL_LAYOUT, StaggeredGridLayoutManager.VERTICAL);
                        photoList.setLayoutManager(staggeredLayoutManager);
                        photoList.setHasFixedSize(true);
                    }
                });
            }
        });
        t.start();
    }

    @Override
    public void downloadSuccess() {
        Context context = getActivity();

        Toast.makeText(context, "Download success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearSuccess() {
        Context context = getActivity();

        Toast.makeText(context, "Clear success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearUnsuccess() {
        Context context = getActivity();

        Toast.makeText(context, "Clear unsuccess", Toast.LENGTH_SHORT).show();
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
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
