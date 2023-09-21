--product; productprice;
-- Create table
create table PRODUCT
(
  sku      VARCHAR2(20) not null,
  des      VARCHAR2(200),
  price    NUMBER,
  incremen NUMBER);
  
  ALTER TABLE PRODUCT ADD CONSTRAINT PRODUCT_pk PRIMARY KEY (sku);
  -- Create table
create table PRODUCTPRICE
(
  id_product_price NUMBER,
  id_product       VARCHAR2(20) not null,
  increment_price  NUMBER,
  max_dues_no      NUMBER

);
  ALTER TABLE PRODUCTPRICE ADD CONSTRAINT PRODUCTPRICE_id PRIMARY KEY (id_product_price);



ALTER TABLE PRODUCTPRICE ADD CONSTRAINT FK_id_prod FOREIGN KEY(id_product) REFERENCES PRODUCT(sku);


