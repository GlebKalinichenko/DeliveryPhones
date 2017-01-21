package com.develop.gleb.deliveryphones;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.develop.gleb.deliveryphones.databinding.ItemModeBinding;
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

        public ModeViewHolder(ItemModeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindEntity(int position){
            ModeEntity entity = entities.get(position);
            binding.setModeEntity(entity);
        }
    }
}
