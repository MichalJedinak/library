CREATE TABLE `member`(
    `member_id` BIGINT NOT NULL,
    `member_name` VARCHAR(30) NOT NULL,
    `member_lastName` VARCHAR(50) NOT NULL
);
ALTER TABLE
    `member` ADD UNIQUE `member_member_id_unique`(`member_id`);
CREATE TABLE `autors`(
    `autor_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE KEY,
    `autor_name` VARCHAR(30) NOT NULL,
    `autor_last_name` VARCHAR(50) NOT NULL
);
CREATE TABLE `borrowed_books`(
    `borrowed_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `book_id` BIGINT NULL DEFAULT 'NOT NULL',
    `person_id` BIGINT NOT NULL DEFAULT 'NOT NULL',
    `day_of_borrowed` DATETIME NOT NULL,
    `amount` DECIMAL(5, 2) NOT NULL
);
ALTER TABLE
    `borrowed_books` ADD UNIQUE `borrowed_books_book_id_unique`(`book_id`);
ALTER TABLE
    `borrowed_books` ADD UNIQUE `borrowed_books_person_id_unique`(`person_id`);
CREATE TABLE `Item`(
    `item_name` VARCHAR(100) NOT NULL
);
ALTER TABLE
    `Item` ADD UNIQUE `item_item_name_unique`(`item_name`);
CREATE TABLE `mployee`(
    `employe_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE KEY,
    `employee_name` VARCHAR(30) NOT NULL,
    `employee_lastName` VARCHAR(50) NOT NULL
);
CREATE TABLE `genres`(
    `genre` VARCHAR(40) NOT NULL DEFAULT 'NOT NULL'
);
ALTER TABLE
    `genres` ADD UNIQUE `genres_genre_unique`(`genre`);
CREATE TABLE `Video`(
    `title` VARCHAR(50) NOT NULL,
    `genge` BIGINT NOT NULL
);
ALTER TABLE
    `Video` ADD UNIQUE `video_title_unique`(`title`);
ALTER TABLE
    `Video` ADD UNIQUE `video_genge_unique`(`genge`);
CREATE TABLE `Magazine`(`title` VARCHAR(30) NOT NULL);
ALTER TABLE
    `Magazine` ADD UNIQUE `magazine_title_unique`(`title`);
CREATE TABLE `books`(
    `book_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `book_title` VARCHAR(30) NOT NULL DEFAULT 'NOT NULL',
    `book_autor` VARCHAR(50) NOT NULL DEFAULT 'NOT NULL',
    `book_genre` ENUM('') NOT NULL DEFAULT 'NOT NULL',
    `book_binding` ENUM('') NOT NULL
);
CREATE TABLE `gender`(`genger` ENUM('man,woman') NOT NULL);
ALTER TABLE
    `gender` ADD UNIQUE `gender_genger_unique`(`genger`);
CREATE TABLE `Binging`(
    `hard_binding` ENUM('hard') NOT NULL,
    `soft_binding` ENUM('soft') NOT NULL
);
ALTER TABLE
    `Binging` ADD UNIQUE `binging_hard_binding_unique`(`hard_binding`);
ALTER TABLE
    `Binging` ADD UNIQUE `binging_soft_binding_unique`(`soft_binding`);
CREATE TABLE `person`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL DEFAULT 'NOT NULL',
    `last_name` VARCHAR(100) NOT NULL DEFAULT 'NOT NULL',
    `gender` ENUM('') NOT NULL
);
ALTER TABLE
    `person` ADD UNIQUE `person_gender_unique`(`gender`);
ALTER TABLE
    `Binging` ADD CONSTRAINT `binging_soft_binding_foreign` FOREIGN KEY(`soft_binding`) REFERENCES `books`(`book_binding`);
ALTER TABLE
    `mployee` ADD CONSTRAINT `mployee_employee_lastname_foreign` FOREIGN KEY(`employee_lastName`) REFERENCES `person`(`last_name`);
ALTER TABLE
    `borrowed_books` ADD CONSTRAINT `borrowed_books_person_id_foreign` FOREIGN KEY(`person_id`) REFERENCES `person`(`id`);
ALTER TABLE
    `person` ADD CONSTRAINT `person_last_name_foreign` FOREIGN KEY(`last_name`) REFERENCES `autors`(`autor_last_name`);
ALTER TABLE
    `genres` ADD CONSTRAINT `genres_genre_foreign` FOREIGN KEY(`genre`) REFERENCES `books`(`book_genre`);
ALTER TABLE
    `mployee` ADD CONSTRAINT `mployee_employee_name_foreign` FOREIGN KEY(`employee_name`) REFERENCES `person`(`name`);
ALTER TABLE
    `member` ADD CONSTRAINT `member_member_lastname_foreign` FOREIGN KEY(`member_lastName`) REFERENCES `person`(`last_name`);
