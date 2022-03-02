package com.example.mvopo.tsekapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvopo.tsekapp.Helper.ListAdapter;
import com.example.mvopo.tsekapp.MainActivity;
import com.example.mvopo.tsekapp.Model.Constants;
import com.example.mvopo.tsekapp.Model.FamilyProfile;
import com.example.mvopo.tsekapp.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mvopo on 10/20/2017.
 */

public class ManagePopulationFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private final int CAMERA_CODE = 100;
    LinearLayout updateFields ;
    ScrollView optionHolder;
    EditText txtFamilyId, txtPhilHealthId, txtNhtsId, txtFname, txtMname, txtLname, txtBday, txtBrgy,
            txtHead, txtEducation, txtSuffix, txtSex, txtIncome, txtUnmet, txtSupply, txtToilet, txtRelation,
            txtFacilityName, txtListNumber, txtDoseScreen, txtDoseDate, txtDoseLot, txtDoseBatch, txtDoseExpiration,
            txtDoseAefi, txtRemarks;

    TextView  tvStatus;
    Button manageBtn, optionBtn, manageDengvaxia, dengvaxiaRegisterBtn;
    TextInputLayout unmetFrame;
    View view;
    ImageView ivPatient;

    // UPDATE
    EditText txtDiabetic, txtHypertension, txtPWD, txtPregnantDate, txtIsPregnant;
    TextInputLayout til_isPregnantFrame, til_pregnantDate;
    /*END*/

    FamilyProfile familyProfile;
    List<FamilyProfile> matchingProfiles = new ArrayList<>();

    String famId, philId, nhtsId, fname, mname, lname, bday, brgy, head, relation, education, suffix, sex, income, unmet, supply, toilet;

    // UPDATE
    String diabetic, hypertension, pwd, pregnant_date, isPregnant;   /*END*/

    // UPDATE - Romaine
    EditText  txtBirthPlace, txtCivil, txtReligion, txtOtherReligion, txtContact, txtHeight, txtWeight, txtCancer,txtCancerType, txtMental, txtTBDOTS, txtCVD,
            txtCovid, txtPwdDesc, txtMenarche, txtMenarcheAge, txtDecease, txtDeceasedDate, txtImmunization, txtNutrition, txtNewbornScreen, txtNewbornText;
    String birth_place, civil_status, religion, other_religion, contact, height, weight, cancer, cancer_type, mental_med,
        tbdots_med,cvd_med, covid_status, menarche, menarche_age, newborn_screen, newborn_text, deceased, deceased_date,
        immu_stat, nutri_stat, pwd_desc;

    TextInputLayout til_otherReligion, til_pwdType, til_cancerType, til_deceasedDate, til_menarche, til_menarcheAge, til_newborn;
    LinearLayout layout_adult, layout_age5;
    /*END*/

    String[] brgys, value;
    String[] brgyIds;
    int age = 0;
    boolean brgyFieldClicked = false;
    boolean toUpdate, addHead;

    String males = "Son, Husband, Father, Brother, Nephew, Grandfather, Grandson, Son in Law, Brother in Law, Father in Law";
    String females = "Daughter, Wife, Mother, Sister, Niece, Grandmother, Granddaugther, Daughter in Law, Sister in Law, Mother in Law";

    Calendar now = Calendar.getInstance();
    DatePickerDialog dpd = DatePickerDialog.newInstance(
            ManagePopulationFragment.this,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
    );

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage_member, container, false);
        findViewByIds();

        toUpdate = getArguments().getBoolean("toUpdate");
        addHead = getArguments().getBoolean("addHead");
        familyProfile = getArguments().getParcelable("familyProfile");

        //Toast.makeText(getContext(), addHead+"", Toast.LENGTH_SHORT).show();
        dpd.setMaxDate(now);
        value = getResources().getStringArray(R.array.educational_attainment_value);
        try {
            JSONArray array = new JSONArray(MainActivity.user.barangay);

            brgys = new String[array.length()];
            brgyIds = new String[array.length()];

            for (int i = 0; i < array.length(); i++) {
                brgys[i] = array.getJSONObject(i).getString("description");
                brgyIds[i] = array.getJSONObject(i).getString("barangay_id");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        manageDengvaxia = view.findViewById(R.id.manageDengvaxia);

        txtFamilyId.setText(familyProfile.familyId);
        if (!toUpdate) {
            view.findViewById(R.id.layout_head).setVisibility(View.GONE);
            if (!addHead || txtHead.getText().toString().equalsIgnoreCase("NO")) {
                updateFields.setVisibility(View.GONE);
                unmetFrame.setVisibility(View.GONE);
                til_isPregnantFrame.setVisibility(View.GONE);
                view.findViewById(R.id.layout_relation).setVisibility(View.VISIBLE);
            }
            manageBtn.setText("Add");
        } else {
            try{
                if(Integer.parseInt(Constants.getAge(familyProfile.dob, Calendar.getInstance())) > 8){
                    manageDengvaxia.setVisibility(View.VISIBLE);
                }
            }catch (Exception e){}

            setFieldTexts();
        }

        txtBrgy.setOnClickListener(this);
        txtBday.setOnClickListener(this);
        txtEducation.setOnClickListener(this);

        if (!addHead) txtHead.setOnClickListener(this);

        txtRelation.setOnClickListener(this);
        txtSex.setOnClickListener(this);
        txtSuffix.setOnClickListener(this);
        txtIncome.setOnClickListener(this);
        txtUnmet.setOnClickListener(this);
        txtSupply.setOnClickListener(this);
        txtToilet.setOnClickListener(this);
        // UPDATE
        txtDiabetic.setOnClickListener(this);
        txtHypertension.setOnClickListener(this);
        txtPWD.setOnClickListener(this);
        txtPregnantDate.setOnClickListener(this);
        txtIsPregnant.setOnClickListener(this);
        // END

        //update r
        txtMental.setOnClickListener(this);
        txtTBDOTS.setOnClickListener(this);
        txtCVD.setOnClickListener(this);
        txtCivil.setOnClickListener(this);
        txtReligion.setOnClickListener(this);
        txtDecease.setOnClickListener(this);
        txtCancer.setOnClickListener(this);
        txtDeceasedDate.setOnClickListener(this);
        txtMenarche.setOnClickListener(this);
        txtNewbornScreen.setOnClickListener(this);
        txtImmunization.setOnClickListener(this);
        txtNutrition.setOnClickListener(this);
        //end
        manageBtn.setOnClickListener(this);

//        manageDengvaxia.setOnClickListener(this);


        // TODO : ADD TEXT CHANGE LISTENER
        txtIsPregnant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                isPregnant = txtIsPregnant.getText().toString();
                {
                    if(isPregnant.equalsIgnoreCase("Yes"))
                    {
                        til_pregnantDate.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        til_pregnantDate.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtSex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sex = txtSex.getText().toString();
                if (((age >= 15 && age <= 49) || addHead) && sex.equalsIgnoreCase("Female"))
                {
                    til_menarche.setVisibility(View.VISIBLE);
                    unmetFrame.setVisibility(View.VISIBLE);
                    til_isPregnantFrame.setVisibility(View.VISIBLE);
                }
                else
                    {
                        til_menarche.setVisibility(View.GONE);
                        til_menarcheAge.setVisibility(View.GONE);
                        unmetFrame.setVisibility(View.GONE);
                        til_isPregnantFrame.setVisibility(View.GONE);
                        til_pregnantDate.setVisibility(View.GONE);
                        txtIsPregnant.setText("");
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        txtBday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(age < 5){
                    layout_adult.setVisibility(View.GONE);
                    layout_age5.setVisibility(View.VISIBLE);
                }else {
                    layout_adult.setVisibility(View.VISIBLE);
                    layout_age5.setVisibility(View.GONE);
                }
            }
        });

        Constants.setDateTextWatcher(getContext(), txtBday);
        Constants.setDateTextWatcher(getContext(), txtDeceasedDate);
        Constants.setDateTextWatcher(getContext(), txtPregnantDate);
        if (!toUpdate) showProfileCheckerDialog();
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            // UPDATE
            case R.id.manage_immunization:
                showChecklistDialog(R.array.immunization, txtImmunization);
                break;
            case R.id.manage_nutrition:
                showChecklistDialog(R.array.nutrition, txtNutrition);
                break;
            case R.id.manage_diabetes:
                showOptionDialog(R.array.medication_availment, txtDiabetic);
                break;
            case R.id.manage_hypertension:
                showOptionDialog(R.array.medication_availment, txtHypertension);
                break;
            case R.id.manage_mental:
                showOptionDialog(R.array.medication_availment, txtMental);
                break;
            case R.id.manage_tbdots:
                showOptionDialog(R.array.medication_availment, txtTBDOTS);
                break;
            case R.id.manage_cvd:
                showOptionDialog(R.array.medication_availment, txtCVD);
                break;
            case R.id.manage_religion:
                showOptionDialog(R.array.religion, txtReligion);

                break;
            case R.id.manage_civil:
                showOptionDialog(R.array.civil_status, txtCivil);
                break;
            case R.id.manage_cancer:
                showOptionDialog(R.array.yes_no, txtCancer);
                break;
            case R.id.manage_menarche:
                showOptionDialog(R.array.yes_no, txtMenarche);
                break;
            case R.id.manage_newborn:
                showOptionDialog(R.array.yes_no, txtNewbornScreen);
                break;

            case R.id.manage_deceased:
                showOptionDialog(R.array.yes_no, txtDecease);
                break;
            case R.id.manage_deceased_date:
                dpd.show(getActivity().getFragmentManager(), "deceased");
                break;
            case R.id.manage_pwd:
                showOptionDialog(R.array.yes_no, txtPWD);
                break;
            case R.id.manage_isPregnant:
                showOptionDialog(R.array.yes_no, txtIsPregnant);
                break;
//                 END
            case R.id.manage_education:
                showOptionDialog(R.array.educational_attainment, txtEducation);
                break;
            case R.id.manage_head:
                showOptionDialog(R.array.yes_no, txtHead);
                break;
            case R.id.manage_relation:
                showOptionDialog(R.array.realation_to_head, txtRelation);
                break;
            case R.id.manage_sex:
                showOptionDialog(R.array.sex, txtSex);
                break;
            case R.id.manage_suffix:
                showOptionDialog(R.array.suffix, txtSuffix);
                break;
            case R.id.manage_bday:
                dpd.show(getActivity().getFragmentManager(), "bday");
                break;
            case R.id.manage_pregnantDate:
                dpd.show(getActivity().getFragmentManager(), "lmp");
                break;
            case R.id.manage_income:
                showOptionDialog(R.array.monthly_income, txtIncome);
                break;
            case R.id.manage_unmet:
                showOptionDialog(R.array.unmet_needs, txtUnmet);
                break;
            case R.id.manage_supply:
                showOptionDialog(R.array.water_supply, txtSupply);
                break;
            case R.id.manage_toilet:
                showOptionDialog(R.array.sanitary_toilet, txtToilet);
                break;
            case R.id.manage_barangay:
                brgyFieldClicked = true;
                showOptionDialog(0, txtBrgy);
                break;
//            case R.id.manageDengvaxia:
////                showDengvaxiaDialog();
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("familyProfile", familyProfile);
//
//                DengvaxiaFormFragment dff = new DengvaxiaFormFragment();
//                dff.setArguments(bundle);
//
//                MainActivity.ft = MainActivity.fm.beginTransaction();
//                MainActivity.ft.replace(R.id.fragment_container, dff).addToBackStack(null).commit();
//                break;

            case R.id.dengvaxia_dose_screen:
                showOptionDialog(R.array.yes_no, txtDoseScreen);
                break;
            case R.id.dengvaxia_dose_date:
                dpd.show(getActivity().getFragmentManager(), "dose_date");
                break;
            case R.id.dengvaxia_dose_expiration:
                dpd.show(getActivity().getFragmentManager(), "dose_expiry");
                break;
            case R.id.dengvaxia_dose_aefi:
                showOptionDialog(R.array.yes_no, txtDoseAefi);
                break;
            case R.id.dengvaxia_patient_image:
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                CAMERA_CODE);
                        break;
                    }
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_CODE);

                break;
