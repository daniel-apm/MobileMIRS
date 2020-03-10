package mdb.project.mobilemirs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import mdb.project.mobilemirs.Interface.OnDateButtonListener;
import mdb.project.mobilemirs.R;

public class DateButtonAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] date;
    private OnDateButtonListener mCallback;

    public DateButtonAdapter(Context context, int[] date, OnDateButtonListener mCallback) {
        this.context = context;
        this.date = date;
        this.mCallback = mCallback;
    }

    @Override
    public int getCount() {
        return date.length;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.date_button_item, null);
            final Button btn = convertView.findViewById(R.id.item_button);
            btn.setText("" + (position + 1));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onSelectedColumn(position, R.color.selectedColumn);
                }
            });
        }
        return convertView;
    }
}
