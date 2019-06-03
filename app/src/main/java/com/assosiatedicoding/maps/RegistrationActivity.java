package com.assosiatedicoding.maps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
private EditText txtemail;
private EditText txtpassword;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txtemail=(EditText)findViewById(R.id.txtemailregister);
        txtemail.getText().clear();
        txtpassword=(EditText)findViewById(R.id.txtpasswordregister);
        txtemail.getText().clear();
        firebaseAuth=FirebaseAuth.getInstance();
    }
    public void btnregister_Click(View v){
        String email=txtemail.getText().toString().trim();
        String password=txtpassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"enter password",Toast.LENGTH_LONG).show();
            return;
        }
        final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this,"please wait","processing",true);
        (firebaseAuth.createUserWithEmailAndPassword(txtemail.getText().toString(),txtpassword.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        txtemail.getText().clear();
                        txtpassword.getText().clear();
                        if(task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this,"success",Toast.LENGTH_LONG).show();
                            Intent i =new Intent(RegistrationActivity.this,LoginActivity.class);
                            startActivity(i);
                        } else {
                            Log.e("Error",task.getException().toString());
                        }
                    }
                });
    }
}