//            case R.id.dengvaxiaBtn:
//                String doseDate = txtDoseDate.getText().toString().trim();
//                if (doseDate.isEmpty() && doseDate.length() != 10) {
//                    Toast.makeText(getContext(), "Invalid date, please follow YYYY-MM-DD format", Toast.LENGTH_SHORT).show();
//                }else{
//                    try {
//                        JSONObject request = new JSONObject();
//                        request.accumulate("tsekap_id", familyProfile.id);
//                        request.accumulate("facility_name", txtFacilityName.getText().toString());
//                        request.accumulate("list_number", txtListNumber.getText().toString());
//                        request.accumulate("fname", familyProfile.fname);
//                        request.accumulate("mname", familyProfile.mname);
//                        request.accumulate("lname", familyProfile.lname);
//                        request.accumulate("muncity", familyProfile.muncityId);
//                        request.accumulate("barangay", familyProfile.barangayId);
//                        request.accumulate("dob", familyProfile.dob);
//                        request.accumulate("sex", familyProfile.sex);
//                        request.accumulate("dose_screened", txtDoseScreen.getText().toString());
//                        request.accumulate("dose_date_given", doseDate);
//
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        Date date;
//                        Calendar c = Calendar.getInstance();
//
//                        try {
//                            date = sdf.parse(doseDate);
//                            c.setTime(date);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//
//                        String age = Constants.getAge(familyProfile.dob, c);
//
//                        request.accumulate("dose_age", age);
//
//                        if(!age.contains("/") && (Integer.parseInt(age) >= 9 &&
//                                Integer.parseInt(age) <= 14)) request.accumulate("validation", "Yes");
//                        else request.accumulate("validation", "No");
//
//                        request.accumulate("dose_lot_no", txtDoseLot.getText().toString());
//                        request.accumulate("dose_batch_no", txtDoseBatch.getText().toString());
//                        request.accumulate("dose_expiration", txtDoseExpiration.getText().toString());
//                        request.accumulate("dose_AEFI", txtDoseAefi.getText().toString());
//                        request.accumulate("remarks", txtRemarks.getText().toString());
//
//                        MainActivity.pd = ProgressDialog.show(getContext(), "Loading", "Please wait...", false, false);
//                        JSONApi.getInstance(getContext()).dengvaxiaRegister(Constants.dengvaxiaUrl + "cmd=register", request);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                break;
            case R.id.manageBtn:
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                famId = txtFamilyId.getText().toString().trim();
                philId = txtPhilHealthId.getText().toString().trim();
                nhtsId = txtNhtsId.getText().toString().trim();
                fname = txtFname.getText().toString().trim();
                mname = txtMname.getText().toString().trim();
                lname = txtLname.getText().toString().trim();
                bday = txtBday.getText().toString().trim();
                brgy = txtBrgy.getText().toString().trim();
                head = txtHead.getText().toString().trim();

                relation = txtRelation.getText().toString().trim();
                if (relation.equalsIgnoreCase("Live-in Partner")) relation = "partner";

                suffix = txtSuffix.getText().toString().trim();
                sex = txtSex.getText().toString().trim();

                // UPDATE
                diabetic = txtDiabetic.getText().toString().trim();
                hypertension = txtHypertension.getText().toString().trim();
                pwd = txtPWD.getText().toString().trim();

                //update r
                birth_place = txtBirthPlace.getText().toString().trim();
                civil_status = txtCivil.getText().toString().trim();
                religion = txtReligion.getText().toString().trim();
                other_religion = txtOtherReligion.getText().toString().trim();
                contact = txtContact.getText().toString().trim();
                height = txtHeight.getText().toString().trim();
                weight = txtWeight.getText().toString().trim();
                cancer = txtCancer.getText().toString().trim();
                cancer_type = txtCancerType.getText().toString().trim();
                mental_med = txtMental.getText().toString().trim();
                tbdots_med = txtTBDOTS.getText().toString().trim();
                cvd_med = txtCVD.getText().toString().trim();
                covid_status = txtCovid.getText().toString().trim();
                menarche = txtMenarche.getText().toString().trim();
                menarche_age = txtMenarcheAge.getText().toString().trim();
                newborn_screen = txtNewbornScreen.getText().toString().trim();
                newborn_text = txtNewbornText.getText().toString().trim();
                deceased = txtDecease.getText().toString().trim();
                deceased_date = txtDeceasedDate.getText().toString().trim();
                immu_stat = txtImmunization.getText().toString().trim();
                nutri_stat = txtNutrition.getText().toString().trim();
                pwd_desc = txtPwdDesc.getText().toString().trim();

                /*end r*/
                pregnant_date = txtPregnantDate.getText().toString().trim();

                try {
                    income = txtIncome.getTag().toString();
                } catch (Exception e) {
                    income = familyProfile.income;
                }
                try {
                    unmet = txtUnmet.getTag().toString();
                } catch (Exception e) {
                    unmet = familyProfile.unmetNeed;
                }
                try {
                    supply = txtSupply.getTag().toString();
                } catch (Exception e) {
                    supply = familyProfile.waterSupply;
                }
                try {
                    toilet = txtToilet.getTag().toString();
                } catch (Exception e) {
                    toilet = familyProfile.sanitaryToilet;
                }
                try {
                    education = txtEducation.getTag().toString();
                } catch (Exception e) {
                    education = familyProfile.educationalAttainment;
                }

                if (fname.isEmpty()) {
                    txtFname.setError("Required");
                    txtFname.requestFocus();
                } else if (lname.isEmpty()) {
                    txtLname.setError("Required");
                    txtLname.requestFocus();
                } else if (bday.isEmpty() && bday.length() != 10)
                {
                    Toast.makeText(getContext(), "Invalid date, please follow YYYY-MM-DD format", Toast.LENGTH_SHORT).show();
                }
                else if (sex.isEmpty())
                {
                    Toast.makeText(getContext(), "Gender required", Toast.LENGTH_SHORT).show();
                }
                // UPDATE
                else if (diabetic.isEmpty())
                {
                    Toast.makeText(getContext(), "Is the person Diabetic?", Toast.LENGTH_SHORT).show();
                }
                else if(hypertension.isEmpty())
                {
                    Toast.makeText(getContext(), "Is the person experiencing Hypertension?", Toast.LENGTH_SHORT).show();
                }
                else if(pwd.isEmpty())
                {
                    Toast.makeText(getContext(), "Is the person with disabilities?", Toast.LENGTH_SHORT).show();
                }
                // END
                else if (brgy.isEmpty())
                {
                    Toast.makeText(getContext(), "Barangay required", Toast.LENGTH_SHORT).show();
                } else {
                    if (brgyFieldClicked) brgy = txtBrgy.getTag().toString();
                    else brgy = familyProfile.barangayId;

                    //Toast.makeText(getContext(), brgy, Toast.LENGTH_SHORT).show();
                    if (manageBtn.getText().toString().equalsIgnoreCase("ADD")) {
                        if (addHead) {
                            head = "YES";
                            relation = "Head";
                        } else head = "NO";

                        FamilyProfile newFamilyProfile = new FamilyProfile(
                                "",
                                fname + mname + lname + suffix + brgy + MainActivity.user.muncity,
                                famId,
                                philId,
                                nhtsId,
                                head,
                                relation,
                                fname,
                                lname,
                                mname,
                                suffix,
                                bday,
                                sex,
                                brgy,
                                MainActivity.user.muncity,
                                "",
                                income,
                                unmet,
                                supply,
                                toilet,
                                education,
                                "1",
                                diabetic,
                                hypertension,
                                pwd,
                                pregnant_date,
                                birth_place, civil_status, religion, other_religion, contact, height, weight, cancer, cancer_type, mental_med,
                                tbdots_med, cvd_med, covid_status, menarche, menarche_age, newborn_screen, newborn_text, deceased, deceased_date,
                                immu_stat, nutri_stat, pwd_desc);
                        MainActivity.db.addProfile(newFamilyProfile);
                        Toast.makeText(getContext(), "Successfully added", Toast.LENGTH_SHORT).show();
                    } else {
                        if (relation.isEmpty()) relation = familyProfile.relation;
                        if (head.equalsIgnoreCase("Yes")) relation = "Head";

                        FamilyProfile updatedFamilyProfile = new FamilyProfile(
                                familyProfile.id,
                                familyProfile.uniqueId,
                                famId,
                                philId,
                                nhtsId,
                                head,
                                relation,
                                fname,
                                lname,
                                mname,
                                suffix,
                                bday,
                                sex,
                                brgy,
                                familyProfile.muncityId,
                                familyProfile.provinceId,
                                income,
                                unmet,
                                supply,
                                toilet,
                                education,
                                "1",
                                diabetic,
                                hypertension,
                                pwd,
                                pregnant_date, birth_place, civil_status, religion, other_religion, contact, height, weight, cancer, cancer_type, mental_med,
                                tbdots_med, cvd_med, covid_status, menarche, menarche_age, newborn_screen, newborn_text, deceased, deceased_date,
                                immu_stat, nutri_stat, pwd_desc);
                        MainActivity.db.updateProfile(updatedFamilyProfile);
                        Toast.makeText(getContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                    }

                    MainActivity.fm.popBackStack();
                }
                break;

//                case:

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            Log.e("ManagePop", data.toString());
            switch (requestCode) {
                case CAMERA_CODE:
                    Uri imageUri = data.getData();
                    CropImage.activity(imageUri)
                            .setFixAspectRatio(true)
                            .start(getContext(), this);
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);
                    Uri resultUri = result.getUri();
                    ivPatient.setImageURI(resultUri);
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_CODE);
                }else{
                    Toast.makeText(getContext(), "Camera Permission Denied, cant proceed this action", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void setFieldTexts() {
        txtFamilyId.setText(familyProfile.familyId);
        txtPhilHealthId.setText(familyProfile.philId);
        txtNhtsId.setText(familyProfile.nhtsId);
        txtFname.setText(familyProfile.fname);
        txtMname.setText(familyProfile.mname);
        txtLname.setText(familyProfile.lname);
        txtBday.setText(familyProfile.dob);

        txtBrgy.setText(Constants.getBrgyName(familyProfile.barangayId));


        if (familyProfile.relation.equalsIgnoreCase("partner"))
            txtRelation.setText("Live-in Partner");
        else txtRelation.setText(familyProfile.relation);

        if (toUpdate) {
            txtHead.setText(familyProfile.isHead);

            if (txtHead.getText().toString().equalsIgnoreCase("NO")) {
                updateFields.setVisibility(View.GONE);
                view.findViewById(R.id.layout_relation).setVisibility(View.VISIBLE);
            }

            age = Integer.parseInt(Constants.getAge(txtBday.getText().toString(), Calendar.getInstance()).split(" ")[0]);
            if ((age < 15 || age > 49) && txtSex.getText().toString().equalsIgnoreCase("Female")) {
                unmetFrame.setVisibility(View.GONE);
                til_isPregnantFrame.setVisibility(View.GONE);
            }
            else {
                unmetFrame.setVisibility(View.VISIBLE);
                til_isPregnantFrame.setVisibility(View.VISIBLE);
            }

            if(familyProfile.sex.equalsIgnoreCase("Female") && age>5){
                til_menarche.setVisibility(View.VISIBLE);
            }else {
                til_menarche.setVisibility(View.GONE);
            }

            if(age < 5){
                layout_adult.setVisibility(View.GONE);
                layout_age5.setVisibility(View.VISIBLE);
            }else   {
                layout_adult.setVisibility(View.VISIBLE);
                layout_age5.setVisibility(View.GONE);
            }

        } else txtHead.setText(familyProfile.relation);

        txtSuffix.setText(familyProfile.suffix);
        txtSex.setText(familyProfile.sex);
        txtDiabetic.setText(familyProfile.diabetic);
        txtHypertension.setText(familyProfile.hypertension);
        txtPWD.setText(familyProfile.pwd);


        if(!familyProfile.pregnant.isEmpty()) {
            txtIsPregnant.setText("NO");
            til_pregnantDate.setVisibility(View.GONE);
        }
        else {
            txtIsPregnant.setText("YES");
            txtPregnantDate.setText(familyProfile.pregnant);
            til_pregnantDate.setVisibility(View.VISIBLE);
        }


        Log.e("MPF", familyProfile.income + " " + familyProfile.unmetNeed + " " + familyProfile.waterSupply + " " + familyProfile.sanitaryToilet);
        if (!familyProfile.income.isEmpty() && !familyProfile.income.equals("0"))
            txtIncome.setText(getResources()
                    .getStringArray(R.array.monthly_income)[Integer.parseInt(familyProfile.income) - 1]);
        if (!familyProfile.unmetNeed.isEmpty() && !familyProfile.unmetNeed.equals("0"))
            txtUnmet.setText(getResources()
                    .getStringArray(R.array.unmet_needs)[Integer.parseInt(familyProfile.unmetNeed) - 1]);
        if (!familyProfile.waterSupply.isEmpty() && !familyProfile.waterSupply.equals("0"))
            txtSupply.setText(getResources()
                    .getStringArray(R.array.water_supply)[Integer.parseInt(familyProfile.waterSupply) - 1]);
        try {
            if (!familyProfile.sanitaryToilet.isEmpty() && !familyProfile.sanitaryToilet.equals("0"))
                txtToilet.setText(getResources()
                        .getStringArray(R.array.sanitary_toilet)[Integer.parseInt(familyProfile.sanitaryToilet) - 1]);
        } catch (Exception e) {
            String toilet = familyProfile.sanitaryToilet;

            if (toilet.equals("non")) {
                txtToilet.setText(getResources()
                        .getStringArray(R.array.sanitary_toilet)[0]);
            } else if (toilet.equals("comm")) {
                txtToilet.setText(getResources()
                        .getStringArray(R.array.sanitary_toilet)[1]);
            } else {
                txtToilet.setText(getResources()
                        .getStringArray(R.array.sanitary_toilet)[2]);
            }
        }

        for (int i = 0; i < value.length; i++) {
            if (familyProfile.educationalAttainment.equals(value[i])) {
                txtEducation.setText(getResources()
                        .getStringArray(R.array.educational_attainment)[i]);
                break;
            }
        }

        //update r
        txtCovid.setText(familyProfile.covid_status);
        txtBirthPlace.setText(familyProfile.birth_place);
        txtCivil.setText(familyProfile.civil_status);
        txtReligion.setText(familyProfile.religion);
        txtOtherReligion.setText(familyProfile.other_religion);
        txtContact.setText(familyProfile.contact);
        txtHeight.setText(familyProfile.height);
        txtWeight.setText(familyProfile.weight);
        txtCancer.setText(familyProfile.cancer);
        txtCancerType.setText(familyProfile.cancer_type);
        txtMental.setText(familyProfile.mental_med);
        txtTBDOTS.setText(familyProfile.tbdots_med);
        txtCVD.setText(familyProfile.cvd_med);
        txtMenarcheAge.setText(familyProfile.menarche_age);
        txtMenarche.setText(familyProfile.menarche);
        txtDecease.setText(familyProfile.deceased);
        txtDeceasedDate.setText(familyProfile.deceased_date);
        txtNutrition.setText(familyProfile.nutri_stat);
        txtImmunization.setText(familyProfile.immu_stat);
        txtPwdDesc.setText(familyProfile.pwd_desc);
        txtNewbornText.setText(familyProfile.newborn_text);
        txtNewbornScreen.setText(familyProfile.newborn_screen);








    }

    public void showOptionDialog(final int id, final EditText txtView) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.options_dialog, null);
        optionHolder = view.findViewById(R.id.option_holder);
        optionBtn = view.findViewById(R.id.optionBtn);

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

        String[] labels;

        if (id != 0) labels = getResources().getStringArray(id);
        else labels = brgys;

        final RadioGroup radioGroup = new RadioGroup(getContext());

        LinearLayout.LayoutParams rbParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rbParam.bottomMargin = 5;
        rbParam.topMargin = 5;

        for (int i = 0; i < labels.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setLayoutParams(rbParam);
            radioButton.setText(labels[i]);

            if (txtView.getId() == R.id.manage_barangay)
                radioButton.setId(Integer.parseInt(brgyIds[i]));
            else if (txtView.getId() == R.id.manage_education) {
                radioButton.setTag(value[i]);
            } else if (txtView.getId() == R.id.manage_income || txtView.getId() == R.id.manage_unmet ||
                    txtView.getId() == R.id.manage_supply || txtView.getId() == R.id.manage_toilet) {
                radioButton.setId(i + 1);
            }
            View lineView = new View(getContext());
            lineView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            lineView.setLayoutParams(params);

            radioGroup.addView(radioButton);
            radioGroup.addView(lineView);
        }

        //((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
        optionHolder.addView(radioGroup);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        final AlertDialog optionDialog = builder.create();
        optionDialog.show();

        optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                if (radioButton != null) {
                    txtView.setText(radioButton.getText());

                    if(txtView.getId() == R.id.manage_religion){
                        if (txtView.getText().toString().trim().equalsIgnoreCase("Others")){
                            til_otherReligion.setVisibility(View.VISIBLE);
                        }else {
                            til_otherReligion.setVisibility(View.GONE);
                        }
                    }

                    if(txtView.getId() == R.id.manage_pwd){
                        if (txtView.getText().toString().trim().equalsIgnoreCase("Yes")){
                            til_pwdType.setVisibility(View.VISIBLE);
                        }else {
                            til_pwdType.setVisibility(View.GONE);
                        }
                    }

                    if(txtView.getId() == R.id.manage_cancer){
                        if (txtView.getText().toString().trim().equalsIgnoreCase("Yes")){
                            til_cancerType.setVisibility(View.VISIBLE);
                        }else {
                            til_cancerType.setVisibility(View.GONE);
                        }
                    }

                    if(txtView.getId() == R.id.manage_deceased){
                        if (txtView.getText().toString().trim().equalsIgnoreCase("Yes")){
                            til_deceasedDate.setVisibility(View.VISIBLE);
                        }else {
                            til_deceasedDate.setVisibility(View.GONE);
                        }
                    }

                    if(txtView.getId() == R.id.manage_menarche){
                        if (txtView.getText().toString().trim().equalsIgnoreCase("Yes")){
                            til_menarcheAge.setVisibility(View.VISIBLE);
                        }else {
                            til_menarcheAge.setVisibility(View.GONE);
                        }
                    }

                    if(txtView.getId() == R.id.manage_newborn){
                        if (txtView.getText().toString().trim().equalsIgnoreCase("Yes")){
                            til_newborn.setVisibility(View.VISIBLE);
                        }else {
                            til_newborn.setVisibility(View.GONE);
                        }
                    }


                    if (txtView.getId() == R.id.manage_barangay) {
                        txtView.setTag(radioButton.getId());
                        txtBrgy = txtView;
                    } else if (txtView.getId() == R.id.manage_education) {
                        txtView.setTag(radioButton.getTag().toString());
                    } else if (txtView.getId() == R.id.manage_income || txtView.getId() == R.id.manage_unmet ||
                            txtView.getId() == R.id.manage_supply || txtView.getId() == R.id.manage_toilet) {
                        txtView.setTag(radioButton.getId());
                    } else if (txtView.getId() == R.id.manage_head) {
                        if (txtView.getText().toString().equalsIgnoreCase("NO")) {
                            updateFields.setVisibility(View.GONE);
                            age = Integer.parseInt(Constants.getAge(txtBday.getText().toString(), Calendar.getInstance()).split(" ")[0]);
                            if ((age < 15 || age > 49) && txtSex.getText().toString().equalsIgnoreCase("Female"))
                            {
                                til_menarche.setVisibility(View.GONE);
                                til_menarcheAge.setVisibility(View.GONE);
                                unmetFrame.setVisibility(View.GONE);
                                til_isPregnantFrame.setVisibility(View.GONE);
                            }

                            ManagePopulationFragment.this.view.findViewById(R.id.layout_relation).setVisibility(View.VISIBLE);
                            txtRelation.setText("Son");
                        } else {
                            updateFields.setVisibility(View.VISIBLE);
                            if (txtSex.getText().toString().equalsIgnoreCase("Female"))
                            {
                                unmetFrame.setVisibility(View.VISIBLE);
                                til_isPregnantFrame.setVisibility(View.VISIBLE);
                                til_menarche.setVisibility(View.VISIBLE);
                            }
                            ManagePopulationFragment.this.view.findViewById(R.id.layout_relation).setVisibility(View.GONE);
                        }
                    } else if (txtView.getId() == R.id.manage_relation) {
                        String relation = txtView.getText().toString();
                        if (males.contains(txtView.getText().toString())) txtSex.setText("Male");
                        else if (females.contains(txtView.getText().toString()))
                            txtSex.setText("Female");
                        else txtSex.setText("");
                    }
                } else {
                    txtView.setText("");
                    txtView.setTag("");
                }

                optionDialog.dismiss();
            }
        });
    }
    private String selectedCheckbox = "";
    public void showChecklistDialog(final int id, final EditText txtView) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.options_dialog, null);
        optionHolder = view.findViewById(R.id.option_holder);
        optionBtn = view.findViewById(R.id.optionBtn);
        selectedCheckbox = "";

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        final RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

        final String[] labels;

        if (id != 0) labels = getResources().getStringArray(id);
        else labels = brgys;

        final LinearLayout.LayoutParams rbParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rbParam.bottomMargin = 5;
        rbParam.topMargin = 5;

        final LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.VERTICAL);
        final CheckBox[] checkBoxes= new CheckBox[labels.length];
        for (int i = 0; i < labels.length; i++) {

            CheckBox cb = new CheckBox(getContext());
            cb.setLayoutParams(rbParam);
            cb.setText(labels[i]);

            checkBoxes[i]=cb;

            View lineView = new View(getContext());
            lineView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            lineView.setLayoutParams(params);

            ll.addView(cb);
            ll.addView(lineView);
        }
        optionHolder.addView(ll);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        final AlertDialog optionDialog = builder.create();
        optionDialog.show();

        optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < labels.length; i++) {
                    if(checkBoxes[i].isChecked()){
                        selectedCheckbox +=(checkBoxes[i].getText().toString().trim() + ", ");
                    }
                }

                if(!selectedCheckbox.equalsIgnoreCase("")) {
                    txtView.setText(selectedCheckbox);
                    txtView.setTag("");

                } else {
                    txtView.setText("");
                    txtView.setTag("");
                }
                optionDialog.dismiss();
            }
        });

    }


