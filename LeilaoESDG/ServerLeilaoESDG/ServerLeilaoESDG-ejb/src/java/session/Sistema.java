
package session;

import entity.Leilao;
import entity.Membro;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@Singleton
@LocalBean
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
            if (username.equalsIgnoreCase(membro.getUsername()))
                return false;
        } 
        return true;   
    }

    public boolean loginMembro(String username, String password) {
        for (Membro membro : this.membros) {
            if (username.equalsIgnoreCase(membro.getUsername()) && password.equalsIgnoreCase(membro.getPassword()))
                return true;
                //return "Bemvindo ".concat(membro.getNome());
        }
        return false;
        //return ("username ou password desconhecida");
    }

    public Membro getMembro(String username){
       for (Membro membro : this.membros) {
            if (username.equalsIgnoreCase(membro.getUsername()))
                return membro;
        } 
        return null;  
    }
  
    
}
