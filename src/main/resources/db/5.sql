select * from passengers
inner join tickets
on tickets.passenger_id = passengers.id and price > 1  in (
select id from flights
where flights.flightDate < date("2030-01-01") and flights.flightDate > date("2000-01-01") in(
select id from airports
where airports.nameAirport like "Kiev" and airports.typeFlight = "Departure"
)
);