package edu.kvcc.cis298.cis298assignment3;

import android.support.v4.app.Fragment;

/**
 * Created by Andy on 11/23/2015.
 */

//THE BEVERAGELISTACTIVITY CLASS INHERITS FROM SINGLEFRAGMENTACTIVITY
public class BeverageListActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        //returns a new beverage list fragment
        return new BeverageListFragment();
    }
}
