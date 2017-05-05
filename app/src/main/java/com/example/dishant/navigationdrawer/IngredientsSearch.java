package com.example.dishant.navigationdrawer;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsSearch extends Fragment{
    @SuppressWarnings("unused")

    private static final String LOG_TAG = IngredientsSearch.class.getSimpleName();
    RecyclerView recyclerView;
    private IngreAdapter ingreAdapter;
    LinearLayoutManager linearLayoutManager;
    private ActionMode mActionMode;
    List<IngreData> data;
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

            MenuInflater menuInflater = actionMode.getMenuInflater();
            menuInflater.inflate(R.menu.selected_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    };



    public IngredientsSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ingre_search, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.ingreList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        data = new ArrayList<>();

        ingreAdapter = new IngreAdapter(getActivity(), getData());
        recyclerView.setAdapter(ingreAdapter);

        ingreAdapter.setMultipleSelectAdapterCallback(new IngreAdapter.MultipleSelectAdapterCallback() {
            @Override
            public void itemClicked(int count, List<Integer> selectedItemId) {
                Log.d(LOG_TAG, "Clicked");
                if (count ==0 & mActionMode != null) {
                    mActionMode.finish();
                } else {
                    mActionMode = getActivity().startActionMode(mActionModeCallback);
                    mActionMode.setTitle("Selected Item #: " + count);

                }
            }
        });


    }

    public List<IngreData> getData(){

        String[] name = {"Onion", "Tomato", "Garlic" , "Ginger", "Potato", "Cheese", "Peas", "Coconut", "Beetroot", "Oil"};

        String image = "https://6d1bdf0e0edb084eae10-5bfabe5484726969ac662c6d377e2f3c.ssl.cf2.rackcdn.com/thumbs/thumbnail/uploads/old/DPTTcfMeW4.jpg";

        String second = "Ingredient info here !";


        for(int i=0; i<name.length; i++){

            IngreData ingreData = new IngreData();
            ingreData.ingreName = name[i];
            ingreData.ingreImage = image;
            ingreData.secondaryText = second;
            ingreData.setmId(i);
            ingreData.setmChecked(false);
            ingreData.setmActivateExpansion(false);

            data.add(ingreData);

        }

        return data;
    }


}
