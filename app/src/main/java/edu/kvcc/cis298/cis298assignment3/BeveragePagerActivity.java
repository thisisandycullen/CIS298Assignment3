package edu.kvcc.cis298.cis298assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by Andy on 11/23/2015.
 */
public class BeveragePagerActivity extends FragmentActivity {

    //variables
    private ViewPager mViewPager;
    private List<BeverageItem> mBeverageList;
    private static final String EXTRA_BEVERAGE_ID = "edu.kvcc.cis298.cis298assignment3.beverage_id";

    //for intent creation/return
    public static Intent newIntent(Context context, String beverageId) {
        Intent intent = new Intent(context, BeveragePagerActivity.class);
        intent.putExtra(EXTRA_BEVERAGE_ID, beverageId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set content to layout
        setContentView(R.layout.activity_beverage_pager);

        //get string
        String beverageId = getIntent().getStringExtra(EXTRA_BEVERAGE_ID);
        //get view from layout
        mViewPager = (ViewPager) findViewById(R.id.activity_beverage_pager_view_pager);
        //get reference to beverage list (Beverage Cart singleton)
        mBeverageList = BeverageCart.get(this).getBeverageList();
        //create fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            //get specific item from list
            @Override
            public Fragment getItem(int i) {
                BeverageItem beverageItem = mBeverageList.get(i);
                return BeverageFragment.newInstance(beverageItem.getItemID());
            }

            //get number of items in list
            @Override
            public int getCount() {

                return mBeverageList.size();
            }
        });

        //loop through each item in list, check for match
        for (int i = 0; i < mBeverageList.size(); i++) {
            if (mBeverageList.get(i).getItemID().equals(beverageId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
