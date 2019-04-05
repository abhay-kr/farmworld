package com.example.hp.framWorld;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginfragment extends Fragment implements View.OnClickListener {
    ImageButton btnLoginDialog;
    ImageButton btnSignUp;

    ProgressDialog progressDialog;

    //setup firebase auth
    FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view =inflater.inflate(R.layout.fragment_login,container,false);
        btnLoginDialog = (ImageButton) view.findViewById(R.id.btnLoginDialog);
        btnSignUp=(ImageButton)view.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        btnLoginDialog.setOnClickListener(this);
        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }
    public void onClick(View v) {
        if (v == btnLoginDialog) {

            // Create Object of Dialog class
            final Dialog login = new Dialog(getActivity());
            // Set GUI of login screen
            login.setContentView(R.layout.login);
            login.setTitle("Login to Pulse 7");






            // Init button of login GUI
            Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
            Button btnCancel = (Button) login.findViewById(R.id.btnCancel);
            final EditText txtUsername = (EditText)login.findViewById(R.id.txtUsername);
            final EditText txtPassword = (EditText)login.findViewById(R.id.txtPassword);

            firebaseAuth=FirebaseAuth.getInstance();

            progressDialog=new ProgressDialog(getActivity());

            FirebaseUser user =firebaseAuth.getCurrentUser();

            if (user!=null){
                getActivity().finish();
                startActivity(new Intent(getActivity(),Homeactivity.class));
            }

            // Attached listener for login GUI button
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(txtUsername.getText().toString().trim().length() > 0 && txtPassword.getText().toString().trim().length() > 0)
                    {
                        validate(txtUsername.getText().toString().trim(),txtPassword.getText().toString().trim());
                        // Validate Your login credential here than display message
//                        Toast.makeText(getActivity(), "Login Sucessfull", Toast.LENGTH_LONG).show();

                        // Redirect to dashboard / home screen.
//                        login.dismiss();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Please enter Username and Password", Toast.LENGTH_LONG).show();

                    }
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login.dismiss();
                }
            });

            // Make dialog box visible.
            login.show();
        }

        if (v==btnSignUp){

            // Create Object of Dialog class
            final Dialog signUp = new Dialog(getActivity());
            // Set GUI of login screen
            signUp.setContentView(R.layout.signup);
            signUp.setTitle("Sign Up");


            // Init button of login GUI
            Button btnRegister = (Button) signUp.findViewById(R.id.btnRegister);
            Button btnCancel = (Button) signUp.findViewById(R.id.btnCancel);
            final EditText txtRegUsername = (EditText)signUp.findViewById(R.id.txtRegUserName);
            final EditText txtRegPassword = (EditText)signUp.findViewById(R.id.txtRegPassword);
            TextView txtAlreadyUser=(TextView)signUp.findViewById(R.id.txtAlreadyUser);

            firebaseAuth=FirebaseAuth.getInstance();

            progressDialog=new ProgressDialog(getActivity());

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(txtRegUsername.getText().toString().trim().length() > 0 && txtRegPassword.getText().toString().trim().length() > 0)
                    {
                        firebaseAuth.createUserWithEmailAndPassword(txtRegUsername.getText().toString().trim(),txtRegPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getActivity(),"Registeration Sucessful",Toast.LENGTH_SHORT).show();
                                    validate(txtRegUsername.getText().toString().trim(),txtRegPassword.getText().toString().trim());
                                    startActivity(new Intent(getActivity(),Homeactivity.class));
                                }
                                else{
                                    Toast.makeText(getActivity(),"Registeration Failed",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Please enter Username and Password", Toast.LENGTH_LONG).show();

                    }

                }
            });

            txtAlreadyUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),Homeactivity.class));
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signUp.dismiss();

                }
            });

            signUp.show();
        }
    }

    private void validate(String userName, String userPassword){

        progressDialog.setMessage("Logging in Please Wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),Homeactivity.class));
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),"Login Failed",Toast.LENGTH_SHORT).show();


                }
            }
        });


    }
}

