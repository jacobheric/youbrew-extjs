
This is my Spring, SpringMVC, Hibernate, Ext JS enterprise multi-module maven starter app.
The multi-module Maven structure is outlined below.  For more information on multi-module maven projects, 
see the documentation: http://www.sonatype.com/books/mvnex-book/reference/multimodule-web-spring.html.  

This app, in it's current form, has no business being a multi-module maven application.  It's built this 
way as a starter for a larger/enterprise type functionality.  I like my starter apps to have some functionality
before I burn it down and build them up (so I can remember how they work).

Before you can run the application, you must create the database schema and tables.  You can use one of the
sql scripts (one has data in it, one creates empty tables) in the docs directory.  Alternatively, you can
try uncommenting the hibernate.hbm2ddl.auto property persist-context.xml to have hibernate generate
your data structures for you.

Something like this should work:

mysql> source /path-to-your-workspace/youbrew-extjs/doc/mysql_ddl_and_data.sql

To build all modules and run the web application, do:
1.  In root run:  mvn clean install
2.  In youbrew-webapp, run: mvn jetty:run
3.  Goto:  http://localhost:8080/youbrew-extjs

To build IDE project files, try:
* For Eclipse: mvn eclipse:eclipse
* For IntelliJ IDEA: mvn idea:idea (or try File > New Project > Create from external model > maven) 

For questions, try me at jacob [@] jacobheric.com.

youbrew-extjs
|-- pom.xml
|-- youbrew-parent
	-- pom.xml
	|-- youbrew-domain
	|   -- pom.xml
	|-- youbrew-persist
	|   -- pom.xml	
	|-- youbrew-service
	|   -- pom.xml
	|-- youbrew-webapp
	|   -- pom.xml