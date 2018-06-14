package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			String sql = "SELECT * FROM clientes";
			preparedStatement = connection.prepareStatement(sql);
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
	
	


	private Cliente resultSetToCliente(ResultSet resultSet) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId_cliente(resultSet.getLong("id_cliente"));
		cliente.setNome(resultSet.getString("nome"));
		cliente.setSobrenome(resultSet.getString("sobrenome"));
		cliente.setSituacao(resultSet.getBoolean("situacao"));
		return cliente;
	}

}
