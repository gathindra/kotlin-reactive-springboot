-- db/migration/V1__create_tables.sql
alter table addresses
    drop constraint addresses_customer_id_fkey;

alter table addresses
    add foreign key (customer_id) references customers
        on delete cascade;

alter table orders
    drop constraint orders_customer_id_fkey;

alter table orders
    add foreign key (customer_id) references customers
        on delete cascade;

alter table order_items
    drop constraint order_items_order_id_fkey;

alter table order_items
    add foreign key (order_id) references orders
        on delete cascade;