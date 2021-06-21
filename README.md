---
title: EMS
category: REST
layout: 2021/sheet
tags: [Featured]
updated: 2021-06-21
keywords:
  - React
  - Spring
  - Bootstrap
  - Hibernate
  - Exceptions
---

 Full Stack Employee Management System
---------------

## Introduction
We have designed an Employee Management System. This application supports management of: 
  - Employee and Department CRUD/HTTP operations
  - Management of H2 (hibernate) database
  - Web interface implemented in React JS

This project incorporates many relevant features of a robust Full-Stack application. The final project should be a Website for the 'Washington Red Tails', which is the slam-dunk obvious name that should be adopted by the Washington football team.

### Structure
This project uses [Spring][spring] Data REST to build hypermedia-powered repositories and [React][react] to leverage easy-to-use views in JavaScript for the web application. 

### Backend
This project is managed with the [Apache Maven][maven] management tool.

`Employee.java` and `Department.java` are POJOs to model employees and departments respectively. Using Spring annotations, these models are transformed into Spring Rest domains. There are a number of department types that inherit from `Department.java` including `Leadership.java` and `Manufacturing.java`.

### Data Persistance
Data persistance has been implemented using H2 and Spring Boot to support a RESTful interface. There are two repositories found in `DepartmentRepository.java` and `EmployeeRepository.java`. Both are interfaces that extend [JpaRepository][jparepo] and that are integrated into the Spring framework and so implemented in an H2/hibernate database. *Note:* At this time, this db exists only in memory while the project is running. Ie, the db is dropped and repopulated with initial data every time the application is run.

There is also support for serializing and storing employee and department data in a plain text file `EMSstate.txt`. Functions for reading and writing to this file exist in `EMS.java` (although this functionality is more of a vestige of earlier implementations at this point).

### Frontend
The frontend for this project lives within the React framework. The CSS style sheet is a modified version of the bootstrap-based [bootswatch united][bwu], and much of the implementation of this styling is handled using [react-bootstrap] tools. Note that `yarn` is the JS dependency management tool we have chosen to use for this project.

## Usage

### Getting files from Github
If you are unclear on cloning a repository from GH, please follow the instructions [here][ghclone]. *Note:* at some time, this project may exist as a single `jar/ear` file, but this is not that time.

### Launching the Backend
To use this application with the command line, navigate to your local install of the project and run it using the `mvmn` command (`mvmn.bat` for Windows users):
```bash
cd /Directory/of/EMS
# first clean up the output directory,
# next build the project and verify the outcome
./mvnw clean verify --enable-preview 
./mvnw spring-boot:run
```
Personally, I work with this project in Eclipse, and so I use the [Spring Tools 4][Spt4] to run the project. If the project runs successfully, you should be able to use `curl` or an app like Postman to make HTTP requests to `http://localhost:8080/api/employees` and `http://localhost:8080/api/departments` (as long as you haven't configured tomcat to run on some port other than the default.)

### Launching the Frontend
To start the web application, navigate to the `js` folder and used `yarn` to build and start the applications:
```bash
cd EMS/src/main/js
yarn build
yarn start
```
By default, the web application should run on `http://localhost:3000`. *Note:* if you choose to run the application on some other port, you will need to update the `@CrossOrigins` annotation in `EmsController.java` to avoid potential CORS issues.

## Still Under Construction

* ### min max size for form
Right now the forms to create new db entries on the front end expand the width of the browser. For the dropdowns in particular, it would be nice if there was a maximum size so its easier to see the dropdown arrow.

* ### form checking
This has just been implemented for Employees in the web app, not departments

* ### implement search bar functionality
Frontend sports a navbar with a useless integrated search input
* ### make about and index pages
* ### Update department, employee
Functionality to update existing db entries
* ### interactive department creation
The nature of of different departments as separate classes that extend the same parent allows for more flexibility with future functionality on the back end. Ie, it allows for department-specific methods. However, if a department is created as is on the front end, the default `Department` constructor is used.
* ### get data from actual washington site
Might want to use python pandas or some other tool to scrape the actually players, coaches, etc. from the actual Washington football team site.
* ### login system for admin/guest
* ### spring integration
At some point, I would like to integrate the front and back ends so that starting the application launches both. For example, I might have to semi-link the front end `index.html` to the location where spring expects it on the back end.
* ### display emails
There is some un-implemented code to support 'company emails' as the employee id combined with some company domain.

<!-- Links -->
[maven]: https://maven.apache.org/
[spring]: https://spring.io/
[react]: https://reactjs.org/
[jparepo]: https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
[bwu]: https://bootswatch.com/united/
[react-bootstrap]: https://react-bootstrap.github.io/
[ghclone]: https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository
[SpT4]: https://marketplace.eclipse.org/content/spring-tools-4-aka-spring-tool-suite-4
