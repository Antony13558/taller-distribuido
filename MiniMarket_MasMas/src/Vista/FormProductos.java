/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.Categoria;

/**
 *
 * @author CRISTIAN
 */
public class FormProductos extends javax.swing.JFrame {

    /**
     * Creates new form FormProductos
     */
    public FormProductos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameWelcome = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jtxtCodProd = new javax.swing.JTextField();
        jtxtNombreProd = new javax.swing.JTextField();
        jspnStockProducto = new javax.swing.JSpinner();
        jtxtPCoste = new javax.swing.JTextField();
        jtxtPVenta = new javax.swing.JTextField();
        jtxtDescripcionProd = new javax.swing.JTextField();
        jbtnGuardarProd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jbtnLimpiarCampos = new javax.swing.JButton();
        jcbxCategoria = new javax.swing.JComboBox<>();
        jbtnActualizarProd = new javax.swing.JButton();
        jbtnEliminarProd = new javax.swing.JButton();
        jbtnConsultarProd = new javax.swing.JButton();
        jPregresar = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1216, 630));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nameWelcome.setFont(new java.awt.Font("Rubik", 1, 28)); // NOI18N
        nameWelcome.setText("Productos");
        getContentPane().add(nameWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 160, 30));

        jLabel4.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/separador-min.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        jTableProductos.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableProductos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 77, 780, 420));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Nuevo producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Rubik Medium", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtCodProd.setFont(new java.awt.Font("Rubik", 0, 16)); // NOI18N
        jtxtCodProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(jtxtCodProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 150, 40));

        jtxtNombreProd.setFont(new java.awt.Font("Rubik", 0, 16)); // NOI18N
        jtxtNombreProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(jtxtNombreProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 180, 40));

        jspnStockProducto.setFont(new java.awt.Font("Rubik", 0, 16)); // NOI18N
        jspnStockProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(jspnStockProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 70, 40));

        jtxtPCoste.setFont(new java.awt.Font("Rubik", 0, 16)); // NOI18N
        jtxtPCoste.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(jtxtPCoste, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 90, 40));

        jtxtPVenta.setFont(new java.awt.Font("Rubik", 0, 16)); // NOI18N
        jtxtPVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(jtxtPVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 160, 40));

        jtxtDescripcionProd.setFont(new java.awt.Font("Rubik", 0, 16)); // NOI18N
        jtxtDescripcionProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(jtxtDescripcionProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 340, 100));

        jbtnGuardarProd.setBackground(new java.awt.Color(156, 234, 66));
        jbtnGuardarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/agregar-producto.png"))); // NOI18N
        jPanel1.add(jbtnGuardarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 190, 50));

        jLabel2.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel3.setText("Precio venta (sin IGV)");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel5.setText("Descripción");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jLabel6.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel6.setText("Precio coste");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabel7.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel7.setText("Stock");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel8.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel8.setText("Categoría");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel9.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel9.setText("Código");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jbtnLimpiarCampos.setBackground(new java.awt.Color(204, 204, 204));
        jbtnLimpiarCampos.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jbtnLimpiarCampos.setText("Limpiar campos");
        jPanel1.add(jbtnLimpiarCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 140, 50));

        jcbxCategoria.setFont(new java.awt.Font("Rubik", 0, 16)); // NOI18N
        jPanel1.add(jcbxCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 150, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 380, 500));

        jbtnActualizarProd.setBackground(new java.awt.Color(142, 228, 255));
        jbtnActualizarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/btn-actualizar.png"))); // NOI18N
        getContentPane().add(jbtnActualizarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 510, 170, 60));

        jbtnEliminarProd.setBackground(new java.awt.Color(255, 154, 165));
        jbtnEliminarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        getContentPane().add(jbtnEliminarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 510, 180, 60));

        jbtnConsultarProd.setBackground(new java.awt.Color(190, 231, 244));
        jbtnConsultarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/buscar-reporte.png"))); // NOI18N
        getContentPane().add(jbtnConsultarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, 180, 50));

        jPregresar.setBackground(new java.awt.Color(238, 238, 238));
        jPregresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/btn-regresar.png"))); // NOI18N
        getContentPane().add(jPregresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 510, 190, 60));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/fondo-menu.jpg"))); // NOI18N
        Fondo.setText("jLabel1");
        Fondo.setPreferredSize(new java.awt.Dimension(1200, 600));
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JButton jPregresar;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableProductos;
    public javax.swing.JButton jbtnActualizarProd;
    public javax.swing.JButton jbtnConsultarProd;
    public javax.swing.JButton jbtnEliminarProd;
    public javax.swing.JButton jbtnGuardarProd;
    public javax.swing.JButton jbtnLimpiarCampos;
    public javax.swing.JComboBox<String> jcbxCategoria;
    public javax.swing.JSpinner jspnStockProducto;
    public javax.swing.JTextField jtxtCodProd;
    public javax.swing.JTextField jtxtDescripcionProd;
    public javax.swing.JTextField jtxtNombreProd;
    public javax.swing.JTextField jtxtPCoste;
    public javax.swing.JTextField jtxtPVenta;
    private javax.swing.JLabel nameWelcome;
    // End of variables declaration//GEN-END:variables
}