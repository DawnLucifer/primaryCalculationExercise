package ch4;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ChartGenerator {
    public static String[] mode = {"mix", "add", "sub"};
    public static String recentFileName = "recent.png";
    public static String statisticsFileName = "statistics.png";

    public static JFreeChart getBarChart(BarChartData[] barChartData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (BarChartData data : barChartData) {
            dataset.setValue(data.getComplete(), mode[data.getMode()], data.getFormatDate());
        }
        JFreeChart chart = ChartFactory.createBarChart("Exercise Recently",//标题
                "Date",//目录轴的显示标签
                "Complete",//数值的显示标签
                dataset,//数据
                PlotOrientation.VERTICAL,//图标方向  水平/垂直
                true,//是否显示图例
                false,//是否生成工具
                false); //

        try {
            File file = new File(recentFileName);
            ChartUtils.saveChartAsPNG(file, chart, 500, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chart;
    }

    public static JFreeChart getLineChart(BarChartData[] barChartData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (BarChartData data : barChartData) {
            dataset.setValue(data.getComplete(), mode[data.getMode()], data.getFormatDate());
        }
        JFreeChart chart = ChartFactory.createLineChart("",//标题
                "",//目录轴的显示标签
                "",//数值的显示标签
                dataset,//数据
                PlotOrientation.VERTICAL,//图标方向  水平/垂直
                false,//是否显示图例
                false,//是否生成工具
                false); //
        CategoryPlot plot = chart.getCategoryPlot();

        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlinePaint(Color.white);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 71, 160));      //头一个出现的折线的颜色
        renderer.setSeriesPaint(1, new Color(204, 51, 0));
        renderer.setSeriesPaint(2, new Color(0, 153, 0));

        renderer.setSeriesStroke(0, new BasicStroke(3F));//设置线条粗细
        renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0F));//设置圆点半径

        renderer.setUseFillPaint(true);//开启填充色


        CategoryAxis categoryAxis = plot.getDomainAxis();   //横轴属性
        categoryAxis.setTickLabelFont(new Font("Century Gothic", Font.PLAIN, 14));   //横轴字体
        categoryAxis.setMaximumCategoryLabelLines(2);   //横轴显示行数

        try {
            File file = new File(recentFileName);
            ChartUtils.saveChartAsPNG(file, chart, 500, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chart;
    }

    public static JFreeChart getPieChart(PieChartData pieChartData) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("right", pieChartData.getRight());
        dataset.setValue("wrong", pieChartData.getWrong());
        JFreeChart chart = ChartFactory.createPieChart(
                "",// 图表标题
                dataset,//数据集，即要显示在图表上的数据
                false,//是否显示图例
                false,//是否显示提示
                false//是否生成URL连接
        );
        PiePlot plot = (PiePlot) chart.getPlot();

        setPiePlot(plot);

        try {
            File file = new File(statisticsFileName);
            ChartUtils.saveChartAsPNG(file, chart, 350, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chart;
    }

    public static JFreeChart getRingChart(PieChartData pieChartData) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("right", pieChartData.getRight());
        dataset.setValue("wrong", pieChartData.getWrong());
        JFreeChart chart = ChartFactory.createRingChart(
                "",// 图表标题
                dataset,//数据集，即要显示在图表上的数据
                false,//是否显示图例
                false,//是否显示提示
                false//是否生成URL连接
        );
        RingPlot plot = (RingPlot) chart.getPlot();
        setPiePlot(plot);
        plot.setLabelGenerator(null);
        plot.setSeparatorsVisible(true);
        plot.setSeparatorPaint(Color.WHITE);
        plot.setSectionDepth(0.1);
        plot.setStartAngle(90);

        try {
            File file = new File(statisticsFileName);
            ChartUtils.saveChartAsPNG(file, chart, 350, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chart;
    }

    private static void setPiePlot(PiePlot plot) {
        plot.setBackgroundPaint(Color.white);
        plot.setSectionPaint("right", new Color(0, 153, 0));
        plot.setSectionPaint("wrong", new Color(255, 255, 255));
        plot.setLabelBackgroundPaint(Color.white);  //标签底色
        plot.setIgnoreZeroValues(true);     //忽略0项
        plot.setOutlinePaint(Color.WHITE); // 设置绘图面板外边的填充颜色
        plot.setShadowPaint(Color.WHITE); // 设置绘图面板阴影的填充颜色
        plot.setLabelOutlinePaint(Color.white);
        plot.setLabelShadowPaint(Color.white);
        plot.setLabelFont(new Font("Century Gothic", Font.PLAIN, 12));
    }

    public static void main(String[] args) {
        BarChartData[] barChartData = XMLFactory.getBarChartData();
        getLineChart(barChartData);

//        PieChartData pieChartData = XMLFactory.getPieChartData();
//        getPieChart(pieChartData);

        PieChartData pieChartData = XMLFactory.getPieChartData();
        getRingChart(pieChartData);
    }
}
