package com.example.professt.mother_baby_care;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public EditText editName1,editEmail1,editCell1,editHusbandCell,editweek1,editPassword1;
    public TextView editblood1;
    public Button buttonRegister1;
    Spinner bloodSpinner;

    private ProgressDialog loading;

    String[] arrrrrname = {"Select","A+","B+","AB+","O+","A-","B-","AB-","O-"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName1 = (EditText)findViewById(R.id.editName);
        editEmail1 = (EditText)findViewById(R.id.editEmail);
        editCell1 = (EditText)findViewById(R.id.editCell);
        editHusbandCell = (EditText)findViewById(R.id.editHusbandCell);
        editblood1 = (TextView)findViewById(R.id.editblood);
        editweek1 = (EditText)findViewById(R.id.editweek);
        editPassword1 = (EditText)findViewById(R.id.editPassword);
        bloodSpinner = (Spinner)findViewById(R.id.bloodSpinnerId);

        buttonRegister1 = (Button)findViewById(R.id.buttonRegister);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, arrrrrname);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodSpinner.setAdapter(arrayAdapter);
        bloodSpinner.setOnItemSelectedListener(this);

        buttonRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sign_up();
                
            }
        });
    }

    private void sign_up() {

        final String motherName,motherEmail,motherCell,husbandCell,motherBlood,motherWeek,motherPassword,doctors,reminder,vaccine;

        motherName = editName1.getText().toString().trim();
        motherEmail = editEmail1.getText().toString().trim();
        motherCell = editCell1.getText().toString().trim();
        husbandCell = editHusbandCell.getText().toString().trim();
        motherBlood = editblood1.getText().toString().trim();
        motherWeek = editweek1.getText().toString().trim();
        motherPassword = editPassword1.getText().toString().trim();

        DatePicker datePicker = new DatePicker(RegisterActivity.this);
        int currentday= datePicker.getDayOfMonth();
        int currentMonth = (datePicker.getMonth()+1);
        int currentYear = datePicker.getYear();

        int y2,m2,d2;

        y2= currentYear*12;
        m2 = y2+currentMonth;
        d2= m2*30;
        d2= d2+currentday+1;

        final String days = String.valueOf(d2);

        if(motherName.isEmpty())
        {
            editName1.setError("Please Enter Name");
            editName1.requestFocus();
        }

        else if(motherEmail.isEmpty())
        {
            editEmail1.setError("Please Enter Email");
            editEmail1.requestFocus();
        }

        else if(motherCell.isEmpty())
        {
            editCell1.setError("Please Enter Cell");
            editCell1.requestFocus();
        }

        else if(husbandCell.isEmpty())
        {
            editHusbandCell.setError("Please Enter Husband Cell");
            editHusbandCell.requestFocus();
        }

        else if(motherBlood.isEmpty())
        {
            editblood1.setError("Please Enter Blood Group");
            editblood1.requestFocus();
        }

        else if(motherWeek.isEmpty())
        {
            editweek1.setError("Please Enter Week of Pregnancy");
            editweek1.requestFocus();
        }

        else if(motherPassword.isEmpty())
        {
            editPassword1.setError("Please Enter Password");
            editPassword1.requestFocus();
        }

        else if(motherBlood.equalsIgnoreCase("select"))
        {
            Toast.makeText(RegisterActivity.this,"Please select a blood group",Toast.LENGTH_SHORT).show();
        }

        else
        {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.load);
            loading.setTitle("Sign Up");
            loading.setMessage("Please wait...");
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.SIGNUP_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //for track response in Logcat
                    Log.d("RESPONSE", "" + response);

                    //if we are getting success from server
                    if (response.equals("success")) {
                        //creating a shared preference
                        loading.dismiss();
                        //starting profile activity
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);

                        Toast.makeText(RegisterActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();

                        editName1.setText("");
                        editEmail1.setText("");
                        editCell1.setText("");
                        editHusbandCell.setText("");
                        editblood1.setText("");
                        editweek1.setText("");
                        editPassword1.setText("");

                    } else if (response.equals("exists")) {
                        Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    } else if (response.equals("failure")) {
                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //return super.getParams();

                    Map<String,String> params = new HashMap<>();

                    //Ading parameters to request
                    params.put(Key.KEY_MNAME,motherName);
                    params.put(Key.KEY_MEMAIL,motherEmail);
                    params.put(Key.KEY_MCELL,motherCell);
                    params.put(Key.KEY_MUSER,husbandCell);
                    params.put(Key.KEY_BLOOD,motherBlood);
                    params.put(Key.KEY_WEEK,motherWeek);
                    params.put(Key.KEY_PASSWORD,motherPassword);
                    params.put(Key.KEY_DATE,days);

                    Log.d("info",""+motherName+" "+motherCell);
                    //returning parameter
                    return params;

                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String motherBloods = bloodSpinner.getSelectedItem().toString();
        editblood1.setText(motherBloods);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(RegisterActivity.this,MainRegisterActivity.class);
        startActivity(intent);

    }
}
