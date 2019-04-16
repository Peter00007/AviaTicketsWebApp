insert into passengers(first_name, last_name, birthday_day)
values("Peter", "Fediuk", "1996-03-04");
insert into passengers(first_name, last_name, birthday_day)
values("Andriy", "Paziuk", "1995-12-14");
insert into passengers(first_name, last_name, birthday_day)
values("Dima", "Chepurnii", "1995-06-06");

insert into route_airports(route_id, airport_id, airport_type)
values(1,1,"Departure");
insert into route_airports(route_id, airport_id, airport_type)
values(1,2,"Arrival");
insert into route_airports(route_id, airport_id, airport_type)
values(2,1,"Departure");
insert into route_airports(route_id, airport_id, airport_type)
values(2,3,"Arrival");
insert into route_airports(route_id, airport_id, airport_type)
values(3,5,"Departure");
insert into route_airports(route_id, airport_id, airport_type)
values(3,4,"Arrival");


insert into routes(name)
values("West");
insert into routes(name)
values("Lvivskii");
insert into routes(name)
values("Central");

insert into airports(name)
values("Kiev");
insert into airports(name)
values("Che");
insert into airports(name)
values("Lviv");
insert into airports(name)
values("Dnipro");
insert into airports(name)
values("IF");

insert into aircraft(name)
values("Boing");
insert into aircraft(name)
values("An");

insert into flights(aircraft_id, flight_date)
values(1, "2019-03-05");
insert into flights(aircraft_id, flight_date)
values(2, "2019-04-05");
insert into flights(aircraft_id, flight_date)
values(1, "2019-06-05");
insert into flights(aircraft_id, flight_date)
values(1, "2019-04-20");

insert into flight_routes(flight_id, route_id)
values(1,1);
insert into flight_routes(flight_id, route_id)
values(2,3);
insert into flight_routes(flight_id, route_id)
values(3,2);

insert into tickets(status, passenger_id, created, seat_type, price)
values("Reserved", 1, "2019-03-01", "Econom", 500);
insert into tickets(status, passenger_id, created, seat_type, price)
values("Reserved", 2, "2019-03-10", "Business", 1000);
insert into tickets(status, passenger_id, created, seat_type, price)
values("Reserved", 3, "2019-04-20", "Econom", 2000);
insert into tickets(status, passenger_id, created, seat_type, price)
values("Reserved", 1, "2019-05-01", "Econom", 1500);

insert into flights_tickets(ticket_id, flight_id)
values(1,1);
insert into flights_tickets(ticket_id, flight_id)
values(2,2);
insert into flights_tickets(ticket_id, flight_id)
values(3,3);
insert into flights_tickets(ticket_id, flight_id)
values(4,4);
