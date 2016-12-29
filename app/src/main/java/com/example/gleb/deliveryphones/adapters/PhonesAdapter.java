package com.example.gleb.deliveryphones.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.databinding.ItemPhoneBinding;

import java.util.List;

public class PhonesAdapter extends RecyclerView.Adapter<PhonesAdapter.PhonesViewHolder> {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private Context context;
    private List<PhoneEntity> entities;

    public PhonesAdapter(Context context, List<PhoneEntity> entities) {
        this.context = context;
        this.entities = entities;
    }

    @Override
    public PhonesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemPhoneBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_phone, parent, false);

        PhonesViewHolder holder = new PhonesViewHolder(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(PhonesViewHolder holder, int position) {
        holder.bindItem(position);
    }

    public List<PhoneEntity> getEntities() {
        return entities;
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class PhonesViewHolder extends RecyclerView.ViewHolder {
        private ImageView deleteButton;
        private ItemPhoneBinding binding;

        public PhonesViewHolder(ItemPhoneBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(int position){
            PhoneEntity entity = entities.get(position);
            binding.setPhoneEntity(entity);

            deleteButton = (ImageView) binding.getRoot().findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(i -> {entities.remove(position); notifyDataSetChanged();});
        }
    }
}
