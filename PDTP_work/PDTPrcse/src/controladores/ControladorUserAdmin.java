
package controladores;


import beans.ClientRemote;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author diogo
 */
public abstract class ControladorUserAdmin extends Controlador{

    /**
     *
     */
    protected int currentItemId;

    /**
     *
     * @param ligacao
     */
    public ControladorUserAdmin(ClientRemote ligacao) {
        super(ligacao);
        this.ligacao = ligacao;
    }

    /**
     *
     * @param time
     * @return
     */
    public String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }
    //public abstract void logOff();
}
