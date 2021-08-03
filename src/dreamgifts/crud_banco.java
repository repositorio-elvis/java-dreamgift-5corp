/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreamgifts;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.SpinnerNumberModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.StampedLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gabri
 */
public class crud_banco extends javax.swing.JFrame {
    
    // Declaramos la conexion a mysql
    private static Connection con;
    
    // Declaramos los datos de conexion a la bd
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="u0y837eoifywem5q";
    private static final String pass="IF9svVbNHhO5FqmNKLQU";
    private static final String url="jdbc:mysql://bttuukyxa2qoegjcitfx-mysql.services.clever-cloud.com:3306/bttuukyxa2qoegjcitfx";
    
      // Funcion que va conectarse a mi bd de mysql
    public static void conector(){
        // Reseteamos a null la conexion a la bd
        con=null;
        try{
            Class.forName(driver);
            // Nos conectamos a la bd
            con= (Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con!=null){
                System.out.println("Conexion establecida");
            }
        }
        // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e){
            System.out.println("Error de conexion" + e);
        }
    }

    /**
     * Creates new form crud_banco
     */
    Vector<String> lista1;
    Vector<String> lista2;
    public crud_banco() {
        initComponents();
        Mostrar_RRSS("");
        Mostrar_COMUNA("");
        Mostrar_BANCO("");
        Mostrar_USUARIO("");
        Mostrar_CLIENTE("");
        Mostrar_PROVEEDOR("");
        Mostrar_CAT_ARTICULO("");
        Mostrar_CAT_VENTA("");
        Mostrar_COMBOX_PACKS();
        Mostrar_COMBOX_BANCOS();
        Mostrar_COMBOX_RRSS();
        Mostrar_COMBOX_COMUNAS();
        Mostrar_COMBOX_ESTADOS();
        Mostrar_VENTA_TABLA("");
        
               
        int panelX = (getWidth() - Panel_tab_menu.getWidth() - getInsets().left - getInsets().right) / 2;
	int panelY = ((getHeight() - Panel_tab_menu.getHeight() - getInsets().top - getInsets().bottom) / 2);
        //Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //setLocation((pantalla.width/2)-(this.getWidth()/2), (pantalla.height/2)-(this.getHeight()/2));
        setLocationRelativeTo(null);
        lista1=new Vector<String>();
        lista2=new Vector<String>();
        cargarLista1();
        SpinnerNumberModel nm = new SpinnerNumberModel();
        nm.setMaximum(1000);
        nm.setMinimum(0);
        addunits_spinner.setModel(nm);
        
        
        
        
        
    }
    
    private void Mostrar_RRSS(String valor){
        Statement st;
        String []datos = new String [3];   
        DefaultTableModel modelo = (DefaultTableModel) jTable4.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT RRS_NOMBRE, RRS_CODIGO, ESTADO FROM rrss where CONCAT(RRS_NOMBRE, ' ',RRS_CODIGO) LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                datos[1]=rs.getString(2);
                if ("1".equals(rs.getString(3))){
                    datos[2] = "activada";
                }
                else datos[2]= "desactivada";
                modelo.addRow(datos); 
                
            }
            jTable4.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Mostrar_BANCO(String valor){
        Statement st;
        String []datos = new String [3];   
        DefaultTableModel modelo = (DefaultTableModel) ban_tabla.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT BAN_DESCRIPCION ,BAN_CODIGO, estado FROM bancos where CONCAT(BAN_DESCRIPCION, ' ',BAN_CODIGO) LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                datos[1]=rs.getString(2);
                if ("1".equals(rs.getString(3))){
                    datos[2]= "activado";
                }
                else datos[2]= "desactivado";
                modelo.addRow(datos); 
            }
            ban_tabla.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Mostrar_COMUNA(String valor){
        Statement st;
        String []datos = new String [3];   
        DefaultTableModel modelo = (DefaultTableModel) comu_tabla.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COM_DESCRIPCION ,COM_CODIGO, estado FROM comunas where CONCAT(COM_DESCRIPCION, ' ',COM_CODIGO) LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                datos[1]=rs.getString(2);
                if ("1".equals(rs.getString(3))){
                    datos[2]= "activada";
                }
                else datos[2]= "desactivada";
                modelo.addRow(datos); 
                
            }
            comu_tabla.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Mostrar_CAT_ARTICULO(String valor){
        Statement st;
        String []datos = new String [2];   
        DefaultTableModel modelo = (DefaultTableModel) cat_art_tabla.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CAT_DESCRIPCION, CAT_CODIGO  FROM categoria_articulo where CONCAT(CAT_DESCRIPCION, ' ',CAT_CODIGO) LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                datos[1]=rs.getString(2);
                modelo.addRow(datos); 
                
            }
            cat_art_tabla.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    private void Mostrar_CAT_VENTA(String valor){
        Statement st;
        String []datos = new String [2];   
        DefaultTableModel modelo = (DefaultTableModel) cat_ven_tabla.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT EST_DESCRIPCION, EST_CODIGO  FROM estados_venta where CONCAT(EST_DESCRIPCION, ' ',EST_CODIGO) LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                datos[1]=rs.getString(2);
                modelo.addRow(datos); 
                
            }
            cat_ven_tabla.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Mostrar_USUARIO(String valor){
        Statement st;
        String []datos = new String [2];   
        DefaultTableModel modelo = (DefaultTableModel) usuario_tabla.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT USU_NOMBRE FROM usuarios where CONCAT(USU_NOMBRE, ' ',USU_CONTRASEÑA) LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                modelo.addRow(datos); 
            }
            usuario_tabla.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    private void Mostrar_CLIENTE(String valor){
        Statement st;
        String []datos = new String [6];   
        DefaultTableModel modelo = (DefaultTableModel) cli_tabla.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CLI_ID_CLIENTE, CLI_NOMBRE, CLI_CELULAR, CLI_CORREO, CLI_FECHA_NACIMIENTO, ESTADO FROM cliente where CONCAT(CLI_ID_CLIENTE, ' ',CLI_NOMBRE) LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3); 
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                if ("1".equals(rs.getString(6))){
                    datos[5] = "activada";
                }
                else datos[5]= "desactivada";
                modelo.addRow(datos);           
                
            }
            cli_tabla.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Mostrar_PROVEEDOR(String valor){
        Statement st;
        String []datos = new String [7];   
        DefaultTableModel modelo = (DefaultTableModel) prov_tabla.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT PRO_ID_PROVEEDOR, PRO_NOMBRE, PRO_TELEFONO, PRO_CORREO, PRO_DIRECCION, PRO_RAZON, ESTADO FROM proveedor where CONCAT(PRO_ID_PROVEEDOR, ' ',PRO_NOMBRE) LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3); 
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                if ("1".equals(rs.getString(7))){
                    datos[6] = "activada";
                }
                else datos[6]= "desactivada";
                modelo.addRow(datos);           
                
            }
            prov_tabla.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Mostrar_VENTA_TABLA(String valor){
        Statement st;
        String []datos = new String [5];   
        DefaultTableModel modelo = (DefaultTableModel) ven_con_tabla.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT VTA_ID_VENTA, CLI_ID_CLIENTE, VTA_CODIGO_TRANSFERENCIA, ESTADO, BAN_ID_BANCO FROM venta where CONCAT(VTA_ID_VENTA, ' ',CLI_ID_CLIENTE) LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                datos[1]=rs.getString(3);
                datos[2]=rs.getString(2); 
                datos[3]=rs.getString(5);
                if ("1".equals(rs.getString(4))){
                    datos[4] = "activada";
                }
                else datos[4]= "desactivada";
                modelo.addRow(datos);           
                
            }
            ven_con_tabla.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void Mostrar_DESTINOS_TABLA(String valor){
        Statement st;
        String []datos = new String [7];   
        DefaultTableModel modelo = (DefaultTableModel) tabla_destinos.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT VTA_ID_VENTA, PCK_NOMBRE, VTA_NOMBRE_DESTINATARIO, "
                    + "COM_DESCRIPCION, VTA_DIRECCION_DESTINATARIO, VTA_FECHA_ENTREGA, "
                    + "VTA_HORA_ENTREGA_INICIAL FROM venta "
                    + "INNER JOIN comunas ON venta.COM_ID_COMUNA = comunas.COM_ID_COMUNA "
                    + "INNER JOIN pack ON venta.PCK_ID_PACK = pack.PCK_ID_PACK where VTA_ID_VENTA LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3); 
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                modelo.addRow(datos);           
                
            }
            tabla_destinos.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Mostrar_DESPACHOS_TABLA(String valor){
        Statement st;
        String []datos = new String [7];   
        DefaultTableModel modelo = (DefaultTableModel) tabla_despachos.getModel();
        modelo.setNumRows(0);
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT VTA_ID_VENTA, PCK_NOMBRE, VTA_NOMBRE_DESTINATARIO, "
                    + "COM_DESCRIPCION, VTA_FECHA_ENTREGA, "
                    + "VTA_HORA_ENTREGA_INICIAL, EST_DESCRIPCION FROM venta "
                    + "INNER JOIN comunas ON venta.COM_ID_COMUNA = comunas.COM_ID_COMUNA "
                    + "INNER JOIN pack ON venta.PCK_ID_PACK = pack.PCK_ID_PACK "
                    + "INNER JOIN estados_venta ON venta.ESTADOS_ID_ESTADO = estados_venta.EST_ID_ESTADO where VTA_ID_VENTA LIKE '%"+valor+"%'");
            while (rs.next()){
                datos[0]=rs.getString(1); 
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3); 
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                modelo.addRow(datos);           
                
            }
            tabla_despachos.setModel(modelo);
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void Mostrar_COMBOX_PACKS(){

           venta_pack_box.removeAllItems();
           Statement st;
           try{
               st = con.createStatement();
               ResultSet rs = st.executeQuery("SELECT PCK_NOMBRE FROM pack WHERE estado = 1");
               while (rs.next()){
                   venta_pack_box.addItem(rs.getString(1));
               }
           }
           catch (SQLException ex) {
               Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
           }       
       }
    
    private void Mostrar_COMBOX_BANCOS(){

           ven_con_banco_combox.removeAllItems();
           Statement st;
           try{
               st = con.createStatement();
               ResultSet rs = st.executeQuery("SELECT BAN_DESCRIPCION FROM bancos WHERE estado = 1");
               while (rs.next()){
                   ven_con_banco_combox.addItem(rs.getString(1));
               }
           }
           catch (SQLException ex) {
               Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
           }       
       }
    
    private void Mostrar_COMBOX_RRSS(){

           venta_rrss_box.removeAllItems();
           Statement st;
           try{
               st = con.createStatement();
               ResultSet rs = st.executeQuery("SELECT RRS_NOMBRE FROM rrss WHERE estado = 1");
               while (rs.next()){
                   venta_rrss_box.addItem(rs.getString(1));
               }
           }
           catch (SQLException ex) {
               Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
           }       
       }
    
    private void Mostrar_COMBOX_COMUNAS(){

           venta_comuna_box.removeAllItems();
           Statement st;
           try{
               st = con.createStatement();
               ResultSet rs = st.executeQuery("SELECT COM_DESCRIPCION FROM comunas WHERE estado = 1");
               while (rs.next()){
                   venta_comuna_box.addItem(rs.getString(1));
               }
           }
           catch (SQLException ex) {
               Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
           }       
       }
    
    private void Mostrar_COMBOX_ESTADOS(){

           venta_estado_box.removeAllItems();
           Statement st;
           try{
               st = con.createStatement();
               ResultSet rs = st.executeQuery("SELECT EST_DESCRIPCION FROM estados_venta");
               while (rs.next()){
                   venta_estado_box.addItem(rs.getString(1));
               }
           }
           catch (SQLException ex) {
               Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
           }       
       }
    
    
    /*private void Mostrar_RUT(String valor){
        Statement st;
        Statement stc;
        Statement rut;
        String campo1 = venta_rut_field.getText();
        try {
            rut = con.createStatement();
            ResultSet rut1 = rut.executeQuery("SELECT CLI_ID_CLIENTE FROM cliente WHERE CLI_ID_CLIENTE = '"+campo1+"'");
            
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CLI_NOMBRE, CLI_CORREO, CLI_CELULAR FROM cliente where CLI_ID_CLIENTE LIKE '%"+campo1+"%'");
            
            stc = con.createStatement();
            ResultSet id_nomb = stc.executeQuery("SELECT VTA_NOMBRE_DESTINATARIO, VTA_FECHA_ENTREGA, "
                    + "VTA_DIRECCION_DESTINATARIO, COM_DESCRIPCION, VTA_SALUDO, RRS_NOMBRE, EST_DESCRIPCION, "
                    + "PCK_NOMBRE, VTA_HORA_ENTREGA_INICIAL, VTA_HORA_ENTREGA_FINAL FROM venta "
                    + "INNER JOIN rrss ON venta.RRSS_ID_RRSS = rrss.RRS_ID_RRSS "
                    + "INNER JOIN comunas ON venta.COM_ID_COMUNA = comunas.COM_ID_COMUNA "
                    + "INNER JOIN estados_venta ON venta.ESTADOS_ID_ESTADO = estados_venta.EST_ID_ESTADO "
                    + "INNER JOIN pack ON venta.PCK_ID_PACK = pack.PCK_ID_PACK where CLI_ID_CLIENTE LIKE '%"+campo1+"%'");
            id_nomb.next();
            
            if (rs.next()){
                //venta_rut_field.setText(rs.getString(1));
                venta_nom_cli_field.setText(rs.getString(1));
                venta_email_field.setText(rs.getString(2));
                venta_telefono_field.setText(rs.getString(3));
                venta_destinatario_field.setText(id_nomb.getString(1));
                venta_fecha_entrega.setText(id_nomb.getString(2));
                venta_direccion_field.setText(id_nomb.getString(3));
                venta_comuna_box.setSelectedItem(id_nomb.getString(4));
                venta_saludo_field.setText(id_nomb.getString(5));
                venta_rrss_box.setSelectedItem(id_nomb.getString(6));
                venta_estado_box.setSelectedItem(id_nomb.getString(7));
                venta_pack_box.setSelectedItem(id_nomb.getString(8));
                venta_fecha_entrega_ini.setText(id_nomb.getString(9));
                venta_entrega_fin.setText(id_nomb.getString(10));
                
            } else {
                    JOptionPane.showMessageDialog(null, "RUT NO REGISTRADO");
                    st.close();
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    */
    
    private void Mostrar_PEDIDO(){
        Statement st;
        Statement stc;
        Statement rut;
        String campo1 = venta_num_pedido_field.getText();
        String campo2 = venta_rut_field.getText();
        try {
            rut = con.createStatement();
            ResultSet rut1 = rut.executeQuery("SELECT CLI_ID_CLIENTE FROM cliente WHERE CLI_ID_CLIENTE = '"+campo2+"'");
            
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CLI_NOMBRE, CLI_CORREO, CLI_CELULAR FROM cliente where CLI_ID_CLIENTE LIKE '"+campo2+"'");
            
            stc = con.createStatement();
            ResultSet id_nomb = stc.executeQuery("SELECT CLI_ID_CLIENTE, VTA_NOMBRE_DESTINATARIO, VTA_FECHA_ENTREGA, "
                    + "VTA_DIRECCION_DESTINATARIO, COM_DESCRIPCION, VTA_SALUDO, RRS_NOMBRE, EST_DESCRIPCION, "
                    + "PCK_NOMBRE, VTA_HORA_ENTREGA_INICIAL, VTA_HORA_ENTREGA_FINAL FROM venta "
                    + "INNER JOIN rrss ON venta.RRSS_ID_RRSS = rrss.RRS_ID_RRSS "
                    + "INNER JOIN comunas ON venta.COM_ID_COMUNA = comunas.COM_ID_COMUNA "
                    + "INNER JOIN estados_venta ON venta.ESTADOS_ID_ESTADO = estados_venta.EST_ID_ESTADO "
                    + "INNER JOIN pack ON venta.PCK_ID_PACK = pack.PCK_ID_PACK where VTA_ID_VENTA LIKE '"+campo1+"'");
            id_nomb.next();
            
            if ("".equals(campo1)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
                
            }else if (rs.next()){
                
                venta_nom_cli_field.setText(rs.getString(1));
                venta_email_field.setText(rs.getString(2));
                venta_telefono_field.setText(rs.getString(3));
                venta_rut_field.setText(id_nomb.getString(1));
                venta_destinatario_field.setText(id_nomb.getString(2));
                venta_fecha_entrega.setText(id_nomb.getString(3));
                venta_direccion_field.setText(id_nomb.getString(4));
                venta_comuna_box.setSelectedItem(id_nomb.getString(5));
                venta_saludo_field.setText(id_nomb.getString(6));
                venta_rrss_box.setSelectedItem(id_nomb.getString(7));
                venta_estado_box.setSelectedItem(id_nomb.getString(8));
                venta_pack_box.setSelectedItem(id_nomb.getString(9));
                venta_fecha_entrega_ini.setText(id_nomb.getString(10));
                venta_entrega_fin.setText(id_nomb.getString(11));
                
             } else {
                    JOptionPane.showMessageDialog(null, "RUT NO REGISTRADO");
                    st.close();
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void BUSCAR_RUT(){
        Statement st;
        String campo1 = venta_rut_field.getText();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CLI_NOMBRE, CLI_CORREO, CLI_CELULAR FROM cliente where CLI_ID_CLIENTE LIKE '"+campo1+"'");
            
            if (rs.next()){
                venta_nom_cli_field.setText(rs.getString(1));
                venta_email_field.setText(rs.getString(2));
                venta_telefono_field.setText(rs.getString(3));
                
            } else {
                    JOptionPane.showMessageDialog(null, "RUT NO REGISTRADO");
                    st.close();
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Mostrar_NUMEROPEDIDO(){
        Statement st;  
        Statement stc;
        String campo1 = ven_con_rut_field.getText();
        String campo2 = ven_con_n_pedido_field.getText();
        try {
            
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT VTA_ID_VENTA, CLI_ID_CLIENTE, VTA_CODIGO_TRANSFERENCIA, VTA_FECHA_TRANSFERENCIA, BAN_DESCRIPCION"
                    + " FROM venta INNER JOIN bancos ON venta.BAN_ID_BANCO = bancos.BAN_ID_BANCO where VTA_ID_VENTA LIKE '"+campo2+"'");
            
            stc = con.createStatement();
            ResultSet id_nomb = stc.executeQuery("SELECT CLI_ID_CLIENTE, CLI_NOMBRE FROM cliente where CLI_ID_CLIENTE LIKE '"+campo1+"'");
            id_nomb.next();
            
            while (rs.next()){
                ven_con_n_pedido_field.setText(rs.getString(1));
                ven_con_rut_field.setText(rs.getString(2));
                ven_con_nom_cliente_field.setText(id_nomb.getString(2));
                ven_con_cod_tran_field.setText(rs.getString(3));
                ven_con_fecha_pago_field.setText(rs.getString(4));
                ven_con_banco_combox.setSelectedItem(rs.getString(5));
                
            }
            st.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    private void CREAR_VENTA(){
        String campo1 = venta_num_pedido_field.getText();
        Integer campo2 = (Integer) venta_comuna_box.getSelectedItem();
        //String campo3 = (String) venta_rrss_box.getSelectedItem();
        //String campo4 = (String) venta_estado_box.getSelectedItem();
        //String campo5 = (String) venta_pack_box.getSelectedItem();
                

        try {
            
            if ("".equals(campo1) || "".equals(campo2)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
            }
            
            else {
                PreparedStatement pps = con.prepareStatement("INSERT INTO venta (VTA_ID_VENTA, CLI_ID_CLIENTE, "
                        + "VTA_NOMBRE_DESTINATARIO, VTA_FECHA_ENTREGA, VTA_DIRECCION_DESTINATARIO, COM_ID_COMUNA) VALUES (?,?,?,?,?,?)");
                /*
                PreparedStatement pps = con.prepareStatement("INSERT INTO venta (VTA_ID_VENTA, CLI_ID_CLIENTE, "
                        + "VTA_NOMBRE_DESTINATARIO, VTA_FECHA_ENTREGA, "
                        + "VTA_DIRECCION_DESTINATARIO, COM_ID_COMUNA, VTA_SALUDO, RRSS_ID_RRSS, ESTADOS_ID_ESTADO, "
                        + "PCK_ID_PACK, VTA_HORA_ENTREGA_INICIAL, VTA_HORA_ENTREGA_FINAL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                */
                pps.setString(1, campo1);
                pps.setString(2, venta_rut_field.getText());
                pps.setString(3, venta_destinatario_field.getText());
                pps.setString(4, venta_fecha_entrega.getText());
                pps.setString(5, venta_direccion_field.getText());
                pps.setInt(6, campo2);
                /*
                pps.setString(7, venta_saludo_field.getText());
                pps.setString(8, campo3);
                pps.setString(9, campo4);
                pps.setString(10, campo5);
                pps.setString(11, venta_fecha_entrega_ini.getText());
                pps.setString(12, venta_entrega_fin.getText());
                */
                pps.executeUpdate();
                pps.close();
                JOptionPane.showMessageDialog(null, "DATOS VENTA CREADOS CON ÉXITO!!!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR - CAMBIE NUMERO DE PEDIDO");
        }
        
    }
    
    
    private void MODIFICAR_CONFIRMACION(){
        String campo1 = ven_con_n_pedido_field.getText();
        String campo2 = (String) ven_con_banco_combox.getSelectedItem();
        
        int flag = 1;
  
        try {
            
            if ("".equals(campo1) || "".equals(campo2)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
            }
            
            else {
                PreparedStatement pps;
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT BAN_ID_BANCO FROM bancos");
                
                while (rs.next()){
                    if (rs.getString(1).equals(campo1)){
                        flag = 0;
                    } 
                }   
                Statement stp;
                stp = con.createStatement();
                ResultSet id_banco = stp.executeQuery("SELECT BAN_ID_BANCO FROM bancos WHERE BAN_DESCRIPCION = '"+campo2+"'");
                id_banco.next();
                if (flag == 0){
                    pps = con.prepareStatement("update venta set BAN_ID_BANCO = ? where VTA_ID_VENTA = ?;");
                    pps.setString(1, campo1);
                    pps.executeUpdate();
                    pps.close();
                    JOptionPane.showMessageDialog(null,"DATOS ACTUALIZADOS");
                    ven_con_n_pedido_field.setEnabled(true);
                }
                else{
                    pps = con.prepareStatement("INSERT INTO venta (BAN_ID_BANCO) VALUES (?)");
                    pps.setString(1, campo1);
                    pps.executeUpdate();
                    pps.close();
                    JOptionPane.showMessageDialog(null,"DATOS GUARDADOS 2");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }   

    }
    
    
    private void cargarLista1(){
        lista1.add("Tazón personalizado");
        lista1.add("Té");
        lista1.add("Jugo natural");
        lista1.add("Cereal");
        lista1.add("Flores");
        lista1.add("Cerveza");
        lista1.add("Pastel");
        lista1.add("Capuccino café sobre");
        packeditor_list.setListData(lista1);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_tab_menu = new javax.swing.JTabbedPane();
        tab_ventas = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        pesta_ventas = new javax.swing.JTabbedPane();
        tab_datosventa = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        venta_num_pedido_field = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        venta_nom_cli_field = new javax.swing.JTextField();
        venta_email_field = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        venta_telefono_field = new javax.swing.JTextField();
        venta_buscar_rut = new javax.swing.JButton();
        venta_rut_field = new javax.swing.JTextField();
        venta_buscar_n_pedido = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        venta_destinatario_field = new javax.swing.JTextField();
        venta_bt_guardar = new javax.swing.JButton();
        venta_bt_cancelar = new javax.swing.JButton();
        venta_entrega_ini = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        venta_direccion_field = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        venta_comuna_box = new javax.swing.JComboBox<>();
        jLabel92 = new javax.swing.JLabel();
        venta_saludo_field = new javax.swing.JTextField();
        venta_pack_box = new javax.swing.JComboBox<>();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        venta_rrss_box = new javax.swing.JComboBox<>();
        jLabel116 = new javax.swing.JLabel();
        venta_estado_box = new javax.swing.JComboBox<>();
        venta_fecha_entrega = new javax.swing.JTextField();
        venta_fecha_entrega_ini = new javax.swing.JTextField();
        venta_entrega_fin = new javax.swing.JTextField();
        venta_bt_modificar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        result_subtotal = new javax.swing.JLabel();
        result_envio = new javax.swing.JLabel();
        result_total = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        tab_confirmpago = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        ven_con_n_pedido_field = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        ven_con_rut_field = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        ven_con_n_bt_buscar = new javax.swing.JButton();
        ven_con_banco_combox = new javax.swing.JComboBox<>();
        jLabel100 = new javax.swing.JLabel();
        ven_con_nom_cliente_field = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        ven_con_cod_tran_field = new javax.swing.JTextField();
        ven_con_bt_confirmar = new javax.swing.JButton();
        ven_con_bt_cancelar = new javax.swing.JButton();
        ven_con_fecha_pago_field = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        ven_con_bar_buscar = new javax.swing.JFormattedTextField();
        ven_con_bt_buscar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        ven_con_tabla = new javax.swing.JTable();
        ven_con_bt_desactivar = new javax.swing.JButton();
        tab_listadestinos = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jFormattedTextField15 = new javax.swing.JFormattedTextField();
        jButton57 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla_destinos = new javax.swing.JTable();
        jButton47 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        bt_recargar = new javax.swing.JButton();
        tab_actudespacho = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        jFormattedTextField17 = new javax.swing.JFormattedTextField();
        jButton61 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabla_despachos = new javax.swing.JTable();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        bt_recargar1 = new javax.swing.JButton();
        tab_compras = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        pesta_compras = new javax.swing.JTabbedPane();
        tab_solpedido = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jScrollPane25 = new javax.swing.JScrollPane();
        packeditor_list1 = new javax.swing.JList<>();
        topack_button1 = new javax.swing.JButton();
        addunits_spinner1 = new javax.swing.JSpinner();
        frompack_button1 = new javax.swing.JButton();
        jScrollPane26 = new javax.swing.JScrollPane();
        packedited_list1 = new javax.swing.JList<>();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        Packs1 = new javax.swing.JLabel();
        jScrollPane27 = new javax.swing.JScrollPane();
        packs_table1 = new javax.swing.JTable();
        deactivatepack_button1 = new javax.swing.JButton();
        deactivatepack_button2 = new javax.swing.JButton();
        deactivatepack_button3 = new javax.swing.JButton();
        tab_regcompra = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jButton60 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jButton72 = new javax.swing.JButton();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jButton73 = new javax.swing.JButton();
        jButton74 = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jLabel114 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel115 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox10 = new javax.swing.JComboBox<>();
        jComboBox11 = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        tab_informes = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        pesta_admin1 = new javax.swing.JTabbedPane();
        tab_informe_ventas = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        inf_ven_bt_buscar = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        inf_ven_fecha_d = new com.toedter.calendar.JDateChooser();
        inf_ven_fecha_h = new com.toedter.calendar.JDateChooser();
        jLabel55 = new javax.swing.JLabel();
        inf_ven_rut = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        inf_ven_tabla = new javax.swing.JTable();
        inf_ven_bt_desc = new javax.swing.JButton();
        inf_ven_bt_buscar_bar = new javax.swing.JButton();
        inf_ven_field_buscar_bar = new javax.swing.JTextField();
        tab_informe_inventario = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        inf_inv_bt_buscar = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        inf_inv_fecha_d = new com.toedter.calendar.JDateChooser();
        inf_inv_fecha_h = new com.toedter.calendar.JDateChooser();
        jLabel58 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        inf_inv_cat_art = new javax.swing.JComboBox<>();
        inf_inv_fecha_venc = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        inf_inv_prov = new javax.swing.JComboBox<>();
        inf_inv_field_buscar_bar = new javax.swing.JFormattedTextField();
        jLabel59 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        inf_inv_tabla = new javax.swing.JTable();
        inf_inv_bt_desc = new javax.swing.JButton();
        inf_inv_bt_buscar_bar = new javax.swing.JButton();
        tab_informe_clientes = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        inf_cli_bt_buscar = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        inf_cli_fecha_d = new com.toedter.calendar.JDateChooser();
        inf_cli_fecha_h = new com.toedter.calendar.JDateChooser();
        jLabel75 = new javax.swing.JLabel();
        inf_cli_rut = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        inf_cli_tabla = new javax.swing.JTable();
        inf_cli_bt_desc = new javax.swing.JButton();
        inf_cli_field_buscar = new javax.swing.JFormattedTextField();
        inf_cli_bt_buscar_bar = new javax.swing.JButton();
        tab_informe_cambios = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        inf_dev_bt_buscar = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        inf_dev_fecha_d = new com.toedter.calendar.JDateChooser();
        inf_dev_fecha_h = new com.toedter.calendar.JDateChooser();
        jLabel79 = new javax.swing.JLabel();
        inf_dev_rut = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        inf_dev_tabla = new javax.swing.JTable();
        inf_dev_bt_desc = new javax.swing.JButton();
        inf_dev_field_buscar = new javax.swing.JFormattedTextField();
        inf_dev_bt_buscar_bar = new javax.swing.JButton();
        tab_administrador = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        pesta_admin = new javax.swing.JTabbedPane();
        tab_clientes = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        clientname = new javax.swing.JLabel();
        cli_nombre_field = new javax.swing.JTextField();
        cli_bt_guardar = new javax.swing.JButton();
        cli_bt_cancelar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cli_telefono_field = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cli_email_field = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cli_rut_field = new javax.swing.JTextField();
        cli_fec_nacimiento_field = new com.toedter.calendar.JDateChooser();
        cli_buscar_bar = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        cli_bt_desactivar = new javax.swing.JButton();
        cli_bt_editar = new javax.swing.JButton();
        cli_bt_buscar = new javax.swing.JButton();
        jScrollPane23 = new javax.swing.JScrollPane();
        cli_tabla = new javax.swing.JTable();
        tab_provee = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        prov_rut_field = new javax.swing.JTextField();
        prov_bt_guardar = new javax.swing.JButton();
        prov_bt_cancelar = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        prov_nombre_field = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        prov_direccion_field = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        prov_email_field = new javax.swing.JTextField();
        prov_razon_field = new javax.swing.JTextField();
        prov_telefono_field = new javax.swing.JTextField();
        prov_buscar_bar = new javax.swing.JFormattedTextField();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        prov_tabla = new javax.swing.JTable();
        prov_bt_desactivar = new javax.swing.JButton();
        prov_bt_editar = new javax.swing.JButton();
        prov_bt_buscar = new javax.swing.JButton();
        tab_articulos = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        tab_packs = new javax.swing.JPanel();
        packs_panel = new javax.swing.JPanel();
        namepack = new javax.swing.JLabel();
        namepack_field = new javax.swing.JTextField();
        savepack_button = new javax.swing.JButton();
        cancelpack_button = new javax.swing.JButton();
        pricepack = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        packedited_list = new javax.swing.JList<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        packeditor_list = new javax.swing.JList<>();
        topack_button = new javax.swing.JButton();
        frompack_button = new javax.swing.JButton();
        addunits_spinner = new javax.swing.JSpinner();
        pricepack_field = new javax.swing.JTextField();
        jFormattedTextField10 = new javax.swing.JFormattedTextField();
        Packs = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        packs_table = new javax.swing.JTable();
        deactivatepack_button = new javax.swing.JButton();
        editpack_button = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        tab_rrss = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        rrss_buscar_bar = new javax.swing.JFormattedTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        rrss_bt_buscar = new javax.swing.JButton();
        tab_categorias = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        cat_art_nombre_field = new javax.swing.JTextField();
        cat_art_bt_guardar = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        cat_art_codigo_field = new javax.swing.JTextField();
        cat_art_buscar_bar = new javax.swing.JFormattedTextField();
        jLabel40 = new javax.swing.JLabel();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        cat_art_bt_buscar = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        cat_art_tabla = new javax.swing.JTable();
        tab_comunas = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        comu_nombre_field = new javax.swing.JTextField();
        comu_bt_guardar = new javax.swing.JButton();
        comu_can = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        comu_codigo_field = new javax.swing.JTextField();
        comu_buscar_bar = new javax.swing.JFormattedTextField();
        jLabel28 = new javax.swing.JLabel();
        comu_desactivar = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        comu_bt_buscar = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        comu_tabla = new javax.swing.JTable();
        tab_bancos = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ban_nombre_field = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ban_codigo_field = new javax.swing.JTextField();
        ban_bt_guardar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ban_buscar_bar = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        ban_desactivar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        ban_bt_buscar = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        ban_tabla = new javax.swing.JTable();
        tab_categorias1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        cat_ven_nombre_field = new javax.swing.JTextField();
        cat_ven_bt_guardar = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        cat_ven_codigo_field = new javax.swing.JTextField();
        cat_ven_buscar_bar = new javax.swing.JFormattedTextField();
        jLabel43 = new javax.swing.JLabel();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        cat_ven_bt_buscar = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        cat_ven_tabla = new javax.swing.JTable();
        tab_usuarios = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        username_field = new javax.swing.JTextField();
        saveuser_button = new javax.swing.JButton();
        canceluser_button = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        pass_field = new javax.swing.JPasswordField();
        repet_pass_field = new javax.swing.JPasswordField();
        usu_buscar_bar = new javax.swing.JFormattedTextField();
        jLabel34 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        usu_bt_buscar = new javax.swing.JButton();
        jScrollPane22 = new javax.swing.JScrollPane();
        usuario_tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Panel_tab_menu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Panel_tab_menu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tab_ventas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Segoe Script", 3, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Dream Gifts");

        pesta_ventas.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        pesta_ventas.setToolTipText("");
        pesta_ventas.setAlignmentX(20.0F);
        pesta_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pesta_ventas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos cliente solicitante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel80.setText("Número pedido:");

        venta_num_pedido_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_num_pedido_field.setText("12");
        venta_num_pedido_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_num_pedido_fieldActionPerformed(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel81.setText("RUT");

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel86.setText("Nombre cliente:");

        venta_nom_cli_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_nom_cli_field.setText("Francisco Tapiado");
        venta_nom_cli_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_nom_cli_fieldActionPerformed(evt);
            }
        });

        venta_email_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_email_field.setText("fjtp1407@gmail.com");
        venta_email_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_email_fieldActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel87.setText("Email:");

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel88.setText("Teléfono");

        venta_telefono_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_telefono_field.setText("228575245");
        venta_telefono_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_telefono_fieldActionPerformed(evt);
            }
        });

        venta_buscar_rut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_buscar_rut.setText("Buscar");
        venta_buscar_rut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_buscar_rutActionPerformed(evt);
            }
        });

        venta_rut_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_rut_field.setText("13562567-8");
        venta_rut_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_rut_fieldActionPerformed(evt);
            }
        });

        venta_buscar_n_pedido.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_buscar_n_pedido.setText("Rellenar");
        venta_buscar_n_pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_buscar_n_pedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(venta_num_pedido_field, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(venta_buscar_n_pedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(223, 223, 223))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel86)
                            .addComponent(jLabel87))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(venta_email_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                            .addComponent(venta_nom_cli_field))))
                .addGap(100, 100, 100)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel22Layout.createSequentialGroup()
                        .addComponent(venta_rut_field, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(venta_buscar_rut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(venta_telefono_field, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel80)
                        .addComponent(venta_num_pedido_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel81)
                        .addComponent(venta_buscar_n_pedido))
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(venta_buscar_rut)
                        .addComponent(venta_rut_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(venta_nom_cli_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88)
                    .addComponent(venta_telefono_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(venta_email_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos destinatario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel84.setText("Nombre destinatario:");

        venta_destinatario_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_destinatario_field.setText("Nombre de Prueba");
        venta_destinatario_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_destinatario_fieldActionPerformed(evt);
            }
        });

        venta_bt_guardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_bt_guardar.setText("Guardar");
        venta_bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_bt_guardarActionPerformed(evt);
            }
        });

        venta_bt_cancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_bt_cancelar.setText("Cancelar");
        venta_bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_bt_cancelarActionPerformed(evt);
            }
        });

        venta_entrega_ini.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_entrega_ini.setText("Horario Inicio Entrega:");

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel89.setText("Fecha entrega:");

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel90.setText("Dirección:");

        venta_direccion_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_direccion_field.setText("Pasaje 1");
        venta_direccion_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_direccion_fieldActionPerformed(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel91.setText("Comuna:");

        venta_comuna_box.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_comuna_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comuna 1", "Comuna 2", "Comuna 3", "Comuna 4" }));

        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel92.setText("Saludo:");

        venta_saludo_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_saludo_field.setText("Saludo de Prueba");

        venta_pack_box.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_pack_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pack 1", "Pack 2", " " }));

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel93.setText("Hora Fin Entrega:");

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel94.setText("Pack:");

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel106.setText("Red Social:");

        venta_rrss_box.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_rrss_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel116.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel116.setText("Estado:");

        venta_estado_box.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_estado_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En Proceso", "Pagado", "No Pagado", " " }));

        venta_bt_modificar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        venta_bt_modificar.setText("Modificar");
        venta_bt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_bt_modificarActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Subtotal:");

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText("Envíos:");

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setText("TOTAL:");

        result_subtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        result_subtotal.setText("$0");

        result_envio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        result_envio.setText("$0");

        result_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        result_total.setText("$0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(result_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(result_envio, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(result_total, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(67, 67, 67))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(result_subtotal))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(result_envio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(result_total))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                    .addComponent(jLabel84))
                .addGap(30, 30, 30)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(venta_destinatario_field)
                    .addComponent(venta_saludo_field)
                    .addComponent(venta_direccion_field, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(venta_comuna_box, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(venta_fecha_entrega))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(venta_pack_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(venta_estado_box, 0, 267, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                                .addComponent(venta_rrss_box, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(venta_entrega_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                                    .addComponent(venta_entrega_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(venta_fecha_entrega_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(venta_bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(venta_bt_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(venta_bt_modificar)
                .addGap(67, 67, 67))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(venta_rrss_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel106))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(venta_estado_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel116))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel94)
                            .addComponent(venta_pack_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(venta_entrega_ini)
                            .addComponent(venta_fecha_entrega_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel93)
                            .addComponent(venta_entrega_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel84)
                            .addComponent(venta_destinatario_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(venta_fecha_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel90)
                            .addComponent(venta_direccion_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel91)
                            .addComponent(venta_comuna_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel92)
                            .addComponent(venta_saludo_field, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(venta_bt_cancelar)
                    .addComponent(venta_bt_guardar)
                    .addComponent(venta_bt_modificar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tab_datosventaLayout = new javax.swing.GroupLayout(tab_datosventa);
        tab_datosventa.setLayout(tab_datosventaLayout);
        tab_datosventaLayout.setHorizontalGroup(
            tab_datosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_datosventaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_datosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 1294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        tab_datosventaLayout.setVerticalGroup(
            tab_datosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_datosventaLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pesta_ventas.addTab("Venta", tab_datosventa);

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Confirma pago cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel82.setText("Número pedido:");

        ven_con_n_pedido_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_n_pedido_field.setText("12");
        ven_con_n_pedido_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_n_pedido_fieldActionPerformed(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel96.setText("Rut:");

        ven_con_rut_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_rut_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_rut_fieldActionPerformed(evt);
            }
        });

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel97.setText("Banco:");

        ven_con_n_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_n_bt_buscar.setText("Buscar");
        ven_con_n_bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_n_bt_buscarActionPerformed(evt);
            }
        });

        ven_con_banco_combox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_banco_combox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_banco_comboxActionPerformed(evt);
            }
        });

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel100.setText("Nombre cliente:");

        ven_con_nom_cliente_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_nom_cliente_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_nom_cliente_fieldActionPerformed(evt);
            }
        });

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel101.setText("Fecha de pago:");

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel102.setText("Código Transacción:");

        ven_con_cod_tran_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_cod_tran_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_cod_tran_fieldActionPerformed(evt);
            }
        });

        ven_con_bt_confirmar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_bt_confirmar.setText("Guardar");
        ven_con_bt_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_bt_confirmarActionPerformed(evt);
            }
        });

        ven_con_bt_cancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_bt_cancelar.setText("Cancelar");
        ven_con_bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_bt_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel97)
                    .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ven_con_rut_field, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addComponent(ven_con_n_pedido_field, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ven_con_n_bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ven_con_banco_combox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(ven_con_bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(ven_con_bt_confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel102))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ven_con_cod_tran_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ven_con_nom_cliente_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ven_con_fecha_pago_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel100)
                            .addComponent(ven_con_nom_cliente_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel101)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(ven_con_fecha_pago_field, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ven_con_cod_tran_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel102))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ven_con_bt_confirmar)
                            .addComponent(ven_con_bt_cancelar)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel82)
                                .addComponent(ven_con_n_pedido_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ven_con_n_bt_buscar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ven_con_rut_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel96))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97)
                            .addComponent(ven_con_banco_combox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel108.setText("Ventas pendientes de pago");

        ven_con_bar_buscar.setToolTipText("buscar...");
        ven_con_bar_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_bar_buscarActionPerformed(evt);
            }
        });

        ven_con_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_bt_buscar.setText("Buscar");
        ven_con_bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_bt_buscarActionPerformed(evt);
            }
        });

        jScrollPane5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        ven_con_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Número Pedido", "Cod Transaccion", "Rut Cliente", "Banco", "Estado", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ven_con_tabla.setColumnSelectionAllowed(true);
        ven_con_tabla.getTableHeader().setReorderingAllowed(false);
        ven_con_tabla.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                ven_con_tablaComponentAdded(evt);
            }
        });
        jScrollPane5.setViewportView(ven_con_tabla);
        ven_con_tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        ven_con_bt_desactivar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ven_con_bt_desactivar.setText("Desactivar");
        ven_con_bt_desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ven_con_bt_desactivarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab_confirmpagoLayout = new javax.swing.GroupLayout(tab_confirmpago);
        tab_confirmpago.setLayout(tab_confirmpagoLayout);
        tab_confirmpagoLayout.setHorizontalGroup(
            tab_confirmpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_confirmpagoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(tab_confirmpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab_confirmpagoLayout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(ven_con_bt_desactivar)
                        .addGap(69, 69, 69)
                        .addComponent(ven_con_bar_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(ven_con_bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        tab_confirmpagoLayout.setVerticalGroup(
            tab_confirmpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_confirmpagoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(tab_confirmpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ven_con_bar_buscar, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel108)
                    .addComponent(ven_con_bt_buscar)
                    .addComponent(ven_con_bt_desactivar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280))
        );

        pesta_ventas.addTab("Confirmación", tab_confirmpago);

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Despacho", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setText("Ventas pendientes de pago");

        jFormattedTextField15.setToolTipText("buscar...");
        jFormattedTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField15ActionPerformed(evt);
            }
        });

        jButton57.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton57.setText("Buscar");

        jScrollPane6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tabla_destinos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla_destinos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Pedido", "Nombre Pack", "Destinatario", "Comuna", "Dirección", "Fecha Entrega", "Hora Entrega", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabla_destinos.getTableHeader().setReorderingAllowed(false);
        tabla_destinos.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tabla_destinosComponentAdded(evt);
            }
        });
        jScrollPane6.setViewportView(tabla_destinos);

        jButton47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton47.setText("Imprimir");

        jButton28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton28.setText("Descargar");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        bt_recargar.setText("RECARGAR");
        bt_recargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_recargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1320, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(bt_recargar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                    .addComponent(bt_recargar))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton47)
                    .addComponent(jButton28))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout tab_listadestinosLayout = new javax.swing.GroupLayout(tab_listadestinos);
        tab_listadestinos.setLayout(tab_listadestinosLayout);
        tab_listadestinosLayout.setHorizontalGroup(
            tab_listadestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_listadestinosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );
        tab_listadestinosLayout.setVerticalGroup(
            tab_listadestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_listadestinosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(393, Short.MAX_VALUE))
        );

        pesta_ventas.addTab("Lista Destinos", tab_listadestinos);

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Despacho", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel111.setText("Actualización Despacho");

        jFormattedTextField17.setToolTipText("buscar...");
        jFormattedTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField17ActionPerformed(evt);
            }
        });

        jButton61.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton61.setText("Buscar");

        jScrollPane9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tabla_despachos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla_despachos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Pedido", "Nombre Pack", "Destinatario", "Comuna", "Fecha Entrega", "Hora Entrega", "Estado de Venta", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabla_despachos.getTableHeader().setReorderingAllowed(false);
        tabla_despachos.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tabla_despachosComponentAdded(evt);
            }
        });
        jScrollPane9.setViewportView(tabla_despachos);

        jButton49.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton49.setText("Imprimir");

        jButton50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton50.setText("Descargar");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        bt_recargar1.setText("RECARGAR");
        bt_recargar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_recargar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1320, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(bt_recargar1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(bt_recargar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton49)
                    .addComponent(jButton50))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout tab_actudespachoLayout = new javax.swing.GroupLayout(tab_actudespacho);
        tab_actudespacho.setLayout(tab_actudespachoLayout);
        tab_actudespachoLayout.setHorizontalGroup(
            tab_actudespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_actudespachoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );
        tab_actudespachoLayout.setVerticalGroup(
            tab_actudespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_actudespachoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(393, Short.MAX_VALUE))
        );

        pesta_ventas.addTab("Actualización Despachos", tab_actudespacho);

        javax.swing.GroupLayout tab_ventasLayout = new javax.swing.GroupLayout(tab_ventas);
        tab_ventas.setLayout(tab_ventasLayout);
        tab_ventasLayout.setHorizontalGroup(
            tab_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addComponent(jSeparator6)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tab_ventasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pesta_ventas)
                .addGap(48, 48, 48))
        );
        tab_ventasLayout.setVerticalGroup(
            tab_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_ventasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesta_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Panel_tab_menu.addTab("Ventas", tab_ventas);

        tab_compras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setFont(new java.awt.Font("Segoe Script", 3, 48)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Dream Gifts");

        pesta_compras.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        pesta_compras.setToolTipText("");
        pesta_compras.setAlignmentX(20.0F);
        pesta_compras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pesta_compras.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Solicitudes de Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel95.setText("Número pedido:");

        jTextField17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField17.setText("00012");
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jButton55.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton55.setText("Guardar");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        jButton56.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton56.setText("Cancelar");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel98.setText("Fecha Pedido:");

        jButton23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton23.setText("Buscar");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        packeditor_list1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        packeditor_list1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane25.setViewportView(packeditor_list1);

        topack_button1.setText(">");
        topack_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topack_button1ActionPerformed(evt);
            }
        });

        frompack_button1.setText("<");
        frompack_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frompack_button1ActionPerformed(evt);
            }
        });

        packedited_list1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        packedited_list1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane26.setViewportView(packedited_list1);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(frompack_button1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(addunits_spinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(topack_button1, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel95)
                        .addGap(41, 41, 41)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jDateChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jDateChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel98))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel95)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton23)))
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(topack_button1)
                        .addGap(14, 14, 14)
                        .addComponent(addunits_spinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(frompack_button1))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton55)
                    .addComponent(jButton56))
                .addGap(30, 30, 30))
        );

        Packs1.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        Packs1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Packs1.setText("Detalle de pedidos realizados");

        jScrollPane27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        packs_table1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        packs_table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Número Pedido", "Fecha Pedido", "Cantidad de artículos", "Selección"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        packs_table1.getTableHeader().setReorderingAllowed(false);
        packs_table1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                packs_table1ComponentAdded(evt);
            }
        });
        jScrollPane27.setViewportView(packs_table1);

        deactivatepack_button1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deactivatepack_button1.setText("Generar Orden de Compra");

        deactivatepack_button2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deactivatepack_button2.setText("Ver");

        deactivatepack_button3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deactivatepack_button3.setText("Editar");
        deactivatepack_button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivatepack_button3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab_solpedidoLayout = new javax.swing.GroupLayout(tab_solpedido);
        tab_solpedido.setLayout(tab_solpedidoLayout);
        tab_solpedidoLayout.setHorizontalGroup(
            tab_solpedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_solpedidoLayout.createSequentialGroup()
                .addGroup(tab_solpedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_solpedidoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deactivatepack_button3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(deactivatepack_button2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deactivatepack_button1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab_solpedidoLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_solpedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab_solpedidoLayout.createSequentialGroup()
                                .addComponent(Packs1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(50, 50, 50))
                            .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane27))))
                .addGap(50, 50, 50))
        );
        tab_solpedidoLayout.setVerticalGroup(
            tab_solpedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_solpedidoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(Packs1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(tab_solpedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deactivatepack_button1)
                    .addComponent(deactivatepack_button2)
                    .addComponent(deactivatepack_button3))
                .addGap(319, 319, 319))
        );

        pesta_compras.addTab("Solicitudes Pedido", tab_solpedido);

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Registro Factura de Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel99.setText("Número factura:");

        jTextField23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField23.setText("00012");
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jButton60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton60.setText("Guardar");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });

        jButton62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton62.setText("Cancelar");
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton62ActionPerformed(evt);
            }
        });

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel103.setText("Razón Social Proveedor:");

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel104.setText("Rut:");

        jTextField36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField36.setText("Francisco Tapiado");
        jTextField36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField36ActionPerformed(evt);
            }
        });

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel105.setText("Fecha Recepción:");

        jButton72.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton72.setText("Buscar");
        jButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton72ActionPerformed(evt);
            }
        });

        jDateChooser5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField36, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel27Layout.createSequentialGroup()
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jButton72, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(43, 43, 43))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel99)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel103)
                            .addComponent(jButton72)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel104)
                            .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel105))
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton60)
                    .addComponent(jButton62))
                .addGap(30, 30, 30))
        );

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos destinatario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel107.setText("Nombre destinatario:");

        jTextField24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField24.setText("Facebook");
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        jButton73.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton73.setText("Guardar");
        jButton73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton73ActionPerformed(evt);
            }
        });

        jButton74.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton74.setText("Cancelar");
        jButton74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton74ActionPerformed(evt);
            }
        });

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel110.setText("Horario Inicio Entrega:");

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel112.setText("Fecha entrega:");

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel113.setText("Dirección:");

        jTextField41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField41.setText("Facebook");
        jTextField41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField41ActionPerformed(evt);
            }
        });

        jLabel114.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel114.setText("Comuna:");

        jComboBox9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel115.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel115.setText("Saludo:");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField2.setText("jTextField1");

        jComboBox10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel125.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel125.setText("Hora Fin Entrega:");

        jLabel126.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel126.setText("Pack:");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton74, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton73, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextField41, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel107)
                                .addGap(30, 30, 30)
                                .addComponent(jTextField24)))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel126, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox10, javax.swing.GroupLayout.Alignment.TRAILING, 0, 267, Short.MAX_VALUE)
                                .addComponent(jComboBox11, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(43, 43, 43))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel126))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel110)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel113)
                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel125))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel114)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel115))
                .addGap(30, 30, 30)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton73)
                    .addComponent(jButton74))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout tab_regcompraLayout = new javax.swing.GroupLayout(tab_regcompra);
        tab_regcompra.setLayout(tab_regcompraLayout);
        tab_regcompraLayout.setHorizontalGroup(
            tab_regcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_regcompraLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(tab_regcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        tab_regcompraLayout.setVerticalGroup(
            tab_regcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_regcompraLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );

        pesta_compras.addTab("Registro Compra", tab_regcompra);

        javax.swing.GroupLayout tab_comprasLayout = new javax.swing.GroupLayout(tab_compras);
        tab_compras.setLayout(tab_comprasLayout);
        tab_comprasLayout.setHorizontalGroup(
            tab_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator7)
            .addComponent(jSeparator8)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tab_comprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pesta_compras)
                .addContainerGap())
        );
        tab_comprasLayout.setVerticalGroup(
            tab_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_comprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesta_compras, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Panel_tab_menu.addTab("Compras", tab_compras);

        tab_informes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel44.setFont(new java.awt.Font("Segoe Script", 3, 48)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Dream Gifts");

        pesta_admin1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        pesta_admin1.setToolTipText("");
        pesta_admin1.setAlignmentX(20.0F);
        pesta_admin1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pesta_admin1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Búsqueda de Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        inf_ven_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_ven_bt_buscar.setText("Buscar");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel45.setText("Búsqueda por RUT:");

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel53.setText("Desde: ");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel55.setText("Hasta: ");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addGap(18, 18, 18)
                        .addComponent(inf_ven_fecha_d, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inf_ven_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 508, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(18, 18, 18)
                        .addComponent(inf_ven_fecha_h, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(254, 254, 254))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(inf_ven_bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(244, 244, 244))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inf_ven_fecha_d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inf_ven_fecha_h, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(inf_ven_bt_buscar)
                    .addComponent(inf_ven_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        jLabel46.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Detalle de Ventas Realizadas");

        jScrollPane13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        inf_ven_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_ven_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Número Pedido", "Rut Cliente", "Nombre Cliente", "Fecha Compra", "Fecha Entrega", "Pack Comprado", "Monto Pagado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        inf_ven_tabla.setColumnSelectionAllowed(true);
        inf_ven_tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(inf_ven_tabla);

        inf_ven_bt_desc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_ven_bt_desc.setText("Descargar");

        inf_ven_bt_buscar_bar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_ven_bt_buscar_bar.setText("Buscar");

        javax.swing.GroupLayout tab_informe_ventasLayout = new javax.swing.GroupLayout(tab_informe_ventas);
        tab_informe_ventas.setLayout(tab_informe_ventasLayout);
        tab_informe_ventasLayout.setHorizontalGroup(
            tab_informe_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_informe_ventasLayout.createSequentialGroup()
                .addGroup(tab_informe_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_informe_ventasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(inf_ven_bt_desc))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_informe_ventasLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_informe_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_informe_ventasLayout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(186, 186, 186)
                                .addComponent(inf_ven_field_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inf_ven_bt_buscar_bar)
                                .addGap(8, 8, 8))
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane13))))
                .addGap(50, 50, 50))
        );
        tab_informe_ventasLayout.setVerticalGroup(
            tab_informe_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_informe_ventasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_informe_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inf_ven_field_buscar_bar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_informe_ventasLayout.createSequentialGroup()
                        .addGroup(tab_informe_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inf_ven_bt_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inf_ven_bt_desc)
                .addGap(30, 30, 30))
        );

        pesta_admin1.addTab("Informe Ventas", tab_informe_ventas);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Búsqueda de Inventario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        inf_inv_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_inv_bt_buscar.setText("Buscar");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("Categoría Articulo");

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel56.setText("Desde: ");

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel58.setText("Hasta: ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Búsqueda por Rango de Fechas Ventas");

        inf_inv_cat_art.setEditable(true);
        inf_inv_cat_art.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Accesorios", "Bebestibles", "Comestibles", "Cajas" }));

        inf_inv_fecha_venc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_inv_fecha_venc.setText("06/06/2021");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("Fecha Vencimiento");

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel49.setText("Proveedor");

        inf_inv_prov.setEditable(true);
        inf_inv_prov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CCU", "Prisa", "Arcor", " " }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel58)
                .addGap(18, 18, 18)
                .addComponent(inf_inv_fecha_h, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inf_inv_prov, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(inf_inv_bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel56)
                        .addGap(18, 18, 18)
                        .addComponent(inf_inv_fecha_d, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 431, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inf_inv_cat_art, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inf_inv_fecha_venc, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(254, 254, 254))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inf_inv_fecha_d, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(inf_inv_cat_art, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inf_inv_fecha_venc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inf_inv_fecha_h, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(inf_inv_prov, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(inf_inv_bt_buscar)))
                .addGap(29, 29, 29))
        );

        inf_inv_field_buscar_bar.setToolTipText("buscar...");

        jLabel59.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Detalle de Inventario");

        jScrollPane14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        inf_inv_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_inv_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código Artículo", "Nombre Artículo", "Stock", "Fecha Vencimiento", "Valor Producto", "Categoría", "Rut Proveedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        inf_inv_tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane14.setViewportView(inf_inv_tabla);

        inf_inv_bt_desc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_inv_bt_desc.setText("Descargar");

        inf_inv_bt_buscar_bar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_inv_bt_buscar_bar.setText("Buscar");

        javax.swing.GroupLayout tab_informe_inventarioLayout = new javax.swing.GroupLayout(tab_informe_inventario);
        tab_informe_inventario.setLayout(tab_informe_inventarioLayout);
        tab_informe_inventarioLayout.setHorizontalGroup(
            tab_informe_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_informe_inventarioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(tab_informe_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_informe_inventarioLayout.createSequentialGroup()
                        .addGap(1058, 1058, 1058)
                        .addComponent(inf_inv_bt_desc))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_informe_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_informe_inventarioLayout.createSequentialGroup()
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(inf_inv_field_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(inf_inv_bt_buscar_bar)
                            .addGap(8, 8, 8))
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane14)))
                .addGap(50, 50, 50))
        );
        tab_informe_inventarioLayout.setVerticalGroup(
            tab_informe_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_informe_inventarioLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_informe_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inf_inv_bt_buscar_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inf_inv_field_buscar_bar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel59))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inf_inv_bt_desc)
                .addGap(30, 30, 30))
        );

        pesta_admin1.addTab("Informe Inventario", tab_informe_inventario);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Búsqueda de Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        inf_cli_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_cli_bt_buscar.setText("Buscar");

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel73.setText("Búsqueda por RUT:");

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel74.setText("Desde: ");

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel75.setText("Hasta: ");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addGap(18, 18, 18)
                        .addComponent(inf_cli_fecha_d, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inf_cli_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 508, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addGap(18, 18, 18)
                        .addComponent(inf_cli_fecha_h, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(inf_cli_bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(254, 254, 254))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inf_cli_fecha_d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inf_cli_fecha_h, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(inf_cli_bt_buscar)
                    .addComponent(inf_cli_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        jLabel76.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("Detalle de Ventas de Clientes");

        jScrollPane15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        inf_cli_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_cli_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código Pack", "Pack", "Fecha Registro", "Cliente", "Estado", "Comuna"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        inf_cli_tabla.setColumnSelectionAllowed(true);
        inf_cli_tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane15.setViewportView(inf_cli_tabla);

        inf_cli_bt_desc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_cli_bt_desc.setText("Descargar");

        inf_cli_field_buscar.setToolTipText("buscar...");

        inf_cli_bt_buscar_bar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_cli_bt_buscar_bar.setText("Buscar");

        javax.swing.GroupLayout tab_informe_clientesLayout = new javax.swing.GroupLayout(tab_informe_clientes);
        tab_informe_clientes.setLayout(tab_informe_clientesLayout);
        tab_informe_clientesLayout.setHorizontalGroup(
            tab_informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_informe_clientesLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(tab_informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_informe_clientesLayout.createSequentialGroup()
                        .addGap(1058, 1058, 1058)
                        .addComponent(inf_cli_bt_desc))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_informe_clientesLayout.createSequentialGroup()
                            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(inf_cli_field_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(inf_cli_bt_buscar_bar)
                            .addGap(8, 8, 8))
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane15)))
                .addGap(50, 50, 50))
        );
        tab_informe_clientesLayout.setVerticalGroup(
            tab_informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_informe_clientesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(tab_informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_informe_clientesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel76))
                    .addGroup(tab_informe_clientesLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(tab_informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(inf_cli_field_buscar)
                            .addComponent(inf_cli_bt_buscar_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inf_cli_bt_desc)
                .addGap(30, 30, 30))
        );

        pesta_admin1.addTab("Informe Clientes", tab_informe_clientes);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Búsqueda de Devoluciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        inf_dev_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_dev_bt_buscar.setText("Buscar");

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel77.setText("Búsqueda por RUT:");

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel78.setText("Desde: ");

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel79.setText("Hasta: ");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(18, 18, 18)
                        .addComponent(inf_dev_fecha_d, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inf_dev_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 508, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addGap(18, 18, 18)
                        .addComponent(inf_dev_fecha_h, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(inf_dev_bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(254, 254, 254))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inf_dev_fecha_d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inf_dev_fecha_h, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(inf_dev_bt_buscar)
                    .addComponent(inf_dev_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        jLabel83.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("Detalle de Devoluciones");

        jScrollPane16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        inf_dev_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_dev_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Registro Venta", "Pack", "Destinatario", "Fecha Entrega", "Comuna", "Hora Entrega", "Devolución", "Ver"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        inf_dev_tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane16.setViewportView(inf_dev_tabla);

        inf_dev_bt_desc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_dev_bt_desc.setText("Descargar");

        inf_dev_field_buscar.setToolTipText("buscar...");

        inf_dev_bt_buscar_bar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inf_dev_bt_buscar_bar.setText("Buscar");

        javax.swing.GroupLayout tab_informe_cambiosLayout = new javax.swing.GroupLayout(tab_informe_cambios);
        tab_informe_cambios.setLayout(tab_informe_cambiosLayout);
        tab_informe_cambiosLayout.setHorizontalGroup(
            tab_informe_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_informe_cambiosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(tab_informe_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_informe_cambiosLayout.createSequentialGroup()
                        .addGap(1058, 1058, 1058)
                        .addComponent(inf_dev_bt_desc))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_informe_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_informe_cambiosLayout.createSequentialGroup()
                            .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(inf_dev_field_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(inf_dev_bt_buscar_bar)
                            .addGap(8, 8, 8))
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane16)))
                .addGap(50, 50, 50))
        );
        tab_informe_cambiosLayout.setVerticalGroup(
            tab_informe_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_informe_cambiosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(tab_informe_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_informe_cambiosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel83))
                    .addGroup(tab_informe_cambiosLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(tab_informe_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(inf_dev_field_buscar)
                            .addComponent(inf_dev_bt_buscar_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inf_dev_bt_desc)
                .addGap(30, 30, 30))
        );

        pesta_admin1.addTab("Informe Dev & Cambios", tab_informe_cambios);

        javax.swing.GroupLayout tab_informesLayout = new javax.swing.GroupLayout(tab_informes);
        tab_informes.setLayout(tab_informesLayout);
        tab_informesLayout.setHorizontalGroup(
            tab_informesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4)
            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_informesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pesta_admin1)
                .addContainerGap())
        );
        tab_informesLayout.setVerticalGroup(
            tab_informesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_informesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pesta_admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        Panel_tab_menu.addTab("Informes", tab_informes);

        tab_administrador.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe Script", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dream Gifts");

        pesta_admin.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        pesta_admin.setToolTipText("");
        pesta_admin.setAlignmentX(20.0F);
        pesta_admin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pesta_admin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        clientname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        clientname.setText("Nombre cliente: ");

        cli_nombre_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cli_nombre_field.setText("Francisco Javier Tapiado Pérez");
        cli_nombre_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cli_nombre_fieldActionPerformed(evt);
            }
        });

        cli_bt_guardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cli_bt_guardar.setText("Guardar");
        cli_bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cli_bt_guardarActionPerformed(evt);
            }
        });

        cli_bt_cancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cli_bt_cancelar.setText("Cancelar");
        cli_bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cli_bt_cancelarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Teléfono:");

        cli_telefono_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cli_telefono_field.setText("228575245");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Email:");

        cli_email_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cli_email_field.setText("fjtp1407@gmail.com");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("RUT:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Fecha de nacimiento:");

        cli_rut_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cli_rut_field.setText("13675655-9");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clientname)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cli_email_field, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                            .addComponent(cli_telefono_field, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                            .addComponent(cli_nombre_field, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE))
                        .addGap(107, 107, 107)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cli_rut_field, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(cli_fec_nacimiento_field, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cli_bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cli_bt_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientname)
                    .addComponent(cli_nombre_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cli_rut_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cli_telefono_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cli_email_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(cli_fec_nacimiento_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(46, 46, 46)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cli_bt_guardar)
                    .addComponent(cli_bt_cancelar))
                .addGap(28, 28, 28))
        );

        cli_buscar_bar.setToolTipText("buscar...");
        cli_buscar_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cli_buscar_barActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Lista Clientes");

        cli_bt_desactivar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cli_bt_desactivar.setText("Desactivar");
        cli_bt_desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cli_bt_desactivarActionPerformed(evt);
            }
        });

        cli_bt_editar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cli_bt_editar.setText("Editar");
        cli_bt_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cli_bt_editarActionPerformed(evt);
            }
        });

        cli_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cli_bt_buscar.setText("Buscar");
        cli_bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cli_bt_buscarActionPerformed(evt);
            }
        });

        jScrollPane23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cli_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cli_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rut", "Nombre", "Telefono", "Correo", "Fecha Nacimiento", "Estado", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        cli_tabla.setColumnSelectionAllowed(true);
        cli_tabla.getTableHeader().setReorderingAllowed(false);
        cli_tabla.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                cli_tablaComponentAdded(evt);
            }
        });
        jScrollPane23.setViewportView(cli_tabla);
        cli_tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout tab_clientesLayout = new javax.swing.GroupLayout(tab_clientes);
        tab_clientes.setLayout(tab_clientesLayout);
        tab_clientesLayout.setHorizontalGroup(
            tab_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_clientesLayout.createSequentialGroup()
                .addGroup(tab_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_clientesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cli_bt_desactivar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cli_bt_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_clientesLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_clientesLayout.createSequentialGroup()
                                .addGroup(tab_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane23)
                                    .addGroup(tab_clientesLayout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cli_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cli_bt_buscar)))
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        tab_clientesLayout.setVerticalGroup(
            tab_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_clientesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cli_buscar_bar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cli_bt_buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cli_bt_desactivar)
                    .addComponent(cli_bt_editar))
                .addGap(76, 76, 76))
        );

        pesta_admin.addTab("Clientes", tab_clientes);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("RUT Proveedor:");

        prov_rut_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_rut_field.setText("70123456-5");
        prov_rut_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prov_rut_fieldActionPerformed(evt);
            }
        });

        prov_bt_guardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_bt_guardar.setText("Guardar");
        prov_bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prov_bt_guardarActionPerformed(evt);
            }
        });

        prov_bt_cancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_bt_cancelar.setText("Cancelar");
        prov_bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prov_bt_cancelarActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("Nombre Contacto:");

        prov_nombre_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_nombre_field.setText("Pedro Perez");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("Dirección:");

        prov_direccion_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_direccion_field.setText("Pasaje 1234");
        prov_direccion_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prov_direccion_fieldActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("Razón social:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("Teléfono:");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("E-mail:");

        prov_email_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_email_field.setText("pedro.p@mcdonals.cl");

        prov_razon_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_razon_field.setText("Mcdonals SPA");
        prov_razon_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prov_razon_fieldActionPerformed(evt);
            }
        });

        prov_telefono_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_telefono_field.setText("912345678");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(prov_direccion_field, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prov_nombre_field, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prov_rut_field, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE))
                        .addGap(82, 82, 82)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(prov_razon_field)
                            .addComponent(prov_email_field, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(prov_telefono_field, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(prov_bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prov_bt_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel33)
                            .addComponent(prov_razon_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(prov_telefono_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(prov_email_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(prov_rut_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(prov_nombre_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(prov_direccion_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))))
                .addGap(42, 42, 42)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prov_bt_guardar)
                    .addComponent(prov_bt_cancelar))
                .addGap(28, 28, 28))
        );

        prov_buscar_bar.setToolTipText("buscar...");
        prov_buscar_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prov_buscar_barActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Proveedores");

        jScrollPane7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        prov_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RUT Proveedor", "Nombre contacto", "Teléfono", "Correo electrónico", "Dirección", "Razón social", "Estado", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        prov_tabla.setColumnSelectionAllowed(true);
        prov_tabla.getTableHeader().setReorderingAllowed(false);
        prov_tabla.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                prov_tablaComponentAdded(evt);
            }
        });
        jScrollPane7.setViewportView(prov_tabla);
        prov_tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        prov_bt_desactivar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_bt_desactivar.setText("Desactivar");
        prov_bt_desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prov_bt_desactivarActionPerformed(evt);
            }
        });

        prov_bt_editar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_bt_editar.setText("Editar");
        prov_bt_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prov_bt_editarActionPerformed(evt);
            }
        });

        prov_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prov_bt_buscar.setText("Buscar");
        prov_bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prov_bt_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab_proveeLayout = new javax.swing.GroupLayout(tab_provee);
        tab_provee.setLayout(tab_proveeLayout);
        tab_proveeLayout.setHorizontalGroup(
            tab_proveeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_proveeLayout.createSequentialGroup()
                .addGroup(tab_proveeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_proveeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(prov_bt_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(prov_bt_desactivar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_proveeLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_proveeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_proveeLayout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prov_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prov_bt_buscar)
                                .addGap(8, 8, 8))
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane7))))
                .addGap(50, 50, 50))
        );
        tab_proveeLayout.setVerticalGroup(
            tab_proveeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_proveeLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_proveeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(prov_buscar_bar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(prov_bt_buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(tab_proveeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prov_bt_desactivar)
                    .addComponent(prov_bt_editar))
                .addGap(76, 76, 76))
        );

        pesta_admin.addTab("Proveedores", tab_provee);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Artículos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Nombre artículo: ");

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField3.setText("Jugo de Mango 500 cc");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("Guardar");

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setText("Cancelar");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Unidades:");

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField5.setText("Un");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Marca:");

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField6.setText("Soprole");

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField7.setText("B001");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Código artículo:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Categoría artículo: ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Fecha de vencimiento:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Proveedor:");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                            .addComponent(jTextField7))
                        .addGap(107, 107, 107)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(28, 28, 28))
        );

        jFormattedTextField2.setToolTipText("buscar...");
        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Artículos");

        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Artículo", "Tipo Artículo", "Unidades", "Fecha Vencimiento", "Marca", "Proveedor", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jTable2ComponentAdded(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(3).setHeaderValue("Unidades");
            jTable2.getColumnModel().getColumn(4).setHeaderValue("Fecha Vencimiento");
            jTable2.getColumnModel().getColumn(5).setHeaderValue("Marca");
            jTable2.getColumnModel().getColumn(6).setHeaderValue("Proveedor");
        }

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("Desactivar");

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setText("Editar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setText("Buscar");

        javax.swing.GroupLayout tab_articulosLayout = new javax.swing.GroupLayout(tab_articulos);
        tab_articulos.setLayout(tab_articulosLayout);
        tab_articulosLayout.setHorizontalGroup(
            tab_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_articulosLayout.createSequentialGroup()
                .addGroup(tab_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_articulosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_articulosLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_articulosLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11)
                                .addGap(8, 8, 8))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))))
                .addGap(50, 50, 50))
        );
        tab_articulosLayout.setVerticalGroup(
            tab_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_articulosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(tab_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(76, 76, 76))
        );

        pesta_admin.addTab("Artículos", tab_articulos);

        packs_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Pack", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        namepack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        namepack.setText("Nombre Pack: ");

        namepack_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        namepack_field.setText("Pack-01 Día del Niño");
        namepack_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namepack_fieldActionPerformed(evt);
            }
        });

        savepack_button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        savepack_button.setText("Guardar");

        cancelpack_button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cancelpack_button.setText("Cancelar");

        pricepack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pricepack.setText("Precio Pack ($):");

        packedited_list.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        packedited_list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane11.setViewportView(packedited_list);

        packeditor_list.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        packeditor_list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane12.setViewportView(packeditor_list);

        topack_button.setText(">");
        topack_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topack_buttonActionPerformed(evt);
            }
        });

        frompack_button.setText("<");
        frompack_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frompack_buttonActionPerformed(evt);
            }
        });

        pricepack_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pricepack_field.setText("54.000");

        javax.swing.GroupLayout packs_panelLayout = new javax.swing.GroupLayout(packs_panel);
        packs_panel.setLayout(packs_panelLayout);
        packs_panelLayout.setHorizontalGroup(
            packs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, packs_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namepack)
                .addGap(15, 15, 15)
                .addGroup(packs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane12)
                    .addComponent(namepack_field, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addGroup(packs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topack_button)
                    .addComponent(frompack_button)
                    .addComponent(addunits_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(packs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(packs_panelLayout.createSequentialGroup()
                        .addComponent(pricepack, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pricepack_field))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, packs_panelLayout.createSequentialGroup()
                        .addGap(0, 335, Short.MAX_VALUE)
                        .addComponent(cancelpack_button, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(savepack_button, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(43, 43, 43))
        );
        packs_panelLayout.setVerticalGroup(
            packs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(packs_panelLayout.createSequentialGroup()
                .addGroup(packs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(packs_panelLayout.createSequentialGroup()
                        .addGroup(packs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namepack)
                            .addComponent(namepack_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pricepack)
                            .addComponent(pricepack_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(packs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(packs_panelLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(topack_button)
                        .addGap(14, 14, 14)
                        .addComponent(addunits_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(frompack_button)))
                .addGap(42, 42, 42)
                .addGroup(packs_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savepack_button)
                    .addComponent(cancelpack_button))
                .addGap(28, 28, 28))
        );

        jFormattedTextField10.setToolTipText("buscar...");
        jFormattedTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField10ActionPerformed(evt);
            }
        });

        Packs.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        Packs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Packs.setText("Packs");

        jScrollPane10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        packs_table.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        packs_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código Pack", "Nombre Pack", "Unidades Bodega", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        packs_table.setColumnSelectionAllowed(true);
        packs_table.getTableHeader().setReorderingAllowed(false);
        packs_table.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                packs_tableComponentAdded(evt);
            }
        });
        jScrollPane10.setViewportView(packs_table);
        packs_table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        deactivatepack_button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deactivatepack_button.setText("Desactivar");

        editpack_button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        editpack_button.setText("Editar");
        editpack_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpack_buttonActionPerformed(evt);
            }
        });

        jButton51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton51.setText("Buscar");

        javax.swing.GroupLayout tab_packsLayout = new javax.swing.GroupLayout(tab_packs);
        tab_packs.setLayout(tab_packsLayout);
        tab_packsLayout.setHorizontalGroup(
            tab_packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_packsLayout.createSequentialGroup()
                .addGroup(tab_packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_packsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(editpack_button, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deactivatepack_button))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_packsLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_packsLayout.createSequentialGroup()
                                .addComponent(Packs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton51)
                                .addGap(8, 8, 8))
                            .addComponent(packs_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane10))))
                .addGap(50, 50, 50))
        );
        tab_packsLayout.setVerticalGroup(
            tab_packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_packsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(packs_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Packs)
                    .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(tab_packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deactivatepack_button)
                    .addComponent(editpack_button))
                .addGap(76, 76, 76))
        );

        pesta_admin.addTab("Packs", tab_packs);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Red social", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Nombre red social:");

        jTextField12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField12.setText("Facebook");
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton17.setText("Guardar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton18.setText("Cancelar");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Código red social:");

        jTextField16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField16.setText("FB");
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(37, 37, 37)
                        .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                        .addGap(93, 93, 93)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17)
                    .addComponent(jButton18))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        rrss_buscar_bar.setToolTipText("buscar...");

        jLabel27.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Redes Sociales");

        jScrollPane4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTable4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre RRSS", "Código RRSS", "Estado", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable4.setColumnSelectionAllowed(true);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jTable4.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jTable4ComponentAdded(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);
        jTable4.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton19.setText("Desactivar");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton20.setText("Editar");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        rrss_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rrss_bt_buscar.setText("Buscar");
        rrss_bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rrss_bt_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab_rrssLayout = new javax.swing.GroupLayout(tab_rrss);
        tab_rrss.setLayout(tab_rrssLayout);
        tab_rrssLayout.setHorizontalGroup(
            tab_rrssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_rrssLayout.createSequentialGroup()
                .addGroup(tab_rrssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_rrssLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton19))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_rrssLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_rrssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_rrssLayout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rrss_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rrss_bt_buscar)
                                .addGap(8, 8, 8))
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
                .addGap(50, 50, 50))
        );
        tab_rrssLayout.setVerticalGroup(
            tab_rrssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_rrssLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_rrssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_rrssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rrss_buscar_bar)
                        .addComponent(jLabel27))
                    .addComponent(rrss_bt_buscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(tab_rrssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19)
                    .addComponent(jButton20))
                .addGap(76, 76, 76))
        );

        pesta_admin.addTab("Redes Sociales", tab_rrss);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Categoría artículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setText("Categoría artículo:");

        cat_art_nombre_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cat_art_nombre_field.setText("Bebestible");
        cat_art_nombre_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat_art_nombre_fieldActionPerformed(evt);
            }
        });

        cat_art_bt_guardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cat_art_bt_guardar.setText("Guardar");
        cat_art_bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat_art_bt_guardarActionPerformed(evt);
            }
        });

        jButton38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton38.setText("Cancelar");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("Código categoría:");

        cat_art_codigo_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cat_art_codigo_field.setText("AB");
        cat_art_codigo_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat_art_codigo_fieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(37, 37, 37)
                        .addComponent(cat_art_nombre_field, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                        .addGap(93, 93, 93)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(cat_art_codigo_field, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cat_art_bt_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(cat_art_nombre_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(cat_art_codigo_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cat_art_bt_guardar)
                    .addComponent(jButton38))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        cat_art_buscar_bar.setToolTipText("buscar...");
        cat_art_buscar_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat_art_buscar_barActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Categorías artículos");

        jButton39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton39.setText("Desactivar");

        jButton40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton40.setText("Editar");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        cat_art_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cat_art_bt_buscar.setText("Buscar");
        cat_art_bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat_art_bt_buscarActionPerformed(evt);
            }
        });

        jScrollPane20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cat_art_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cat_art_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Categoria", "Codigo Categoria", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        cat_art_tabla.getTableHeader().setReorderingAllowed(false);
        cat_art_tabla.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                cat_art_tablaComponentAdded(evt);
            }
        });
        jScrollPane20.setViewportView(cat_art_tabla);
        cat_art_tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout tab_categoriasLayout = new javax.swing.GroupLayout(tab_categorias);
        tab_categorias.setLayout(tab_categoriasLayout);
        tab_categoriasLayout.setHorizontalGroup(
            tab_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_categoriasLayout.createSequentialGroup()
                .addGroup(tab_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_categoriasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton39))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_categoriasLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_categoriasLayout.createSequentialGroup()
                                .addGroup(tab_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane20)
                                    .addGroup(tab_categoriasLayout.createSequentialGroup()
                                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cat_art_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cat_art_bt_buscar)))
                                .addGap(8, 8, 8)))))
                .addGap(50, 50, 50))
        );
        tab_categoriasLayout.setVerticalGroup(
            tab_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_categoriasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cat_art_buscar_bar)
                        .addComponent(jLabel40))
                    .addComponent(cat_art_bt_buscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton39)
                    .addComponent(jButton40))
                .addGap(76, 76, 76))
        );

        pesta_admin.addTab("Categorías Artículos", tab_categorias);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Comuna", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Nombre comuna: ");

        comu_nombre_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comu_nombre_field.setText("Santiago");

        comu_bt_guardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comu_bt_guardar.setText("Guardar");
        comu_bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comu_bt_guardarActionPerformed(evt);
            }
        });

        comu_can.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comu_can.setText("Cancelar");
        comu_can.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comu_canActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("Código comuna:");

        comu_codigo_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comu_codigo_field.setText("01");
        comu_codigo_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comu_codigo_fieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comu_nombre_field, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                        .addGap(107, 107, 107)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(comu_codigo_field, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(comu_can, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comu_bt_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(comu_nombre_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(comu_codigo_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comu_bt_guardar)
                    .addComponent(comu_can))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        comu_buscar_bar.setToolTipText("buscar...");
        comu_buscar_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comu_buscar_barActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Comunas");

        comu_desactivar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comu_desactivar.setText("Desactivar");
        comu_desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comu_desactivarActionPerformed(evt);
            }
        });

        jButton25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton25.setText("Editar");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        comu_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comu_bt_buscar.setText("Buscar");
        comu_bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comu_bt_buscarActionPerformed(evt);
            }
        });

        jScrollPane19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        comu_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comu_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Comuna", "Codigo Comuna", "Estado", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        comu_tabla.setColumnSelectionAllowed(true);
        comu_tabla.getTableHeader().setReorderingAllowed(false);
        comu_tabla.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                comu_tablaComponentAdded(evt);
            }
        });
        jScrollPane19.setViewportView(comu_tabla);
        comu_tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout tab_comunasLayout = new javax.swing.GroupLayout(tab_comunas);
        tab_comunas.setLayout(tab_comunasLayout);
        tab_comunasLayout.setHorizontalGroup(
            tab_comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_comunasLayout.createSequentialGroup()
                .addGroup(tab_comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_comunasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comu_desactivar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_comunasLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_comunasLayout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comu_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comu_bt_buscar)
                                .addGap(8, 8, 8))
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane19))))
                .addGap(50, 50, 50))
        );
        tab_comunasLayout.setVerticalGroup(
            tab_comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_comunasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comu_buscar_bar)
                        .addComponent(jLabel28))
                    .addComponent(comu_bt_buscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comu_desactivar)
                    .addComponent(jButton25))
                .addGap(76, 76, 76))
        );

        pesta_admin.addTab("Comunas", tab_comunas);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bancos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Nombre banco: ");

        ban_nombre_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ban_nombre_field.setText("Banco Estado");
        ban_nombre_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ban_nombre_fieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Código banco: ");

        ban_codigo_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ban_codigo_field.setText("12");
        ban_codigo_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ban_codigo_fieldActionPerformed(evt);
            }
        });

        ban_bt_guardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ban_bt_guardar.setText("Guardar");
        ban_bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ban_bt_guardarActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(ban_nombre_field, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 388, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(ban_codigo_field, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(796, 796, 796)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ban_bt_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ban_nombre_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(ban_codigo_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ban_bt_guardar)
                    .addComponent(jButton2))
                .addGap(28, 28, 28))
        );

        ban_buscar_bar.setToolTipText("buscar...");
        ban_buscar_bar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ban_buscar_bar.setMinimumSize(new java.awt.Dimension(6, 22));
        ban_buscar_bar.setPreferredSize(new java.awt.Dimension(6, 22));
        ban_buscar_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ban_buscar_barActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Bancos");

        ban_desactivar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ban_desactivar.setText("Desactivar");
        ban_desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ban_desactivarActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Editar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        ban_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ban_bt_buscar.setText("Buscar");
        ban_bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ban_bt_buscarActionPerformed(evt);
            }
        });

        jScrollPane17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        ban_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ban_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Banco", "Codigo Banco", "Estado", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ban_tabla.setColumnSelectionAllowed(true);
        ban_tabla.getTableHeader().setReorderingAllowed(false);
        ban_tabla.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                ban_tablaComponentAdded(evt);
            }
        });
        jScrollPane17.setViewportView(ban_tabla);
        ban_tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout tab_bancosLayout = new javax.swing.GroupLayout(tab_bancos);
        tab_bancos.setLayout(tab_bancosLayout);
        tab_bancosLayout.setHorizontalGroup(
            tab_bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_bancosLayout.createSequentialGroup()
                .addGroup(tab_bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_bancosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ban_desactivar))
                    .addGroup(tab_bancosLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tab_bancosLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(ban_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ban_bt_buscar)
                                .addGap(8, 8, 8))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(50, 50, 50))
        );
        tab_bancosLayout.setVerticalGroup(
            tab_bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_bancosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ban_bt_buscar)
                        .addComponent(ban_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(tab_bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ban_desactivar)
                    .addComponent(jButton4))
                .addGap(76, 76, 76))
        );

        pesta_admin.addTab("Bancos", tab_bancos);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Categoría venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("Categorías ventas:");

        cat_ven_nombre_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cat_ven_nombre_field.setText("Pago Recibido");
        cat_ven_nombre_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat_ven_nombre_fieldActionPerformed(evt);
            }
        });

        cat_ven_bt_guardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cat_ven_bt_guardar.setText("Guardar");
        cat_ven_bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat_ven_bt_guardarActionPerformed(evt);
            }
        });

        jButton43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton43.setText("Cancelar");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setText("Código categoría:");

        cat_ven_codigo_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cat_ven_codigo_field.setText("V");
        cat_ven_codigo_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat_ven_codigo_fieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(37, 37, 37)
                        .addComponent(cat_ven_nombre_field, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                        .addGap(93, 93, 93)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(cat_ven_codigo_field, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cat_ven_bt_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(cat_ven_nombre_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(cat_ven_codigo_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cat_ven_bt_guardar)
                    .addComponent(jButton43))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        cat_ven_buscar_bar.setToolTipText("buscar...");
        cat_ven_buscar_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat_ven_buscar_barActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Categorías de ventas registradas");

        jButton44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton44.setText("Desactivar");

        jButton45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton45.setText("Editar");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        cat_ven_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cat_ven_bt_buscar.setText("Buscar");
        cat_ven_bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat_ven_bt_buscarActionPerformed(evt);
            }
        });

        jScrollPane21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cat_ven_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cat_ven_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Categoria", "Codigo Categoria", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        cat_ven_tabla.getTableHeader().setReorderingAllowed(false);
        cat_ven_tabla.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                cat_ven_tablaComponentAdded(evt);
            }
        });
        jScrollPane21.setViewportView(cat_ven_tabla);
        cat_ven_tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout tab_categorias1Layout = new javax.swing.GroupLayout(tab_categorias1);
        tab_categorias1.setLayout(tab_categorias1Layout);
        tab_categorias1Layout.setHorizontalGroup(
            tab_categorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_categorias1Layout.createSequentialGroup()
                .addGroup(tab_categorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_categorias1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton44))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_categorias1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_categorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_categorias1Layout.createSequentialGroup()
                                .addGroup(tab_categorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane21, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab_categorias1Layout.createSequentialGroup()
                                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cat_ven_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cat_ven_bt_buscar)))
                                .addGap(8, 8, 8)))))
                .addGap(50, 50, 50))
        );
        tab_categorias1Layout.setVerticalGroup(
            tab_categorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_categorias1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_categorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_categorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cat_ven_buscar_bar)
                        .addComponent(jLabel43))
                    .addComponent(cat_ven_bt_buscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_categorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton44)
                    .addComponent(jButton45))
                .addGap(76, 76, 76))
        );

        pesta_admin.addTab("Categorías Ventas", tab_categorias1);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        username.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        username.setText("Nombre de usuario:");

        username_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        username_field.setText("usuario");
        username_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username_fieldActionPerformed(evt);
            }
        });

        saveuser_button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saveuser_button.setText("Guardar");
        saveuser_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveuser_buttonActionPerformed(evt);
            }
        });

        canceluser_button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        canceluser_button.setText("Cancelar");
        canceluser_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canceluser_buttonActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Ingrese clave:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("Confirme clave:");

        pass_field.setText("jPasswordField1");

        repet_pass_field.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(username)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(username_field, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                            .addComponent(pass_field)
                            .addComponent(repet_pass_field))
                        .addGap(543, 543, 543))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(canceluser_button, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveuser_button, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(username)
                    .addComponent(username_field, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(pass_field))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(repet_pass_field, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(42, 42, 42)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveuser_button)
                    .addComponent(canceluser_button))
                .addGap(28, 28, 28))
        );

        usu_buscar_bar.setToolTipText("buscar...");
        usu_buscar_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usu_buscar_barActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Usuarios");

        jButton29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton29.setText("Eliminar");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton30.setText("Editar");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        usu_bt_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usu_bt_buscar.setText("Buscar");
        usu_bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usu_bt_buscarActionPerformed(evt);
            }
        });

        jScrollPane22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        usuario_tabla.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usuario_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Usuario", "Acción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        usuario_tabla.setColumnSelectionAllowed(true);
        usuario_tabla.getTableHeader().setReorderingAllowed(false);
        usuario_tabla.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                usuario_tablaComponentAdded(evt);
            }
        });
        jScrollPane22.setViewportView(usuario_tabla);
        usuario_tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout tab_usuariosLayout = new javax.swing.GroupLayout(tab_usuarios);
        tab_usuarios.setLayout(tab_usuariosLayout);
        tab_usuariosLayout.setHorizontalGroup(
            tab_usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_usuariosLayout.createSequentialGroup()
                .addGroup(tab_usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_usuariosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton29))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_usuariosLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(tab_usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_usuariosLayout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usu_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usu_bt_buscar)
                                .addGap(8, 8, 8))
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane22))))
                .addGap(50, 50, 50))
        );
        tab_usuariosLayout.setVerticalGroup(
            tab_usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_usuariosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(usu_buscar_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usu_bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(tab_usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton29)
                    .addComponent(jButton30))
                .addGap(76, 76, 76))
        );

        pesta_admin.addTab("Usuarios", tab_usuarios);

        javax.swing.GroupLayout tab_administradorLayout = new javax.swing.GroupLayout(tab_administrador);
        tab_administrador.setLayout(tab_administradorLayout);
        tab_administradorLayout.setHorizontalGroup(
            tab_administradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tab_administradorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pesta_admin)
                .addContainerGap())
        );
        tab_administradorLayout.setVerticalGroup(
            tab_administradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_administradorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesta_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        Panel_tab_menu.addTab("Administrador", tab_administrador);

        getContentPane().add(Panel_tab_menu, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
//editar banco
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int cantidad_filas = ban_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (ban_tabla.isCellSelected(i, 3) == true){
                try {
                    Statement st;
                    String nombre = null;
                    //String codigo = null;
                    st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT BAN_DESCRIPCION, BAN_CODIGO FROM bancos;");
                    PreparedStatement pps2;
                    while (rs.next()){
                        
                        if (rs.getString(2).equals(ban_tabla.getValueAt(i,1))){
                            nombre = JOptionPane.showInputDialog(null,"Ingrese nuevo nombre de banco", "Editar Banco", JOptionPane.QUESTION_MESSAGE);
                            
                            //codigo = JOptionPane.showInputDialog("Ingrese nuevo codigo");
                        }               
                    }
                                                                                      
                    pps2 = con.prepareStatement("update bancos set  BAN_DESCRIPCION = ? where BAN_CODIGO = ?;");
                    if ("".equals(nombre)){
                        pps2.setString(1,(String) ban_tabla.getValueAt(i,0));
                    }
                    else pps2.setString(1,nombre);
                    /*
                    if (null != codigo){
                        pps2.setString(2,codigo);
                    }
                    else pps2.setString(2,(String) jTable4.getValueAt(i,1));
                    */                  
                    pps2.setString(2, (String) ban_tabla.getValueAt(i,1));
                    pps2.executeUpdate();
                    pps2.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_BANCO("");
        Mostrar_COMBOX_BANCOS();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ban_buscar_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ban_buscar_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ban_buscar_barActionPerformed

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void jTable2ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTable2ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2ComponentAdded

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void cli_buscar_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cli_buscar_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cli_buscar_barActionPerformed

    private void cli_bt_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cli_bt_editarActionPerformed
        int cantidad_filas = cli_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (cli_tabla.isCellSelected(i, 6) == true){
                try {
                    Statement st;
                    String nombre = null;
                    //String codigo = null;
                    st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT CLI_ID_CLIENTE, CLI_NOMBRE, CLI_CELULAR, CLI_CORREO, CLI_FECHA_NACIMIENTO, ESTADO FROM cliente;");
                    PreparedStatement pps2;
                    while (rs.next()){
                        if (rs.getString(2).equals(cli_tabla.getValueAt(i,1))){
                            nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre");
                            //codigo = JOptionPane.showInputDialog("Ingrese nuevo codigo");
                        }               
                    }
                                                                                      
                    pps2 = con.prepareStatement("update cliente set CLI_NOMBRE = ? where CLI_ID_CLIENTE = ?;");
                    if ("".equals(nombre)){
                        pps2.setString(1,(String) cli_tabla.getValueAt(i,0));
                    }
                    else pps2.setString(1,nombre);
                    /*
                    if (null != codigo){
                        pps2.setString(2,codigo);
                    }
                    else pps2.setString(2,(String) jTable4.getValueAt(i,1));
                    */                  
                    pps2.setString(2, (String) cli_tabla.getValueAt(i,1));
                    pps2.executeUpdate();
                    pps2.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_CLIENTE("");
    }//GEN-LAST:event_cli_bt_editarActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTable4ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTable4ComponentAdded
    }//GEN-LAST:event_jTable4ComponentAdded
//editar redes sociales
    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        int cantidad_filas = jTable4.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (jTable4.isCellSelected(i, 3) == true){
                try {
                    Statement st;
                    String nombre = null;
                    //String codigo = null;
                    st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT RRS_NOMBRE, RRS_CODIGO FROM rrss;");
                    PreparedStatement pps2;
                    while (rs.next()){
                        if (rs.getString(2).equals(jTable4.getValueAt(i,1))){
                            nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre");
                            //codigo = JOptionPane.showInputDialog("Ingrese nuevo codigo");
                        }               
                    }
                                                                                      
                    pps2 = con.prepareStatement("update rrss set RRS_NOMBRE = ? where RRS_CODIGO = ?;");
                    if ("".equals(nombre)){
                        pps2.setString(1,(String) jTable4.getValueAt(i,0));
                    }
                    else pps2.setString(1,nombre);
                    /*
                    if (null != codigo){
                        pps2.setString(2,codigo);
                    }
                    else pps2.setString(2,(String) jTable4.getValueAt(i,1));
                    */                  
                    pps2.setString(2, (String) jTable4.getValueAt(i,1));
                    pps2.executeUpdate();
                    pps2.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_RRSS("");
    }//GEN-LAST:event_jButton20ActionPerformed

    private void comu_buscar_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comu_buscar_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comu_buscar_barActionPerformed
//editar comunas
    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        int cantidad_filas = comu_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (comu_tabla.isCellSelected(i, 3) == true){
                try {
                    Statement st;
                    String nombre = null;
                    //String codigo = null;
                    st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT COM_DESCRIPCION, COM_CODIGO FROM comunas;");
                    PreparedStatement pps2;
                    while (rs.next()){
                        if (rs.getString(2).equals(comu_tabla.getValueAt(i,1))){
                            nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre");
                            //codigo = JOptionPane.showInputDialog("Ingrese nuevo codigo");
                        }               
                    }
                                                                                      
                    pps2 = con.prepareStatement("update comunas set  COM_DESCRIPCION = ? where COM_CODIGO = ?;");
                    if ("".equals(nombre)){
                        pps2.setString(1,(String) comu_tabla.getValueAt(i,0));     
                    }
                    else pps2.setString(1,nombre);
                    /*
                    if (null != codigo){
                        pps2.setString(2,codigo);
                    }
                    else pps2.setString(2,(String) jTable5.getValueAt(i,1));
                    */                  
                    pps2.setString(2, (String) comu_tabla.getValueAt(i,1));
                    pps2.executeUpdate();
                    pps2.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_COMUNA("");
        Mostrar_COMBOX_COMUNAS();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void username_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username_fieldActionPerformed

    private void usu_buscar_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usu_buscar_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usu_buscar_barActionPerformed
//editar usuario
    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        int cantidad_filas = usuario_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (usuario_tabla.isCellSelected(i, 1) == true){
                try {
                    Statement st;
                    String nombre = null;
                    String actual = null;
                    String id = null;
                    //String codigo = null;
                    st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT USU_ID_USUARIOS,USU_NOMBRE FROM usuarios;");
                    PreparedStatement pps2;
                    while (rs.next()){
                        
                        if (rs.getString(2).equals(usuario_tabla.getValueAt(i,0))){
                            id = rs.getString(1);
                            actual = rs.getString(2);
                            nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre");
                            //codigo = JOptionPane.showInputDialog("Ingrese nuevo codigo");
                        }       
                    }
                                                                                      
                    pps2 = con.prepareStatement("update usuarios set USU_NOMBRE = ? where  USU_ID_USUARIOS = ?;");
                    if ("".equals(nombre)){
                        pps2.setString(1,actual );
                    }
                    else pps2.setString(1,nombre);
                    /*
                    if (null != codigo){
                        pps2.setString(2,codigo);
                    }
                    else pps2.setString(2,(String) jTable5.getValueAt(i,1));
                    */                  
                    pps2.setString(2, id);
                    pps2.executeUpdate();
                    pps2.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_USUARIO("");    
    }//GEN-LAST:event_jButton30ActionPerformed

    private void prov_rut_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prov_rut_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prov_rut_fieldActionPerformed

    private void prov_buscar_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prov_buscar_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prov_buscar_barActionPerformed

    private void prov_tablaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_prov_tablaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_prov_tablaComponentAdded

    private void prov_bt_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prov_bt_editarActionPerformed
        int cantidad_filas = prov_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (prov_tabla.isCellSelected(i, 6) == true){
                try {
                    Statement st;
                    String nombre = null;
                    //String codigo = null;
                    st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT PRO_ID_PROVEEDOR, PRO_NOMBRE, PRO_TELEFONO, PRO_CORREO, PRO_DIRECCION, PRO_RAZON, ESTADO FROM cliente;");
                    PreparedStatement pps2;
                    while (rs.next()){
                        if (rs.getString(2).equals(prov_tabla.getValueAt(i,1))){
                            nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre");
                            //codigo = JOptionPane.showInputDialog("Ingrese nuevo codigo");
                        }               
                    }
                                                                                      
                    pps2 = con.prepareStatement("update cliente set CLI_NOMBRE = ? where CLI_ID_CLIENTE = ?;");
                    if ("".equals(nombre)){
                        pps2.setString(1,(String) prov_tabla.getValueAt(i,0));
                    }
                    else pps2.setString(1,nombre);
                    /*
                    if (null != codigo){
                        pps2.setString(2,codigo);
                    }
                    else pps2.setString(2,(String) jTable4.getValueAt(i,1));
                    */                  
                    pps2.setString(2, (String) prov_tabla.getValueAt(i,1));
                    pps2.executeUpdate();
                    pps2.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_PROVEEDOR("");
    }//GEN-LAST:event_prov_bt_editarActionPerformed

    private void cat_art_nombre_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat_art_nombre_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat_art_nombre_fieldActionPerformed

    private void cat_art_codigo_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat_art_codigo_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat_art_codigo_fieldActionPerformed

    private void cat_art_buscar_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat_art_buscar_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat_art_buscar_barActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void cat_ven_nombre_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat_ven_nombre_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat_ven_nombre_fieldActionPerformed

    private void cat_ven_codigo_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat_ven_codigo_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat_ven_codigo_fieldActionPerformed

    private void cat_ven_buscar_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat_ven_buscar_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat_ven_buscar_barActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton45ActionPerformed

    private void namepack_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namepack_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namepack_fieldActionPerformed

    private void jFormattedTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField10ActionPerformed

    private void packs_tableComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_packs_tableComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_packs_tableComponentAdded

    private void editpack_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpack_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editpack_buttonActionPerformed

    private void topack_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topack_buttonActionPerformed
        int i=packeditor_list.getSelectedIndex();
        packedited_list.removeAll();
        if(addunits_spinner.getValue().equals(0)){
            lista2.add(lista1.get(i)+ "(1)");
        }else{
            lista2.add(lista1.get(i)+ "("+ addunits_spinner.getValue().toString()+")");
        }
        
        lista1.remove(i);
        packedited_list.setListData(lista2);
    }//GEN-LAST:event_topack_buttonActionPerformed

    private void frompack_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frompack_buttonActionPerformed
        int i=packedited_list.getSelectedIndex();
        packeditor_list.removeAll();
        int x = lista2.get(i).indexOf("(");
        int y = lista2.get(i).indexOf(")");
        String temp = lista2.get(i).substring(0, x);
        lista1.add(temp);
        lista2.remove(i);
        packeditor_list.setListData(lista1);
    }//GEN-LAST:event_frompack_buttonActionPerformed
//guardar redes sociales
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        String campo1 = jTextField12.getText();
        String campo2 = jTextField16.getText();
        
        try {
            if ("".equals(campo1)  || "".equals(campo2)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
            }
            
            else {
                PreparedStatement pps = con.prepareStatement("INSERT INTO rrss (RRS_NOMBRE, RRS_CODIGO, estado) VALUES (?,?,?)");
                pps.setString(1, campo1);
                pps.setString(2, campo2);
                pps.setString(3, "1");
                pps.executeUpdate();
                pps.close();
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
                Mostrar_RRSS("");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Codigo no permitido");
        }
    }//GEN-LAST:event_jButton17ActionPerformed
//guardar banco
    private void ban_bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ban_bt_guardarActionPerformed
        String campo1 = ban_nombre_field.getText();
        String campo2 = ban_codigo_field.getText();
        
        try {
            if ("".equals(campo1)  || "".equals(campo2)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
            }
            
            else {
                PreparedStatement pps = con.prepareStatement("INSERT INTO bancos (BAN_DESCRIPCION, BAN_CODIGO, estado) VALUES (?,?,?)");
                pps.setString(1, campo1);
                pps.setString(2, campo2);
                pps.setString(3, "1");
                pps.executeUpdate();
                pps.close();
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
                Mostrar_BANCO("");
                Mostrar_COMBOX_BANCOS();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Codigo no permitido");
        }
    }//GEN-LAST:event_ban_bt_guardarActionPerformed

    private void ban_tablaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_ban_tablaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_ban_tablaComponentAdded

    private void ban_nombre_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ban_nombre_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ban_nombre_fieldActionPerformed

    private void ban_codigo_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ban_codigo_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ban_codigo_fieldActionPerformed
//guardar comuna
    private void comu_bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comu_bt_guardarActionPerformed
      String campo1 = comu_nombre_field.getText();
        String campo2 = comu_codigo_field.getText();
        
        try {
            if ("".equals(campo1)  || "".equals(campo2)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
            }
            
            else {
                PreparedStatement pps = con.prepareStatement("INSERT INTO comunas (COM_DESCRIPCION, COM_CODIGO, estado) VALUES (?,?,?)");
                pps.setString(1, campo1);
                pps.setString(2, campo2);
                pps.setString(3, "1");
                pps.executeUpdate();
                pps.close();
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
                Mostrar_COMUNA("");
                Mostrar_COMBOX_COMUNAS();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Codigo no permitido");
        }
    }//GEN-LAST:event_comu_bt_guardarActionPerformed

    private void comu_codigo_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comu_codigo_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comu_codigo_fieldActionPerformed

    private void comu_tablaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_comu_tablaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_comu_tablaComponentAdded

    private void cat_art_bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat_art_bt_guardarActionPerformed
        try {
            PreparedStatement pps = con.prepareStatement("INSERT INTO categoria_articulo (CAT_DESCRIPCION, CAT_CODIGO) VALUES (?,?)");
            pps.setString(1, cat_art_nombre_field.getText());
            pps.setString(2, cat_art_codigo_field.getText());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
            Mostrar_CAT_ARTICULO("");
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cat_art_bt_guardarActionPerformed

    private void cat_art_tablaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_cat_art_tablaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_cat_art_tablaComponentAdded
//cancelar comuna
    private void comu_canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comu_canActionPerformed
        comu_nombre_field.setText(null);
        comu_codigo_field.setText(null);
    }//GEN-LAST:event_comu_canActionPerformed

    private void cat_ven_tablaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_cat_ven_tablaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_cat_ven_tablaComponentAdded

    private void cat_ven_bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat_ven_bt_guardarActionPerformed
        try {
            PreparedStatement pps = con.prepareStatement("INSERT INTO estados_venta (EST_DESCRIPCION, EST_CODIGO) VALUES (?,?)");
            pps.setString(1, cat_ven_nombre_field.getText());
            pps.setString(2, cat_ven_codigo_field.getText());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
            Mostrar_CAT_VENTA("");
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cat_ven_bt_guardarActionPerformed
//guardar usuario
    private void saveuser_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveuser_buttonActionPerformed
       String nombre = username_field.getText();
        char[] c1 = pass_field.getPassword();
        char[] c2 = repet_pass_field.getPassword();
        String contraseña = String.valueOf(c1);
        String contraseña2 = String.valueOf(c2);
               
        try {
            if (contraseña.equals(contraseña2)){
                                
                PreparedStatement pps = con.prepareStatement("INSERT INTO usuarios (USU_NOMBRE, USU_CONTRASEÑA) VALUES (?,?)");
                pps.setString(1, nombre);
                pps.setString(2, contraseña);
                pps.executeUpdate();
                pps.close();
                JOptionPane.showMessageDialog(null, "Usuario guardado exitosamente");
           
                Mostrar_USUARIO("");
            }
            
            else {
                JOptionPane.showMessageDialog(null, "Repetir contraseña");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo agregar el usuario");
        }  
    }//GEN-LAST:event_saveuser_buttonActionPerformed

    private void usuario_tablaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_usuario_tablaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_usuario_tablaComponentAdded

    private void cli_tablaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_cli_tablaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_cli_tablaComponentAdded

    private void cli_bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cli_bt_guardarActionPerformed

        String campo1 = cli_nombre_field.getText();
        String campo2 = cli_telefono_field.getText();
        String campo3 = cli_email_field.getText();
        String campo4 = cli_rut_field.getText();  
        try {
            if ("".equals(campo1) || "".equals(campo2) || "".equals(campo3) || "".equals(campo4)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
            }
            
            else {
                java.util.Date utilDate = (java.util.Date) cli_fec_nacimiento_field.getDate();
                java.sql.Date cli_fec_nacimiento_field1 = new java.sql.Date(utilDate.getTime());
                PreparedStatement pps = con.prepareStatement("INSERT INTO cliente (CLI_ID_CLIENTE, CLI_NOMBRE, CLI_CELULAR, CLI_CORREO, CLI_FECHA_NACIMIENTO, ESTADO) VALUES (?,?,?,?,?,?)");
                pps.setString(2, campo1);
                pps.setString(3, campo2);
                pps.setString(4, campo3);
                pps.setString(1, campo4);
                pps.setDate(5, cli_fec_nacimiento_field1);
                pps.setString(6, "1");
                pps.executeUpdate();
                pps.close();
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
                Mostrar_CLIENTE("");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Codigo no permitido");
        }
        
    }//GEN-LAST:event_cli_bt_guardarActionPerformed

    private void cli_nombre_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cli_nombre_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cli_nombre_fieldActionPerformed
//desactivar redes sociales
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        int cantidad_filas = jTable4.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            
       
            if (jTable4.isCellSelected(i, 3) == true){
                try {
                    PreparedStatement pps;
                    pps = con.prepareStatement("update rrss set ESTADO = ? where RRS_CODIGO = ?");
                    System.out.print("pase");
                    if ("activada".equals(jTable4.getValueAt(i, 2))){
                        pps.setString(1,"0");
                    }
                    else pps.setString(1,"1"); 
                    
                    pps.setString(2, (String) jTable4.getValueAt(i,1));
                    pps.executeUpdate();
                    pps.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_RRSS("");
    }//GEN-LAST:event_jButton19ActionPerformed
//cancelar redes sociales
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        jTextField12.setText(null);
        jTextField16.setText(null);
    }//GEN-LAST:event_jButton18ActionPerformed
//desactivar comuna
    private void comu_desactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comu_desactivarActionPerformed
        int cantidad_filas = comu_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (comu_tabla.isCellSelected(i, 3) == true){
                try {
                    PreparedStatement pps;
                    pps = con.prepareStatement("update comunas set estado = ? where COM_CODIGO = ?;");
                    if ("activada".equals(comu_tabla.getValueAt(i, 2))){
                        pps.setString(1,"0");
                    }
                    else pps.setString(1,"1"); 
                    
                    pps.setString(2, (String) comu_tabla.getValueAt(i,1));
                    pps.executeUpdate();
                    pps.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_COMUNA(""); 
    }//GEN-LAST:event_comu_desactivarActionPerformed
//cancelar banco
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ban_nombre_field.setText(null);
        ban_codigo_field.setText(null);
    }//GEN-LAST:event_jButton2ActionPerformed
//desactivar banco
    private void ban_desactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ban_desactivarActionPerformed
        int cantidad_filas = ban_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (ban_tabla.isCellSelected(i, 3) == true){
                try {
                    PreparedStatement pps;
                    pps = con.prepareStatement("update bancos set estado = ? where BAN_CODIGO = ?;");
                    if ("activado".equals(ban_tabla.getValueAt(i, 2))){
                        pps.setString(1,"0");
                    }
                    else pps.setString(1,"1"); 
                    
                    pps.setString(2, (String) ban_tabla.getValueAt(i,1));
                    pps.executeUpdate();
                    pps.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_BANCO("");
    }//GEN-LAST:event_ban_desactivarActionPerformed
//cancelar usuario
    private void canceluser_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canceluser_buttonActionPerformed
        username_field.setText(null);
        pass_field.setText(null);
        repet_pass_field.setText(null);
    }//GEN-LAST:event_canceluser_buttonActionPerformed
//eliminar usuario
    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        int cantidad_filas = usuario_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (usuario_tabla.isCellSelected(i, 1) == true){
                try {
                    PreparedStatement pps;
                    pps = con.prepareStatement("DELETE FROM usuarios WHERE USU_NOMBRE = ?;");
                    pps.setString(1,(String) usuario_tabla.getValueAt(i, 0)); 
                    pps.executeUpdate();
                    pps.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_USUARIO("");
    }//GEN-LAST:event_jButton29ActionPerformed

    private void cli_bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cli_bt_cancelarActionPerformed
        cli_nombre_field.setText(null);
        cli_telefono_field.setText(null);
        cli_email_field.setText(null);
        cli_rut_field.setText(null);
        cli_fec_nacimiento_field.setDate(null);
    }//GEN-LAST:event_cli_bt_cancelarActionPerformed

    private void cli_bt_desactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cli_bt_desactivarActionPerformed
        int cantidad_filas = cli_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (cli_tabla.isCellSelected(i, 6) == true){
                try {
                    PreparedStatement pps;
                    pps = con.prepareStatement("update cliente set ESTADO = ? where CLI_ID_CLIENTE = ?");
                    System.out.print("pase");
                    if ("activada".equals(cli_tabla.getValueAt(i, 5))){
                        pps.setString(1,"0");
                    }
                    else pps.setString(1,"1"); 
                    pps.setString(2, (String) cli_tabla.getValueAt(i,0));
                    pps.executeUpdate();
                    pps.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_CLIENTE("");
    }//GEN-LAST:event_cli_bt_desactivarActionPerformed

    private void prov_bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prov_bt_cancelarActionPerformed
        prov_rut_field.setText(null);
        prov_nombre_field.setText(null);
        prov_direccion_field.setText(null);
        prov_razon_field.setText(null);
        prov_telefono_field.setText(null);
        prov_email_field.setText(null);
    }//GEN-LAST:event_prov_bt_cancelarActionPerformed

    private void prov_bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prov_bt_guardarActionPerformed
        String campo1 = prov_rut_field.getText();
        String campo2 = prov_nombre_field.getText();
        String campo3 = prov_direccion_field.getText();
        String campo4 = prov_razon_field.getText();  
        String campo5 = prov_telefono_field.getText();  
        String campo6 = prov_email_field.getText();  
        try {
            if ("".equals(campo1) || "".equals(campo2) || "".equals(campo3) || "".equals(campo4) || "".equals(campo5) || "".equals(campo6)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
            }
            
            else {
                PreparedStatement pps = con.prepareStatement("INSERT INTO proveedor (PRO_ID_PROVEEDOR, PRO_NOMBRE, PRO_TELEFONO, PRO_CORREO, PRO_DIRECCION, PRO_RAZON, ESTADO) VALUES (?,?,?,?,?,?,?)");
                pps.setString(1, campo1);
                pps.setString(2, campo2);
                pps.setString(5, campo3);
                pps.setString(6, campo4);
                pps.setString(3, campo5);
                pps.setString(4, campo6);
                pps.setString(7, "1");
                pps.executeUpdate();
                pps.close();
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
                Mostrar_PROVEEDOR("");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Codigo no permitido");
        }
    }//GEN-LAST:event_prov_bt_guardarActionPerformed

    private void prov_bt_desactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prov_bt_desactivarActionPerformed
        int cantidad_filas = prov_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (prov_tabla.isCellSelected(i, 7) == true){
                try {
                    PreparedStatement pps;
                    pps = con.prepareStatement("update proveedor set ESTADO = ? where PRO_ID_PROVEEDOR = ?");
                    System.out.print("pase");
                    if ("activada".equals(prov_tabla.getValueAt(i, 6))){
                        pps.setString(1,"0");
                    }
                    else pps.setString(1,"1"); 
                    pps.setString(2, (String) prov_tabla.getValueAt(i,0));
                    pps.executeUpdate();
                    pps.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_PROVEEDOR("");
    }//GEN-LAST:event_prov_bt_desactivarActionPerformed

    private void prov_direccion_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prov_direccion_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prov_direccion_fieldActionPerformed

    private void prov_razon_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prov_razon_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prov_razon_fieldActionPerformed

    private void rrss_bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rrss_bt_buscarActionPerformed
        Mostrar_RRSS(rrss_buscar_bar.getText());
    }//GEN-LAST:event_rrss_bt_buscarActionPerformed

    private void cli_bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cli_bt_buscarActionPerformed
        Mostrar_CLIENTE(cli_buscar_bar.getText());
    }//GEN-LAST:event_cli_bt_buscarActionPerformed

    private void usu_bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usu_bt_buscarActionPerformed
        Mostrar_USUARIO(usu_buscar_bar.getText());
    }//GEN-LAST:event_usu_bt_buscarActionPerformed

    private void cat_ven_bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat_ven_bt_buscarActionPerformed
        Mostrar_CAT_VENTA(cat_ven_buscar_bar.getText());
    }//GEN-LAST:event_cat_ven_bt_buscarActionPerformed

    private void prov_bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prov_bt_buscarActionPerformed
        Mostrar_PROVEEDOR(prov_buscar_bar.getText());
    }//GEN-LAST:event_prov_bt_buscarActionPerformed

    private void cat_art_bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat_art_bt_buscarActionPerformed
        Mostrar_CAT_ARTICULO(cat_art_buscar_bar.getText());
    }//GEN-LAST:event_cat_art_bt_buscarActionPerformed

    private void comu_bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comu_bt_buscarActionPerformed
        Mostrar_COMUNA(comu_buscar_bar.getText());
    }//GEN-LAST:event_comu_bt_buscarActionPerformed

    private void ban_bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ban_bt_buscarActionPerformed
        Mostrar_BANCO(ban_buscar_bar.getText());
    }//GEN-LAST:event_ban_bt_buscarActionPerformed

    private void venta_num_pedido_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_num_pedido_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_venta_num_pedido_fieldActionPerformed

    private void venta_nom_cli_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_nom_cli_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_venta_nom_cli_fieldActionPerformed

    private void venta_email_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_email_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_venta_email_fieldActionPerformed

    private void venta_telefono_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_telefono_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_venta_telefono_fieldActionPerformed

    private void venta_buscar_rutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_buscar_rutActionPerformed
        //Mostrar_RUT(venta_rut_field.getText());
        BUSCAR_RUT();
    }//GEN-LAST:event_venta_buscar_rutActionPerformed

    private void venta_destinatario_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_destinatario_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_venta_destinatario_fieldActionPerformed

    private void venta_bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_bt_guardarActionPerformed
        //CREAR_VENTA();
        Statement stc;
        String campo1 = venta_num_pedido_field.getText();
        String campo2 = venta_rut_field.getText();
        String campo3 = venta_destinatario_field.getText();
        String campo4 = (String) venta_comuna_box.getSelectedItem();

        try {
            if ("".equals(campo1)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
            }
            
            else {
                stc = con.createStatement();
                ResultSet id_comuna = stc.executeQuery("SELECT COM_ID_COMUNA FROM comunas WHERE COM_DESCRIPCION = '"+campo4+"'");
                id_comuna.next();
                //java.util.Date utilDate = (java.util.Date) cli_fec_nacimiento_field.getDate();
                //java.sql.Date cli_fec_nacimiento_field1 = new java.sql.Date(utilDate.getTime());
                PreparedStatement pps = con.prepareStatement("INSERT INTO venta (VTA_ID_VENTA, CLI_ID_CLIENTE, VTA_NOMBRE_DESTINATARIO, COM_ID_COMUNA, ESTADO) VALUES (?,?,?,?,?)");
                pps.setString(1, campo1);
                pps.setString(2, campo2);
                pps.setString(3, campo3);
                pps.setString(4, id_comuna.getString(1));
                pps.setString(5, "1");

                //pps.setDate(5, cli_fec_nacimiento_field1);

                pps.executeUpdate();
                pps.close();
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Codigo no permitido");
        }
    }//GEN-LAST:event_venta_bt_guardarActionPerformed

    private void venta_bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_bt_cancelarActionPerformed
        venta_num_pedido_field.setText(null);
        venta_rut_field.setText(null);
        venta_nom_cli_field.setText(null);
        venta_email_field.setText(null);
        venta_telefono_field.setText(null);

        venta_destinatario_field.setText(null);
        venta_fecha_entrega.setText(null);
        venta_direccion_field.setText(null);
        venta_comuna_box.setSelectedItem(null);
        venta_saludo_field.setText(null);
        venta_rrss_box.setSelectedItem(null);
        
        venta_estado_box.setSelectedItem(null);
        venta_pack_box.setSelectedItem(null);
        venta_fecha_entrega_ini.setText(null);
        venta_entrega_fin.setText(null);
    }//GEN-LAST:event_venta_bt_cancelarActionPerformed

    private void venta_direccion_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_direccion_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_venta_direccion_fieldActionPerformed

    private void ven_con_n_pedido_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_n_pedido_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ven_con_n_pedido_fieldActionPerformed

    private void ven_con_rut_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_rut_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ven_con_rut_fieldActionPerformed

    private void ven_con_n_bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_n_bt_buscarActionPerformed
        Mostrar_NUMEROPEDIDO();
    }//GEN-LAST:event_ven_con_n_bt_buscarActionPerformed

    private void ven_con_nom_cliente_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_nom_cliente_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ven_con_nom_cliente_fieldActionPerformed

    private void ven_con_cod_tran_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_cod_tran_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ven_con_cod_tran_fieldActionPerformed

    private void ven_con_bt_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_bt_confirmarActionPerformed
        //MODIFICAR_CONFIRMACION();
        Statement stc;
        String campo1 = ven_con_cod_tran_field.getText();
        String campo2 = ven_con_fecha_pago_field.getText();
        String campo3 = (String) ven_con_banco_combox.getSelectedItem();

        try {
            if ("".equals(campo1)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
            }
            
            else {
                stc = con.createStatement();
                ResultSet id_banco = stc.executeQuery("SELECT BAN_ID_BANCO FROM bancos WHERE BAN_DESCRIPCION = '"+campo3+"'");
                id_banco.next();
                //java.util.Date utilDate = (java.util.Date) ven_con_fecha_pago_field.getDate();
                //java.sql.Date ven_con_fecha_pago_field1 = new java.sql.Date(utilDate.getTime());
                PreparedStatement pps = con.prepareStatement("UPDATE venta SET VTA_CODIGO_TRANSFERENCIA=?, VTA_FECHA_TRANSFERENCIA=?, BAN_ID_BANCO=? WHERE VTA_ID_VENTA=?");
                pps.setString(1, campo1);
                pps.setString(2, campo2);
                pps.setString(3, id_banco.getString(1));
                //pps.setDate(2, ven_con_fecha_pago_field1);
                pps.setString(4, ven_con_n_pedido_field.getText());

                pps.executeUpdate();
                pps.close();
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Codigo no permitido");
        }
        Mostrar_VENTA_TABLA("");
    }//GEN-LAST:event_ven_con_bt_confirmarActionPerformed

    private void ven_con_bar_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_bar_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ven_con_bar_buscarActionPerformed

    private void ven_con_tablaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_ven_con_tablaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_ven_con_tablaComponentAdded

    private void jFormattedTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField15ActionPerformed

    private void tabla_destinosComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabla_destinosComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_destinosComponentAdded

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jFormattedTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField17ActionPerformed

    private void tabla_despachosComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabla_despachosComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_despachosComponentAdded

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void topack_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topack_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_topack_button1ActionPerformed

    private void frompack_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frompack_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frompack_button1ActionPerformed

    private void packs_table1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_packs_table1ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_packs_table1ComponentAdded

    private void deactivatepack_button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivatepack_button3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deactivatepack_button3ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton62ActionPerformed

    private void jTextField36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField36ActionPerformed

    private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton72ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jButton73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton73ActionPerformed

    private void jButton74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton74ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton74ActionPerformed

    private void jTextField41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField41ActionPerformed

    private void ven_con_banco_comboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_banco_comboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ven_con_banco_comboxActionPerformed

    private void ven_con_bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_bt_cancelarActionPerformed
        ven_con_n_pedido_field.setText(null);
        ven_con_rut_field.setText(null);
        ven_con_banco_combox.setSelectedItem(null);
        ven_con_nom_cliente_field.setText(null);
        ven_con_fecha_pago_field.setText(null);
        ven_con_cod_tran_field.setText(null);
    }//GEN-LAST:event_ven_con_bt_cancelarActionPerformed

    private void ven_con_bt_desactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_bt_desactivarActionPerformed
        int cantidad_filas = ven_con_tabla.getRowCount();
        
        for (int i = 0; i <= cantidad_filas; i++){
            if (ven_con_tabla.isCellSelected(i, 5) == true){
                try {
                    PreparedStatement pps;
                    pps = con.prepareStatement("update venta set ESTADO = ? where VTA_ID_VENTA = ?");
                    System.out.print("pase");
                    if ("activada".equals(ven_con_tabla.getValueAt(i, 4))){
                        pps.setString(1,"0");
                    }
                    else pps.setString(1,"1"); 
                    pps.setString(2, (String) ven_con_tabla.getValueAt(i,0));
                    pps.executeUpdate();
                    pps.close();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
        Mostrar_VENTA_TABLA("");
    }//GEN-LAST:event_ven_con_bt_desactivarActionPerformed

    private void ven_con_bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ven_con_bt_buscarActionPerformed
        Mostrar_VENTA_TABLA(ven_con_bar_buscar.getText());
    }//GEN-LAST:event_ven_con_bt_buscarActionPerformed

    private void venta_bt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_bt_modificarActionPerformed
        String campo1 = venta_destinatario_field.getText();

        try {
            if ("".equals(campo1)){
                JOptionPane.showMessageDialog(null, "rellene todos los campos");
            }
            
            else {
                //java.util.Date utilDate = (java.util.Date) cli_fec_nacimiento_field.getDate();
                //java.sql.Date cli_fec_nacimiento_field1 = new java.sql.Date(utilDate.getTime());
                PreparedStatement pps = con.prepareStatement("UPDATE venta SET VTA_NOMBRE_DESTINATARIO=? WHERE VTA_ID_VENTA=?");
                pps.setString(1, campo1);
                pps.setString(2, venta_num_pedido_field.getText());

                //pps.setDate(5, cli_fec_nacimiento_field1);

                pps.executeUpdate();
                pps.close();
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(crud_banco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Codigo no permitido");
        }
    }//GEN-LAST:event_venta_bt_modificarActionPerformed

    private void venta_rut_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_rut_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_venta_rut_fieldActionPerformed

    private void venta_buscar_n_pedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_buscar_n_pedidoActionPerformed
        Mostrar_PEDIDO();// TODO add your handling code here:
    }//GEN-LAST:event_venta_buscar_n_pedidoActionPerformed

    private void bt_recargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_recargarActionPerformed
        Mostrar_DESTINOS_TABLA("");
    }//GEN-LAST:event_bt_recargarActionPerformed

    private void bt_recargar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_recargar1ActionPerformed
        Mostrar_DESPACHOS_TABLA("");
    }//GEN-LAST:event_bt_recargar1ActionPerformed

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
            java.util.logging.Logger.getLogger(crud_banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crud_banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crud_banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crud_banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        
        conector();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new crud_banco().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Packs;
    private javax.swing.JLabel Packs1;
    private javax.swing.JTabbedPane Panel_tab_menu;
    private javax.swing.JSpinner addunits_spinner;
    private javax.swing.JSpinner addunits_spinner1;
    private javax.swing.JButton ban_bt_buscar;
    private javax.swing.JButton ban_bt_guardar;
    private javax.swing.JFormattedTextField ban_buscar_bar;
    private javax.swing.JTextField ban_codigo_field;
    private javax.swing.JButton ban_desactivar;
    private javax.swing.JTextField ban_nombre_field;
    private javax.swing.JTable ban_tabla;
    private javax.swing.JButton bt_recargar;
    private javax.swing.JButton bt_recargar1;
    private javax.swing.JButton cancelpack_button;
    private javax.swing.JButton canceluser_button;
    private javax.swing.JButton cat_art_bt_buscar;
    private javax.swing.JButton cat_art_bt_guardar;
    private javax.swing.JFormattedTextField cat_art_buscar_bar;
    private javax.swing.JTextField cat_art_codigo_field;
    private javax.swing.JTextField cat_art_nombre_field;
    private javax.swing.JTable cat_art_tabla;
    private javax.swing.JButton cat_ven_bt_buscar;
    private javax.swing.JButton cat_ven_bt_guardar;
    private javax.swing.JFormattedTextField cat_ven_buscar_bar;
    private javax.swing.JTextField cat_ven_codigo_field;
    private javax.swing.JTextField cat_ven_nombre_field;
    private javax.swing.JTable cat_ven_tabla;
    private javax.swing.JButton cli_bt_buscar;
    private javax.swing.JButton cli_bt_cancelar;
    private javax.swing.JButton cli_bt_desactivar;
    private javax.swing.JButton cli_bt_editar;
    private javax.swing.JButton cli_bt_guardar;
    private javax.swing.JFormattedTextField cli_buscar_bar;
    private javax.swing.JTextField cli_email_field;
    private com.toedter.calendar.JDateChooser cli_fec_nacimiento_field;
    private javax.swing.JTextField cli_nombre_field;
    private javax.swing.JTextField cli_rut_field;
    private javax.swing.JTable cli_tabla;
    private javax.swing.JTextField cli_telefono_field;
    private javax.swing.JLabel clientname;
    private javax.swing.JButton comu_bt_buscar;
    private javax.swing.JButton comu_bt_guardar;
    private javax.swing.JFormattedTextField comu_buscar_bar;
    private javax.swing.JButton comu_can;
    private javax.swing.JTextField comu_codigo_field;
    private javax.swing.JButton comu_desactivar;
    private javax.swing.JTextField comu_nombre_field;
    private javax.swing.JTable comu_tabla;
    private javax.swing.JButton deactivatepack_button;
    private javax.swing.JButton deactivatepack_button1;
    private javax.swing.JButton deactivatepack_button2;
    private javax.swing.JButton deactivatepack_button3;
    private javax.swing.JButton editpack_button;
    private javax.swing.JButton frompack_button;
    private javax.swing.JButton frompack_button1;
    private javax.swing.JButton inf_cli_bt_buscar;
    private javax.swing.JButton inf_cli_bt_buscar_bar;
    private javax.swing.JButton inf_cli_bt_desc;
    private com.toedter.calendar.JDateChooser inf_cli_fecha_d;
    private com.toedter.calendar.JDateChooser inf_cli_fecha_h;
    private javax.swing.JFormattedTextField inf_cli_field_buscar;
    private javax.swing.JTextField inf_cli_rut;
    private javax.swing.JTable inf_cli_tabla;
    private javax.swing.JButton inf_dev_bt_buscar;
    private javax.swing.JButton inf_dev_bt_buscar_bar;
    private javax.swing.JButton inf_dev_bt_desc;
    private com.toedter.calendar.JDateChooser inf_dev_fecha_d;
    private com.toedter.calendar.JDateChooser inf_dev_fecha_h;
    private javax.swing.JFormattedTextField inf_dev_field_buscar;
    private javax.swing.JTextField inf_dev_rut;
    private javax.swing.JTable inf_dev_tabla;
    private javax.swing.JButton inf_inv_bt_buscar;
    private javax.swing.JButton inf_inv_bt_buscar_bar;
    private javax.swing.JButton inf_inv_bt_desc;
    private javax.swing.JComboBox<String> inf_inv_cat_art;
    private com.toedter.calendar.JDateChooser inf_inv_fecha_d;
    private com.toedter.calendar.JDateChooser inf_inv_fecha_h;
    private javax.swing.JTextField inf_inv_fecha_venc;
    private javax.swing.JFormattedTextField inf_inv_field_buscar_bar;
    private javax.swing.JComboBox<String> inf_inv_prov;
    private javax.swing.JTable inf_inv_tabla;
    private javax.swing.JButton inf_ven_bt_buscar;
    private javax.swing.JButton inf_ven_bt_buscar_bar;
    private javax.swing.JButton inf_ven_bt_desc;
    private com.toedter.calendar.JDateChooser inf_ven_fecha_d;
    private com.toedter.calendar.JDateChooser inf_ven_fecha_h;
    private javax.swing.JTextField inf_ven_field_buscar_bar;
    private javax.swing.JTextField inf_ven_rut;
    private javax.swing.JTable inf_ven_tabla;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private javax.swing.JFormattedTextField jFormattedTextField10;
    private javax.swing.JFormattedTextField jFormattedTextField15;
    private javax.swing.JFormattedTextField jFormattedTextField17;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel namepack;
    private javax.swing.JTextField namepack_field;
    private javax.swing.JList<String> packedited_list;
    private javax.swing.JList<String> packedited_list1;
    private javax.swing.JList<String> packeditor_list;
    private javax.swing.JList<String> packeditor_list1;
    private javax.swing.JPanel packs_panel;
    private javax.swing.JTable packs_table;
    private javax.swing.JTable packs_table1;
    private javax.swing.JPasswordField pass_field;
    private javax.swing.JTabbedPane pesta_admin;
    private javax.swing.JTabbedPane pesta_admin1;
    private javax.swing.JTabbedPane pesta_compras;
    private javax.swing.JTabbedPane pesta_ventas;
    private javax.swing.JLabel pricepack;
    private javax.swing.JTextField pricepack_field;
    private javax.swing.JButton prov_bt_buscar;
    private javax.swing.JButton prov_bt_cancelar;
    private javax.swing.JButton prov_bt_desactivar;
    private javax.swing.JButton prov_bt_editar;
    private javax.swing.JButton prov_bt_guardar;
    private javax.swing.JFormattedTextField prov_buscar_bar;
    private javax.swing.JTextField prov_direccion_field;
    private javax.swing.JTextField prov_email_field;
    private javax.swing.JTextField prov_nombre_field;
    private javax.swing.JTextField prov_razon_field;
    private javax.swing.JTextField prov_rut_field;
    private javax.swing.JTable prov_tabla;
    private javax.swing.JTextField prov_telefono_field;
    private javax.swing.JPasswordField repet_pass_field;
    private javax.swing.JLabel result_envio;
    private javax.swing.JLabel result_subtotal;
    private javax.swing.JLabel result_total;
    private javax.swing.JButton rrss_bt_buscar;
    private javax.swing.JFormattedTextField rrss_buscar_bar;
    private javax.swing.JButton savepack_button;
    private javax.swing.JButton saveuser_button;
    private javax.swing.JPanel tab_actudespacho;
    private javax.swing.JPanel tab_administrador;
    private javax.swing.JPanel tab_articulos;
    private javax.swing.JPanel tab_bancos;
    private javax.swing.JPanel tab_categorias;
    private javax.swing.JPanel tab_categorias1;
    private javax.swing.JPanel tab_clientes;
    private javax.swing.JPanel tab_compras;
    private javax.swing.JPanel tab_comunas;
    private javax.swing.JPanel tab_confirmpago;
    private javax.swing.JPanel tab_datosventa;
    private javax.swing.JPanel tab_informe_cambios;
    private javax.swing.JPanel tab_informe_clientes;
    private javax.swing.JPanel tab_informe_inventario;
    private javax.swing.JPanel tab_informe_ventas;
    private javax.swing.JPanel tab_informes;
    private javax.swing.JPanel tab_listadestinos;
    private javax.swing.JPanel tab_packs;
    private javax.swing.JPanel tab_provee;
    private javax.swing.JPanel tab_regcompra;
    private javax.swing.JPanel tab_rrss;
    private javax.swing.JPanel tab_solpedido;
    private javax.swing.JPanel tab_usuarios;
    private javax.swing.JPanel tab_ventas;
    private javax.swing.JTable tabla_despachos;
    private javax.swing.JTable tabla_destinos;
    private javax.swing.JButton topack_button;
    private javax.swing.JButton topack_button1;
    private javax.swing.JLabel username;
    private javax.swing.JTextField username_field;
    private javax.swing.JButton usu_bt_buscar;
    private javax.swing.JFormattedTextField usu_buscar_bar;
    private javax.swing.JTable usuario_tabla;
    private javax.swing.JComboBox<String> ven_con_banco_combox;
    private javax.swing.JFormattedTextField ven_con_bar_buscar;
    private javax.swing.JButton ven_con_bt_buscar;
    private javax.swing.JButton ven_con_bt_cancelar;
    private javax.swing.JButton ven_con_bt_confirmar;
    private javax.swing.JButton ven_con_bt_desactivar;
    private javax.swing.JTextField ven_con_cod_tran_field;
    private javax.swing.JTextField ven_con_fecha_pago_field;
    private javax.swing.JButton ven_con_n_bt_buscar;
    private javax.swing.JTextField ven_con_n_pedido_field;
    private javax.swing.JTextField ven_con_nom_cliente_field;
    private javax.swing.JTextField ven_con_rut_field;
    private javax.swing.JTable ven_con_tabla;
    private javax.swing.JButton venta_bt_cancelar;
    private javax.swing.JButton venta_bt_guardar;
    private javax.swing.JButton venta_bt_modificar;
    private javax.swing.JButton venta_buscar_n_pedido;
    private javax.swing.JButton venta_buscar_rut;
    private javax.swing.JComboBox<String> venta_comuna_box;
    private javax.swing.JTextField venta_destinatario_field;
    private javax.swing.JTextField venta_direccion_field;
    private javax.swing.JTextField venta_email_field;
    private javax.swing.JTextField venta_entrega_fin;
    private javax.swing.JLabel venta_entrega_ini;
    private javax.swing.JComboBox<String> venta_estado_box;
    private javax.swing.JTextField venta_fecha_entrega;
    private javax.swing.JTextField venta_fecha_entrega_ini;
    private javax.swing.JTextField venta_nom_cli_field;
    private javax.swing.JTextField venta_num_pedido_field;
    private javax.swing.JComboBox<String> venta_pack_box;
    private javax.swing.JComboBox<String> venta_rrss_box;
    private javax.swing.JTextField venta_rut_field;
    private javax.swing.JTextField venta_saludo_field;
    private javax.swing.JTextField venta_telefono_field;
    // End of variables declaration//GEN-END:variables
}
