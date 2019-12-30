package com.example.professt.mother_baby_care.Baby.DoctorsFind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorFindActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.DoctorListActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.HospitalActivity;
import com.example.professt.mother_baby_care.Mother.DoctorFind.HospitalPosition;
import com.example.professt.mother_baby_care.R;

public class HospitalsActivity extends AppCompatActivity {

    private ListView listView;
    ArrayAdapter<String> adapter;
    String[] hospitalNames;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);

        listView = (ListView) findViewById(R.id.listViewIds);
        searchView = (SearchView) findViewById(R.id.searchViewIds);

        hospitalNames = getResources().getStringArray(R.array.hospital_names);   //fetch the string array from resourse file's string folder and keep them in the String array

        adapter = new ArrayAdapter<String>(HospitalsActivity.this,R.layout.sample_view,R.id.textViewId,hospitalNames); //creating object of ArrayAdapter....thre are 4 parameters in ArrayAdapter
        listView.setAdapter(adapter);   //setting the adapter in listView

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(HospitalsActivity.this,DoctorListsActivity.class);
                String value = adapter.getItem(position);
                HospitalPosition hospitalPosition = HospitalPosition.getInstance();
                hospitalPosition.setId(value);

                startActivity(intent);
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {  //Listener add with searchView
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {        //When we search a text or String in the serachView the onQueryTextChange method will help to find

                adapter.getFilter().filter(newText);              //we will find the String by the getFilter method and it's filter method from the adapter which makes the sampleView in ListView.
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(HospitalsActivity.this,DoctorsFindActivity.class);
        startActivity(intent);

    }
}
