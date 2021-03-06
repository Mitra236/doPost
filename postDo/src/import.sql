INSERT INTO contacts (firstName, lastName, display, email, format, photo) VALUES ('Pera', 'Peric', 'pex', 'pexpex@gmail.com', 'HTML', 1);
INSERT INTO contacts (firstName, lastName, display, email, format, photo) VALUES ('Djura', 'Dobric', 'djux', 'djux@gmail.com', 'HTML', 2);
INSERT INTO contacts (firstName, lastName, display, email, format, photo) VALUES ('Srdjan', 'Nikolic', 'srki123', 'srki123@gmail.com', 'PLAIN', 3);
INSERT INTO contacts (firstName, lastName, display, email, format, photo) VALUES ('Milos', 'Dostojic', 'knjaz223', 'knjaz223@gmail.com', 'PLAIN', 4);

INSERT INTO tags (name) VALUES ('First Tag');
INSERT INTO tags (name) VALUES ('Second Tag');
INSERT INTO tags (name) VALUES ('Third Tag');
INSERT INTO tags (name) VALUES ('Fourth Tag');

INSERT INTO rules (condition, operation) VALUES ('TO', 'MOVE');
INSERT INTO rules (condition, operation) VALUES ('FROM', 'DELETE');
INSERT INTO rules (condition, operation) VALUES ('SUBJECT', 'COPY');

INSERT INTO folders (name, rule) VALUES ('Drafts', 1);
INSERT INTO folders (name, rule) VALUES ('Trash', 2);
INSERT INTO folders (name, rule) VALUES ('Promotions', 3);
INSERT INTO folders (name, rule) VALUES ('Electronics', 4);
INSERT INTO folders (name, rule) VALUES ('Recent promotions', 5);

INSERT INTO accounts (smtp, pop3/imap, username, password) VALUES ('smpt1', 'pop3', 'user', 'user');
INSERT INTO accounts (smtp, pop3/imap, username, password) VALUES ('smpt1', 'pop3', 'u', 'u');
INSERT INTO accounts (smtp, pop3/imap, username, password) VALUES ('smpt1', 'pop3', 'user', 'user');
INSERT INTO accounts (smtp, pop3/imap, username, password) VALUES ('smpt1', 'pop3', 'user', 'user');

INSERT INTO messages (contact, dateTime, subject, content, folder, account) VALUES (1, '2019-02-13 09:50:00', 'Matematika 1', 'Some content', 1, 1);
INSERT INTO messages (contact, dateTime, subject, content, folder, account) VALUES (1, '2019-02-13 09:50:00', 'Osnove programiranja', 'More content', 2, 3);
INSERT INTO messages (contact, dateTime, subject, content, folder, account) VALUES (1, '2019-02-13 09:50:00', 'Sistemski softver', 'Stuffs...', 2, 4);
INSERT INTO messages (contact, dateTime, subject, content, folder, account) VALUES (1, '2019-02-13 09:50:00', 'Matematika 2', 'Some more content', 3, 4);

INSERT INTO attachments (data, attachmentType, name) VALUES ('data1', 'base64', 'attachment1');
INSERT INTO attachments (data, attachmentType, name) VALUES ('data2', 'base64', 'attachment2');
INSERT INTO attachments (data, attachmentType, name) VALUES ('data3', 'base64', 'attachment3');
INSERT INTO attachments (data, attachmentType, name) VALUES ('data4', 'base64', 'attachment4');

--INSERT INTO admins (username, pasword) VALUES ('admin', 'admin');
--
--INSERT INTO categories (parent_cat_id, cat_name, cat_desc) VALUES (NULL, 'Racunari', 'Razni racunari');
--INSERT INTO categories (parent_cat_id, cat_name, cat_desc) VALUES (1, 'Laptop', 'Prenosni racunari');
--INSERT INTO categories (parent_cat_id, cat_name, cat_desc) VALUES (1, 'Desktop', 'Prenosni ali teski racunari');
--INSERT INTO categories (parent_cat_id, cat_name, cat_desc) VALUES (1, 'Server', 'Tesko prenosni racunari');
--
--INSERT INTO suppliers (sup_name, sup_address) VALUES ('Zika trade', 'Bul. oslobodjenja 1');
--INSERT INTO suppliers (sup_name, sup_address) VALUES ('Zika computers', 'Bul. oslobodjenja 2');
--INSERT INTO suppliers (sup_name, sup_address) VALUES ('Zika soft', 'Bul. oslobodjenja 3');
--
--INSERT INTO products (category_id, supplier_id, prod_name, vendor, description, price) VALUES (2, 1, 'ThinkPad T61', 'IBM', 'opis', 1000);
--INSERT INTO products (category_id, supplier_id, prod_name, vendor, description, price) VALUES (2, 1, 'Compaq nx9010', 'HP', 'opis', 1200);
--INSERT INTO products (category_id, supplier_id, prod_name, vendor, description, price) VALUES (2, 1, 'Tecra S5', 'Toshiba', 'opis', 1100);
--
--INSERT INTO users (username, pasword, first_name, last_name, user_address, email, receive_news) VALUES ('chenejac', 'chenejac', 'Dragan', 'Ivanovic', 'Fruskogorska 11', 'chenejac@uns.ac.rs', FALSE);
--INSERT INTO users (username, pasword, first_name, last_name, user_address, email, receive_news) VALUES ('minja', 'minja', 'Milan', 'Vidakovic', 'Fruskogorska 11', 'minja@uns.ac.rs', FALSE);