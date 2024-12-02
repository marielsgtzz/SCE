connect 'jdbc:derby://localhost:1527/bd_SCE;user=app;password=app';

-- Eliminación de tablas existentes
DROP TABLE IF EXISTS ordered_product;
DROP TABLE IF EXISTS customer_order;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS credito;
DROP TABLE IF EXISTS envios;

------------------------------- 
--     CUSTOMER
-------------------------------
CREATE TABLE customer 
(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1, INCREMENT BY 1) 
     CONSTRAINT CUSTOMER_PK PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  phone VARCHAR(45) NOT NULL,
  address VARCHAR(45) NOT NULL,
  city_region VARCHAR(2) NOT NULL,
  cc_number VARCHAR(19) NOT NULL
);

INSERT INTO customer (name, email, phone, address, city_region, cc_number) VALUES
('Ditirambo Farfulla', 'ditirambo.farfulla@mkt.bond', '56284000', 'Arroyo Bajo 10 int 10', 'AO', '123456'),
('Gandulfo Roncante', 'gandulfo.roncante@mkt.bond', '56284000', 'Arroyo Bajo 10 int 12', 'AO', '123456'),
('Vagonzo Durmiente', 'vagonzo.durminte@mkt.bond', '56284000', 'Arroyo Bajo 10 int 45', 'AO', '123456'),
('Hambrosio Comensal', 'hambrosio.comensal@mkt.bond', '56284000', 'Arroyo Bajo 10 int 58', 'AO', '123456');

------------------------------- 
--     CUSTOMER_ORDER
-------------------------------
CREATE TABLE customer_order 
(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1, INCREMENT BY 1) 
     CONSTRAINT CUSTOMER_ORDER_PK PRIMARY KEY,
  amount DECIMAL(6,2) NOT NULL,
  date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  confirmation_number INT NOT NULL,
  customer_id INT NOT NULL CONSTRAINT CUST_ORD_FK REFERENCES customer
);

------------------------------- 
--     CATEGORY
-------------------------------
CREATE TABLE category 
(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1, INCREMENT BY 1) 
     CONSTRAINT CATEGORY_PK PRIMARY KEY,
  name VARCHAR(45) NOT NULL
);

INSERT INTO category (name) VALUES 
('CD'),
('Vinilo'),
('Cassette');

------------------------------- 
--     GENRE
-------------------------------
CREATE TABLE genre 
(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1, INCREMENT BY 1) 
     CONSTRAINT GENRE_PK PRIMARY KEY,
  name VARCHAR(45) NOT NULL
);

INSERT INTO genre (name) VALUES 
('Rock'),
('Pop'),
('Jazz'),
('Clásica');

------------------------------- 
--     ARTIST
-------------------------------
CREATE TABLE artist 
(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1, INCREMENT BY 1) 
     CONSTRAINT ARTIST_PK PRIMARY KEY,
  name VARCHAR(45) NOT NULL
);

INSERT INTO artist (name) VALUES 
('The Beatles'),
('Queen'),
('Miles Davis'),
('Ludwig van Beethoven');

------------------------------- 
--     PRODUCT
-------------------------------
CREATE TABLE product 
(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1, INCREMENT BY 1),
  name VARCHAR(45) NOT NULL,
  price DECIMAL(5,2) NOT NULL,
  description VARCHAR(50),
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  category_id INT NOT NULL,
  genre_id INT NOT NULL,
  artist_id INT NOT NULL,
  existencia INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_product_genre FOREIGN KEY (genre_id) REFERENCES genre (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_product_artist FOREIGN KEY (artist_id) REFERENCES artist (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO product (name, price, description, last_update, category_id, genre_id, artist_id, existencia) VALUES
('Abbey Road', 12.99, 'Álbum icónico', TIMESTAMP('2020-10-06 09:00:00'), 2, 1, 1, 500),
('A Night at the Opera', 10.99, 'Álbum clásico', TIMESTAMP('2020-10-06 09:00:00'), 1, 1, 2, 500),
('Kind of Blue', 15.99, 'Álbum de jazz', TIMESTAMP('2020-10-06 09:00:00'), 3, 3, 3, 500),
('Symphony No.9', 9.99, 'Obra maestra', TIMESTAMP('2020-10-06 09:00:00'), 1, 4, 4, 500);

------------------------------- 
--     ORDERED_PRODUCT
-------------------------------
CREATE TABLE ordered_product
(
  customer_order_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (customer_order_id, product_id),
  CONSTRAINT fk_ordered_product_customer_order FOREIGN KEY (customer_order_id) REFERENCES customer_order (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_ordered_product_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

------------------------------- 
--     CREDITO
-------------------------------
CREATE TABLE credito 
(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1, INCREMENT BY 1) 
     CONSTRAINT CREDITO_PK PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  phone VARCHAR(45) NOT NULL,
  address VARCHAR(45) NOT NULL,
  city_region VARCHAR(2) NOT NULL,
  cc_number VARCHAR(19) NOT NULL,
  credito DECIMAL(10,2) NOT NULL
);

INSERT INTO credito (name, email, phone, address, city_region, cc_number, credito) VALUES
('Ditirambo Farfulla', 'ditirambo.farfulla@mkt.bond', '56284000', 'Arroyo Bajo 10 int 10', 'AO', '123456', 1000.0),
('Gandulfo Roncante', 'gandulfo.roncante@mkt.bond', '56284000', 'Arroyo Bajo 10 int 12', 'AO', '123456', 1000.0),
('Vagonzo Durmiente', 'vagonzo.durminte@mkt.bond', '56284000', 'Arroyo Bajo 10 int 45', 'AO', '123456', 1000.0),
('Hambrosio Comensal', 'hambrosio.comensal@mkt.bond', '56284000', 'Arroyo Bajo 10 int 58', 'AO', '123456', 1000.0);

------------------------------- 
--     ENVIOS
-------------------------------
CREATE TABLE envios 
(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY 
     (START WITH 1, INCREMENT BY 1) 
     CONSTRAINT ENVIOS_PK PRIMARY KEY,
  id_Tda INT NOT NULL,
  id_CUSTOMER_ORDER INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  phone VARCHAR(45) NOT NULL,
  address VARCHAR(45) NOT NULL,
  city_region VARCHAR(2) NOT NULL
);

disconnect;
