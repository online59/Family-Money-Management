package com.example.famtrack.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famtrack.R;
import com.example.famtrack.api.Payment;
import com.example.famtrack.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    private List<Payment> dataList = new ArrayList<>();
    private final MutableLiveData<List<Payment>> paymentDataList = new MutableLiveData<>();
    private OnItemClickListener onItemClickListener;

    public PaymentAdapter(String path, MainViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.requestAllPayment(path).observe(lifecycleOwner, paymentData -> {
            dataList = paymentData;
            paymentDataList.setValue(paymentData);
            notifyDataSetChanged();
        });

    }

    public LiveData<List<Payment>> getDataList() {
        return paymentDataList;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_payment, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        if (position != RecyclerView.NO_POSITION) {
            Payment item = dataList.get(position);
            holder.getIvPayment().setImageResource(R.drawable.united_kingdom);
            holder.getTvPaymentType().setText(item.getTransCategory());
            holder.getTvAmount().setText(item.getTransTotal());
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageView ivPayment;
        private final TextView tvPaymentType, tvAmount;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPayment = itemView.findViewById(R.id.iv_payment);
            tvPaymentType = itemView.findViewById(R.id.tv_payment_type);
            tvAmount = itemView.findViewById(R.id.tv_amount);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(getAdapterPosition());
            }
        }

        public ImageView getIvPayment() {
            return ivPayment;
        }

        public TextView getTvPaymentType() {
            return tvPaymentType;
        }

        public TextView getTvAmount() {
            return tvAmount;
        }
    }
}
