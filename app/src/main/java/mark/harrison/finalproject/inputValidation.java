package mark.harrison.finalproject;

import android.util.Patterns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class inputValidation {

    private static inputValidation sInstance;



    public static inputValidation getInstance(){
        if(sInstance == null){
            sInstance = new inputValidation();
        }
    return sInstance;
    }




    //Method to validate the email
    public boolean emailCheck(String pEmail){

        Pattern pattern = Patterns.EMAIL_ADDRESS;

        return pattern.matcher(pEmail).matches();

    }

    //Validate the Password

    public boolean passwordChecker(String pPassWord){
        Pattern special = Pattern.compile ("[!@#$£%&*()_+=|<>?{}\\[\\]~-]");
        if(pPassWord.length() >6){
            Matcher hasSpecial = special.matcher(pPassWord);

            return hasSpecial.find();
        }
        else {
            return false;
        }

    }

    public int calculateAge(String pDOBString){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(pDOBString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }


        return age;
    }

    public boolean whiteSpaceCheck(String pInput){
        if(pInput.trim().isEmpty()){
            return false;
        }
        else {
            
            return true;
        }
    }

}
