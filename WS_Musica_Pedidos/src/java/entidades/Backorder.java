/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

// Clase interna para manejar el backorder
public class Backorder {
    private final int productId;
    private final int cantidadPendiente;

    public Backorder(int productId, int cantidadPendiente) {
        this.productId = productId;
        this.cantidadPendiente = cantidadPendiente;
    }

    public int getProductId() {
        return productId;
    }

    public int getCantidadPendiente() {
        return cantidadPendiente;
    }
}