
package pdtp;

import java.sql.Timestamp;
import pdtp.Item;


/**
 *
 * @author diogo
 */
public class DenunciaItem extends Denuncia{
    private Item item;

    /**
     *
     * @param item
     * @param denunciador
     * @param razao
     */
    public DenunciaItem(Item item, Utilizador denunciador, String razao) {
        super(denunciador, razao);
        this.item = item;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Utilizador getDenunciador() {
        return denunciador;
    }

    /**
     *
     * @return
     */
    @Override
    public Timestamp getData() {
        return data;
    }

    /**
     *
     * @return
     */
    @Override
    public String getRazao() {
        return razao;
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("Item: ");
        result.append(item.getItemID());
        result.append(" Denuncia: ");
        result.append(razao);
         result.append(" Por: ");
          result.append(denunciador.getUsername());
        return result.toString();
    }
}
