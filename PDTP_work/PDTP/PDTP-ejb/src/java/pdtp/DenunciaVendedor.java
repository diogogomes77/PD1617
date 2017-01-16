
package pdtp;

import java.sql.Timestamp;

/**
 *
 * @author diogo
 */
public class DenunciaVendedor extends Denuncia  {
    private Utilizador vendedor;

    /**
     *
     * @param vendedor
     * @param denunciador
     * @param razao
     */
    public DenunciaVendedor(Utilizador vendedor, Utilizador denunciador, String razao) {
        super(denunciador, razao);
        this.vendedor = vendedor;
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

    /**
     *
     * @return
     */
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
