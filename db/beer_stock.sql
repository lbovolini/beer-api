CREATE DATABASE IF NOT EXISTS `beer_stock`;

CREATE TABLE IF NOT EXISTS `beer_stock`.`beer` (
  `id` BINARY(16) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `brand` VARCHAR(100) NOT NULL,
  `max` INT NOT NULL,
  `quantity` INT NOT NULL,
  `type` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);
