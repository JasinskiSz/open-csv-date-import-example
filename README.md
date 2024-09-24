# opencsv import LocalDate field example

This project aims to answer a questions regarding importing LocalDate type fields using opencsv library.

## Features

- **Import Employees** - Upload a CSV file to import employee details into the system.
- **Retrieve Employees** - Fetch the list of all employees stored in the database.

## Tech stack

- Java
- Spring Boot
- Maven
- H2 Database
- OpenCSV
- Lombok

## Getting Started

### Prerequisites

- At least Java 11
- Maven 3.6.3 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/JasinskiSz/open-csv-date-import-example.git
    cd open-csv-date-import-example
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

### Usage

#### Import

`POST /api/v1/employees` - Import employee details from a CSV file.

#### Retrieve

`GET /api/v1/employees` - Fetch the list of all employees.

### Running Tests

Run the tests using the following command:
```sh
mvn test
```

That's the minimum you need to do to get the application up and running. You can now test the application yourself with any API testing tool. I'll cover Postman in the next section.

### How to import data with a csv file?

Prepare a CSV file. You can copy my example:
```csv
First Name,Birth Date
John,1985-05-15
Jane,1990-08-25
Doe,1975-12-30
Alice,2000-01-01
Bob,1995-07-20
   ```

#### Using postman

1. Open Postman and create a new request. 
2. Set the request type to POST and enter the URL `http://localhost:8080/api/v1/employees`. 
3. Go to the Body tab and select form-data. 
4. Add a new key with the name `file` and select a CSV file containing employee details. 
5. Click on the Send button to import the employees. 
6. You can check send a GET request to `http://localhost:8080/api/v1/employees` to retrieve the list of all employees.

