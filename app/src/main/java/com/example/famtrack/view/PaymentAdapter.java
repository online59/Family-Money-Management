package com.example.famtrack.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famtrack.R;
import com.example.famtrack.api.Payment;
import com.example.famtrack.utils.Utils;
import com.example.famtrack.vm.MainViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    private static final String TAG = "com.example.famtrack.view.PaymentAdapter";
    private List<Payment> sortedPaymentList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public PaymentAdapter(String path, MainViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.requestAllPayment(path).observe(lifecycleOwner, requestedPaymentList -> {

            // Sort payment by its transaction date
            Collections.sort(requestedPaymentList);
            sortedPaymentList = requestedPaymentList;

            notifyDataSetChanged();
        });
    }

    // The method compare a sorted list of payment and return boolean value of
    // true if the payment at the specific position is needed to has a new header
    // and return false if not to
    public boolean isHeader(int position) {
        // Because the payment list maybe null, so we check for null
        if (sortedPaymentList == null) {
            return false;
        }

        // If there is no data, not drawing header
        if (sortedPaymentList.size() == 0) {
            return false;
        }

        // At the first item of the list, add a header for it
        if (position == 0) {
            return true;
        }

        // Compare date of two items next to each other whether they should have their new header
        // or should just follow the same header
        String currentHeader = Utils.getDate(sortedPaymentList.get(position).getTransDate());
        String previousHeader = Utils.getDate(sortedPaymentList.get(position - 1).getTransDate());

        // If the current header name match the previous header, not drawing header, else draw header
        return !currentHeader.equalsIgnoreCase(previousHeader);
    }

    // This method return a string value for header if the header is needed to be created
    public String getHeader(int position) {

        if (sortedPaymentList == null || sortedPaymentList.size() == 0) {
            return "";
        }

        // Text to put as a header
        return Utils.getDate(sortedPaymentList.get(position).getTransDate());
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
            Payment item = sortedPaymentList.get(position);
            holder.getIconPayment().setImageResource(R.drawable.united_kingdom);
            holder.getTvPaymentType().setText(item.getTransCategory());
            holder.getTvAmount().setText(item.getTransTotal());
        }
    }

    @Override
    public int getItemCount() {
        return sortedPaymentList == null ? 0 : sortedPaymentList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView iconPayment;
        private final TextView tvPaymentType, tvAmount;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

            iconPayment = itemView.findViewById(R.id.iv_payment);
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

        public ImageView getIconPayment() {
            return iconPayment;
        }

        public TextView getTvPaymentType() {
            return tvPaymentType;
        }

        public TextView getTvAmount() {
            return tvAmount;
        }
    }
}
