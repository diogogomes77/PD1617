
package session;

import entity.Leilao;
import entity.Membro;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class Sistema implements SistemaLocal {


    private List<Membro> membros;
    private List<Leilao> leiloes;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Sistema() {
        this.membros = new ArrayList<>();
        this.leiloes = new ArrayList<>();
    }

    @Override
    public String addMembro(String nome, String morada, String username, String password) {
        if (isUnique(username)){
            this.membros.add(new Membro(nome,morada,username,password));
            return "Membro inscrito com sucesso!"; 
        }
        return "username repetido";
    }

    private boolean isUnique(String username) {
        for (Membro membro : this.membros) {
            if (username.equalsIgnoreCase(username))
                return false;
        } 
        return true;   
    }

  
    
}
