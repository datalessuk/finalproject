package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class signUpActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private TextView mDisplayDate;
    private TextView mpasswordInput;
    private TextView mEamilImput;
    private TextView mfirstNameinput;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    EditText dateOfBirthInput;
    EditText passwordInput;
    Button mCreateAccountbutton;

    int year;
    int month;
    int day ;
    String date;
    String pPasswordInput;
    String mEmail;
    String mPassword;
    String mFirstname;
    String mLastName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
       // if(mAuth.getCurrentUser() !=null){
            //startActivity(new Intent(getApplicationContext(),homeScreen.class));
        //}


        //Input for password
        mpasswordInput = (TextView) findViewById(R.id.signUpPasswordInput);
        //Input for Email
        mEamilImput = (TextView) findViewById(R.id.emailInput);
        //input for firstname
        mfirstNameinput = (TextView) findViewById(R.id.firstNameText);


       //All the input for the date
        mDisplayDate = (TextView) findViewById(R.id.datePicker);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        signUpActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);

                int userAge = inputValidation.getInstance().calculateAge(date);

                if(userAge < 18){
                    mCreateAccountbutton.setEnabled(false);
                    //Toast.makeText(signUpActivity.this,"Sorry You need to be over 18 to use Craft Watch",Toast.LENGTH_LONG).show();
                    mDisplayDate.setError("Sorry you need to be over 18 to use Craft Watch");
                }
                else {
                    mCreateAccountbutton.setEnabled(true);
                    mDisplayDate.setError(null);
                }
            }
        };

        mCreateAccountbutton = findViewById(R.id.createAccount);
        mCreateAccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(!inputValidation.getInstance().passwordChecker(mpasswordInput.getText().toString())){

                mpasswordInput.setError("Sorry your password has to be longer than 6 with one special character"); // Working
            }
            else {
                mPassword = mpasswordInput.getText().toString();
            }
            if(!inputValidation.getInstance().emailCheck(mEamilImput.getText().toString())){
                mEmail = null;
                mEamilImput.setError("Sorry that is not a valid Email Address");
            }
            else {
                mEmail = mEamilImput.getText().toString();
            }

            if(!inputValidation.getInstance().whiteSpaceCheck(mfirstNameinput.getText().toString())){
                mfirstNameinput.setError("Please enter your first name");
            }
            else {
                mFirstname= mfirstNameinput.getText().toString();
            }



            if(mEmail ==null || mPassword ==null){

            }
            else {
                mAuth.createUserWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(mFirstname,mEmail,mPassword);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("User");
                            myRef.setValue(user);
                            /*FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(signUpActivity.this,"Added To databasse",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });*/

                            //Toast.makeText(signUpActivity.this,"User Made",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),homeScreen.class));
                        }

                    }
                });

            }


            }



        });

    //End of input for date

    }



}
