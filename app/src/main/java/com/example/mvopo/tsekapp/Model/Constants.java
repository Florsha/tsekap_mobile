package com.example.mvopo.tsekapp.Model;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.mvopo.tsekapp.Helper.DBHelper;
import com.example.mvopo.tsekapp.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by mvopo on 10/30/2017.
 * Edited by Romaine on 02/18/2022
 */

public class Constants {

//    public static String url = "http://203.177.67.126/tsekap/vii/api?";
//    public static String apkUrl = "http://203.177.67.126/tsekap/vii/resources/apk/PHA%20Check-App.apk";

    public static String dengvaxiaUrl = "http://203.177.67.124/dengvaxia/api?";
    public static String dengvaxiaRegUrl = "http://192.168.101.59:8080/tsekap/vii/api/insertDengvaxia";
    public static String imageBaseUrl = "http://s/hrh/public/upload_picture/picture/";

//    public static String url = "http://192.168.100.145:8080/tsekap/vii/api?";
//    public static String apkUrl = "http://192.168.100.145:8080/tsekap/vii/resources/apk/PHA%20Check-App.apk";

   /* public static String url = "http://124.6.144.164/tsekap/vii/apiv21?";
    public static String apkUrl = "http://192.168.110.62:8000/tsekap/vii/resources/apk/PHA%20Check-App.apk";*/


    public static String url = "http://222.127.126.34/tsekap/dummy/apiv21?"; /**updated on 02/14/2022*/
    // public static String url = "http://222.127.126.34/tsekap/vii/apiv21?"; /**updated on 02/14/2022*/
    public static String apkUrl = "http://192.168.81.4:8000/tsekap/vii/resources/apk/PHA%20Check-App.apk"; /**updated on 02/14/2022*/

