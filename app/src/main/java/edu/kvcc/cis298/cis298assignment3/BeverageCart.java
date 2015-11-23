package edu.kvcc.cis298.cis298assignment3;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Andy on 11/23/2015.
 */

//THE BEVERAGECART CLASS IS A SINGLETON. IT CREATES AND HOLDS THE LIST OF BEVERAGE ITEMS FROM THE CSV FILE.
public class BeverageCart {

    //class variables
    private static BeverageCart sBeverageCart;
    private List<BeverageItem> mBeverageList;

    //constructor that ensures only one instance of BeverageCart exists
    public static BeverageCart get(Context context) {
        if(sBeverageCart == null) {
            sBeverageCart = new BeverageCart(context);
        }
        return sBeverageCart;
    }

    //constructor that populates the beverage list from the csv file
    private BeverageCart(Context context) {
        mBeverageList = new ArrayList<>();
        populateBeverageList(context);
    }

    //returns a beverage item when searched by ID
    public BeverageItem getBeverageItem(String id) {
        for(BeverageItem beverageItem : mBeverageList) {
            if(beverageItem.getItemID().equals(id)) {
                return beverageItem;
            }
        }
        return null;
    }

    //returns the beverage list
    public List<BeverageItem> getBeverageList() {
        return mBeverageList;
    }


    private void populateBeverageList(Context context) {
        Scanner scanner = null;

        try {

            scanner = new Scanner(context.getResources().openRawResource(R.raw.beverage_list));

            //loops through each line of the csv file
            while(scanner.hasNextLine()) {
                //splits data at commas
                String line = scanner.nextLine();
                String parts[] = line.split(",");
                //assigns properties to item in value order
                String itemID = parts[0];
                String itemDescription = parts[1];
                String itemPack = parts[2];
                String itemPrice = parts[3];
                String itemActive = parts[4];
                //determine whether item is active based on value
                boolean isActive;
                if (itemActive.equals("True")) {
                    isActive = true;
                } else {
                    isActive = false;
                }

                //finally, add the item to the list
                mBeverageList.add(new BeverageItem(itemID, itemDescription, itemPack, Double.parseDouble(itemPrice), isActive));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //close the file
            scanner.close();
        }
    }
}
