# Skills in Spring, Data manipulation and REST API
This is a test to check your skills in Java, Spring Boot and REST services. 
Using your preferred tools, create an application with the following demands:

- Expose a REST service to list cities - there are database scripts included.
- Java (preferably Java 8 or greater and Functional programming as much as possible)
- JPA
- Spring-boot with a self-contained application (Container included)


# Database
The actual implementation uses H2 in memory as the database. You will find also the scripts 
for MySQL. The scripts insert a few entries in each table. We recommend using spring-initialzr to scaffold the application.

# Proposed exercise
The candidate must:
- Create Entity classes for the tables, including relationships
- Create the Data manipulation layer. Feel free to use structure or framework you like (JPA, JDBC, Spring Data, etc).
- Create a GET REST service to retrieve the list of cities in the database, and return them as a JSON object.
- The service may receive the query param "country" as a String, to restrict the search. The parameter may be part of the Country name

   http://server:port/rest/cities[?country=name]

Optional challenges:
- Create an operation to load data into the database (Here you are free to be creative. You can load data from a simple CSV, a spreadsheet, a rest service, etc)
- Use Gradle

# Expected results
After the implementation, the application should run after the following command line:

	java -jar target/spring-jpa-jersey.jar
    
    
Then, open a browser and type :

    http://localhost:8090/rest/cities?country=Uni


It must return, at least the following (ids may vary) :

    [
        {
            "id":86,
            "name":"New York",
            "country":{
                "id":2,
                "name":"United States"
            }
        },
        {
            "id":87,
            "name":"Los Angeles",
            "country":{
                "id":2,
                "name":"United States"
            }
        },
        {
            "id":88,
            "name":"Atlanta",
            "country":{
                "id":2,
                "name":"United States"
            }
        }
    ]


# Unit tests

Create unit tests with targeting code coverage

## Challenges for tests

- Include the percentage of line covered by tests of your application - Use Jacoco plugin.
- Include an integration test.
