create table passengers (
id int AUTO_INCREMENT,
firstName varchar(100) not null,
lastName varchar(100) not null,
birthdayDay date not null,
primary key(id)
);

create table routes (
id INT AUTO_INCREMENT,
nameRoute varchar(100) not null,
primary key(id)
);

create table airports (
id INT AUTO_INCREMENT,
nameAirport varchar(100) not null,
typeFlight varchar(100) not null,
primary key(id)
);

create table aircrafts (
id INT AUTO_INCREMENT,
typeAircraft varchar(100) not null,
primary key(id)
);

create table route_airports (
id INT not null auto_increment,
route_id INT not null,
airport_id INT not null,
primary key(id),
foreign key(route_id)  REFERENCES routes (id),
foreign key(airport_id)  REFERENCES airports (id)
);

create table tickets (
id int AUTO_INCREMENT,
statusTicket varchar(100) not null,
passenger_id int not null,
buyDate date not null,
seatType varchar(100) not null,
price double not null,
primary key(id),
foreign key(passenger_id)  REFERENCES passengers (id)
);

create table flights (
id INT AUTO_INCREMENT,
route_airports_id INT not null,
aircraft_id INT not null,
flightDate date not null,
primary key(id),
foreign key(route_airports_id)  REFERENCES route_airports (id),
foreign key(aircraft_id)  REFERENCES aircrafts (id)
);

create table flights_tickets (
ticket_id INT not null,
flight_id INT not null,
foreign key(ticket_id)  REFERENCES tickets (id),
foreign key(flight_id)  REFERENCES flights (id)
);
