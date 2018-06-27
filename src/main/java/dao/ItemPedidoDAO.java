package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ItemPedido;
import model.Pedido;
import model.Produto;

public class ItemPedidoDAO extends BaseDAO {
	private Produto produto;

	public List<ItemPedido> getAll() throws SQLException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConection();
			prepareStatement = connection.prepareStatement("SELECT * FROM itempedido;");
			resultSet = prepareStatement.executeQuery();
			List<ItemPedido> itens = new ArrayList<ItemPedido>();
			while (resultSet.next()) {
				itens.add(resultSetToItem(resultSet));
			}
			return itens;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			prepareStatement.close();
			resultSet.close();
		}

	}

	public ItemPedido getById(ItemPedido item) throws SQLException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConection();
			prepareStatement = connection.prepareStatement("SELECT * FROM itempedido WHERE id_pedido = ? and id_produto = ?;");
			prepareStatement.setLong(1, item.getId_pedido());
			prepareStatement.setLong(2, item.getId_produto());
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				item = resultSetToItem(resultSet);
			} else {
				System.out.println("O item do pedido " + item.getId_pedido() + " e do produto " + item.getId_produto() + " não existe\n");
			}
			return item;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			prepareStatement.close();
			resultSet.close();
		}

	}
	
	public List<ItemPedido> getByIdPedido(Pedido pedido) throws SQLException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConection();
			prepareStatement = connection.prepareStatement("SELECT * FROM itempedido WHERE id_pedido = ?;");
			prepareStatement.setLong(1, pedido.getId_pedido());
			resultSet = prepareStatement.executeQuery();
			List<ItemPedido> itens = new ArrayList<ItemPedido>();
			while (resultSet.next()) {
				itens.add(resultSetToItem(resultSet));
			}
			return itens;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.close();
			prepareStatement.close();
			resultSet.close();
		}

	}

	public boolean insert(ItemPedido itemPedido) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			String sql = "INSERT INTO itempedido VALUES (?, ?, ?, ?, ?);";
			connection = BaseDAO.getConection();
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			itemToPreparedStatment(itemPedido, preparedStatement);
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
			if (resultSet != null) {
				resultSet.close();
			}
		}

	}
	
	public boolean update(ItemPedido itemPedido) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			String sql = "UPDATE itempedido SET id_produto=?, id_pedido=?, quantidade=?, situacao=?, totalItem=? WHERE id_itemPedido=?;";
			connection = BaseDAO.getConection();
			preparedStatement = connection.prepareStatement(sql);
			itemToPreparedStatment(itemPedido, preparedStatement);
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
	
	public boolean delete(ItemPedido itemPedido) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = BaseDAO.getConection();
			preparedStatement = connection.prepareStatement("UPDATE itempedido SET situacao=? WHERE id_itemPedido=?;");
			preparedStatement.setBoolean(1, false);
			preparedStatement.setLong(2, itemPedido.getId_itemPedido());
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

	private void itemToPreparedStatment(ItemPedido itemPedido, PreparedStatement preparedStatement)
			throws SQLException {
		preparedStatement.setLong(1, itemPedido.getId_produto());
		preparedStatement.setLong(2, itemPedido.getId_pedido());
		preparedStatement.setInt(3, itemPedido.getQuantidade());
		preparedStatement.setBoolean(4, itemPedido.getSituacao());
		preparedStatement.setDouble(5, itemPedido.getTotalItem());
		if (itemPedido.getId_itemPedido() != null) {
			preparedStatement.setLong(6, itemPedido.getId_itemPedido());
		}

	}

	private ItemPedido resultSetToItem(ResultSet resultSet) throws SQLException {
		ItemPedido item = new ItemPedido();
		item.setId_itemPedido(resultSet.getLong("id_itemPedido"));
		item.setQuantidade(resultSet.getInt("quantidade"));
		item.setTotalItem(resultSet.getFloat("totalItem"));
		item.setSituacao(resultSet.getBoolean("situacao"));
		item.setId_pedido(resultSet.getLong("id_pedido"));
		item.setId_produto(resultSet.getLong("id_produto"));
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produto = produtoDAO.getById(item.getId_produto());
		item.setProduto(produto);
		return item;
	}
}