package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDAO extends BaseDAO {
	public List<Cliente> getAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConection();
			preparedStatement = connection.prepareStatement("SELECT * FROM clientes");
			resultSet = preparedStatement.executeQuery();
			List<Cliente> clientes = new ArrayList<Cliente>();
			while (resultSet.next()) {
				clientes.add(resultSetToCliente(resultSet));
			}
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}

	}

	public Cliente getById(Long i) throws SQLException {

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {

			connection = getConection();
			prepareStatement = connection.prepareStatement("select * from clientes where id_cliente = ?");
			prepareStatement.setLong(1, i);
			resultSet = prepareStatement.executeQuery();
			Cliente cliente = null;
			if (resultSet.next()) {

				cliente = resultSetToCliente(resultSet);

			}
			return cliente;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			prepareStatement.close();
			resultSet.close();
		}

	}

	public List<Cliente> getByName(String i) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConection();
			preparedStatement = connection.prepareStatement("SELECT * FROM clientes WHERE nome = ?");
			preparedStatement.setString(1, i);
			resultSet = preparedStatement.executeQuery();
			List<Cliente> clientes = new ArrayList<Cliente>();
			while (resultSet.next()) {
				clientes.add(resultSetToCliente(resultSet));
			}
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}

	}
	
	public boolean insert(Cliente cliente) throws SQLException {
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;

		try {
			String sql = "INSERT INTO clientes (nome, sobrenome, situacao) VALUES (?, ?, ?);";
			conn = BaseDAO.getConection();
			prepareStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			clienteToPreparedStatment(cliente, prepareStatement);
			int cont = prepareStatement.executeUpdate();
			if (cliente.getId_cliente() == 0L) {
				rs = prepareStatement.getGeneratedKeys();
				if (rs.next()) {
					cliente.setId_cliente(rs.getLong("id_cliente"));
				}
			}
			return cont > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (prepareStatement != null) {
				prepareStatement.close();
			}
			if (rs != null) {
				rs.close();
			}
		}

	}
	


	public boolean update(Cliente cliente) throws SQLException {
		Connection conn = null;
		PreparedStatement prepareStatement = null;

		try {
			String sql = "UPDATE clientes SET nome=?, sobrenome=?, situacao=? WHERE id_cliente=?;";
			conn = BaseDAO.getConection();
			prepareStatement = conn.prepareStatement(sql);
			clienteToPreparedStatment(cliente, prepareStatement);
			int cont = prepareStatement.executeUpdate();
			return cont > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (prepareStatement != null) {
				prepareStatement.close();
			}
		}

	}	
	
	public boolean delete(Cliente cliente) throws SQLException {
		Connection conn = null;
		PreparedStatement prepareStatement = null;

		try {
			conn = BaseDAO.getConection();
			prepareStatement = conn.prepareStatement("UPDATE clientes SET situacao=? WHERE id_cliente=?;");
			prepareStatement.setBoolean(1, false);
			prepareStatement.setLong(2, cliente.getId_cliente());
			int cont = prepareStatement.executeUpdate();
			return cont > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (prepareStatement != null) {
				prepareStatement.close();
			}
		}

	}	

	private void clienteToPreparedStatment(Cliente cliente, PreparedStatement prepareStatement) throws SQLException {
		prepareStatement.setString(1, cliente.getNome());
		prepareStatement.setString(2, cliente.getSobrenome());
		prepareStatement.setBoolean(3, cliente.getSituacao());
		if (cliente.getId_cliente() != 0L) {
			prepareStatement.setLong(4, cliente.getId_cliente());
		}
		
	}
	
	private Cliente resultSetToCliente(ResultSet resultSet) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId_cliente(resultSet.getLong("id_cliente"));
		cliente.setNome(resultSet.getString("nome"));
		cliente.setSobrenome(resultSet.getString("sobrenome"));
		cliente.setSituacao(resultSet.getBoolean("situacao"));
		return cliente;
	}

}
