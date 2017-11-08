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
import org.hibernate.Session;
import org.hibernate.query.Query;


/**
 *
 * @author franc
 */
public class DaoBedel {
    
    public void insertarBedel(String apellido,String nombre,String turno,String nickUsuario,String contrasea){
                     Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                     s.beginTransaction();
               
                     Clave cl = new Clave();
                     Politicadeseguridad ps = new Politicadeseguridad();
                     s.createQuery("select MAX(idpolitica) from Politicadeseguridad  ");
                     
                     ps.setSignosespeciales("!$%&)(*+,-./@");
                     ps.setLongclavemin(7);
                     ps.setContienedigito(true);
                     ps.setIgualaanterior(false);
                     s.save(ps);        
                     Usuario ar = new Usuario();
                     ar.setNickusuario(nickUsuario);
                     ar.setApellido(apellido);
                     ar.setNombre(nombre);
                     s.save(ar);
                     Bedel adm = new Bedel(ar,turno);
                     s.save(adm);
                     Date mifecha = new Date(115,6,2);
                     Clave c = new Clave(ps,adm,contrasea,mifecha);
                     s.save(c);
                     s.getTransaction().commit();
                    
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
    /*public List <Bedel> buscarPorTurno(String turno){
        Bedel bedel = new Bedel();
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Bedel> lista = s.createQuery("select apellido from Usuario where( )")
    }
    public List <Bedel> buscarPorTurnoyApellido(String turno,String apellido){
        Bedel bedel = new Bedel();
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Bedel> lista = s.createQuery("select apellido from Usuario where( )")
    }*/
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
