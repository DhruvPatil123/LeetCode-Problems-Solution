# Write your MySQL query statement below
SELECT email AS Email
FROM (
    SELECT email, COUNT(email) AS email_count
    FROM Person
    GROUP BY email
) AS email_counts
WHERE email_count > 1;