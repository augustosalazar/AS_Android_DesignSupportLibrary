package com.augusto.as_android_designsupportlibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static final java.lang.String SELECTED_ITEM_ID = "SELECTED_ITEM_ID";
    private static final String FIRST_TIME = "FIRST_TIME";
    Toolbar mToolbar;
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mdrawerToggle;
    int mSelectedId;
    private boolean mUserSawDrawer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        mNavigationView = (NavigationView) findViewById(R.id.main_drawer);
        mNavigationView.setNavigationItemSelectedListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mdrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);

        mDrawerLayout.setDrawerListener(mdrawerToggle);
        mdrawerToggle.syncState();

        if (!didUserSawDrawer()) {
            mDrawerLayout.openDrawer(GravityCompat.START);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            mUserSawDrawer = true;
            sharedPreferences.edit().putBoolean(FIRST_TIME, mUserSawDrawer).commit();
        } else {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }

        mSelectedId = savedInstanceState == null ? R.id.navigation_item_1 : savedInstanceState.getInt(SELECTED_ITEM_ID);

        navigate(mSelectedId);
    }

    private boolean didUserSawDrawer() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserSawDrawer = sharedPreferences.getBoolean(FIRST_TIME, false);
        return mUserSawDrawer;
    }

    private void navigate(int selected) {
        Intent intent = null;

        if (selected == R.id.navigation_item_2) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }

        if (selected == R.id.navigation_item_3) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
        }

        if (selected == R.id.navigation_item_5 || selected == R.id.navigation_item_6) {
            Fragment fragment = null;
            Class fragmentClass = null;

            if (selected == R.id.navigation_item_5)
                fragmentClass = Fragment1.class;

            if (selected == R.id.navigation_item_6)
                fragmentClass = Fragment2.class;

            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            mDrawerLayout.closeDrawers();


        }


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mdrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        setTitle(menuItem.getTitle());
        navigate(mSelectedId);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(SELECTED_ITEM_ID, mSelectedId);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            onBackPressed();
        }
    }
}
