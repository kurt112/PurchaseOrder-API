#To use api
Go to src/main/resources/application.properties

###change the path of 
* spring.datasource.url= "Your dataSource path"
* spring.datasource.username = "Your DB username"
* spring.datasource.password = "Your DB password"

### Change the allowed cors
src/main/java/com/API/PurchaseOrder/configuration/CORS.java

* Find the .allowedOrigins("Your Client")