package mdb.project.mobilemirs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mdb.project.mobilemirs.Interface.IEngine;
import mdb.project.mobilemirs.Model.EngineModel;
import mdb.project.mobilemirs.R;

public class EngineAdapter extends RecyclerView.Adapter<EngineAdapter.EngineViewHolder> {

    private Context context;
    private ArrayList<EngineModel> engineList;
    private IEngine adapterCallback;

    public EngineAdapter(Context context, ArrayList<EngineModel> engineList, IEngine adapterCallback) {
        this.context = context;
        this.engineList = engineList;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public EngineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_engine, parent, false);
        return new EngineViewHolder(view, engineList, adapterCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull EngineViewHolder holder, int position) {
        EngineModel currentEngineModel = engineList.get(position);
        holder.engineText.setText(currentEngineModel.getEngineName());
    }

    @Override
    public int getItemCount() {
        return engineList.size();
    }

    public static class EngineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView engineText;
        private ArrayList<EngineModel> engineList;
        private IEngine adapterCallback;

        private EngineViewHolder(@NonNull View itemView, ArrayList<EngineModel> engineList, IEngine adapterCallback) {
            super(itemView);
            this.engineList = engineList;
            this.adapterCallback = adapterCallback;
            engineText = itemView.findViewById(R.id.item_text_view_engine);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapterCallback.selectEngine(engineList.get(getAdapterPosition()).getEngineName());
        }
    }
}
