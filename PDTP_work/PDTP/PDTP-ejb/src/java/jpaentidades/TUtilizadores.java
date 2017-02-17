/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaentidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import pdtp.UtilizadorEstado;

/**
 *
 * @author diogo
 */
@Entity
@Table(name = "t_utilizadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUtilizadores.findAll", query = "SELECT t FROM TUtilizadores t")
    , @NamedQuery(name = "TUtilizadores.findByUsername", query = "SELECT t FROM TUtilizadores t WHERE t.username = :username")
    , @NamedQuery(name = "TUtilizadores.findByEstado", query = "SELECT t FROM TUtilizadores t WHERE t.estado = :estado")
    , @NamedQuery(name = "TUtilizadores.findByLogged", query = "SELECT t FROM TUtilizadores t WHERE t.logged = :logged")
    , @NamedQuery(name = "TUtilizadores.findByMorada", query = "SELECT t FROM TUtilizadores t WHERE t.morada = :morada")
    , @NamedQuery(name = "TUtilizadores.findByNome", query = "SELECT t FROM TUtilizadores t WHERE t.nome = :nome")
    , @NamedQuery(name = "TUtilizadores.findByPassword", query = "SELECT t FROM TUtilizadores t WHERE t.password = :password")
    , @NamedQuery(name = "TUtilizadores.findByRazaopedidosuspensao", query = "SELECT t FROM TUtilizadores t WHERE t.razaopedidosuspensao = :razaopedidosuspensao")
    , @NamedQuery(name = "TUtilizadores.findBySaldo", query = "SELECT t FROM TUtilizadores t WHERE t.saldo = :saldo")})
public class TUtilizadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "estado")
    private String estado;
    @Column(name = "logged")
    private Boolean logged;
    @Size(max = 255)
    @Column(name = "morada")
    private String morada;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "razaopedidosuspensao")
    private String razaopedidosuspensao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo")
    private Double saldo;
    @OneToMany(mappedBy = "denunciador")
    private Collection<TDenunciasItens> tDenunciasItensCollection;
    @OneToMany(mappedBy = "licitador")
    private Collection<TLicitacoes> tLicitacoesCollection;
    @OneToMany(mappedBy = "destinatario")
    private Collection<TMensagens> tMensagensCollection;
    @OneToMany(mappedBy = "remetente")
    private Collection<TMensagens> tMensagensCollection1;
    @OneToMany(mappedBy = "comprador")
    private Collection<TVendas> tVendasCollection;
    @OneToMany(mappedBy = "denunciador")
    private Collection<TDenunciasVendedores> tDenunciasVendedoresCollection;
    @OneToMany(mappedBy = "vendedor")
    private Collection<TDenunciasVendedores> tDenunciasVendedoresCollection1;
    @OneToMany(mappedBy = "vendedor")
    private Collection<TItens> tItensCollection;

    public TUtilizadores() {
    }

    public TUtilizadores(String username) {
        this.username = username;
    }

    public TUtilizadores(String nome, String morada, String username, String password, Double saldo, boolean logged, UtilizadorEstado estado) {
        this.username = username;
        this.estado = estado.msg();
        this.logged = logged;
        this.morada = morada;
        this.nome = nome;
        this.password = password;
        this.saldo = saldo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRazaopedidosuspensao() {
        return razaopedidosuspensao;
    }

    public void setRazaopedidosuspensao(String razaopedidosuspensao) {
        this.razaopedidosuspensao = razaopedidosuspensao;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @XmlTransient
    public Collection<TDenunciasItens> getTDenunciasItensCollection() {
        return tDenunciasItensCollection;
    }

    public void setTDenunciasItensCollection(Collection<TDenunciasItens> tDenunciasItensCollection) {
        this.tDenunciasItensCollection = tDenunciasItensCollection;
    }

    @XmlTransient
    public Collection<TLicitacoes> getTLicitacoesCollection() {
        return tLicitacoesCollection;
    }

    public void setTLicitacoesCollection(Collection<TLicitacoes> tLicitacoesCollection) {
        this.tLicitacoesCollection = tLicitacoesCollection;
    }

    @XmlTransient
    public Collection<TMensagens> getTMensagensCollection() {
        return tMensagensCollection;
    }

    public void setTMensagensCollection(Collection<TMensagens> tMensagensCollection) {
        this.tMensagensCollection = tMensagensCollection;
    }

    @XmlTransient
    public Collection<TMensagens> getTMensagensCollection1() {
        return tMensagensCollection1;
    }

    public void setTMensagensCollection1(Collection<TMensagens> tMensagensCollection1) {
        this.tMensagensCollection1 = tMensagensCollection1;
    }

    @XmlTransient
    public Collection<TVendas> getTVendasCollection() {
        return tVendasCollection;
    }

    public void setTVendasCollection(Collection<TVendas> tVendasCollection) {
        this.tVendasCollection = tVendasCollection;
    }

    @XmlTransient
    public Collection<TDenunciasVendedores> getTDenunciasVendedoresCollection() {
        return tDenunciasVendedoresCollection;
    }

    public void setTDenunciasVendedoresCollection(Collection<TDenunciasVendedores> tDenunciasVendedoresCollection) {
        this.tDenunciasVendedoresCollection = tDenunciasVendedoresCollection;
    }

    @XmlTransient
    public Collection<TDenunciasVendedores> getTDenunciasVendedoresCollection1() {
        return tDenunciasVendedoresCollection1;
    }

    public void setTDenunciasVendedoresCollection1(Collection<TDenunciasVendedores> tDenunciasVendedoresCollection1) {
        this.tDenunciasVendedoresCollection1 = tDenunciasVendedoresCollection1;
    }

    @XmlTransient
    public Collection<TItens> getTItensCollection() {
        return tItensCollection;
    }

    public void setTItensCollection(Collection<TItens> tItensCollection) {
        this.tItensCollection = tItensCollection;
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
        return "jpaentidades.TUtilizadores[ username=" + username + " ]";
    }
    
}
