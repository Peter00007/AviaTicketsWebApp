select * from passengers
inner join tickets
on passengers.id = tickets.passenger_id
where tickets.id in (
select id from flights
where DAYOFYEAR(date_add(flightDate, interval 3 day)) >= DAYOFYEAR(passengers.birthdayDay)
and DAYOFYEAR(date_sub(flightDate,  interval 3 day))  <= DAYOFYEAR(passengers.birthdayDay)
);