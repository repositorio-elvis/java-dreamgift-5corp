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
public class Articulos {
    
    private String id;
    private String nombre;
    private String codigo;
    private String marca;
    private String descripcion;
    private String stock;
    
    private String fecha_vencimiento;
    private String estado;
    private String pro_razon;
    private String id_categoria;
        
    public Articulos(String a, String b, String c, String d, String e, String f, String h, String j, String k, String l){
        id = a;
        nombre = b;
        codigo = c;
        marca = d; 
        descripcion = e;
        stock = f;
        fecha_vencimiento = h; 
        estado = j;
        pro_razon = k;
        id_categoria = l;
    }

    public String getPro_razon() {
        return pro_razon;
    }

    public void setPro_razon(String pro_razon) {
        this.pro_razon = pro_razon;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }
    
    
}
