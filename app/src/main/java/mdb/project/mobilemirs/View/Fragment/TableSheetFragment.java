package mdb.project.mobilemirs.View.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import mdb.project.mobilemirs.R;
import mdb.project.mobilemirs.Utils.HorizontalScroll;
import mdb.project.mobilemirs.Utils.VerticalScroll;

public class TableSheetFragment extends Fragment implements HorizontalScroll.ScrollViewListener, VerticalScroll.ScrollViewListener {

    private RelativeLayout layoutMain, layoutMultiLineHeader, layoutColumnHeader, layoutRowHeader, layoutContent;
    private HorizontalScroll horizontalScrollColumnHeader, horizontalScrollContent;
    private VerticalScroll verticalScrollRowHeader, verticalScrollContent;
    private TableLayout tableMultiLineHeader, tableColumnHeader, tableRowHeader, tableContent;
    private TableRow tableRow;
    private TextView textView;
    private int maxRowHeaderWidth, maxContentWidth, maxContentHeight;

    private LinkedHashMap<String, String[]> map;
    private int[] date;
    private int[] imageResource;
    private int datePosition;

    public TableSheetFragment(LinkedHashMap<String, String[]> map, int[] date, int[] imageResource, int datePosition) {
        this.map = map;
        this.date = date;
        this.imageResource = imageResource;
        this.datePosition = datePosition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_sheet, container, false);
        layoutMain = view.findViewById(R.id.layout_main);
        initializeLayout();
        initializeScrollers();
        initializeTableLayout();
        horizontalScrollColumnHeader.setScrollViewListener(this);
        horizontalScrollContent.setScrollViewListener(this);
        verticalScrollRowHeader.setScrollViewListener(this);
        verticalScrollContent.setScrollViewListener(this);
        addDataTableContent();
        addDataTableRowHeader();
        addDataTableMultiLineHeader();
        addDataTableColumnHeader();
        selectColumn(datePosition, R.color.selectedColumn);
        return view;
    }

    private void initializeLayout() {
        layoutMultiLineHeader = new RelativeLayout(getContext());
        layoutMultiLineHeader.setId(R.id.layout_multi_line_header);
        layoutMultiLineHeader.setPadding(0, 0, 0, 0);

        layoutColumnHeader = new RelativeLayout(getContext());
        layoutColumnHeader.setId(R.id.layout_column_header);
        layoutColumnHeader.setPadding(0, 0, 0, 0);

        layoutRowHeader = new RelativeLayout(getContext());
        layoutRowHeader.setId(R.id.layout_row_header);
        layoutRowHeader.setPadding(0, 0, 0, 0);

        layoutContent = new RelativeLayout(getContext());
        layoutContent.setId(R.id.layout_content);
        layoutContent.setPadding(0, 0, 0, 0);

        layoutMultiLineHeader.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layoutMain.addView(layoutMultiLineHeader);

        RelativeLayout.LayoutParams layoutParamsLayoutColumnHeader = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLayoutColumnHeader.addRule(RelativeLayout.RIGHT_OF, R.id.layout_multi_line_header);
        layoutColumnHeader.setLayoutParams(layoutParamsLayoutColumnHeader);
        layoutMain.addView(layoutColumnHeader);

        RelativeLayout.LayoutParams layoutParamsLayoutRowHeader = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLayoutRowHeader.addRule(RelativeLayout.BELOW, R.id.layout_multi_line_header);
        layoutRowHeader.setLayoutParams(layoutParamsLayoutRowHeader);
        layoutMain.addView(layoutRowHeader);

        RelativeLayout.LayoutParams layoutParamsLayoutContent = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLayoutContent.addRule(RelativeLayout.BELOW, R.id.layout_column_header);
        layoutParamsLayoutContent.addRule(RelativeLayout.RIGHT_OF, R.id.layout_row_header);
        layoutContent.setLayoutParams(layoutParamsLayoutContent);
        layoutMain.addView(layoutContent);
    }

    private void initializeScrollers() {
        horizontalScrollColumnHeader = new HorizontalScroll(getContext());
        horizontalScrollColumnHeader.setPadding(0, 0, 0, 0);
        horizontalScrollColumnHeader.setHorizontalScrollBarEnabled(false);

        horizontalScrollContent = new HorizontalScroll(getContext());
        horizontalScrollContent.setPadding(0, 0, 0, 0);
        horizontalScrollContent.setHorizontalScrollBarEnabled(false);

        verticalScrollRowHeader = new VerticalScroll(getContext());
        verticalScrollRowHeader.setPadding(0, 0, 0, 0);
        verticalScrollRowHeader.setVerticalScrollBarEnabled(false);

        verticalScrollContent = new VerticalScroll(getContext());
        verticalScrollContent.setPadding(0, 0, 0, 0);
        verticalScrollContent.setVerticalScrollBarEnabled(false);

        layoutColumnHeader.addView(horizontalScrollColumnHeader);
        layoutRowHeader.addView(verticalScrollRowHeader);
        verticalScrollContent.addView(horizontalScrollContent);
        layoutContent.addView(verticalScrollContent);
    }

    private void initializeTableLayout() {
        tableMultiLineHeader = new TableLayout(getContext());
        tableMultiLineHeader.setPadding(0, 0, 0, 0);

        tableColumnHeader = new TableLayout(getContext());
        tableColumnHeader.setPadding(0, 0, 0, 0);

        tableRowHeader = new TableLayout(getContext());
        tableRowHeader.setPadding(0, 0, 0, 0);

        tableContent = new TableLayout(getContext());
        tableContent.setPadding(0, 0, 0, 0);

        tableMultiLineHeader.setBackgroundColor(getResources().getColor(R.color.colorHeader));
        layoutMultiLineHeader.addView(tableMultiLineHeader);

        tableColumnHeader.setBackgroundColor(getResources().getColor(R.color.colorHeader));
        horizontalScrollColumnHeader.addView(tableColumnHeader);

        tableRowHeader.setBackgroundColor(getResources().getColor(R.color.colorHeader));
        verticalScrollRowHeader.addView(tableRowHeader);

        horizontalScrollContent.addView(tableContent);
    }

    @Override
    public void onScrollChanged(HorizontalScroll scrollView, int x, int y, int oldx, int oldy) {
        if (scrollView == horizontalScrollColumnHeader) {
            horizontalScrollContent.scrollTo(x, y);
        } else if (scrollView == horizontalScrollContent) {
            horizontalScrollColumnHeader.scrollTo(x, y);
        }
    }

    @Override
    public void onScrollChanged(VerticalScroll scrollView, int x, int y, int oldx, int oldy) {
        if (scrollView == verticalScrollRowHeader) {
            verticalScrollContent.scrollTo(x, y);
        } else if (scrollView == verticalScrollContent) {
            verticalScrollRowHeader.scrollTo(x, y);
        }
    }

    private void addDataTableMultiLineHeader() {
        tableRow = new TableRow(getContext());
        tableRow.setGravity(Gravity.CENTER);
        textView = new TextView(getContext());
        createHeader(tableRow, textView, "Genset");
        textView.setWidth(maxRowHeaderWidth);
        textView.setHeight(maxContentHeight);
        tableMultiLineHeader.addView(tableRow);
    }

    private void addDataTableColumnHeader() {
        tableRow = new TableRow(getContext());
        tableRow.setGravity(Gravity.CENTER);
        for (int i = 0; i < date.length; i++) {
            textView = new TextView(getContext());
            createHeader(tableRow, textView, "" + (i + 1));
            textView.setWidth(maxContentWidth);
            textView.setHeight(maxContentHeight);
        }
        tableColumnHeader.addView(tableRow);
    }

    private void addDataTableRowHeader() {
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            for (String value : entry.getValue()) {
                tableRow = new TableRow(getContext());
                textView = new TextView(getContext());
                createHeader(tableRow, textView, value);
                textView.setPadding(7, 0, 7, 0);
                textView.measure(0, 0);
                textView.setHeight(maxContentHeight);
                int tempMaxRowHeaderWidth = textView.getMeasuredWidth();
                if (tempMaxRowHeaderWidth > maxRowHeaderWidth) {
                    maxRowHeaderWidth = tempMaxRowHeaderWidth;
                }
                tableRowHeader.addView(tableRow);
            }
        }
    }

    private void addDataTableContent() {
        int count = 0;
        int imgId = 0;
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            for (String value : entry.getValue()) {
                if (imgId >= imageResource.length) {
                    imgId = 0;
                }
                count++;
                tableRow = new TableRow(getContext());
                for (int i = 0; i < date.length; i++) {
                    if (entry.getKey().equals("Voltmeter") || entry.getKey().equals("Display")) {
                        textView = new TextView(getContext());
                        createRow(tableRow, textView, "");
                        ImageView imageView = new ImageView(getContext());
                        imageView.setImageResource(imageResource[0]);
                        imageView.setPadding(7,7,7,7);
                        imageView.measure(0, 0);
                        int tempMaxContentWidth = imageView.getMeasuredWidth();
                        int tempMaxContentHeight = imageView.getMeasuredHeight();
                        if (tempMaxContentWidth > maxContentWidth) {
                            maxContentWidth = tempMaxContentWidth;
                        }
                        if (tempMaxContentHeight > maxContentHeight) {
                            maxContentHeight = tempMaxContentHeight;
                        }
                        textView.setWidth(maxContentWidth);
                        textView.setHeight(maxContentHeight);
                    } else if ((entry.getKey().equals("Visual"))) {
                        ImageView imageView = new ImageView(getContext());
                        createRowImg(tableRow, imageView, imgId);
                        imageView.setPadding(7,7,7,7);
                        imageView.setMinimumHeight(maxContentHeight);
                    }
                }
                imgId++;
                tableContent.addView(tableRow);
            }
        }
    }

    private void createHeader(TableRow tableRow, TextView textView, String text) {
        textView.setText(text);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        tableRow.addView(textView);
    }

    private void createRow(TableRow tableRow, TextView textView, String text) {
        textView.setText(text);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tableRow.setGravity(Gravity.CENTER);
        tableRow.addView(textView);
    }

    private void createRowImg(TableRow tableRow, ImageView imageView, int i) {
//        imageView.setImageResource(imageResource[i]);
        tableRow.addView(imageView);
    }

    public void selectColumn(int position, int colorId) {
        for (int i = 0; i < tableContent.getChildCount(); i++) {
            View view = tableContent.getChildAt(i);
            TableRow row = (TableRow) view;
            View col = row.getChildAt(position);
            col.setBackgroundColor(getResources().getColor(colorId));
            if (i == 0) {
                col.getParent().requestChildFocus(col, col);
            }
        }
    }

    public void updateColumn(ArrayList<Integer> dataHolder, int position) {
        for (int i = 0; i < tableContent.getChildCount(); i++) {
            View view = tableContent.getChildAt(i);
            TableRow row = (TableRow) view;
            View col = row.getChildAt(position);
            if (col instanceof TextView) {
                TextView data = (TextView) col;
                data.setText("" + dataHolder.get(i));
            } else if (col instanceof ImageView) {
                ImageView data = (ImageView) col;
                data.setImageResource(imageResource[dataHolder.get(i)]);
            }
        }
    }
    
}
