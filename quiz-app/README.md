Quiz Applications : 

	1. User will login from the login screen.
	2. Displayed with the set of questions and multiple choice answers for each question.
	3. After selecting the answers, user can navigate Previous and Next screens.
	4. After submitting the final Answer, user will be displayed with the results.

Prerequisites :

	2. Node JS

Technologies/Frameworks :

	1. Angular 7 (with Karma and Jasmnine for Unit Testing)

Installing Angular

	1. npm install
	2. npm install -g @angular/cli
	3. npm install -g @angular/common
	4. npm install -g @angular/http

Create Angular Quiz Application

	1. npm new quiz-app
	2. npm generate login
	3. npm generate test
	4. npm generate result

Unit Test Angular Quiz Application

	1. ng test quiz-app

Start Quiz Angular Application

	1. npm start

Quiz Application URL

	1. http://localhost:4200/login 

Application Configued Logins in server code, modify any changes required in : \src\main\resources\scripts\insert-data.sql
	
	1. tom/123
	2. peter/123
	3. alex/123


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

Refer Quiz_Application.docx for application screens.


	
