package com.develop.gleb.deliveryphones.fragments.photo;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.develop.gleb.deliveryphones.BaseApplication;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.adapters.PhotoAdapter;
import com.develop.gleb.deliveryphones.di.component.SendPhotosFragmentComponent;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.fragments.base.BasePhotoFragment;
import com.develop.gleb.deliveryphones.mvp.base.IBaseLogicView;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphotos.ISendPhotoPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphotos.ISendPhotoView;
import java.util.List;
import javax.inject.*;
import static android.view.View.GONE;

public class SendPhotoFragment extends BasePhotoFragment implements IBaseLogicView, ISendPhotoView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    @Inject
    public ISendPhotoPresenter presenter;

    public static SendPhotoFragment getInstance() {
        SendPhotoFragment fragment = new SendPhotoFragment();
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
        SendPhotosFragmentComponent component = ((BaseApplication) getActivity().getApplication())
                .getSendPhotosFragmentComponent(context, this);
        component.inject(this);
    }

    @Override
    public void initWidgets(View view) {
        super.initWidgets(view);
        actionButton = (FloatingActionButton) view.findViewById(R.id.action_button);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        handler = new Handler();

        actionButton.setOnClickListener(i -> {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    });
                    List<PhotoEntity> photoEntities = adapter.getPhotos();
                    presenter.uploadPhotos(photoEntities);
                }
            });
            t.start();
        });
    }

    @Override
    public void initAdapter(List<PhotoEntity> photos) {
        Context context = getActivity();
        adapter = new PhotoAdapter(photos, context);
        StaggeredGridLayoutManager staggeredLayoutManager = new StaggeredGridLayoutManager(VERTICAL_LAYOUT, StaggeredGridLayoutManager.VERTICAL);
        photoList.setLayoutManager(staggeredLayoutManager);
        photoList.setHasFixedSize(true);
        photoList.setAdapter(adapter);
        progressBar.setVisibility(GONE);
    }

    @Override
    public void uploadSuccess() {
        Context context = getActivity();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, R.string.send_photos_success, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(GONE);
            }
        });
    }

    @Override
    public void uploadUnsuccess() {
        Context context = getActivity();
        Toast.makeText(context, R.string.send_photos_unsuccess, Toast.LENGTH_SHORT).show();
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
