package mdb.project.mobilemirs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mdb.project.mobilemirs.Model.DetailPartRequestModel;
import mdb.project.mobilemirs.R;

public class DetailPartRequestAdapter extends RecyclerView.Adapter<DetailPartRequestAdapter.DetailPartRequestViewHolder> {

    private Context context;
    private ArrayList<DetailPartRequestModel> detailPartRequestList;

    public DetailPartRequestAdapter(Context context, ArrayList<DetailPartRequestModel> detailPartRequestList) {
        this.context = context;
        this.detailPartRequestList = detailPartRequestList;
    }

    @NonNull
    @Override
    public DetailPartRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_part_request, parent, false);
        return new DetailPartRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailPartRequestViewHolder holder, int position) {
        DetailPartRequestModel currentItem = detailPartRequestList.get(position);
        holder.tvNumber.setText(String.valueOf(position+1));
        holder.tvPart.setText(currentItem.getPartName());
        holder.tvMerk.setText(currentItem.getMerk());
        holder.tvQty.setText(String.valueOf(currentItem.getQuantity()));
        holder.tvDate.setText(currentItem.getReqDate());
    }

    @Override
    public int getItemCount() {
        return detailPartRequestList.size();
    }

    public static class DetailPartRequestViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNumber, tvPart, tvMerk, tvQty, tvDate;

        public DetailPartRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.item_number);
            tvPart = itemView.findViewById(R.id.item_part_name);
            tvMerk = itemView.findViewById(R.id.item_merk);
            tvQty = itemView.findViewById(R.id.item_qty);
            tvDate = itemView.findViewById(R.id.item_req_date);
        }
    }
}
