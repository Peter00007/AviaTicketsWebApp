select birthday_day from passengers
group by birthday_day
having count(*) > 5;