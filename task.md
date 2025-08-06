# Task

## Objective
Create a JSF + PrimeFaces web application that allows users to submit, view, edit, and delete service requests. This exercise will help you get comfortable with Java web technologies and backend integration.

## Requirements

- learn Java and demonstrate codding skills
- Create a web app
- Use JSF, PrimeFaces, and MySQL
- Implement features
    - Login Page (Static credentials: admin/admin123)
    - Dashboard to display all service requests in a table
    - Add New Request Form with Title, Description, Priority, Date
    - Edit Request (Title, Description, Priority, Status)
    - Delete Request
- Use PrimeFaces components (dataTable, dialog, growl, etc.)
- Backend Setup
    - Create a MySQL database named `support_tracker` with the following table: look notes

## Goal of the Task

- Get hands-on with Java + JSF + PrimeFaces + JDBC.
    - Mast be a web page (JSF) that uses best prictice from PrimeFaces that does data manipulation in DB
- Learn how to build form-driven web applications
- Understand managed beans and basic JSF scopes.
- Work with database integration using MySQL.

## notes

### SQL table description
CREATE TABLE service_requests (
id INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(255),
description TEXT,
priority VARCHAR(10),
status VARCHAR(20),
date_submitted DATE
);

## make a review about the work

Be ready to explain your project, challenges faced, and what you learned.