
package pdtp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Licitacao implements Serializable{
    private Item item;
    private Utilizador licitador;
    private Timestamp timestamp;
    private Double valor;

    public Licitacao(Item item, Utilizador licitador, Double valor) {
        this.item = item;
        this.licitador = licitador;
        this.valor = valor;
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Item getItem() {
        return item;
    }

    public Utilizador getLicitador() {
        return licitador;
    }

    public String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(timestamp);
       
    }

    public Double getValor() {
        return valor;
    }
    
}
