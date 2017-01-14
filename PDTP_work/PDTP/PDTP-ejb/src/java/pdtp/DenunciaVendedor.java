
package pdtp;

import java.sql.Timestamp;

public class DenunciaVendedor extends Denuncia  {
    private Utilizador vendedor;

    public DenunciaVendedor(Utilizador vendedor, Utilizador denunciador, String razao) {
        super(denunciador, razao);
        this.vendedor = vendedor;
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

    public Utilizador getVendedor() {
        return vendedor;
    }
    
}
