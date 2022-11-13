package vn.edu.poly.ph26439_mob201_assi.Frg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import vn.edu.poly.ph26439_mob201_assi.LoginActivity;
import vn.edu.poly.ph26439_mob201_assi.MainActivity;
import vn.edu.poly.ph26439_mob201_assi.R;
import vn.edu.poly.ph26439_mob201_assi.RegisterActivity;

public class QuanLyTaiKhoanFragment extends Fragment {
    EditText ed_pass,ed_newpass;
    Button btnluu;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    public QuanLyTaiKhoanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_tai_khoan, container, false);
        ed_pass = view.findViewById(R.id.ed_pass);
        ed_newpass = view.findViewById(R.id.ed_newpass);
        progressDialog = new ProgressDialog(getActivity());
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        btnluu = view.findViewById(R.id.btnluu);
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePass();
            }
        });


        return view;
    }

    private void changePass() {
        String pass = ed_pass.getText().toString();
        String newpass = ed_newpass.getText().toString();
        if(pass.isEmpty()||pass.length()<0){
            ed_pass.setError("Email không hợp lệ");
        }else if(newpass.isEmpty()||newpass.length()<6){
            ed_newpass.setError("Pass không hợp lệ");
        }else{
            progressDialog.setMessage("Okkk...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.confirmPasswordReset(pass,newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(getActivity(), "Okkk", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}