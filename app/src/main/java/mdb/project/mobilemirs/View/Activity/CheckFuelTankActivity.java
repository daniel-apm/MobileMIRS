package mdb.project.mobilemirs.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import mdb.project.mobilemirs.R;
import mdb.project.mobilemirs.Utils.HorizontalScroll;
import mdb.project.mobilemirs.Utils.VerticalScroll;

public class CheckFuelTankActivity extends AppCompatActivity implements HorizontalScroll.ScrollViewListener, VerticalScroll.ScrollViewListener, View.OnClickListener {

    String[] header = {"Isi Liter", "Main Tank", "Pemakaian", "Total Pemakaian", "Note"};
    int[] date = new int[31];
    TableRow tableRow;
    TextView textView;
    int tempMaxWidth, maxWidth, tempMaxHeight, maxHeight, datePosition;

    private EditText inputPemakaian, inputIsi, inputNote;
    private Button buttonSubmit, buttonCancel;
    private RelativeLayout layoutMain, layoutMultiLineHeader, layoutColumnHeader, layoutRowHeader, layoutContent;
    private VerticalScroll verticalScrollRowHeader, verticalScrollContent;
    private HorizontalScroll horizontalScrollColumnHeader, horizontalScrollContent;
    private TableLayout tableMultiLineHeader, tableColumnHeader, tableRowHeader, tableContent;
    private int maxContentWidth, maxContentHeight, maxRowWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_fuel_tank);
        datePosition = getIntent().getIntExtra("Date", 0);
        layoutMain = findViewById(R.id.layout_table);
        setMeasure();
        initializeLayout();
        initializeScrollers();
        initializeTableLayout();
        horizontalScrollColumnHeader.setScrollViewListener(this);
        horizontalScrollContent.setScrollViewListener(this);
        verticalScrollRowHeader.setScrollViewListener(this);
        verticalScrollContent.setScrollViewListener(this);
        addDataTableMultiLineHeader();
        addDataTableColumnHeader();
        addDataTableRowHeader();
        addDataTableContent();
        inputPemakaian = findViewById(R.id.input_pemakaian);
        inputIsi = findViewById(R.id.input_isi);
        inputNote = findViewById(R.id.input_note);
        buttonSubmit = findViewById(R.id.input_button);
        buttonCancel = findViewById(R.id.cancel_button);
        selectRow(datePosition);
        buttonSubmit.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    private void initializeLayout() {
        layoutMultiLineHeader = new RelativeLayout(this);
        layoutMultiLineHeader.setId(R.id.layout_multi_line_header);
        layoutMultiLineHeader.setPadding(0, 0, 0, 0);

        layoutColumnHeader = new RelativeLayout(this);
        layoutColumnHeader.setId(R.id.layout_column_header);
        layoutColumnHeader.setPadding(0, 0, 0, 0);

        layoutRowHeader = new RelativeLayout(this);
        layoutRowHeader.setId(R.id.layout_row_header);
        layoutRowHeader.setPadding(0, 0, 0, 0);

        layoutContent = new RelativeLayout(this);
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
        horizontalScrollColumnHeader = new HorizontalScroll(this);
        horizontalScrollColumnHeader.setPadding(0, 0, 0, 0);
        horizontalScrollColumnHeader.setHorizontalScrollBarEnabled(false);

        horizontalScrollContent = new HorizontalScroll(this);
        horizontalScrollContent.setPadding(0, 0, 0, 0);
        horizontalScrollContent.setHorizontalScrollBarEnabled(false);

        verticalScrollRowHeader = new VerticalScroll(this);
        verticalScrollRowHeader.setPadding(0, 0, 0, 0);
        verticalScrollRowHeader.setVerticalScrollBarEnabled(false);

        verticalScrollContent = new VerticalScroll(this);
        verticalScrollContent.setPadding(0, 0, 0, 0);
        verticalScrollContent.setVerticalScrollBarEnabled(false);

        layoutColumnHeader.addView(horizontalScrollColumnHeader);
        layoutRowHeader.addView(verticalScrollRowHeader);
        verticalScrollContent.addView(horizontalScrollContent);
        layoutContent.addView(verticalScrollContent);
    }

    private void initializeTableLayout() {
        tableMultiLineHeader = new TableLayout(this);
        tableMultiLineHeader.setPadding(0, 0, 0, 0);

        tableColumnHeader = new TableLayout(this);
        tableColumnHeader.setPadding(0, 0, 0, 0);

        tableRowHeader = new TableLayout(this);
        tableRowHeader.setPadding(0, 0, 0, 0);

        tableContent = new TableLayout(this);
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
        tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.CENTER);
        textView = new TextView(this);
        createHeader(tableRow, textView, "Date");
        textView.setPadding(30, 30, 30, 30);
        textView.measure(0, 0);
        int tempMaxRowWidth = textView.getMeasuredWidth();
        if (tempMaxRowWidth > maxRowWidth) {
            maxRowWidth = tempMaxRowWidth;
        }
        textView.setHeight(maxHeight);
        tableMultiLineHeader.addView(tableRow);
    }

    private void addDataTableColumnHeader() {
        tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.CENTER);
        for (int i = 0; i < header.length; i++) {
            textView = new TextView(this);
            createHeader(tableRow, textView, header[i]);
            textView.measure(0, 0);
            int tempMaxContentWidth = textView.getMeasuredWidth();
//            int tempMaxContentHeight = textView.getMeasuredHeight();
            if (tempMaxContentWidth > maxContentWidth) {
                maxContentWidth = tempMaxContentWidth;
            }
//            if (tempMaxContentHeight > maxContentHeight) {
//                maxContentHeight = tempMaxContentHeight;
//            }
        }
        tableColumnHeader.addView(tableRow);
        for (int i = 0; i < tableRow.getChildCount(); i++) {
            TextView textView = (TextView) tableRow.getChildAt(i);
            textView.setWidth(maxContentWidth);
            textView.setHeight(maxHeight);
        }
    }

    private void addDataTableRowHeader() {
        for (int i = 0; i < date.length; i++) {
            tableRow = new TableRow(this);
            tableRow.setGravity(Gravity.CENTER);
            textView = new TextView(this);
            createHeader(tableRow, textView, String.valueOf(i + 1));
            textView.setWidth(maxRowWidth);
            textView.setHeight(maxHeight);
            tableRowHeader.addView(tableRow);
        }
    }

    private void addDataTableContent() {
        for (int i = 0; i < date.length; i++) {
            tableRow = new TableRow(this);
            tableRow.setGravity(Gravity.CENTER);
            for (int j = 0; j < header.length; j++) {
                textView = new TextView(this);
                createRow(tableRow, textView, String.valueOf(j + 1));
//                int tempMaxContentWidth = textView.getMeasuredWidth();
//                int tempMaxContentHeight = textView.getMeasuredHeight();
//                if (tempMaxContentWidth > maxContentWidth) {
//                    maxContentWidth = tempMaxContentWidth;
//                }
//                if (tempMaxContentHeight > maxContentHeight) {
//                    maxContentHeight = tempMaxContentHeight;
//                }
                textView.setWidth(maxContentWidth);
                textView.setHeight(maxHeight);
            }
            tableContent.addView(tableRow);
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

    private void setMeasure() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.checked_input_state_on);
        imageView.setPadding(7, 7, 7, 7);
        imageView.measure(0, 0);
        tempMaxHeight = imageView.getMeasuredHeight();
        tempMaxWidth = imageView.getMeasuredWidth();
        if (tempMaxWidth > maxWidth) {
            maxWidth = tempMaxWidth;
        }
        if (tempMaxHeight > maxHeight) {
            maxHeight = tempMaxHeight;
        }
    }

    private void selectRow(int position) {
        int count = 0;
        String[] dataHolder = new String[3];
        TableRow row = (TableRow) tableContent.getChildAt(position);
        for (int i = 0; i < row.getChildCount(); i++) {
            TextView col = (TextView) row.getChildAt(i);
            col.setBackgroundColor(getResources().getColor(R.color.selectedColumn));
            if (i == 0) {
                col.getParent().requestChildFocus(col, col);
            }
            if (i == 0 || i == 2 || i == 4) {
                dataHolder[count] = col.getText().toString();
                count++;
            }
        }
        inputPemakaian.setText(dataHolder[1]);
        inputIsi.setText(dataHolder[0]);
        inputNote.setText(dataHolder[2]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_button:
                int count = 0;
                String[] dataHolder = new String[3];
                dataHolder[0] = inputIsi.getText().toString();
                dataHolder[1] = inputPemakaian.getText().toString();
                dataHolder[2] = inputNote.getText().toString();
                tableRow = (TableRow) tableContent.getChildAt(datePosition);
                for (int i = 0; i < tableRow.getChildCount(); i++) {
                    if (i == 0) {
                        TextView tv = (TextView) tableRow.getChildAt(i);
                        tv.getParent().requestChildFocus(tv, tv);
                    }
                    if (i == 0 || i == 2 || i == 4) {
                        TextView tv = (TextView) tableRow.getChildAt(i);
                        tv.setText(dataHolder[count]);
                        count++;
                    }
                }
                break;
            case R.id.cancel_button:
                onBackPressed();
                break;
        }
    }

}