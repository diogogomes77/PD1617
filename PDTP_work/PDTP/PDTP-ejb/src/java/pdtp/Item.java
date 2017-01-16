package pdtp;


import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author diogo
 */
public class Item implements Serializable {

    private TreeMap<Double, Licitacao> licitacoes;
    private Timestamp dataInicio;
    private Timestamp dataFim;
    private Utilizador vendedor;
    private String descricao;
    private Double precoInicial;
    private Double comprarJa;
    private String categoria;
    private ItemEstados estado;
    private Venda venda;
    private Utilizador comprador;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH");
    private int itemID;
    
    /**
     *
     * @param itemID
     * @param vendedor
     * @param precoInicial
     * @param comprarJa
     * @param dataLimite
     * @param descricao
     */
    public Item(int itemID, Utilizador vendedor, Double precoInicial, Double comprarJa, Timestamp dataLimite, String descricao) {
        this.vendedor = vendedor;
        this.descricao = descricao;
        this.precoInicial = precoInicial;
        this.comprarJa = comprarJa;
        this.licitacoes = new TreeMap<>();
        this.dataInicio = new Timestamp(new Date().getTime());
        this.dataFim = dataLimite;
        this.itemID = itemID;
        //this.dataFim = dataLimite;
        this.estado = ItemEstados.INICIADA;
       
    }

    /**
     *
     * @return
     */
    public SimpleDateFormat getSdf() {
        return sdf;
    }

    /**
     *
     * @return
     */
    public String getDataInicio() {
        return sdf.format(dataInicio);
    }

    /**
     *
     * @return
     */
    public String getDataFim() {
         return sdf.format(dataFim);
    }

    /**
     *
     * @return
     */
    public Timestamp getDataFimTimeStamp() {
         return dataFim;
    }

    /**
     *
     * @return
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     *
     * @param categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     *
     * @return
     */
    public ItemEstados getEstado() {
        return estado;
    }

    /**
     *
     * @return
     */
    public Venda getVenda() {
        return venda;
    }

    /**
     *
     * @return
     */
    public Utilizador getComprador() {
        return comprador;
    }

    /**
     *
     * @param comprador
     */
    public void setComprador(Utilizador comprador) {
        this.comprador = comprador;
    }

    /**
     *
     * @param licitador
     * @param valor
     * @return
     */
    public boolean addLicitacao(Utilizador licitador, Double valor) {
        if (!proprioVendedor(licitador)) {
            return false;
        }
        if (!licitacaoValorValido(valor)) {
            return false;
        }
        Licitacao lic = new Licitacao(this,licitador,valor);
        this.licitacoes.put(valor, lic);
        return true;
    }

    /**
     *
     */
    public void terminaItemSemLicitacoes(){
        this.estado = ItemEstados.TERMINADA;
    }

    /**
     *
     * @return
     */
    public boolean addVendaLicitacao(){
        if (this.venda!=null)
            return false;
        Timestamp now = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        if(dataFim.after(now)){
            return false;
        }
        Licitacao lic=licitacoes.lastEntry().getValue();
        Utilizador vencedor = lic.getLicitador();
        this.comprador = vencedor;
        this.comprador.addItemPorPagar(this);
        this.venda = new Venda(lic);
        this.estado = ItemEstados.TERMINADA;
        return true;
    }

    /**
     *
     * @param membro
     * @return
     */
    public boolean addVendacomprarJa(Utilizador membro) {
        if (!proprioVendedor(membro)) {
            return false;
        }
        this.comprador = membro;
        this.comprador.addItemPorPagar(this);   
        this.venda = new Venda(this,membro,this.comprarJa);
        this.estado = ItemEstados.TERMINADA;
        return true;
    }

    /**
     *
     * @param membro
     * @return
     */
    public boolean cancelarItem(Utilizador membro) {
        if (proprioVendedor(membro)) {
            this.estado = ItemEstados.CANCELADA;
            return true;
        }
        return false;
    }

    private boolean proprioVendedor(Utilizador licitador) {
        return licitador != vendedor;
    }

    private boolean licitacaoValorValido(Double valor) {
        return (valor <= licitacoes.lastKey());
    }

    /**
     *
     * @return
     */
    public Utilizador getVendedor() {
        return vendedor;
    }

    /**
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *
     * @return
     */
    public Double getComprarJa() {
        return comprarJa;
    }
    @Override
    public String toString(){
        StringBuilder item = new StringBuilder();
        item.append("ID: ");
        item.append(itemID);
        item.append("\nDescricao: ");
        item.append(descricao);
        item.append("\nData fim: ");
        item.append(this.getDataFim());
        item.append("\nVendedor: ");
        item.append(vendedor.getUsername());
        item.append("\n");
        item.append("\nComprar Ja: ");
        item.append(this.comprarJa);
        item.append("\n");
        item.append("\nLicitacoes: ");
        int licitacoesSize=this.licitacoes.size();
        item.append(licitacoesSize);
        item.append("\n");
        Double licAtual =0.00;
        if (licitacoesSize>0){
             
             licAtual = this.licitacoes.lastKey();
        }else {
            licAtual = this.precoInicial;
        }
        item.append("\nLicitacao Atual: ");
        item.append(Double.toString(licAtual));
        return item.toString();
    }

    /**
     *
     * @return
     */
    public int getItemID(){
        return itemID;
    }
    
    /**
     *
     * @return
     */
    public String toLineString(){
        StringBuilder item = new StringBuilder();
        item.append("ID: ");
         item.append(itemID);
        item.append(" Descricao: ");
        item.append(descricao);
        item.append(" Data fim: ");
        
        item.append(this.getDataFim());
        item.append(" Vendedor: ");
        item.append(vendedor.getUsername());
        item.append("\n");
        return item.toString();
    }

    /**
     *
     * @return
     */
    public TreeMap<Double, Licitacao> getLicitacoes() {
        return licitacoes;
    }
}
