# Changelog
All notable changes will be logged here

## [Unreleased] 0.0.1 - 2017-10-16
 Added  
 - Changelog.md file  

 ## Version 1 1.0.0 - 2017-10-30
 Added  
  - Front End Components: Login, Dashboard, Session
  - Back End Components: Mysql database, create User endpoint, return Users endpoint

 Changed  
  - Role of App.js

## Version 1 1.1.0 - 2017-11-08
 Added  
  - Front End Components: NewSession, NewSessionDraft
  - Back End Components: Round robin algorithm initial draft, QUANDL stock data query

 Changed  
  - Button and display format enhancements
  
  Removed
  - Roster object, consolidated it as a portfolio object with long/short/bench states

## Version 1 1.2.0 - 2017-11-17
 Added  
  - Front End Components: mockService, currentService and realService
  - Back End Components: Portfolio scoring algorithm set up and implementation
  - Added Sessions and Portfolios and related endpoints
  - Made database queries safe

 Changed  
  - The way we store data by adding a relation to the database
