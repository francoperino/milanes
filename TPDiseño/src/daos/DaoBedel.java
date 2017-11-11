/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;



import Util.HibernateUtil;
import datos.Bedel;
import datos.Clave;
import datos.Politicadeseguridad;
import datos.Usuario;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
//import javax.management.Query;
//import org.hibernate.Query;


/**
 *
 * @author franc
 */
public class DaoBedel {
    
    public void insertarBedel(Bedel bedel, String contrasea){
                     Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                     s.beginTransaction();
                     int lgm=0;
                      Politicadeseguridad p = new Politicadeseguridad();
            Query query= s.createQuery("select  new map(max(p.idpolitica)) from Politicadeseguridad p ");
            List<Map> lista = query.list();
            for(int i=0; i<lista.size();i++){
                Map mapa = lista.get(i);
                Set llaves = mapa.keySet();
                for(Iterator<String> it = llaves.iterator(); it.hasNext();){
                    String llaveActual = it.next();
                   
                    Politicadeseguridad ps =(Politicadeseguridad) s.get(Politicadeseguridad.class,(int)mapa.get(llaveActual));
                    
                    lgm = ps.getIdpolitica();
                }
                 
                
            }
                     p.setIdpolitica(lgm);
                     ;
                     Usuario us = new Usuario();
                     us.setNickusuario(bedel.getNickusuario());
                     us.setApellido(bedel.getApellido());
                     us.setNombre(bedel.getNombre());
                     s.save(us);
                     Bedel adm =  new Bedel();
                     adm.setUsuario(us);
                     adm.setNickusuario(bedel.getNickusuario());
                     adm.setTurno(bedel.getTurno());
                     s.save(adm);
                     
                     
                     
                     
                     
                           
                     
                     
                     
                     
                     
                     Date mifecha = new Date(115,6,2);
                     Clave c = new Clave(p,us,contrasea,mifecha);
                     
                     s.save(c);
                     s.getTransaction().commit();
                    
}
    public Bedel recuperarBedel(String nickBedel,Bedel bedel){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                     s.beginTransaction();
                     int lgm=0;
                      Politicadeseguridad p = new Politicadeseguridad();
            Query query= s.createQuery("select new map(u) from Usuario u, Bedel b where    (b.nickusuario = u.nickusuario) and (u.nickusuario =:nickUsuario) ").setParameter("nickUsuario",bedel.getNickusuario());
            List<Map> lista = query.list();
            for(int i=0; i<lista.size();i++){
                Map mapa = lista.get(i);
                Set llaves = mapa.keySet();
                for(Iterator<String> it = llaves.iterator(); it.hasNext();){
                    String llaveActual = it.next();
                   
                    Politicadeseguridad ps =(Politicadeseguridad) s.get(Politicadeseguridad.class,(int)mapa.get(llaveActual));
                    
                    lgm = ps.getIdpolitica();
                }
                 
                
            }
                    
                     s.getTransaction().commit();
        return bedel;
    }
    public List <Map> buscarPorApellido(String apellido){
        
            Session s =  HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            Query query= s.createQuery("select new map(u.apellido) from Usuario u, Bedel b where    (b.nickusuario = u.nickusuario) and (upper(u.apellido) LIKE upper(:apellido)) ").setParameter("apellido", apellido+"%");
            List<Map> lista = query.list();
            for(int i=0; i<lista.size();i++){
                Map mapa = lista.get(i);
                Set llaves = mapa.keySet();
                for(Iterator<String> it = llaves.iterator(); it.hasNext();){
                    String llaveActual = it.next();
                    System.out.println( mapa.get(llaveActual));
                }
                 
                
            }
            
            s.getTransaction().commit();
            
            return lista;
    }
    public List <Map> buscarPorTurno(String turno){
        Session s =  HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            Query query;
            
            if(turno.equals("Todos")){
                query= s.createQuery("select new map(b.turno,u.nombre,u.apellido,u.nickusuario) from Usuario u, Bedel b where    (b.nickusuario = u.nickusuario)"); 
            }
            else{
               query= s.createQuery("select new map(b.turno,u.nombre,u.apellido,u.nickusuario) from Usuario u, Bedel b where    (b.nickusuario = u.nickusuario) and (b.turno=:turno) ").setParameter("turno", turno); 
               
            }
            List<Map> lista = query.list();
           
            for(int i=0; i<lista.size();i++){
                Map mapa = lista.get(i);
                Set llaves = mapa.keySet();
                for(Iterator<String> it = llaves.iterator(); it.hasNext();){
                    String llaveActual = it.next();
                    System.out.println( mapa.get(llaveActual));
                }
                 
                
            }
            
            s.getTransaction().commit();
            
            return lista;
    }
    public List <Map> buscarPorTurnoyApellido(String turno,String apellido){
       Session s =  HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            Query query;
            
            if(turno.equals("Todos")){
                query= s.createQuery("select new map(b.turno,u.nombre,u.apellido,u.nickusuario) from Usuario u, Bedel b where    (b.nickusuario = u.nickusuario) and (upper(u.apellido) LIKE upper(:apellido)) ").setParameter("apellido", apellido+"%"); 
            }
            else{
               query= s.createQuery("select new map(b.turno,u.nombre,u.apellido,u.nickusuario) from Usuario u, Bedel b where    (b.nickusuario = u.nickusuario) and (b.turno=:turno) and (upper(u.apellido) LIKE upper(:apellido))").setParameter("turno", turno).setParameter("apellido", apellido+"%"); 
               
            }
            List<Map> lista = query.list();
           
            for(int i=0; i<lista.size();i++){
                Map mapa = lista.get(i);
                Set llaves = mapa.keySet();
                for(Iterator<String> it = llaves.iterator(); it.hasNext();){
                    String llaveActual = it.next();
                    System.out.println( mapa.get(llaveActual));
                }
                 
                
            }
            
            s.getTransaction().commit();
            
            return lista;
    }
    public List <Bedel> consultarNickBedel(String nickUsuario){
     
       Bedel bedel = new Bedel();
        
       
               Session s =  HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            //Criteria crit = s.createCriteria(Bedel.class);
            //List<Bedel> lista = (list<Bedel>)s.createQuery("From Bedel Where (nickUsuario == unnickUsuario");
           
            List<Bedel> lista = s.createQuery("from Bedel where nickUsuario =:nickUsuario").setParameter("nickUsuario", nickUsuario).list();
            /*
            for(Bedel b : lista){
                System.out.println("nick:" + b.getNickusuario());
                
            }*/
            s.getTransaction().commit();
            
            return lista;
    } 




}
