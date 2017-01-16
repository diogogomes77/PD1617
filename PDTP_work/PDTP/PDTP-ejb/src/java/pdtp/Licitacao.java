
package pdtp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author diogo
 */
public class Licitacao implements Serializable{
    private Item item;
    private Utilizador licitador;
    private Timestamp timestamp;
    private Double valor;

    /**
     *
     * @param item
     * @param licitador
     * @param valor
     */
    public Licitacao(Item item, Utilizador licitador, Double valor) {
        this.item = item;
        this.licitador = licitador;
        this.valor = valor;
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    /**
     *
     * @return
     */
    public Item getItem() {
        return item;
    }

    /**
     *
     * @return
     */
    public Utilizador getLicitador() {
        return licitador;
    }

    /**
     *
     * @return
     */
    public String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(timestamp);
       
    }

    /**
     *
     * @return
     */
    public Double getValor() {
        return valor;
    }
    
}
