/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author diogo
 */
@Entity
@Table(name = "t_utilizadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUtilizadores.findAll", query = "SELECT t FROM TUtilizadores t")
    , @NamedQuery(name = "TUtilizadores.findByNome", query = "SELECT t FROM TUtilizadores t WHERE t.nome = :nome")
    , @NamedQuery(name = "TUtilizadores.findByMorada", query = "SELECT t FROM TUtilizadores t WHERE t.morada = :morada")
    , @NamedQuery(name = "TUtilizadores.findByUsername", query = "SELECT t FROM TUtilizadores t WHERE t.username = :username")
    , @NamedQuery(name = "TUtilizadores.findByPassword", query = "SELECT t FROM TUtilizadores t WHERE t.password = :password")
    , @NamedQuery(name = "TUtilizadores.findBySaldo", query = "SELECT t FROM TUtilizadores t WHERE t.saldo = :saldo")
    , @NamedQuery(name = "TUtilizadores.findByEstado", query = "SELECT t FROM TUtilizadores t WHERE t.estado = :estado")
    , @NamedQuery(name = "TUtilizadores.findByRazaoPedidoSuspensao", query = "SELECT t FROM TUtilizadores t WHERE t.razaoPedidoSuspensao = :razaoPedidoSuspensao")
    , @NamedQuery(name = "TUtilizadores.findByLogged", query = "SELECT t FROM TUtilizadores t WHERE t.logged = :logged")})
public class TUtilizadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "morada")
    private String morada;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "password")
    private String password;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo")
    private Double saldo;
    @Size(max = 15)
    @Column(name = "estado")
    private String estado;
    @Size(max = 100)
    @Column(name = "razaoPedidoSuspensao")
    private String razaoPedidoSuspensao;
    @Column(name = "logged")
    private Boolean logged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "denunciador")
    private List<TDenunciasItens> tDenunciasItensList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licitador")
    private List<TLicitacoes> tLicitacoesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinatario")
    private List<TMensagens> tMensagensList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remetente")
    private List<TMensagens> tMensagensList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizador")
    private List<TitemsAVenda> titemsAVendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizador")
    private List<TitemsSeguidos> titemsSeguidosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "denunciador")
    private List<TDenunciasVendedores> tDenunciasVendedoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor")
    private List<TDenunciasVendedores> tDenunciasVendedoresList1;

    public TUtilizadores() {
    }

    public TUtilizadores(String username) {
        this.username = username;
    }

    public TUtilizadores(String username, String nome, String morada, String password) {
        this.username = username;
        this.nome = nome;
        this.morada = morada;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getEstado_() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRazaoPedidoSuspensao() {
        return razaoPedidoSuspensao;
    }

    public void setRazaoPedidoSuspensao(String razaoPedidoSuspensao) {
        this.razaoPedidoSuspensao = razaoPedidoSuspensao;
    }

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    @XmlTransient
    public List<TDenunciasItens> getTDenunciasItensList() {
        return tDenunciasItensList;
    }

    public void setTDenunciasItensList(List<TDenunciasItens> tDenunciasItensList) {
        this.tDenunciasItensList = tDenunciasItensList;
    }

    @XmlTransient
    public List<TLicitacoes> getTLicitacoesList() {
        return tLicitacoesList;
    }

    public void setTLicitacoesList(List<TLicitacoes> tLicitacoesList) {
        this.tLicitacoesList = tLicitacoesList;
    }

    @XmlTransient
    public List<TMensagens> getTMensagensList() {
        return tMensagensList;
    }

    public void setTMensagensList(List<TMensagens> tMensagensList) {
        this.tMensagensList = tMensagensList;
    }

    @XmlTransient
    public List<TMensagens> getTMensagensList1() {
        return tMensagensList1;
    }

    public void setTMensagensList1(List<TMensagens> tMensagensList1) {
        this.tMensagensList1 = tMensagensList1;
    }

    @XmlTransient
    public List<TitemsAVenda> getTitemsAVendaList() {
        return titemsAVendaList;
    }

    public void setTitemsAVendaList(List<TitemsAVenda> titemsAVendaList) {
        this.titemsAVendaList = titemsAVendaList;
    }

    @XmlTransient
    public List<TitemsSeguidos> getTitemsSeguidosList() {
        return titemsSeguidosList;
    }

    public void setTitemsSeguidosList(List<TitemsSeguidos> titemsSeguidosList) {
        this.titemsSeguidosList = titemsSeguidosList;
    }

    @XmlTransient
    public List<TDenunciasVendedores> getTDenunciasVendedoresList() {
        return tDenunciasVendedoresList;
    }

    public void setTDenunciasVendedoresList(List<TDenunciasVendedores> tDenunciasVendedoresList) {
        this.tDenunciasVendedoresList = tDenunciasVendedoresList;
    }

    @XmlTransient
    public List<TDenunciasVendedores> getTDenunciasVendedoresList1() {
        return tDenunciasVendedoresList1;
    }

    public void setTDenunciasVendedoresList1(List<TDenunciasVendedores> tDenunciasVendedoresList1) {
        this.tDenunciasVendedoresList1 = tDenunciasVendedoresList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUtilizadores)) {
            return false;
        }
        TUtilizadores other = (TUtilizadores) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades2.TUtilizadores[ username=" + username + " ]";
    }
    
}
