CREATE OR REPLACE PROCEDURE act_price IS
  ln_cuotas     numeric;
  ln_precio     numeric;
  ln_precio_inc numeric;
  cursor c_precio is
    select * from PRODUCT p;
  t_precio c_precio%rowtype;
BEGIN

  if c_precio%isopen then
    close c_precio;
  end if;
  open c_precio();
  LOOP
    FETCH c_precio
      INTO t_precio;
  
    ln_precio     := t_precio.price * (t_precio.incremen / 100);
    ln_precio_inc := ln_precio + t_precio.price;
    select round(ln_precio_inc, 0) + 0.1 into ln_precio_inc from dual;
    if ln_precio_inc > 1000 then
      ln_cuotas := 30;
    elsif ln_precio_inc > 300 then
      ln_cuotas := 24;
    elsif ln_precio_inc > 250 then
      ln_cuotas := 18;
    else
      if ln_precio_inc / 12 > 10 then
        ln_cuotas := 12;
      elsif ln_precio_inc / 9 > 10 then
        ln_cuotas := 9;
      elsif ln_precio_inc / 6 > 10 then
        ln_cuotas := 6;
      elsif ln_precio_inc / 3 > 10 then
        ln_cuotas := 3;
      end if;
    end if;
  
    update PRODUCTPRICE
       set increment_price = ln_precio_inc, max_dues_no = ln_cuotas
     where id_product = t_precio.sku;
    EXIT WHEN c_precio%NOTFOUND;
  
  end loop;
  close c_precio;

END act_price;
