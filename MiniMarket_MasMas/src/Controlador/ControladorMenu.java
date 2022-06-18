package Controlador;

// importando las vistas
import Formatos.Mensajes;
import Modelo.Login;
import Vista.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

public class ControladorMenu implements ActionListener {

    // atributos
    FormMenu vista;
    Login login = new Login();

    // constructor
    public ControladorMenu(FormMenu fmenu, Login lg) {

        login.setDni(lg.getDni());
        login.setPass(lg.getPass());
        login.setRol(lg.getRol());

        this.vista = fmenu;

        showPieChart();
        showHistogram();

        this.vista.btnEmpleadosForm.addActionListener(this);
        this.vista.btnProductosForm.addActionListener(this);
        this.vista.btnRolesForm.addActionListener(this);
        this.vista.btnVentasForm.addActionListener(this);
        this.vista.btnCerrarSesion.addActionListener(this);
        this.vista.btnInventarioForm.addActionListener(this);
        this.vista.btnCategoriasForm.addActionListener(this);

        if (lg.getRol() == 2) {
            fmenu.btnCategoriasForm.setEnabled(false);
            fmenu.btnEmpleadosForm.setEnabled(false);
            fmenu.btnInventarioForm.setEnabled(false);
            fmenu.btnProductosForm.setEnabled(false);
            fmenu.btnRolesForm.setEnabled(false);

        } else if (lg.getRol() == 3) {
            fmenu.btnEmpleadosForm.setEnabled(false);
            fmenu.btnRolesForm.setEnabled(false);
            fmenu.btnVentasForm.setEnabled(false);

        } else if (lg.getRol() == 1) {
            fmenu.btnCategoriasForm.setEnabled(true);
            fmenu.btnEmpleadosForm.setEnabled(true);
            fmenu.btnInventarioForm.setEnabled(true);
            fmenu.btnProductosForm.setEnabled(true);
            fmenu.btnRolesForm.setEnabled(true);
            fmenu.btnVentasForm.setEnabled(true);
        } else if (lg.getRol() != 1) {
            fmenu.btnCategoriasForm.setEnabled(false);
            fmenu.btnEmpleadosForm.setEnabled(false);
            fmenu.btnInventarioForm.setEnabled(false);
            fmenu.btnProductosForm.setEnabled(false);
            fmenu.btnRolesForm.setEnabled(false);
            fmenu.btnVentasForm.setEnabled(false);

            Mensajes.m1("No tienes permisos");
        }

    }

    public void showPieChart() {

        //create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset.setValue("Bonito", new Double(20));
        barDataset.setValue("Jurel", new Double(20));
        barDataset.setValue("Merlusa", new Double(40));
        barDataset.setValue("Lisa", new Double(10));

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Nivel de Extracción", barDataset, false, true, false);//explain

        PiePlot piePlot = (PiePlot) piechart.getPlot();

        //changing pie chart blocks colors
        piePlot.setSectionPaint("Bonito", new Color(255, 255, 102));
        piePlot.setSectionPaint("Jurel", new Color(102, 255, 102));
        piePlot.setSectionPaint("Merlusa", new Color(255, 102, 153));
        piePlot.setSectionPaint("Lisa", new Color(0, 204, 204));

        piePlot.setBackgroundPaint(Color.white);

        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        this.vista.jPanel4.removeAll();
        this.vista.jPanel4.add(barChartPanel, BorderLayout.CENTER);
        this.vista.jPanel4.validate();
    }

    public void showHistogram() {

        double[] values = {95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
            77, 44, 58, 91, 10, 67, 57, 19, 88, 84
        };

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);

        JFreeChart chart = ChartFactory.createHistogram("Nivel de produccion", "Cantidad", "Frecuencia", dataset, PlotOrientation.VERTICAL, false, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);

        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        this.vista.jPanel5.removeAll();
        this.vista.jPanel5.add(barpChartPanel2, BorderLayout.CENTER);
        this.vista.jPanel5.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnEmpleadosForm) {
            FormEmpleados femp = new FormEmpleados();
            femp.setVisible(true);
            femp.setTitle("Gestión de empleados");
            femp.setLocationRelativeTo(null);
            vista.dispose();
            // creamos el controlador y le enviamos el formulario que va a manipular (fFormInvent)
            ControladorEmpleado contEmp = new ControladorEmpleado(femp, login);
        }

        if (e.getSource() == vista.btnRolesForm) {
            FormRoles frol = new FormRoles();
            frol.setVisible(true);
            frol.setTitle("Gestión de los roles");
            frol.setLocationRelativeTo(null);
            vista.dispose();
            // creamos el controlador y le enviamos el formulario que va a manipular (fFormInvent)
            ControladorRoles contRol = new ControladorRoles(frol, login);
        }

        if (e.getSource() == vista.btnProductosForm) {
            FormProductos fpro = new FormProductos();
            fpro.setVisible(true);
            fpro.setTitle("Gestión de los productos");
            fpro.setLocationRelativeTo(null);
            // creamos el controlador y le enviamos el formulario que va a manipular (fFormInvent)
            vista.dispose();
            ControladorProductos contPro = new ControladorProductos(fpro, login);
        }

        if (e.getSource() == vista.btnInventarioForm) {
            FormInventario finven = new FormInventario();
            finven.setVisible(true);
            finven.setTitle("Gestión del inventario");
            finven.setLocationRelativeTo(null);
            // creamos el controlador y le enviamos el formulario que va a manipular (fFormInvent)
            vista.dispose();
            ControladorInventario contInv = new ControladorInventario(finven, login);
        }
        if (e.getSource() == vista.btnCategoriasForm) {
            FormCategorias fcate = new FormCategorias();
            fcate.setVisible(true);
            fcate.setTitle("Gestión de las categorías");
            fcate.setLocationRelativeTo(null);
            // creamos el controlador y le enviamos el formulario que va a manipular (fFormInvent)
            vista.dispose();
            ControladorCategoria controlCat = new ControladorCategoria(fcate, login);
        }

        if (e.getSource() == vista.btnCerrarSesion) {

            FormLogin fFormLogin = new FormLogin();
            fFormLogin.setVisible(true);
            fFormLogin.setTitle("Bienvenido a Minimarket Mas Mas");
            fFormLogin.setLocationRelativeTo(null);
            vista.dispose();
            // creamos el controlador y le enviamos el formulario que va a manipular (fFormInvent)
            ControladorLogin controlLogin = new ControladorLogin(fFormLogin);
        }
        if (e.getSource() == vista.btnVentasForm) {
            FormVentas fvent = new FormVentas();
            fvent.setVisible(true);
            fvent.setTitle("Ventas");
            fvent.setLocationRelativeTo(null);
            vista.dispose();
            // creamos el controlador y le enviamos el formulario que va a manipular (fFormInvent)
            ControladorVenta contVent = new ControladorVenta(fvent, login);

        }
    }

}
