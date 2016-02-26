package nprot.vista;

import NProt.accesoDatos.ManejoBD;
import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a dual axis chart
 * based on data from two {@link CategoryDataset} instances.
 *
 */
public class organismEDBar extends JFrame {

    String sbBarType;
    String top;
    String actualBar;
    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    LinkedList<String[]> organismResult;
    LinkedList<Float> tmpForCysteines = new LinkedList();
    LinkedList<Float> tmpForEvolutionary = new LinkedList();

    ManejoBD nuevo = new ManejoBD();
    String titles = "";
    String sbAxiName = "";
    CategoryDataset dataset1 = null;
    XYDataset data1 = null;

    public organismEDBar(
            String sbBarType,
            String title,
            String top,
            HashMap aminosResult,
            LinkedList<String> possibleCombinations
    ) {
        super(title);
        titles = title;
        this.sbBarType = sbBarType;
        this.top = top;
        //JOptionPane.showMessageDialog(null,sbBarType);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        String sbAxiName = "";
        if (sbBarType.equalsIgnoreCase("nplets")) {
            actualBar = "Nplets";
            sbAxiName = "Nplets Bars";
            dataset1 = datasetForNplets(
                    title,
                    aminosResult,
                    possibleCombinations
            );

            createSingleWindow();
        } else if (sbBarType.equalsIgnoreCase("aminocount")) {
            actualBar = "Count";
            sbAxiName = "Aminos count";
            dataset1 = datasetAminoCount(
                    title,
                    aminosResult,
                    possibleCombinations
            );

            createSingleWindow();
        }
    }

    public organismEDBar(
            String sbBarType,
            String title,
            String top
    ) {
        super(title);
        titles = title;
        this.sbBarType = sbBarType;
        this.top = top;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        if (sbBarType.equalsIgnoreCase("ED")) {
            sbAxiName = "Evoltionary distances";
            dataset1 = datasetForED(title);
            actualBar = "Organisms";
            createSingleWindow();

        } else if (sbBarType.equalsIgnoreCase("CI")) {
            sbAxiName = "Cysteine %";
            dataset1 = datasetForCI(title);
            actualBar = "Organisms";
            createSingleWindow();
        } else {
            sbAxiName = "Evolutionary distance vs cysteine %";
            dataset1 = datasetForBO(title);
            int number = 0;
            multipleWindow();
        }

    }
    /*
     Single window for comparison
     */

    public void multipleWindow() {
        /*
         JTabbedPane jtabbedpane = new JTabbedPane();
         jtabbedpane.add("Histograms:", createChartPanel2());
        
         data1 = createSampleData();
         jtabbedpane.add("Lines:", createChartPanel1());
         this.add(jtabbedpane);
         */
        final NumberAxis rangeAxis1 = new NumberAxis("Value");
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
        subplot1.setDomainGridlinesVisible(true);

        final CategoryDataset dataset2 = dataset1;
        final NumberAxis rangeAxis2 = new NumberAxis("Value");
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final BarRenderer renderer2 = new BarRenderer();
        renderer2.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2, renderer2);
        subplot2.setDomainGridlinesVisible(true);

        final CategoryAxis domainAxis = new CategoryAxis("Category");
        final CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.add(subplot1, 2);
        plot.add(subplot2, 1);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        final JFreeChart result = new JFreeChart(
                "ED vs cysteines",
                new Font("SansSerif", Font.BOLD, 12),
                plot,
                true
        );
        result.setBackgroundPaint(Color.white);

        final ChartPanel chartPanel = new ChartPanel(result);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

