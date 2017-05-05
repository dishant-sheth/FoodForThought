package com.example.dishant.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Calories extends Fragment {

    CrystalRangeSeekbar rangeSeekbar;
    TextView start, end;
    Button apply;
    String min, max;

    public Calories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calories, container, false);

        rangeSeekbar = (CrystalRangeSeekbar) view.findViewById(R.id.calorie_range_bar);
        start = (TextView) view.findViewById(R.id.start_range);
        end = (TextView) view.findViewById(R.id.end_range);
        apply = (Button) view.findViewById(R.id.apply);

        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                start.setText(String.valueOf(minValue));
                end.setText(String.valueOf(maxValue));
            }
        });

        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                min = String.valueOf(minValue);
                max = String.valueOf(maxValue);
            }
        });


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

}
