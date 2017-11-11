/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Util.HibernateUtil;
import daos.DaoBedel;
import datos.Bedel;
import datos.Clave;
import datos.Politicadeseguridad;
import datos.Usuario;
import interfaz.InicioAdmin;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Session;


public class GestorBedel {
    DaoBedel db;
    
    InicioAdmin ia = new InicioAdmin(); 
    GestorPoliticaClave gpc = new GestorPoliticaClave();
    public Integer registrarBedel(String apellido,String nombre,String turno,String nickUsuario,String contrasea ){
       
        db = new DaoBedel();
        Bedel bd = new Bedel();
        bd.setApellido(apellido);
        bd.setNickusuario(nickUsuario);
        bd.setNombre(nombre);
        bd.setTurno(turno);
        Integer valor = gpc.validarPoliticas(contrasea);
        switch(valor){
            case 0:
                 if(db.consultarNickBedel(nickUsuario).isEmpty()){
                     db.insertarBedel(bd,contrasea);
                     valor =7 ;
                     
                 }
                 else{
                     valor = 100;
                 }
                break;
            default:
                
                
                break;
                
            
        }
                
       return valor; 
    }
    public Integer modificarBedel(String apellido,String nombre,String turno,String contrasea,String nickUsuario){
        db = new DaoBedel();
        Integer valor = gpc.validarPoliticas(contrasea);
        switch(valor){
            case 0:
                if(/*db.recuperarBedel(nickUsuario,)*/true){
                     
                     valor =7 ;
                     
                 }
                 else{
                     valor = 100;
                 }
                break;
            default:
                
                
                break;
                
            
        }
                
       return valor;
        
    }
}
