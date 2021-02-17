# Customer Phone Number
Is a microservice for managing and activating customer phone number.

### Setup Database locally
run mysql locally
1. Create database with name test
2. create user with name testuser and password testuser

### Building Package.
./gradlew clean build

### Running application in debug mode
./gradlew bootRun --debug-jvm

### Running jacoco test.
./gradlew jacocoTestReport

### Health check
http://localhost:8080/actuator/health

### Get All phone numbers
http://localhost:8080/getAllPhoneIds   

### Get phone Numbers by Customer id
http://localhost:8080/getPhoneNumbersByCusomterId/{customer_id} 

### Put Activate phone Number for customer 
http://localhost:8080/activatePhoneNumber/{phoneNumber} 