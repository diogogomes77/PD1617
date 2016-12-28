
package controladores;


import pdtp.ClientRemote;

public abstract class ControladorUserAdmin extends Controlador{
  
    public ControladorUserAdmin(ClientRemote ligacao) {
        super(ligacao);
        this.ligacao = ligacao;
    }
    public abstract void logOff();
}
