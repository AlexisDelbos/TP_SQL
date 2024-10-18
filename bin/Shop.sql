DROP DATABASE IF EXISTS Shop;
CREATE DATABASE Shop;
USE Shop;

CREATE TABLE T_Articles (
  IdArticle       int(4)      PRIMARY KEY AUTO_INCREMENT,
  Description     varchar(30) NOT NULL,
  Brand           varchar(30) NOT NULL,
  UnitaryPrice    float(8)    NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('WebCam', 'Logitech', 0);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Casque Audio', 'Syno', 105);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Office', 'Microsoft', 150);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('S10', 'Samsung', 2000);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Macbook', 'Apple', 2000);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Iphone50', 'Apple', 20000);


   -- 1.5 --
-- UPDATE T_Articles SET UnitaryPrice = 55 WHERE IdArticle='1';

   -- 1.6 --
-- DELETE FROM t_articles WHERE IdArticle = '1';

   -- 1.7 -- 
-- SELECT * FROM t_articles WHERE UnitaryPrice > 100

   -- 1.8 -- 
-- SELECT * FROM t_articles WHERE UnitaryPrice < 100 AND UnitaryPrice > 50;

   -- 1.9 -- 
-- SELECT * FROM t_articles ORDER BY UnitaryPrice ASC;

   -- 1.10 -- 
-- SELECT DESCRIPTION FROM t_articles ;


   -- 1.12 -- 
CREATE TABLE Category_Articles (
  IdCategory       INT(4)      PRIMARY KEY AUTO_INCREMENT,
  NomCategory      VARCHAR(30) NOT NULL,
  DescriptionCategory    VARCHAR(100) NOT NULL
) ENGINE = InnoDB;


INSERT INTO Category_Articles (NomCategory, DescriptionCategory) VALUES ('Matériel Info', 'Indispensables à un pc');
INSERT INTO Category_Articles (NomCategory, DescriptionCategory) VALUES ('Logiciel', 'SE,Antivir,Ide...');
INSERT INTO Category_Articles (NomCategory, DescriptionCategory) VALUES ('PC', 'LAPTOP et micro ordi...');
INSERT INTO Category_Articles (NomCategory, DescriptionCategory) VALUES ('Smartphone', 'ordi...');


ALTER TABLE t_articles ADD COLUMN IdCategory INT(4);
ALTER TABLE t_articles ADD FOREIGN KEY(IdCategory) REFERENCES category_articles(IdCategory); 

   -- 1.13 -- 
   
-- UPDATE t_articles SET IdCategory=1 WHERE IdArticle=1;
-- UPDATE t_articles SET IdCategory=1 WHERE IdArticle=2;
-- UPDATE t_articles SET IdCategory=2 WHERE IdArticle=3;
-- UPDATE t_articles SET IdCategory=4 WHERE IdArticle=4;
-- UPDATE t_articles SET IdCategory=3 WHERE IdArticle=5;
-- UPDATE t_articles SET IdCategory=4 WHERE IdArticle=6;
-- 
-- SELECT IdArticle,t_articles.description, Brand, UnitaryPrice, NomCategory FROM t_articles INNER JOIN category_articles ON t_articles.IdCategory = category_articles.IdCategory ;
-- 

   -- 6 -- 
   
CREATE TABLE T_Users (
  IdUser    INT(4) PRIMARY KEY AUTO_INCREMENT,
  Login     VARCHAR(20) NOT NULL,
  Password  VARCHAR(20) NOT NULL
) ENGINE = InnoDB;

	
	
	-- 7 -- 

INSERT INTO T_Users (Login, Password) VALUES ('Alexis', 'motdepasse');
INSERT INTO T_Users (Login, Password) VALUES ('Louis', 'capoutdraconis');


  -- 11 -- 
CREATE USER 'Alexis'@'localhost' IDENTIFIED BY 'motdepasse';
GRANT ALL PRIVILEGES ON Shop.* TO 'Alexis'@'localhost';
FLUSH PRIVILEGES;


