# Soen387A1

## .war file
No .war file seems to be generated in this project

## Task 1 and 2: 
To Run the Servlet, run the ` Servlet1.java ` class in Eclipse. The servlet runs at ` port 8005 ` (On my PC at least). 
To access the index1.html and index2.html files, The complete paths for them are:

```
http://localhost:8005/Soen387A1/html
http://localhost:8005/Soen387A1/html/index2
```

In order to make a post request, you can pass you parameters this way:
```
http://localhost:8005/Soen387A1/Servlet1?[parameters here]
```
### Example of POST
http://localhost:8005/Soen387A1/Servlet1?format=html&param=Hello&param2=World&param3=Java

Should display a table like the one underneath:

|Request Method | GET           | 
| ------------- |:-------------:| 

|Request Headers|               | 
| ------------- |:-------------:| 
| format        | html          | 
| param         | Hello         | 
| param2        | World         | 
| param3        | Java          | 

Not specifying the format defaults to html. 


## Task 3: Http Server
The HTTP Server corresponds to the ` ServerInstance.java ` class inside of ` ./Java Resources/src/(default package)  `. 
Run it in Eclipse in order to run the server. The server runs at ` port 8011 `

