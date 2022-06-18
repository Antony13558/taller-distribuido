/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Formatos.Mensajes;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author auceda
 */
public class ImprimirDAO extends ConectarBD {

    public void pdfV(int idventa, double totales, String vendedor) {
        try {
            Date date = new Date();
            FileOutputStream archivo;
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File salida = new File(url + "venta1.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance(getClass().getResource("/Img/logo-color.png"));
            //Fecha
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            Font negritaM = new Font(Font.FontFamily.COURIER, 18, Font.BOLD, BaseColor.DARK_GRAY);

            /*----------------------------------------*/
            PdfPTable codigo = new PdfPTable(4);
            codigo.setWidthPercentage(100);
            codigo.getDefaultCell().setBorder(0);
            float[] columnWidthsCodigo = new float[]{70f, 20f, 30f, 40f};
            codigo.setWidths(columnWidthsCodigo);
            codigo.setHorizontalAlignment(Element.ALIGN_LEFT);
            codigo.addCell("");
            codigo.addCell("");
            codigo.addCell("");

            String codv = "";
            try {
                rs = st.executeQuery("SELECT codVenta FROM ventas WHERE IdVentas=" + idventa);
                //recorremos los registros de la consulta
                while (rs.next()) { //next(): recupera un registro de la consulta.
                    codv = rs.getString(1);

                    //codigo.addCell(cliCod);
                }
                rs.close(); //cerramos la conexion para liberar espacio
            } catch (Exception e) {
                Mensajes.m1("ERROR no se puede recuperar los datos....." + e);
            }
            PdfPCell cliCod = new PdfPCell(new Phrase(codv, negritaM));
            cliCod.setBorder(Rectangle.NO_BORDER);
            codigo.addCell(cliCod);
            doc.add(codigo);
            /*----------------------------------------*/

            PdfPTable salto = new PdfPTable(4);
            salto.setWidthPercentage(100);
            salto.getDefaultCell().setBorder(0);
            float[] columnWidthsSalto = new float[]{50f, 20f, 20f, 70f};
            salto.setWidths(columnWidthsSalto);
            salto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell ssalto = new PdfPCell(new Phrase("", negrita));
            cliCod.setBorder(Rectangle.NO_BORDER);
            salto.addCell("");
            salto.addCell("");
            salto.addCell("");
            salto.addCell(ssalto);
            doc.add(salto);
            /*----------------*/
            fecha.add(Chunk.NEWLINE);

            fecha.add("Vendedor: " + vendedor + "\nFolio: " + idventa + "\nFecha: "
                    + new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(columnWidthsEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezado.addCell(img);
            Encabezado.addCell("");

            //info empresa
            String mensaje = "";
            Encabezado.addCell("Ruc:    20456789612 \nNombre: Minimarket MaSMas \nTeléfono: 01-506913 \nDirección: Lima - Perú" + "\n\n");

            //
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            //cliente
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("DATOS DEL CLIENTE" + "\n\n");
            doc.add(cli);

            PdfPTable proveedor = new PdfPTable(1);
            proveedor.setWidthPercentage(100);
            proveedor.getDefaultCell().setBorder(0);
            float[] columnWidthsCliente = new float[]{20f};
            proveedor.setWidths(columnWidthsCliente);
            proveedor.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliNom = new PdfPCell(new Phrase("Nombre:", negrita));
            cliNom.setBorder(Rectangle.NO_BORDER);
            proveedor.addCell(cliNom);

            String nombre = "";
            try {
                rs = st.executeQuery("SELECT nomCliente FROM ventas order by IdVentas desc limit 1;");
                //recorremos los registros de la consulta
                while (rs.next()) { //next(): recupera un registro de la consulta.
                    nombre = rs.getString(1);
                    proveedor.addCell(nombre + "\n\n");
                }
                rs.close(); //cerramos la conexion para liberar espacio
            } catch (Exception e) {
                Mensajes.m1("ERROR no se puede recuperar los datos....." + e);
            }
            doc.add(proveedor);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setBorder(0);
            float[] columnWidths = new float[]{10f, 50f, 15f, 15f};
            tabla.setWidths(columnWidths);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell c1 = new PdfPCell(new Phrase("Cant.", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("Descripción.", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("P. unt.", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("P. Total", negrita));
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
            c4.setBorder(Rectangle.NO_BORDER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);
            try {
                rs = st.executeQuery(
                        " select dv.IdDetalleVentas, p.nombre, dv.cantidad, dv.precioUnit, dv.total\n"
                        + "from detalle_ventas dv\n"
                        + "inner join producto p on p.IdProducto = dv.IdProducto\n"
                        + "where dv.IdVentas = '" + idventa + "'");
                while (rs.next()) {
                    double subTotal = rs.getInt("cantidad") * rs.getDouble("precioUnit");
                    double totapagar = subTotal + subTotal * 0.18;
                    tabla.addCell(rs.getString("cantidad"));
                    tabla.addCell(rs.getString("nombre"));
                    tabla.addCell(rs.getString("precioUnit"));
                    tabla.addCell(String.valueOf(subTotal));
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }

            doc.add(tabla);

            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total S/: " + totales);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion \n\n");
            firma.add("------------------------------------\n");
            firma.add("Firma \n");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            Paragraph gr = new Paragraph();
            gr.add(Chunk.NEWLINE);
            gr.add(mensaje);
            gr.setAlignment(Element.ALIGN_CENTER);
            doc.add(gr);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);

        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }

    }

    public void pdfr() throws FileNotFoundException, DocumentException, BadElementException, IOException {
        try {
            Date date = new Date();
            FileOutputStream archivo;
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File salida = new File(url + "Reporte.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance(getClass().getResource("/Img/logo-color.png"));
            //Fecha
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            Font negritaM = new Font(Font.FontFamily.COURIER, 18, Font.BOLD, BaseColor.DARK_GRAY);
            
            
            //EMCABEZADO
            PdfPTable salto = new PdfPTable(4);
            salto.setWidthPercentage(100);
            salto.getDefaultCell().setBorder(0);
            float[] columnWidthsSalto = new float[]{50f, 20f, 20f, 70f};
            salto.setWidths(columnWidthsSalto);
            salto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell ssalto = new PdfPCell(new Phrase("", negrita));
            ssalto.setBorder(Rectangle.NO_BORDER);
            salto.addCell("");
            salto.addCell("");
            salto.addCell("");
            salto.addCell(ssalto);
            doc.add(salto);
            /*----------------*/
            fecha.add(Chunk.NEWLINE);

            fecha.add( "\nFecha: "+ new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(columnWidthsEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezado.addCell(img);
            Encabezado.addCell("");

            //info empresa
            String mensaje = "";
            Encabezado.addCell("Ruc:    20456789612 \nNombre: Minimarket MaSMas \nTeléfono: 01-506913 \nDirección: Lima - Perú" + "\n\n");

            Encabezado.addCell(fecha);
            doc.add(Encabezado);
            

            //cliente
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("REPORTE DE VENTAS" + "\n\n");
            doc.add(cli);

            /*-------------------*/
            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setBorder(0);
            float[] columnWidths = new float[]{12f, 16f, 12f, 12f, 12f, 8f, 12f};
            tabla.setWidths(columnWidths);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell c1 = new PdfPCell(new Phrase("Código.", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("Comprobante", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("Empleado.", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("Cliente", negrita));
            PdfPCell c5 = new PdfPCell(new Phrase("DNI", negrita));
            PdfPCell c6 = new PdfPCell(new Phrase("Total", negrita));
            PdfPCell c7 = new PdfPCell(new Phrase("Fecha", negrita));
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
            c4.setBorder(Rectangle.NO_BORDER);
            c5.setBorder(Rectangle.NO_BORDER);
            c6.setBorder(Rectangle.NO_BORDER);
            c7.setBorder(Rectangle.NO_BORDER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c7.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);
            tabla.addCell(c5);
            tabla.addCell(c6);
            tabla.addCell(c7);
            try {
                rs = st.executeQuery("select v.codVenta,  v.tipoDeComprobante, e.Apellidos, v.nomCliente,v.dni_o_ruc, sum(dv.total) \"TotalVenta\",v.fechaVenta  from ventas v\n"
                        + "inner join detalle_ventas dv on v.IdVentas=dv.IdVentas \n"
                        + "inner join empleado e on e.IdEmpleado= v.IdEmpleado\n"
                        + "group by dv.IdVentas\n"
                        + "order by v.IdVentas desc");
                while (rs.next()) {
                    //double subTotal = rs.getInt("cantidad") * rs.getDouble("precioUnit");
                    //double totapagar = subTotal + subTotal * 0.18;
                    tabla.addCell(rs.getString("codVenta"));
                    tabla.addCell(rs.getString("tipoDeComprobante"));
                    tabla.addCell(rs.getString("Apellidos"));
                    tabla.addCell(rs.getString("nomCliente"));
                    tabla.addCell(rs.getString("dni_o_ruc"));
                    tabla.addCell(rs.getString("TotalVenta"));
                    tabla.addCell(rs.getString("fechaVenta"));
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }

            doc.add(tabla);

            /*--------------------------*/
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add(" \n\n");
            firma.add("------------------------------------\n");
            firma.add("Firma \n");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            Paragraph gr = new Paragraph();
            gr.add(Chunk.NEWLINE);
            String mensaje2 = "";
            gr.add(mensaje2);
            gr.setAlignment(Element.ALIGN_CENTER);
            doc.add(gr);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);

        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }

    }

}
