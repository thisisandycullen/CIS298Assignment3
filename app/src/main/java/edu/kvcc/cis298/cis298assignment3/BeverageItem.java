package edu.kvcc.cis298.cis298assignment3;

//THE BEVERAGE ITEM CLASS CONTAINS THE PROPERTIES FOR A BEVERAGE ITEM
public class BeverageItem {

    //variables
    private String mItemID;
    private String mItemDescription;
    private String mItemPackSize;
    private Double mItemCasePrice;
    private Boolean mActive;

    //constructor
    public BeverageItem(String id, String description, String packSize, Double price, Boolean active) {
        mItemID = id;
        mItemDescription = description;
        mItemPackSize = packSize;
        mItemCasePrice = price;
        mActive = active;
    }

    //getters/setters
    public String getItemID() {

        return mItemID;
    }

    public String getItemDescription() {

        return mItemDescription;
    }

    public String getItemPackSize() {

        return mItemPackSize;
    }

    public Double getItemPrice() {

        return mItemCasePrice;
    }

    public Boolean getActive() {

        return mActive;
    }

    public void setActive(Boolean active) {

        mActive = active;
    }
}