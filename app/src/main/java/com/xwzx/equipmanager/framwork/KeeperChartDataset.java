package com.xwzx.equipmanager.framwork;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * T  代表温度
 * H  代表湿度
 */
public class KeeperChartDataset {

    private LineChart mChart;
    private ArrayList<Entry> t1List = new ArrayList<>();
    private ArrayList<Entry> t2List = new ArrayList<>();
    private ArrayList<Entry> h1List = new ArrayList<>();
    private ArrayList<Entry> h2List = new ArrayList<>();
    private ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    private final Context context;
    private List<String> mXaxisList;
    private List<String> mYaxisList;
    private ArrayList<LineDataSet> TLineDataSetList = new ArrayList<>();
    private ArrayList<LineDataSet> HLineDataSetList = new ArrayList<>();

    private static final String TLabel = "温度";
    private static final String HLabel = "湿度";
    public KeeperChartDataset(LineChart lineChart) {
        this.mChart = lineChart;
        this.context = mChart.getContext();
        //初始化轴线
        this.getXaxisList();
        this.getYaxisList();
        this.initAxis(mChart);
        //设置Chat属性
        this.initChatProperty();
        //初始化测试数据
        this.addH();
        this.addT();

        this.createDataSetList();
        //设置数据
        this.setLineDataSetToChat();
    }

    private void setLineDataSetToChat() {
        dataSets.addAll(TLineDataSetList); // add the datasets
        dataSets.addAll(HLineDataSetList);
        LineData data = new LineData(dataSets);
        this.mChart.setData(data);
        this.mChart.invalidate();
    }

    private void initChatProperty(){
        mChart.getDescription().setEnabled(false);
        mChart.setNoDataText("暂无数据");
        mChart.setScaleEnabled(false);
        mChart.setScaleXEnabled(false);
        mChart.setDragEnabled(true);
        mChart.getLegend().setEnabled(false);
        mChart.setDoubleTapToZoomEnabled(false);
//        mChart.setBackgroundColor(this.context.getColor(R.color.transparent));
//        Drawable drawable = ContextCompat.getDrawable(this.context, R.drawable.chart_bg_shape);
//        mChart.setBackground(drawable);
//        mChart.setNoDataTextColor(Color.WHITE);
        mChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
    }

    private static final int borderColor = 0xFF333333;

