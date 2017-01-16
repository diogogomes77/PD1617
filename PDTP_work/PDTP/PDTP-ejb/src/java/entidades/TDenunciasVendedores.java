/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@MappedSuperclass
@Table(name = "t_denuncias_vendedores")
@XmlRootElement
public class TDenunciasVendedores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "razao")
    private String razao;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_denuncia_vendedor")
    private Integer idDenunciaVendedor;
    @JoinColumn(name = "denunciador", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizadores denunciador;
    @JoinColumn(name = "vendedor", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizadores vendedor;

    public TDenunciasVendedores() {
    }

    public TDenunciasVendedores(Integer idDenunciaVendedor) {
        this.idDenunciaVendedor = idDenunciaVendedor;
    }

    public TDenunciasVendedores(Integer idDenunciaVendedor, Date data, String razao) {
        this.idDenunciaVendedor = idDenunciaVendedor;
        this.data = data;
        this.razao = razao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public Integer getIdDenunciaVendedor() {
        return idDenunciaVendedor;
    }

    public void setIdDenunciaVendedor(Integer idDenunciaVendedor) {
        this.idDenunciaVendedor = idDenunciaVendedor;
    }

    public TUtilizadores getDenunciador() {
        return denunciador;
    }

    public void setDenunciador(TUtilizadores denunciador) {
        this.denunciador = denunciador;
    }

    public TUtilizadores getVendedor() {
        return vendedor;
    }

    public void setVendedor(TUtilizadores vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDenunciaVendedor != null ? idDenunciaVendedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDenunciasVendedores)) {
            return false;
        }
        TDenunciasVendedores other = (TDenunciasVendedores) object;
        if ((this.idDenunciaVendedor == null && other.idDenunciaVendedor != null) || (this.idDenunciaVendedor != null && !this.idDenunciaVendedor.equals(other.idDenunciaVendedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TDenunciasVendedores[ idDenunciaVendedor=" + idDenunciaVendedor + " ]";
    }
    
}