ALTER TABLE
    `member` ADD CONSTRAINT `member_member_name_foreign` FOREIGN KEY(`member_name`) REFERENCES `person`(`name`);
ALTER TABLE
    `autors` ADD CONSTRAINT `autors_autor_id_foreign` FOREIGN KEY(`autor_id`) REFERENCES `books`(`book_autor`);
ALTER TABLE
    `Binging` ADD CONSTRAINT `binging_hard_binding_foreign` FOREIGN KEY(`hard_binding`) REFERENCES `books`(`book_binding`);
ALTER TABLE
    `genres` ADD CONSTRAINT `genres_genre_foreign` FOREIGN KEY(`genre`) REFERENCES `books`(`book_genre`);
ALTER TABLE
    `borrowed_books` ADD CONSTRAINT `borrowed_books_book_id_foreign` FOREIGN KEY(`book_id`) REFERENCES `books`(`book_id`);
ALTER TABLE
    `person` ADD CONSTRAINT `person_name_foreign` FOREIGN KEY(`name`) REFERENCES `autors`(`autor_name`);
ALTER TABLE
    `Video` ADD CONSTRAINT `video_genge_foreign` FOREIGN KEY(`genge`) REFERENCES `genres`(`genre`);
ALTER TABLE
    `Magazine` ADD CONSTRAINT `magazine_title_foreign` FOREIGN KEY(`title`) REFERENCES `Item`(`item_name`);
ALTER TABLE
    `Item` ADD CONSTRAINT `item_item_name_foreign` FOREIGN KEY(`item_name`) REFERENCES `books`(`book_title`);
ALTER TABLE
    `gender` ADD CONSTRAINT `gender_genger_foreign` FOREIGN KEY(`genger`) REFERENCES `person`(`gender`);
ALTER TABLE
    `Video` ADD CONSTRAINT `video_title_foreign` FOREIGN KEY(`title`) REFERENCES `Item`(`item_name`);



    ///////////////////////////////////////////////////////////////////////////neperslo to////
    `mployee` ADD CONSTRAINT `mployee_employee_lastname_foreign` FOREIGN KEY(`employee_lastName`) REFERENCES `persons`(`person_lastName`)
    ALTER TABLE
    `persons` ADD CONSTRAINT `person_last_name_foreign` FOREIGN KEY(`person_lastNname`) REFERENCES `autors`(`autor_last_name`);




ALTER TABLE
    `genres` ADD CONSTRAINT `genres_genre_foreign` FOREIGN KEY(`genre`) REFERENCES `books`(`book_genre`);
ALTER TABLE
    `mployee` ADD CONSTRAINT `mployee_employee_name_foreign` FOREIGN KEY(`employee_name`) REFERENCES `person`(`person_name`);
ALTER TABLE
    `member` ADD CONSTRAINT `member_member_lastname_foreign` FOREIGN KEY(`member_lastName`) REFERENCES `person`(`person_lastName`);
ALTER TABLE
    `member` ADD CONSTRAINT `member_member_name_foreign` FOREIGN KEY(`member_name`) REFERENCES `persons`(`person_name`);
ALTER TABLE
    `autors` ADD CONSTRAINT `autors_autor_id_foreign` FOREIGN KEY(`autor_id`) REFERENCES `books`(`book_autor`);
ALTER TABLE
    `Binging` ADD CONSTRAINT `binging_hard_binding_foreign` FOREIGN KEY(`hard_binding`) REFERENCES `books`(`book_binding`);
ALTER TABLE
    `genres` ADD CONSTRAINT `genres_genre_foreign` FOREIGN KEY(`genre`) REFERENCES `books`(`book_genre`);
ALTER TABLE
    `borrowed_books` ADD CONSTRAINT `borrowed_books_book_id_foreign` FOREIGN KEY(`book_id`) REFERENCES `books`(`book_id`);
ALTER TABLE
    `persons` ADD CONSTRAINT `person_name_foreign` FOREIGN KEY(`name`) REFERENCES `autors`(`autor_name`);
ALTER TABLE
    `Video` ADD CONSTRAINT `video_genge_foreign` FOREIGN KEY(`genge`) REFERENCES `genres`(`genre`);
ALTER TABLE
    `Magazine` ADD CONSTRAINT `magazine_title_foreign` FOREIGN KEY(`title`) REFERENCES `Item`(`item_name`);
ALTER TABLE
    `Item` ADD CONSTRAINT `item_item_name_foreign` FOREIGN KEY(`item_name`) REFERENCES `books`(`book_title`);
ALTER TABLE
    `gender` ADD CONSTRAINT `gender_genger_foreign` FOREIGN KEY(`genger`) REFERENCES `person`(`gender`);
ALTER TABLE
    `Video` ADD CONSTRAINT `video_title_foreign` FOREIGN KEY(`title`) REFERENCES `Item`(`item_name`);