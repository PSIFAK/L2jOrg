DROP TABLE IF EXISTS `character_recipebook`;
CREATE TABLE IF NOT EXISTS `character_recipebook` (
  `charId` INT UNSIGNED NOT NULL DEFAULT 0,
  `id` decimal(11) NOT NULL DEFAULT 0,
  `type` INT NOT NULL DEFAULT 0,

  PRIMARY KEY (`id`,`charId`),
  FOREIGN KEY FK_CHARACTER_RECIPE_BOOK (`charId`) REFERENCES characters (`charId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;