package fr.fms.business;

import fr.fms.entities.Article;
import fr.fms.dao.ArticleDao;
import java.util.ArrayList;
import java.util.List;

public class IJobImpl implements IJob {
	
    private ArticleDao articleDao;
    private List<Article> panier;

    public IJobImpl() {
        this.articleDao = new ArticleDao();  
        this.panier = new ArrayList<>();     
    }

    @Override
    public void displayArticles() {
        List<Article> articles = articleDao.findAll();
        articles.forEach(article -> System.out.println(article));
    }

    @Override
    public void addArticleCart(Article article) {
        panier.add(article);
        System.out.println("Un article a été ajouté au panier : " + article.getDescription());
    }

    @Override
    public void deleteArticleCart(Article article) {
        panier.remove(article);
        System.out.println("Un article a été supprimé du panier : " + article.getDescription());
    }

    @Override
    public void displayCart() {
        System.out.println("Contenu du panier :");
        panier.forEach(article -> System.out.println(article.getDescription() + " - " + article.getUnitaryPrice() + "€"));
    }

    @Override
    public void validOrder() {
        double total = panier.stream().mapToDouble(Article::getUnitaryPrice).sum();
        System.out.println("Validation de la commande le montant total : " + total + "€");
        panier.clear(); 
    }

    @Override
    public void clearCart() {
        panier.clear();
        System.out.println("Panier vidé !");
    }
}
