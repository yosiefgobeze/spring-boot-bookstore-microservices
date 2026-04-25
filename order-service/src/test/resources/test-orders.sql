truncate table orders cascade;
alter sequence order_id_seq restart with 100;
alter sequence order_item_id_seq restart with 100;

insert into orders (id,order_number,username,
                    customer_name,customer_email,customer_phone,
                    delivery_address_line1,delivery_address_line2,delivery_address_city,
                    delivery_address_state,delivery_address_zip_code,delivery_address_country,
                    status,comments) values
                                         (1, 'order-123', 'user', 'lamiek', 'lamiek@gmail.com', '5105621256', '2056 Eagle Street', 'Apt 1', 'Dallas', 'TX', '56231', 'USA', 'NEW', null),
                                         (2, 'order-456', 'user', 'minas', 'minas@gmail.com', '4254561236', '451 BuenoVista Ave', 'Apt 1', 'Alameda', 'CA', '94555', 'USA', 'NEW', null)
;

insert into order_items(order_id, code, name, price, quantity) values
                                                                   (1, 'P130', 'Dracula', 15.00, 2),
                                                                   (2, 'P117', 'The Great Gatsby', 20.00, 1),
                                                                   (2, 'P119', 'War and Peace', 35.00, 1)
;