/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
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
@MappedSuperclass
@Table(name = "t_utilizadores")
@XmlRootElement
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
    private Collection<TDenunciasItens> tDenunciasItensCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licitador")
    private Collection<TLicitacoes> tLicitacoesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinatario")
    private Collection<TMensagens> tMensagensCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remetente")
    private Collection<TMensagens> tMensagensCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizador")
    private Collection<TitemsAVenda> titemsAVendaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizador")
    private Collection<TitemsSeguidos> titemsSeguidosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "denunciador")
    private Collection<TDenunciasVendedores> tDenunciasVendedoresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor")
    private Collection<TDenunciasVendedores> tDenunciasVendedoresCollection1;

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

    public String getEstado() {
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
    public Collection<TitemsAVenda> getTitemsAVendaCollection() {
        return titemsAVendaCollection;
    }

    public void setTitemsAVendaCollection(Collection<TitemsAVenda> titemsAVendaCollection) {
        this.titemsAVendaCollection = titemsAVendaCollection;
    }

    @XmlTransient
    public Collection<TitemsSeguidos> getTitemsSeguidosCollection() {
        return titemsSeguidosCollection;
    }

    public void setTitemsSeguidosCollection(Collection<TitemsSeguidos> titemsSeguidosCollection) {
        this.titemsSeguidosCollection = titemsSeguidosCollection;
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
        return "entidades.TUtilizadores[ username=" + username + " ]";
    }
    
}
