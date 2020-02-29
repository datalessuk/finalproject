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

    //public int getmStock() {
        //return mStock;
    //}

    //public void setmStock(int mStock) {
      //  this.mStock = mStock;
    //}

    public String getmBarcode() {
        return mBarcode;
    }

    public void setmBarcode(String mBarcode) {
        this.mBarcode = mBarcode;
    }

    //public long getmReview() {
      //  return mReview;
    //}

   // public void setmReview(long mReview) {
        //this.mReview = mReview;
    //}

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


    private String mName;
    private String mBrewery;
    //private int mStock;
    private String mBarcode;
    //private long mReview;
    private String mFlavours;
    private String mBeerInformation;
    private Bitmap mPhoto;


    public Beers(){
    }

    public Beers(String pName,String pBrewery,String pBarcode){
        this.mName = pName;
        this.mBrewery = pBrewery;
        this.mBarcode = pBarcode;
    }

    //public Beers(String pName,String pBrewery){
        //this.mName = pName;
        //this.mBrewery = pBrewery;
    //}

}