  //      result.getLegend().setAnchor(Legend.SOUTH);
    }

    public CategoryDataset crearDatasetBarras() {

        final DefaultCategoryDataset result = new DefaultCategoryDataset();

        // row keys...
        final String series1 = "First";
        final String series2 = "Second";

        // column keys...
        final String type1 = "Type 1";
        final String type2 = "Type 2";
        final String type3 = "Type 3";
        final String type4 = "Type 4";
        final String type5 = "Type 5";
        final String type6 = "Type 6";
        final String type7 = "Type 7";
        final String type8 = "Type 8";

        result.addValue(1.0, series1, type1);
        result.addValue(4.0, series1, type2);
        result.addValue(3.0, series1, type3);
        result.addValue(5.0, series1, type4);
        result.addValue(5.0, series1, type5);
        result.addValue(7.0, series1, type6);
        result.addValue(7.0, series1, type7);
        result.addValue(8.0, series1, type8);
        result.addValue(5.0, series2, type1);
        result.addValue(7.0, series2, type2);
        result.addValue(6.0, series2, type3);
        result.addValue(8.0, series2, type4);
        result.addValue(4.0, series2, type5);
        result.addValue(4.0, series2, type6);
        result.addValue(2.0, series2, type7);
        result.addValue(1.0, series2, type8);

        return result;

    }

    /**
     * Creates a dataset.
     *
     * @return A dataset.
     */
    public CategoryDataset crarDatasetLineas() {

        final DefaultCategoryDataset result = new DefaultCategoryDataset();

        // row keys...
        final String series1 = "Third";
        final String series2 = "Fourth";

        // column keys...
        final String type1 = "Type 1";
        final String type2 = "Type 2";
        final String type3 = "Type 3";
        final String type4 = "Type 4";
        final String type5 = "Type 5";
        final String type6 = "Type 6";
        final String type7 = "Type 7";
        final String type8 = "Type 8";

        result.addValue(11.0, series1, type1);
        result.addValue(14.0, series1, type2);
        result.addValue(13.0, series1, type3);
        result.addValue(15.0, series1, type4);
        result.addValue(15.0, series1, type5);
        result.addValue(17.0, series1, type6);
        result.addValue(17.0, series1, type7);
        result.addValue(18.0, series1, type8);

        result.addValue(15.0, series2, type1);
        result.addValue(17.0, series2, type2);
        result.addValue(16.0, series2, type3);
        result.addValue(18.0, series2, type4);
        result.addValue(14.0, series2, type5);
        result.addValue(14.0, series2, type6);
        result.addValue(12.0, series2, type7);
        result.addValue(11.0, series2, type8);

        return result;

    }

    private XYDataset createSampleData() {
        //dataset1
        XYSeries xyseries = new XYSeries("Evolutionary Distances");
        XYSeries xyseries1 = new XYSeries("Cysteines difference");
        int acum = 1;
        for (int i = 0; i < tmpForEvolutionary.size(); i++) {
            /*
             xyseries.addValue(
             Float.parseFloat(organismResult.get(i)[2]) , 
             series1, 
             organismName
             );
             */
            xyseries.add(acum, tmpForEvolutionary.get(i));
            acum++;
        }

        XYSeriesCollection xyseriescollection = new XYSeriesCollection(xyseries);

        acum = 1;
        for (int i = 0; i < tmpForCysteines.size(); i++) {
            xyseries1.add(acum, tmpForCysteines.get(i));
            acum++;
        }

        xyseriescollection.addSeries(xyseries1);

        return xyseriescollection;

    }

    private ChartPanel createChartPanel2() {
        final JFreeChart chart = ChartFactory.createBarChart(
                titles, // chart title
                "ED vs cysteines", // domain axis label
                sbAxiName, // range axis label
                dataset1, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips?
                false // URL generator?  Not required...
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
//        chart.getLegend().setAnchor(Legend.SOUTH);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        plot.mapDatasetToRangeAxis(1, 1);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        //domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
        // OPTIONAL CUSTOMISATION COMPLETED.

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chart.setBackgroundPaint(Color.white);
        return chartPanel;
    }

    private ChartPanel createChartPanel1() {
        NumberAxis numberaxis = new NumberAxis("X");
        numberaxis.setAutoRangeIncludesZero(false);
        NumberAxis numberaxis1 = new NumberAxis("Y");
        numberaxis1.setAutoRangeIncludesZero(false);
        XYSplineRenderer xysplinerenderer = new XYSplineRenderer();

        XYPlot xyplot = new XYPlot(
                data1,
                numberaxis,
                numberaxis1,
                xysplinerenderer
        );

        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(4D, 4D, 4D, 4D));
        JFreeChart jfreechart = new JFreeChart(titles, JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
        jfreechart.setBackgroundPaint(Color.white);
        ChartPanel chartpanel = new ChartPanel(jfreechart, false);
        return chartpanel;
    }

    /*
     Single window for Cysteines and ED only
     */
    public void createSingleWindow() {
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
                titles, // chart title
                actualBar, // domain axis label
                sbAxiName, // range axis label
                dataset1, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips?
                false // URL generator?  Not required...
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
//        chart.getLegend().setAnchor(Legend.SOUTH);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        plot.mapDatasetToRangeAxis(1, 1);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
        // OPTIONAL CUSTOMISATION COMPLETED.

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * dataset for evolutionary distances only
     */
    private CategoryDataset datasetForNplets(
            String sbOrgName,
            HashMap results,
            LinkedList<String> possibleCombinations
    ) {

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        final String series1 = "First";

        for (int i = 0; i < possibleCombinations.size(); i++) {
            String organismName = "";
            try {
                dataset.addValue(
                        (Float) results.get(possibleCombinations.get(i)),
                        series1,
                        possibleCombinations.get(i)
                );
            } catch (Exception e1) {
                System.out.println("fallo con " + organismName);
            }
        }
        return dataset;
    }

    /**
     * dataset for evolutionary distances only
     */
    private CategoryDataset datasetAminoCount(
            String sbOrgName,
            HashMap results,
            LinkedList<String> allAminos
    ) {

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        final String series1 = "First";

        for (int i = 0; i < allAminos.size(); i++) {
            String organismName = "";
            
            try {
                System.out.println(allAminos.get(i));
                int valorsito=((int)results.get(allAminos.get(i)));
                float valorsito2 = (float)valorsito;
//                Float tmp_val = 
//                        Integer i = results.get(allAminos.get(i));
                dataset.addValue(
                        valorsito2,
                        series1,
                        allAminos.get(i)
                );
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println("fallo con " + organismName);
            }
        }
        return dataset;
    }

    /**
     * dataset for evolutionary distances only
     */
    private CategoryDataset datasetForED(
            String sbOrgName
    ) {
        organismResult = nuevo.getEDResults(sbOrgName, top);

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        final String series1 = "First";

        for (int i = 0; i < organismResult.size(); i++) {
            System.out.println("res=>" + organismResult.get(i)[2]);
            String organismName = "";
            if (organismResult.get(i)[0].equalsIgnoreCase(sbOrgName)) {
                organismName = organismResult.get(i)[1];
            } else {
                organismName = organismResult.get(i)[0];
            }
            System.out.println(organismName);
            try {
                dataset.addValue(
                        Float.parseFloat(organismResult.get(i)[2]),
                        series1,
                        organismName
                );
            } catch (Exception e1) {
                System.out.println("fallo con " + organismName);
            }
        }
        return dataset;
    }

    /**
     * dataset for cisteine % differences only
     */
    private CategoryDataset datasetForCI(
            String sbOrgName
    ) {
        organismResult = nuevo.getCisDifference(sbOrgName, top);

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        final String series1 = "First";

        for (int i = 0; i < organismResult.size(); i++) {
            String organismName = "";
            try {
                dataset.addValue(
                        Float.parseFloat(organismResult.get(i)[1]),
                        series1,
                        organismResult.get(i)[0]
                );

            } catch (Exception e1) {
                System.out.println("fallo con " + organismName);
            }
        }

        return dataset;
    }

    /**
     * dataset for both results
     */
    private CategoryDataset datasetForBO(
            String sbOrgName
    ) {

        LinkedList<String[]> liEdResul = nuevo.getEDResults(sbOrgName, top);
        organismResult = nuevo.getCisDifference(sbOrgName, "100");

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        final String series1 = "ED";
        final String series2 = "Cysteines";

        for (int i = 0; i < liEdResul.size(); i++) {
            String organismName = "";
            if (liEdResul.get(i)[0].equalsIgnoreCase(sbOrgName)) {
                organismName = liEdResul.get(i)[1];
            } else {
                organismName = liEdResul.get(i)[0];
            }
            try {
                dataset.addValue(
                        Float.parseFloat(liEdResul.get(i)[2]),
                        series1,
                        organismName
                );

                tmpForEvolutionary.add(i, Float.parseFloat(liEdResul.get(i)[2]));

            } catch (Exception e1) {
                System.out.println("fallo con " + organismName);
            }

            try {

                dataset.addValue(
                        getDifferenceResult(organismName, organismResult),
                        series2,
                        organismName
                );
                tmpForCysteines.add(i, getDifferenceResult(organismName, organismResult));

            } catch (Exception e1) {
                System.out.println("fallo2 con " + organismName);
            }
        }

        for (int i = 0; i < tmpForCysteines.size(); i++) {
            //   System.out.println(" index "+i +" value "+tmpForCysteines.get(i));
        }
        return dataset;
    }

    /*
     * Returns the cisteine difference * 100000
     */
    public Float getDifferenceResult(
            String sbOrgName,
            LinkedList<String[]> liResCist
    ) {
        Float result = (float) 2000;
        for (int i = 0; i < liResCist.size(); i++) {
            if (liResCist.get(i)[0].equalsIgnoreCase(sbOrgName)) {
                return Float.parseFloat(liResCist.get(i)[1]) * 100000;
            }
        }
        return result;
    }
}
