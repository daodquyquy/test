package vn.edu.poly.ph26439_mob201_assi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    TextView tv_Login;
    private EditText ed_user,ed_pass,ed_email,ed_repass;
    Button btndangKy;
    String EmailPatten = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed_user = findViewById(R.id.ed_user);
        ed_pass = findViewById(R.id.ed_pass);
        ed_repass = findViewById(R.id.ed_repass);
        ed_email = findViewById(R.id.ed_email);
        tv_Login =findViewById(R.id.tvLogin);
        btndangKy =findViewById(R.id.btnSigup);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        btndangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });
        tv_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    private void PerforAuth() {
        String user =ed_user.getText().toString();
        String email=ed_email.getText().toString();
        String pass=ed_pass.getText().toString();
        String repass=ed_repass.getText().toString();
        if(user.isEmpty()||user.length()<6){
            ed_user.setError("User không hợp lệ");
        }
        else if(!email.matches(EmailPatten)){
            ed_email.setError("Email không hợp lệ");
        }else if(pass.isEmpty()||pass.length()<6){
            ed_pass.setError("Pass không hợp lệ");
        }else if(!pass.equals(repass)){
            ed_repass.setError("Repass không giống pass");
        }else{
            progressDialog.setMessage("Okkk...");
            progressDialog.setTitle("Register");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();

                        Toast.makeText(RegisterActivity.this, "Okkk", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}