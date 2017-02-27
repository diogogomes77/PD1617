
package menus;

/**
 *
 * @author diogo
 */
public class OpcaoMenu {
    private String opcao;
    private Runnable funcao;

    /**
     *
     * @param opcao
     * @param funcao
     */
    public OpcaoMenu(String opcao, Runnable funcao ) {
        this.opcao = opcao;
        this.funcao = funcao;
    }

    /**
     *
     * @return
     */
    public Runnable getFuncao() {
        return funcao;
    }

    /**
     *
     * @return
     */
    public String getOpcao() {
        return opcao;
    }
    
}
