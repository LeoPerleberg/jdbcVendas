package model;

public class Cliente {
	private Long id_cliente;
	private String nome;
	private String sobrenome;
	private boolean situacao;
	/**
	 * @return the id_cliente
	 */
	public long getId_cliente() {
		return id_cliente;
	}
	/**
	 * @param id_cliente the id_cliente to set
	 */
	public void setId_cliente(long id_cliente) {
		this.id_cliente = id_cliente;
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
	 * @return the sobrenome
	 */
	public String getSobrenome() {
		return sobrenome;
	}
	/**
	 * @param sobrenome the sobrenome to set
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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
	@Override
	public String toString() {
		return "\nCliente [id_cliente=" + id_cliente + ", nome=" + nome + ", sobrenome="
				+ sobrenome + ", situacao=" + situacao + "]";
	}

}
