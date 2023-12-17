-- 1
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
	SELECT 
		first_name,
		last_name
	FROM employees
	WHERE salary > 35000
	ORDER BY first_name, last_name, employee_id;
END $$
DELIMITER ;
;


-- 2
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(salary_above DECIMAL(19,4))
BEGIN
	SELECT
		first_name,
		last_name
	FROM employees
	WHERE salary >= salary_above
	ORDER BY first_name, last_name, employee_id;
END $$
DELIMITER ;
;


-- 3
DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(start_string VARCHAR(50))
BEGIN
	SELECT 
		`name`
	FROM towns
	WHERE `name` LIKE CONCAT(start_string, '%')
    ORDER BY `name`;
END $$
DELIMITER ;
;


-- 4
DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
	SELECT 
		e.first_name,
		e.last_name
	FROM employees AS e
		JOIN addresses AS a ON e.address_id = a.address_id
		JOIN towns AS t ON a.town_id = t.town_id
    WHERE t.name = town_name
	ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;
;


-- 5
DELIMITER $$
CREATE FUNCTION ufn_get_salary_level(salary DECIMAL(19,4))
RETURNS VARCHAR(7)
DETERMINISTIC
BEGIN
	DECLARE salary_level VARCHAR(7);
    CASE
		WHEN salary < 30000 THEN SET salary_level = 'Low';
		WHEN salary <= 50000 THEN SET salary_level = 'Average';
        ELSE SET salary_level = 'High';
	END CASE;
    RETURN salary_level;
END $$
DELIMITER ;
;


-- 6
DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(level_of_salary VARCHAR(7))
BEGIN
	SELECT 
		first_name,
		last_name
	FROM employees
	WHERE ufn_get_salary_level(employees.salary) = level_of_salary
	ORDER BY first_name DESC, last_name DESC;
END $$
DELIMITER ;
;


-- 7
DELIMITER $$
CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN word REGEXP CONCAT('^[', set_of_letters, ']+$');
END $$
DELIMITER ;
;


-- 8
DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	SELECT
		concat_ws(' ', first_name, last_name) AS 'full_name'
	FROM account_holders
	ORDER BY `full_name`, id;
END $$
DELIMITER ;
;


-- 9
DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(balance DECIMAL(19,4))
BEGIN
	SELECT 
		ah.first_name,
		ah.last_name
	FROM account_holders AS ah
		JOIN accounts AS a ON ah.id = a.account_holder_id
	GROUP BY ah.id
	HAVING SUM(a.balance) > balance
	ORDER BY ah.id;
END $$
DELIMITER ;
;


-- 10
DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(initial_sum DECIMAL(19,4), interest_rate DECIMAL(19,4), years INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN
	RETURN initial_sum * (POW((1 + interest_rate), years));
END $$
DELIMITER ;
;


-- 11
DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(id INT, interest_rate DECIMAL(19,4))
BEGIN
	SELECT 
		a.id AS 'account_id',
		ah.first_name,
		ah.last_name,
		a.balance AS 'current_balance',
		ufn_calculate_future_value(a.balance, interest_rate, 5) AS 'balance_in_5_years'
		FROM accounts AS a
			JOIN account_holders AS ah ON a.account_holder_id = ah.id
		WHERE a.id = id;
END $$
DELIMITER ;
;


-- 12
DELIMITER $$
CREATE PROCEDURE usp_deposit_money(account_id int, money_amount DECIMAL(19,4))
BEGIN
	IF (money_amount > 0) THEN
		START TRANSACTION;
        UPDATE accounts SET balance = balance + money_amount
        WHERE id = account_id;
	END IF;
END $$
DELIMITER ;
;


-- 13
DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(19,4))
BEGIN
	IF (money_amount > 0)
		AND ((SELECT balance FROM accounts WHERE id = account_id) >= money_amount)
        THEN
		START TRANSACTION;
        UPDATE accounts SET balance = balance - money_amount
        WHERE id = account_id;
	END IF;
END $$
DELIMITER ;
;


-- 14
DELIMITER $$
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19,4))
BEGIN
	IF (amount > 0)
	AND ((SELECT balance FROM accounts WHERE id = from_account_id) >= amount)
    AND ((SELECT id FROM accounts WHERE id = from_account_id) IS NOT NULL)
    AND ((SELECT id FROM accounts WHERE id = to_account_id) IS NOT NULL)
    AND (from_account_id != to_account_id) THEN
		START TRANSACTION;
		UPDATE accounts SET balance = balance - amount
		WHERE id = from_account_id;
        UPDATE accounts SET balance = balance + amount
		WHERE id = to_account_id;
	END IF;
END $$
DELIMITER ;
;


-- 15
CREATE TABLE `logs`(
	log_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    old_sum DECIMAL(19,4),
    new_sum DECIMAL(19,4)
);

DELIMITER $$
CREATE TRIGGER tr_logs_accounts
AFTER UPDATE
ON accounts
FOR EACH ROW
BEGIN
	IF OLD.balance != NEW.balance THEN
		INSERT INTO logs(account_id, old_sum, new_sum) 
		VALUES (NEW.id, OLD.balance, NEW.balance);
    END IF;
END $$
DELIMITER ;
;


-- 16
CREATE TABLE notification_emails(
id INT PRIMARY KEY AUTO_INCREMENT,
recipient INT,
subject VARCHAR(100),
body TEXT
);


DELIMITER $$
CREATE TRIGGER tr_create_email
AFTER INSERT
ON `logs`
FOR EACH ROW
BEGIN
	INSERT INTO notification_emails(recipient, subject, body)
    VALUES (
    NEW.account_id,
    CONCAT('Balance change for account: ', NEW.account_id),
    CONCAT_WS(' ',
		'On',
        DATE_FORMAT(NOW(), '%b %d %Y'),
        'at',
        DATE_FORMAT(NOW(), '%r'),
        'your balance was changed from',
        NEW.old_sum,
        'to',
        NEW.new_sum
    ));
END $$
DELIMITER ;
;