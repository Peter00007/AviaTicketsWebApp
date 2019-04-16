SELECT * FROM passengers WHERE id IN (
SELECT passenger_id from tickets WHERE id IN (
SELECT ticket_id FROM flights_tickets WHERE flight_id IN (
select f.id from flights f
inner join passengers p
on
DAYOFYEAR(date_add(flight_date, interval 3 day)) >= DAYOFYEAR(p.birthday_day)
and DAYOFYEAR(date_sub(flight_date,  interval 3 day))  <= DAYOFYEAR(p.birthday_day))));