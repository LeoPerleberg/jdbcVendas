package controler;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.ClienteDAO;
import dao.ItemPedidoDAO;
import dao.ProdutoDAO;
import dao.PedidoDAO;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

public class App {
	public static void main(String[] args) throws SQLException {
		ProdutoDAO produtoDAO = ProdutoDAO.getInstance();

		imprimirListaProd(produtoDAO);
		System.out.println("\n");

		// insert
		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setValor((float) 1.25);
		produto.setDescricao("Caneta bic preta");
		produto.setSituacao(true);

		try {
			if (produtoDAO.insert(produto)) {
				System.out.println("\nProduto salvo.\n");
			} else {
				System.out.println("\nErro ao tentar salvar o produto.\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		imprimirListaProd(produtoDAO);

		// update
		produto = new Produto();
		produto = produtoDAO.getById((long) 23);
		produto.setValor((float) 18.50);
		produto.setSituacao(true);

		try {
			if (produtoDAO.update(produto)) {
				System.out.println("\nProduto atualizado.\n");
			} else {
				System.out.println("\nErro ao tentar atualizar o produto.\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		imprimirListaProd(produtoDAO);

		// delete
		produto = new Produto();
		produto = produtoDAO.getById((long) 17);
		try {
			if (produtoDAO.delete(produto)) {
				System.out.println("\nProduto excluido.\n");
			} else {
				System.out.println("\nErro ao tentar excluir o produto.\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		imprimirListaProd(produtoDAO);
		

		ClienteDAO clienteDAO = ClienteDAO.getInstance();
		imprimirListaClie(clienteDAO);
		System.out.println("\n");

		// insert
		Cliente cliente = new Cliente();
		cliente.setNome("Fernando");
		cliente.setSobrenome("Perleberg");
		cliente.setSituacao(true);
		try {
			if (clienteDAO.insert(cliente)) {
				System.out.println("Cliente inserido com sucesso");
			} else {
				System.out.println("Erro ao inserir o cliente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		imprimirListaClie(clienteDAO);

		// update
		cliente = new Cliente();
		cliente = clienteDAO.getById((long) 8);
		cliente.setNome("Leonardo");
		cliente.setSobrenome("Könsgen Perleberg");
		cliente.setSituacao(true);

		try {
			if (clienteDAO.update(cliente)) {
				System.out.println("Cliente alterado com sucesso");
			} else {
				System.out.println("Erro ao alterar o cliente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		imprimirListaClie(clienteDAO);

		// delete
		cliente = new Cliente();
		cliente = clienteDAO.getById((long) 11);
		try {
			if (clienteDAO.delete(cliente)) {
				System.out.println("\nCliente excluido.\n");
			} else {
				System.out.println("\nErro ao tentar excluir o cliente.\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		imprimirListaClie(clienteDAO);

		// insert
		PedidoDAO pedidoDAO = PedidoDAO.getInstance();
		imprimirListaPedi(pedidoDAO);
		System.out.println("\n");

		double vPedido = 0;
		Date data = Date.valueOf(LocalDate.now());
		Pedido pedido = new Pedido();
		ItemPedido item = new ItemPedido();
		ItemPedido item2 = new ItemPedido();
		List<ItemPedido> itens = new ArrayList<ItemPedido>();
		item.setProduto(produtoDAO.getById((long) 23));
		item.setQuantidade(5);
		item.setTotalItem(item.getProduto().getValor() * item.getQuantidade());
		vPedido = vPedido + item.getTotalItem();
		item.setSituacao(true);
		itens.add(item);
		item2.setProduto(produtoDAO.getById((long) 24));
		item2.setQuantidade(9);
		item2.setTotalItem(item2.getProduto().getValor() * item2.getQuantidade());
		vPedido = vPedido + item2.getTotalItem();
		item.setSituacao(true);
		itens.add(item2);

		pedido.setFormaPagamento("á vista");
		pedido.setEstado("faturado");
		pedido.setDataCriacao(data);
		pedido.setCliente(clienteDAO.getById((long) 8));
		pedido.setTotalPedido(vPedido);
		pedido.setSituacao(true);
		pedido.setItens(itens);
		try {
			if (pedidoDAO.insert(pedido)) {
				System.out.println("\nPedido salvo.\n");
				for (int i = 0; i < itens.size(); i++) {
					ItemPedidoDAO itemDAO = ItemPedidoDAO.getInstance();
					item = itens.get(i);
					item.setId_pedido(pedido.getId_pedido());
					try {
						itemDAO.insert(item);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("\nErro ao tentar salvar o pedido.\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		imprimirListaPedi(pedidoDAO);
		System.out.println("\n");

		// update

		pedido = new Pedido();
		pedido = pedidoDAO.getById(2); // pedido a ser atualizado
		vPedido = pedido.getTotalPedido();
		item = new ItemPedido();
		itens = new ArrayList<ItemPedido>();
		item.setProduto(produtoDAO.getById((long) 23));
		item.setQuantidade(9);
		item.setTotalItem(item.getProduto().getValor() * item.getQuantidade());
		vPedido = vPedido + item.getTotalItem();
		item.setSituacao(true);
		itens.add(item);

		pedido.setFormaPagamento("á praso");
		pedido.setEstado("faturado");
		pedido.setCliente(clienteDAO.getById((long) 7));
		pedido.setTotalPedido(vPedido);
		pedido.setSituacao(true);
		pedido.setItens(itens);
		try {
			if (pedidoDAO.update(pedido)) {
				System.out.println("\nPedido salvo.\n");
				for (int i = 0; i < itens.size(); i++) {
					ItemPedidoDAO itemDAO = ItemPedidoDAO.getInstance();
					item = itens.get(i);
					item.setId_pedido(pedido.getId_pedido());
					try {
						itemDAO.insert(item);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("\nErro ao tentar salvar o pedido.\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		imprimirListaPedi(pedidoDAO);
		System.out.println("\n");

		// delete
		data = new Date(0);
		data.getTime();
		pedido = new Pedido();
		pedido = pedidoDAO.getById(2); // pedido a ser removido
		pedido.setDataModificacao(data);
		pedido.setSituacao(false);
		try {
			pedidoDAO.update(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void imprimirListaPedi(PedidoDAO pedidoDAO) {
		List<Pedido> pedidos;
		try {
			pedidos = pedidoDAO.getAll();
			System.out.println(pedidos);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void imprimirListaProd(ProdutoDAO produtoDAO) {
		List<Produto> produtos;
		try {
			produtos = produtoDAO.getAll();
			System.out.println(produtos);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void imprimirListaClie(ClienteDAO clienteDAO) {
		List<Cliente> clientes;
		try {
			clientes = clienteDAO.getAll();
			System.out.println(clientes);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
