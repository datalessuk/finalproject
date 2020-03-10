package mark.harrison.finalproject;

public class beersRatings {

    public float getmBeerRatings() {
        return mBeerRatings;
    }

    public void setmBeerRatings(float mBeerRatings) {
        this.mBeerRatings = mBeerRatings;
    }

    private float mBeerRatings;

    beersRatings(){

    }
    beersRatings(float pRating){
        this.mBeerRatings = pRating;
    }
}
