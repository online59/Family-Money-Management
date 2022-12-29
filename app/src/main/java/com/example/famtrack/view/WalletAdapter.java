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
import com.example.famtrack.api.Wallet;
import com.example.famtrack.utils.Utils;
import com.example.famtrack.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletViewHolder> {

    private List<Wallet> walletList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public WalletAdapter(String path, MainViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.requestAllWallet(path).observe(lifecycleOwner, requestedWalletList -> {
            walletList = requestedWalletList;
            notifyDataSetChanged();
        });
    }

    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_wallet, parent, false);
        return new WalletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder holder, int position) {
        if (position != RecyclerView.NO_POSITION) {
            Wallet item = walletList.get(position);
            holder.getIconWallet().setImageResource(R.drawable.united_states);
            holder.getTvCurrentBalance().setText(String.valueOf(item.getGroupBalance()));
            holder.getTvWalletName().setText(item.getGroupName());
            holder.getTvLastActiveTime().setText(Utils.getDate(item.getGroupActiveTime()));
            holder.getTvMemberCount().setText(String.valueOf(item.getGroupMember().size()));
        }
    }

    @Override
    public int getItemCount() {
        return walletList == null ? 0 : walletList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class WalletViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView iconWallet;
        private final TextView tvWalletName, tvCurrentBalance, tvLastActiveTime, tvMemberCount;

        public WalletViewHolder(@NonNull View itemView) {
            super(itemView);
            iconWallet = itemView.findViewById(R.id.iv_wallet);
            tvWalletName = itemView.findViewById(R.id.tv_wallet_name);
            tvCurrentBalance = itemView.findViewById(R.id.tv_current_balance);
            tvLastActiveTime = itemView.findViewById(R.id.tv_last_active_time);
            tvMemberCount = itemView.findViewById(R.id.tv_member_count);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(getAdapterPosition());
            }
        }

        public ImageView getIconWallet() {
            return iconWallet;
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
