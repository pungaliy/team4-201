# Suite Home #
#### CSCI-201 Team 4 Final Project ####


## Deployment ##

### Server ###
1. Open IntelliJ IDEA
2. Select New -> Project from version control -> git
3. Enter the repository link: https://github.com/pungaliy/team4-201.git
4. Select ‘Import Project’

### Web Configuration ###
1. Press cmd-; to enter the Project Structure window
2. Select ‘Facets’ tab on the left-hand side
3. Press the + button in the top left corner
4. Click ‘Web’
5. Click ‘Okay’
6. Click ‘Create Artifact’
7. Click ‘Apply’
8. Select ‘Project’ tab
9. Select ‘JDK 11’ from the dropdown menu
    1. If not present, see ‘Installing JDK 11’ doc
10. Click ‘Apply’
11. Select file explorer from the Project Compiler Output menu
12. Navigate to the project directory
13. Make a new folder named ‘out’
14. Select ‘Open’
15. Click ‘Apply’

### Make Artifacts ###
1. Go to Tools, then press Edit Configurations...
2. Press + in the top left corner
3. Select ‘Tomcat Server’ -> Local
4. Click ‘Add Artifact’ if warning pops up
    1. If no popup, follow ‘Add artifact to intellij project’ guide on the web
5. Double check that you are in the artifacts tab in the project structure window
6. Click + in the top left corner
7. Click ‘New Web Application - exploded’
8. Click ‘From modules’
9. Press Okay
10. Should be redirected to run configurations page
11. Double check that the port is 8080
12. Click ‘Configure’ in the top right corner
13. Press + button in the bottom center of the new popup window
14. Select the following jar files:
    * bson-3.8.2.jar
    * gson-2.8.2.jar
    * mongodb-driver-3.8.2.jar
    * mongodb-driver-core-3.8.2.jar
    * javaee-api-7.0.jar
    * javax.annotation.jar
    * javax.ejb.jar
    * javax.jms.jar
    * javax.persistence.jar
    * javax.resource.jar
    * javax.servlet.jar
    * javax.servlet.jsp.jar
    * javax.servlet.jsp.jstl.jar
    * javax.transaction.jar
    * javax.websocket-api-1.1.jar
    * javax.websocket-api-1.1-javadoc.jar
    * servlet-api.jar

### Database ###
1. Open a terminal window
    1. if homebrew is not installed, install homebrew
2. Enter the following commands:
    1. brew install mongodb
    2. mkdir -p /data/db
    3. sudo chown -R ‘id -un’ /data/db
3. To run mongodb:
    1. Type into the terminal mongod
    2. This must be running whenever the web server is running

### Run ###
1. Right click the ‘dbsrc’ directory and mark it as a sources root
2. Right click the ‘src’ directory and mark it as a sources root
3. In the top-right of the IDE, select the configured Tomcat server and press Run
4. Begin interaction by navigating to localhost:8080/login


## Requirements ##

### Software ###
* Intellij IDEA 2018.2.3 (Ultimate Edition)
* Apache Tomcat Version 9.0.11
* MongoDB version 3.8.2
* Java EE 11 SDK or later

### Hardware ###
* Pentium 2 266 MHz processor or later
* RAM: 1GB or more
* Disk: 250MB or more free space

### Environment ###
* Google Chrome
* MacOS


## Troubleshooting ##
If the program does not work, there is a support hotline that can be reached by calling 714-686-9939 or by emailing allanzha@usc.edu