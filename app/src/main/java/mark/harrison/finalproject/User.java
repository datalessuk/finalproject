package mark.harrison.finalproject;

public class User {
    private String mFirstName;
    private String mUserName;
    private String mEmail;


    public User(){

    }

    public User(String pFirstName,String pLastname,String pEmail){
        this.mFirstName = pFirstName;
        this.mUserName = pLastname;
        this.mEmail = pEmail;

    }



    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }



    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }








}
