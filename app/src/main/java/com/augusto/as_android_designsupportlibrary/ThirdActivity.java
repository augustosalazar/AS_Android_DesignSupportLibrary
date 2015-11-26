package com.augusto.as_android_designsupportlibrary;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class ThirdActivity extends AppCompatActivity {

    private FloatingActionButton mFAB;
    private RelativeLayout mRoot;

    private View.OnClickListener mFabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Snackbar.make(mRoot, "FAB Clicked", Snackbar.LENGTH_SHORT)
                    .show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mRoot = (RelativeLayout) findViewById(R.id.root_activity_third);

        mFAB = (FloatingActionButton) findViewById(R.id.fab);
        mFAB.setOnClickListener(mFabClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_third, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
