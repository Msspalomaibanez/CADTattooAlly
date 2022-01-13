/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadtattooally;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/**
 * En esta clase se ofrece el CAD para gestionar la BD de datos TattooAlly
 *
 * @author usuario
 */
public class CADTattooAlly {

    Connection conexion;

    public CADTattooAlly() throws ExceptionTattooAlly {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(null);
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(null);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }

    }

    private void conectarBD() throws ExceptionTattooAlly {
        try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.220:1521:test", "TattooAlly", "kk");
        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(null);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }

    }
    //hecho y probado
    /**
     * Método que permite la inserción de una registro en la tabla USUARIO
     *
     * @param usuario objeto de tipo USUARIO que contiene todos los datos del
     * nuevo registro a insertar
     * @return Cantidad de registro insertados (siempre devolverá uno)
     * @throws ExceptionTattooAlly si se produce una violación de alguna de las
     * constraints de la BD o si se produce cualquier otro error
     */
    public int insertarUsuario(Usuario usuario) throws ExceptionTattooAlly {
        conectarBD();
        int registrosAfectados = 0;
        String dml = null;
        try {
            dml = "{call insertar_usuario(?,?,?,?)}";
            CallableStatement cs = conexion.prepareCall(dml);
            cs.setString(1, usuario.getUsuarioNombre());
            cs.setString(2, usuario.getUsuarioTlfn());
            cs.setString(3, usuario.getUsuarioEmail());
            cs.setString(4, usuario.getUsuarioAlias());
            registrosAfectados = cs.executeUpdate();
            cs.executeQuery();
            cs.close();
            conexion.close();
        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 1400:
                    e.setMensajeErrorUsuario("El identificador del usuario, el nombre, el email y el alias del usuario es obligatorio");
                    break;
                case 00001:
                    e.setMensajeErrorUsuario("El teléfono, el correo electrónico y el alias de un usuario deben ser únicos");
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }

            throw e;
        }
        return registrosAfectados;
    }

    // hecho y probado
    /**
     * Método que permite la eliminación de una registro en la tabla USUARIO
     *
     * @param usuarioId identificador de aquel registro que queramos eliminar
     * @return Cantidad de registro eliminados (siempre devolverá uno)
     * @throws ExceptionTattooAlly si se produce una violación de alguna de las
     * constraints de la BD o si se produce cualquier otro error
     */
    public int eliminarUsuario(Integer usuarioId) throws ExceptionTattooAlly {
        conectarBD();
        String dml = "delete from USUARIO where USUARIO_ID=" + usuarioId;

        try {
            Statement sentencia = conexion.createStatement();
            int registrosAfectados = sentencia.executeUpdate(dml);
            System.out.println("Registros Afectados: " + registrosAfectados);
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 2292:
                    e.setMensajeErrorUsuario("No se puede eliminar el usuario porque tiene publicaciones asociadas");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }

            throw e;
        }
        return 0;
    }
    //errores con el update
    /**
     * Método que permite la actualización de una registro ya insertado en la
     * tabla USUARIO
     *
     * @param usuario objeto de tipo USUARIO que contiene todos los datos del
     * nuevo registro a actualizar
     * @param usuarioId identificador de aquel registro que queramos actualizar
     * @return Cantidad de registro actualizados (siempre devolverá uno)
     * @throws ExceptionTattooAlly si se produce una violación de alguna de las
     * constraints de la BD o si se produce cualquier otro error
     */
    public int actualizarUsuario(Integer usuarioId, Usuario usuario) throws ExceptionTattooAlly {
        conectarBD();
        int registrosAfectados = 0;
        String dml = "update USUARIO set USUARIO_ID = ?, USUARIO_NOMBRE = ?, USUARIO_TLFN = ?, USUARIO_EMAIL = ?, USUARIO_ALIAS = ? where USUARIO_ID = ?";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setObject(1, usuarioId, Types.INTEGER);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            while (resultado.next()) {
                usuario.setUsuarioId(resultado.getInt("USUARIO_ID"));
                usuario.setUsuarioNombre(resultado.getString("USUARIO_NOMBRE"));
                usuario.setUsuarioTlfn(resultado.getString("USUARIO_TLFN"));
                usuario.setUsuarioEmail(resultado.getString("USUARIO_EMAIL"));
                usuario.setUsuarioAlias(resultado.getString("USUARIO_ALIAS"));
            }
            resultado.close();
            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 1400:
                    e.setMensajeErrorUsuario("El identificador del usuario, el nombre, el email y el alias de la publicación es obligatorio");
                    break;
                case 00001:
                    e.setMensajeErrorUsuario("El teléfono, el correo electrónico y el alias de un usuario deben ser únicos");
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            throw e;
        }
        return registrosAfectados;
    }

    //hecho y revisado
    /**
     * Método que permite la lectura de una registro ya insertado en la tabla
     * USUARIO
     *
     * @param usuarioId identificador de aquel registro que queramos leer
     * @returns un objeto de tipo USUARIO que contendrá todos los parámetros del
     * registro seleccionado
     * @throws ExceptionTattooAlly si se produce una violación de alguna de las
     * constraints de la BD o si se produce cualquier otro error
     */
    public Usuario leerUsuario(Integer usuarioId) throws ExceptionTattooAlly {
        conectarBD();
        String dql = null;
        Usuario u = new Usuario();
        try {
            dql = "select * from USUARIO where USUARIO_ID = ?";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dql);
            sentenciaPreparada.setObject(1, usuarioId, Types.INTEGER);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            while (resultado.next()) {
                u.setUsuarioId(resultado.getInt("USUARIO_ID"));
                u.setUsuarioNombre(resultado.getString("USUARIO_NOMBRE"));
                u.setUsuarioTlfn(resultado.getString("USUARIO_TLFN"));
                u.setUsuarioEmail(resultado.getString("USUARIO_EMAIL"));
                u.setUsuarioAlias(resultado.getString("USUARIO_ALIAS"));
            }
            resultado.close();
            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }

        return u;
    }

    //hecho y probado
    /**
     * Método que permite la lectura de todos los registros de la tabla USUARIO
     *
     * @return una lista (ArrayList) de objetos de tipo USUARIO
     * @throws ExceptionTattooAlly si se produce una violación de alguna de las
     * constraints de la BD o si se produce cualquier otro error
     */
    public ArrayList<Usuario> leerUsuarios() throws ExceptionTattooAlly {
        conectarBD();
        ArrayList<Usuario> lista = new ArrayList<>();
        String dql = "select * from USUARIO";

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                Usuario u = new Usuario();
                u.setUsuarioId(resultado.getInt("USUARIO_ID"));
                u.setUsuarioNombre(resultado.getString("USUARIO_NOMBRE"));
                u.setUsuarioTlfn(resultado.getString("USUARIO_TLFN"));
                u.setUsuarioEmail(resultado.getString("USUARIO_EMAIL"));
                u.setUsuarioAlias(resultado.getString("USUARIO_USUARIO"));
                lista.add(u);
            }
            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        return lista;
    }

    //hecho y revisado menos fallos con el trigger
    /**
     * Método que permite la inserción de una registro en la tabla PUBLICACION
     *
     * @param publicacion objeto de tipo PUBLICACION que contiene todos los
     * datos del nuevo registro a insertar
     * @return Cantidad de registro insertados (siempre devolverá uno)
     * @throws ExceptionTattooAlly si se produce una violación de alguna de las
     * constraints de la BD o si se produce cualquier otro error
     */
    public int insertarPublicacion(Publicacion publicacion) throws ExceptionTattooAlly {
        conectarBD();
        int registrosAfectados = 0;
        String dml = null;
        try {
            dml = "insert into PUBLICACION(PUBLI_ID, PUBLI_INTERACCION, PUBLI_IMAGEN, "
                    + "PUBLI_DESCRIPCION, PUBLI_COMENTARIO, USUARIO_ID) values (SEQ_PUBLI.nextval,?,?,?,?,?)";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setObject(1, publicacion.getPubliInteraccion(), Types.INTEGER);
            sentenciaPreparada.setString(2, publicacion.getPubliImagen());
            sentenciaPreparada.setString(3, publicacion.getPubliDescripcion());
            sentenciaPreparada.setString(4, publicacion.getPubliComentario());
            sentenciaPreparada.setObject(5, publicacion.getUsuario().getUsuarioId(), Types.INTEGER);
            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 1400:
                    e.setMensajeErrorUsuario("El identificador de la publicación, la interacción y la descripción de la publicación son obligatorios");
                    break;
                case 2291:
                    e.setMensajeErrorUsuario("No existe el usuario seleccionado");
                    break;
                case 20004:
                    e.setMensajeErrorUsuario("Un usuario no puede tener más de 5 publicaciones");
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }

            throw e;
        }
        return registrosAfectados;
    }

    // hecho y revisado
    /**
     * Método que permite la eliminación de una registro en la tabla PUBLICACION
     *
     * @param publiId identificador de aquel registro que queramos eliminar
     * @return Cantidad de registro eliminados (siempre devolverá uno)
     * @throws ExceptionTattooAlly si se produce una violación de alguna de las
     * constraints de la BD o si se produce cualquier otro error
     */
    public int eliminarPublicacion(Integer publiId) throws ExceptionTattooAlly {
        conectarBD();
        String dml = "delete from PUBLICACION where PUBLI_ID=" + publiId;

        try {
            Statement sentencia = conexion.createStatement();
            int registrosAfectados = sentencia.executeUpdate(dml);
            System.out.println("Registros Afectados: " + registrosAfectados);
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dml);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");

            throw e;
        }
        return 0;
    }
    //errores con el update y revisar error de trigger
    /**
     * Método que permite la actualización de una registro ya insertado en la
     * tabla PUBLICACION
     *
     * @param publicacion objeto de tipo PUBLICACION que contiene todos los
     * datos del nuevo registro a actualizar
     * @param publiId identificador de aquel registro que queramos actualizar
     * @return Cantidad de registro actualizados (siempre devolverá uno)
     * @throws ExceptionTattooAlly si se produce una violación de alguna de las
     * constraints de la BD o si se produce cualquier otro error
     */
    public int actualizarPublicacion(Integer publiId, Publicacion publicacion) throws ExceptionTattooAlly {
        conectarBD();
        int registrosAfectados = 0;
        String dml = null;
        try {
            dml = "{call actualizar_publicacion(?,?,?,?)}";
            CallableStatement cs = conexion.prepareCall(dml);
            cs.setObject(1, publicacion.getPubliInteraccion(), Types.INTEGER);
            cs.setString(2, publicacion.getPubliImagen());
            cs.setString(3, publicacion.getPubliDescripcion());
            cs.setString(4, publicacion.getPubliComentario());
            registrosAfectados = cs.executeUpdate();
            cs.executeQuery();
            cs.close();
            conexion.close();
        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 1400:
                    e.setMensajeErrorUsuario("El identificador de la publicación, la interacción y la descripción de la publicación es obligatorio");
                    break;
                case 2291:
                    e.setMensajeErrorUsuario("No existe el usuario seleccionado");
                    break;
                case 20003:
                    e.setMensajeErrorUsuario("No se puede cambiar el usuario de la publicación");
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }

            throw e;
        }
        return registrosAfectados;
    }

    //hecho y revisado
    /**
     * Método que permite la lectura de una registro ya insertado en la tabla
     * PUBLICACION
     *
     * @param publiId identificador de aquel registro que queramos leer
     * @return un objeto de tipo PUBLICACION que contendrá todos los parámetros
     * del registro seleccionado
     * @throws ExceptionTattooAlly si se produce una violación de alguna de las
     * constraints de la BD o si se produce cualquier otro error
     */
    public Publicacion leerPublicacion(Integer publiId) throws ExceptionTattooAlly {
        conectarBD();
        String dql = null;
        Publicacion p = new Publicacion();
        try {
            dql = "select * from PUBLICACION p, USUARIO u where p.USUARIO_ID = u.USUARIO_ID and PUBLI_ID = ?";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dql);
            sentenciaPreparada.setObject(1, publiId, Types.INTEGER);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            while (resultado.next()) {
                p.setPubliId(resultado.getInt("PUBLI_ID"));
                p.setPubliInteraccion(resultado.getInt("PUBLI_INTERACCION"));
                p.setPubliImagen(resultado.getString("PUBLI_IMAGEN"));
                p.setPubliDescripcion(resultado.getString("PUBLI_DESCRIPCION"));
                p.setPubliComentario(resultado.getString("PUBLI_COMENTARIO"));
                Usuario u = new Usuario();
                p.setUsuario(u);
                u.setUsuarioId(resultado.getInt("USUARIO_ID"));
                u.setUsuarioNombre(resultado.getString("USUARIO_NOMBRE"));
                u.setUsuarioTlfn(resultado.getString("USUARIO_EMAIL"));
                u.setUsuarioAlias(resultado.getString("USUARIO_ALIAS"));
            }
            resultado.close();
            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }

        return p;
    }

    //hecho y revisado
    /**
     * Método que permite la lectura de todos los registros de la tabla
     * PUBLICACION
     *
     * @return una lista (ArrayList) de objetos de tipo PUBLICACION
     * @throws ExceptionTattooAlly si se produce una violación de alguna de las
     * constraints de la BD o si se produce cualquier otro error
     */
    public ArrayList<Publicacion> leerPublicaciones() throws ExceptionTattooAlly {
        conectarBD();
        ArrayList<Publicacion> lista = new ArrayList<>();
        String dql = "select * from USUARIO u, PUBLICACION p where u.USUARIO_ID = p.USUARIO_ID";

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                Publicacion p = new Publicacion();
                p.setPubliId(resultado.getInt("PUBLI_ID"));
                p.setPubliInteraccion(resultado.getInt("PUBLI_INTERACCION"));
                p.setPubliImagen(resultado.getString("PUBLI_IMAGEN"));
                p.setPubliDescripcion(resultado.getString("PUBLI_DESCRIPCION"));
                p.setPubliComentario(resultado.getString("PUBLI_COMENTARIO"));
                Usuario u = new Usuario();
                p.setUsuario(u);
                u.setUsuarioId(resultado.getInt("USUARIO_ID"));
                u.setUsuarioNombre(resultado.getString("USUARIO_NOMBRE"));
                u.setUsuarioTlfn(resultado.getString("USUARIO_EMAIL"));
                u.setUsuarioAlias(resultado.getString("USUARIO_USUARIO"));
                lista.add(p);
            }
            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExceptionTattooAlly e = new ExceptionTattooAlly();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeErrorBD(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        return lista;
    }
}
