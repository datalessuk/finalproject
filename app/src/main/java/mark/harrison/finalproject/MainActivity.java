package mark.harrison.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView mEmail;
    private TextView mPassword;

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



        mEmail = findViewById(R.id.loginEmailInput);
        mPassword =(TextView) findViewById(R.id.passwordInput);

        mLogInButton = (Button)findViewById(R.id.signInButton);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    Toast.makeText(MainActivity.this,"Sorry ",Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(mEmailInput,mPasswordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),homeScreen.class));
                            }
                            else {
                                Toast.makeText(MainActivity.this,"Sorry you",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });



    }

}
