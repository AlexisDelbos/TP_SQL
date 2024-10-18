package fr.fms;

import fr.fms.business.IJobImpl;
import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre login : ");
        String login = scanner.nextLine();

        System.out.print("Entrez votre mot de passe : ");
        String password = scanner.nextLine();

        UserDao userDao = new UserDao();
        boolean isAuthenticated = userDao.authenticate(login, password);

        if (isAuthenticated) {
            System.out.println("Bienvenue, " + login + " !");
            IJobImpl shopJob = new IJobImpl();
            shopJob.displayArticles();
        } else {
            System.out.println("Login ou mot de passe incorrect.");
        }

        scanner.close();
    }

}
