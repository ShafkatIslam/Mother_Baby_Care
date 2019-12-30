package com.example.professt.mother_baby_care;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BabyRegisterActivity extends AppCompatActivity {

    public Spinner spinner;

    DatePickerDialog datePickerDialog;

    public EditText editbabyName,editMotherEmail,editMotherCell,editFatherCell,editBabyPassword;
    TextView babyBirthDate;
    public Button babyBirthdayId,buttonBabyRegister;

    String[] babyGenders;

    private ProgressDialog loading;

    String parsedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_register);

        editbabyName = (EditText)findViewById(R.id.editbabyName);
        editMotherEmail = (EditText)findViewById(R.id.editMotherEmail);
        editMotherCell = (EditText)findViewById(R.id.editMotherCell);
        editFatherCell = (EditText)findViewById(R.id.editFatherCell);
        editBabyPassword = (EditText)findViewById(R.id.editBabyPassword);
        babyBirthDate = (TextView)findViewById(R.id.babyBirthdayTextViewId);

        buttonBabyRegister = (Button)findViewById(R.id.buttonBabyRegister);
        babyBirthdayId = (Button)findViewById(R.id.babyBirthdayId);

        spinner = (Spinner) findViewById(R.id.spinnerId);

        babyGenders =getResources().getStringArray(R.array.baby_gender);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BabyRegisterActivity.this,R.layout.sample_view,R.id.textViewId,babyGenders); //creating object of ArrayAdapter....thre are 4 parameters in ArrayAdapter
        spinner.setAdapter(adapter);   //setting the adapter in spinner

        babyBirthdayId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePicker datePicker = new DatePicker(BabyRegisterActivity.this);
                int currentday= datePicker.getDayOfMonth();
                int currentMonth = (datePicker.getMonth());
                int currentYear = datePicker.getYear();
                datePickerDialog = new DatePickerDialog(BabyRegisterActivity.this,

                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                String result = (dayOfMonth+"/"+(month+1)+"/"+year);
                                babyBirthDate.setText(result);

//                                EventClass eventClass = EventClass.getInstance();
//                                eventClass.setDate(result);

                            }
                        },currentYear,currentMonth,currentday);

                datePickerDialog.show();
            }
        });

        buttonBabyRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sign_up();
            }
        });
    }

    private void sign_up() {

        final String babyName,babyEmail,babyMotherCell,babyFatherCell,babyBirthDay,babyGender,babyPassword,breminder,vaccine;

        babyName = editbabyName.getText().toString();
        babyEmail = editMotherEmail.getText().toString();
        babyMotherCell = editMotherCell.getText().toString();
        babyFatherCell = editFatherCell.getText().toString();
        babyPassword = editBabyPassword.getText().toString();
        babyBirthDay = babyBirthDate.getText().toString();
        babyGender = spinner.getSelectedItem().toString();


        Log.d("RESPONSE", "" + babyBirthDay);



        if(babyName.isEmpty())
        {
            editbabyName.setError("Please Enter Name");
            editbabyName.requestFocus();
        }

        else if(babyEmail.isEmpty())
        {
            editMotherEmail.setError("Please Enter Name");
            editMotherEmail.requestFocus();
        }

        else if(babyMotherCell.isEmpty())
        {
            editMotherCell.setError("Please Enter Name");
            editMotherCell.requestFocus();
        }

        else if(babyFatherCell.isEmpty())
        {
            editFatherCell.setError("Please Enter Name");
            editFatherCell.requestFocus();
        }

        else if(babyPassword.isEmpty())
        {
            editBabyPassword.setError("Please Enter Name");
            editBabyPassword.requestFocus();
        }

        else if(babyBirthDay.isEmpty())
        {
            Toast.makeText(BabyRegisterActivity.this,"Please select Birthdate",Toast.LENGTH_SHORT).show();
        }

        else if(babyGender.isEmpty())
        {
            Toast.makeText(BabyRegisterActivity.this,"Please select gender",Toast.LENGTH_SHORT).show();
        }


        else
        {

            try {
                Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(babyBirthDay);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                parsedDate = formatter.format(initDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.load);
            loading.setTitle("Sign Up");
            loading.setMessage("Please wait...");
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.B_SIGNUP_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //for track response in Logcat
                    Log.d("RESPONSE", "" + response);

                    //if we are getting success from server
                    if (response.equals("success")) {
                        //creating a shared preference
                        loading.dismiss();
                        //starting profile activity
                        Intent intent = new Intent(BabyRegisterActivity.this, MainActivity.class);
                        startActivity(intent);

                        Toast.makeText(BabyRegisterActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();

                        editbabyName.setText("");
                        editMotherEmail.setText("");
                        editMotherCell.setText("");
                        editFatherCell.setText("");
                        editBabyPassword.setText("");
                        babyBirthDate.setText("");

                    } else if (response.equals("exists")) {
                        Toast.makeText(BabyRegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    } else if (response.equals("failure")) {
                        Toast.makeText(BabyRegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(BabyRegisterActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //return super.getParams();

                    Map<String,String> params = new HashMap<>();

                    //Ading parameters to request
                    params.put(Key.KEY_BNAME,babyName);
                    params.put(Key.KEY_BMEMAIL,babyEmail);
                    params.put(Key.KEY_BUSER,babyMotherCell);
                    params.put(Key.KEY_BFCELL,babyFatherCell);
                    params.put(Key.KEY_BPASSWORD,babyPassword);
                    params.put(Key.KEY_BDAY,parsedDate);
                    params.put(Key.KEY_BGENDER,babyGender);

                    Log.d("info",""+babyName+" "+babyFatherCell+" "+parsedDate);
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
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(BabyRegisterActivity.this,MainRegisterActivity.class);
        startActivity(intent);

    }
}
