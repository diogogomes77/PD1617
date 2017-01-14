
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
        @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("Vendedor: ");
        result.append(vendedor.getUsername());
        result.append(" Denuncia: ");
        result.append(razao);
         result.append(" Por: ");
          result.append(denunciador.getUsername());
        return result.toString();
    }
}
