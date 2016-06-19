package baman.lankahomes.lk.pathfinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends ActionBarActivity {
    EditText fullname;
    EditText email;
    EditText password;
    EditText telephone;
    Spinner country;
    EditText city;
    Button signup;
    String ErrorMessage="";
    String SuccessMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullname    = (EditText) findViewById(R.id.et_login_email);
        email       = (EditText) findViewById(R.id.et_email_address);
        password    = (EditText) findViewById(R.id.et_password);
        telephone   = (EditText) findViewById(R.id.et_telephone);
        country     = (Spinner) findViewById(R.id.sp_country);
        city        = (EditText) findViewById(R.id.et_city);
        signup      = (Button) findViewById(R.id.btn_sign_up_screen);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    //need to check with the server


                    // send data to server


                    // if success from server add to shared preferences
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("pathRegistration", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putBoolean("is_logged_in", true);
                    editor.putString("fullname", fullname.getText().toString());
                    editor.putString("email", email.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.putString("telephone", telephone.getText().toString());
                    editor.putString("county", country.getSelectedItem().toString());
                    editor.putString("city", city.getText().toString());
                    editor.commit();

                    //then show success message
                    showSuccessMessage(SuccessMessage);
                }else{
                    showErrorMessage(ErrorMessage);
                }
            }
        });
    }


    private boolean validateInput(){
        String fname = fullname.getText().toString();
        String emailad = email.getText().toString();
        String pass = password.getText().toString();
        String tele = telephone.getText().toString();
        String county = country.getSelectedItem().toString();
        String cty = city.getText().toString();
        if(fname.length() < 5 ||fname.equals("Full Name")){
            ErrorMessage ="FullName not valid, Must have minimum of 5 characters.";
            return false;
        }else if(!isEmailValid(emailad)){
            ErrorMessage ="Email address not valid.";
            return false;
        }else if(pass.length() < 5 || pass.equals("Enter Password")){
            ErrorMessage ="Password Invalid. Password must have minimum of 5 characters.";
            return false;
        }else if(tele.length() < 9 || tele.equals("+14155552671")){
            ErrorMessage ="Telephone number Invalid. Please enter valid telephone number.";
            return false;
        }else if(cty.length() < 3 || cty.equals("City or State")){
            ErrorMessage ="Invalid City name.Must have minimum 3 characters.";
            return false;
        }else{
            SuccessMessage = "Registration Successful. You will receive an email shortly! . You can login now.";
            return true;
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
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

    private void showSuccessMessage(String data){
        new AlertDialog.Builder(this)
                .setTitle("Registration Successful!")
                .setMessage(data)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        startActivity(new Intent(SignUp.this, SignIn.class));
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
