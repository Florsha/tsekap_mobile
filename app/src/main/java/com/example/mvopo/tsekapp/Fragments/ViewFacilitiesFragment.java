package com.example.mvopo.tsekapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvopo.tsekapp.Helper.ListAdapter;
import com.example.mvopo.tsekapp.MainActivity;
import com.example.mvopo.tsekapp.Model.FacilityModel;
import com.example.mvopo.tsekapp.Model.FamilyProfile;
import com.example.mvopo.tsekapp.R;

import java.util.ArrayList;

public class ViewFacilitiesFragment extends Fragment {
    public View view;
    ListView lv, lvMembers;
    ListAdapter adapter, memAdapter;
    ImageView btnSearch;
    EditText txtSearch;
    ManageFacilityFragment mff;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_layout, container, false);
        lv = view.findViewById(R.id.lv);
        txtSearch = view.findViewById(R.id.list_searchTxt);
        btnSearch = view.findViewById(R.id.list_searchBtn);
        mff=new ManageFacilityFragment();

        ArrayList<FacilityModel> facilityModels = new ArrayList<>();
        FacilityModel model1 = new FacilityModel("1","DOH000000000037924","Adventist Hospital - Cebu Inc.", "AHCI","","","","","","","","","","","","","","","","","","","","","","" );
        FacilityModel model2 = new FacilityModel("2","DOH000000000012345","Romaine Sample Hospital - Cebu Inc.", "AHCI","","","","","","","","","","","","","","","","","","","","","","" );
        facilityModels.add(model1); facilityModels.add(model2);
        adapter = new ListAdapter(getContext(), R.layout.population_item, null, null, null,facilityModels,null);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                View focusedView = getActivity().getCurrentFocus();
                if (focusedView != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                MainActivity.ft = MainActivity.fm.beginTransaction();
                MainActivity.ft.replace(R.id.fragment_container, mff).addToBackStack("").commit();

            }});


        return view;
    }

}
