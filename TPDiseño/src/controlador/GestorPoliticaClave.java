/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import Util.HibernateUtil;
import datos.Politicadeseguridad;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import java.util.Scanner; 
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author franc
 */
public class GestorPoliticaClave {
   String sign;
     
       
            
            
    
   public Integer validarPoliticas(String clave){
       Session s =  HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            int lgm=0;
            
            Boolean iguales=false,contiene=false;
            Query query= s.createQuery("select  new map(max(p.idpolitica)) from Politicadeseguridad p ");
            List<Map> lista = query.list();
            for(int i=0; i<lista.size();i++){
                Map mapa = lista.get(i);
                Set llaves = mapa.keySet();
                for(Iterator<String> it = llaves.iterator(); it.hasNext();){
                    String llaveActual = it.next();
                   
                    Politicadeseguridad ps =(Politicadeseguridad) s.get(Politicadeseguridad.class,(int)mapa.get(llaveActual));
                    sign = ps.getSignosespeciales();
                    iguales = ps.getIgualaanterior();
                    lgm = ps.getLongclavemin();
                    contiene = ps.getContienedigito();
                    
                }
                 
                
            }
            
            
            s.getTransaction().commit();
            if(contiene){
       
       if(clave.length()>=lgm && clave.length()<20){
           if(esMayuscula(clave)){
               if(esDigito(clave)){
                   if(esEspecial(clave)){
                       return 0;
                  }else return 1;
               }else return 2;
           }else return 3;
       }else return 4;
            }
            else 
       
       if(clave.length()>=lgm && clave.length()<20){
           if(esMayuscula(clave)){
                   if(esEspecial(clave)){
                       return 0;
                  }else return 1;
           }else return 3;
       }else return 4;
       
        
    }
   public boolean esMayuscula(String clave){

      
       boolean mayuscula=false;
       for(int i=0; i<clave.length(); i++){
           if(Character.isUpperCase(clave.charAt(i))){
               mayuscula=true;
           }
       }
   return mayuscula;
   }
   public boolean esDigito(String clave){

      
       boolean esDigito=false;
       for(int i=0; i<clave.length(); i++){
           if(Character.isDigit(clave.charAt(i))){
               esDigito=true;
           }
       }
   return esDigito;
   }
   public boolean esEspecial(String clave){

      
       boolean esEspecial=false;
       for(int i=0; i<clave.length(); i++){
           int resultado = sign.indexOf(clave.charAt(i));

           if(resultado !=-1){
               esEspecial=true;
           }
       }
   return esEspecial;
   }
}