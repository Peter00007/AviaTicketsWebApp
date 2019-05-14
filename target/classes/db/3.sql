CREATE temporary table passenger_flight AS
   SELECT *
   FROM (
       SELECT passenger_id FROM tickets where id in(
       select ticket_id from flights_tickets where flight_id in (
       select id from flights where date("2019-03-01") < flights.flight_date and
      date("2019-04-01") > flights.flight_date
       )
       )
   ) as passenger_flight;

select * from passengers where id in (
select passenger_id from passenger_flight group by passenger_id
having count(*) > 1
);