
package beans;

/**
 *
 * @author diogo
 */
public enum UtilizadorTipoLista {

    /**
     *
     */
    LISTA_USER_ONLINE("Lista Utilizadores OnLine"),

    /**
     *
     */
    LISTA_USER_DENUNCIADOS("Lista Utilizadores Denunciados"),

    /**
     *
     */
    LISTA_USER_ATIVOS("Lista Utilizadores Ativos"),

    /**
     *
     */
    LISTA_USER_ALL("Lista Todos o Utilizadores"),

    /**
     *
     */
    LISTA_USER_ADESOES("Lista Adesoes"),

    /**
     *
     */
    LISTA_USER_REARIVAR("Lista Reativar"),

    /**
     *
     */
    LISTA_USER_SUPENDER("Lista Suspender");
    
    private String msg;
    UtilizadorTipoLista(String s) { msg = s;};

    /**
     *
     * @return
     */
    public String msg() {return msg;}
}
