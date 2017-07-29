-- -----------------------------------------------------
-- Schema deal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `deal` DEFAULT CHARACTER SET utf8 ;
USE `deal` ;

-- -----------------------------------------------------
-- Table `deal`.`store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `deal`.`store` (
  `id` INT NOT NULL,
  `store_name` VARCHAR(45) NOT NULL,
  `email_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `deal`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `deal`.`product` (
  `id` INT NOT NULL,
  `product_name` VARCHAR(45) NOT NULL,
  `comments` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `deal`.`product_store`
-- -----------------------------------------------------
CREATE TABLE `deal`.`product_store` (
  `id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `store_id` INT NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_store_1_idx` (`product_id` ASC),
  INDEX `fk_product_store_2_idx` (`store_id` ASC),
  CONSTRAINT `fk_product_category_1`
    FOREIGN KEY (`store_id`)
    REFERENCES `deal`.`store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_store_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `deal`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Values `deal`.`store`
-- -----------------------------------------------------
INSERT INTO `deal`.`store`
(`id`,
`store_name`,
`email_id`)
VALUES
(1,
'Jacobs Pizza',
'deal.jacobs@mail.com');

INSERT INTO `deal`.`store`
(`id`,
`store_name`,
`email_id`)
VALUES
(2,
'Thomas Pizza & Green Salad',
'deal.thomas@mail.com');

-- -----------------------------------------------------
-- Values `deal`.`product`
-- -----------------------------------------------------
INSERT INTO `deal`.`product`
(`id`,
`product_name`,
`comments`)
VALUES
(1,
'Pineapple Bacon Thincrust Pizza',
'Crispy & Super Yummy');

INSERT INTO `deal`.`product`
(`id`,
`product_name`,
`comments`)
VALUES
(2,
'Mexican Avacado Pizza',
'Soft & Cheesy');

INSERT INTO `deal`.`product`
(`id`,
`product_name`,
`comments`)
VALUES
(3,
'Crispy Ghost Pepper Pizza',
'warning! no extra flakes even by mistake');


INSERT INTO `deal`.`product`
(`id`,
`product_name`,
`comments`)
VALUES
(4,
'Green Salad',
'Rich in fibre with the twist of sweetness');

-- -----------------------------------------------------
-- Values `deal`.`product`
-- -----------------------------------------------------
INSERT INTO `deal`.`product_store`
(`id`,
`product_id`,
`store_id`)
VALUES
(1,
1,
1);

INSERT INTO `deal`.`product_store`
(`id`,
`product_id`,
`store_id`)
VALUES
(2,
1,
2);

INSERT INTO `deal`.`product_store`
(`id`,
`product_id`,
`store_id`)
VALUES
(3,
2,
1);

INSERT INTO `deal`.`product_store`
(`id`,
`product_id`,
`store_id`)
VALUES
(4,
2,
2);


INSERT INTO `deal`.`product_store`
(`id`,
`product_id`,
`store_id`)
VALUES
(5,
3,
2);


INSERT INTO `deal`.`product_store`
(`id`,
`product_id`,
`store_id`)
VALUES
(6,
4,
2);

-- -----------------------------------------------------
-- Release v1.1
-- -----------------------------------------------------
INSERT INTO `deal`.`product`
(`id`,
`product_name`,
`comments`)
VALUES
(5,
'Jacobs Thin',
'Rich in fibre buscuits');

INSERT INTO `deal`.`product_store`
(`id`,
`product_id`,
`store_id`)
VALUES
(7,
5,
1);
