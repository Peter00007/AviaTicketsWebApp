insert into passengers(id, firstName, lastName, birthdayDay)
values(1, "Peter", "Fediuk", "1996-03-04");
insert into passengers(id, firstName, lastName, birthdayDay)
values(2, "Nadia", "Fediuk", "1990-06-27");
insert into passengers(id, firstName, lastName, birthdayDay)
values(3, "Ania", "Fediuk", "1989-12-16");
insert into passengers(id, firstName, lastName, birthdayDay)
values(4, "Kolya", "Fediuk", "1987-12-13");
insert into passengers(id, firstName, lastName, birthdayDay)
values(5, "Olya", "Fediuk", "1985-03-25");
insert into passengers(id, firstName, lastName, birthdayDay)
values(6, "q", "q", "2000-05-04");
insert into passengers(id, firstName, lastName, birthdayDay)
values(7, "a", "a", "2000-05-04");
insert into passengers(id, firstName, lastName, birthdayDay)
values(8, "e", "w", "2000-05-04");
insert into passengers(id, firstName, lastName, birthdayDay)
values(9, "q", "w", "2000-05-04");
insert into passengers(id, firstName, lastName, birthdayDay)
values(10, "r", "grouping", "2000-05-04");
insert into passengers(id, firstName, lastName, birthdayDay)
values(11, "q", "world", "2000-05-04");
insert into passengers(id, firstName, lastName, birthdayDay)
values(12, "d", "h", "2000-05-04");
insert into passengers(id, firstName, lastName, birthdayDay)
values(13, "Dima", "Che", "1995-06-06");
insert into passengers(id, firstName, lastName, birthdayDay)
values(14, "Andrii", "Paz", "1995-12-14");

insert into aircrafts(id, typeAircraft)
values(1, "Boing");
insert into aircrafts(id, typeAircraft)
values(2, "An");
insert into aircrafts(id, typeAircraft)
values(3, "Airbus");

insert into routes(id, nameRoute)
values(1, "Ukraine");
insert into routes(id, nameRoute)
values(2, "Europe");
insert into routes(id, nameRoute)
values(3, "Asia");
insert into routes(id, nameRoute)
values(4, "America");

insert into airports(id, nameAirport, typeFlight)
values(1, "Kiev", "Departure");
insert into airports(id, nameAirport, typeFlight)
values(2, "Che", "Arrival");
insert into airports(id, nameAirport, typeFlight)
values(3, "London", "Departure");
insert into airports(id, nameAirport, typeFlight)
values(4, "PAris", "Arrival");
insert into airports(id, nameAirport, typeFlight)
values(5, "New Delhi", "Departure");
insert into airports(id, nameAirport, typeFlight)
values(6, "Pekin", "Arrival");
insert into airports(id, nameAirport, typeFlight)
values(7, "New York", "Departure");
insert into airports(id, nameAirport, typeFlight)
values(8, "Las Vegas", "Arrival");

insert into routeairports(route_id, airport_id)
values(1, 1);
insert into routeairports(route_id, airport_id)
values(1, 2);
insert into routeairports(route_id, airport_id)
values(2, 3);
insert into routeairports(route_id, airport_id)
values(2, 4);
insert into routeairports(route_id, airport_id)
values(3, 5);
insert into routeairports(route_id, airport_id)
values(3, 6);
insert into routeairports(route_id, airport_id)
values(4, 7);
insert into routeairports(route_id, airport_id)
values(4, 8);

insert into flights(id, route_id, aircraft_id, flightDate)
values(1,1,1, "2019-03-05");
insert into flights(id, route_id, aircraft_id, flightDate)
values(2,2,2, "2019-04-10");
insert into flights(id, route_id, aircraft_id, flightDate)
values(3,4,1, "2019-03-15");
insert into flights(id, route_id, aircraft_id, flightDate)
values(4,3,3, "2019-06-05");

insert into tickets (id, statusTicket, passenger_id, seatType, price)
values (1, "reserved", 1, "Econom", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (2, "reserved", 2, "Econom", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (3, "reserved", 2, "Business", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (4, "reserved", 2, "Business", 1500);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (5, "reserved", 2, "Business", 2000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (6, "reserved", 2, "Econom", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (7, "reserved", 2, "Econom", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (8, "reserved", 2, "Econom", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (9, "reserved", 2, "Business", 3000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (10, "reserved", 2, "Econom", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (11, "reserved", 2, "Business", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (11, "reserved", 2, "Econom", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (12, "reserved", 3, "Econom", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (13, "reserved", 4, "Econom", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (14, "reserved", 5, "Econom", 1000);
insert ignore into tickets (id, statusTicket, passenger_id, seatType, price)
values (15, "reserved", 6, "Econom", 1000);

insert into flightstickets (flight_id, ticket_id)
values(1,1);
insert into flightstickets (flight_id, ticket_id)
values(1,2);
insert into flightstickets (flight_id, ticket_id)
values(1,3);
insert into flightstickets (flight_id, ticket_id)
values(1,4);
insert into flightstickets (flight_id, ticket_id)
values(2,5);
insert into flightstickets (flight_id, ticket_id)
values(2,6);
insert into flightstickets (flight_id, ticket_id)
values(2,7);
insert into flightstickets (flight_id, ticket_id)
values(2,8);
insert into flightstickets (flight_id, ticket_id)
values(3,9);
insert into flightstickets (flight_id, ticket_id)
values(3,10);
insert into flightstickets (flight_id, ticket_id)
values(3,11);
insert into flightstickets (flight_id, ticket_id)
values(4,12);
insert into flightstickets (flight_id, ticket_id)
values(4,13);
insert into flightstickets (flight_id, ticket_id)
values(4,14);
insert into flightstickets (flight_id, ticket_id)
values(4,15);