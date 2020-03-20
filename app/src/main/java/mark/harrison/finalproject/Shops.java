package mark.harrison.finalproject;

public class Shops {

    public String getmShopName() {
        return mShopName;
    }

    public void setmShopName(String mShopName) {
        this.mShopName = mShopName;
    }

    public String getmBeerName() {
        return mBeerName;
    }

    public void setmBeerName(String mBeerName) {
        this.mBeerName = mBeerName;
    }

    public Boolean getmInStock() {
        return mInStock;
    }

    public void setmInStock(Boolean mInStock) {
        this.mInStock = mInStock;
    }

    private String mShopName;
    private String mBeerName;
    private Boolean mInStock;

    public  Shops()
    {

    }
    public Shops(String pShopName){
        this.mShopName = pShopName;
    }
    public Shops(String pShopname,String pBeerName,Boolean pInStock){
        this.mShopName = pShopname;
        this.mBeerName = pShopname;
        this.mInStock = pInStock;
    }

    public Shops(String pBeerName,Boolean pInStock){
        this.mBeerName = pBeerName;
        this.mInStock = pInStock;
    }


}
