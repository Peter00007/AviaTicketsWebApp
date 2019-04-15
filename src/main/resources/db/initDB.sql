create table passengers (
id int not null,
firstName varchar(100) not null,
lastName varchar(100) not null,
birthdayDay date not null,
primary key(id)
);

create table tickets (
id int not null,
statusTicket varchar(100) not null,
passenger_id int not null,
seatType varchar(100) not null,
price double not null,
primary key(id)
);

create table flightstickets (
fight_id INT not null,
ticket_id INT not null
);

create table flights (
id INT not null,
route_id INT not null,
aircraft_id INT not null,
flightDate date not null,
primary key(id)
);

create table aircrafts (
id INT not null,
typeAircraft varchar(100) not null,
primary key(id)
);

create table routeairports (
route_id INT not null,
airport_id INT not null
);

create table routes (
id INT not null,
nameRoute varchar(100) not null,
primary key(id)
);

create table airports (
id INT not null,
nameAirport varchar(100) not null,
typeFlight varchar(100) not null,
primary key(id)
);