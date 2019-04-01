SELECT * FROM ag_sistemas.alarma;
SELECT * FROM ag_sistemas.casa;
SELECT * FROM ag_sistemas.cliente;
SELECT distinct cliente , baterias FROM n_cuenta , casa where persona_Mail='@';
SELECT * FROM ag_sistemas.persona;
SELECT * FROM ag_sistemas.cliente where apellido='gatti';
select * from  casa WHERE n_Cuenta= 'cl.getN_Cuenta()';
INSERT INTO alarma  VALUES ('sw3454','SP5000',4,'MG5000','Miranda 1220');

INSERT INTO baterias VALUES ('sw3452','2019-01-23','duracell','sw3453');

INSERT INTO casa VALUES ('Miranda 1200','La alera',2);

INSERT INTO cliente VALUES (8,'fsa',null,
'2019-02-03',null,null,null,null,1);
delete from baterias where idBateria='sdf';
delete from alarma where n_Serie='SP70001';
delete from casa where n_Cuenta='1-PAN';
delete from cliente where n_Cuenta='1-PAN';

select count(Producto) as cantidadProducto, Rubro, avg(precio) as preciopromedio
from productos p 
               inner join rubros r on r.idRubro=p.idRubro
               group by Rubro
               order by Rubro;
select zona  from zonas 
where idZona not in
(select c.idZona
from facturacabecera fc inner join clientes c on fc.idCliente=c.idCliente
where anulada=0);

select numero, fecha,Cliente,Vendedor,sum(cantidad*precio) as total
from facturacabecera fc
                       inner join facturadetalle fd on fd.idFactura=fc.idFactura
                       inner join clientes c on c.idCliente=fc.idCliente
                       inner join vendedores v on v.idVendedor=fc.idVendedor
                       inner join productos p on p.idProducto=fd.idProducto
where year(fecha)=2008 and month(fecha) in (1,2,3)
group by numero,Cliente,Vendedor,fech

select nombre , apellido ,direccion ,marca_Modelo, n_Serie,a.nota,fecha_Bateria
from cliente cl 
				inner join casa cs on cs.n_Cuenta= cl.n_Cuenta
                inner join alarma a on a.casa_Direccion= cs.direccion
                WHERE year(fecha_Bateria)=2019 and month(fecha_Bateria) in (1,2) and 05<day(fecha_Bateria)<15; 
