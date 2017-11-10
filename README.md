#Skills in Spring, Data manipulation and JAX RS

All the libraries used on this porject were updated to lastest one.
Check pom.xml for details.

# Database
Database used was H2 in memory.

# Proposed exercise

 Four rest services were created:

1) GET REST service to retrieve the list of cities in the database by country name (Using query parameter).
2) GET REST service to retrieve the list of countries in the database by country name or part of it.
3) POST REST service to store new countries on the database
4) POST REST service to store new cities on the database (For this service execution, number 3 should be executed to ensure the new country in the database. If the country has been already in the database, just execute service number 2 to get country information).


# Expected results
Please, start up the project accoring to the command below: 

    mvn spring-boot:run
    

# Unit tests

Unit tests were created and this application is availabled with 82,3% of coverage.

#Examples of JSON request for POST Service

POST REST service to store new cities on the database

{"name":"Amapa", 
    "country":{
        "id":"1",
        "name":"Brazil"
    }
}

POST REST service to store new countries on the database

{"name":"Guine"}


