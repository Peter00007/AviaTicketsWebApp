select birthdayDay from passengers
group by birthdayDay
having count(*) > 5;