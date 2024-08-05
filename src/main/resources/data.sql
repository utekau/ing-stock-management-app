INSERT INTO stock_exchange (name, description) VALUES ('XNAS', 'Nasdaq');
INSERT INTO stock_exchange (name, description) VALUES ('XNYS', 'New York Stock Exchange');
INSERT INTO stock_exchange (name, description) VALUES ('XAMS', 'Euronext Amsterdam');
INSERT INTO stock_exchange (name, description) VALUES ('XTAI', 'Taiwan Stock Exchange');
INSERT INTO stock_exchange (name, description) VALUES ('XDUMMY', '');

INSERT INTO stock (name, description, current_price, last_update) VALUES ('Stock A', '', 99.99, CURRENT_TIMESTAMP());
INSERT INTO stock (name, description, current_price, last_update) VALUES ('Stock B', '', 20.00, CURRENT_TIMESTAMP());
INSERT INTO stock (name, description, current_price, last_update) VALUES ('Stock C', '', 1000.00, CURRENT_TIMESTAMP());
INSERT INTO stock (name, description, current_price, last_update) VALUES ('Stock D', '', 1000000.00, CURRENT_TIMESTAMP());
INSERT INTO stock (name, description, current_price, last_update) VALUES ('Stock E', '', 250.50, CURRENT_TIMESTAMP());

INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (1, 3);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (1, 1);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (2, 1);