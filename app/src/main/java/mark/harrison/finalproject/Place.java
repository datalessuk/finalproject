package mark.harrison.finalproject;

import java.io.Serializable;

public class Place {
    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmReference() {
        return mReference;
    }

    public void setmReference(String mReference) {
        this.mReference = mReference;
    }

    public String getmVicinity() {
        return mVicinity;
    }

    public void setmVicinity(String mVicinity) {
        this.mVicinity = mVicinity;
    }

    private String mId;
    private String mName;
    private String mReference;
    private String mVicinity;

    public Place(){

    }
    public Place(String pName,String pVicinity){
        this.mName = pName;
        this.mVicinity =pVicinity;
    }

    public Place(String pId,String pName,String pReference,String pVicinity){
        this.mId = pId;
        this.mName =pName;
        this.mReference = pReference;
        this.mVicinity = pVicinity;
    }

}
