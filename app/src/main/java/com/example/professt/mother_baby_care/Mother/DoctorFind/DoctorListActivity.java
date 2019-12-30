package com.example.professt.mother_baby_care.Mother.DoctorFind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.professt.mother_baby_care.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoctorListActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private CustomAdapter customAdapter;   //declare CustomAdapter variable
    List<String> listDataHeader;   // it is declared to get together all the data of header,that's why we declare a list and the list must be String type
    HashMap<String,List<String>> listDataChild;  //to put the child under every header
    private int lastExpandedPosition= -1; // to find the lastExpanded Position we need to declare a variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        prepareListdata();          //calling a method

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListViewId);
        customAdapter = new CustomAdapter(this,listDataHeader,listDataChild);  //create customListView which helps to get the resource in his parameter and pick up the value one by one and set it in the expandableListView and coverts it in to view and return it
        expandableListView.setAdapter(customAdapter);        //the returning view is set in the adapter and set it in the expandableListView

        //Listener add with expandableListView
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {   //when we clicked a group or header,the string in that header is shown by a Toast using a listener
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                String groupName = listDataHeader.get(groupPosition);   //we receive the string from the listDataHeader and store it to groupName using get function
                //Toast.makeText(DoctorListActivity.this, groupName+" is clicked", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        //When we collapse a group it is worked by this ClickListener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

                String groupName = listDataHeader.get(groupPosition);   //we receive the string from the listDataHeader and store it to groupName using get function
                //Toast.makeText(DoctorListActivity.this, groupName+" is collapsed", Toast.LENGTH_SHORT).show();


            }
        });

        //When we click a child there must have a listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {   //when we clicked a child,the string in that header is shown by a Toast using a listener
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String childString = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);  //we receive the string from the listDataChild of listDataHeader and store it to childName using get function.There have a lot of listDataChild under every listDataHeader
                //Toast.makeText(DoctorListActivity.this, childString+" is clicked", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        //listener add for collapse the previous item when we click a new item of expandableListVIew
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandedPosition!=-1 && lastExpandedPosition!=groupPosition)   //here we have to check the initial value of lastExpandedPosition and compare that if lastExpandedPosition is not equal to new position(groupPosition)
                {
                    expandableListView.collapseGroup(lastExpandedPosition);  //if the condition is true,then lastExpandedPosition will collaspe
                }
                lastExpandedPosition = groupPosition;   //every time it will change the position with new position or groupPosition
            }
        });

    }

    private void prepareListdata() {

        String[] headerString = getResources().getStringArray(R.array.ctgmdcl_list_header);   //to get access the string file
        String[] childString = getResources().getStringArray(R.array.ctgmdcl_names_list_child);     //to get access the string file
        String[] headerString1 = getResources().getStringArray(R.array.maa_list_header);     //to get access the string file
        String[] childString1 = getResources().getStringArray(R.array.maa_names_list_child);     //to get access the string file
        String[] headerString2 = getResources().getStringArray(R.array.metro_list_header);     //to get access the string file
        String[] childString2 = getResources().getStringArray(R.array.metro_names_list_child);     //to get access the string file
        String[] headerString3 = getResources().getStringArray(R.array.general_list_header);     //to get access the string file
        String[] childString3 = getResources().getStringArray(R.array.general_names_list_child);     //to get access the string file
        String[] headerString4 = getResources().getStringArray(R.array.ustc_list_header);     //to get access the string file
        String[] childString4 = getResources().getStringArray(R.array.ustc_names_list_child);     //to get access the string file
        String[] headerString5 = getResources().getStringArray(R.array.max_list_header);     //to get access the string file
        String[] childString5 = getResources().getStringArray(R.array.max_names_list_child);     //to get access the string file
        String[] headerString6 = getResources().getStringArray(R.array.mdclcntr_list_header);     //to get access the string file
        String[] childString6 = getResources().getStringArray(R.array.mdclcntr_list_child);     //to get access the string file
        String[] headerString7 = getResources().getStringArray(R.array.national_list_header);     //to get access the string file
        String[] childString7 = getResources().getStringArray(R.array.national_list_child);     //to get access the string file

        listDataHeader = new ArrayList<>();    //to create the listdataHeader
        listDataChild = new HashMap<>();       //to create the listDataChild


        //to add every header string with listDataHeader we use a loop

        HospitalPosition hospitalPosition = HospitalPosition.getInstance();
        String id = hospitalPosition.getId();

        if(id.equalsIgnoreCase("Chittagong Medical"))
        {
            for (int i=0;i<headerString.length;i++)   //the loop is continued till last headerString,if there is 10 headerString it will continue 0 to 9 and 10 header wil create
            {
                //adding header data with listDataHeader
                listDataHeader.add(headerString[i]);

                //to find the child Data
                List<String> child = new ArrayList<>();
                child.add(childString[i]);  //to get the childString and add them with "child" ArrayList


                //to add the child data with listDataChild
                listDataChild.put(listDataHeader.get(i),child);  //here ""listDataHeader is a key and value is "child" and we find one by one index in the Array

            }
        }

        else if(id.equalsIgnoreCase("Chittagong Maa O Shishu Hospital"))
        {
            for (int i=0;i<headerString1.length;i++)   //the loop is continued till last headerString,if there is 10 headerString it will continue 0 to 9 and 10 header wil create
            {
                //adding header data with listDataHeader
                listDataHeader.add(headerString1[i]);

                //to find the child Data
                List<String> child = new ArrayList<>();
                child.add(childString1[i]);  //to get the childString and add them with "child" ArrayList


                //to add the child data with listDataChild
                listDataChild.put(listDataHeader.get(i),child);  //here ""listDataHeader is a key and value is "child" and we find one by one index in the Array

            }
        }

        else if(id.equalsIgnoreCase("Metropoliton Hospital"))
        {
            for (int i=0;i<headerString2.length;i++)   //the loop is continued till last headerString,if there is 10 headerString it will continue 0 to 9 and 10 header wil create
            {
                //adding header data with listDataHeader
                listDataHeader.add(headerString2[i]);

                //to find the child Data
                List<String> child = new ArrayList<>();
                child.add(childString2[i]);  //to get the childString and add them with "child" ArrayList


                //to add the child data with listDataChild
                listDataChild.put(listDataHeader.get(i),child);  //here ""listDataHeader is a key and value is "child" and we find one by one index in the Array

            }
        }

        else if(id.equalsIgnoreCase("General Hospital"))
        {
            for (int i=0;i<headerString3.length;i++)   //the loop is continued till last headerString,if there is 10 headerString it will continue 0 to 9 and 10 header wil create
            {
                //adding header data with listDataHeader
                listDataHeader.add(headerString3[i]);

                //to find the child Data
                List<String> child = new ArrayList<>();
                child.add(childString3[i]);  //to get the childString and add them with "child" ArrayList


                //to add the child data with listDataChild
                listDataChild.put(listDataHeader.get(i),child);  //here ""listDataHeader is a key and value is "child" and we find one by one index in the Array

            }
        }

        else if(id.equalsIgnoreCase("USTC Hospital"))
        {
            for (int i=0;i<headerString4.length;i++)   //the loop is continued till last headerString,if there is 10 headerString it will continue 0 to 9 and 10 header wil create
            {
                //adding header data with listDataHeader
                listDataHeader.add(headerString4[i]);

                //to find the child Data
                List<String> child = new ArrayList<>();
                child.add(childString4[i]);  //to get the childString and add them with "child" ArrayList


                //to add the child data with listDataChild
                listDataChild.put(listDataHeader.get(i),child);  //here ""listDataHeader is a key and value is "child" and we find one by one index in the Array

            }
        }

        else if(id.equalsIgnoreCase("Max Hospital"))
        {
            for (int i=0;i<headerString5.length;i++)   //the loop is continued till last headerString,if there is 10 headerString it will continue 0 to 9 and 10 header wil create
            {
                //adding header data with listDataHeader
                listDataHeader.add(headerString5[i]);

                //to find the child Data
                List<String> child = new ArrayList<>();
                child.add(childString5[i]);  //to get the childString and add them with "child" ArrayList


                //to add the child data with listDataChild
                listDataChild.put(listDataHeader.get(i),child);  //here ""listDataHeader is a key and value is "child" and we find one by one index in the Array

            }
        }

        else if(id.equalsIgnoreCase("Medical Center"))
        {
            for (int i=0;i<headerString6.length;i++)   //the loop is continued till last headerString,if there is 10 headerString it will continue 0 to 9 and 10 header wil create
            {
                //adding header data with listDataHeader
                listDataHeader.add(headerString6[i]);

                //to find the child Data
                List<String> child = new ArrayList<>();
                child.add(childString6[i]);  //to get the childString and add them with "child" ArrayList


                //to add the child data with listDataChild
                listDataChild.put(listDataHeader.get(i),child);  //here ""listDataHeader is a key and value is "child" and we find one by one index in the Array

            }
        }

        else if(id.equalsIgnoreCase("National Hospital"))
        {
            for (int i=0;i<headerString7.length;i++)   //the loop is continued till last headerString,if there is 10 headerString it will continue 0 to 9 and 10 header wil create
            {
                //adding header data with listDataHeader
                listDataHeader.add(headerString7[i]);

                //to find the child Data
                List<String> child = new ArrayList<>();
                child.add(childString7[i]);  //to get the childString and add them with "child" ArrayList


                //to add the child data with listDataChild
                listDataChild.put(listDataHeader.get(i),child);  //here ""listDataHeader is a key and value is "child" and we find one by one index in the Array

            }
        }



    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(DoctorListActivity.this,HospitalActivity.class);
        startActivity(intent);

    }
}
