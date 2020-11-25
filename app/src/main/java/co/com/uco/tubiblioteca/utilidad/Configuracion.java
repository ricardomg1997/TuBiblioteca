package co.com.uco.tubiblioteca.utilidad;

import co.com.uco.tubiblioteca.dto.Usuario;

public class Configuracion {
    private static Usuario usuario;
    public static  Usuario getUsuario(){
        return usuario;
    }
    public static  void setUsuario(Usuario usuario){
        Configuracion.usuario = usuario;
    }

}
