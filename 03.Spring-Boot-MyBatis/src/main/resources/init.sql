CREATE TABLE student (
    sno VARCHAR(3) NOT NULL PRIMARY KEY,
    name VARCHAR(9) NOT NULL,
    sex CHAR(2) NOT NULL
);

INSERT INTO student VALUES ('001', 'KangKang', 'M');
INSERT INTO student VALUES ('002', 'Mike', 'M');
INSERT INTO student VALUES ('003', 'Jane', 'F');