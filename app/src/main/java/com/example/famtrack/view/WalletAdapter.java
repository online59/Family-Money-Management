package com.example.famtrack.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famtrack.R;
import com.example.famtrack.database.WalletModel;
import com.example.famtrack.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletViewHolder> implements View.OnClickListener {

    private List<WalletModel> dataList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public WalletAdapter(MainViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getAllWallet().observe(lifecycleOwner, walletData -> {
            dataList = walletData;
            notifyDataSetChanged();
        });
    }

    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_wallet, parent, false);
        view.setOnClickListener(this);
        return new WalletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder holder, int position) {
        if (position != RecyclerView.NO_POSITION) {
            WalletModel item = dataList.get(position);
            holder.getIvWallet().setImageResource(item.getIvWallet());
            holder.getTvCurrentBalance().setText(item.getTvCurrentBalance());
            holder.getTvWalletName().setText(item.getTvWalletName());
            holder.getTvLastActiveTime().setText(item.getTvLastActive());
            holder.getTvMemberCount().setText(item.getTvMemberCount());
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public static class WalletViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivWallet;
        private final TextView tvWalletName, tvCurrentBalance, tvLastActiveTime, tvMemberCount;
        public WalletViewHolder(@NonNull View itemView) {
            super(itemView);
            ivWallet = itemView.findViewById(R.id.iv_wallet);
            tvWalletName = itemView.findViewById(R.id.tv_wallet_name);
            tvCurrentBalance = itemView.findViewById(R.id.tv_current_balance);
            tvLastActiveTime = itemView.findViewById(R.id.tv_last_active_time);
            tvMemberCount = itemView.findViewById(R.id.tv_member_count);
        }

        public ImageView getIvWallet() {
            return ivWallet;
        }

        public TextView getTvWalletName() {
            return tvWalletName;
        }

        public TextView getTvCurrentBalance() {
            return tvCurrentBalance;
        }

        public TextView getTvLastActiveTime() {
            return tvLastActiveTime;
        }

        public TextView getTvMemberCount() {
            return tvMemberCount;
        }
    }
}
