## Hair Salon App
The salon owner can add a list of stylists, and for each stylist, add clients who see that stylist. The stylists work independently, so each client only belongs to a single stylist. 

[Link to App](http://hairsalon-ip.herokuapp.com/)

**Specs**
1. Salon employee can see list of all stylists.
2. Upon selecting a stylist, employee should see their details and list of all clients that belong to the stylist.
3. Employee can add new stylist
4. Employee can add clients to a stylist.
5. Employee can update stylist details.
6. Employee can update client details.
7. Employee can delete stylist.
8. Employee can delete client.

**Tech Used**
1. Java Programming Language
2. Spark Java Web Framework
3. PostgreSQL Database
4. Velocity Template Engine

**Set Up and Installation**
1. Download the project folders
> ```
>$ git clone https://github.com/ngenovictor/hairsalon.git
>$ cd hairsalon
>```
2. Change postgres db login details
> ```
> $ cp src/main/java/DbDetailsExample.java src/main/java/DbDetails.java 
> $ gedit src/main/java/DbDetails.java
>```
> Change the class name to DbDetails and the db logins to your credentials
3. Launch
> ```
> $gradle run
>```
> open [http://localhost:4567](http://localhost:4567)

**Licence and copyright**
Ngeno Victor, Moringa School
Licensed under [MIT Licence](License.txt) 