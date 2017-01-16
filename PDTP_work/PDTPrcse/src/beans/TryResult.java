
package beans;

/**
 *
 * @author diogo
 */
public enum TryResult {

    /**
     *
     */
    TooBig("Demasiado grande"),

    /**
     *
     */
    TooSmall("Demasiado pequeno"),

    /**
     *
     */
    Right("Acertaste"),

    /**
     *
     */
    NewNumber("Novo numero"),

    /**
     *
     */
    NoName("Falta fornecer o nome"),

    /**
     *
     */
    Error("Ocorreu um erro qualquer"),

    /**
     *
     */
    NotLogged("Nao estas logado (timeout?)");
    private String msg;
    TryResult(String s) { msg = s;};

    /**
     *
     * @return
     */
    public String msg() {return msg;}
    
    
}
