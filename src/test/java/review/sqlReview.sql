-- select all columns from EMPLOYEES table
select * from EMPLOYEES;

-- select only the FIRST_NAME column from EMPLOYEES table
select FIRST_NAME from EMPLOYEES;

-- select specific columns (FIRST_NAME, LAST_NAME, SALARY) from EMPLOYEES table
select FIRST_NAME, LAST_NAME, SALARY from EMPLOYEES;

-- select distinct values of FIRST_NAME from EMPLOYEES table
select distinct FIRST_NAME from EMPLOYEES;

-- select FIRST_NAME where it equals 'David'
select FIRST_NAME from EMPLOYEES where FIRST_NAME = 'David';

-- select FIRST_NAME where it is not 'David'
select FIRST_NAME from EMPLOYEES where FIRST_NAME != 'David';

-- select FIRST_NAME where it is not equal to 'David'
select FIRST_NAME from EMPLOYEES where FIRST_NAME <> 'David';

-- select SALARY where SALARY is greater than or equal to 10000
select SALARY from EMPLOYEES where SALARY >= 10000;

-- select SALARY where SALARY is between 5000 and 9000
select SALARY from EMPLOYEES where SALARY > 5000 and SALARY < 9000;

-- select SALARY where SALARY is greater than 5000 and less than 4000 (impossible condition)
select SALARY from EMPLOYEES where SALARY > 5000 and SALARY < 4000;

-- select SALARY where SALARY is 10000 or 9000
select SALARY from EMPLOYEES where SALARY = 10000 or SALARY = 9000;

-- select SALARY where SALARY is one of 10000, 9000, 5000, 6000
select SALARY from EMPLOYEES where SALARY in (10000, 9000, 5000, 6000);

-- select FIRST_NAME where it is 'David' or 'Steven'
select FIRST_NAME from EMPLOYEES where FIRST_NAME in ('David', 'Steven');

-- select FIRST_NAME where it is not 'David', 'Steven', or 'Ellen'
select FIRST_NAME from EMPLOYEES where FIRST_NAME not in ('David', 'Steven', 'Ellen');

-- select SALARY from EMPLOYEES, ordered by SALARY in ascending order
select SALARY from EMPLOYEES order by SALARY;

-- select SALARY from EMPLOYEES, ordered by SALARY in descending order
select SALARY from EMPLOYEES order by SALARY desc;

-- select FIRST_NAME from EMPLOYEES, ordered by FIRST_NAME in ascending order
select FIRST_NAME from EMPLOYEES order by FIRST_NAME;

-- select FIRST_NAME from EMPLOYEES, ordered by FIRST_NAME in descending order
select FIRST_NAME from EMPLOYEES order by FIRST_NAME desc;

-- select FIRST_NAME, LAST_NAME from EMPLOYEES, ordered by FIRST_NAME in descending order then LAST_NAME in ascending order
select FIRST_NAME, LAST_NAME from EMPLOYEES order by FIRST_NAME desc, LAST_NAME asc;

-- count the total number of rows in EMPLOYEES table
select count(*) from EMPLOYEES;

-- count the number of non-null values in COMMISSION_PCT column of EMPLOYEES table
select count(COMMISSION_PCT) from EMPLOYEES;


-- select all COMMISSION_PCT values from EMPLOYEES table
select COMMISSION_PCT from EMPLOYEES;

-- select FIRST_NAME where it starts with 'M'
select FIRST_NAME from EMPLOYEES where FIRST_NAME like 'M%';

-- select LAST_NAME where it ends with 'et'
select LAST_NAME from EMPLOYEES where LAST_NAME like '%et';

-- select JOB_ID where it has any value
select JOB_ID from EMPLOYEES where JOB_ID like '%';

-- select FIRST_NAME where it contains the letter 'v'
select FIRST_NAME from EMPLOYEES where FIRST_NAME like '%v%';

-- select FIRST_NAME where it has exactly 5 characters
select FIRST_NAME from EMPLOYEES where FIRST_NAME like '_____';

-- select FIRST_NAME where it starts with 'D' and has exactly 7 characters
select FIRST_NAME from EMPLOYEES where FIRST_NAME like 'D______';

