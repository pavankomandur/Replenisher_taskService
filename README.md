Installation
1. Download either zip folder or through Github  by cloning or forking.
2. Open project in any IDE such as Eclipse or IntelliJ etc.

The Following are the Softwares required for this Project
1. Java 8
2. Tomcat 8.0 or higher
3. Spring boot 2.x
4. Spring Data
5. Mysql 8.0
6. Angular 4 or higher

I have given 8880 as port while installing mysql. if you have different port, please change it in properties file.

Design
Followed MVC architecture for loose coupling and to ease to add/remove any other componenet based on requirement. 
Following are the modules/packages I have used for this application.
1.	Controllers
2.	Model Objects
3.	DAO(Repositories)
4.	Services
5.	Utils.
6.	Chron Service(Schedular): Chron and TimerJob
7.	Exceptional Handling is done at centralized level.
8.  Logs
9.  Spring Security(Basic) : Due to Time Constraint, otherwise would have implemented either jwt token or oauth

Front end Pages are developed and Integrated with backend, but still work needs to be done from frond end side on alignment, routing  and Menu

Application Features
1.	User can be able to create tasks
2.	Business user can create tasks and assign to him or assign to his reporters using Task Template
3.	Option to create a tasks on a basis of frequency/schedule : Recurring Tasks(only for business users)
4.	Display of pending tasks and also based on Rank and priority
5.	Tracking Time for each status : Coding is done, but display on UI is still in progress.

Right now security code is commented out in the controller in order to execute from post man

The below could not be done due to time constraint.
TODO
1.	Integration of Security Module with Angular.(Coding is done from Backend)
2.	Minor Validations in backend code.
3.	Only important Test Cases are written, but still more needs to be done.



