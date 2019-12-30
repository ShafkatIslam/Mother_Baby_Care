package com.example.professt.mother_baby_care;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.professt.mother_baby_care.Baby.BabyHomeActivity;
import com.example.professt.mother_baby_care.Mother.Mother;
import com.example.professt.mother_baby_care.Mother.MotherHomeActivity;
import com.karan.churi.PermissionManager.PermissionManager;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editCell,editPassword;
    Button buttonLogin,buttonRegister;

    String cell,password;

    private ProgressDialog loading;
    private ProgressDialog loading1;

    private AlertDialog.Builder alertdialogBuilder;

    PermissionManager permissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCell = (EditText)findViewById(R.id.editCell);
        editPassword = (EditText)findViewById(R.id.editPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);

        permissionManager = new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
                
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =  new Intent(MainActivity.this,MainRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {

        //Getting values from editTexts

        final String cell = editCell.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();



        //checking usercell field/validation

        if(cell.isEmpty())
        {
            editCell.setError("Please Enter Cell");
            editCell.requestFocus();
        }

        //checking password field/validation
        else if(password.isEmpty())
        {
            editPassword.setError("Please Enter Password");
            editPassword.requestFocus();
        }
        else
        {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.load);
            loading.setTitle("Login");
            loading.setMessage("Please wait...");
            loading.show();

            //creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Key.LOGIN_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d("RESPONSE", "" + response);

                    //if we are getting success from server
                    if (response.equals("success")) {

                        //creating a shared preference

                        SharedPreferences sp = MainActivity.this.getSharedPreferences(Key.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        //Creating editor to store values to shared preferences
                        SharedPreferences.Editor editor = sp.edit();
                        //Adding values to editor
                        editor.putString(Key.CELL_SHARED_PREF, cell);

                        //Saving values to editor
                        editor.commit();

                        loading.dismiss();

                        Mother mother = Mother.getInstance();
                        mother.setNumber(cell);
                        //starting profile activity
                        Intent intent = new Intent(MainActivity.this, MotherHomeActivity.class);
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                    }
                    else if (response.equals("success1")) {

                        //creating a shared preference

                        SharedPreferences sp = MainActivity.this.getSharedPreferences(Key.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        //Creating editor to store values to shared preferences
                        SharedPreferences.Editor editor = sp.edit();
                        //Adding values to editor
                        editor.putString(Key.CELL_SHARED_PREF, cell);

                        //Saving values to editor
                        editor.commit();

                        loading.dismiss();

                        Mother mother = Mother.getInstance();
                        mother.setNumber(cell);
                        //starting profile activity
                        Intent intent = new Intent(MainActivity.this, BabyHomeActivity.class);
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                    }
                    else if (response.equals("failure")) {

                        //if the server response is not success
                        //displaying an error message or toast
                        Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    } else {
                        //if the server response is not success
                        //displaying an error message or toast
                        Toast.makeText(MainActivity.this, "Invalid Usercell or password", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "There is an error", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //return super.getParams();

                    Map<String,String> params = new HashMap<>();

                    //Ading parameters to request
                    params.put(Key.KEY_CELL,cell);
                    params.put(Key.KEY_PASS,password);

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

        alertdialogBuilder = new AlertDialog.Builder(this);    //creating object of alertDialogBuilder

        //setting the properties of alertDialogBuilder:

        //for setting title
        alertdialogBuilder.setTitle("Mother & Baby Care");

        //for setting message
        alertdialogBuilder.setMessage("Do you want to exit?");

        //for setting icon
        alertdialogBuilder.setIcon(R.drawable.exit);

        //for setting cancelable
        alertdialogBuilder.setCancelable(false);

        //for setting Button
        alertdialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //exit
//                finish();;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
//                finish();;
//                System.exit(0);
//                onDestroy();
            }
        });
        alertdialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Not exit
                // Toast.makeText(MainActivity.this,"You have clicked on no button",Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        alertdialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Not exit
                //Toast.makeText(MainActivity.this,"You have clicked on cancel button",Toast.LENGTH_SHORT).show();
            }
        });

        //showing alertDialog by creating alertDialog in object and creating alertDialogBuilder in this object
        AlertDialog alertDialog = alertdialogBuilder.create();
        alertDialog.show();
    }
}