-- select FIRST_NAME where it starts with 'D' and has more than 3 characters
select FIRST_NAME from EMPLOYEES where FIRST_NAME like 'D___%';

-- select COMMISSION_PCT where it has any value
select COMMISSION_PCT from EMPLOYEES where COMMISSION_PCT like '%';

-- select the maximum value of SALARY from EMPLOYEES table
select max(SALARY) from EMPLOYEES;

-- select the minimum value of COMMISSION_PCT from EMPLOYEES table
select min(COMMISSION_PCT) from EMPLOYEES;

-- select the average value of SALARY from EMPLOYEES table
select avg(SALARY) from EMPLOYEES;

-- select the rounded average value of SALARY from EMPLOYEES table
select round(avg(SALARY)) from EMPLOYEES;

-- select the rounded average value of SALARY from EMPLOYEES table with 2 decimal places
select round(avg(SALARY), 2) from EMPLOYEES;

-- select the sum of all COMMISSION_PCT values from EMPLOYEES table
select sum(COMMISSION_PCT) from EMPLOYEES;

-- select the rounded sum of all COMMISSION_PCT values from EMPLOYEES table
select round(sum(COMMISSION_PCT)) from EMPLOYEES;

-- select JOB_ID and count of occurrences of each JOB_ID from EMPLOYEES table
select JOB_ID, count(JOB_ID) from EMPLOYEES group by JOB_ID;

-- select MANAGER_ID and average SALARY for each MANAGER_ID from EMPLOYEES table where average SALARY is greater than 5000
select MANAGER_ID, avg(SALARY) from EMPLOYEES group by MANAGER_ID having avg(SALARY) > 5000;

-- select JOB_ID and sum of SALARY for each JOB_ID from EMPLOYEES table where sum of SALARY is less than 7000
select JOB_ID, sum(SALARY) from EMPLOYEES group by JOB_ID having sum(SALARY) < 7000;

-- select FIRST_NAME from EMPLOYEES table with row number less than or equal to 10
select FIRST_NAME from EMPLOYEES where ROWNUM <= 10;

-- select FIRST_NAME and SALARY from EMPLOYEES table where SALARY is less than the average SALARY
select FIRST_NAME, SALARY from EMPLOYEES where SALARY < (select avg(SALARY) from EMPLOYEES);

-- select the maximum SALARY value from EMPLOYEES table excluding the highest SALARY
select max(SALARY) from EMPLOYEES where SALARY != (select max(SALARY) from EMPLOYEES);

-- select the top 15 distinct SALARY values from EMPLOYEES table in descending order
select SALARY from (select distinct SALARY from EMPLOYEES order by SALARY desc) where ROWNUM <= 15;

-- select concatenation of FIRST_NAME, LAST_NAME, and '@gmail.com' as fullEmail from EMPLOYEES table
select FIRST_NAME||LAST_NAME||'@gmail.com' as fullEmail from EMPLOYEES;

-- select uppercased FIRST_NAME from EMPLOYEES table
select upper(FIRST_NAME) from EMPLOYEES;

-- select lowercased LAST_NAME from EMPLOYEES table
select lower(LAST_NAME) from EMPLOYEES;

-- select FIRST_NAME and its length from EMPLOYEES table
select FIRST_NAME, length(FIRST_NAME) from EMPLOYEES;

-- select FIRST_NAME with its first character capitalized from EMPLOYEES table
select initcap(EMAIl) from EMPLOYEES;

-- select substring of FIRST_NAME from position 1 to 3 from EMPLOYEES table
select FIRST_NAME, substr(FIRST_NAME,1,3) from EMPLOYEES;

-- create a view named newEmail1 with the EMAIL column from EMPLOYEES table
create view newEmail1 as select EMAIL as email from EMPLOYEES;

-- create a view named realEmail with concatenation of FIRST_NAME, '.', LAST_NAME, and '@gmail.com' as realEmail from EMPLOYEES table
create view realEmail as select FIRST_NAME||'.'||LAST_NAME||'@gmail.com' as realEmail from EMPLOYEES;

