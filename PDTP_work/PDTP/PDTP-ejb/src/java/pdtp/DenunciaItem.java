
package pdtp;

import java.sql.Timestamp;
import pdtp.Item;

public class DenunciaItem extends Denuncia{
    private Item item;

    public DenunciaItem(Item item, Utilizador denunciador, String razao) {
        super(denunciador, razao);
        this.item = item;
    }
    
    @Override
    public Utilizador getDenunciador() {
        return denunciador;
    }

    @Override
    public Timestamp getData() {
        return data;
    }

    @Override
    public String getRazao() {
        return razao;
    }
    
}
