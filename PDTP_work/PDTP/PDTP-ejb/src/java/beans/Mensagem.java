
package beans;

import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.ZoneOffset;
import java.util.Date;

public class Mensagem {
   private String remetente;
   private String destinatario;
   private MensagemEstado estado;
   private String texto;
   private String assunto;
   private long data;
    public Mensagem(String remetente, String destinatario, String texto,String assunto, MensagemEstado estado) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.estado = estado;
        this.texto = texto;
        this.assunto = assunto;
        this.data = LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
    }

    public String getTexto() {
        this.estado=MensagemEstado.LIDA;
        return texto;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public MensagemEstado getEstado() {
        return estado;
    }

    public long getData() {
        return data;
    }
   
}
