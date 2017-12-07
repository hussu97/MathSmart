package com.pythagorithm.mathsmartv2.UILayer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pythagorithm.mathsmartv2.AppLogic.Teacher;
import com.pythagorithm.mathsmartv2.R;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Random;

public class reportTeacher extends AppCompatActivity {

    private String topicSet[];
    private Teacher teacher;
    private HashMap<String,Float> vals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_teacher);
        teacher = (Teacher)getIntent().getParcelableExtra("teacher");
        teacher.getTopicScores();
        HashMap<Integer,String> map = new HashMap<>();
        map.put(0,"Algebra");
        map.put(1,"Multiplication");
        map.put(2, "Fractions");
        map.put(3, "SexEd");
        //

        HashMap<String,Float> map2 = new HashMap<>();
        map2.put("Algebra",5.0f);
        map2.put("Multiplication",10.0f);
        map2.put("Fractions",6.0f);
        map2.put("SexEd", 7.0f);

        HashMap<String,Integer>map3=new HashMap<>();
        map3.put("Algebra",4);
        map3.put("Fractions",6);
        map3.put("Multiplication",10);
        showBarChart(map,map2);
        showPieChart(map3);
    }

    public void showPieChart(HashMap<String,Integer> vals){
        Random rnd=new Random();
        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);
        ViewGroup myRoot1 = (ViewGroup) findViewById(R.id.legendLayout);
        float[] values=new float[vals.size()];
        int[]colors= new int[vals.size()];
        topicSet=vals.keySet().toArray(new String[vals.size()]);
        int sum=0;
        for(int i=0;i<vals.size();i++){
            values[i]=(int)vals.values().toArray()[i];
            Log.d("Hussu","Array at:"+i+" value:"+values[i]);
            sum+=values[i];
        }
        for(int i=0;i<vals.size();i++){
            values[i]=(float)values[i]/sum*100;
            Log.d("Hussu","Array at:"+i+" value:"+values[i]);
            colors[i]=Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            mPieChart.addPieSlice(new PieModel(topicSet[i],values[i],colors[i]));
            View inflatedLayout = LayoutInflater.from(this).inflate(R.layout.legend_item, null, false);
            ((TextView) inflatedLayout.findViewById(R.id.colorSquare)).setBackgroundColor(colors[i]);
            ((TextView) inflatedLayout.findViewById(R.id.itemText)).setText(topicSet[i] + "   " + round(values[i],2) + "%");
            inflatedLayout.setPadding(10,5,0,0);
            myRoot1.addView(inflatedLayout);
        }
        mPieChart.startAnimation();
    }

    public void showBarChart(HashMap<Integer,String> secs, HashMap<String,Float> vals){
        BarChart mBarChart = (BarChart) findViewById(R.id.barchart);
        ViewGroup myRoot1 = (ViewGroup) findViewById(R.id.legendBarLayout);
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

    public void backBtnClicked(View v){
        finish();
    }
}