//    public void showDengvaxiaDialog() {
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.dengvaxia_dialog, null);
//
//        tvStatus = view.findViewById(R.id.dengvaxia_status);
//        txtFacilityName = view.findViewById(R.id.dengvaxia_facility_name);
//        txtListNumber = view.findViewById(R.id.dengvaxia_list_number);
//        txtDoseScreen = view.findViewById(R.id.dengvaxia_dose_screen);
//        txtDoseDate = view.findViewById(R.id.dengvaxia_dose_date);
//        txtDoseLot = view.findViewById(R.id.dengvaxia_dose_lot);
//        txtDoseBatch = view.findViewById(R.id.dengvaxia_dose_batch);
//        txtDoseExpiration = view.findViewById(R.id.dengvaxia_dose_expiration);
//        txtDoseAefi = view.findViewById(R.id.dengvaxia_dose_aefi);
//        txtRemarks = view.findViewById(R.id.dengvaxia_remarks);
//        dengvaxiaRegisterBtn = view.findViewById(R.id.dengvaxiaBtn);
//        ivPatient = view.findViewById(R.id.dengvaxia_patient_image);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setView(view);
//        builder.show();
//
//        txtDoseScreen.setOnClickListener(this);
//        txtDoseDate.setOnClickListener(this);
//        txtDoseExpiration.setOnClickListener(this);
//        txtDoseAefi.setOnClickListener(this);
//        dengvaxiaRegisterBtn.setOnClickListener(this);
//        ivPatient.setOnClickListener(this);
//
//        Constants.setDateTextWatcher(getContext(), txtDoseDate);
//        Constants.setDateTextWatcher(getContext(), txtDoseExpiration);
//
//        MainActivity.pd = ProgressDialog.show(getContext(), "Loading", "Please wait...", false, false);
//        JSONApi.getInstance(getContext()).getDengvaxiaDetails(Constants.dengvaxiaUrl + "cmd=dose&id="+familyProfile.id);
//    }

