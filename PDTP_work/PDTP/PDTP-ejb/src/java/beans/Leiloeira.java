package beans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import pdtp.Leilao;
import pdtp.Utilizador;
import pdtp.UtilizadorEstado;

@Singleton
public class Leiloeira implements LeiloeiraLocal {

    HashMap<String, Utilizador> utilizadores = new HashMap<>();
    List<Leilao> leiloes = new ArrayList<>();
    List<String> categorias = new ArrayList<>();;
    List<Mensagem> mensagens = new ArrayList<>();
    
    public Leiloeira() {
        if( !utilizadores.containsKey("admin") )
            utilizadores.put("admin",
                    new Utilizador("Administrador", "Sistema", "admin", "admin",UtilizadorEstado.ATIVO));
    }

    
    @Override
    public HashMap<String, Utilizador> getUtilizadores() {
        return utilizadores;
    }

    @Override
    public boolean existeUtilizador(String username) {
        if (username == null) {
            return false;
        }
        Utilizador j = utilizadores.get(username);
        if (j==null) // sera que nao fica null?
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean registaUtilizador(String nome, String morada, String username, String password) {
        if (!existeUtilizador(username)) {
            utilizadores.put(username, 
                    new Utilizador(nome, morada, username, password,UtilizadorEstado.ATIVO_PEDIDO));
            return true;
        }
        return false;
    }

    @Override
    public boolean loginUtilizador(String username, String password) {
        Utilizador j = utilizadores.get(username);
        if (j != null) {
            // existe
            if (j.getPassword().equalsIgnoreCase(password)) {
                if (j.getEstado()==UtilizadorEstado.ATIVO || j.getEstado()==UtilizadorEstado.SUSPENDO_PEDIDO){
                    if (j.isLogged()) // esta logado -Z nao deixa repetir user
                    {
                        return false;
                    } else {
                        j.setLogged();
                        j.setLastAction();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean logOff(String username) {
        if (username == null) //quem?
        {
            return false;
        }
        Utilizador j = utilizadores.get(username);
        if (j == null) {
            return false; //nao conheco
        }
        if (!j.isLogged()) {
            return false;
        }
        j.resetLogged(); //unloga
        return true;
    }

    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void checkInactivity() throws InterruptedException {
        long now = LocalDateTime.now()
                .toInstant(ZoneOffset.UTC).getEpochSecond();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j : todos) {
            if (j.isLogged()) {
                if (j.fromLastActionFromNoew(now) > 240) { // 4 minutos
                    j.resetLogged();
                }
            }
        }
    }

    @PostConstruct
    public void loadstate() {
        try (ObjectInputStream ois
                = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream("/tmp/LeiloeiraDados")))) {
            utilizadores = (HashMap<String, Utilizador>) ois.readObject();
            mensagens = (ArrayList<Mensagem>) ois.readObject();
            categorias = (ArrayList<String>) ois.readObject();
        } catch (Exception e) {
            //Utilizadors = fica com o objecto vazio criado no construtor
        }
    }

    @PreDestroy
    public void saveState() {
        try (ObjectOutputStream oos
                = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream("/tmp/LeiloeiraDados")))) {
            oos.writeObject(utilizadores);
             oos.writeObject(mensagens);
            oos.writeObject(categorias);
        } catch (Exception e) {

        }
    }

    @Override
    public ArrayList getUsernameInscritos() {
        ArrayList inscritos = new ArrayList<>();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j : todos) {
            inscritos.add(j.getUsername());
        }
        return inscritos;
    }

    @Override
    public ArrayList getUsernamesOnline() {
        ArrayList logados = new ArrayList<>();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j : todos) {
            if (j.isLogged()) {
                logados.add(j.getUsername());
            }
        }
        return logados;
    }

    @Override
    public Double addSaldo(Double valor,String username) {
        if (utilizadores.get(username).isLogged())
            return utilizadores.get(username).addSaldo(valor);
        return null;
    }

    @Override
    public Double getSaldo(String username) {
        if (utilizadores.get(username).isLogged())
            return utilizadores.get(username).getSaldo();
        return null;
      
    }

    @Override
    public boolean ativaUtilizador(String username) {
        Utilizador u = utilizadores.get(username);
        if (u.getEstado()!=UtilizadorEstado.ATIVO){
            u.setEstado(UtilizadorEstado.ATIVO);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList getUtilizadoresEstado(UtilizadorEstado estado) {
        ArrayList users = new ArrayList<>();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j : todos) {
            if (j.getEstado()==estado) {
                users.add(j.getUsername());//.concat("-").concat(j.getEstado().msg()));
            }
        }
        return users;
    }

    @Override
    public String getDadosUtilizador(String username) {
        return utilizadores.get(username).getDados();
    }

    @Override
    public boolean atualizaDadosUtilizador(String username, String nome, String morada) {
        return utilizadores.get(username).aualizaDados(nome,morada);
    }

    @Override
    public boolean pedirSuspensaoUtilizador(String username,String razao) {
        utilizadores.get(username).setEstado(UtilizadorEstado.SUSPENDO_PEDIDO);
        utilizadores.get(username).setRazaoPedidoSuspensao(razao);
        return true;
    }

    @Override
    public HashMap<String,String> getPedidosSuspensao() {
        HashMap<String,String> pedidos = new HashMap<>();
       // ArrayList users = new ArrayList<>();
        Collection<Utilizador> todos = utilizadores.values();
        for (Utilizador j : todos) {
            if (j.getEstado()==UtilizadorEstado.SUSPENDO_PEDIDO) {
                pedidos.put(j.getUsername(), j.getRazaoPedidoSuspensao());
            }
        }
        return pedidos;
 
    }

    @Override
    public boolean suspendeUtilizador(String username) {
        utilizadores.get(username).setEstado(UtilizadorEstado.SUSPENSO);
        return true;
    }

    @Override
    public void setLastAction(String username) {
        utilizadores.get(username).setLastAction();
    }

    @Override
    public boolean addMensagem(String remetente, String destinatario, String texto,String assunto) {
        Mensagem msg = new Mensagem(remetente,destinatario,texto,assunto,MensagemEstado.ENVIADA);
        mensagens.add(msg);
        return true;
    }

    @Override
    public ArrayList<Mensagem> getMensagensUtilizador(String username) {
        ArrayList<Mensagem> myMsg = new ArrayList<>();
        for (Mensagem msg : mensagens){
            if (msg.getDestinatario().equals(username)){
                myMsg.add(msg);
            }
        }
        return myMsg;
    }

    @Override
    public boolean adicionarCategoria(String nomeCategoria) {
        if( categorias.indexOf(nomeCategoria) > 0 )
            return false;
        else
            categorias.add(nomeCategoria);
        return true;
    }

    @Override
    public List<String> obterCategorias() {
        return categorias;
    }

    @Override
    public boolean eliminaCategoria(String nomeCategoria) {
        if( categorias.indexOf(nomeCategoria) > 0 ){
            categorias.remove(nomeCategoria);
            return false;
            
        }
        else
            categorias.add(nomeCategoria);
        return false;
    }

    @Override
    public boolean pedirReativacaoUsername(String username,String password) {
        if (existeUtilizador(username)){
            Utilizador u = utilizadores.get(username);
            if (u.getPassword().equals(password)){
                if (u.getEstado()==UtilizadorEstado.SUSPENSO){
                    u.setEstado(UtilizadorEstado.REATIVACAO_PEDIDO);
                    return true;
                }
            }
        }           
        return false;
    }
    
    
    
}
