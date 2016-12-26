
package menus;

public class OpcaoMenu {
    private String opcao;
    private Runnable funcao;

    public OpcaoMenu(String opcao, Runnable funcao) {
        this.opcao = opcao;
        this.funcao = funcao;
    }

    public Runnable getFuncao() {
        return funcao;
    }

    public String getOpcao() {
        return opcao;
    }
    
}