-- join EMPLOYEES and DEPARTMENTS tables on DEPARTMENT_ID
select JOB_ID, DEPARTMENTS.MANAGER_ID from EMPLOYEES inner join DEPARTMENTS on EMPLOYEES.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID;

-- select FIRST_NAME, LAST_NAME, and DEPARTMENT_NAME from EMPLOYEES and DEPARTMENTS tables where employees are from 'Shipping' department
select FIRST_NAME, LAST_NAME, DEPARTMENT_NAME from EMPLOYEES left join DEPARTMENTS on EMPLOYEES.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID where DEPARTMENT_NAME='Shipping';

-- select FIRST_NAME and END_DATE from EMPLOYEES and JOB_HISTORY tables
select FIRST_NAME, END_DATE from EMPLOYEES right join JOB_HISTORY on EMPLOYEES.EMPLOYEE_ID = JOB_HISTORY.EMPLOYEE_ID;

-- select DEPARTMENT_NAME and CITY from DEPARTMENTS and LOCATIONS tables where CITY is 'Seattle'
select DEPARTMENT_NAME, CITY from DEPARTMENTS d full join LOCATIONS l on d.LOCATION_ID = l.LOCATION_ID where CITY='Seattle';

-- select employees' FIRST_NAME, LAST_NAME, their manager's FIRST_NAME, LAST_NAME
select e1.EMPLOYEE_ID, e1.FIRST_NAME, e1.LAST_NAME, e1.MANAGER_ID, e2.EMPLOYEE_ID from EMPLOYEES e1 join EMPLOYEES e2 on e1.MANAGER_ID=e2.EMPLOYEE_ID;

-- create a table named Teachers with columns T_id, T_name, T_branch
create table Teachers(T_id integer primary key , T_name char(40), T_branch char(40));

-- create a table named Students with columns S_id, S_name, S_branch
create table Students(S_id integer primary key , S_name char(40), S_branch char(40));

-- insert values into Teachers table
insert into TEACHERS values (1,'Jamal','Automation');
insert into TEACHERS values (2,'Muhtar','Java');
insert into TEACHERS values (3,'Oscar','API');

-- insert values into Students table
insert into STUDENTS values (1,'Ahmet','Java');
insert into STUDENTS values (2,'Jenifer','API');
insert into STUDENTS values (3,'Suarez','Automation');

-- update S_BRANCH to 'Database' where S_ID is 1 in Students table
update Students set S_BRANCH = 'Database' where S_ID = 1;

-- delete the row where S_ID is 2 from Students table
delete Students where S_ID = 2;

-- add a new column named Salary to Teachers table
alter table Teachers add Salary integer;

-- rename the column T_name to name in Teachers table
alter table Teachers rename column T_name to name;

-- rename the table Students to allStudents
alter table Students rename to allStudents;

-- delete all rows from Students table
truncate table Students;

-- drop the table TEACHERS
drop table TEACHERS;

-- Selecting unique first names and last names from the EMPLOYEES table and combining them into a single result set.
-- The UNION operator removes duplicate values.
select FIRST_NAME from EMPLOYEES union select LAST_NAME from EMPLOYEES;

-- Selecting first names from the EMPLOYEES table, including duplicates, and appending last names from the EMPLOYEES table.
-- The UNION ALL operator does not remove duplicate values.
select FIRST_NAME from EMPLOYEES union all select LAST_NAME from EMPLOYEES;

-- Selecting salaries from the EMPLOYEES table except for the commission percentages.
-- The MINUS operator returns rows from the first query that are not present in the second query.
select SALARY from EMPLOYEES minus select COMMISSION_PCT from EMPLOYEES;

-- Selecting employee IDs that intersect with manager IDs from the EMPLOYEES table.
-- The INTERSECT operator returns common rows between the two queries.
select EMPLOYEE_ID from EMPLOYEES intersect select MANAGER_ID from EMPLOYEES;


--UNION -> combines, removes duplicates, sorts
--UNION ALL-> combines, does not remove duplicates, does not sort
--MINUS -> show records from query1 that are not present in query2
--INTERSECT -> show common records from




