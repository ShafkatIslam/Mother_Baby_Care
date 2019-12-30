package com.example.professt.mother_baby_care.Mother.DoctorFind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.professt.mother_baby_care.R;

import java.util.HashMap;
import java.util.List;

public class CustomAdapter extends BaseExpandableListAdapter {

    private Context context;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;


    public CustomAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {  //constructor create
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {    //how many groups or dataHeader are set in the expandableListView that is counted here
        return listDataHeader.size();  //we want to receive how many dataHeader we have
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).size();  //we have find in which listdataHeader the listDataChild is situated.So we have to find the position listDataHeader and then we find the listDataChild of that listDataHeader
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);   //the position of the listDataHeader
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);   //the position of the listDataChild in every listDataHeader
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;                       //return the groupPosition
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;                    //return the childPosition
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    //the text which is in the group or header that is collected in this method and then that collected text is shown in the group_layout using group_layout and then return the view
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        //to get the the text of header
        String headerText = (String) getGroup(groupPosition);  //it will return the header text from every group position

        if(convertView==null)   //check the condition if the convertView is null or not. If null we will inflate covert the group_layout into view by using Layout Inflater
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //we need LayoutInflater to covert a xml file to view....So we declare a variable of LayoutInflater
            convertView = inflater.inflate(R.layout.group_layout,null); // inflate method convert the total group_layout into a view and retuns a view and keep it in a variable "convertView"

        }

        TextView headerTextView= (TextView) convertView.findViewById(R.id.headerTextViewId);  //we will find the textView which is in the group_layout by using convertView
        headerTextView.setText(headerText);    //we will set the headerText in headerTextView

        return convertView;  //it returns to the adapter
    }


    //the string which is in the child that is collected in this method and then that collected string is shown in the child_layout using child_layout and then return the view
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        //to get the the string of child
        String childText = (String) getChild(groupPosition,childPosition);  //it will return the child text from every group position

        if(convertView==null)   //check the condition if the convertView is null or not. If null we will inflate covert the child_layout into view by using Layout Inflater
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //we need LayoutInflater to covert a xml file to view....So we declare a variable of LayoutInflater
            convertView = inflater.inflate(R.layout.child_layout,null); // inflate method convert the total child_layout into a view and retuns a view and keep it in a variable "convertView"

        }

        TextView childTextView= (TextView) convertView.findViewById(R.id.childTextViewId);  //we will find the textView which is in the child_layout by using convertView
        childTextView.setText(childText);    //we will set the childText in childTextView

        return convertView;  //it returns to the adapter
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {  //When Child is clicked it returns true
        return true;
    }
}


