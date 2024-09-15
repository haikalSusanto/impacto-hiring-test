# Docker compose commands
up:
	@docker-compose up -d
	@echo "Docker containers are up."

down:
	@docker-compose down
	@echo "Docker containers are stopped."

build:
	mvn clean install

run:
	mvn spring-boot:run	
