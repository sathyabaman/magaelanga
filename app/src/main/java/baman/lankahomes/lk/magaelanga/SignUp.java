package baman.lankahomes.lk.magaelanga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class SignUp extends ActionBarActivity {

    Button btn_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


    }

    public void sign_in(View v){
        startActivity(new Intent(SignUp.this, MainActivity.class));
    }
}
