
package beans;

/**
 *
 * @author diogo
 */
public enum ItensTipoLista {

    /**
     *
     */
    LISTA_ITENS_ALL("Lista de todos os Itens"),

    /**
     *
     */
    LISTA_ITENS_ULTIMOS_VENDIDOS("Lista dos Ultimos itens vendidos"),

    /**
     *
     */
    LISTA_ITENS_DENUCIADOS("Lista dos itens Denunciados"),

    /**
     *
     */
    LISTA_ITENS_SUPENDER("Lista Suspender");
    
    private String msg;
    ItensTipoLista(String s) { msg = s;};

    /**
     *
     * @return
     */
    public String msg() {return msg;}
}
