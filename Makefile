
build-run: build-image run-app

build-image:
	export DOCKER_BUILDKIT=1 && \
  	export COMPOSE_DOCKER_CLI_BUILD=1 && \
	docker build -t beer-stock-api .

run-db: stop-db rm-db
	docker run --name beer_stock_mysqldb -p 3306:3306 \
	-e MYSQL_RANDOM_ROOT_PASSWORD=yes \
	-e MYSQL_USER=dev \
	-e MYSQL_PASSWORD=dev \
	-e MYSQL_DATABASE=beer_stock \
	-d library/mysql:8.0.23

run-app: stop-app rm-app
	docker run --name beer_stock_api -p 8080:8080 -d \
	-e SPRING_DATASOURCE_URL=jdbc:mysql://beer_stock_mysqldb:3306/beer_stock \
	-e SPRING_DATASOURCE_USERNAME=dev -e SPRING_DATASOURCE_PASSWORD=dev \
	-e SPRING_DATASOURCE_DRIVERCLASSNAME=com.mysql.cj.jdbc.Driver \
	-e SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQL8Dialect \
	-e SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT_STORAGE_ENGINE=innodb \
	--link beer_stock_mysqldb:beer_stock_mysqldb beer-stock-api:latest

create-db:
	docker exec beer_stock_mysqldb mysql -udev -pdev -e "$(cat db/beer_stock.sql)"

stop-db:
	docker stop beer_stock_mysqldb

rm-db:
	docker rm beer_stock_mysqldb

stop-app:
	docker stop beer_stock_api

rm-app:
	docker rm beer_stock_api