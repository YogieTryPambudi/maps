package com.assosiatedicoding.maps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SaveActivity extends AppCompatActivity implements View.OnClickListener{
private FirebaseAuth mAuth;
private Button buttonsave;
private EditText editTextLatitude;
private EditText editTextLongitude;
private Button buttonproceed;
private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
      //  buttonproceed=(Button)findViewById(R.id.btnproceed);
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
        editTextLatitude=(EditText)findViewById(R.id.edittextlatitude);
        editTextLongitude=(EditText)findViewById(   R.id.edittextlongitude);
        buttonsave=(Button)findViewById(R.id.btnsave);
        FirebaseUser user=mAuth.getCurrentUser();

    }
    private void saveUserInformation(){
       double    latitude=Double.parseDouble(editTextLatitude.getText().toString().trim());
       double longitude=Double.parseDouble(editTextLongitude.getText().toString().trim());
       UserInformation userInformation = new UserInformation(latitude,longitude);
       FirebaseUser user=mAuth.getCurrentUser();
       mDatabase.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this,"Information saved",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonsave){
            saveUserInformation();
            editTextLatitude.getText().clear();
            editTextLongitude.getText().clear();
        }
    }
}
