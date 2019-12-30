package com.example.professt.mother_baby_care;

public class Key {

    //main url or address
    //public static final String MAIN_URL = "http://192.168.43.231";
    public static final String MAIN_URL = "http://www.tikabarta.com";

    //access db from device for mother
    public static  final String SIGNUP_URL = MAIN_URL+"/baby_mother/signup.php";
    public static  final String LOGIN_URL = MAIN_URL+"/baby_mother/loginall.php";

    //access db from device for baby
    public static  final String B_SIGNUP_URL = MAIN_URL+"/baby_mother/babysignup.php";
    public static  final String B_LOGIN_URL = MAIN_URL+"/baby_mother/login.php";

    //Keys for server communications for mother
    public static final String KEY_ID = "id";
    public static final String KEY_MNAME = "M_Name";
    public static final String KEY_MEMAIL = "M_Email";
    public static final String KEY_MCELL = "M_Cell";
    public static final String KEY_MUSER = "M_User";
    public static final String KEY_BLOOD = "M_Blood";
    public static final String KEY_WEEK = "M_Week";
    public static final String KEY_PASSWORD = "M_Pass";
    public static final String KEY_DATE = "M_Date";

    public static final String KEY_MUCELL = "cell";
    public static final String KEY_MPDATE = "pdate";
    public static final String KEY_MNDATE = "ndate";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_NUMBERS = "numbers";


    public static final String KEY_CELL = "cell";
    public static final String KEY_PASS = "password";

    public static final String MOTHER_HOME_VIEW_URL = MAIN_URL+"/baby_mother/motherhome.php?cell=";
    public static final String DOCTOR_URL = MAIN_URL+"/baby_mother/doctor.php?cell=";
    public static final String VACCINE = MAIN_URL+"/baby_mother/vaccine.php";
    public static final String REMINDER = MAIN_URL+"/baby_mother/reminder.php?cell=";
    public static final String REMINDERS = MAIN_URL+"/baby_mother/reminders.php?cell=";
    public static final String VACCINATION_URL = MAIN_URL+"/baby_mother/vaccination.php";
    public static final String VACCINATION_UPDATE = MAIN_URL+"/baby_mother/update_mvaccine.php";
    public static final String VACCINATION_CHECK = MAIN_URL+"/baby_mother/vaccinecheck.php?cell=";
    public static final String Mother_VACCINATION_LIST_URL = MAIN_URL+"/baby_mother/mothervaccinationlist.php?cell=";


    //Keys for server communications for baby
    public static final String KEY_BID = "id";
    public static final String KEY_BNAME = "B_Name";
    public static final String KEY_BMEMAIL = "BM_Email";
    public static final String KEY_BUSER = "B_User";
    public static final String KEY_BFCELL = "BF_Cell";
    public static final String KEY_BPASSWORD = "B_Pass";
    public static final String KEY_BDAY = "B_BDay";
    public static final String KEY_BGENDER = "B_Gender";

    public static final String BABY_HOME_VIEW_URL = MAIN_URL+"/baby_mother/babyhome.php?cell=";
    public static final String BABY_VACCINATION_CHECK = MAIN_URL+"/baby_mother/babyvaccinecheck.php?cell=";
    public static final String BABY_VACCINATION_UPDATE = MAIN_URL+"/baby_mother/update_bvaccine.php";
    public static final String BABY_REMINDER = MAIN_URL+"/baby_mother/babyreminder.php?cell=";
    public static final String BABY_VACCINATION_URL = MAIN_URL+"/baby_mother/babyvaccination.php";
    public static final String BABY_VACCINATION_LIST_URL = MAIN_URL+"/baby_mother/babyvaccinationlist.php?cell=";


    //share preference
    //We will use this to store the user cell number into shared preference
    public static final String SHARED_PREF_NAME = "com.example.professt.mother_baby_care.userlogin"; //pcakage name+ id(any name)

    //This would be used to store the cell of current logged in user
    public static final String CELL_SHARED_PREF = "cell";

    //json array name.We will received data in this array
    public static final String JSON_ARRAY = "result";
}
