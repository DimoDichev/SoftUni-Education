-- 1
SELECT 
    title
FROM
    books
WHERE
    SUBSTRING(title, 1, 3) = 'The'
ORDER BY id;


-- 2
SELECT 
    REPLACE(title, 'The', '***')
FROM
    books
WHERE
    SUBSTRING(title, 1, 3) = 'THE'
ORDER BY id;


-- 3
SELECT 
    ROUND(sum(cost), 2)
FROM
    books;


-- 4
SELECT 
    CONCAT_WS(' ', first_name, last_name) AS 'Full Name',
    TIMESTAMPDIFF(day, born, died) AS 'Days Lived'
FROM
    authors;


-- 5
SELECT 
    title
FROM
    books
WHERE
    title LIKE 'Harry Potter%';