    private void initAxis(LineChart chart) {
        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setAxisMinimum(0f);//设置x轴的最小值
        xAxis.setAxisMaximum(24f);//设置最大值
        xAxis.setGridColor(this.context.getColor(R.color.transparent));
        xAxis.setTextColor(context.getColor(R.color.light6_blue));//设置字体颜色
        xAxis.setLabelCount(10);
        xAxis.setTextSize(8f);//设置字体
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value < mXaxisList.size()) {
                    return mXaxisList.get((int) value);
                }
                return "24:00";
            }
        });

        YAxis yLAxis = chart.getAxisLeft();
        yLAxis.setEnabled(true);
        yLAxis.setDrawLabels(true);
        yLAxis.setDrawAxisLine(true);
        yLAxis.setDrawGridLines(true);
        yLAxis.setGridColor(this.context.getColor(R.color.transparent));
        yLAxis.setTextColor(context.getColor(R.color.light6_blue));
        yLAxis.setAxisMaximum(30f);
        yLAxis.setAxisMinimum(0f);
        yLAxis.setLabelCount(10);
        yLAxis.setTextSize(8f);
        yLAxis.setZeroLineColor(Color.TRANSPARENT);
        yLAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return mYaxisList.get((int) value);
            }
        });
        YAxis yRLAxis = chart.getAxisRight();
        yRLAxis.setEnabled(false);
    }

    private void createDataSetList() {
        TLineDataSetList.add(createDataSet(TLabel,t1List,R.color.light1_yellow,R.drawable.tline_shape));
        TLineDataSetList.add(createDataSet(TLabel,t2List,R.color.light21_blue,R.drawable.hline_shape));

        HLineDataSetList.add(createDataSet(TLabel,h1List,R.color.light3_blue,R.drawable.hline_shape));
        HLineDataSetList.add(createDataSet(TLabel,h2List,R.color.light1_yellow,R.drawable.tline_shape));
    }

    //创建DataSet工厂
    private LineDataSet createDataSet(String label,ArrayList<Entry> dataList, int dataSetColor, int drawable) {
        LineDataSet dataSet = new LineDataSet(dataList, label);
        dataSet.setColor(this.context.getColor(dataSetColor));//R.color.light_yellow
        dataSet.setCircleColor(Color.WHITE);
        dataSet.setValueTextSize(0f);
        dataSet.setValueTextColor(this.context.getColor(R.color.transparent));
//        tSet.setValueTextColor(Color.BLACK);
        dataSet.setLineWidth(1f);//设置线宽
        dataSet.setCircleRadius(1f);//设置焦点圆心的大小
        dataSet.enableDashedHighlightLine(10f, 0f, 0f);//点击后的高亮线的显示样式
        dataSet.setHighlightLineWidth(1f);//设置点击交点后显示高亮线宽
        dataSet.setHighlightEnabled(true);//是否禁用点击高亮线
        dataSet.setHighLightColor(this.context.getColor(R.color.light22_blue));//设置点击交点后显示交高亮线的颜色
        dataSet.setValueTextSize(9f);//设置显示值的文字大小
        dataSet.setDrawFilled(true);//设置禁用范围背景填充
        Drawable tDrawable = ContextCompat.getDrawable(this.context, drawable);// R.drawable.tline_shape
        dataSet.setFillDrawable(tDrawable);
        return dataSet;
    }


    public void showT() {
        dataSets.clear();
        dataSets.addAll(TLineDataSetList);
        this.mChart.notifyDataSetChanged();
        this.mChart.invalidate();
    }

    public void showH() {
        dataSets.clear();
        dataSets.addAll(HLineDataSetList);
        this.mChart.notifyDataSetChanged();
        this.mChart.invalidate();
    }

    //添加温度
    public void addT() {
        t1List.add(new Entry(0, 3));
        t1List.add(new Entry(1, 5));
        t1List.add(new Entry(2, 6));
        t1List.add(new Entry(3, 7));
        t1List.add(new Entry(4, 8));
        t1List.add(new Entry(5, 9));
        t1List.add(new Entry(6, 10));
        t1List.add(new Entry(7, 11));
        t1List.add(new Entry(8, 12));
        t1List.add(new Entry(9, 13));
        t1List.add(new Entry(10, 14));
        t1List.add(new Entry(11, 15));
        t1List.add(new Entry(12, 16));
        t1List.add(new Entry(13, 17));
        t1List.add(new Entry(14, 22));
        t1List.add(new Entry(15, 23));
        t1List.add(new Entry(16, 25));
        t1List.add(new Entry(17, 27));
        t1List.add(new Entry(18, 29));
        t1List.add(new Entry(19, 27));
        t1List.add(new Entry(20, 26));
        t1List.add(new Entry(21, 29));
        t1List.add(new Entry(22, 26));
        t1List.add(new Entry(23, 28));
        t1List.add(new Entry(24, 25));


        t2List.add(new Entry(0, 13));
        t2List.add(new Entry(1, 15));
        t2List.add(new Entry(2, 16));
        t2List.add(new Entry(3, 17));
        t2List.add(new Entry(4, 18));
        t2List.add(new Entry(5, 19));
        t2List.add(new Entry(6, 20));
        t2List.add(new Entry(7, 21));
        t2List.add(new Entry(8, 22));
        t2List.add(new Entry(9, 23));
        t2List.add(new Entry(10, 24));
        t2List.add(new Entry(11, 22));
        t2List.add(new Entry(12, 20));
        t2List.add(new Entry(13, 17));
        t2List.add(new Entry(14, 18));
        t2List.add(new Entry(15, 19));
        t2List.add(new Entry(16, 20));
        t2List.add(new Entry(17, 22));
        t2List.add(new Entry(18, 19));
        t2List.add(new Entry(19, 17));
        t2List.add(new Entry(20, 16));
        t2List.add(new Entry(21, 19));
        t2List.add(new Entry(22, 16));
        t2List.add(new Entry(23, 18));
        t2List.add(new Entry(24, 15));
    }

    //添加湿度
    public void addH() {
        h1List.add(new Entry(0, 20));
        h1List.add(new Entry(1, 25));
        h1List.add(new Entry(2, 26));
        h1List.add(new Entry(3, 27));
        h1List.add(new Entry(4, 28));
        h1List.add(new Entry(5, 29));
        h1List.add(new Entry(6, 27));
        h1List.add(new Entry(7, 26));
        h1List.add(new Entry(8, 25));
        h1List.add(new Entry(9, 23));
        h1List.add(new Entry(10, 22));
        h1List.add(new Entry(11, 21));
        h1List.add(new Entry(12, 18));
        h1List.add(new Entry(13, 17));
        h1List.add(new Entry(14, 16));
        h1List.add(new Entry(15, 15));
        h1List.add(new Entry(16, 14));
        h1List.add(new Entry(17, 13));
        h1List.add(new Entry(18, 12));
        h1List.add(new Entry(19, 12));
        h1List.add(new Entry(20, 11));
        h1List.add(new Entry(21, 13));
        h1List.add(new Entry(22, 16));
        h1List.add(new Entry(23, 19));
        h1List.add(new Entry(24, 22));


        h2List.add(new Entry(0, 10));
        h2List.add(new Entry(1, 15));
        h2List.add(new Entry(2, 16));
        h2List.add(new Entry(3, 17));
        h2List.add(new Entry(4, 18));
        h2List.add(new Entry(5, 19));
        h2List.add(new Entry(6, 17));
        h2List.add(new Entry(7, 16));
        h2List.add(new Entry(8, 15));
        h2List.add(new Entry(9, 13));
        h2List.add(new Entry(10, 12));
        h2List.add(new Entry(11, 11));
        h2List.add(new Entry(12, 16));
        h2List.add(new Entry(13, 19));
        h2List.add(new Entry(14, 22));
        h2List.add(new Entry(15, 23));
        h2List.add(new Entry(16, 24));
        h2List.add(new Entry(17, 23));
        h2List.add(new Entry(18, 22));
        h2List.add(new Entry(19, 25));
        h2List.add(new Entry(20, 23));
        h2List.add(new Entry(21, 21));
        h2List.add(new Entry(22, 29));
        h2List.add(new Entry(23, 29));
        h2List.add(new Entry(24, 26));
    }

    private List<String> getXaxisList() {
        mXaxisList = new ArrayList<>();
        mXaxisList.add("00:00");
        mXaxisList.add("01:00");
        mXaxisList.add("02:00");
        mXaxisList.add("03:00");
        mXaxisList.add("04:00");
        mXaxisList.add("05:00");
        mXaxisList.add("06:00");
        mXaxisList.add("07:00");
        mXaxisList.add("08:00");
        mXaxisList.add("09:00");
        mXaxisList.add("10:00");
        mXaxisList.add("11:00");
        mXaxisList.add("12:00");
        mXaxisList.add("13:00");
        mXaxisList.add("14:00");
        mXaxisList.add("15:00");
        mXaxisList.add("16:00");
        mXaxisList.add("17:00");
        mXaxisList.add("18:00");
        mXaxisList.add("19:00");
        mXaxisList.add("20:00");
        mXaxisList.add("21:00");
        mXaxisList.add("22:00");
        mXaxisList.add("23:00");
        return mXaxisList;
    }

    private void getYaxisList() {
        mYaxisList = new ArrayList<>();
        mYaxisList.add("0 ℃");
        mYaxisList.add("1 ℃");
        mYaxisList.add("2 ℃");
        mYaxisList.add("3 ℃");
        mYaxisList.add("4 ℃");
        mYaxisList.add("5 ℃");
        mYaxisList.add("6 ℃");
        mYaxisList.add("7 ℃");
        mYaxisList.add("8 ℃");
        mYaxisList.add("9 ℃");
        mYaxisList.add("10 ℃");
        mYaxisList.add("11 ℃");
        mYaxisList.add("12 ℃");
        mYaxisList.add("13 ℃");
        mYaxisList.add("14 ℃");
        mYaxisList.add("15 ℃");
        mYaxisList.add("16 ℃");
        mYaxisList.add("17 ℃");
        mYaxisList.add("18 ℃");
        mYaxisList.add("19 ℃");
        mYaxisList.add("20 ℃");
        mYaxisList.add("21 ℃");
        mYaxisList.add("22 ℃");
        mYaxisList.add("23 ℃");
        mYaxisList.add("24 ℃");
        mYaxisList.add("25 ℃");
        mYaxisList.add("26 ℃");
        mYaxisList.add("27 ℃");
        mYaxisList.add("28 ℃");
        mYaxisList.add("29 ℃");
        mYaxisList.add("30 ℃");
    }
}
