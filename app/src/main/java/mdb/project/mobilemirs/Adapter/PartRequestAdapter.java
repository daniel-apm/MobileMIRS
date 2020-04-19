package mdb.project.mobilemirs.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mdb.project.mobilemirs.Interface.IPartRequest;
import mdb.project.mobilemirs.Manager.SessionManager;
import mdb.project.mobilemirs.Model.PartRequestModel;
import mdb.project.mobilemirs.R;
import mdb.project.mobilemirs.View.Activity.HomeActivity;
import mdb.project.mobilemirs.View.Activity.LoginActivity;

public class PartRequestAdapter extends RecyclerView.Adapter<PartRequestAdapter.PartRequestViewHolder> {

    private Context context;
    private IPartRequest adapterCallback;
    private ArrayList<PartRequestModel> partRequestList;

    public ArrayList<PartRequestModel> getPartRequestList() {
        return partRequestList;
    }

    public PartRequestAdapter(Context context, IPartRequest adapterCallback, ArrayList<PartRequestModel> partRequestList) {
        this.context = context;
        this.adapterCallback = adapterCallback;
        this.partRequestList = partRequestList;
    }

    @NonNull
    @Override
    public PartRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_part_request, parent, false);
        return new PartRequestViewHolder(view, adapterCallback, partRequestList);
    }

    @Override
    public void onBindViewHolder(@NonNull PartRequestViewHolder holder, int position) {
        PartRequestModel currentItem = partRequestList.get(position);
        holder.tvNumber.setText(String.valueOf(position + 1));
        holder.tvDocument.setText(String.format("OPC-%s", currentItem.getDocument()));
        holder.tvDate.setText(currentItem.getDate());
        holder.tvStatus.setText(currentItem.getStatus());
    }

    @Override
    public int getItemCount() {
        return partRequestList.size();
    }

    public static class PartRequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvNumber, tvDocument, tvDate, tvStatus;
        private IPartRequest adapterCallback;
        private ArrayList<PartRequestModel> partRequestList;
        private ImageView imgDelete;


        public PartRequestViewHolder(@NonNull View itemView, IPartRequest adapterCallback, ArrayList<PartRequestModel> partRequestList) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.item_number);
            tvDocument = itemView.findViewById(R.id.item_document);
            tvDate = itemView.findViewById(R.id.item_date);
            tvStatus = itemView.findViewById(R.id.item_status);
            imgDelete = itemView.findViewById(R.id.image_delete);
            this.partRequestList = partRequestList;
            this.adapterCallback = adapterCallback;
            imgDelete.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_delete:
                    adapterCallback.removeAdapter(getAdapterPosition());
                    break;
                default:
                    adapterCallback.getDocumentId(partRequestList.get(getAdapterPosition()).getDocumentId());
                    break;
            }
        }
    }

}
