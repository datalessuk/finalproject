package mark.harrison.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

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

                int userAge = calculateAge(date);

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
            if(!passwordChecker(mpasswordInput.getText().toString())){

                mpasswordInput.setError("Sorry your password has to be longer than 6 with one special character"); // Working
            }
            if(!emailCheck(mEamilImput.getText().toString())){
                mEamilImput.setError("Sorry that is not a valid Email Address");
            }


            }
        });

    //End of input for date

    }
    //To make sure the passworld is longer than six chars and has one speical charicater

    public boolean passwordChecker(String pPassWord){
        Pattern special = Pattern.compile ("[!@#$£%&*()_+=|<>?{}\\[\\]~-]");
        if(pPassWord.length() >6){
            Matcher hasSpecial = special.matcher(pPassWord);

            return hasSpecial.find();
        }
        else {
            return false;
        }

    }

    //Works out the user ages

    private int calculateAge(String pDOBString){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(pDOBString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }


        return age;
    }
    //Email Validation
    private boolean emailCheck(String pEmail){

        Pattern pattern = Patterns.EMAIL_ADDRESS;

        return pattern.matcher(pEmail).matches();

    }


}
