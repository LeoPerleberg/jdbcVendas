package controler;

import java.sql.SQLException;
import java.util.List;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import model.Cliente;
import model.Produto;


public class App {
    public static void main( String[] args ) {
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	ClienteDAO clienteDAO = new ClienteDAO();

    	imprimirListaProd(produtoDAO);
    	System.out.println("\n");
    	imprimirListaClie(clienteDAO);
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
}
