package com.example.drivingtest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.drivingtest.question.DBHelper;
import com.example.drivingtest.ui.home.HomeFragment;

import java.io.IOException;

public class  MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.home);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight

                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();


                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );
        //Create  Database
        DBHelper db= new DBHelper(this);
        try {
            db.createDataBase();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //Navigation icon event
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight

                        int id=menuItem.getItemId();
                        if (id==R.id.B1)
                        {
                            B1Fragment b1Fragment =new B1Fragment();
                            FragmentManager manager =getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.content_main, b1Fragment, b1Fragment.getTag()).commit();
                        }
                        else if (id==R.id.B2)
                        {
                            B2Fragment b2Fragment =new B2Fragment();
                            FragmentManager manager =getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.content_main, b2Fragment, b2Fragment.getTag()).commit();
                        }
                        else if (id==R.id.C1)
                        {
                            C1Fragment c1Fragment =new C1Fragment();
                            FragmentManager manager =getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.content_main, c1Fragment, c1Fragment.getTag()).commit();
                        }
                        else if (id==R.id.C2)
                        {
                            C2Fragment c2Fragment =new C2Fragment();
                            FragmentManager manager =getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.content_main, c2Fragment, c2Fragment.getTag()).commit();
                        }
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });


        mDrawerLayout.openDrawer(GravityCompat.START);
        return true;


       // return super.onOptionsItemSelected(item);
    }
}
