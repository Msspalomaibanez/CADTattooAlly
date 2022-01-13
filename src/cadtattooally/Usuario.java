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
public class Usuario {
    private Integer usuarioId;
    private String usuarioNombre;
    private String usuarioTlfn;
    private String usuarioEmail;
    private String usuarioAlias;

    public Usuario() {
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    
    public Usuario(Integer usuarioId, String usuarioNombre, String usuarioTlfn, String usuarioEmail, String usuarioAlias) {
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.usuarioTlfn = usuarioTlfn;
        this.usuarioEmail = usuarioEmail;
        this.usuarioAlias = usuarioAlias;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioTlfn() {
        return usuarioTlfn;
    }

    public void setUsuarioTlfn(String usuarioTlfn) {
        this.usuarioTlfn = usuarioTlfn;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getUsuarioAlias() {
        return usuarioAlias;
    }

    public void setUsuarioAlias(String usuarioAlias) {
        this.usuarioAlias = usuarioAlias;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuarioId=" + usuarioId + ", usuarioNombre=" + usuarioNombre + ", usuarioTlfn=" + usuarioTlfn + ", usuarioEmail=" + usuarioEmail + ", usuarioAlias=" + usuarioAlias + '}';
    }
    
    
}
