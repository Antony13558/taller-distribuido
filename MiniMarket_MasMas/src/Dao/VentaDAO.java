package Dao;

import Formatos.Mensajes;
import Modelo.ListaVenta;
import Modelo.Producto;
import Modelo.Venta;
import java.util.ArrayList;


public class VentaDAO extends ConectarBD implements crudVenta {

    @Override
    public void InsertarVenta(Venta ven) {
        try {
            //definimos la sql con interrogantes(parametros) para que sean actualizado desde el metodo
            ps = conexion.prepareStatement("INSERT INTO ventas (nomCliente,dni_o_ruc,fechaVenta,tipoDeComprobante,IdEmpleado,codVenta) VALUES(?,?,?,?,?,?)");
            //actualizar los parametros de la consulta

            ps.setString(1, ven.getNomCliente());
            ps.setInt(2, ven.getDni_o_ruc());
            ps.setString(3, ven.getFechaVenta());
            ps.setString(4, ven.getTipoDeComprobante());
            ps.setInt(5, ven.getIdEmpleado());
            ps.setString(6, ven.getCodVenta());
            ps.executeUpdate(); // actualiza y ejecuta la consulta SQL
            ps.close();

        } catch (Exception e) {
            Mensajes.m1("ERROR1: no se puede insertar el registro ..." + e);
        }
    }

    @Override
    public void InsertarDetalleVenta(Venta ven, int idVentas, int idProducto, int cantidad, double precioUnit, double total) {
        try {
            //definimos la sql con interrogantes(parametros) para que sean actualizado desde el metodo
            ps = conexion.prepareStatement("INSERT INTO detalle_ventas (IdDetalleVentas, IdVentas, IdProducto, cantidad, precioUnit, total, codVenta) VALUES (NULL, ?, ?, ?, ?, ?, ?)");
            //actualizar los parametros de la consulta
            ps.setInt(1, idVentas);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            ps.setDouble(4, precioUnit);
            ps.setDouble(5, total);
            ps.setString(6, ven.getCodVenta());
            ps.executeUpdate(); // actualiza y ejecuta la consulta SQL
            ps.close();

            

        } catch (Exception e) {
            Mensajes.m1("ERROR2: no se puede insertar el registro ..." + e);
        }
    }

    @Override
    public Venta BuscarVenta(String codprod) {
        Venta ven = null; //creamos el objeto inicializamos en vacio
        try {
            rs = st.executeQuery(
                    " select * from producto where codProducto ='" + codprod + "'");
            if (rs.next()) {

                Producto prod = new Producto();
                prod.setCodProd(rs.getString(1));
                prod.setNomProd(rs.getString(2));
                prod.setDescripcion(rs.getString(3));
                prod.setCategoria(rs.getString(4));
                prod.setStock(rs.getInt(5));
                prod.setPrecioCoste(rs.getDouble(6));
                prod.setPrecioVenta(rs.getDouble(7));
            }
            rs.close();
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede buscar el codigo .." + e);
        }
        return ven;
    }

    @Override
    public void ActualizarVenta(Venta nuevaventa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void EliminarVentaBoleta(String codprod) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Venta> SincronizarListaVenta() {
        ArrayList<Venta> Lista = new ArrayList();
        try {
            //ejecutamos la consulta
            rs = st.executeQuery("SELECT codVenta, FROM ventas;");
            //recorremos los registros de la consulta
            while (rs.next()) { //next(): recupera un registro de la consulta.
                Venta ven = new Venta(); //creamos el objeto vacio
                ven.setCodVenta(rs.getString(1)); //recuperamos el codigo(columna 1) de la consulta y lo enviamos al objeto producto
                ven.setNomCliente(rs.getString(2));
                ven.setDni_o_ruc(rs.getInt(3));
                ven.setFechaVenta(rs.getString(4));
                ven.setTipoDeComprobante(rs.getString(5));
                ven.setIdEmpleado(rs.getInt(6));
                Lista.add(ven);
            }
            rs.close(); //cerramos la conexion para liberar espacio
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede recuperar los datos....." + e);
        }
        return Lista;

    }

    @Override
    public ArrayList<Venta> SincronizarListaVentaObjetos(ListaVenta venta) {
        ArrayList<Venta> Lista = new ArrayList();
        for (int i = 1; i <= 10; i++) {

        }
        return Lista;
    }

    @Override
    public String IdVentas() {
        String idv = "";
        try {
            //ejecutamos la consulta

            rs = st.executeQuery("SELECT MAX(IdVentas) FROM ventas");
            //recorremos los registros de la consulta
            while (rs.next()) { //next(): recupera un registro de la consulta.
                idv = rs.getString(1);
            }
            rs.close(); //cerramos la conexion para liberar espacio
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede recuperar los datos....." + e);
        }
        return idv;
    }
    
    public int IdProducto(int codProducto){
        int cod = 0;
        try {
            //ejecutamos la consulta

            rs = st.executeQuery("SELECT IdProducto from producto where codProducto = "+codProducto+"");
            //recorremos los registros de la consulta
            while (rs.next()) { //next(): recupera un registro de la consulta.
                cod = rs.getInt(1);
            }
            rs.close(); //cerramos la conexion para liberar espacio
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede recuperar los datos....." + e);
        }
        return cod;
    }
    
        public int obtenerStock(int codProducto){
        int cod = 0;
        try {
            //ejecutamos la consulta

            rs = st.executeQuery("SELECT stock from producto where codProducto = "+codProducto+"");
            //recorremos los registros de la consulta
            while (rs.next()) { //next(): recupera un registro de la consulta.
                cod = rs.getInt(1);
            }
            rs.close(); //cerramos la conexion para liberar espacio
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede recuperar los datos....." + e);
        }
        return cod;
    }

    @Override
    public void ActualizarStock(int cantidad , int idProducto) {
        try {

            ps = conexion.prepareStatement("UPDATE producto SET stock = ? WHERE IdProducto = ?");
            // actualizar los parametros 
            ps.setInt(1, cantidad );
            ps.setInt(2, idProducto);

            ps.executeUpdate(); // actualiza y ejecuta  la consulta SQL
            ps.close();

        } catch (Exception e) {
            Mensajes.m1("Error no se puede actualizar Stock del producto..." + e);
        }
    }

}
