package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDAO extends BaseDAO {
	public List<Produto> getAll() throws SQLException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConection();
			String sql = "SELECT * FROM produtos";
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			List<Produto> produtos = new ArrayList<Produto>();
			while (resultSet.next()) {
				produtos.add(resultSetToProduto(resultSet));
			}
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			prepareStatement.close();
			resultSet.close();
		}

	}

	public Produto getbyId(int i) throws SQLException {

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {

			connection = getConection();
			String sql = "select * from produtos where id_produto = ?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setLong(1, i);
			resultSet = prepareStatement.executeQuery();
			Produto produto = null;
			if (resultSet.next()) {

				produto = resultSetToProduto(resultSet);

			}
			return produto;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			prepareStatement.close();
			resultSet.close();
		}

	}

	public Produto getbyName(String i) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConection();
			String sql = "SELECT * FROM produtos WHERE nome = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, i);
			resultSet = preparedStatement.executeQuery();
			Produto produto = null;
			if (resultSet.next()) {
				produto = resultSetToProduto(resultSet);
			}
			return produto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}

	}
	
	public boolean insert(Produto produto) throws SQLException {
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;

		try {
			String sql = "INSERT INTO produtos(nome, valor, descricao, situacao)"
					+ "VALUES (?, ?, ?, ?);";
			conn = BaseDAO.getConection();
			prepareStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			produtoToPreparedStatment(produto, prepareStatement);
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
			if (rs != null) {
				rs.close();
			}
		}

	}
	
	public boolean update(Produto produto) throws SQLException {
		Connection conn = null;
		PreparedStatement prepareStatement = null;

		try {
			String sql = "UPDATE produtos SET id_produto=?, nome=?, valor=?, descricao=?, situacao=? WHERE id_produto=?";
			conn = BaseDAO.getConection();
			prepareStatement = conn.prepareStatement(sql);
			produtoToPreparedStatment(produto, prepareStatement);
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
	
	public boolean delete(Produto produto) throws SQLException {
		Connection conn = null;
		PreparedStatement prepareStatement = null;

		try {
			String sql = "DELETE FROM produtos WHERE id_produto=?";
			conn = BaseDAO.getConection();
			prepareStatement = conn.prepareStatement(sql);
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
	

	private void produtoToPreparedStatment(Produto produto, PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1, produto.getNome());
		preparedStatement.setFloat(2, produto.getValor());
		preparedStatement.setString(3, produto.getDescricao());
		preparedStatement.setBoolean(4, produto.getSituacao());
		preparedStatement.setLong(5, produto.getId_produto());
		
	}
	
	
	private Produto resultSetToProduto(ResultSet resultSet) throws SQLException {
		Produto produto = new Produto();
		produto.setId_produto(resultSet.getLong("id_produto"));
		produto.setNome(resultSet.getString("nome"));
		produto.setValor(resultSet.getFloat("valor"));
		produto.setDescricao(resultSet.getString("descricao"));
		return produto;
	}

}
