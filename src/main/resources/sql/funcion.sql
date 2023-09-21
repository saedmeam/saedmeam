create or replace function f_incremento_venta(pv_sku varchar2)
  return number is
  ln_precio     numeric;
  ln_precio_inc numeric;

  lv_valor_dec varchar2(2);
  cursor c_precio(cv_sku varchar2) is
select * from PRODUCT p where p.sku = cv_sku;
t_precio c_precio%rowtype;

begin
  if c_precio%isopen then
    close c_precio;
  end if;
  open c_precio(pv_sku);
  FETCH c_precio
    INTO t_precio;
  close c_precio;

  ln_precio     := t_precio.price * (t_precio.incremen / 100);
  ln_precio_inc := ln_precio + t_precio.price;
  select round(ln_precio_inc, 0) + 0.1 into ln_precio_inc from dual;
  update PRODUCTPRICE
     set increment_price = ln_precio_inc
   where id_product = pv_sku;

  return ln_precio_inc;
end;
