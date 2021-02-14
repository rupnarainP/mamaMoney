# mamaMoney

<b>USSD Service:</b>

Instructions are based on Intellij Ultimate 2020.3
Developed using:
JDK 11
Soring Boot v2.4.2
Maven 3.6.3
H2 database
Windows 10

<b>Build/Run Instructions:</b>
1. Import project as Maven project.
2. In IntelliJ Maven menu, under the lifecycle menu, double click "clean", then double click "install".
3. The "target" folder will generate the "ussd-0.0.1-SNAPSHOT.jar".
4. Navigate to the "target" folder in your file explorer.
5. Open a powershell window in the directory (SHIFT + Right click).
6. Paste this command: "java -jar ussd-0.0.1-SNAPSHOT.jar"
7. The jar file will run in the powershell window.

<b>Alternatively:</b>
1. Import project as Maven project.
2. Right click on "UssdAppliction.java" and select "Run UssdApplication".
3. The application will start within IntelliJ.

<b>Testing:</b>
1. Can use an application like Postman (https://www.postman.com/downloads/) to test the endpoint: "http://localhost:8080/mamamoney/ussd".
2. In Postman, click on "File", then select "Import".
3. Select "Upload Files" in Postman. The Postman collection found in "src/test/Postman".
5. Each request is labelled as the Menu screen.
6. To start the process, select the "Menu1" request and then click on the "Send" button.
7. Under the body tab for each request, you can change the userEntry variable to the applicable options for each menu. 
8. The H2 console can be opened in a browser of your choice by using this link: http://localhost:8080/h2-console/
9. The H2 credentials are standard and does not have a password. You can access it by clicking on "Connect"
10. The database is preloaded with 3 rows for data in the USSD table.
11. H2 is an in-memory database that will reset all data every time you stop and re-run the Springboot application.
