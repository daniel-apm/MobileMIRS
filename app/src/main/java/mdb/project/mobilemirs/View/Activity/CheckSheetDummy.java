package mdb.project.mobilemirs.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import mdb.project.mobilemirs.View.Fragment.InputSheetFragment;
import mdb.project.mobilemirs.View.Fragment.TableSheetFragment;
import mdb.project.mobilemirs.Interface.OnInputSheetListener;
import mdb.project.mobilemirs.Model.ItemHolder;
import mdb.project.mobilemirs.R;

import static mdb.project.mobilemirs.Model.ItemHolder.DISPLAY_LAYOUT;
import static mdb.project.mobilemirs.Model.ItemHolder.VISUAL_LAYOUT;
import static mdb.project.mobilemirs.Model.ItemHolder.VOLTMETER_LAYOUT;

public class CheckSheetDummy extends AppCompatActivity implements OnInputSheetListener {

    FragmentTransaction fragmentTransaction;
    TableSheetFragment tableSheetFragment;
    InputSheetFragment inputSheetFragment;
    ArrayList<ItemHolder> itemHolderList;
    String[] key = {"Voltmeter", "Display", "Visual"};
    String[][] values = {{"Genset Output (V)", "Battery Output (V)"}, {"Genset Frekuensi (Hz)"}, {"Battery Air Level", "Fuel Leak", "Fuel Water Separator", "Cooler Leak", "Cooler Pipe", "Oil Engine Leak", "Oil Engine Level"}};
    int[] imageResource = {R.drawable.damaged_input_state_on, R.drawable.checked_input_state_on, R.drawable.unchecked_input_state_on};
    int[] date = new int[31];
    int onBackPosition;
    int datePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sheet_dummy);
        datePosition = getIntent().getIntExtra("Date", 0);
        onBackPosition = datePosition;
        LinkedHashMap<String, String[]> map = generateMap();
        itemHolderList = generateListItem(map);
        tableSheetFragment = new TableSheetFragment(map, date, imageResource, datePosition);
        inputSheetFragment = new InputSheetFragment(itemHolderList, datePosition);
        fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.table_container, tableSheetFragment, null).add(R.id.input_container, inputSheetFragment, null);
        fragmentTransaction.commit();
    }

    private LinkedHashMap<String, String[]> generateMap() {
        LinkedHashMap<String, String[]> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < key.length; i++) {
            linkedHashMap.put(key[i], values[i]);
        }
        return linkedHashMap;
    }

    private ArrayList<ItemHolder> generateListItem(LinkedHashMap<String, String[]> linkedHashMap) {
        ArrayList<ItemHolder> arrayList = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : linkedHashMap.entrySet()) {
            if (entry.getKey().equals("Voltmeter")) {
                for (String value : entry.getValue()) {
                    arrayList.add(new ItemHolder(VOLTMETER_LAYOUT, value, "Insert Here ... (V)"));
                }
            } else if (entry.getKey().equals("Display")) {
                for (String value : entry.getValue()) {
                    arrayList.add(new ItemHolder(DISPLAY_LAYOUT, value, "Insert Here ... (Hz)"));
                }
            } else if (entry.getKey().equals("Visual")) {
                for (String value : entry.getValue()) {
                    arrayList.add(new ItemHolder(VISUAL_LAYOUT, value));
                }
            }
        }
        return arrayList;
    }

    @Override
    public void onSubmitData(ArrayList<Integer> dataHolder, int position, int colorId) {
        tableSheetFragment.updateColumn(dataHolder, position);
        tableSheetFragment.selectColumn(position, colorId);
    }

    @Override
    public void onCancelInput() {
        onBackPressed();
    }


}
