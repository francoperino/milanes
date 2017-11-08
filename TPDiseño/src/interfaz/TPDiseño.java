/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Util.HibernateUtil;
import controlador.GestorBedel;
import daos.DaoBedel;
import datos.Admin;
import datos.Bedel;
import datos.Ciclolectivo;
import datos.Politicadeseguridad;
import datos.Usuario;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author franc
 */
public class TPDiseño {

    /**
     *
     * @param args
     */
   
    public static void main(String[] args) {
        DaoBedel db = new DaoBedel();
        db.buscarPorApellido("a");
        /* datos.Politicadeseguridad adm = new datos.Politicadeseguridad();
        adm.setIdPolitica(1);
        adm.setContienedigito(true);
        adm.setIgualaanterior(false);
        adm.setLongclavemin(7) ;
        adm.setSignosespeciales("@");
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(adm);
        s.getTransaction().commit();
        System.out.println("hola");
        System.exit(0);*/
        /*Politicadeseguridad adm = new Politicadeseguridad();
        adm.setIdPolitica(0);
        adm.setSignosespeciales("@");
        adm.setLongclavemin(7);
        adm.setContienedigito(true);
        adm.setIgualaanterior(false);
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(adm);
        s.getTransaction().commit();
        System.out.println("hola");
        System.exit(0);
       */
       /* iniciarSesion obj=new iniciarSesion();
        obj.setVisible(true);*/

    }
    
}
