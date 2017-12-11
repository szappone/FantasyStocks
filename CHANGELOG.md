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
  - Sessions, Portfolios and related endpoints
  - Tests for get methods on DAOs
  - Made database queries safe

 Changed  
  - Implemented a relation for database storage
  
  ## Version 1 1.3.0 - 2017-12-08
 Added  
  - Front End Components: FantasyStocksBaseComponent, NavBar, official FantasyStocks Logo
  - Back End Components: hitting portfolio, matchup and stocks endpoints
  - More integration and unit testing
  - Draft phase complete

 Changed  
  - Boosted noncrud operations: round robin and scoring algorithm not supplemented with stock recommender 
  - Stock selection workflow: radio buttons rather than autocomplete to set play state (long/short/bench) at time of draft
