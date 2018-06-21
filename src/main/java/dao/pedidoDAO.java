package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.ItemPedido;
import model.Pedido;

public class PedidoDAO extends BaseDAO {
	public List<Pedido> getAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConection();
			preparedStatement = connection.prepareStatement("SELECT * FROM pedidos;");
			resultSet = preparedStatement.executeQuery();
			List<Pedido> pedidos = new ArrayList<Pedido>();
			while (resultSet.next()) {
				pedidos.add(resultSetToPedido(resultSet));
			}
			return pedidos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}

	}

	public Pedido getById(int i) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConection();
			preparedStatement = connection.prepareStatement("SELECT * FROM pedidos WHERE id_pedido=?;");
			preparedStatement.setLong(1, i);
			resultSet = preparedStatement.executeQuery();
			Pedido pedido = new Pedido();
			if (resultSet.next()) {
				pedido = resultSetToPedido(resultSet);
			}
			return pedido;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}

	}

	public List<Pedido> getByCliente(int i) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConection();
			preparedStatement = connection.prepareStatement("SELECT * FROM pedidos WHERE id_cliente=?;");
			preparedStatement.setLong(1, i);
			resultSet = preparedStatement.executeQuery();
			List<Pedido> pedidos = new ArrayList<Pedido>();
			while (resultSet.next()) {
				pedidos.add(resultSetToPedido(resultSet));
			}
			return pedidos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}

	}
	
	

	private Pedido resultSetToPedido(ResultSet resultSet) throws SQLException {
		Pedido pedido = new Pedido();
		pedido.setId_pedido(resultSet.getLong("id_pedido"));
		pedido.setFormaPagamento(resultSet.getString("pagamento"));
		pedido.setEstado(resultSet.getString("estado"));
		pedido.setDataCriacao(resultSet.getDate("data_criacao"));
		pedido.setDataModificacao(resultSet.getDate("data_modificacao"));
		pedido.setId_cliente(resultSet.getLong("id_cliente"));
		pedido.setTotalPedido(resultSet.getDouble("totalpedido"));
		pedido.setSituacao(resultSet.getBoolean("situacao"));
		ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
		List<ItemPedido> itens = itemPedidoDAO.getByIdPedido(pedido.getId_pedido());
		pedido.setItens(itens);
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.getById(pedido.getId_cliente());
		pedido.setCliente(cliente);
		return pedido;
	}
}
