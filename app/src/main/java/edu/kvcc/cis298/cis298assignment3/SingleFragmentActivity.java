package edu.kvcc.cis298.cis298assignment3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Andy on 11/23/2015.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //represents support version of fragment manager
        FragmentManager fm = getSupportFragmentManager();
        //get fragment currently in created frame
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //create fragment if null
        if(fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
