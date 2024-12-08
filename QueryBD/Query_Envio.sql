connect 'jdbc:derby://localhost:1527/bd_Musica_Envios;user=app;password=app';

-- Eliminación de tablas existentes
DROP TABLE envios;

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