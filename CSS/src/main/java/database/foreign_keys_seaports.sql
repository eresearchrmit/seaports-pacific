ALTER TABLE `seaports`.`data_element_abs_data` DROP FOREIGN KEY `FKFD21512FBE7C83D4` , DROP FOREIGN KEY `FKFD21512F12C92CDE` ;

ALTER TABLE `seaports`.`data_element_abs_data` 

  ADD CONSTRAINT `FKFD21512FBE7C83D4`

  FOREIGN KEY (`abs_data_id` )

  REFERENCES `seaports`.`abs_data` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE, 

  ADD CONSTRAINT `FKFD21512F12C92CDE`

  FOREIGN KEY (`data_element_id` )

  REFERENCES `seaports`.`data_element` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE;

ALTER TABLE `seaports`.`data_element_acorn_sat_data` DROP FOREIGN KEY `FK11EB5831EC94D333` , DROP FOREIGN KEY `FK11EB58316ACE14B7` ;

ALTER TABLE `seaports`.`data_element_acorn_sat_data` 

  ADD CONSTRAINT `FK11EB5831EC94D333`

  FOREIGN KEY (`acorn_sat_data_id` )

  REFERENCES `seaports`.`acorn_sat_data` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE, 

  ADD CONSTRAINT `FK11EB58316ACE14B7`

  FOREIGN KEY (`data_element_id` )

  REFERENCES `seaports`.`data_element` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE;

ALTER TABLE `seaports`.`data_element_bitre_data` DROP FOREIGN KEY `FK92C34061ED5D362C` , DROP FOREIGN KEY `FK92C34061525894B0` ;

ALTER TABLE `seaports`.`data_element_bitre_data` 

  ADD CONSTRAINT `FK92C34061ED5D362C`

  FOREIGN KEY (`data_element_id` )

  REFERENCES `seaports`.`data_element` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE, 

  ADD CONSTRAINT `FK92C34061525894B0`

  FOREIGN KEY (`bitre_data_id` )

  REFERENCES `seaports`.`bitre_data` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE;

ALTER TABLE `seaports`.`data_element_cmar_data` DROP FOREIGN KEY `FKAE352C76CB8CE91C` , DROP FOREIGN KEY `FKAE352C76719E52B5` ;

ALTER TABLE `seaports`.`data_element_cmar_data` 

  ADD CONSTRAINT `FKAE352C76CB8CE91C`

  FOREIGN KEY (`cmar_data_id` )

  REFERENCES `seaports`.`cmar_data` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE, 

  ADD CONSTRAINT `FKAE352C76719E52B5`

  FOREIGN KEY (`data_element_id` )

  REFERENCES `seaports`.`data_element` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE;

ALTER TABLE `seaports`.`data_element_csiro_data` DROP FOREIGN KEY `FKEC1C902B505D479C` , DROP FOREIGN KEY `FKEC1C902BED6FB022` ;

ALTER TABLE `seaports`.`data_element_csiro_data` 

  ADD CONSTRAINT `FKEC1C902B505D479C`

  FOREIGN KEY (`csiro_data_id` )

  REFERENCES `seaports`.`csiro_data` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE, 

  ADD CONSTRAINT `FKEC1C902BED6FB022`

  FOREIGN KEY (`data_element_id` )

  REFERENCES `seaports`.`data_element` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE;

ALTER TABLE `seaports`.`data_element_engineering_model_data` DROP FOREIGN KEY `FK610A3B84354219F0` , DROP FOREIGN KEY `FK610A3B8437160FB9` ;

ALTER TABLE `seaports`.`data_element_engineering_model_data` 

  ADD CONSTRAINT `FK610A3B84354219F0`

  FOREIGN KEY (`data_element_id` )

  REFERENCES `seaports`.`data_element` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE, 

  ADD CONSTRAINT `FK610A3B8437160FB9`

  FOREIGN KEY (`engineering_model_data_id` )

  REFERENCES `seaports`.`engineering_model_data` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE;

ALTER TABLE `seaports`.`data_element_past_data` DROP FOREIGN KEY `FK12BA90DF7504AA8A` , DROP FOREIGN KEY `FK12BA90DF71A410AC` ;

ALTER TABLE `seaports`.`data_element_past_data` 

  ADD CONSTRAINT `FK12BA90DF7504AA8A`

  FOREIGN KEY (`past_data_id` )

  REFERENCES `seaports`.`past_data` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE, 

  ADD CONSTRAINT `FK12BA90DF71A410AC`

  FOREIGN KEY (`data_element_id` )

  REFERENCES `seaports`.`data_element` (`id` )

  ON DELETE CASCADE

  ON UPDATE CASCADE;

