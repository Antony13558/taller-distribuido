/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Modelo.ListaVenta;
import Modelo.Venta;
import java.util.ArrayList;

/**
 *
 * @author auceda
 */
public interface crudVenta {

    public void InsertarVenta(Venta ven);
    
    public void InsertarDetalleVenta(Venta ven, int idVenta ,int codProducto,int cantidad,double precioUnit, double total);

    public Venta BuscarVenta(String boleta);

    public void ActualizarVenta(Venta nuevaventa);

    public void EliminarVentaBoleta(String codprod);

    public ArrayList<Venta> SincronizarListaVenta();

    public ArrayList<Venta> SincronizarListaVentaObjetos(ListaVenta ven);
    
    public String IdVentas();
    
    public void ActualizarStock(int cantidad, int idProducto);
}
