# Radix Tech Assessment
## Prerequisites
#### Before running the application, ensure you have the following installed:

* Java Development Kit (JDK): Version 17 or higher.
* Maven: For building the project.
* Postman or cURL: For testing the APIs. 
* Git: For cloning the repository.

## Build and Run the Application
### Clone the Repository

> git clone https://github.com/thabolinake/radix-tech-assessment.git

> cd radix-tech-assessment

### Build the Application

#### To build the application, run:

> mvn clean install

### Run the Application

#### To start the application, run:

> mvn spring-boot:run

#### The application will start on port 8095. You can access it at:

http://localhost:8095

## API Documentation
### The application provides the following APIs:

#### Create a Loan
**Endpoint**: *POST /api/loans*

###### Request Body:
```json
{
  "amount": 1580.0,
  "term": 6
}
```

###### Response:

```json
{
  "id": 1,
  "amount": 1580.0,
  "term": 6,
  "balanceAmount": 1229.5,
  "createdAt": "2025-03-17T07:20:05.15887"
}
```

***

#### Get All Loans
**Endpoint**: *GET /api/loans*

###### Response:

```json
[
  {
    "id": 1,
    "amount": 1580.0,
    "term": 6,
    "balanceAmount": 1229.5,
    "createdAt": "2025-03-17T07:20:05.15887"
  },
  {
    "id": 2,
    "amount": 2580.0,
    "term": 12,
    "balanceAmount": 2580.0,
    "createdAt": "2025-03-17T07:25:27.183585"
  },
  {
    "id": 3,
    "amount": 3500.0,
    "term": 24,
    "balanceAmount": 3500.0,
    "createdAt": "2025-03-17T07:38:35.725935"
  }
]
```

***

#### Get a Loan by ID
**Endpoint**: *GET /api/loans/{id}*

###### Response:

```json
{
  "id": 1,
  "amount": 1580.0,
  "term": 6,
  "balanceAmount": 1229.5,
  "createdAt": "2025-03-17T07:20:05.15887"
}
```

***

#### Get a Loan Payments by loanId {id}
**Endpoint**: *GET /api/loans/{id}/payments*

###### Response:

```json
[
  {
    "id": 1,
    "amount": 250.0,
    "createdAt": "2025-03-17T09:45:19.106398",
    "loan": {
      "id": 1,
      "amount": 1580.0,
      "term": 6,
      "balanceAmount": 1229.5,
      "createdAt": "2025-03-17T07:20:05.15887"
    }
  },
  {
    "id": 2,
    "amount": 100.5,
    "createdAt": "2025-03-17T09:45:28.44459",
    "loan": {
      "id": 1,
      "amount": 1580.0,
      "term": 6,
      "balanceAmount": 1229.5,
      "createdAt": "2025-03-17T07:20:05.15887"
    }
  }
]
```

#### Create a Payment
**Endpoint**: *POST /api/payments*

###### Request Body:
```json
{
  "amount": 100.50,
  "loan": {
    "id": 1
  }
}
```

###### Response:

```json
{
  "id": 2,
  "amount": 100.5,
  "createdAt": "2025-03-17T09:45:28.44459",
  "loan": {
    "id": 1,
    "amount": 1580.0,
    "term": 6,
    "balanceAmount": 1229.5,
    "createdAt": "2025-03-17T07:20:05.15887"
  }
}
```

***

#### Get All Payments
**Endpoint**: *GET /api/payments*

###### Response:

```json
[
  {
    "id": 1,
    "amount": 250.0,
    "createdAt": "2025-03-17T09:45:19.106398",
    "loan": {
      "id": 1,
      "amount": 1580.0,
      "term": 6,
      "balanceAmount": 1229.5,
      "createdAt": "2025-03-17T07:20:05.15887"
    }
  },
  {
    "id": 2,
    "amount": 100.5,
    "createdAt": "2025-03-17T09:45:28.44459",
    "loan": {
      "id": 1,
      "amount": 1580.0,
      "term": 6,
      "balanceAmount": 1229.5,
      "createdAt": "2025-03-17T07:20:05.15887"
    }
  }
]
```

## Testing the APIs
### You can test the APIs using Postman or cURL.

#### Using cURL
##### Here are some example cURL commands:

###### Create a Loan

``` json
curl -X POST http://localhost:8095/api/loans \
-H "Content-Type: application/json" \
-d '{
"amount": 1500.0,
"term": 12
}'
```

###### Get a Loan by ID
``` json
curl -X GET http://localhost:8095/api/loans/1
```

###### Get All Loans
``` json 
curl -X GET http://localhost:8095/api/loans
```

###### Create a Loan Payment
``` json
curl -X POST http://localhost:8095/api/payments \
-H "Content-Type: application/json" \
-d '{
	"amount": 100.50,
	"loan": {
		"id": 1
	}
}'
```

###### Get All Payments
``` json 
curl -X GET http://localhost:8095/api/payments
```

### Configuration
#### The application uses the following configuration:

#### Application Properties
>Server Port: 8095

>Database: H2 in-memory database

>H2 Console: Enabled at http://localhost:8095/h2-console

#### H2 Database Console
##### To access the H2 database console:

> Go to http://localhost:8095/h2-console.

##### Use the following credentials:
> JDBC URL: jdbc:h2:mem:radixdb

> Username: linake

> Password: pass
