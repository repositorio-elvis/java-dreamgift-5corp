/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreamgifts;

/**
 *
 * @author Usuario
 */
public class Orden_Compra {
    private String id;
    private String proveedor;
    private String fecha;

    public Orden_Compra(String id, String proveedor, String fecha) {
        this.id = id;
        this.proveedor = proveedor;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
