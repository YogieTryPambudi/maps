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

public class LoginActivity extends AppCompatActivity {
private EditText txtemaillogin;
private EditText txtpass;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtemaillogin=(EditText)findViewById(R.id.txtemaillogin);
        txtemaillogin.getText().clear();
        txtpass=(EditText)findViewById(R.id.txtpasslogin);
        txtpass.getText().clear();
        firebaseAuth=FirebaseAuth.getInstance();
    }
    public void  btnlogin_CLick(View v){
        String email=txtemaillogin.getText().toString().trim();
        String password=txtpass.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"enter password",Toast.LENGTH_LONG).show();
            return;
        }
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this,"please wait","processing",true);
        (firebaseAuth.signInWithEmailAndPassword(txtemaillogin.getText().toString(),txtpass.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        txtemaillogin.getText().clear();
                        txtpass.getText().clear();
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_LONG).show();
                            Intent i =new Intent(LoginActivity.this,SaveActivity.class);
                            startActivity(i);
                        } else {
                            Log.e("Error",task.getException().toString());
                        }
                    }
                });
    }


}
