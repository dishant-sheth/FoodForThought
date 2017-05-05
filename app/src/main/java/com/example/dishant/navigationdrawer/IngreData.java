package com.example.dishant.navigationdrawer;

/**
 * Created by dishant on 21/3/17.
 */

public class IngreData {

    public String ingreName;
    public String ingreImage;
    public String secondaryText;
    public int mId;
    public boolean mChecked;
    public boolean mActivateExpansion;

    public boolean ismActivateExpansion() {
        return mActivateExpansion;
    }

    public void setmActivateExpansion(boolean mActivateExpansion) {
        this.mActivateExpansion = mActivateExpansion;
    }

    public String getIngreName() {
        return ingreName;
    }

    public void setIngreName(String ingreName) {
        this.ingreName = ingreName;
    }

    public String getIngreImage() {
        return ingreImage;
    }

    public void setIngreImage(String ingreImage) {
        this.ingreImage = ingreImage;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public boolean ismChecked() {
        return mChecked;
    }

    public void setmChecked(boolean mChecked) {
        this.mChecked = mChecked;
    }



}
