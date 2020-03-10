package mdb.project.mobilemirs.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mdb.project.mobilemirs.Model.ItemHolder;
import mdb.project.mobilemirs.R;

import static mdb.project.mobilemirs.Model.ItemHolder.DISPLAY_LAYOUT;
import static mdb.project.mobilemirs.Model.ItemHolder.VISUAL_LAYOUT;
import static mdb.project.mobilemirs.Model.ItemHolder.VOLTMETER_LAYOUT;

public class InputSheetAdapter extends RecyclerView.Adapter {

    private List<ItemHolder> itemHolderList;

    public InputSheetAdapter(List<ItemHolder> itemHolderList) {
        this.itemHolderList = itemHolderList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (itemHolderList.get(position).getViewType()) {
            case 0:
                return VOLTMETER_LAYOUT;
            case 1:
                return DISPLAY_LAYOUT;
            case 2:
                return VISUAL_LAYOUT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VOLTMETER_LAYOUT:
                View voltmeterLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.input_text_item, parent, false);
                return new VoltmeterLayout(voltmeterLayout);
            case DISPLAY_LAYOUT:
                View displayLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.input_text_item, parent, false);
                return new DisplayLayout(displayLayout);
            case VISUAL_LAYOUT:
                View visualLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.input_image_item, parent, false);
                return new VisualLayout(visualLayout);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (itemHolderList.get(position).getViewType()) {
            case VOLTMETER_LAYOUT:
                String voltLabel = itemHolderList.get(position).getText();
                String voltHint = itemHolderList.get(position).getHint();
                ((VoltmeterLayout) holder).setVoltmeterData(voltLabel, voltHint);
                break;
            case DISPLAY_LAYOUT:
                String displayLabel = itemHolderList.get(position).getText();
                String displayHint = itemHolderList.get(position).getHint();
                ((DisplayLayout) holder).setDisplayData(displayLabel, displayHint);
                break;
            case VISUAL_LAYOUT:
                String visualLabel = itemHolderList.get(position).getText();
                ((VisualLayout) holder).setVisualData(visualLabel);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return itemHolderList.size();
    }


    class VoltmeterLayout extends RecyclerView.ViewHolder {
        private TextView rowLabel;
        private EditText inputData;

        public VoltmeterLayout(@NonNull View itemView) {
            super(itemView);
            rowLabel = itemView.findViewById(R.id.label_text);
            inputData = itemView.findViewById(R.id.item_edit_text);
        }

        private void setVoltmeterData(String text, String hint) {
            rowLabel.setText(text);
            inputData.setHint(hint);
        }
    }

    class DisplayLayout extends RecyclerView.ViewHolder {
        private TextView rowLabel;
        private EditText inputData;

        public DisplayLayout(@NonNull View itemView) {
            super(itemView);
            rowLabel = itemView.findViewById(R.id.label_text);
            inputData = itemView.findViewById(R.id.item_edit_text);
        }

        private void setDisplayData(String text, String hint) {
            rowLabel.setText(text);
            inputData.setHint(hint);
        }
    }

    class VisualLayout extends RecyclerView.ViewHolder {
        private TextView rowLabel;

        public VisualLayout(@NonNull View itemView) {
            super(itemView);
            rowLabel = itemView.findViewById(R.id.label_text);
        }

        private void setVisualData(String text) {
            rowLabel.setText(text);
        }
    }

}
