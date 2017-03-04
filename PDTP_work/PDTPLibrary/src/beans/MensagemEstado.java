package beans;

/**
 *
 * @author diogo
 */
public enum MensagemEstado {

    /**
     *
     */
    ENVIADA("Enviada"),
    /**
     *
     */
    ENTREGUE("Entregue"),
    /**
     *
     */
    LIDA("Lida");

    private String msg;

    MensagemEstado(String s) {
        msg = s;
    }

    /**
     *
     * @return
     */
    public String msg() {
        return msg;
    }

    @Override
    public String toString() {
        return msg;
    }
    
}
