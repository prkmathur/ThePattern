package com.app.thenhpattern.model.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.thenhpattern.databinding.RowItemSettingBinding;
import com.app.thenhpattern.model.handler.ISettingItemEventListner;
import com.app.thenhpattern.model.vo.SettingModel;
import java.util.ArrayList;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingsViewHolder> {

    private ArrayList<SettingModel> settingModels;
    private ISettingItemEventListner iSettingItemEventListner;

    public SettingAdapter(ArrayList<SettingModel> settingModels,ISettingItemEventListner iSettingItemEventListner) {
        this.settingModels = settingModels;
        this.iSettingItemEventListner = iSettingItemEventListner;
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowItemSettingBinding itemBinding = RowItemSettingBinding.inflate(layoutInflater, parent, false);
        itemBinding.setCallback(iSettingItemEventListner);
        return new SettingsViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        SettingModel item = settingModels.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return settingModels.size();
    }

    // ViewHolder

    public class SettingsViewHolder extends RecyclerView.ViewHolder{

        private final RowItemSettingBinding binding;

        public SettingsViewHolder(@NonNull RowItemSettingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SettingModel item) {
            binding.setModel(item);
            binding.executePendingBindings();
        }
    }
}