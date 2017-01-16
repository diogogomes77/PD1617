
package pdtp;

import java.sql.Timestamp;
import java.util.Date;
import pdtp.Utilizador;

/**
 *
 * @author diogo
 */
public abstract class Denuncia {

    /**
     *
     */
    protected Utilizador denunciador;

    /**
     *
     */
    protected Timestamp data;

    /**
     *
     */
    protected String razao;

    /**
     *
     * @param denunciador
     * @param razao
     */
    public Denuncia(Utilizador denunciador, String razao) {
        this.denunciador = denunciador;
        this.data = new Timestamp(new Date().getTime());
        this.razao = razao;
    }

    /**
     *
     * @return
     */
    public abstract Utilizador getDenunciador();

    /**
     *
     * @return
     */
    public abstract Timestamp getData();

    /**
     *
     * @return
     */
    public abstract String getRazao();
    
    @Override
    public abstract String toString();
}
