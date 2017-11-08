/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.Scanner; 
/**
 *
 * @author franc
 */
public class GestorPoliticaClave {
   public Integer validarClave(String clave){
       
       if(clave.length()>6 && clave.length()<20){
           if(esMayuscula(clave)){
               if(esDigito(clave)){
                   if(esEspecial(clave)){
                       return 0;
                  }else return 1;
               }else return 2;
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
           if(((int)(clave.charAt(i))>=33 && (int)(clave.charAt(i))<=47)|| ((int)(clave.charAt(i))==64) ){
               esEspecial=true;
           }
       }
   return esEspecial;
   }
}
