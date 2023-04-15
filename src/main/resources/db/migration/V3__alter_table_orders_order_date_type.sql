
alter table orders
    alter column order_date type timestamp using order_date::timestamp;