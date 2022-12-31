package com.example.famtrack.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famtrack.R;
import com.example.famtrack.api.Wallet;
import com.example.famtrack.database.WalletDatabaseModel;
import com.example.famtrack.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class SmallWalletAdapter extends RecyclerView.Adapter<SmallWalletAdapter.SmallWalletViewHolder> {

    private List<WalletDatabaseModel> walletList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private int lastPosition = RecyclerView.NO_POSITION;
    private View lastView = null;

    public SmallWalletAdapter(MainViewModel viewModel, LifecycleOwner lifecycleOwner) {

        viewModel.getAllWallet().observe(lifecycleOwner, walletData -> {
            walletList = walletData;
            notifyDataSetChanged();
        });

    }

    @NonNull
    @Override
    public SmallWalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_small_wallet, parent, false);
        return new SmallWalletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmallWalletViewHolder holder, int position) {
        if (position != RecyclerView.NO_POSITION) {
            WalletDatabaseModel walletData = walletList.get(position);
            holder.getIconWallet().setImageResource(walletData.getIvWallet());
            holder.getTvWalletName().setText(walletData.getTvWalletName());
            holder.getTvWalletBalance().setText(walletData.getTvCurrentBalance());
        }
    }

    @Override
    public int getItemCount() {
        return walletList == null ? 0 : walletList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(@Nullable String selectWalletId);
    }

    public class SmallWalletViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView iconWallet;
        private final TextView tvWalletName, tvWalletBalance;

        public SmallWalletViewHolder(@NonNull View itemView) {
            super(itemView);

            iconWallet = itemView.findViewById(R.id.iv_wallet);
            tvWalletName = itemView.findViewById(R.id.tv_wallet_name);
            tvWalletBalance = itemView.findViewById(R.id.tv_current_balance);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {

                // If the currently selected item's position is not the same as the last position
                if (lastPosition != getAdapterPosition()) {

                    // Set new selected item's position as the last selected position
                    lastPosition = getAdapterPosition();
                    // Activate the current view
                    view.setActivated(true);

                    // If last view is not null
                    if (lastView != null) {

                        // Deactivated last view
                        lastView.setActivated(false);
                    }

                    // Save currently selected view as last selected view
                    lastView = view;

                } else {

                    // Set last position as -1 (For not selecting any item)
                    lastPosition = RecyclerView.NO_POSITION;

                    // Deactivated last view
                    lastView.setActivated(false);

                    // Set last view as null (Because no item is selected)
                    lastView = null;
                }

                if (lastPosition != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(walletList.get(lastPosition).getUid());
                } else {
                    onItemClickListener.onItemClick(null);
                }
            }
        }

        public ImageView getIconWallet() {
            return iconWallet;
        }

        public TextView getTvWalletName() {
            return tvWalletName;
        }

        public TextView getTvWalletBalance() {
            return tvWalletBalance;
        }
    }
}
