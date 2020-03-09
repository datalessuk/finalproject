package mark.harrison.finalproject;

import android.graphics.Bitmap;

public class Beers {

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmBrewery() {
        return mBrewery;
    }

    public void setmBrewery(String mBrewery) {
        this.mBrewery = mBrewery;
    }

    public int getmStock() {
        return mStock;
    }

    public void setmStock(int mStock) {
        this.mStock = mStock;
    }

    public String getmBarcode() {
        return mBarcode;
    }

    public void setmBarcode(String mBarcode) {
        this.mBarcode = mBarcode;
    }



    public String getmFlavours() {
        return mFlavours;
    }

    public void setmFlavours(String mFlavours) {
        this.mFlavours = mFlavours;
    }

    public String getmPercentage() {
        return mPercentage;
    }

    public void setmPercentage(String mPercentage) {
        this.mPercentage = mPercentage;
    }


    private String mName;
    private String mBrewery;
    private int mStock;
    private String mBarcode;
    private String mReview;
    private String mFlavours;



    private String mPercentage;

    public Beers(){

    }


    //Test Contructor
    public Beers(String pName,String pBrewery,String pBarcode){
        this.mName = pName;
        this.mBrewery = pBrewery;
        this.mBarcode = pBarcode;
    }


    public Beers(String pName,String pBrewery,String pBarcode,int pStock,String pFlavours,String pPercentage){
        this.mName = pName;
        this.mBrewery = pBrewery;
        this.mBarcode = pBarcode;
        this.mStock = pStock;
        this.mFlavours = pFlavours;
        this.mPercentage = pPercentage;
    }






}