    private static  final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("ph", "PH"));


   //public static String url = "http://192.168.111.55/tsekap/apiv21?";  //r testing
    public static JSONObject getProfileJson() {

        FamilyProfile profile = MainActivity.db.getProfileForSync();

        JSONObject request = new JSONObject();
        JSONObject data = new JSONObject();

       try {
            data.accumulate("unique_id", profile.uniqueId);
            data.accumulate("familyID", profile.familyId);
            data.accumulate("phicID", profile.philId);
            data.accumulate("nhtsID", profile.nhtsId);
            data.accumulate("head", profile.isHead);
            data.accumulate("relation", profile.relation);
            data.accumulate("fname", profile.fname);
            data.accumulate("mname", profile.mname);
            data.accumulate("lname", profile.lname);
            data.accumulate("suffix", profile.suffix);
            data.accumulate("sex", profile.sex);
            data.accumulate("dob", profile.dob);
            data.accumulate("barangay_id", profile.barangayId);
            data.accumulate("muncity_id", profile.muncityId);
            data.accumulate("province_id", profile.provinceId);
            data.accumulate("income", profile.income);
            data.accumulate("unmet", profile.unmetNeed);
            data.accumulate("water", profile.waterSupply);
            data.accumulate("user_id", MainActivity.user.id);

            String toilet = profile.sanitaryToilet;

            if(!toilet.isEmpty()) {
                if(toilet.equals("1")) toilet = "non";
                else if(toilet.equals("2")) toilet = "comm";
                else if(toilet.equals("3")) toilet = "indi";
            }

            data.accumulate("toilet",toilet);
            data.accumulate("education", profile.educationalAttainment);

            data.accumulate("diabetic", profile.diabetic);
            data.accumulate("hypertension", profile.hypertension);
            data.accumulate("pwd", profile.pwd);
            data.accumulate("pregnant", profile.pregnant);
            //update r
            data.accumulate("birth_place", profile.birth_place);
            data.accumulate("civil_status", profile.civil_status);
            data.accumulate("religion", profile.religion);
            data.accumulate("other_religion", profile.other_religion);
            data.accumulate("contact", profile.contact);
            data.accumulate("height", profile.height);
            data.accumulate("weight", profile.weight);
            data.accumulate("cancer", profile.cancer);
            data.accumulate("cancer_type", profile.cancer_type);
            data.accumulate("mental_med", profile.mental_med);
            data.accumulate("tbdots_med", profile.tbdots_med);
            data.accumulate("cvd_med", profile.cvd_med);
            data.accumulate("covid_status", profile.covid_status);
            data.accumulate("menarche", profile.menarche);
            data.accumulate("menarche_age", profile.menarche_age);
            data.accumulate("newborn_screen", profile.newborn_screen);
            data.accumulate("newborn_text", profile.newborn_text);
            data.accumulate("deceased", profile.deceased);
            data.accumulate("deceased_date", profile.deceased_date);
            data.accumulate("immu_stat", profile.immu_stat);
            data.accumulate("nutri_stat", profile.nutri_stat);
            data.accumulate("pwd_desc", profile.pwd_desc);
           data.accumulate("sexually_active", profile.sexually_active);

            request.accumulate("data", data);
            //request.accumulate("_token", MainActivity.user.token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return request;
    }

    public static void setDateTextWatcher(final Context context, final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //                //Log.e("QWEQWE", i + " " + i1 + " " + i2);

                String date = editText.getText().toString();
                Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH) + 1;
                int day = c.get(Calendar.DAY_OF_MONTH);

                if (i1 == 0) {
                    if (date.length() == 4) {
                        if (Integer.parseInt(date) > year) {
                            Toast.makeText(context, "Future date is invalid+.", Toast.LENGTH_SHORT).show();
                            date = "";
                            editText.setText("");
                        }
                    } else if (date.length() == 7) {
                        if (Integer.parseInt(date.substring(5, 7)) > 12) {

                            if (Integer.parseInt(date.substring(0, 4)) == year) {
                                date = date.substring(0,5) + String.format("%02d", month);
                                editText.setText(date);
                                editText.setSelection(editText.getText().length());
                                Toast.makeText(context, "Date should not exceed current date.", Toast.LENGTH_SHORT).show();
                            } else {
                                date = date.substring(0,5) + "12";
                                Toast.makeText(context, "Maximum month is 12", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else if (date.length() == 10) {
                        c.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, 1);
                        int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);

                        if (Integer.parseInt(date.substring(8, 10)) > maxDay) {
                            Toast.makeText(context, "Maximum day for " + date.substring(0, date.length() - 3) + " is " + maxDay, Toast.LENGTH_LONG).show();
                            date = date.substring(0,8) + maxDay;

                            editText.setText(date);
                            editText.setSelection(editText.getText().length());
                        }

                        if (Integer.parseInt(date.substring(0, 4)) == year && Integer.parseInt(date.substring(5, 7)) >= month &&
                                Integer.parseInt(date.substring(8, 10)) > day) {
                            date = date.substring(0,5) + String.format("%02d", day);
                            Toast.makeText(context, "Date should not exceed current date.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if ((date.length() == 4 || date.length() == 7)) {
                        editText.setText(date += "-");
                        editText.setSelection(editText.getText().length());
                    }
                } else if (i1 == 1) {
                    if (date.length() == 4 || date.length() == 7) {
                        editText.setText(date.substring(0, date.length() - 1));
                        editText.setSelection(editText.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public static void setMoneyTextWatcher(final Context context, final EditText editText){
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setRoundingMode(RoundingMode.FLOOR);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable editable) {
                editText.removeTextChangedListener(this);

                String initialText = editText.getText().toString();
                String formatted;
                String decimal="", integer="";
                /**Remove all not numbers, (, . PHP)*/
                String tempo1 = initialText.replaceAll("PHP ", "");
                String tempo2 = tempo1.replaceAll(",", "");
                String currentValue = tempo2.replaceAll("\\.", "");

                if(!currentValue.isEmpty()){
                    if(currentValue.length()==1){
                        decimal="0"+currentValue.trim();
                    }
                    else {
                        decimal=currentValue.substring(currentValue.length()-2);
                        integer=currentValue.substring(0, currentValue.length()-2);
                    }
                    if(!integer.trim().isEmpty()){
                        formatted = numberFormat.format(Double.parseDouble(integer)) + "." + decimal;
                        formatted = formatted.replaceAll("PHP", "PHP ");
                    }
                    else{
                        formatted="PHP 0." + decimal;
                    }
                }else{
                    formatted="PHP 0." + decimal;
                }

                editText.setText(formatted);
                editText.setSelection(editText.getText().length());
                editText.addTextChangedListener(this);
            }
        });
    }



    public static String getAge(String date, Calendar c) {
        int year, month, day;
        String ageString = "";

        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar dob = Calendar.getInstance();
            dob.setTime(myFormat.parse(date));

            year = c.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH);

            if (month >= 0) {
                ageString = month + " m/o";

                if(day > 0)ageString = day + " d/o";
                else if(day < 0){
                    if(year > 0){
                        year--;
                        month += 11;
                    }
                    else month--;
                }

            } else if(month < 0) year--;


            if(year > 0) ageString = year + "";
            else if(month > 0) ageString = month + " m/o";
            else{
                if(day > 0){
                    ageString = day + " d/o";
                }else{
                    if(day < 0) month--;

                    if(month <= 0) {
                        String now = c.get(Calendar.YEAR) + "-" + String.format("%02d", (c.get(Calendar.MONTH) + 1)) +
                                "-" + String.format("%02d", (c.get(Calendar.DAY_OF_MONTH)));

                        Date date1 = myFormat.parse(date);
                        Date date2 = myFormat.parse(now);
                        long diff = date2.getTime() - date1.getTime();

                        ageString = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + " d/o";
                    }
                }
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ageString;
    }

    public static String getBrgyName(String id) {
        String name = "";
        try {
            JSONArray arrayBrgy = new JSONArray(MainActivity.user.barangay);
            for (int i = 0; i < arrayBrgy.length(); i++) {
                JSONObject assignedBrgy = arrayBrgy.getJSONObject(i);
                String barangayId = assignedBrgy.getString("barangay_id");
                if (id.equalsIgnoreCase(barangayId)) {
                    name = assignedBrgy.getString("description");
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static String twoDigitFormat(int value)
    {
        return new DecimalFormat("00").format(value);
    }


}
