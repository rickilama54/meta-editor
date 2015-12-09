# Introduction #
This wiki page provides information about building the project from the source code, because it is an open-source; and provided you accept the licence agreement, you are allowed to do that. It also gives the information about code separation into particular maven modules, information about used technologies, security aspects, etc.

## Building form Source Code ##

From architectural perspective, the editor is divided into several Maven modules forming the modular system. The whole application can be build from source code by running one of the following command on the top-lvl maven module.
```
mvn install # default build (hostname = editor.mzk.cz)
mvn install -Dfilter.hostname=hostnameXY #for specific deployment
mvn install -P dev # development profile will shorten the compilation time by omitting gwt permutation for specific locales and browsers
```

Unfortunately, a few libraries are not present at any public Maven repository, thus user have to install them manually. They are located at https://github.com/moravianlibrary/MEditor/tree/master/resources/lib and in the main (top-lvl) pom.xml, there should be some description how to do this.

After the project is successfully built, the war is located at the directory called "target" and can be deployed to any servlet container (neither EJB nor Spring was used and all the libraries except jdbc driver are provided in the web archive).

If you have the war file ready for the deployment, you can continue with InstallationNotes.


## Code Separation ##
As mentioned above, the Editor uses the Maven build system and the related classes are situated int the same Maven module. In general there are different approaches how to separate the project into particular projects. One can take the layer approach, one can take the feature based approach, we are probably somewhere between the two. Some modules are feature based (like editor-{creation|editation|user}) some are more layer based like editor-dao.

The structure of mvn modules:
```
.
|-- editor-common
    |-- editor-common-client
    \-- editor-common-server
|-- editor-converter
|-- editor-creation
|-- editor-dao
|-- editor-editation
|-- editor-request
|-- editor-user
\-- editor-webapp
```

Here is a short description to the modules:

**commons**
Common-client contains the shared functionality across other client modules. The same applies for common-server. Here the client/server corresponds with the notion of client-side and server-side from GWT (client-side Java classes are compiled into JavaScript)

**converter**
Contains the code written in Scala language for parallel execution of convert tasks using the Akka framework. More info in AkkaConversionFramework.

**creation**
This module contains client side and server side for creation of new digital object and creating the FOXML out of them.

**dao**
Data access layer, pure JDBC is used (no ORM), it should be relatively easy to change the implementation of the DAO interfaces, say, with some NoSQL DB if needed. However, the editor is not "DB intense" application.

**editation**
Similarly to cration module, this one takes care of editing the existing digital objects in fedora.

**request**
Since application is using OpenID for authentication and ssl for secure access, this module provides the way for submitting the request for access permission to application using unsecured GWT endpoint -> contains its own GWT descriptor.

**user**
Provides the administration GUI for adding/modifying new users, putting users into roles, dealing with requests for adding (see previous module), etc.

**webapp**
"Glue" module, contains deployment descriptor (web.xml), images and other web resources and depends  on all other modules (directly or transitively). It's pom.xml is also responsible for assembling the war file.

## Security ##
All the communication is tunneled via SSL. If the user is not authenticated by third party OpenID service, he is redirected to login page.

There is an authorization server filter with some white-listed resources redirecting the user if not logged in.

## Frameworks ##
I was trying to use similar technologies as related project [code.google.com/p/kramerius/ Kramerius 4] i.e. GWT, Google Guice, Apache Commons stuff, etc. For visual component library I decided to choose the SmartGWT framework and for "gwt-best-practices-framework" I've picked the gwtp.

### UI ###

Unfortunately, sometimes both GWTP and SmartGWT frameworks solves the same problems differently and developer has to choose between the two. Firstly, I was using the concept of datasources from SmartGWT, then I've realized the GWTP implementation of command pattern (GoF design pattern) is more robust and flexible in the same time, so the new code uses this approach for fetching the data from server/db or sending them to the server/db.

In fact, after refactoring, the SmartGWT datasources ([link](https://github.com/moravianlibrary/MEditor/tree/master/editor-common/editor-common-client/src/main/java/cz/mzk/editor/client/gwtrpcds)) are used only for `InputTree` component IIRC.

GWTP uses the code generators for generating the Action and Result tuples and for generating the Event and its Handler as well. Actually, the handlers substitute the old fashioned service impl classes from pure GWT.

GWTP provides also the MVP framework.

### Domain Specific ###
For working with Z39.50 there is a library called jzkit-z3950. For calling the REST API of Fedora Commons repository, I've used the plain old HttpURLConnection and utility class called RESTHelper (based on the class from Kramerius project). For dependency injection I've used the Google Guice.

For mapping between `DublinCore` and Mods on the one hand and the Java objects on the other hand the JAXB was used. Unfortunately, the GWT doesn't support the JAXB annotation on the client side, therefore, the client version of the generated objects had to be introduced. This part of projects is a good candidate for a big refactoring.

## Database ##
The system incorporates an easy way for coping with database versioning. There are two related files located in [here](https://github.com/moravianlibrary/MEditor/tree/master/editor-webapp/src/main/resources). `schema.sql` and `schemaVersion.txt`. Whenever the schema version (content of the `schemaVersion.txt` file) is increased, the content of the file `schema.sql` is applied on the Postgres database automatically by the system. In other words, it drops the existing schema and applies the new one form the file.

The schema is relatively small, there are only few tables (up to 20). The old version of ERD is present in my [thesis](http://code.google.com/p/meta-editor/downloads/detail?name=Thesis.pdf&can=2&q=).

## Tests ##
Unfortunately, there are no tests now. If you want to contribute, go ahead and send us the pull request on `GitHub`.

## Djatoka ##
Thumbnails of the scanned images are generated on-the-fly by the Djatoka image web server, which is basically the Java Servlet wrapper of the tool Kakadu. When using the editation mode (not the creation one), the thumbnails are obtained from the Fedora and the imageserver is not being used. Djatoka comes with the Editor's war file as a separated application in its own war file. This war file has to be deployed into same servlet container. (It can be changed in the editor configuration).

## i18n ##
Metadata Editor supports Czech language and English language. If you want to contribute and make the localization for your country and your mother tongue, go ahead and send us the pull request on `GitHub`.