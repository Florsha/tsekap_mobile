package com.example.mvopo.tsekapp.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.mvopo.tsekapp.MainActivity;
import com.example.mvopo.tsekapp.Model.AffiliatedFacilitiesModel;
import com.example.mvopo.tsekapp.Model.FamilyProfile;
import com.example.mvopo.tsekapp.Model.FeedBack;
import com.example.mvopo.tsekapp.Model.ServicesStatus;
import com.example.mvopo.tsekapp.Model.ServiceAvailed;
import com.example.mvopo.tsekapp.Model.SpecialistModel;
import com.example.mvopo.tsekapp.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    final static String DBNAME = "db_tsekap";
    final static String USERS = "tbl_user";
    final static String SERVICES = "tbl_services";
    final static String SERVICESTATUS = "tbl_must_services";
    final static String PROFILES = "tbl_profile";
    final static String FEEDBACK = "tbl_feedback";
    final static String CHPHS = "tbl_chphs";
    final static String CLUSTER = "tbl_cluster";
    final static String DISTRICT = "tbl_district";

    final static String SPECIALIST = "tbl_specialist";
    final static String FACILITY = "tbl_facility";
    final static String FACILITY_ASSIGNMENT = "tbl_facility_assignment";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 6);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table " + USERS + " (id integer, fname varchar(50), mname varchar(50), lname varchar(50)," +
                " muncity varchar(50), contact varchar(50), barangay varchar(255), target varchar(100), image varchar(50))";

        String sql1 = "Create table " + PROFILES + " " +
                "(id integer, " +
                "uniqueId varchar(100), " +
                "familyId varchar(50), " +
                "philId varchar(50), " +
                "nhtsId varchar(50), " +
                "isHead varchar(50), " +
                "relation varchar(50), " +
                "fname varchar(50), " +
                "mname varchar(50), " +
                "lname varchar(50), " +
                "suffix varchar(50), " +
                "dob varchar(50), " +
                "sex varchar(50), " +
                "barangayId varchar(50), " +
                "muncityId varchar(50),  " +
                "provinceId varchar(50), " +
                "income varchar(50), " +
                "unmetNeed varchar(50), " +
                "waterSupply varchar(50), " +
                "sanitaryToilet varchar(50), " +
                "educationalAttainment varchar(50)," +
                "status varchar(3)," +
                "diabetic varchar(30)," +
                "hypertension varchar(30)," +
                "pwd varchar(5)," +
                "pregnant varchar(15)," +

                "birth_place varchar(15)," +
                "civil_status varchar(15)," +
                "religion varchar(30)," +
                "other_religion varchar(30)," +
                "contact varchar(15)," +
                "height varchar(10)," +
                "weight varchar(10)," +
                "cancer varchar(5)," +
                "cancer_type varchar(100)," +
                "mental_med varchar(30)," +
                "tbdots_med varchar(30)," +
                "cvd_med varchar(30)," +
                "covid_status varchar(30)," +
                "menarche varchar(30)," +
                "menarche_age varchar(30)," +
                "newborn_screen varchar(5)," +
                "newborn_text varchar(50)," +
                "deceased varchar(5)," +
                "deceased_date varchar(50)," +
                "immu_stat varchar(100)," +
                "nutri_stat varchar(100)," +
                "pwd_desc varchar(100)," +
                "sexually_active varchar(5))";

        String sql2 = "Create table " + SERVICES + " (id integer primary key autoincrement, request TEXT)";

        String sql3 = "Create table " + SERVICESTATUS + " (id integer primary key autoincrement, name varchar(100), group1 varchar(2), group2 varchar(2)," +
                " group3 varchar(2), barangayId varchar(10))";

        String sql4 = "Create table " + FEEDBACK + " (id integer primary key autoincrement, subject varchar(25), body varchar(255))";

        String sql5 = "Create table " + SPECIALIST + " " +
                "(id integer  primary key autoincrement, " +
                "username varchar(100), " +
                "fname varchar(100), " +
                "mname varchar(100), " +
                "lname varchar(100))";

        String sql6 = "Create table " + FACILITY_ASSIGNMENT + " " +
                "(id integer  primary key autoincrement, " +
                "username varchar(100), " +
                "facility_id varchar(50), " +
                "specialization varchar(100), " +
                "contact varchar(100), " +
                "email varchar(100), " +
                "schedule varchar(100), " +
                "fee varchar(100))";

        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql = "Create table IF NOT EXISTS " + SERVICESTATUS + " (id integer primary key autoincrement, name varchar(100), group1 varchar(2), group2 varchar(2)," +
//                " group3 varchar(2), barangayId varchar(10))";
//
//        String sql1 = "Create table IF NOT EXISTS " + FEEDBACK + " (id integer primary key autoincrement, subject varchar(25), body varchar(255))";

//        String sql2 = "ALTER TABLE "+ USERS +" ADD image varchar(50)";
        String sql = "Create table IF NOT EXISTS " + CHPHS + " (id integer primary key autoincrement, cluster varchar(5), district varchar(5), houseNo varchar(50)," +
                " street varchar(100), sitio varchar(50), purok varchar(50), bloodType varchar(5), weight varchar(10), height varchar(10), contact varchar(50))";

        String sql1 = "Create table IF NOT EXISTS " + CLUSTER + " (id integer primary key autoincrement, description varchar(100))";

        String sql2 = "Create table IF NOT EXISTS " + DISTRICT + " (id integer primary key autoincrement, description varchar(100))";

        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", user.id);
        cv.put("fname", user.fname);
        cv.put("mname", user.mname);
        cv.put("lname", user.lname);
        cv.put("muncity", user.muncity);
        cv.put("contact", user.contact);
        cv.put("barangay", user.barangay);
        cv.put("target", user.target);
        cv.put("image", user.image);
        db.insert(USERS, null, cv);
        db.close();
    }

    public void deleteUser() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(USERS, null, null);
    }

    public User getUser() {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "Select * from " + USERS;
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            String id = c.getString(c.getColumnIndex("id"));
            String fname = c.getString(c.getColumnIndex("fname"));
            String mname = c.getString(c.getColumnIndex("mname"));
            String lname = c.getString(c.getColumnIndex("lname"));
            String muncity = c.getString(c.getColumnIndex("muncity"));
            String contact = c.getString(c.getColumnIndex("contact"));
            String barangay = c.getString(c.getColumnIndex("barangay"));
            String target = c.getString(c.getColumnIndex("target"));
            String image = c.getString(c.getColumnIndex("image"));

            user = new User(id, fname, mname, lname, muncity, contact, barangay, target, image);
        }
        c.close();
        return user;
    }

    public void addProfile(FamilyProfile familyProfile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", familyProfile.id);
        cv.put("uniqueId", familyProfile.uniqueId);
        cv.put("familyId", familyProfile.familyId);
        cv.put("philId", familyProfile.philId);
        cv.put("nhtsId", familyProfile.nhtsId);
        cv.put("isHead", familyProfile.isHead);
        cv.put("relation", familyProfile.relation);
        cv.put("fname", familyProfile.fname);
        cv.put("mname", familyProfile.mname);
        cv.put("lname", familyProfile.lname);
        cv.put("suffix", familyProfile.suffix);
        cv.put("dob", familyProfile.dob);
        cv.put("sex", familyProfile.sex);
        cv.put("barangayId", familyProfile.barangayId);
        cv.put("muncityId", familyProfile.muncityId);
        cv.put("provinceId", familyProfile.provinceId);
        cv.put("income", familyProfile.income);
        cv.put("unmetNeed", familyProfile.unmetNeed);
        cv.put("waterSupply", familyProfile.waterSupply);
        cv.put("sanitaryToilet", familyProfile.sanitaryToilet);
        cv.put("educationalAttainment", familyProfile.educationalAttainment);
        cv.put("status", familyProfile.status);
        cv.put("diabetic", familyProfile.diabetic);
        cv.put("hypertension", familyProfile.hypertension);
        cv.put("pwd", familyProfile.pwd);
        cv.put("pregnant", familyProfile.pregnant);

        cv.put("birth_place", familyProfile.birth_place);
        cv.put("civil_status", familyProfile.civil_status);
        cv.put("religion", familyProfile.religion);
        cv.put("other_religion", familyProfile.other_religion);
        cv.put("contact", familyProfile.contact);
        cv.put("height", familyProfile.height);
        cv.put("weight", familyProfile.weight);
        cv.put("cancer", familyProfile.cancer);
        cv.put("cancer_type", familyProfile.cancer_type);
        cv.put("mental_med", familyProfile.mental_med);
        cv.put("tbdots_med", familyProfile.tbdots_med);
        cv.put("cvd_med", familyProfile.cvd_med);
        cv.put("covid_status", familyProfile.covid_status);
        cv.put("menarche", familyProfile.menarche);
        cv.put("menarche_age", familyProfile.menarche_age);
        cv.put("newborn_screen", familyProfile.newborn_screen);
        cv.put("newborn_text", familyProfile.newborn_text);
        cv.put("deceased", familyProfile.deceased);
        cv.put("deceased_date", familyProfile.deceased_date);
        cv.put("immu_stat", familyProfile.immu_stat);
        cv.put("nutri_stat", familyProfile.nutri_stat);
        cv.put("pwd_desc", familyProfile.pwd_desc);
        cv.put("sexually_active", familyProfile.sexually_active);
        db.insertWithOnConflict(PROFILES, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
    }

    public void updateProfile(FamilyProfile familyProfile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", familyProfile.id);
        cv.put("uniqueId", familyProfile.uniqueId);
        cv.put("familyId", familyProfile.familyId);
        cv.put("philId", familyProfile.philId);
        cv.put("nhtsId", familyProfile.nhtsId);
        cv.put("isHead", familyProfile.isHead);
        cv.put("relation", familyProfile.relation);
        cv.put("fname", familyProfile.fname);
        cv.put("mname", familyProfile.mname);
        cv.put("lname", familyProfile.lname);
        cv.put("suffix", familyProfile.suffix);
        cv.put("dob", familyProfile.dob);
        cv.put("sex", familyProfile.sex);
        cv.put("barangayId", familyProfile.barangayId);
        cv.put("muncityId", familyProfile.muncityId);
        cv.put("provinceId", familyProfile.provinceId);
        cv.put("income", familyProfile.income);
        cv.put("unmetNeed", familyProfile.unmetNeed);
        cv.put("waterSupply", familyProfile.waterSupply);
        cv.put("sanitaryToilet", familyProfile.sanitaryToilet);
        cv.put("educationalAttainment", familyProfile.educationalAttainment);
        cv.put("status", familyProfile.status);
        cv.put("diabetic", familyProfile.diabetic);
        cv.put("hypertension", familyProfile.hypertension);
        cv.put("pwd", familyProfile.pwd);
        cv.put("pregnant", familyProfile.pregnant);

        cv.put("birth_place", familyProfile.birth_place);
        cv.put("civil_status", familyProfile.civil_status);
        cv.put("religion", familyProfile.religion);
        cv.put("other_religion", familyProfile.other_religion);
        cv.put("contact", familyProfile.contact);
        cv.put("height", familyProfile.height);
        cv.put("weight", familyProfile.weight);
        cv.put("cancer", familyProfile.cancer);
        cv.put("cancer_type", familyProfile.cancer_type);
        cv.put("mental_med", familyProfile.mental_med);
        cv.put("tbdots_med", familyProfile.tbdots_med);
        cv.put("cvd_med", familyProfile.cvd_med);
        cv.put("covid_status", familyProfile.covid_status);
        cv.put("menarche", familyProfile.menarche);
        cv.put("menarche_age", familyProfile.menarche_age);
        cv.put("newborn_screen", familyProfile.newborn_screen);
        cv.put("newborn_text", familyProfile.newborn_text);
        cv.put("deceased", familyProfile.deceased);
        cv.put("deceased_date", familyProfile.deceased_date);
        cv.put("immu_stat", familyProfile.immu_stat);
        cv.put("nutri_stat", familyProfile.nutri_stat);
        cv.put("pwd_desc", familyProfile.pwd_desc);
        cv.put("sexually_active", familyProfile.sexually_active);


        db.update(PROFILES, cv, "uniqueId=?", new String[]{familyProfile.uniqueId});
        db.close();
    }

    public ArrayList<FamilyProfile> getFamilyProfiles(String name) {
        name += "%";
        ArrayList<FamilyProfile> profiles = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(PROFILES, null, "fname LIKE ? or mname LIKE ? or lname LIKE ? or familyId LIKE ?", new String[]{name, name, name, name}, null, null, null, "20");

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String uniqueId = c.getString(c.getColumnIndex("uniqueId"));
                String familyId = c.getString(c.getColumnIndex("familyId"));
                String philId = c.getString(c.getColumnIndex("philId"));
                String nhtsId = c.getString(c.getColumnIndex("nhtsId"));
                String isHead = c.getString(c.getColumnIndex("isHead"));
                String relation = c.getString(c.getColumnIndex("relation"));
                String fname = c.getString(c.getColumnIndex("fname"));
                String mname = c.getString(c.getColumnIndex("mname"));
                String lname = c.getString(c.getColumnIndex("lname"));
                String suffix = c.getString(c.getColumnIndex("suffix"));
                String dob = c.getString(c.getColumnIndex("dob"));
                String sex = c.getString(c.getColumnIndex("sex"));
                String barangayId = c.getString(c.getColumnIndex("barangayId"));
                String muncityId = c.getString(c.getColumnIndex("muncityId"));
                String provinceId = c.getString(c.getColumnIndex("provinceId"));
                String income = c.getString(c.getColumnIndex("income"));
                String unmetNeed = c.getString(c.getColumnIndex("unmetNeed"));
                String waterSupply = c.getString(c.getColumnIndex("waterSupply"));
                String sanitaryToilet = c.getString(c.getColumnIndex("sanitaryToilet"));
                String educationalAttainment = c.getString(c.getColumnIndex("educationalAttainment"));
                String status = c.getString(c.getColumnIndex("status"));
                String diabetic = c.getString(c.getColumnIndex("diabetic"));
                String hypertension = c.getString(c.getColumnIndex("hypertension"));
                String pwd = c.getString(c.getColumnIndex("pwd"));
                String pregnant = c.getString(c.getColumnIndex("pregnant"));

                String birth_place = c.getString(c.getColumnIndex("birth_place"));
                String civil_status = c.getString(c.getColumnIndex("civil_status"));
                String religion = c.getString(c.getColumnIndex("religion"));
                String other_religion = c.getString(c.getColumnIndex("other_religion"));
                String contact = c.getString(c.getColumnIndex("contact"));
                String height = c.getString(c.getColumnIndex("height"));
                String weight = c.getString(c.getColumnIndex("weight"));
                String cancer = c.getString(c.getColumnIndex("cancer"));
                String cancer_type = c.getString(c.getColumnIndex("cancer_type"));
                String mental_med = c.getString(c.getColumnIndex("mental_med"));
                String tbdots_med = c.getString(c.getColumnIndex("tbdots_med"));
                String cvd_med = c.getString(c.getColumnIndex("cvd_med"));
                String covid_status = c.getString(c.getColumnIndex("covid_status"));
                String menarche = c.getString(c.getColumnIndex("menarche"));
                String menarche_age = c.getString(c.getColumnIndex("menarche_age"));
                String newborn_screen = c.getString(c.getColumnIndex("newborn_screen"));
                String newborn_text = c.getString(c.getColumnIndex("newborn_text"));
                String deceased = c.getString(c.getColumnIndex("deceased"));
                String deceased_date = c.getString(c.getColumnIndex("deceased_date"));
                String immu_stat = c.getString(c.getColumnIndex("immu_stat"));
                String nutri_stat = c.getString(c.getColumnIndex("nutri_stat"));
                String pwd_desc = c.getString(c.getColumnIndex("pwd_desc"));
                String sexually_active = c.getString(c.getColumnIndex("sexually_active"));

                FamilyProfile profile = new FamilyProfile(
                        id + "", /**Hello am here*/
                        uniqueId,
                        familyId,
                        philId,
                        nhtsId,
                        isHead,
                        relation,
                        fname,
                        lname,
                        mname,
                        suffix,
                        dob,
                        sex,
                        barangayId,
                        muncityId,
                        provinceId,
                        income,
                        unmetNeed,
                        waterSupply,
                        sanitaryToilet,
                        educationalAttainment,
                        status,
                        diabetic,
                        hypertension,
                        pwd,
                        pregnant,
                        birth_place, civil_status, religion, other_religion, contact, height, weight, cancer, cancer_type, mental_med,
                        tbdots_med, cvd_med, covid_status, menarche, menarche_age, newborn_screen, newborn_text, deceased, deceased_date,
                        immu_stat, nutri_stat, pwd_desc, sexually_active);

                if(familyId.equals(name.substring(0, name.length()-1)) && relation.equalsIgnoreCase("Head")) profiles.add(0, profile);
                else profiles.add(profile);

                c.moveToNext();
            }
            c.close();
        }
        db.close();
        return profiles;
    }

    public FamilyProfile getProfileForSync() {
        FamilyProfile familyProfile = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(PROFILES, null, "status = 1", null, null, null, null, "20");

        if (c.moveToFirst()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String uniqueId = c.getString(c.getColumnIndex("uniqueId"));
            String familyId = c.getString(c.getColumnIndex("familyId"));
            String philId = c.getString(c.getColumnIndex("philId"));
            String nhtsId = c.getString(c.getColumnIndex("nhtsId"));
            String isHead = c.getString(c.getColumnIndex("isHead"));
            String relation = c.getString(c.getColumnIndex("relation"));
            String fname = c.getString(c.getColumnIndex("fname"));
            String mname = c.getString(c.getColumnIndex("mname"));
            String lname = c.getString(c.getColumnIndex("lname"));
            String suffix = c.getString(c.getColumnIndex("suffix"));
            String dob = c.getString(c.getColumnIndex("dob"));
            String sex = c.getString(c.getColumnIndex("sex"));
            String barangayId = c.getString(c.getColumnIndex("barangayId"));
            String muncityId = c.getString(c.getColumnIndex("muncityId"));
            String provinceId = c.getString(c.getColumnIndex("provinceId"));
            String income = c.getString(c.getColumnIndex("income"));
            String unmetNeed = c.getString(c.getColumnIndex("unmetNeed"));
            String waterSupply = c.getString(c.getColumnIndex("waterSupply"));
            String sanitaryToilet = c.getString(c.getColumnIndex("sanitaryToilet"));
            String educationalAttainment = c.getString(c.getColumnIndex("educationalAttainment"));
            String status = c.getString(c.getColumnIndex("status"));
            String diabetic = c.getString(c.getColumnIndex("diabetic"));
            String hypertension = c.getString(c.getColumnIndex("hypertension"));
            String pwd = c.getString(c.getColumnIndex("pwd"));
            String pregnant = c.getString(c.getColumnIndex("pregnant"));

            String birth_place = c.getString(c.getColumnIndex("birth_place"));
            String civil_status = c.getString(c.getColumnIndex("civil_status"));
            String religion = c.getString(c.getColumnIndex("religion"));
            String other_religion = c.getString(c.getColumnIndex("other_religion"));
            String contact = c.getString(c.getColumnIndex("contact"));
            String height = c.getString(c.getColumnIndex("height"));
            String weight = c.getString(c.getColumnIndex("weight"));
            String cancer = c.getString(c.getColumnIndex("cancer"));
            String cancer_type = c.getString(c.getColumnIndex("cancer_type"));
            String mental_med = c.getString(c.getColumnIndex("mental_med"));
            String tbdots_med = c.getString(c.getColumnIndex("tbdots_med"));
            String cvd_med = c.getString(c.getColumnIndex("cvd_med"));
            String covid_status = c.getString(c.getColumnIndex("covid_status"));
            String menarche = c.getString(c.getColumnIndex("menarche"));
            String menarche_age = c.getString(c.getColumnIndex("menarche_age"));
            String newborn_screen = c.getString(c.getColumnIndex("newborn_screen"));
            String newborn_text = c.getString(c.getColumnIndex("newborn_text"));
            String deceased = c.getString(c.getColumnIndex("deceased"));
            String deceased_date = c.getString(c.getColumnIndex("deceased_date"));
            String immu_stat = c.getString(c.getColumnIndex("immu_stat"));
            String nutri_stat = c.getString(c.getColumnIndex("nutri_stat"));
            String pwd_desc = c.getString(c.getColumnIndex("pwd_desc"));
            String sexually_active = c.getString(c.getColumnIndex("sexually_active"));

            familyProfile = new FamilyProfile(
                    id + "",
                    uniqueId,
                    familyId,
                    philId,
                    nhtsId,
                    isHead,
                    relation,
                    fname,
                    lname,
                    mname,
                    suffix,
                    dob,
                    sex,
                    barangayId,
                    muncityId,
                    provinceId,
                    income,
                    unmetNeed,
                    waterSupply,
                    sanitaryToilet,
                    educationalAttainment,
                    status,
                    diabetic,
                    hypertension,
                    pwd,
                    pregnant,
                    birth_place, civil_status, religion, other_religion, contact, height, weight, cancer, cancer_type, mental_med,
                    tbdots_med, cvd_med, covid_status, menarche, menarche_age, newborn_screen, newborn_text, deceased, deceased_date,
                    immu_stat, nutri_stat,pwd_desc, sexually_active);
        }
        c.close();
        db.close();
        return familyProfile;
    }

    public ArrayList<FamilyProfile> getMatchingProfiles(String cFname, String cMname, String cLname, String cSuffix) {
        ArrayList<FamilyProfile> profiles = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(PROFILES, null, "fname LIKE ? and mname LIKE ? and lname LIKE ? and suffix LIKE ?",
                new String[]{cFname + "%", cMname + "%", cLname + "%", cSuffix + "%"}, null, null, null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String uniqueId = c.getString(c.getColumnIndex("uniqueId"));
                String familyId = c.getString(c.getColumnIndex("familyId"));
                String philId = c.getString(c.getColumnIndex("philId"));
                String nhtsId = c.getString(c.getColumnIndex("nhtsId"));
                String isHead = c.getString(c.getColumnIndex("isHead"));
                String relation = c.getString(c.getColumnIndex("relation"));
                String fname = c.getString(c.getColumnIndex("fname"));
                String mname = c.getString(c.getColumnIndex("mname"));
                String lname = c.getString(c.getColumnIndex("lname"));
                String suffix = c.getString(c.getColumnIndex("suffix"));
                String dob = c.getString(c.getColumnIndex("dob"));
                String sex = c.getString(c.getColumnIndex("sex"));
                String barangayId = c.getString(c.getColumnIndex("barangayId"));
                String muncityId = c.getString(c.getColumnIndex("muncityId"));
                String provinceId = c.getString(c.getColumnIndex("provinceId"));
                String income = c.getString(c.getColumnIndex("income"));
                String unmetNeed = c.getString(c.getColumnIndex("unmetNeed"));
                String waterSupply = c.getString(c.getColumnIndex("waterSupply"));
                String sanitaryToilet = c.getString(c.getColumnIndex("sanitaryToilet"));
                String educationalAttainment = c.getString(c.getColumnIndex("educationalAttainment"));
                String status = c.getString(c.getColumnIndex("status"));
                String diabetic = c.getString(c.getColumnIndex("diabetic"));
                String hypertension = c.getString(c.getColumnIndex("hypertension"));
                String pwd = c.getString(c.getColumnIndex("pwd"));
                String pregnant = c.getString(c.getColumnIndex("pregnant"));


                String birth_place = c.getString(c.getColumnIndex("birth_place"));
                String civil_status = c.getString(c.getColumnIndex("civil_status"));
                String religion = c.getString(c.getColumnIndex("religion"));
                String other_religion = c.getString(c.getColumnIndex("other_religion"));
                String contact = c.getString(c.getColumnIndex("contact"));
                String height = c.getString(c.getColumnIndex("height"));
                String weight = c.getString(c.getColumnIndex("weight"));
                String cancer = c.getString(c.getColumnIndex("cancer"));
                String cancer_type = c.getString(c.getColumnIndex("cancer_type"));
                String mental_med = c.getString(c.getColumnIndex("mental_med"));
                String tbdots_med = c.getString(c.getColumnIndex("tbdots_med"));
                String cvd_med = c.getString(c.getColumnIndex("cvd_med"));
                String covid_status = c.getString(c.getColumnIndex("covid_status"));
                String menarche = c.getString(c.getColumnIndex("menarche"));
                String menarche_age = c.getString(c.getColumnIndex("menarche_age"));
                String newborn_screen = c.getString(c.getColumnIndex("newborn_screen"));
                String newborn_text = c.getString(c.getColumnIndex("newborn_text"));
                String deceased = c.getString(c.getColumnIndex("deceased"));
                String deceased_date = c.getString(c.getColumnIndex("deceased_date"));
                String immu_stat = c.getString(c.getColumnIndex("immu_stat"));
                String nutri_stat = c.getString(c.getColumnIndex("nutri_stat"));
                String pwd_desc = c.getString(c.getColumnIndex("pwd_desc"));
                String sexually_active = c.getString(c.getColumnIndex("sexually_active"));

                FamilyProfile profile = new FamilyProfile(
                        id + "",
                        uniqueId,
                        familyId,
                        philId,
                        nhtsId,
                        isHead,
                        relation,
                        fname,
                        lname,
                        mname,
                        suffix,
                        dob,
                        sex,
                        barangayId,
                        muncityId,
                        provinceId,
                        income,
                        unmetNeed,
                        waterSupply,
                        sanitaryToilet,
                        educationalAttainment,
                        status,
                        diabetic,
                        hypertension,
                        pwd,
                        pregnant,
                        birth_place, civil_status, religion, other_religion, contact, height, weight, cancer, cancer_type, mental_med,
                        tbdots_med, cvd_med, covid_status, menarche, menarche_age, newborn_screen, newborn_text, deceased, deceased_date,
                        immu_stat, nutri_stat, pwd_desc, sexually_active);

                if(relation.equalsIgnoreCase("Head")) profiles.add(0, profile);
                else profiles.add(profile);

                c.moveToNext();
            }
            c.close();
        }
        db.close();
        return profiles;
    }

    public void updateProfileById(String uniqueId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("status", 0);

        db.update(PROFILES, cv, "uniqueId = ?", new String[]{uniqueId});
    }

    public void deleteProfiles() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PROFILES, null, null);
    }

    public int getProfilesCount(String brgyId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + PROFILES;

        if(!brgyId.equals("")) countQuery += " where barangayId = '"+ brgyId +"'";

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();
        return count;
    }

    public int getUploadableCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + PROFILES + " where status = '1'";

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();
        return count;
    }

    //    public void addAccoount(Accounts acc) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("category", acc.category);
