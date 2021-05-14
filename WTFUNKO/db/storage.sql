DROP DATABASE IF EXISTS storage;
CREATE DATABASE storage;
USE storage;

DROP TABLE IF EXISTS product;

CREATE TABLE product (	
  code int primary key AUTO_INCREMENT,
  name char(20) not null,
  description char(200),
  price int default 0,
  quantity int default 0,
  rarity int default 0,
  dimension double default 9.5, 
  FranchiseName char(50),
  series char(50),
  mediumScore double default 0
);

INSERT INTO product values (1,"Venomized Captain America","Ecco a voi i Funko Pop! Vinyl dei vostri personaggi preferiti Marvel in versione Venomizzata!", 16 ,5, 0, 10, " Marvel ",  " Captain America",7 );
INSERT INTO product values (2,"Venomized Iron Man","Ecco a voi i Funko Pop! Vinyl dei vostri personaggi preferiti Marvel in versione Venomizzata!", 16 , 8, 0, 9, " Marvel ",  " Iron Man",7 );
INSERT INTO product values (3,"Venomized Thor ","Ecco a voi i Funko Pop! Vinyl dei vostri personaggi preferiti Marvel in versione Venomizzata!", 16 ,5, 0, 10, " Marvel ",  " Thor",8 );
INSERT INTO product values (4,"Venomized Doctor Strange","Ecco a voi i Funko Pop! Vinyl dei vostri personaggi preferiti Marvel in versione Venomizzata!", 16 ,5, 0, 10, " Marvel ",  " Doctor Strange ",7 );
INSERT INTO product values (5,"Balrog","Ecco a voi il nuovo Funko Pop! Il Signore Degli Anelli!", 20 ,5, 0, 20, " Lord of the rings ",  " Balrog",7 );
INSERT INTO product values (5,"LINKIN PARK HYBRID THEORY","Ecco a voi i nuovi Funko Pop! Linkin Park!", 20 ,5, 0, 20, " Linkin Park ",  " Linkin Park ",7 );

