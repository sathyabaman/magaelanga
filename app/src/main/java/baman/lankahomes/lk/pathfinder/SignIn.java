package baman.lankahomes.lk.pathfinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends ActionBarActivity {

    Button btn_signin;
    EditText login_email;
    EditText login_password;

    String error_message = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        login_email = (EditText) findViewById(R.id.et_login_email);
        login_password = (EditText) findViewById(R.id.et_login_password);

    }

    public void sign_in(View v){
        String stored_email = "";
        String Stored_password ="";
        String email = login_email.getText().toString();
        String passw = login_password.getText().toString();

                    try {// checking the users login credentials in local
                        SharedPreferences prefs = getSharedPreferences("pathRegistration", MODE_PRIVATE);
                        boolean isloggedin = prefs.getBoolean("is_logged_in", false);
                            if(isloggedin) {
                                stored_email = prefs.getString("email", "No name defined");
                                Stored_password = prefs.getString("password", "No password ");
                            }
                    }catch (Exception e) {
                        Toast toast = Toast.makeText(this, "Unable to login. Please try again later.", Toast.LENGTH_SHORT);
                        toast.show();
                    }

            if(email.equals(stored_email) && passw.equals(Stored_password) && !email.matches("") && !passw.matches("")){
                startActivity(new Intent(SignIn.this, MainActivity.class));
            }else{
                error_message = "Invalid email address or password. Please try again";
                showErrorMessage(error_message);
            }
    }


    public void sign_up(View v){
        startActivity(new Intent(SignIn.this, SignUp.class));
    }


    private void showErrorMessage(String data){
        new AlertDialog.Builder(this)
                .setTitle("Error Occured!")
                .setMessage(data)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
