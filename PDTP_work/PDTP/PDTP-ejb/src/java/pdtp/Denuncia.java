
package pdtp;

import java.sql.Timestamp;
import java.util.Date;
import pdtp.Utilizador;

public abstract class Denuncia {
    protected Utilizador denunciador;
    protected Timestamp data;
    protected String razao;

    public Denuncia(Utilizador denunciador, String razao) {
        this.denunciador = denunciador;
        this.data = new Timestamp(new Date().getTime());
        this.razao = razao;
    }

    public abstract Utilizador getDenunciador() ;

    public abstract Timestamp getData() ;

    public abstract String getRazao() ;
    
    @Override
    public abstract String toString();
}
