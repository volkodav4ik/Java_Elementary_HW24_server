Hello, Friend! 

It is a web application for work with simple database. 
You can do basic CRUD requests for Object User, which has 3 value fields:
1) id
2) name
3) age

Base URL depends on server, where webapp was deployed. Code name for it 
will be BASE_URL. 

All type of requests you can do:

1) Add User to DataBase: 
Do @POST request with body which contains json String of adding User. 
Url = BASE_URL + "/add"

2) Get User from DataBase by its name: 
Do @POST request with body which contains json String of User (field "name" mustn't be empty!). 
Url = BASE_URL + "/get/name"

3) Get User from DataBase by its id: 
Do @POST request with body which contains json String of User (field "id" mustn't be empty!). 
Url = BASE_URL + "/get/id"

4) Get all User from DataBase: 
Do @GET request
Url = BASE_URL + "/get/all"

5) Update User in DataBase:
Do @POST request with body which contains json String of User (field "id" mustn't be empty!).
Url = BASE_URL + "/update"

6) Delete User from DataBase by its name:
Do @POST request with body which contains json String of User (field "name" mustn't be empty!). 
Url = BASE_URL + "/delete/name"

6) Delete User from DataBase by its id:
Do @POST request with body which contains json String of User (field "id" mustn't be empty!). 
Url = BASE_URL + "/delete/id"