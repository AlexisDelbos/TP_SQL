import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import fr.fms.entities.Article;

public class TestJdbc {

	public static void main(String[] args) throws Exception {
		ArrayList<Article> articles = new ArrayList<Article>();


		Properties prop = readPropertiesFile("C:\\Users\\DelbosA\\Workspace\\JavaSE-workspace\\ExBdd\\src\\credentials.propreties");

		CreateConfigFile file = new CreateConfigFile(prop.getProperty("db.driver.class"), prop.getProperty("db.url"),prop.getProperty("db.username"),prop.getProperty("db.password"));

		try (Connection connection = DriverManager.getConnection(file.getUrl(),file.getUsername(),file.getPassword())) {
			String strSql = "SELECT * FROM T_Articles";
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(strSql)) {
					while (resultSet.next()) {
						int rsIdUser = resultSet.getInt(1);
						String rsDescription = resultSet.getString(2);
						String rsMarque = resultSet.getString(3);
						int rsPrixUnitaire = resultSet.getInt(4);
						articles.add(new Article(rsIdUser, rsDescription, rsMarque, rsPrixUnitaire));
					}
				}
			}

			// addArticle(connection, new Article("Rog Strix", "Asus", 1300));
			// updateArticle(connection, 50, "UnitaryPrice", 2);
			// deleteArticle(connection,1);
			// Article article = readArticle(connection, 2);
			// System.out.println("Article: " + article.getIdArticle() + " - " +
			// article.getDescription() + " - " + article.getBrand() + " - " +
			// article.getUnitaryPrice());

			for (Article a : articles) {
				System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - "
						+ a.getUnitaryPrice());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// C
	public static void addArticle(Connection connection, Article article) {
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
	public static Article readArticle(Connection connection, int idArticle) {
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
	public static void updateArticle(Connection connection, Object newValue, String columnName, int idArticle) {
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
	public static void deleteArticle(Connection connection, int idArticle) {
		String deleteArticleSQL = "DELETE FROM T_Articles WHERE idArticle = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteArticleSQL)) {
			preparedStatement.setInt(1, idArticle);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}
}
