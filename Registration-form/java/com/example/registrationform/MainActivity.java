package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner sp;
    String Colector="";
    TextView txtalertName;
    EditText eText,eText2,UserName,UserPassword,UserContact,UserComment,UserAge,UserDOB;
    Button SubmitSave;
    RadioButton Malebtn,Femalbtn;
    CheckBox html,css;
    DatePickerDialog Datepicker;
    TimePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=findViewById(R.id.SpCountry);
        UserName=findViewById(R.id.userName);
        UserPassword=findViewById(R.id.userPassword);
        UserAge=findViewById(R.id.UserAge);
        UserContact=findViewById(R.id.userContact);
        txtalertName=findViewById(R.id.userAlert);
        Malebtn =findViewById(R.id.Male);
        Femalbtn=findViewById(R.id.Female);
        html=findViewById(R.id.HTML);
        css=findViewById(R.id.CSS);

        List<String> categoryCountry=new ArrayList<>();
        categoryCountry.add("Select Category:");
        categoryCountry.add("MARRIED");
        categoryCountry.add("SINGLE");
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categoryCountry);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if(parent.getItemAtPosition(position).equals("Select Category")){
                    //Do Nothing

                }
                else{
                    String item=parent.getItemAtPosition(position).toString();
                    Colector+=item+"\n";
                    Toast.makeText(MainActivity.this, "Selected Category: "+item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        eText=findViewById(R.id.usercomment);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eText.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        eText2=findViewById(R.id.UserDOB);
        eText2.setInputType(InputType.TYPE_NULL);
        eText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr1 = Calendar.getInstance();
                int day = cldr1.get(Calendar.DAY_OF_MONTH);
                int month = cldr1.get(Calendar.MONTH);
                int year = cldr1.get(Calendar.YEAR);
                // date picker dialog
                Datepicker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                Datepicker.show();
            }
        });

        SubmitSave=findViewById(R.id.btnSubmit);
        SubmitSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = UserName.getText().toString();
                String address=UserPassword.getText().toString();
                String age=UserAge.getText().toString();
                String dob = UserDOB.getText().toString();
                String contact=UserContact.getText().toString();
                String time=UserComment.getText().toString();

                if (name.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill the NAME field",Toast.LENGTH_SHORT).show();
                }

                else if(address.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill the ADDRESS field",Toast.LENGTH_SHORT).show();
                }

                else if(age.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill the AGE field",Toast.LENGTH_SHORT).show();
                }

                else if(dob.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill the DOB filed",Toast.LENGTH_SHORT).show();
                }

                else if (contact.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill the CONTACT field",Toast.LENGTH_SHORT).show();
                }

                else if (time.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill the TIME field",Toast.LENGTH_SHORT).show();
                }

                else{

                    Colector+=name+"\n";
                    Colector+=address+"\n";
                    Colector+=age+"\n";
                    Colector+=dob+"\n";
                    Colector+=contact+"\n";
                    Colector+=time+"\n";

                    Integer Male;
                    Integer Female;

                    if(Malebtn.isChecked()){
                        Male = 1;
                        Colector+="Male"+"\n";
                    }
                    else{
                        Female = 1;
                        Colector+="Female"+"\n";
                    }


                    if (html.isChecked()){
                        Colector+="Smoking"+"\n";
                        if (css.isChecked()) {
                            Colector += "Drinking" + "\n";
                        }
                    }
                    Toast.makeText(MainActivity.this,"User Info \n:"+Colector,Toast.LENGTH_SHORT).show();
                }

                if (view == SubmitSave){
                    startActivity(new Intent(MainActivity.this, MainActivity2.class));
                }

            }
        });

        Button reset = (Button)findViewById(R.id.btnReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == reset) {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
            }
        });
    }
}