//        cv.put("site_name", acc.site_name);
//        cv.put("user_name", acc.user_name);
//        cv.put("password", acc.password);
//        db.insert(ACCOUNTS, null, cv);
//        db.close();
//    }
//
    public void addServicesAvail(String request) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("request", request);
        db.insert(SERVICES, null, cv);
        db.close();

        Toast.makeText(context, "Succesfully availed", Toast.LENGTH_SHORT).show();
    }

    public int getServicesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + SERVICES;

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();
        return count;
    }

    public ServiceAvailed getServiceForUpload() {
        ServiceAvailed serviceAvailed = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(SERVICES, null, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            JSONObject request=null;
            String id = c.getString(c.getColumnIndex("id"));
            try {
                request = new JSONObject(c.getString(c.getColumnIndex("request")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            serviceAvailed = new ServiceAvailed(id, request);
            Log.e("DBHelper", request.toString());
        }
        c.close();
        db.close();
        return serviceAvailed;
    }

    public void deleteService(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SERVICES, "id=?", new String[]{id});
    }

    public void addServiceStatus(ServicesStatus ss) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", ss.name);
        cv.put("group1", ss.group1);
        cv.put("group2", ss.group2);
        cv.put("group3", ss.group3);
        cv.put("barangayId", ss.brgyId);
        db.insert(SERVICESTATUS, null, cv);
        db.close();
    }

    public int getServiceStatusCount(String brgyId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + SERVICESTATUS;

        if(!brgyId.equals("")) countQuery += " where barangayId = '"+ brgyId +"'";

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();
        return count;
    }

    public void deleteServiceStatus() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SERVICESTATUS, null, null);
    }

    public ArrayList<ServicesStatus> getServicesStatus(String filter) {
        ArrayList<ServicesStatus> servicesStatuses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(SERVICESTATUS, null, filter, null, null, null, null, "20");

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                String name = c.getString(c.getColumnIndex("name"));
                String group1 = c.getString(c.getColumnIndex("group1"));
                String group2 = c.getString(c.getColumnIndex("group2"));
                String group3 = c.getString(c.getColumnIndex("group3"));
                String brgyId = c.getString(c.getColumnIndex("barangayId"));

                ServicesStatus servicesStatus = new ServicesStatus(name, group1, group2, group3, brgyId);

                servicesStatuses.add(servicesStatus);
                c.moveToNext();
            }
            c.close();
        }
        db.close();
        return servicesStatuses;
    }

    public void addFeedback(FeedBack fb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("subject", fb.subject);
        cv.put("body", fb.body);
        db.insert(FEEDBACK, null, cv);
        db.close();
    }

    public void deleteFeedback(String id) {
        SQLiteDatabase db = getWritableDatabase();
        if(id.isEmpty()) db.delete(FEEDBACK, null, null);
        else db.delete(FEEDBACK, "id=?", new String[]{id});
    }

    public String getFeedbacksForUpload() {
        String feedback = "";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(FEEDBACK, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                String subject = c.getString(c.getColumnIndex("subject"));
                String body = c.getString(c.getColumnIndex("body"));

                feedback +=  (c.getPosition() + 1) + ". " + subject + " - " + body + "\n\n";
                c.moveToNext();
            }
            c.close();
        }
        db.close();
        return feedback;
    }

    public ArrayList<FeedBack> getFeedbacks() {
        ArrayList<FeedBack> feedBacks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(FEEDBACK, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                String id = c.getString(c.getColumnIndex("id"));
                String subject = c.getString(c.getColumnIndex("subject"));
                String body = c.getString(c.getColumnIndex("body"));

                feedBacks.add(new FeedBack(id, subject, body));
                c.moveToNext();
            }
            c.close();
        }
        db.close();
        return feedBacks;
    }

    public int getFeedbacksCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + FEEDBACK;

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();
        return count;
    }


    /**SPECIALIST*/
    public void addSpecialist(SpecialistModel specialist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", specialist.username);
        cv.put("fname", specialist.fname);
        cv.put("mname", specialist.mname);
        cv.put("lname", specialist.lname);

        db.insertWithOnConflict(SPECIALIST, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
    }
    public void updateSpecialist(SpecialistModel specialist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", specialist.username);
        cv.put("fname", specialist.fname);
        cv.put("mname", specialist.mname);
        cv.put("lname", specialist.lname);
        db.update(SPECIALIST, cv, "username=?", new String[]{specialist.username});
        db.close();
    }

    public ArrayList<SpecialistModel> getSpecialists(String name) {
        name += "%";
        ArrayList<SpecialistModel> specialists = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(SPECIALIST, null, "fname LIKE ? or mname LIKE ? or lname LIKE ?", new String[]{name, name, name}, null, null, null, "20");

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String username = c.getString(c.getColumnIndex("username"));
                String fname = c.getString(c.getColumnIndex("fname"));
                String mname = c.getString(c.getColumnIndex("mname"));
                String lname = c.getString(c.getColumnIndex("lname"));

                SpecialistModel specialist = new SpecialistModel(id + "", username, fname, mname, lname);
                specialists.add(specialist);

                c.moveToNext();
            }
            c.close();
        }
        db.close();
        return specialists;
    }

    public void addAffiliatedFacility(AffiliatedFacilitiesModel facility) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", facility.username);
        cv.put("facility_id", facility.facility_id);
        cv.put("specialization", facility.specialization);
        cv.put("contact", facility.contact);
        cv.put("email", facility.email);
        cv.put("schedule", facility.schedule);
        cv.put("fee", facility.fee);

        db.insertWithOnConflict(FACILITY_ASSIGNMENT, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
    }
    public void updateAffiliatedFacility(AffiliatedFacilitiesModel facility) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", facility.username);
        cv.put("facility_id", facility.facility_id);
        cv.put("specialization", facility.specialization);
        cv.put("contact", facility.contact);
        cv.put("email", facility.email);
        cv.put("schedule", facility.schedule);
        cv.put("fee", facility.fee);

        db.update(FACILITY_ASSIGNMENT, cv, "id=?", new String[]{facility.id});
        db.close();
    }
    public ArrayList<AffiliatedFacilitiesModel> getAffiliatedFacilities(String name) {
        name += "%";
        ArrayList<AffiliatedFacilitiesModel> facilities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(FACILITY_ASSIGNMENT, null, "username LIKE ? ", new String[]{name}, null, null, null, "20");

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String username = c.getString(c.getColumnIndex("username"));
                String facility_id = c.getString(c.getColumnIndex("facility_id"));
                String specialization = c.getString(c.getColumnIndex("specialization"));
                String contact = c.getString(c.getColumnIndex("contact"));
                String email = c.getString(c.getColumnIndex("email"));
                String schedule = c.getString(c.getColumnIndex("schedule"));
                String fee = c.getString(c.getColumnIndex("fee"));

                AffiliatedFacilitiesModel facility = new AffiliatedFacilitiesModel(id + "", username, facility_id, specialization, contact, email, schedule, fee);
                facilities.add(facility);

                c.moveToNext();
            }
            c.close();
        }
        db.close();
        return facilities;
    }


    public void deleteAffiliatedFacility(String username) {
        SQLiteDatabase db = getWritableDatabase();
        if(username.isEmpty()) db.delete(FACILITY_ASSIGNMENT, null, null);
        else db.delete(FACILITY_ASSIGNMENT, "username=?", new String[]{username});
    }
    public void deleteAffiliatedFacilityById(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(FACILITY_ASSIGNMENT, "id=?", new String[]{id});
    }


}