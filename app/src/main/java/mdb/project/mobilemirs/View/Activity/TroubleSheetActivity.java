package mdb.project.mobilemirs.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import mdb.project.mobilemirs.R;
import mdb.project.mobilemirs.Utils.HorizontalScroll;
import mdb.project.mobilemirs.Utils.VerticalScroll;

public class TroubleSheetActivity extends AppCompatActivity implements HorizontalScroll.ScrollViewListener, View.OnClickListener {

    private Spinner datePicker, kapalPicker;
    private RelativeLayout layoutMain, layoutColumnHeader, layoutContent;
    private HorizontalScroll horizontalScrollColumnHeader, horizontalScrollContent;
    private VerticalScroll verticalScrollContent;
    private TableLayout tableColumnHeader, tableContent;
    private TableRow tableRow;
    private TextView textView;
    private Button btnSubmit, btnCancel;
    private EditText inputDokumen;

    private String[] dateList = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
    private String[] kapalList = {"AISYAH-1", "GETS-1"};
    private String[] columnHeader = {"No", "Nama Dokumen", "Date", "Detail"};
    private int maxWidth, maxHeight, datePosition;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trouble_sheet);
        layoutMain = findViewById(R.id.layout_table);
        btnSubmit = findViewById(R.id.input_button);
        btnCancel = findViewById(R.id.cancel_button);
        inputDokumen = findViewById(R.id.input_dokumen);
        datePosition = getIntent().getIntExtra("Date", 0);
        setMeasure();
        initializeLayout();
        initializeScrollers();
        initializeTableLayout();
        horizontalScrollColumnHeader.setScrollViewListener(this);
        horizontalScrollContent.setScrollViewListener(this);
        addDataTableColumnHeader();
        datePicker = findViewById(R.id.date_picker);
        kapalPicker = findViewById(R.id.kapal_picker);
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dateList);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        datePicker.setAdapter(dateAdapter);
        ArrayAdapter<String> kapalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kapalList);
        kapalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kapalPicker.setAdapter(kapalAdapter);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void initializeLayout() {
        layoutColumnHeader = new RelativeLayout(this);
        layoutColumnHeader.setId(R.id.layout_column_header);
        layoutColumnHeader.setPadding(0, 0, 0, 0);


        layoutContent = new RelativeLayout(this);
        layoutContent.setId(R.id.layout_content);
        layoutContent.setPadding(0, 0, 0, 0);

        layoutColumnHeader.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layoutMain.addView(layoutColumnHeader);

        RelativeLayout.LayoutParams layoutParamsLayoutContent = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLayoutContent.addRule(RelativeLayout.BELOW, R.id.layout_column_header);
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

        verticalScrollContent = new VerticalScroll(this);
        verticalScrollContent.setPadding(0,0,0,0);
        verticalScrollContent.setVerticalScrollBarEnabled(false);

        layoutColumnHeader.addView(horizontalScrollColumnHeader);
        verticalScrollContent.addView(horizontalScrollContent);
        layoutContent.addView(verticalScrollContent);
    }

    private void initializeTableLayout() {
        tableColumnHeader = new TableLayout(this);
        tableColumnHeader.setPadding(0, 0, 0, 0);
        tableColumnHeader.setStretchAllColumns(true);

        tableContent = new TableLayout(this);
        tableContent.setPadding(0, 0, 0, 0);
        tableContent.setStretchAllColumns(true);

        tableColumnHeader.setBackgroundColor(getResources().getColor(R.color.colorHeader));
        horizontalScrollColumnHeader.addView(tableColumnHeader);

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

    private void addDataTableColumnHeader() {
        tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.CENTER);
        for (String header : columnHeader) {
            textView = new TextView(this);
            createHeader(tableRow, textView, header);
            textView.measure(0, 0);
            int tempMaxWidth = textView.getMeasuredWidth();
            if (tempMaxWidth > maxWidth) {
                maxWidth = tempMaxWidth;
            }
        }
        tableColumnHeader.addView(tableRow);

        for (int i = 0; i < tableRow.getChildCount(); i++) {
            textView = (TextView) tableRow.getChildAt(i);
            textView.setWidth(maxWidth);
            textView.setHeight(maxHeight);
        }
    }

    private void createHeader(TableRow tableRow, TextView textView, String text) {
        textView.setText(text);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        tableRow.addView(textView);
    }

    private void setMeasure() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.checked_input_state_on);
        imageView.setPadding(7, 7, 7, 7);
        imageView.measure(0, 0);
        int tempMaxHeight = imageView.getMeasuredHeight();
        if (tempMaxHeight > maxHeight) {
            maxHeight = tempMaxHeight;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_button:
                index++;
                String[] dataHolder = new String[3];
                dataHolder[0] = inputDokumen.getText().toString();
                dataHolder[1] = datePicker.getSelectedItem().toString();
                dataHolder[2] = kapalPicker.getSelectedItem().toString();
                addDataTableContent(dataHolder);
                inputDokumen.setText("");
                break;
            case R.id.cancel_button:
                onBackPressed();
                break;
        }
    }

    private void addDataTableContent(final String[] dataHolder) {
        int count = 0;
        tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.CENTER);
        for (int i = 0; i < columnHeader.length; i++) {
            textView = new TextView(this);
            if (i == 0) {
                createRow(tableRow, textView, String.valueOf(index));
            } else if (i == (columnHeader.length - 1)){
                Button button = new Button(this);
                button.setText("Detail");
                button.setWidth(maxWidth);
                button.setHeight(maxHeight);
                button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TroubleSheetActivity.this, ReportListActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Date", datePosition);
                        bundle.putStringArray("Data Holder", dataHolder);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                tableRow.addView(button);
            } else {
                createRow(tableRow, textView, dataHolder[count]);
                count++;
            }
            textView.setWidth(maxWidth);
            textView.setHeight(maxHeight);
        }
        tableContent.addView(tableRow);
    }

    private void createRow(TableRow tableRow, TextView textView, String text) {
        textView.setText(text);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        tableRow.addView(textView);
    }
}
