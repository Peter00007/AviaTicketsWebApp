select * from passengers where id in (
select passenger_id from tickets
where tickets.price > 400 and date("2021-01-01") > tickets.created and
date("2018-01-01") < tickets.created and id in(
select ticket_id from flights_tickets where flight_id in (
select id from flights where id in (
select flight_id from flight_routes where route_id in (
select id from routes where id in (
select route_id from route_airports
where route_airports.airport_type = "Arrival" and airport_id in (
select id from airports where airports.name = "Che")))))));