package controler;

import java.sql.SQLException;
import java.util.List;

import dao.ClienteDAO;
import dao.ItemPedidoDAO;
import dao.ProdutoDAO;
import model.Cliente;
import model.ItemPedido;
import model.Produto;


public class App {
    public static void main( String[] args ) {
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
    	
    	ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    	imprimirListaItem(itemPedidoDAO);
    	System.out.println("\n");
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

	private static void imprimirListaClie(ClienteDAO cliente) {
		List<Cliente> clientes;
		try {
			clientes = cliente.getAll();
			System.out.println(clientes);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void imprimirListaItem(ItemPedidoDAO itemPedidoDAO) {
		List<ItemPedido> itemPedidos;
		try {
			itemPedidos = itemPedidoDAO.getAll();
			System.out.println(itemPedidos);
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
	pedidoDAO.insert(ped)
	*/
}
