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

    public boolean getmStock() {
        return mStock;
    }

    public void setmStock(boolean mStock) {
        this.mStock = mStock;
    }

    public String getmBarcode() {
        return mBarcode;
    }

    public void setmBarcode(String mBarcode) {
        this.mBarcode = mBarcode;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
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
    private boolean mStock;
    private String mBarcode;
    private String mReview;
    private String mFlavours;



    private String mRating;
    private String mPercentage;

    public String getmReview() {
        return mReview;
    }

    public void setmReview(String mReview) {
        this.mReview = mReview;
    }



    public Beers(){

    }


    //Test Contructor
    public Beers(String pName,String pBrewery,String pBarcode){
        this.mName = pName;
        this.mBrewery = pBrewery;
        this.mBarcode = pBarcode;
    }


    public Beers(String pName,String pBrewery,String pBarcode,boolean pStock,String pFlavours,String pPercentage,String pRating){
        this.mName = pName;
        this.mBrewery = pBrewery;
        this.mBarcode = pBarcode;
        this.mStock = pStock;
        this.mFlavours = pFlavours;
        this.mPercentage = pPercentage;
        this.mRating = pRating;
    }


    public Beers(String pRaing){
        this.mRating = pRaing;
    }



}
