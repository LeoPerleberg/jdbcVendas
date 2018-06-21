package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private Long id_pedido;
	private String formaPagamento; 
	private String estado;
	private Date dataCriacao;
	private Date dataModificacao;
	private double totalPedido;
	private boolean situacao;
	private Long id_cliente;

	Cliente cliente;
	List<ItemPedido> itens = new ArrayList<ItemPedido>();
	
	public Pedido() {
		itens.add(new ItemPedido());
	}

	public Long getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Long id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(double totalPedido) {
		this.totalPedido = totalPedido;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "\nPedido [id_pedido=" + id_pedido + ", formaPagamento=" + formaPagamento + ", estado=" + estado
				+ ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + ", totalPedido="
				+ totalPedido + ", situacao=" + situacao + ", id_cliente=" + id_cliente + ", cliente=" + cliente
				+ ", itens=" + itens + "]";
	}
	
}
