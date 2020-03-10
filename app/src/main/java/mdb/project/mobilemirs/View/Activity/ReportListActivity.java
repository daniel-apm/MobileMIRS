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

public class ReportListActivity extends AppCompatActivity implements HorizontalScroll.ScrollViewListener, View.OnClickListener {

    private RelativeLayout layoutMain, layoutColumnHeader, layoutContent;
    private HorizontalScroll horizontalScrollColumnHeader, horizontalScrollContent;
    private VerticalScroll verticalScrollContent;
    private TableLayout tableColumnHeader, tableContent;
    private TableRow tableRow;
    private Button btnSubmit, btnCancel;
    private TextView textView, dokumenTv, monthTv, kapalTv, dateTv;
    private EditText inputMesin, inputMasalah, inputTeknisi, inputDurasiPekerjaan;

    private String[] dataHolder;
    private String[] columnHeader = {"Date", "Nama Mesin", "Masalah", "Teknisi", "Durasi Pekerjaan"};
    private int datePosition, maxHeight, maxWidth;
    private boolean isAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        layoutMain = findViewById(R.id.layout_table);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            dataHolder = bundle.getStringArray("Data Holder");
            datePosition = bundle.getInt("Date");
        }

        dokumenTv = findViewById(R.id.dokumen_text);
        dokumenTv.setText("Nama Dokumen : " + dataHolder[0]);

        monthTv = findViewById(R.id.month_text);
        monthTv.setText("Month : " + dataHolder[1]);

        kapalTv = findViewById(R.id.kapal_text);
        kapalTv.setText("Kapal : " + dataHolder[2]);

        dateTv = findViewById(R.id.date_output_text);
        dateTv.setText(String.valueOf(datePosition + 1));

        inputMesin = findViewById(R.id.input_mesin);
        inputMasalah = findViewById(R.id.input_masalah);
        inputTeknisi = findViewById(R.id.input_teknisi);
        inputDurasiPekerjaan = findViewById(R.id.input_durasi_pekerjaan);
        btnSubmit = findViewById(R.id.input_button);
        btnCancel = findViewById(R.id.cancel_button);

        setMeasure();
        initializeLayout();
        initializeScrollers();
        initializeTableLayout();
        horizontalScrollColumnHeader.setScrollViewListener(this);
        horizontalScrollContent.setScrollViewListener(this);

        addDataTableColumnHeader();
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
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
        verticalScrollContent.setPadding(0, 0, 0, 0);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_button:
                String[] dataInput = new String[5];
                dataInput[0] = dateTv.getText().toString();
                dataInput[1] = inputMesin.getText().toString();
                dataInput[2] = inputMasalah.getText().toString();
                dataInput[3] = inputTeknisi.getText().toString();
                dataInput[4] = inputDurasiPekerjaan.getText().toString();

                for (int i = 0; i < tableContent.getChildCount(); i++) {
                    tableRow = (TableRow) tableContent.getChildAt(i);
                    for (int j = 0; j < tableRow.getChildCount(); j++) {
                        if (j == 0) {
                            textView = (TextView) tableRow.getChildAt(j);
                            if (textView.getText().toString().equals(dataInput[0])) {
                                isAdded = true;
                            }
                        }
                    }
                }

                if (!isAdded) {
                    addDataTableContent(dataInput);
                } else {
                    updateDataTableContent(dataInput);
                }

                break;
            case R.id.cancel_button:
                onBackPressed();
                break;
        }
    }

    private void addDataTableContent(String[] dataInput) {
        tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.CENTER);
        for (int i = 0; i < columnHeader.length; i++) {
            textView = new TextView(this);
            createRow(tableRow, textView, dataInput[i]);
            textView.setWidth(maxWidth);
            textView.setHeight(maxHeight);
        }
        tableContent.addView(tableRow);
    }

    private void updateDataTableContent(String[] dataInput) {
        for (int i = 0; i < tableRow.getChildCount(); i++) {
            textView = (TextView) tableRow.getChildAt(i);
            textView.setText(dataInput[i]);
        }
    }

    private void createRow(TableRow tableRow, TextView textView, String text) {
        textView.setText(text);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        tableRow.addView(textView);
    }
}
