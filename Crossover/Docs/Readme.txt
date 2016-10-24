- Instructions to install and configure any prerequisites or dependencies

In application.properties change the following to appropriate values:
spring.mail.host=smtp.gmail.com
spring.mail.username=***********@gmail.com
spring.mail.password=*********
spring.mail.port=587

In the data.sql
INSERT INTO user(login_name, pwd, enabled, role, email_id) VALUES ('publisher2', '$2a$10$MUahUza86ErCxtsgpmMBDeR5VtoGHioRdl03/jQmkM/sk6L.Eg28e', TRUE, 'PUBLISHER','technicalyorker1@gmail.com');
the email id of the publisher/user should be changed accordingly.
-----------------------------------------------------------------------------------------------------------------------------
- Instructions to create and initialize the database

The DDL to create 'notification' table and adding a column email_id to 'user' table is in the createdb-script.sql  
The DMLs to update the email id for each user is present inside the data.sql file.
They will be executed as a part of the build.

-----------------------------------------------------------------------------------------------------------------------------
- Instructions to configure and prepare the source code to build and run properly

to build: mvn -e clean install
we just need to run the Application.java or do a 
spring-boot:run
-----------------------------------------------------------------------------------------------------------------------------
- Assumptions you have made - it is good to explain your thought process and the assumptions you have made

Assumptions made were that the first trigger of the email notification to the user doesn't happen the very first time. i.e only subsequent addition will be notified. This was done so because we dont want the user to get an entire list of journals just because this feature was being run the first time. 
Subsequently any new journal addition will be notified in the next 24 hour cycle.
-----------------------------------------------------------------------------------------------------------------------------
- Any issues you have faced while completing the assignment
Code takes a lot of time to build. Hence even unit testing (when the container is involved)  and Integration Testing is very time consuming process. Most of the time was spent on this process. We could rather have split this into multiple maven modules and have tested each as seperate services.
Since the existing testcases didn't purge the data after doing their testing there was a lot of dependency on the new testcases hence it was taking time to resolve issues on existing testcases.
Should have used embedded db from begining for testing purpose. The scripts are very mysql specific hence it would be time consuming now.
Couldn't get enough time to work on all the testcases. However I have provided atleast 1 model testcases on each layer. If given another 5-7 hours I could try to compelete the testcases.
-----------------------------------------------------------------------------------------------------------------------------
- Any constructive feedback for improving the assignment: The project requires a bit more time, something like 10-15 hours mainly because the testcases are sharing the data which is a very bad practise. Hence it requires more time to do it in this fashion. Alternately the testcases can be re-written to use in memory database, keeping data for each testcases mutually exclusive to others.
-----------------------------------------------------------------------------------------------------------------------------
