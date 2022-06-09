package com.example.mvopo.tsekapp.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvopo.tsekapp.MainActivity;
import com.example.mvopo.tsekapp.Model.AffiliatedFacilitiesModel;
import com.example.mvopo.tsekapp.Model.Constants;
import com.example.mvopo.tsekapp.Model.SpecialistModel;
import com.example.mvopo.tsekapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ManageSpecialistFragment extends Fragment implements View.OnClickListener {
    View view;
    ScrollView optionHolder;
    Button optionBtn, updateBtn, addFacility;
    EditText txtFacility, txtSpecialization, txtContact, txtEmail, txtSchedule, txtFee;
    LinearLayout facilitiesHolder;
    String fName, mName, lName, username;
    EditText txtFName, txtMName, txtLName;

    Boolean toUpdate;
    SpecialistModel specialist;

    ArrayList<AffiliatedFacilitiesModel> facilities = new ArrayList<>();
    ArrayList<AffiliatedFacilitiesModel> allFacilities = new ArrayList<>();
    ArrayList<String> removedFacilityIDs = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage_specialist, container, false);
        findViewByIds();

        toUpdate = getArguments().getBoolean("toUpdate");
        specialist = getArguments().getParcelable("specialist");

        /* for testing only
        allFacilities = MainActivity.db.getAffiliatedFacilities("");
        Log.e("specialist", ""+allFacilities.size());
       for(AffiliatedFacilitiesModel model : allFacilities){
            Log.e("specialist", "aff facility id "+ model.id);
        }*/

        if(toUpdate){
            facilities = MainActivity.db.getAffiliatedFacilities(specialist.username);
            setFieldTexts();
        }else{
            updateBtn.setText("ADD SPECIALIST");
            updateBtn.setTag("add");
        }

        updateBtn.setOnClickListener(this);
        addFacility.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.specialist_add: //add affiliated facility
                addFacility();
                break;

            case R.id.specialist_remove: //remove affiliated facility
                Log.e("affiliated onclick", "tag: "+v.getTag());
                txtSpecialization = facilitiesHolder.getChildAt(Integer.parseInt(v.getTag().toString())).findViewById(R.id.specialist_specialization);
                if(txtSpecialization.getTag()!=null){
                    if(!txtSpecialization.getTag().toString().isEmpty()){
                        removedFacilityIDs.add(txtSpecialization.getTag().toString().trim());
                        Log.e("specialist", "specialization tag removedId: " + removedFacilityIDs);
                    }
                }
                removeFacility(v.getTag().toString());
                break;

            case R.id.specialist_facility:
                //showOptionDialog(R.array.accreditation_status, null, (EditText)v.findViewById(R.id.specialist_facility));
                break;

            case R.id.specialistBtn: //update specialist
                Calendar c = Calendar.getInstance();
                DateFormat df = new SimpleDateFormat("yyMMddHHmm"); // last 2digit of Year, Month, Day, Hour24, Minutes
                String code = df.format(Calendar.getInstance().getTime());

                fName = txtFName.getText().toString().trim();
                mName= txtMName.getText().toString().trim();
                lName= txtLName.getText().toString().trim();

                if(toUpdate){
                    specialist = new SpecialistModel(specialist.id, specialist.username, fName, mName, lName);
                    MainActivity.db.updateSpecialist(specialist);
                }else { //add new
                    username = fName.substring(0,1).toLowerCase(Locale.ROOT) + lName.toLowerCase(Locale.ROOT) + code;
                    specialist = new SpecialistModel("",username, fName, mName, lName);
                    MainActivity.db.addSpecialist(specialist);
                }

                int child = facilitiesHolder.getChildCount();
                Log.e("specialist", " count of affiliated facility child layout: " + child);

                for(int x=0; x<child ; x++){
                    View view = facilitiesHolder.getChildAt(x); //reading
                    txtFacility = view.findViewById(R.id.specialist_facility);
                    txtSpecialization = view.findViewById(R.id.specialist_specialization);
                    txtContact = view.findViewById(R.id.specialist_contact);
                    txtEmail = view.findViewById(R.id.specialist_email);
                    txtSchedule = view.findViewById(R.id.specialist_sched);
                    txtFee = view.findViewById(R.id.specialist_fee);

                    Log.e("specialist", " facility: " + txtFacility.getText().toString() +
                                " specialization: " + txtSpecialization.getText().toString() +
                                " Contact: " + txtContact.getText().toString() +
                                " Email: " + txtEmail.getText().toString() +
                                " Schedule: " + txtSchedule.getText().toString() +
                                " Fee: " + txtFee.getText().toString());

                    if(txtSpecialization.getTag()!=null){
                        if(!txtSpecialization.getTag().toString().isEmpty()){
                            Log.e("specialist", "specialization tag toUpdate" + txtSpecialization.getTag().toString());
                            AffiliatedFacilitiesModel facility = new AffiliatedFacilitiesModel(""+txtSpecialization.getTag().toString(), specialist.username, txtFacility.getText().toString(),txtSpecialization.getText().toString(),
                                    txtContact.getText().toString(), txtEmail.getText().toString(), txtSchedule.getText().toString(),txtFee.getText().toString());
                            MainActivity.db.updateAffiliatedFacility(facility);
                        }
                    }else {
                        AffiliatedFacilitiesModel facility = new AffiliatedFacilitiesModel("", specialist.username, txtFacility.getText().toString(),txtSpecialization.getText().toString(),
                                txtContact.getText().toString(), txtEmail.getText().toString(), txtSchedule.getText().toString(),txtFee.getText().toString());
                            MainActivity.db.addAffiliatedFacility(facility);
                    }
                }
                for(String s : removedFacilityIDs){
                    MainActivity.db.deleteAffiliatedFacilityById(s);
                }
                MainActivity.fm.popBackStack();
                break;
        }
    }

    public void showOptionDialog(final int id, final List<String> list, final EditText txtView) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.options_dialog, null);
        optionHolder = view.findViewById(R.id.option_holder);
        optionBtn = view.findViewById(R.id.optionBtn);

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

        String[] labels;

        if (id != 0) labels = getResources().getStringArray(id);
        else labels = list.toArray(new String[0]) ;

        final RadioGroup radioGroup = new RadioGroup(getContext());

        LinearLayout.LayoutParams rbParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rbParam.bottomMargin = 5;
        rbParam.topMargin = 5;

        for (int i = 0; i < labels.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setLayoutParams(rbParam);
            radioButton.setText(labels[i]);

            View lineView = new View(getContext());
            lineView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            lineView.setLayoutParams(params);


            radioGroup.addView(radioButton);
            radioGroup.addView(lineView);
        }

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
                    txtView.setTag(radioButton.getId());
                } else {
                    txtView.setText("");
                    txtView.setTag("");
                }
                optionDialog.dismiss();
            }
        });
    }

    String selectedCheckbox = ""; String[] labels = {};
    public void showScheduleDialog(final int id, final EditText txtView) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.options_dialog, null);
        optionHolder = view.findViewById(R.id.option_holder);
        optionBtn = view.findViewById(R.id.optionBtn);
        selectedCheckbox = "";

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        final RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

        if (id != 0) labels = getResources().getStringArray(id);

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

    public void addFacility(){
        View affiliatedFacilityView = LayoutInflater.from(getContext()).inflate(R.layout.specialist_facilities, null);
        affiliatedFacilityView.findViewById(R.id.specialist_remove).setOnClickListener(this);
        affiliatedFacilityView.findViewById(R.id.specialist_facility).setOnClickListener(this);
        Constants.setMoneyTextWatcher(getContext(), affiliatedFacilityView.findViewById(R.id.specialist_fee));

        facilitiesHolder.addView(affiliatedFacilityView);
        changeChildTags();
    }

    public void changeChildTags(){
        for (int pos = 0;  pos<facilitiesHolder.getChildCount(); pos++){
            Button btn = facilitiesHolder.getChildAt(pos).findViewById(R.id.specialist_remove);
            btn.setTag(pos);
        }
    }

    public void removeFacility(String index){
        facilitiesHolder.removeViewAt(Integer.parseInt(index));
        changeChildTags();
    }

    public void readFacilities(){
        int child = facilitiesHolder.getChildCount();
        Log.e("affiliated", " count: " + child);

        for(int x=0; x<child ; x++){
            View view = facilitiesHolder.getChildAt(x);  //reading
        }
    }

    public void setFieldTexts(){
        Log.e("specialist", "setFieldTexts specialist id: " + specialist.id);
        txtFName.setText(specialist.fname);
        txtMName.setText(specialist.mname);
        txtLName.setText(specialist.lname);

        Log.e("specialist","msf, facility count: " + facilities.size());
        for(AffiliatedFacilitiesModel model : facilities){
            View affiliatedFacilityView = LayoutInflater.from(getContext()).inflate(R.layout.specialist_facilities, null);

            txtFacility = affiliatedFacilityView.findViewById(R.id.specialist_facility);
            txtSpecialization = affiliatedFacilityView.findViewById(R.id.specialist_specialization);
            txtContact = affiliatedFacilityView.findViewById(R.id.specialist_contact);
            txtEmail = affiliatedFacilityView.findViewById(R.id.specialist_email);
            txtSchedule = affiliatedFacilityView.findViewById(R.id.specialist_sched);
            txtFee = affiliatedFacilityView.findViewById(R.id.specialist_fee);

            //txtFacility.setOnClickListener(this);
            affiliatedFacilityView.findViewById(R.id.specialist_remove).setOnClickListener(this);
            Constants.setMoneyTextWatcher(getContext(), txtFee);

            facilitiesHolder.addView(affiliatedFacilityView);

            txtFacility.setText(model.facility_id);
            txtSpecialization.setText(model.specialization);
            txtSpecialization.setTag(""+model.id);
            txtContact.setText(model.contact);
            txtEmail.setText(model.email);
            txtSchedule.setText(model.schedule);
            txtFee.setText(model.fee);
            changeChildTags();

        }
    }

    public void findViewByIds(){
        updateBtn = view.findViewById(R.id.specialistBtn);
        txtFName = view.findViewById(R.id.specialist_fname);
        txtMName = view.findViewById(R.id.specialist_mname);
        txtLName = view.findViewById(R.id.specialist_lname);

        facilitiesHolder = view.findViewById(R.id.specialist_holder1);
        addFacility = view.findViewById(R.id.specialist_add);

    }


}
