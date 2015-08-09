package com.co.iatech.crm.sugarmovil.model;

public class DrawerItem {

    String itemName;
    int imgResID;

    public DrawerItem(String itemName, int imgResID) {
        super();
        setItemName(itemName);
        setImgResID(imgResID);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getImgResID() {
        return imgResID;
    }

    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }
}
