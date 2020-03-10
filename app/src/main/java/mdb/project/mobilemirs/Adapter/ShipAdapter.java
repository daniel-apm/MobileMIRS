package mdb.project.mobilemirs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mdb.project.mobilemirs.Interface.IShip;
import mdb.project.mobilemirs.Model.ShipModel;
import mdb.project.mobilemirs.R;

public class ShipAdapter extends RecyclerView.Adapter<ShipAdapter.ShipVIewHolder> {

    private Context context;
    private IShip adapterCallback;
    private ArrayList<ShipModel> shipList;

    public ShipAdapter(Context context, IShip adapterCallback, ArrayList<ShipModel> shipList) {
        this.context = context;
        this.adapterCallback = adapterCallback;
        this.shipList = shipList;
    }

    @NonNull
    @Override
    public ShipVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ship, parent, false);
        return new ShipVIewHolder(view, adapterCallback, shipList);
    }

    @Override
    public void onBindViewHolder(@NonNull ShipVIewHolder holder, int position) {
        ShipModel currentItem = shipList.get(position);
        holder.shipImage.setImageResource(currentItem.getShipImage());
        holder.shipText.setText(currentItem.getShipCode());
    }

    @Override
    public int getItemCount() {
        return shipList.size();
    }

    public static class ShipVIewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView shipImage;
        private TextView shipText;
        private IShip adapterCallback;
        private ArrayList<ShipModel> shipList;

        private ShipVIewHolder(@NonNull View itemView, IShip adapterCallback, ArrayList<ShipModel> shipList) {
            super(itemView);
            this.adapterCallback = adapterCallback;
            this.shipList = shipList;
            shipImage = itemView.findViewById(R.id.item_image_view_ship);
            shipText = itemView.findViewById(R.id.item_text_view_ship);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapterCallback.selectShip(shipList.get(getAdapterPosition()).getShipCode(), shipList.get(getAdapterPosition()).getShipName());
        }
    }

}
