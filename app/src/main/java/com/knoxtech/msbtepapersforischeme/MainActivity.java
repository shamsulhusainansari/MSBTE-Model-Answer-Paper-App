
package com.knoxtech.msbtepapersforischeme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}

public class MainActivity  extends AppCompatActivity{
    public RelativeLayout coordinatorLayout;
    public AutoCompleteTextView mainAutoBranch;
    public AutoCompleteTextView mainAutoSem;
    public AutoCompleteTextView mainAutoYear;

    public String branchString;
    public String semesterString;
    public String yearString;
    public ArrayAdapter<Object> adBranch;
    public ArrayAdapter<Object> adSem;
    public ArrayAdapter<Object> adYear;
    public RadioGroup mainRadioGrp;



    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_main);

        this.coordinatorLayout = (RelativeLayout)this.findViewById(R.id.relative);
        mainAutoBranch=findViewById(R.id.autoBranch);
        mainAutoSem= findViewById(R.id.autoSemester);
        mainAutoYear= findViewById(R.id.autoYear);
        mainRadioGrp=findViewById(R.id.radioGroup);
        FloatingActionButton floatingActionButton = (FloatingActionButton)this.findViewById(R.id.submitBtn);
        Object[] arrobject = new String[]{"CIVIL", "COMPUTER", "ELECTRICAL", "ELECTRONICS", "MECHANICAL", "AUTOMOBILE"};
        Object[] arrobject2 = new String[]{"SEMESTER 1", "SEMESTER 2", "SEMESTER 3", "SEMESTER 4", "SEMESTER 5"};
        Object[] arrobject3 = new String[]{"WINTER 2019", "SUMMER 2019", "WINTER 2018", "SUMMER 2018"};
        adBranch = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, arrobject);
        adSem = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, arrobject2);
        adYear = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, arrobject3);
        mainAutoBranch.setAdapter(adBranch);
        mainAutoSem.setAdapter(adSem);
        mainAutoYear.setAdapter(adYear);

        mainAutoBranch.setOnItemClickListener((parent, view, position, id) -> {
            branchString= parent.getItemAtPosition(position).toString();
            Log.e("ITEM: ",branchString);
        });
        mainAutoSem.setOnItemClickListener((parent, view, position, id) -> {
            semesterString= parent.getItemAtPosition(position).toString();
            Log.e("ITEM: ",semesterString);
        });
        mainAutoYear.setOnItemClickListener((parent, view, position, id) -> {
            yearString= parent.getItemAtPosition(position).toString();
            Log.e("ITEM: ",yearString);
        });

        floatingActionButton.setOnClickListener(v -> {
            RadioButton radioButton;
            radioButton = findViewById(mainRadioGrp.getCheckedRadioButtonId());
            String pprType = radioButton.getText().toString();
            if (branchString != null && semesterString != null && yearString != null) {
                Intent intent = new Intent(getApplicationContext(), PaperActivity.class);
                intent.putExtra("paperType", pprType);
                intent.putExtra("Branch", branchString);
                intent.putExtra("Semester", semesterString);
                intent.putExtra("Year", yearString);
                Bundle bundle1 = new Bundle();
                bundle1.putString("item_id", pprType);
                bundle1.putString("item_name", branchString);
                //this.mFirebaseAnalytics.logEvent("select_content", bundle);
                Log.e("Details", "" + pprType + branchString + semesterString + yearString);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Fill the details first!!!", Toast.LENGTH_SHORT).show();
            }

        });
}
}



