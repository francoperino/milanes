package datos;
// Generated 08/11/2017 00:15:15 by Hibernate Tools 4.3.1



/**
 * Admin generated by hbm2java
 */
public class Admin extends Usuario  implements java.io.Serializable {


     private String nickusuario;
     private Usuario usuario;

    public Admin() {
    }

    public Admin(Usuario usuario) {
       this.usuario = usuario;
    }
   
     @Override
    public String getNickusuario() {
        return this.nickusuario;
    }
    
     @Override
    public void setNickusuario(String nickusuario) {
        this.nickusuario = nickusuario;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




}


