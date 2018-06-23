package controler;

import java.sql.Date;
import java.sql.SQLException;
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
    public static void main( String[] args ) throws SQLException {
    	ProdutoDAO produtoDAO = new ProdutoDAO();

    	imprimirListaProd(produtoDAO);
    	System.out.println("\n");

/*
    	
 		// insert
    	Produto produto = new Produto();
    	produto.setNome("Lapizeira");
    	produto.setValor((float) 3.25);
    	produto.setDescricao("Lapizeira Faber Castel grafite 0.5");
    	produto.setSituacao(true);
    	
    	try {
			if (produtoDAO.insert(produto)) {
				System.out.println("\nProduto salvo.\n");
			} else {
				System.out.println("\nErro ao tentar salvar o produto.\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	imprimirListaProd(produtoDAO);
    	
    	// update
    	produto = new Produto();
    	produto.setId_produto(17);
    	produto.setNome("Lapizeira");
    	produto.setValor((float) 3.25);
    	produto.setDescricao("Lapizeira Faber Castel grafite 0.9");
    	produto.setSituacao(true);
    	
    	try {
			if (produtoDAO.update(produto)) {
				System.out.println("\nProduto atualizado.\n");
			} else {
				System.out.println("\nErro ao tentar atualizar o produto.\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	imprimirListaProd(produtoDAO);
    	
    	//delete
    	produto = new Produto();
    	produto.setId_produto(18L);
    	
    	try {
			if (produtoDAO.delete(produto)) {
				System.out.println("\nProduto excluido.\n");
			} else {
				System.out.println("\nErro ao tentar excluir o produto.\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	imprimirListaProd(produtoDAO);
    	*/
    	

    	ClienteDAO clienteDAO = new ClienteDAO();
    	imprimirListaClie(clienteDAO);
    	System.out.println("\n");
/*    	
 		// insert
    	Cliente cliente = new Cliente();
    	cliente.setNome("Leonardo");
    	cliente.setSobrenome("Konsgen");
    	cliente.setSituacao(true);
    	
    	try {
			if (clienteDAO.insert(cliente)) {
				System.out.println("Cliente inserido com sucesso");
			} else {
				System.out.println("Erro ao inserir o cliente");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	imprimirListaClie(clienteDAO);
    	
    	//update
    	cliente = new Cliente();
    	cliente.setNome("Leonardo");
    	cliente.setSobrenome("Perleberg");
    	cliente.setSituacao(false);
    	cliente.setId_cliente(8);
    	
    	try {
			if (clienteDAO.update(cliente)) {
				System.out.println("Cliente alterado com sucesso");
			} else {
				System.out.println("Erro ao alterar o cliente");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	imprimirListaClie(clienteDAO);
    	*/

		
		
    	
    	PedidoDAO pedidoDAO = new PedidoDAO();
		imprimirListaPedi(pedidoDAO);
    	System.out.println("\n");
    	
    	// insert
    	Date data = new Date(0);
    	data.getTime();
    	Pedido pedido = new Pedido();
    	ItemPedido item = new ItemPedido();
    	List<ItemPedido> itens = new ArrayList<ItemPedido>();
    	item.setProduto(produtoDAO.getById((long) 2));
    	item.setQuantidade(5);
    	item.setTotalItem(item.getProduto().getValor() * item.getQuantidade());
    	item.setSituacao(true);
    	itens.add(item);
    	item.setProduto(produtoDAO.getById((long) 19));
    	item.setQuantidade(9);
    	item.setTotalItem(item.getProduto().getValor() * item.getQuantidade());
    	item.setSituacao(true);
    	itens.add(item);
    	
    	pedido.setFormaPagamento("á vista");
    	pedido.setEstado("faturado");
    	pedido.setDataCriacao(data);
    	pedido.setDataModificacao(data);
    	pedido.setCliente(clienteDAO.getById((long) 7));
    	pedido.setTotalPedido(0);
    	pedido.setSituacao(true);
    	pedido.setItens(itens);
    	try {
			if (pedidoDAO.insert(pedido)) {
				System.out.println("\nPedido salvo.\n");
				for (int i = 0; i < itens.size(); i++) {
					ItemPedidoDAO itemDAO = new ItemPedidoDAO();
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
	
	/*
	Pedido ped - new Pedido(;);
	ItemPeduddo  item - new ItemPeido(;)
	item.setProduto(daoPrduto.getById(1))
	item.setQuantidade(1)
	item.setTotalItem(1*produto.getValor())
	ped.getItem(.).add(itm)
	ped.setCliente(cliDAO.getClienteById(1))
	PedidoDAO.insert(ped)
	*/
}