//    public void setDengvaxiaDetails(DengvaxiaDetails details){
//        tvStatus.setText("STATUS: " + details.getStatus().toUpperCase());
//        txtFacilityName.setText(details.getFacilityName());
//        txtListNumber.setText(details.getListNumber());
//        txtDoseScreen.setText(details.getDoseScreen());
//        txtDoseDate.setText(details.getDoseDate());
//        txtDoseLot.setText(details.getDoseLot());
//        txtDoseBatch.setText(details.getDoseBatch());
//        txtDoseExpiration.setText(details.getDoseExpiry());
//        txtDoseAefi.setText(details.getDoseAefi());
//        txtRemarks.setText(details.getRemarks());
//
//        txtFacilityName.setEnabled(false);
//        txtListNumber.setEnabled(false);
//        txtDoseScreen.setEnabled(false);
//        txtDoseDate.setEnabled(false);
//        txtDoseLot.setEnabled(false);
//        txtDoseBatch.setEnabled(false);
//        txtDoseExpiration.setEnabled(false);
//        txtDoseAefi.setEnabled(false);
//        txtRemarks.setEnabled(false);
//
//        dengvaxiaRegisterBtn.setVisibility(View.GONE);
//
//        MainActivity.pd.dismiss();
//    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        switch (view.getTag().toString()) {
            case "bday":
                txtBday.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + String.format("%02d", dayOfMonth));

                age = Integer.parseInt(Constants.getAge(txtBday.getText().toString(), Calendar.getInstance()).split(" ")[0]);
                if (((age >= 15 && age <= 49) || addHead) && txtSex.getText().toString().equalsIgnoreCase("Female")) {
                    unmetFrame.setVisibility(View.VISIBLE);
                    til_menarche.setVisibility(View.VISIBLE);
                }
                else {
                    til_menarche.setVisibility(View.GONE);
                    unmetFrame.setVisibility(View.GONE);
                }

                if(age < 5){
                    layout_adult.setVisibility(View.GONE);
                    layout_age5.setVisibility(View.VISIBLE);
                }else {
                    layout_adult.setVisibility(View.VISIBLE);
                    layout_age5.setVisibility(View.GONE);
                }

                break;
            case "dose_date":
                txtDoseDate.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + String.format("%02d", dayOfMonth));
                break;
            case "dose_expiry":
                txtDoseExpiration.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + String.format("%02d", dayOfMonth));
                break;
            case "lmp" :
                txtPregnantDate.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + String.format("%02d", dayOfMonth));
                break;

            case "deceased": txtDeceasedDate.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + String.format("%02d", dayOfMonth));

        }
    }

    public void showProfileCheckerDialog() {
        View checkerDialogView = LayoutInflater.from(getContext()).inflate(R.layout.profile_checker_dialog, null, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(checkerDialogView);

        final EditText txtCheckerFname, txtCheckerMname, txtCheckerLname, txtCheckerSuffix;
        final ListView lvMatchingProfiles;
        final LinearLayout txtFrame, lvFrame;
        TextView btnCheck, btnUpdate, btnNew;

        txtCheckerFname = checkerDialogView.findViewById(R.id.checker_fname);
        txtCheckerMname = checkerDialogView.findViewById(R.id.checker_mname);
        txtCheckerLname = checkerDialogView.findViewById(R.id.checker_lname);
        txtCheckerSuffix = checkerDialogView.findViewById(R.id.checker_suffix);
        lvMatchingProfiles = checkerDialogView.findViewById(R.id.checker_lv);
        lvFrame = checkerDialogView.findViewById(R.id.chercker_lv_frame);
        txtFrame = checkerDialogView.findViewById(R.id.checker_txt_frame);
        btnCheck = checkerDialogView.findViewById(R.id.checker_btnCheck);
        btnUpdate = checkerDialogView.findViewById(R.id.checker_btnUpdate);
        btnNew = checkerDialogView.findViewById(R.id.checker_btnNew);

        final AlertDialog checkerDialog = builder.create();
        checkerDialog.show();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname, mname, lname, suffix;

                fname = txtCheckerFname.getText().toString().trim();
                mname = txtCheckerMname.getText().toString().trim();
                lname = txtCheckerLname.getText().toString().trim();
                suffix = txtCheckerSuffix.getText().toString().trim();

                matchingProfiles = MainActivity.db.getMatchingProfiles(fname, mname, lname, suffix);

                if (matchingProfiles.size() > 0) {
                    ListAdapter adapter = new ListAdapter(getContext(), R.layout.population_dialog_item, matchingProfiles, null, null);
                    lvMatchingProfiles.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    lvFrame.setVisibility(View.VISIBLE);
                    txtFrame.setVisibility(View.GONE);
                } else {
                    txtFname.setText(fname);
                    txtMname.setText(mname);
                    txtLname.setText(lname);
                    txtSuffix.setText(suffix);
                    checkerDialog.dismiss();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lvMatchingProfiles.getCheckedItemPosition() >= 0) {
                    familyProfile = matchingProfiles.get(lvMatchingProfiles.getCheckedItemPosition());
                    setFieldTexts();
                    checkerDialog.dismiss();
                } else
                    Toast.makeText(getContext(), "Please select profile to update.", Toast.LENGTH_SHORT).show();
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFname.setText(txtCheckerFname.getText().toString().trim());
                txtMname.setText(txtCheckerMname.getText().toString().trim());
                txtLname.setText(txtCheckerLname.getText().toString().trim());
                txtSuffix.setText(txtCheckerSuffix.getText().toString().trim());
                checkerDialog.dismiss();
            }
        });
    }

    public void findViewByIds(){
        txtFamilyId = view.findViewById(R.id.manage_familyId);
        txtPhilHealthId = view.findViewById(R.id.manage_philhealthId);
        txtNhtsId = view.findViewById(R.id.manage_nhtsId);
        txtFname = view.findViewById(R.id.manage_fname);
        txtMname = view.findViewById(R.id.manage_mname);
        txtLname = view.findViewById(R.id.manage_lname);
        txtBday = view.findViewById(R.id.manage_bday);
        txtBrgy = view.findViewById(R.id.manage_barangay);
        txtEducation = view.findViewById(R.id.manage_education);
        txtSex = view.findViewById(R.id.manage_sex);
        txtSuffix = view.findViewById(R.id.manage_suffix);
        txtIncome = view.findViewById(R.id.manage_income);
        txtUnmet = view.findViewById(R.id.manage_unmet);
        txtSupply = view.findViewById(R.id.manage_supply);
        txtToilet = view.findViewById(R.id.manage_toilet);
        txtHead = view.findViewById(R.id.manage_head);
        txtRelation = view.findViewById(R.id.manage_relation);

        // UPDATE
        txtDiabetic = view.findViewById(R.id.manage_diabetes);
        txtHypertension = view.findViewById(R.id.manage_hypertension);
        txtPWD = view.findViewById(R.id.manage_pwd);

        txtIsPregnant = view.findViewById(R.id.manage_isPregnant);
        txtPregnantDate = view.findViewById(R.id.manage_pregnantDate);
        til_isPregnantFrame = view.findViewById(R.id.isPregnant_Frame);
        til_pregnantDate = view.findViewById(R.id.pregnantDate_frame);

        //update r
        txtBirthPlace = view.findViewById(R.id.manage_bplace);

        txtContact = view.findViewById(R.id.manage_contact);
        txtHeight = view.findViewById(R.id.manage_height);
        txtWeight = view.findViewById(R.id.manage_weight);


        txtMental = view.findViewById(R.id.manage_mental);
        txtTBDOTS = view.findViewById(R.id.manage_tbdots);
        txtCVD = view.findViewById(R.id.manage_cvd);
        txtCovid = view.findViewById(R.id.manage_covid);

        txtCivil = view.findViewById(R.id.manage_civil);

        txtReligion = view.findViewById(R.id.manage_religion);
        txtOtherReligion = view.findViewById(R.id.manage_other_religion);
        til_otherReligion = view.findViewById(R.id.religion_frame);

        txtCancer = view.findViewById(R.id.manage_cancer);
        txtCancerType = view.findViewById(R.id.manage_cancer_type);
        til_cancerType = view.findViewById(R.id.cancer_frame);

        txtPwdDesc = view.findViewById(R.id.manage_pwd_type);
        til_pwdType = view.findViewById(R.id.pwd_frame);

        txtDecease = view.findViewById(R.id.manage_deceased);
        txtDeceasedDate = view.findViewById(R.id.manage_deceased_date);
        til_deceasedDate = view.findViewById(R.id.deceased_frame);

        txtMenarche  = view.findViewById(R.id.manage_menarche);
        txtMenarcheAge  = view.findViewById(R.id.manage_menarche_age);
        til_menarche  = view.findViewById(R.id.menarche_frame);
        til_menarcheAge  = view.findViewById(R.id.menarche_age_frame);
        layout_adult = view.findViewById(R.id.adult_medication_holder);
        layout_age5 = view.findViewById(R.id.age5_holder);

        txtNewbornScreen = view.findViewById(R.id.manage_newborn);
        txtNewbornText = view.findViewById(R.id.manage_newborn_type);
        til_newborn  = view.findViewById(R.id.newborn_frame);

        txtImmunization  = view.findViewById(R.id.manage_immunization);
        txtNutrition  = view.findViewById(R.id.manage_nutrition);

        updateFields = view.findViewById(R.id.updateFields_holder);
        unmetFrame = view.findViewById(R.id.unmet_frame);
        manageBtn = view.findViewById(R.id.manageBtn);
    }
}
