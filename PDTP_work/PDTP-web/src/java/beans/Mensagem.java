package beans;




import java.io.Serializable;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.ZoneOffset;
import java.util.Date;

/**
 *
 * @author diogo
 */
public class Mensagem implements Serializable {
   private String remetente;
   private String destinatario;
   private MensagemEstado estado;
   private String texto;
   private String assunto;
   private long data;

    /**
     *
     * @param remetente
     * @param destinatario
     * @param texto
     * @param assunto
     * @param estado
     */
    public Mensagem(String remetente, String destinatario, String texto,String assunto, MensagemEstado estado) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.estado = estado;
        this.texto = texto;
        this.assunto = assunto;
        this.data = LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
    }

    /**
     *
     * @return
     */
    public String getTexto() {
        return texto;
    }

    /**
     *
     * @return
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     *
     * @return
     */
    public String getRemetente() {
        return remetente;
    }

    /**
     *
     * @return
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     *
     * @return
     */
    public MensagemEstado getEstado() {
        return estado;
    }

    /**
     *
     * @return
     */
    public long getData() {
        return data;
    }
   
}
