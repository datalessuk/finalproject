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

    public long getmBarcode() {
        return mBarcode;
    }

    public void setmBarcode(long mBarcode) {
        this.mBarcode = mBarcode;
    }

    public long getmReview() {
        return mReview;
    }

    public void setmReview(long mReview) {
        this.mReview = mReview;
    }

    public String getmFlavours() {
        return mFlavours;
    }

    public void setmFlavours(String mFlavours) {
        this.mFlavours = mFlavours;
    }

    public String getmBeerInformation() {
        return mBeerInformation;
    }

    public void setmBeerInformation(String mBeerInformation) {
        this.mBeerInformation = mBeerInformation;
    }

    public Bitmap getmPhoto() {
        return mPhoto;
    }

    public void setmPhoto(Bitmap mPhoto) {
        this.mPhoto = mPhoto;
    }

    public short getmPercentage() {
        return mPercentage;
    }

    public void setmPercentage(short mPercentage) {
        this.mPercentage = mPercentage;
    }

    private String mName;
    private String mBrewery;
    private int mStock;
    private long mBarcode;
    private long mReview;
    private String mFlavours;
    private String mBeerInformation;
    private Bitmap mPhoto;
    private short mPercentage;

    public Beers(){
    }



}
