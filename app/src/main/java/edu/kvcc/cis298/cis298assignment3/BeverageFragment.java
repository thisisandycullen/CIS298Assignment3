package edu.kvcc.cis298.cis298assignment3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Andy on 11/23/2015.
 */

//THE BEVERAGE FRAGMENT CLASS DISPLAYS A SINGLE BEVERAGE ITEM
public class BeverageFragment extends Fragment{

    //class variables
    private static final String BEVERAGE_ID = "beverage_id";
    private BeverageItem mBeverageItem;

    //layout variables
    private TextView mBeverageDescription;
    private TextView mBeverageID;
    private EditText mBeveragePackSize;
    private EditText mBeveragePrice;
    private CheckBox mBeverageActiveCheckBox;

    //fragment constructor
    public static BeverageFragment newInstance(String beverageId) {
        //create a bundle to hold arguments, set them to the fragment and return it
        Bundle args = new Bundle();
        args.putString(BEVERAGE_ID, beverageId);
        BeverageFragment fragment = new BeverageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //gets arguments sent by intent and creates a new bev item
        String beverageId = getArguments().getString(BEVERAGE_ID);
        mBeverageItem = BeverageCart.get(getActivity()).getBeverageItem(beverageId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //create view with inflater
        View view = inflater.inflate(R.layout.fragment_beverage, container, false);

        //assign layout variables to the layout items
        mBeverageDescription = (TextView) view.findViewById(R.id.beverage_description);
        mBeverageID = (TextView) view.findViewById(R.id.beverage_id);
        mBeveragePackSize = (EditText) view.findViewById(R.id.beverage_pack_size);
        mBeveragePrice = (EditText) view.findViewById(R.id.beverage_item_price);
        mBeverageActiveCheckBox = (CheckBox) view.findViewById(R.id.beverage_active_check_box);

        //set actual layout text to item's properties
        mBeverageDescription.setText(mBeverageItem.getItemDescription());
        mBeverageID.setText(mBeverageItem.getItemID());
        mBeveragePackSize.setText(mBeverageItem.getItemPackSize());
        DecimalFormat format = new DecimalFormat("0.00");
        mBeveragePrice.setText("$" + format.format(mBeverageItem.getItemPrice()));
        mBeverageActiveCheckBox.setChecked(mBeverageItem.getActive());
        mBeverageActiveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mBeverageItem.setActive(b);
            }
        });

        //returns the view
        return view;
    }
}

