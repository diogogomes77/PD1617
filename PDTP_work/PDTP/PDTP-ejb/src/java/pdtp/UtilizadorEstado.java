
package pdtp;

/**
 *
 * @author diogo
 */
public enum UtilizadorEstado {

    /**
     *
     */
    ATIVO_PEDIDO("Ativacao pedida"),

    /**
     *
     */
    ATIVO("Ativo"),

    /**
     *
     */
    SUSPENDO_PEDIDO("Suspensao pedida"),

    /**
     *
     */
    SUSPENSO("Suspenso"),

    /**
     *
     */
    REATIVACAO_PEDIDO("Reativacao pedida");
    
     private String msg;
    UtilizadorEstado(String s) { msg = s;};

    /**
     *
     * @return
     */
    public String msg() {return msg;}
}
