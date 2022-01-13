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
public class ExceptionTattooAlly extends Exception{
    private Integer codigoError;
    private String mensajeErrorBD;
    private String mensajeErrorUsuario;
    private String sentenciaSQL;

    public ExceptionTattooAlly() {
    }

    public ExceptionTattooAlly(Integer codigoError, String mensajeErrorBD, String mensajeErrorUsuario, String sentenciaSQL) {
        this.codigoError = codigoError;
        this.mensajeErrorBD = mensajeErrorBD;
        this.mensajeErrorUsuario = mensajeErrorUsuario;
        this.sentenciaSQL = sentenciaSQL;
    }

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensajeErrorBD() {
        return mensajeErrorBD;
    }

    public void setMensajeErrorBD(String mensajeErrorBD) {
        this.mensajeErrorBD = mensajeErrorBD;
    }

    public String getMensajeErrorUsuario() {
        return mensajeErrorUsuario;
    }

    public void setMensajeErrorUsuario(String mensajeErrorUsuario) {
        this.mensajeErrorUsuario = mensajeErrorUsuario;
    }

    public String getSentenciaSQL() {
        return sentenciaSQL;
    }

    public void setSentenciaSQL(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    @Override
    public String toString() {
        return "ExceptionTattooAlly{" + "codigoError=" + codigoError + ", mensajeErrorBD=" + mensajeErrorBD + ", mensajeErrorUsuario=" + mensajeErrorUsuario + ", sentenciaSQL=" + sentenciaSQL + '}';
    }
    
    
    
}
