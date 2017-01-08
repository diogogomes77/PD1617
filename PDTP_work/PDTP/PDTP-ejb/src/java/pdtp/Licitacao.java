
package pdtp;

import java.io.Serializable;
import java.sql.Timestamp;

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
    
}
