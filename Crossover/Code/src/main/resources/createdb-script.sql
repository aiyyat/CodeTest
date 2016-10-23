CREATE SCHEMA `journals` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE user
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    enabled BIT(1) NOT NULL,
    login_name VARCHAR(255) NOT NULL,
    pwd VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    email_id VARCHAR(255) NOT NULL
);
ALTER TABLE `journals`.`user` 
ADD UNIQUE INDEX `email_id_UNIQUE` (`email_id` ASC);

CREATE TABLE item
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE journal
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    publish_date DATETIME NOT NULL,
    uuid VARCHAR(255),
    publisher_id BIGINT(20) NOT NULL,
    CONSTRAINT FK_c7picib39dl7kxro2349cnpn9 FOREIGN KEY (publisher_id) REFERENCES publisher (id)
);
CREATE INDEX FK_c7picib39dl7kxro2349cnpn9 ON journal (publisher_id);
CREATE TABLE publisher
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    user_id BIGINT(20) NOT NULL,
    CONSTRAINT FK_ml1xc0aovqkkm2p1lssgjkfas FOREIGN KEY (user_id) REFERENCES user (id)
);
CREATE UNIQUE INDEX UK_ml1xc0aovqkkm2p1lssgjkfas ON publisher (user_id);
CREATE TABLE subscription
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    date DATETIME NOT NULL,
    user_id BIGINT(20) NOT NULL
);
CREATE UNIQUE INDEX UK_tq3cq3gmsss8jjyb2l5sb1o6k ON subscription (user_id);

-- ~Achuth:Added for notification Registeration
CREATE TABLE `notification` (
  `id` BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `last_trigger` TIMESTAMP DEFAULT NULL,
  `email_status` VARCHAR(20) NOT NULL,
  `message` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
);