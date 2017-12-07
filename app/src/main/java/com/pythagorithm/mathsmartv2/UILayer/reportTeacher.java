package com.pythagorithm.mathsmartv2.UILayer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pythagorithm.mathsmartv2.R;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class reportTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_teacher);


        HashMap<Integer,String> map = new HashMap<>();
        map.put(0,"Algebra");
        map.put(1,"Multiplication");
        map.put(2, "Fractions");
        map.put(3, "SexEd");

        HashMap<String,Float> map2 = new HashMap<>();
        map2.put("Algebra",5.0f);
        map2.put("Multiplication",10.0f);
        map2.put("Fractions",6.0f);
        map2.put("SexEd", 7.0f);

        showBarChart(map,map2);
    }

    private void showPieChart(HashMap<String,Integer> vals){
        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);
        ViewGroup myRoot1 = (ViewGroup) findViewById(R.id.legendLayout);
        int alg = vals.get("Algebra");
        int mult = vals.get("Multiplication");
        int frac = vals.get("Fractions");
        int sum = alg + mult + frac;
        float algPCNT = (float) alg/sum * 100;
        float multPCNT = (float) mult/sum * 100;
        float fracPCNT = (float) frac/sum * 100;

        mPieChart.addPieSlice(new PieModel("Algebra", alg , Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("Multiplication", mult , Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Fractions", frac , Color.parseColor("#FED70E")));

        View inflatedLayout = LayoutInflater.from(this).inflate(R.layout.legend_item, null, false);
        ((TextView) inflatedLayout.findViewById(R.id.colorSquare)).setBackgroundColor(Color.parseColor("#FE6DA8"));
        ((TextView) inflatedLayout.findViewById(R.id.itemText)).setText("Algebra" + "   " + round(algPCNT,2) + "%");
        inflatedLayout.setPadding(10,5,0,0);
        View inflatedLayout2 = LayoutInflater.from(this).inflate(R.layout.legend_item, null, false);
        ((TextView) inflatedLayout2.findViewById(R.id.colorSquare)).setBackgroundColor(Color.parseColor("#56B7F1"));
        ((TextView) inflatedLayout2.findViewById(R.id.itemText)).setText("Multiplication" + "   " + round(multPCNT,2) + "%");
        inflatedLayout2.setPadding(10,5,0,0);

        View inflatedLayout3 = LayoutInflater.from(this).inflate(R.layout.legend_item, null, false);
        ((TextView) inflatedLayout3.findViewById(R.id.colorSquare)).setBackgroundColor(Color.parseColor("#FED70E"));
        ((TextView) inflatedLayout3.findViewById(R.id.itemText)).setText("Fractions" + "   " + round(fracPCNT, 2) + "%");
        inflatedLayout3.setPadding(10,5,0,5);

        myRoot1.addView(inflatedLayout);
        myRoot1.addView(inflatedLayout2);
        myRoot1.addView(inflatedLayout3);
        mPieChart.startAnimation();
    }

    private void showBarChart(HashMap<Integer,String> secs, HashMap<String,Float> vals){
        BarChart mBarChart = (BarChart) findViewById(R.id.barchart);
        ViewGroup myRoot1 = (ViewGroup) findViewById(R.id.legendBarLayout);
        ArrayList<Float> scores = new ArrayList<>();
        int sections = secs.size();
        int color;
        Random rnd = new Random();

        for (int i = 0; i<sections; i++){
            float score = vals.get(secs.get(i));
            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            mBarChart.addBar(new BarModel(score, color));

            View inflatedLayout = LayoutInflater.from(this).inflate(R.layout.legend_item, null, false);
            ((TextView) inflatedLayout.findViewById(R.id.colorSquare)).setBackgroundColor(color);
            ((TextView) inflatedLayout.findViewById(R.id.itemText)).setText(secs.get(i));
            inflatedLayout.setPadding(10,5,0,0);

            myRoot1.addView(inflatedLayout);
        }

        mBarChart.startAnimation();
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
