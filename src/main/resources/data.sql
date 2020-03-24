DROP TABLE IF EXISTS project;

CREATE TABLE `project` (
	`project_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`requirements` VARCHAR(2500) DEFAULT '',
	`description` VARCHAR(2500) DEFAULT '',
	`maximum_budget` INT DEFAULT '0',
	`last_allowed_bid_date` TIMESTAMP NOT NULL,
	`contractor_name` VARCHAR(2500) DEFAULT 'NOT_FINALIZED',
	PRIMARY KEY (`project_id`)
);

INSERT INTO project (requirements, description, maximum_budget, last_allowed_bid_date) VALUES('requirements', 'description', 1234567, '2020-03-23T00:00:00.000');

DROP TABLE IF EXISTS bid;

CREATE TABLE `bid` (
	`bid_id` INT NOT NULL AUTO_INCREMENT,
	`project_id` INT NOT NULL,
	`bid_amount` INT DEFAULT '0',
	`bid_date` TIMESTAMP NOT NULL,
	`bid_won` BOOLEAN DEFAULT 'false',
	`contractor_name` VARCHAR(2500) DEFAULT '',
	`bid_status` VARCHAR(2500) DEFAULT 'NOT_FINALIZED',
	PRIMARY KEY (`bid_id`)
);
