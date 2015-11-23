package edu.kvcc.cis298.cis298assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Andy on 11/23/2015.
 */

//THE BEVERAGELISTFRAGMENT CLASS CONTAINS A RECYCLERVIEW THAT STORES THE BEVERAGE ITEMS LOCALLY
public class BeverageListFragment extends Fragment {

    //class variables
    private RecyclerView mBeverageRecyclerView;
    private BeverageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate recycler view on create, assign to layout, refresh, and return view
        View view = inflater.inflate(R.layout.fragment_beverage_list, container, false);
        mBeverageRecyclerView = (RecyclerView) view.findViewById(R.id.beverage_recycler_view);
        mBeverageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        refreshScreen();
        return view;
    }

    @Override
    public void onResume() {
        //refreshes when values change
        super.onResume();
        refreshScreen();
    }

    private void refreshScreen() {
        //create a beverage cart and get the item list
        BeverageCart beverageCart = BeverageCart.get(getActivity());
        List<BeverageItem> beverageItemList = beverageCart.getBeverageList();
        //check if new adapter should be created, or notify it that values have changed
        if(mAdapter == null) {
            mAdapter = new BeverageAdapter(beverageItemList);
            mBeverageRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class BeverageItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //this nested class holds each beverage item

        //variables
        private BeverageItem mBeverageItem;
        private TextView mBeverageName;
        private TextView mBeverageID;
        private TextView mBeveragePrice;

        //constructor
        public BeverageItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //assign layout variables to layout items
            mBeverageName = (TextView) itemView.findViewById(R.id.item_description);
            mBeverageID = (TextView) itemView.findViewById(R.id.item_id);
            mBeveragePrice = (TextView) itemView.findViewById(R.id.item_price);
        }

        //sets text on layout
        public void bindBeverageItem(BeverageItem beverageItem) {
            mBeverageItem = beverageItem;
            mBeverageName.setText(mBeverageItem.getItemDescription());
            mBeverageID.setText(mBeverageItem.getItemID());
            DecimalFormat format = new DecimalFormat("0.00");
            mBeveragePrice.setText("$" + format.format(mBeverageItem.getItemPrice()));
        }

        //creates new intent for beveragepager on click
        @Override
        public void onClick(View view) {
            Intent intent = BeveragePagerActivity.newIntent(getActivity(), mBeverageItem.getItemID());
            startActivity(intent);
        }
    }

    private class BeverageAdapter extends RecyclerView.Adapter<BeverageItemHolder> {
        //variables
        private List<BeverageItem> mBeverageItemList;

        //constructor
        public BeverageAdapter(List<BeverageItem> beverageItemList) {

            mBeverageItemList = beverageItemList;
        }

        //create a view for every item
        @Override
        public BeverageItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_beverage, viewGroup, false);
            return new BeverageItemHolder(view);
        }

        //get item's position in list and "bind" the holder to it
        @Override
        public void onBindViewHolder(BeverageItemHolder beverageItemHolder, int i) {
            BeverageItem beverageItem = mBeverageItemList.get(i);
            beverageItemHolder.bindBeverageItem(beverageItem);
        }

        //returns number of items in list
        @Override
        public int getItemCount() {
            return mBeverageItemList.size();
        }
    }
}
