package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.ItemPedido;
import model.Pedido;

public class PedidoDAO extends BaseDAO {
	private static PedidoDAO pedidoDAO = null;
	
	private PedidoDAO() {}
	
	//Padrão de projeto Singleton
	public static PedidoDAO getInstance() {
		if (pedidoDAO == null) {
			pedidoDAO = new PedidoDAO();
			return pedidoDAO;
		}
		return pedidoDAO;
	}

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
	
	public boolean insert(Pedido pedido) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "INSERT INTO pedidos(pagamento, estado, data_criacao, data_modificacao, id_cliente, totalpedido, situacao) VALUES (?, ?, ?, ?, ?, ?, ?);";
			connection = BaseDAO.getConection();
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pedidoToPrepareStatement(pedido, preparedStatement);
			int cont = preparedStatement.executeUpdate();
			if (pedido.getId_pedido() == null) {
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					pedido.setId_pedido(resultSet.getLong("id_pedido"));
				}
			}
			return cont > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
	}
	
	public boolean update(Pedido pedido) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConection();
			String sql = "UPDATE pedidos "
					+ "SET pagamento=?, estado=?, data_criacao=?,  data_modificacao=?, id_cliente=?, totalpedido=?, situacao=? "
					+ "WHERE id_pedido=?;";
			preparedStatement = connection.prepareStatement(sql);
			pedidoToPrepareStatement(pedido, preparedStatement);
			int cont = preparedStatement.executeUpdate();
			return cont > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}
	
	public boolean delete(Pedido pedido) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConection();
			preparedStatement = connection.prepareStatement("UPDATE pedidos SET situacao=? WHERE id_pedido=?;");
			preparedStatement.setBoolean(1, false);
			preparedStatement.setLong(2, pedido.getId_pedido());
			int cont = preparedStatement.executeUpdate();
			return cont > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}

	private void pedidoToPrepareStatement(Pedido pedido, PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1, pedido.getFormaPagamento());
		preparedStatement.setString(2, pedido.getEstado());
		preparedStatement.setDate(3, pedido.getDataCriacao());
		preparedStatement.setDate(4, Date.valueOf(LocalDate.now()));
		preparedStatement.setLong(5, pedido.getId_cliente());
		preparedStatement.setDouble(6, pedido.getTotalPedido());
		preparedStatement.setBoolean(7, pedido.getSituacao());
		if (pedido.getId_pedido() != null) {
			preparedStatement.setLong(8, pedido.getId_pedido());
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
		ItemPedidoDAO itemPedidoDAO = ItemPedidoDAO.getInstance();
		List<ItemPedido> itens = itemPedidoDAO.getByIdPedido(pedido);
		pedido.setItens(itens);
		ClienteDAO clienteDAO = ClienteDAO.getInstance();
		Cliente cliente = clienteDAO.getById(pedido.getId_cliente());
		pedido.setCliente(cliente);
		return pedido;
	}
}
