create table route_price (
id INT not null,
name varchar(100) not null,
price double,
primary key(id)
)

insert into route_price (id, name)
select * from routes;

update route_price
set price = (
select sum(price) from tickets where id in (
select ticket_id from flights_tickets where flight_id in (
select id from flights where date("2019-03-01") < flights.flight_date and
date("2019-04-01") > flights.flight_date and id in (
select flight_id from flight_routes where route_id in(
select id from routes
where route_price.id = routes.id)))));

select * from route_price order by price desc limit 1;