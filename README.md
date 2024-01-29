# ReadMe

This is a demo app that calculates mean value of db entities within given time interval.

## To start the application:
1. CLONE repo: `git clone https://github.com/jihudus/meanservice.git`
2. Go into docker directory: `cd meanservice/docker`
3. Run docker compose: `docker compose up -d --build meanservice`

## To send a request:

Swagger ui page is [available](http://localhost:8800/mean/swagger.html)

For test purposes some data added into db, you can check it in [V2__ADD_TEST_DATA.sql](src/main/resources/db/migration/V2__ADD_TEST_DATA.sql)

Address to request: **http://localhost:8800/mean/{uuid}**

Here `uuid` relates to `client.id` or `receipt.client_id` field on db.

Parameters needed: 
+ `fromDate` - start of period to calculate
+ `tillDate` - end of period (will be today if not present)

+ If dates are formatted correctly (yyyy-mm-dd): response has status `200`, body represents string value of BigDecimal.
+ If date has incorrect format: status `400`
+ If tillDate is before fromDate: status `400`

## To run the tests
1. Go to main app directory
2. run command `./mvnw test`
There are 4 unit tests to examine controller and service methods.
