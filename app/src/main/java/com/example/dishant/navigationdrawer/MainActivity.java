package com.example.dishant.navigationdrawer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    FrameLayout frameLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        frameLayout = (FrameLayout) findViewById(R.id.fragmentContainer);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Home home = new Home();
        fragmentTransaction.replace(R.id.fragmentContainer, home);
        fragmentTransaction.commit();


        DrawerFragment drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.drawerFragment);
        drawerFragment.setup(R.id.drawerFragment, (DrawerLayout)findViewById(R.id.drawerLayout), toolbar);


    }

    public void switchContent(int id, Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.action_settings){
            Toast.makeText(getApplicationContext(), "Havent configured that crap yet", Toast.LENGTH_LONG).show();
        }

        else if(id == R.id.navigate){
            startActivity(new Intent(MainActivity.this, Second.class));
        }




        return super.onOptionsItemSelected(item);
    }


}
