CREATE TABLE IF NOT EXISTS Book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publication_year INT,
    isbn VARCHAR(20) UNIQUE
);

CREATE TABLE IF NOT EXISTS Patron (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    country VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS BorrowingRecord (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    patron_id INT,
    borrow_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(255),
    FOREIGN KEY (book_id) REFERENCES Book(id),
    FOREIGN KEY (patron_id) REFERENCES Patron(id)
);

INSERT INTO admins (username, password)
VALUES ('admin', 'admin');
----------- Developed by: Sherif Magdy -------------
-----------        www.sherio.me       -------------