package com.develop.gleb.deliveryphones;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.develop.gleb.deliveryphones.databinding.ItemModeBinding;
import com.develop.gleb.deliveryphones.events.ReceivePhonesEvent;
import com.develop.gleb.deliveryphones.events.SendPhonesEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ModeAdapter extends RecyclerView.Adapter<ModeAdapter.ModeViewHolder> {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private List<ModeEntity> entities;
    private Context context;

    public ModeAdapter(List<ModeEntity> entities, Context context) {
        this.entities = entities;
        this.context = context;
    }

    @Override
    public ModeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemModeBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_mode, parent, false);

        ModeViewHolder viewHolder = new ModeViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ModeViewHolder holder, int position) {
        holder.bindEntity(position);
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class ModeViewHolder extends RecyclerView.ViewHolder {
        private ItemModeBinding binding;
        private Button openButton;

        public ModeViewHolder(ItemModeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindEntity(int position){
            ModeEntity entity = entities.get(position);
            binding.setModeEntity(entity);

            View view = binding.getRoot();
            openButton = (Button) view.findViewById(R.id.button_open);
            openButton.setOnClickListener(i -> {
                ModeIdentifier id = entity.getId();
                switch (id){
                    case RECEIVE_PHONES:
                        EventBus.getDefault().post(new ReceivePhonesEvent());
                        break;

                    case SEND_PHONES:
                        EventBus.getDefault().post(new SendPhonesEvent());
                        break;
                }
            });
        }
    }
}
