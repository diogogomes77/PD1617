package controladores;

import beans.ClientAuthRemote;
import beans.ClientVisitanteRemote;
import beans.Mensagem;
import beans.SessionException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import menus.MenuVisitante;
import pdtprcse.ReferenciaVisitante;

/**
 *
 * @author diogo
 */
public abstract class ControladorUserAdmin extends Controlador {

    protected ClientAuthRemote ligacaoAuth;

    /**
     *
     * @param ligacao
     */
    public ControladorUserAdmin(ClientAuthRemote ligacao) {
        super(ligacao);
        this.ligacaoAuth = ligacao;
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

    /**
     *
     */
    @Override
    public void logOff() {
        try {
            if (ligacaoAuth.logOff()) {
                System.out.println("\nlog off");

            } else {
                System.out.println("ERRO: accao nao aceite");
            }
        } catch (SessionException ex) {
        }
        ReferenciaVisitante refVisitante = new ReferenciaVisitante();
        ClientVisitanteRemote ligVisitante = refVisitante.getLigacao();
        controlador = new ControladorVisitante(ligVisitante);
        menu = new MenuVisitante(ligVisitante, (ControladorVisitante) controlador);
    }

    /**
     *
     */
    public void enviarMensagem() {
        System.out.println("enviar Mensagem a utilizador");
        String destinatario;
        String texto;
        String assunto;
        System.out.print("Destinatario: ");
        destinatario = sc.nextLine();
        System.out.print("Assunto: ");
        assunto = sc.nextLine();
        System.out.print("Texto: ");
        texto = sc.nextLine();
        try {
            if (ligacaoAuth.sendMensagem(destinatario, texto, assunto)) {
                System.out.println("Mensagem enviada");
            } else {
                System.out.println("ERRO: mensagem nao enviada");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void consultarMensagensMinhas() {
        System.out.println("Minhas mensagems:");
        ArrayList<Mensagem> mensagens;
        try {
            mensagens = ligacaoAuth.consultarMensagens();
            for (Mensagem msg : mensagens) {
                System.out.println("Enviada: ".concat(msg.getData().toString()).concat(" por: ").concat(msg.getDestinatario()).concat(" Assunto: ").concat(msg.getAssunto()));
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    /**
     *
     */
    public void alterarPassword() {
        String password;
        System.out.print("Antiga password: ");
        password = sc.nextLine();
        try {
            if (ligacaoAuth.verificaPassword(password)) {
                System.out.print("Nova password: ");
                password = sc.nextLine();
                if (ligacaoAuth.alteraPassword(password)) {
                    System.out.println("Password alterada com sucesso");
                } else {
                    System.out.println("ERRO: Password nao alterada");
                }
            } else {
                System.out.println("ERRO: Password antiga incorreta");
            }
        } catch (SessionException ex) {
            if (ex.getStatus() == SessionException.sessionStatus.LOGOUTSTAUS) {
                this.logOff();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            this.logOff();
        } finally {
            super.finalize();
        }
    }

}
