/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author auceda
 */
public class ListaVenta implements TablaVenta {
//atributos

    public ArrayList<Venta> Lista;

    //constructor
    public ListaVenta() {
        Lista = new ArrayList();
    }

    //Metodos que manipulan los objetos de la coleccion
    public void AgregarVenta(Venta ven) {
        Lista.add(ven);
    } //metodo que agrega un objeto a la coleccion (add)

    public Venta RecuperarVenta(int posicion) {
        return Lista.get(posicion);
    } //metodo que retorna un docente en una posicion (get)

    public void EliminarVenta(int posicion) {
        Lista.remove(posicion);
    } //metodo que elimina un objeto de la coleccion (remove)
    //metodo que actualiza un objeto en base a su posicion en la coleccion (set)

    public void Actualizar(int posicion, Venta venactual) {
        Lista.set(posicion, venactual);
    }

    //metodo que busca un objeto en la coleccion y retorna su posicion. La busqueda se realizará por el codigo de docente
    public int BuscarVenta(String codbuscado) {
        for (int i = 0; i < Lista.size(); i++) { //size(): lleva la cantidad de objetos en la coleccion
            if (codbuscado.equals(Lista.get(i).getCodVenta())) {
                return i; //si existe retorna la posicion
            }
        }
        return -1; // retornará -1 si no existe el codigo en la coleccion
    }

    @Override
    public void MostrarEnTabla(JTable tabla) {
        DefaultTableModel mt = new DefaultTableModel(null, Encabezado);
        tabla.setModel(mt);
        for (int i = 0; i < Lista.size(); i++) {
            mt.addRow(Lista.get(i).CrearVenta());;// por que i+1, por que i inicia en 0
        }
    }
    
    public void MostrarEncabezadoTabla(JTable tabla){
        DefaultTableModel mt = new DefaultTableModel(null, Encabezado);
        tabla.setModel(mt);
    }
    
    public void MostrarNuevo(JTable tabla, Object[] ob, DefaultTableModel mt){
        //DefaultTableModel mt = new DefaultTableModel(null, Encabezado);
        mt.addRow(ob);
        tabla.setModel(mt);
    } 

}
