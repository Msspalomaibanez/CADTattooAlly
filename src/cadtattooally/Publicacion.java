/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadtattooally;

/**
 *
 * @author usuario
 */
public class Publicacion{
    
    private Integer publiId;
    private Integer publiInteraccion;
    private String publiImagen;
    private String publiDescripcion;
    private String publiComentario;
    private Usuario usuario;

    public Publicacion() {
    }

    public Publicacion(Integer publiId, Integer publiInteraccion, String publiImagen, String publiDescripcion, String publiComentario) {
        this.publiId = publiId;
        this.publiInteraccion = publiInteraccion;
        this.publiImagen = publiImagen;
        this.publiDescripcion = publiDescripcion;
        this.publiComentario = publiComentario;
    }

    public Integer getPubliId() {
        return publiId;
    }

    public void setPubliId(Integer publiId) {
        this.publiId = publiId;
    }

    public Integer getPubliInteraccion() {
        return publiInteraccion;
    }

    public void setPubliInteraccion(Integer publiInteraccion) {
        this.publiInteraccion = publiInteraccion;
    }

    public String getPubliImagen() {
        return publiImagen;
    }

    public void setPubliImagen(String publiImagen) {
        this.publiImagen = publiImagen;
    }

    public String getPubliDescripcion() {
        return publiDescripcion;
    }

    public void setPubliDescripcion(String publiDescripcion) {
        this.publiDescripcion = publiDescripcion;
    }

    public String getPubliComentario() {
        return publiComentario;
    }

    public void setPubliComentario(String publiComentario) {
        this.publiComentario = publiComentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Publicacion{" + "publiId=" + publiId + ", publiInteraccion=" + publiInteraccion + ", publiImagen=" + publiImagen + ", publiDescripcion=" + publiDescripcion + ", publiComentario=" + publiComentario + ", usuario=" + usuario + '}';
    }
    
    
}
