package mark.harrison.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

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
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    EditText dateOfBirthInput;
    EditText passwordInput;
    Button mCreateAccountbutton;

    int year;
    int month;
    int day ;
    String date;
    String pPasswordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Input for password
        mpasswordInput = (TextView) findViewById(R.id.signUpPasswordInput);
        //Input for Email
        mEamilImput = (TextView) findViewById(R.id.emailInput);



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
            if(!inputValidation.getInstance().emailCheck(mEamilImput.getText().toString())){
                mEamilImput.setError("Sorry that is not a valid Email Address");
            }


            }



        });

    //End of input for date

    }



}
