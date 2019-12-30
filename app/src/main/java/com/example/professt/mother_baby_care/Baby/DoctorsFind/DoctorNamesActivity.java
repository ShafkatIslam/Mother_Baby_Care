package com.example.professt.mother_baby_care.Baby.DoctorsFind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorDetailsActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorFindActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorName;
import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorNameActivity;
import com.example.professt.mother_baby_care.R;

public class DoctorNamesActivity extends AppCompatActivity {

    private ListView listView1;
    ArrayAdapter<String> adapter1;
    String[] doctorNames;
    private SearchView searchView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_names);

        listView1 = (ListView) findViewById(R.id.listViewId1s);
        searchView1 = (SearchView) findViewById(R.id.searchViewId1s);

        doctorNames = getResources().getStringArray(R.array.doctor_list_header);   //fetch the string array from resourse file's string folder and keep them in the String array

        adapter1 = new ArrayAdapter<String>(DoctorNamesActivity.this,R.layout.sample_view,R.id.textViewId,doctorNames); //creating object of ArrayAdapter....thre are 4 parameters in ArrayAdapter
        listView1.setAdapter(adapter1);   //setting the adapter in listView

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(DoctorNamesActivity.this,DoctorDetailActivity.class);
                String value = adapter1.getItem(position);
                DoctorName doctorName = DoctorName.getInstance();
                doctorName.setId(value);

                startActivity(intent);
            }
        });


        searchView1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {  //Listener add with searchView
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {        //When we search a text or String in the serachView the onQueryTextChange method will help to find

                adapter1.getFilter().filter(newText);              //we will find the String by the getFilter method and it's filter method from the adapter which makes the sampleView in ListView.
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(DoctorNamesActivity.this,DoctorsFindActivity.class);
        startActivity(intent);

    }
}
