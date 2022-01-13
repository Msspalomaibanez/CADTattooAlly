/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import cadtattooally.*;

/**
 *
 * @author usuario
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {

        /*try {
            cad.eliminarUsuario(1);
        } catch (ExceptionTattooAlly ex) {
            System.out.println(ex.toString());
        }*/
        Publicacion p = new Publicacion();
        p.setPubliInteraccion(54);
        p.setPubliImagen("ruta no se");
        p.setPubliDescripcion("fojghjkljhfe");
        p.setPubliComentario("hecho por x");
        p.setUsuario(new Usuario(5));

          Usuario u = new Usuario();
          u.setUsuarioNombre("estefa");
          u.setUsuarioTlfn("999999999");
          u.setUsuarioEmail("estefania@gmail.com");
          u.setUsuarioAlias("estef65");
          
        try {
            CADTattooAlly cad = new CADTattooAlly();
            //System.out.println(cad.leerPublicaciones());
            //System.out.println(cad.leerUsuarios());
            System.out.println(cad.leerUsuario(6));
            //System.out.println(cad.leerPublicacion(1));
            int registros; // = cad.eliminarPublicacion(1);
            //registros = cad.actualizarUsuario(5, u);
            //registros = cad.insertarPublicacion(p);
            //registros = cad.eliminarUsuario(3);
            //System.out.println(cad.leerPublicacion(3));
            //registros = cad.eliminarPublicacion(4);
//            int registros = cad.insertarPublicacion(p);
//            System.out.println("Operación realizada correctamente. " + registros + " registro/s insertado/s");
//            registros = cad.eliminarUsuario(1);
//            System.out.println("Operación realizada correctamente. " + registros + " registro/s eliminado/s");
        } catch (ExceptionTattooAlly ex) {
            System.out.println(ex.toString());
        }
    }

}
