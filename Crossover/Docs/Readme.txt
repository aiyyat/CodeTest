- Instructions to install and configure any prerequisites or dependencies
 
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
-----------------------------------------------------------------------------------------------------------------------------
- Any constructive feedback for improving the assignment
-----------------------------------------------------------------------------------------------------------------------------
