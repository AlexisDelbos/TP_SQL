package fr.fms.business;

import fr.fms.entities.Article;
import java.util.List;

public interface IJob {


    void displayArticles();
    
    void addArticleCart(Article article);

    void deleteArticleCart(Article article);

  
    void displayCart();

    void validOrder();

    void clearCart();
}
