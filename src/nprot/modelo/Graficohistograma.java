package nprot.modelo;

/**
 *
 * @author hernando
 */
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;

public class Graficohistograma {

    public Graficohistograma() {
    }

    public IntervalXYDataset crearDataset(double[] valores, int numClases, String nombre) {
        System.out.println("HISTOGRAMA: " + nombre);
        System.out.println("VALORES:");
        for (double a : valores) {
            System.out.println(a);
        }
        System.out.println("NUMERO DE CLASES" + numClases);
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries(nombre, valores, numClases);
        return dataset;
    }

    private JFreeChart crearChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(
                "",
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        return chart;
    }

    public JPanel crearPanel(IntervalXYDataset dataset) {
        JFreeChart chart = crearChart(dataset);
        return new ChartPanel(chart);
    }

}
