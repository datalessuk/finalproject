package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView mEmail;
    private TextView mPassword;
    private ProgressBar mProgressBar;

    Button mLogInButton;

    String mEmailInput;
    String mPasswordInput;

    public void onClick(View v){
        Intent openSignUp = new Intent(getApplicationContext(),signUpActivity.class);
        startActivity(openSignUp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Firebase
        mAuth = FirebaseAuth.getInstance();

        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);

        mEmail = findViewById(R.id.loginEmailInput);
        mPassword =(TextView) findViewById(R.id.passwordInput);

        if(mAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),homeScreen.class));
            finish();
        }

        mLogInButton = (Button)findViewById(R.id.signInButton);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);

                if(!inputValidation.getInstance().whiteSpaceCheck(mEmail.getText().toString())){
                    mEmail.setError("Please enter your Email Address");
                }else {
                    mEmailInput = mEmail.getText().toString();

                }
                if(!inputValidation.getInstance().whiteSpaceCheck(mPassword.getText().toString())){
                    mPassword.setError("Please enter you password");
                }else {
                    mPasswordInput = mPassword.getText().toString();
                }
                // end of input Validation

                if(mEmailInput ==null || mPasswordInput ==null ){//Dont like
                    Toast.makeText(LoginActivity.this,"Sorry ",Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(mEmailInput,mPasswordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                mProgressBar.setVisibility(View.GONE);
                                startActivity(new Intent(getApplicationContext(),homeScreen.class));
                            }
                            else {
                                //Add Dialog box
                                AlertDialog.Builder noAccountDialog = new AlertDialog.Builder(LoginActivity.this);
                                noAccountDialog.setTitle("Error").setMessage("Sorry we can not find your account, please try again or if you don't have an account please sign up").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();


                            }
                        }
                    });
                }

            }
        });



    }

}
