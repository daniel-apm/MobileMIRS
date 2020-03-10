package mdb.project.mobilemirs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import mdb.project.mobilemirs.R;

public class InputFuelAdapter extends BaseAdapter {

    private Context context;
    private String[] inputLabel;
    private LayoutInflater layoutInflater;

    public InputFuelAdapter(Context context, String[] inputLabel) {
        this.context = context;
        this.inputLabel = inputLabel;
    }

    @Override
    public int getCount() {
        return inputLabel.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.input_text_item, null);
            TextView textView = convertView.findViewById(R.id.label_text);
            textView.setText(inputLabel[position]);
        }
        return convertView;
    }
}