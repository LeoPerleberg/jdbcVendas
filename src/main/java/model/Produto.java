package model;

public class Produto {
	private long id_produto;
	private String nome;
	private float valor;
	private String descricao;
	private boolean situacao;
	/**
	 * @return the id_produto
	 */
	public long getId_produto() {
		return id_produto;
	}
	/**
	 * @param id_produto the id_produto to set
	 */
	public void setId_produto(long id_produto) {
		this.id_produto = id_produto;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the valor
	 */
	public float getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(float valor) {
		this.valor = valor;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the situacao
	 */
	public boolean getSituacao() {
		return situacao;
	}
	/**
	 * @param situacao the situacao to set
	 */
	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nProduto [id_produto=" + id_produto + ", nome=" + nome + ", valor=" + valor + ", descricao=" + descricao
				+ ", situacao=" + situacao + "]";
	}
	
}
