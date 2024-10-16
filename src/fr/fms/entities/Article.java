
package fr.fms.entities;

public class Article {
	
	int IdArticle;
	String Description;
	int UnitaryPrice;
	String Brand;
	
	public Article(int idArticle, String description,String brand, int unitaryPrice ) {
		
		IdArticle = idArticle;
		Description = description;
		UnitaryPrice = unitaryPrice;
		Brand = brand;
	}
	
	public Article(String description,String brand, int unitaryPrice ) {
		Description = description;
		UnitaryPrice = unitaryPrice;
		Brand = brand;
	}


	public int getIdArticle() {
		return IdArticle;
	}

	public void setIdArticle(int idArticle) {
		IdArticle = idArticle;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getUnitaryPrice() {
		return UnitaryPrice;
	}

	public void setUnitaryPrice(int unitaryPrice) {
		UnitaryPrice = unitaryPrice;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}
	
	

}
