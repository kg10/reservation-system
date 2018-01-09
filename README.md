# reservation-system

At the beginning build *.jar file.

Next build, a docker images by (name has was defined in file docker-compose.yml):
	docker build -t [name] .

At the end run containers by:
	docker-compose up
	
Default IP address: 
http://192.168.99.100:8080

ZUUL - port: 8073
Eureka - port: 8070
Service 1 - port: 8071
Service 2 - port: 8072 


