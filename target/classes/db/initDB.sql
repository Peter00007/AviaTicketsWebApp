create table passengers (
id int AUTO_INCREMENT,
first_name varchar(100) not null,
last_name varchar(100) not null,
birthday_day date not null,
primary key(id)
);

create table routes (
id INT AUTO_INCREMENT,
name varchar(100) not null,
primary key(id)
);

create table airports (
id INT AUTO_INCREMENT,
name varchar(100) not null,
primary key(id)
);

create table aircraft (
id INT AUTO_INCREMENT,
name varchar(100) not null,
primary key(id)
);

create table route_airports (
route_id INT not null,
airport_id INT not null,
airport_type varchar(100) not null,
foreign key(route_id)  REFERENCES routes (id),
foreign key(airport_id)  REFERENCES airports (id)
);

create table tickets (
id int AUTO_INCREMENT,
status varchar(100) not null,
passenger_id int not null,
created date not null,
seat_type varchar(100) not null,
price double not null,
primary key(id),
foreign key(passenger_id)  REFERENCES passengers (id)
);

create table flights (
id INT AUTO_INCREMENT,
aircraft_id INT not null,
flight_date date not null,
primary key(id),
foreign key(aircraft_id)  REFERENCES aircraft (id)
);

create table flights_tickets (
ticket_id INT not null,
flight_id INT not null,
foreign key(ticket_id)  REFERENCES tickets (id),
foreign key(flight_id)  REFERENCES flights (id)
);

create table flight_routes (
flight_id INT not null,
route_id INT not null,
foreign key(route_id)  REFERENCES routes (id),
foreign key(flight_id)  REFERENCES flights (id)
);