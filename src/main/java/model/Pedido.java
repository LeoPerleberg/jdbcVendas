package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private Long id;
	Cliente cliente;
	List<ItemPedido> itens = new ArrayList<ItemPedido>();
	private String formaPagamento; 
	private String estado;
	private Date dataCriacao;
	private Date dataModificacao;
	private double totalPedido;
	private String situacao;
	
	
	public Pedido() {
		itens.add(new ItemPedido());
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}


	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	/**
	 * @return the itens
	 */
	public List<ItemPedido> getItens() {
		return itens;
	}


	/**
	 * @param itens the itens to set
	 */
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}


	/**
	 * @return the formaPagamento
	 */
	public String getFormaPagamento() {
		return formaPagamento;
	}


	/**
	 * @param formaPagamento the formaPagamento to set
	 */
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}


	/**
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}


	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	/**
	 * @return the dataModificacao
	 */
	public Date getDataModificacao() {
		return dataModificacao;
	}


	/**
	 * @param dataModificacao the dataModificacao to set
	 */
	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}


	/**
	 * @return the totalPedido
	 */
	public double getTotalPedido() {
		return totalPedido;
	}


	/**
	 * @param totalPedido the totalPedido to set
	 */
	public void setTotalPedido(double totalPedido) {
		this.totalPedido = totalPedido;
	}


	/**
	 * @return the situacao
	 */
	public String getSituacao() {
		return situacao;
	}


	/**
	 * @param situacao the situacao to set
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", cliente=" + cliente + ", itens=" + itens + ", formaPagamento=" + formaPagamento
				+ ", estado=" + estado + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao
				+ ", totalPedido=" + totalPedido + ", situacao=" + situacao + ", getId()=" + getId() + ", getCliente()="
				+ getCliente() + ", getItens()=" + getItens() + ", getFormaPagamento()=" + getFormaPagamento()
				+ ", getEstado()=" + getEstado() + ", getDataCriacao()=" + getDataCriacao() + ", getDataModificacao()="
				+ getDataModificacao() + ", getTotalPedido()=" + getTotalPedido() + ", getSituacao()=" + getSituacao()
				+ "]";
	}
	
	
	
	
}
