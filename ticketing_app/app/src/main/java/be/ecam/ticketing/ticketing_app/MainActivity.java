package be.ecam.ticketing.ticketing_app;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.R.attr.name;

public  class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClk;
    private Button btnCreate;
    private TextView msg;
    private EditText Name, Password;
    private String name;
    private String password;
    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Connexion DB
       /* String[] db_conifon ={"ticketing_app","root","root"};
        this.db = new DatabaseManager(db_conifon);*/

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(sharedPreferences.getBoolean("background", false) ? R.style.AppThemeDayNight : R.style.AppThemeLight);

        setContentView(R.layout.connexion_activity);
        btnClk = (Button)findViewById(R.id.buttonConnection);
        btnCreate=(Button)findViewById(R.id.create_user);
        btnClk.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
        Name = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.psswd);
        msg = (TextView)findViewById(R.id.user2);
        //Connexion info
        /*name = Name.getText().toString();
        password= Password.getText().toString();*/
    }

    protected void onRestart(){
        super.onRestart();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick (View v) {
        String[] infoUser= new String[2];
        infoUser[0]=name;
        infoUser[1]=password;
        String[] infoDB={"ticketing_app","root","root"};
        this.db = new DatabaseManager(infoDB);

        if (v == btnClk)
        {
            name = Name.getText().toString();
            password = Password.getText().toString();

            //test offlline
            User user = new BasicUser("test","test@tes.com","TestID",25,"test",50);

            //online code
           /* String[] info={name,password};
            if(db.ConnectUser(info))
            {
                String[] info_user=db.getInfo(name);
                user = new BasicUser(info_user[0],info_user[5],info_user[3], Integer.parseInt(info_user[2]),info_user[4],Double.parseDouble(info_user[7]));

                SQLiteManager db_local= new SQLiteManager(this);

                if(db_local.Insert(user)== true)
                {
                    //setContentView(R.layout.user_activity);
                    //Toast.makeText(MainActivity.this, user.getForeName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,UserActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Error SQLITE",Toast.LENGTH_SHORT);
                }
            }*/

            SQLiteManager db_local= new SQLiteManager(this);

            if(db_local.Insert(user)== true)
            {
                //setContentView(R.layout.user_activity);
                //Toast.makeText(MainActivity.this, user.getForeName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,UserActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(MainActivity.this,"Error SQLITE",Toast.LENGTH_SHORT);
            }
        }
        else if(v==btnCreate)
        {
            Intent intent = new Intent(this,AddUserActivity.class);
            startActivity(intent);
        }
    }
}
