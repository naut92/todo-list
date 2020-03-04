DROP TABLE IF EXISTS task;

create table task(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    heading VARCHAR(250),
    text VARCHAR(250),
    date_added VARCHAR(250),
    edit_date VARCHAR(250),
    status VARCHAR(250)
);


INSERT INTO task (heading, text, date_added, edit_date, status) VALUES
('heading1', 'text1', '01-03-2020', '02-03-2020', 'done'),
('heading2', 'text2', '02-03-2020', '04-03-2020', 'new'),
('heading3', 'text3', '02-03-2020', '04-03-2020', 'new'),
('heading778', 'text4', '03-03-2020', '04-03-2020', 'done');