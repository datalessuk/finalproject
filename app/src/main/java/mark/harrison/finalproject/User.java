package mark.harrison.finalproject;

public class User {
    private String mFirstName;
    private String mLastName;
    private String mEmail;


    public User(){

    }

    public User(String pFirstName,String pLastname,String pEmail){
        this.mFirstName = pFirstName;
        this.mLastName = pLastname;
        this.mEmail = pEmail;

    }



    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }



    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }








}
