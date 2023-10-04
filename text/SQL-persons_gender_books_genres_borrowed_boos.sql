
CREATE TABLE `books`(
    `book_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `book_title` VARCHAR(30) NOT NULL DEFAULT 'NOT NULL',
    `book_autor` VARCHAR(70) NOT NULL DEFAULT 'NOT NULL',
    `book_genre` CHAR(30) NOT NULL DEFAULT 'NOT NULL',
    `book_binding` ENUM('') NOT NULL
);
CREATE TABLE `genres`(`genre` CHAR(30) NOT NULL);
CREATE TABLE `person`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL DEFAULT 'NOT NULL',
    `last_name` VARCHAR(100) NOT NULL DEFAULT 'NOT NULL',
    `midle_name` VARCHAR(30) NULL,
    `gender` ENUM('') NOT NULL
);
CREATE TABLE `gender`(`genger` ENUM('') NULL DEFAULT 'man');
CREATE TABLE `borrowed_books`(
    `borrowed_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `book_id` BIGINT NULL DEFAULT 'NOT NULL',
    `person_id` BIGINT NOT NULL DEFAULT 'NOT NULL',
    `day_of_borrowed` DATETIME NOT NULL
);


ALTER TABLE
    `genres` ADD UNIQUE `genre_unique`(`genre`);
ALTER TABLE
    `books` ADD CONSTRAINT `books_book_binding_Fk` FOREIGN KEY(`book_binding`) REFERENCES `genres`(`genre`);


ALTER TABLE
    `gender` ADD UNIQUE `genger_unique`(`genger`);
ALTER TABLE
    `gender` ADD CONSTRAINT `gender_genger_Fk` FOREIGN KEY(`genger`) REFERENCES `person`(`gender`);



    ALTER TABLE
    `genres` ADD CONSTRAINT `genres_genre_Fk` FOREIGN KEY(`genre`) REFERENCES `books`(`genre`);

ALTER TABLE
    `borrowed_books` ADD UNIQUE `bb_book_id_unique`(`book_id`);
ALTER TABLE
    `borrowed_books` ADD UNIQUE `bb_person_id_unique`(`person_id`);
ALTER TABLE
    `borrowed_books` ADD CONSTRAINT `borrowed_books_person_id_Fk` FOREIGN KEY(`person_id`) REFERENCES `person`(`id`);
ALTER TABLE
    `borrowed_books` ADD CONSTRAINT `borrowed_books_book_Fk` FOREIGN KEY(`book_id`) REFERENCES `books`(`book_id`);

