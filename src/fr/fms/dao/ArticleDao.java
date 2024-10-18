package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class ArticleDao implements Dao<Article> {

	private Connection connection;

	public ArticleDao() {
		this.connection = BddConnection.getConnection();
	}
	
	// C
	@Override
	public void create(Article article) {
		String addArticleSQL = "INSERT INTO T_Articles (description, brand, unitaryPrice) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(addArticleSQL)) {
			preparedStatement.setString(1, article.getDescription());
			preparedStatement.setString(2, article.getBrand());
			preparedStatement.setInt(3, article.getUnitaryPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// R
	@Override
	public Article read(int idArticle) {
		String readArticleSQL = "SELECT * FROM T_Articles WHERE idArticle = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(readArticleSQL)) {
			preparedStatement.setInt(1, idArticle);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int rsIdUser = resultSet.getInt(1);
					String rsDescription = resultSet.getString(2);
					String rsMarque = resultSet.getString(3);
					int rsPrixUnitaire = resultSet.getInt(4);
					return new Article(rsIdUser, rsDescription, rsMarque, rsPrixUnitaire);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// U
	@Override
	public void update(String columnName, Object newValue, int idArticle) {
		String updateArticleSQL = "UPDATE T_Articles SET " + columnName + " = ? WHERE idArticle = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateArticleSQL)) {
			preparedStatement.setObject(1, newValue);
			preparedStatement.setInt(2, idArticle);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// D
	@Override
	public void delete(int idArticle) {
		String deleteArticleSQL = "DELETE FROM T_Articles WHERE idArticle = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteArticleSQL)) {
			preparedStatement.setInt(1, idArticle);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Article> findAll() {
	    ArrayList<Article> articles = new ArrayList<>();
	    String allArticleSql = "SELECT * FROM T_Articles";

	    try (Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery(allArticleSql)) {

	        while (resultSet.next()) {
	            int rsIdArticle = resultSet.getInt("idArticle");
	            String rsDescription = resultSet.getString("description");
	            String rsMarque = resultSet.getString("brand");
	            int rsPrixUnitaire = resultSet.getInt("unitaryPrice");
	            articles.add(new Article(rsIdArticle, rsDescription, rsMarque, rsPrixUnitaire));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return articles;
	}

}
