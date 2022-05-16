# GeofenceApi

### Instructions
  1. Created SpringBoot Application
  2. Created Geofence table in the H2 in memory database
  3. Created CRUD Operations
  4. Unit TestCases

### Steps to execute
1. ### Run Geofence Application Server
   
2. ### Login into H2 database
   Enter the URL into browser: http://localhost:8888/h2-console
   Please enter following details click test for connection successful and click connect to access SQl  
   url: jdbc:h2:mem:mydb
   username: sa
   password: password

3. ### Run Geofence API - CRUD Operations
   Run CRUD Operations through Postman and verify the results in the H2 Database
1. Create Geofence:
   Method: POST
   URL: http://localhost:8888/geofence/createGeoFence/v1
   Request Body:
   {
   "longitude": 25,
   "latitude": 4444,
   "radius": 166
   }
2. Get Geofence:
   Method: GET
   URL: http://localhost:8888/geofence/getGeoFence/v1/{id}
   QueryParam: id = 1

3. Update Geofence:
   Method: PUT
   URL: http://localhost:8888/geofence/updateGeoFence/v1/
   Request Body:
   {
   "id": 1,
   "longitude": 111111,
   "latitude": 878787,
   "radius": 15555
   }

4. Delete Geofence:
   Method: DELETE
   URL: http://localhost:8888/geofence/deleteGeoFence/v1/1

