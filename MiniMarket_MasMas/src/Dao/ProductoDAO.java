package Dao;
// librerias

import Modelo.*;
import java.util.*;
import Formatos.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class ProductoDAO extends ConectarBD implements crudProducto {

    @Override
    public ArrayList<Producto> SincronizarListaProducto() {
        ArrayList<Producto> Lista = new ArrayList();

        try {
            // ejecutamos la consulta
            rs = st.executeQuery("SELECT pr.codProducto, pr.nombre, pr.descripcion, cat.nombre, pr.stock, pr.precioCoste, pr.precioVenta\n"
                    + "FROM producto pr INNER JOIN categoria cat ON (pr.categoria = cat.idCategoria)");
            // recorremos los registros de la consulta
            while (rs.next()) { // recupera registros por registro
                Producto prod = new Producto();
                prod.setCodProd(rs.getString(1));
                prod.setNomProd(rs.getString(2));
                prod.setDescripcion(rs.getString(3));
                prod.setCategoria(rs.getString(4));
                prod.setStock(rs.getInt(5));
                prod.setPrecioCoste(rs.getDouble(6));
                prod.setPrecioVenta(rs.getDouble(7));
                Lista.add(prod);
            }

            rs.close(); // cerramos la conexion para liberar espacio

        } catch (Exception e) {
            Mensajes.m1("Error no se puede recuperar los datos" + e);
        }
        return Lista;
    }

    // Listar las categorias en productos
    @Override
    public ArrayList<Categoria> SincronizarListaCategoriasEnProducto() {
        ArrayList<Categoria> listacat = new ArrayList<>();

        try {
            // ejecutamos la consulta
            rs = st.executeQuery("SELECT IdCategoria, nombre FROM categoria");

            while (rs.next()) {
                Categoria categ = new Categoria();
                categ.setCodCat(rs.getInt("IdCategoria"));
                categ.setNomCat(rs.getString("nombre"));
                listacat.add(categ);
            }

        } catch (Exception e) {
            Mensajes.m1("Error no se puede llamar a la lista de categorias" + e);
        }
        return listacat;
    }
    // Listar las categorias en productos

    @Override
    public void InsertarProducto(Producto prod) {
        try {
            // definimos la consulta SQL con interrogantes
            ps = conexion.prepareStatement("INSERT INTO producto (nombre,descripcion,categoria,stock,precioCoste,precioVenta,codProducto) VALUES(?,?,?,?,?,?,?)");
            // actualizar los parametros 
            ps.setString(1, prod.getNomProd());
            ps.setString(2, prod.getDescripcion());
            ps.setInt(3, prod.getIdCategoria());
            ps.setInt(4, prod.getStock());
            ps.setDouble(5, prod.getPrecioCoste());
            ps.setDouble(6, prod.getPrecioVenta());
            ps.setString(7, prod.getCodProd());
            ps.executeUpdate(); // actualiza y ejecuta  la consulta SQL
            ps.close();

        } catch (Exception e) {
            Mensajes.m1("Error no se puede insertar el registro" + e);
        }
    }

    @Override
    public Producto BuscarProductoPorCodigo(String codproducto) {
        Producto prod = null; // creamos el objeto inicializamsoe en vacio
        try {
            rs = st.executeQuery(""
                    + "SELECT  pr.codProducto, pr.nombre, pr.descripcion, cat.nombre, pr.stock, pr.precioCoste, pr.precioVenta,pr.IdProducto, cat.IdCategoria\n"
                    + "FROM producto pr INNER JOIN categoria cat ON (pr.categoria = cat.idCategoria) WHERE codProducto='" + codproducto + "'");
            if (rs.next()) {
                prod = new Producto();
                prod.setCodProd(rs.getString(1));
                prod.setNomProd(rs.getString(2));
                prod.setDescripcion(rs.getString(3));
                prod.setCategoria(rs.getString(4));
                prod.setStock(rs.getInt(5));
                prod.setPrecioCoste(rs.getDouble(6));
                prod.setPrecioVenta(rs.getDouble(7));
                prod.setIdproducto(rs.getInt(8));
                prod.setIdCategoria(rs.getInt(9));
            }
            rs.close();

        } catch (Exception e) {
            Mensajes.m1("Error no se puede buscar el codigo..." + e);
        }
        return prod;

    }

    @Override
    public void ActualizarProducto(Producto nuevoprod) {

        try {

            ps = conexion.prepareStatement("UPDATE producto SET nombre=?, descripcion = ?,categoria = ?, stock = ?, precioCoste = ? ,precioVenta = ? WHERE codProducto = ?");
            // actualizar los parametros 
            ps.setString(1, nuevoprod.getNomProd());
            ps.setString(2, nuevoprod.getDescripcion());
            ps.setInt(3, nuevoprod.getIdCategoria()+1);
            ps.setInt(4, nuevoprod.getStock());
            ps.setDouble(5, nuevoprod.getPrecioCoste());
            ps.setDouble(6, nuevoprod.getPrecioVenta());
            ps.setString(7, nuevoprod.getCodProd());
            ps.executeUpdate(); // actualiza y ejecuta  la consulta SQL
            ps.close();

        } catch (Exception e) {
            Mensajes.m1("Error no se puede actualizar el producto..." + e);
        }

    }

    @Override
    public void EliminarProducto(String codproducto) {

        try {
            ps = conexion.prepareStatement("DELETE FROM producto WHERE codProducto = ?;");
            ps.setString(1, codproducto);
            ps.executeUpdate();
            ps.close();
            
            Mensajes.m1("Registro del producto ha sido eliminado...");

        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede eliminar el producto porque ya existe en una venta");
        }
    }

    @Override
    public Producto BuscarProductoPorCodigoAPI(String codproducto) {
        Producto prod = null;
        prod = new Producto();
        try {
                URL url = new URL("http://localhost:9000/api/"+codproducto);
                
                System.out.println(url);
                
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                
                int responseCode = conn.getResponseCode();
                
                if(responseCode != 200){
                    Mensajes.m1("NO SE ENCONTRARON LOS DATOS");
                    throw new RuntimeException("Ocurrio un erro: "+ responseCode); 
                }else{
                    StringBuilder info = new StringBuilder();
                    Scanner scanner = new Scanner(url.openStream());
                    
                    while(scanner.hasNext()){
                        info.append(scanner.nextLine());
                    }
                    scanner.close();
                    
                    JSONArray jsonArray = new JSONArray(info.toString());
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    System.out.println(jsonObject.get("nombre"));
                    
                    
                    prod.setCodProd(jsonObject.get("codProducto").toString());
                    prod.setNomProd(jsonObject.get("nombre").toString());
                    prod.setDescripcion(jsonObject.get("descripcion").toString());
                    //prod.setCategoria(jsonObject.get("nombre").toString());
                    prod.setStock(Integer.parseInt(jsonObject.get("stock").toString()));
                    prod.setPrecioCoste(Integer.parseInt(jsonObject.get("precioCoste").toString()));
                    prod.setPrecioVenta(Integer.parseInt(jsonObject.get("precioVenta").toString()));
                    prod.setIdproducto(Integer.parseInt(jsonObject.get("IdProducto").toString()));
                    prod.setIdCategoria(Integer.parseInt(jsonObject.get("categoria").toString()));

                }
   
                } catch (MalformedURLException ex) {
                    Mensajes.m1("Error no se puede buscar el codigo..." + ex);
                } catch (IOException ex) {
                    Mensajes.m1("Error no se puede buscar el codigo..." + ex);
            }
        
        return prod;
    }

} // fin de la clase DAO
