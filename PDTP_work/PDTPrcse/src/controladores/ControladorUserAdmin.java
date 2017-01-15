
package controladores;


import beans.ClientRemote;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ControladorUserAdmin extends Controlador{
  
    public ControladorUserAdmin(ClientRemote ligacao) {
        super(ligacao);
        this.ligacao = ligacao;
    }
        public String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }
    //public abstract void logOff();
}
