.PHONY: all
all: 
	mvn package && java -jar target/fantasyStocks-1.0-SNAPSHOT.jar

.PHONY: db
db:
	mysql.server start
	mysqladmin -u root password admin
	mysqladmin -u root -padmin create fantasy_stocks

.PHONY: test
test:
	mvn test

.PHONY: clean
clean:
	mysqladmin -u root -padmin drop fantasy_stocks
	mysql.server stop

