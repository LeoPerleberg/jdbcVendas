package model;

public class ItemPedido {
	private Long id_itemPedido;
	private Long id_produto;
	private Long id_pedido;
	private int quantidade;
	private double totalItem;
	private boolean situacao;
	

	Produto produto = new Produto();


	public Long getId_itemPedido() {
		return id_itemPedido;
	}


	public void setId_itemPedido(Long id_itemPedido) {
		this.id_itemPedido = id_itemPedido;
	}


	public Long getId_produto() {
		return id_produto;
	}


	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}


	public Long getId_pedido() {
		return id_pedido;
	}


	public void setId_pedido(Long id_pedido) {
		this.id_pedido = id_pedido;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public double getTotalItem() {
		return totalItem;
	}


	public void setTotalItem(double totalItem) {
		this.totalItem = totalItem;
	}


	public boolean getSituacao() {
		return situacao;
	}


	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	@Override
	public String toString() {
		return "\nItemPedido [id_itemPedido=" + id_itemPedido + ", id_produto=" + id_produto + ", id_pedido=" + id_pedido
				+ ", quantidade=" + quantidade + ", totalItem=" + totalItem + ", situacao=" + situacao + ", produto="
				+ produto + "]";
	}
	

}
