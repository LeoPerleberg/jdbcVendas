package model;

public class ItemPedido {
	private Long id;
	Produto produto = new Produto();
	private int quantidade;
	private double totalItem;
	private String situacao;
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
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	/**
	 * @return the totalItem
	 */
	public double getTotalItem() {
		return totalItem;
	}
	/**
	 * @param totalItem the totalItem to set
	 */
	public void setTotalItem(double totalItem) {
		this.totalItem = totalItem;
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
		return "ItemPedido [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", totalItem="
				+ totalItem + ", situacao=" + situacao + ", getId()=" + getId() 
				+ ", getQuantidade()=" + getQuantidade() + ", getTotalItem()=" + getTotalItem() + ", getSituacao()="
				+ getSituacao() + "]";
	}
}
