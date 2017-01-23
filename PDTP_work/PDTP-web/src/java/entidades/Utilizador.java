
package entidades;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author diogo
 */
@Entity
public class Utilizador implements Serializable {
    
    private String nome;
    private String morada;
    @Id
    private String username;
    private String password;
    private Double saldo;
//    private List<Item> itemsAVenda;
//    private List<Item> itemsSeguidos;
//    private List<Item> itemsPorPagar;
//    private List<Item> itemsJaPagos;
//    //private List<Item> leiloes;
//    private UtilizadorEstado estado;
    private String razaoPedidoSuspensao;
    private boolean logged;
    long lastAction;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
