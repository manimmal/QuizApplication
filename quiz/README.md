Quiz Applications : 

	1. User will login from the login screen.
	2. Displayed with the set of questions and multiple choice answers for each question.
	3. After selecting the answers, user can navigate Previous and Next screens.
	4. After submitting the final Answer, user will be displayed with the results.

Prerequisites :

	1. Java 8
	2. Eclipse (for developer : Installed with lombok and Maven plugins)

Technologies/Frameworks :

	1. JAVA - 8
	2. Spring Boot - 2.1.5
		a. Web
		b. Security (secure micro services)
		c. JPA (ORM :Hibernate)
		d. Test (Unit Testing :JUnit, Mokito)
		e. Cache (cache metedata like questions and answers)
		f. Swagger (generate swagger api doc)
		g. jacoco (code coverage)
	3. hsqldb - 2.4.1 (user as an inmemory database)
	4. lombok - 1.2.3 (reduce coding for developer)

Maven Build Quiz Applications :generate jar

	1. mvn clean install
	
Start Quiz REST Java Application : spring boot application

	1. java -jar quiz-app-0.0.1-SNAPSHOT.jar

Application Configued Logins, modify any changes required in file : \src\main\resources\scripts\insert-data.sql
	
	1. tom/123
	2. peter/123
	3. alex/123

Spring security is configured with user name : admin and password : test

All DDL scripts are mentioned in the file : create-db.sql

Question and Answers can be modified in file : \src\main\resources\scripts\insert-data.sql

Refer swagger.json for API specifications

Application Features :

	1. Question will be shuffuled for each user
	2. Each answer selection is persisted in the database.
	3. Cached mete data, all question and answers
	4. AOP for logging
	5. Secured rest services
	6. Total test duration time displayed in the results screen.

Futute Enhancements :

	1. Test left in between will be carried from there in the next login.
	2. Selecting multiple answers for each question.
	3. External database for production application.
	4. Admin  screen to display all test attended users and results. 
	5. Configure questions and answers from Admin sceen.
