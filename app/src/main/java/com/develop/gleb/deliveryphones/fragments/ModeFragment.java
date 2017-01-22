package com.develop.gleb.deliveryphones.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.gleb.deliveryphones.IBaseView;
import com.develop.gleb.deliveryphones.ModeAdapter;
import com.develop.gleb.deliveryphones.ModeEntity;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.mvp.base.IBaseLogicView;
import java.util.List;
import javax.inject.*;

public class ModeFragment extends Fragment implements IBaseLogicView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    public static final String MODE_LIST_KEY = "ModeListKey";
    public static final int MODE_LIST_KEY_INDEX = 1;
    private RecyclerView modeList;
    private List<ModeEntity> entities;

    public static ModeFragment getInstance(List<ModeEntity> entities) {
        ModeFragment fragment = new ModeFragment();

        Bundle bundle = new Bundle();
        SparseArray array = new SparseArray();
        array.put(MODE_LIST_KEY_INDEX, entities);
        bundle.putSparseParcelableArray(MODE_LIST_KEY, array);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SparseArray array = getArguments().getSparseParcelableArray(MODE_LIST_KEY);
        entities = (List<ModeEntity>) array.get(MODE_LIST_KEY_INDEX);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mode, container, false);
        initWidgets(view);
        initAdapter();
        return view;
    }

    @Override
    public void initWidgets(View view) {
        modeList = (RecyclerView) view.findViewById(R.id.mode_list);
    }

    private void initAdapter(){
        Context context = getActivity();
        ModeAdapter adapter = new ModeAdapter(entities, context);
        modeList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        modeList.setLayoutManager(layoutManager);
        modeList.setAdapter(adapter);
    }
}
