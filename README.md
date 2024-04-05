
# REST api for book library

Allows you to find, add, change books, authors and publishers.

implemented one-to-many connection: publishers -> book

and many-to-many authors <---> books


## DB set up

Create script

```bash
CREATE TABLE publishing_house (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    year_of_publication SMALLINT NOT NULL,
    publishing_house_id INTEGER NOT NULL,
    FOREIGN KEY (publishing_house_id) REFERENCES publishing_house (id) ON DELETE CASCADE
);

CREATE TABLE author (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL
);

CREATE TABLE author_book(
    book_id INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE
);
```
DB diagram
![diagram](https://github.com/AidarArt/REST_API/blob/master/screenshots/rest_db.png)