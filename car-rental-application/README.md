# Car Rental Application

## Description
Car Rental Application is an exemplary microservices application developed for research purposes. This microservices architecture includes modern microservices features like Discovery Server, Config Server and API Gateway. The application works in a discovery-first paradigm. All the microservices are build in Kotlin with Springboot framework.

From the business perspective, this is a simple application that serves car rent queries with demo data.

## Services
- Discovery Service
  - Acts as the registry service. All the other services register themselves with this service. The registered services are discoverable by the other services.
- Config Service
  - Holds the configuration for other services other than Discovery Service (discovery-first design)
- API Gateway
  - Works as the entrypoint for the exposed REST APIs. Delegates the incoming requests to the corresponding services.
- Car Service
  - A business service that processes and serves car queries.
- Location Service
  - A business service that processes and serves location queries.
- Rent Service
  - A business service that processes and serves rent queries.

## Deployment
The services are deployable as Springboot applications.

### Prerequisite
- RabbitMQ server

### Deployment Order
```
Discovery Service -> Config Service -> Car Service -> Location Service -> Rent Service -> API Gateway 
```

The code base includes the environment files for the services.

#### Discovery Service
```
~/discovery-service/.env
```
####  Config Service
```
~/config-service/.env
```
####  API Gateway
```
~/api-gateway/.env
```
####  Car Service
```
~/car-service/.env
```
####  Location Service
```
~/location-service/.env
```
####  Rent Service
```
~/rent-service/.env
```

## Testing
The codebase includes a Postman request collection for testing.
```
~/Car Rental Application API Requests.postman_collection.json
```