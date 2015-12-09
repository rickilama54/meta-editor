



---



# Editor Installation #
The goal of this page is to give information concerning the installation and deployment of the Metadata Editor. The most of the content copes with linux customization.

## Notes: ##

  * Let H is a host name where we want to deploy the editor
  * Let E is a host name of a computer where the editor is already deployed
  * Installation process is primarilly designed for a Debian-like linux, but it was tested for example on `RedHat` distribution after few editations as well.

Add a new user called "meditor" on H computer:
`sudo adduser meditor`

## Packages: ##

Add packages:

`sudo aptitude (apt-get) install mc vim aptitude acpi ssh rar sshfs wget curl openntpd ntpdate libtiff-tools imagemagick unzip htop`

## Database: ##

If the DB is not present, install Postgres
```
sudo su postgres && psql 
```
and run following commands:
```
CREATE ROLE meditor WITH PASSWORD 'samePassAsUnixPassForMeditor' LOGIN;
CREATE SCHEMA AUTHORIZATION meditor;
ALTER SCHEMA meditor OWNER TO meditor;
CREATE DATABASE meditor WITH OWNER meditor;
```
  * The database schema is [here](https://github.com/moravianlibrary/MEditor/blob/master/resources/newSqlWithClean/schema.sql). You can import it with the command "`psql -f /path_to_the_file/shema.sql meditor`" from bash. Or with the command "`\i /path_to_the_file/shema.sql`" from Postgres command line. Please run both of the commands as a user meditor. If you don't have the old version (before 1. 11. 2012) you can omit the "DROP & ALTER" part.
  * add (or ensure about their presence) the following lines to the configuration file of the postgres (_/etc/postgresql/${version}/main/pg\_hba.conf_ or _/usr/share/pgsql/pg\_hba.conf_ on RHEL), for joining via http for user and database called "meditor". The "MZK lines" is only an example. Please use the IP(s) of you institute.
```
#localhost
host	all 	meditor 	127.0.0.1/32		password
# MZK
host	all 	meditor 	195.113.155.0/24   	password
```
  * in file _/etc/postgresql/${version}/main/postgresql.conf_ uncomment the line "listen\_addresses" and as a value set the strings "localhost" and ip address of H computer, separated by comma. Example:
```
listen_addresses = 'localhost,195.113.155.50'
```

  * restart postgres
```
/etc/init.d/postgresql-${version} restart
```

## Install Tomcat: ##

```
wget http://mirror.hosting90.cz/apache/tomcat/tomcat-7/${tomcat_version}/bin/apache-tomcat-${tomcat_version}.zip #(or newer)
unzip apache-tomcat-${tomcat_version}.zip
```
  * Into
```
$CATALINA_HOME/bin/catalina.sh
```
> ...add a line:
```
CATALINA_PID="$CATALINA_HOME/tomcat.pid"
```
> place it after the command, which set a variable `CATALINA_HOME` in case it was not set yet (in version 7 Tomcat it is situated about line 100)

  * Into directory
```
CATALINA_HOME/lib/
```
> copy the JDBC driver for Postgres (for example postgresql-${version}.jdbc4.jar)

## Environment Variables: ##

Into
```
/home/meditor/.bashrc
```
add the following lines (+/-):
```
export HISTSIZE=10000
export HISTFILESIZE=10000
export HISTCONTROL=ignoreboth
export JAVA_HOME='/path_to_java/jdk-${java_version}/'
export CATALINA_HOME='/home/meditor/apache-tomcat-${tomcat_version}/'
export CATALINA_OPTS='-server -Xmx512m -XX:MaxPermSize=128m -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n'
export PATH=$JAVA_HOME/bin:$PATH
export LC_ALL=cs_CZ.utf8
export PGCLIENTENCODING=UNICODE
```

## Image Server: ##

  * create
```
H:/home/meditor/.meditor
```

  * copy directories
```
resources/djatoka dir
resources/xml
```
from [resources](https://github.com/moravianlibrary/MEditor/blob/master/resources) into _H:/meditor/.meditor_ (directories are in SVN)
    * in war file: _H/user/.meditor/djatoka/dist/djatoka.war#/WEB-INF/classes/djatoka.properties_ change the password for database by _‘samePassAsUnixPassForMeditor'_ (mc can be used), eventually port of the database as well if it is not the default one (5432).

  * Copy the war file of djatoka image server into Tomcat (into webapps)

  * Check that java is not using JVM from OpenJDK (java -version) It should be using JVM from Oracle (JRockit is not recommended). There should be equality between java, which is launched from bash ('$PATH/java') and which is launched from _$JAVA\_HOME/bin/java_ (due to external processes as image conversion).


  * Make a file _configuration.properties_ in H/user/.meditor and set the [parameters](ConfigurationPropertiesFile.md), especially the path to the import directory, the path to the images (internal imgserver), etc.

## DB Credentials: ##
On the PC from which I want to deploy, create the directory META-INF (next to the WEB-INF in the war directory) and subsequently create the file context.xml with following content:

```
<?xml version="1.0" encoding="UTF-8"?>
<Context antiJARLocking="true" path="/meditor">
   <Resource name="jdbc/editor" auth="Container" type="javax.sql.DataSource"
           driverClassName="org.postgresql.Driver"
           maxActive="40" maxIdle="20" maxWait="7500"
           removeAbandoned="true" removeAbandonedTimeout="100" logAbandoned="true"
           username="meditor" password="samePassAsUnixPassForMeditor"
           url="jdbc:postgresql://localhost:5432/meditor" />
</Context> 

```

Note: the file is not included in the SVN, because of the password

## Reverse Proxy: ##

  * Install Apache (httpd or `RedHat`) and check the presence of (if absent, install them) modules (situated at _/etc/apache2/mods-available_) _mod\_proxy, mod-proxy-html_ a _mod\_ssl_ and _mod\_rewrite_ and activate it by a2enmod (_/etc/apache2/mods-enabled_).

  * Create the definitions of virtual hosts in /etc/apache/sites-available_, one for editor running on port 80 and one for editor running on port 443. In order to run imageserver Djatoka you will need to set rewrite rules:
```
<IfModule mod_rewrite.c>
        RewriteEngine on
        RewriteLog "/var/log/apache/rewrite.log"
        RewriteLogLevel 9
        Options +FollowSymlinks

        RewriteRule ^[a-zA-Z0-9_\/\.\-]*\/([a-zA-Z0-9_\.\-]*)\/preview.jpg$ http://hostURL/djatoka/resolver?url_ver=Z39.88-2004&svc_id=info:lanl-repo/svc/getRegion&svc_val_fmt=info:ofi/fmt:kev:mtx:jpeg2000&svc.level=2&svc.scale=150&rft_id=$1& [L]
        RewriteRule ^[a-zA-Z0-9_\/\.\-]*\/([a-zA-Z0-9_\.\-]*)\/large.jpg$ http://hostURL/djatoka/resolver?url_ver=Z39.88-2004&svc_id=info:lanl-repo/svc/getRegion&svc_val_fmt=info:ofi/fmt:kev:mtx:jpeg2000&svc.level=4&svc.scale=975&rft_id=$1& [L]
        RewriteRule ^[a-zA-Z0-9_\/\.\-]*\/([a-zA-Z0-9_\.\-]*)\/big.jpg$ http://hostURL/djatoka/resolver?url_ver=Z39.88-2004&svc_id=info:lanl-repo/svc/getRegion&svc_val_fmt=info:ofi/fmt:kev:mtx:jpeg2000&svc.level=5&svc.scale=1500&rft_id=$1& [L]
</IfModule>
```
Change the hostURL in all places for the proper one._


  * Add links for certificates into the virtual host definition for SSL.

  * Allow these the two sites by a2ensite.

  * Restart Apache (_/etc/init.d/apache2 restart_ or reload if changes are only on the level of hosts).

  * Revise the file _/etc/apache2/mods-enabled/proxy.conf_ to be equal to the file on the computer, where was editor installed before (E).

  * host H should know he is H. In other words "ping H" should work and H should be resolved to ip address (add H to the /etc/hosts if it is not there).

  * Add/comment/change the file _H:$CATALINA\_HOME/conf/server.xml_ to achieve the state as follows:
```
 <Connector port="8081" protocol="HTTP/1.1"
             proxyName="H" redirectPort="8443" proxyPort="80" />
<Connector port="8443"
 maxThreads="150" minSpareThreads="25" maxSpareThreads="75"
 enableLookups="true" disableUploadTimeout="true"
 acceptCount="100" debug="0" scheme="https" secure="true"
 clientAuth="false" sslProtocol="TLS" SSLEnabled="true" SSLEngine="on"
 keystoreFile="PATH_TO_KEYSTORE"
 keystorePass="changeme" />
```
> where H is the hostname
> _PATH\_TO\_KEYSTORE_ is the path to the file, which was created by running command:
```
$JAVA_HOME/bin/keytool -genkey -alias tomcat -keyalg RSA
```
> and  “changeme” will be a password, which the command keytool asks for.

  * restart Tomcat: use script in
```
/home/meditor/.meditor/djatoka/bin/tomcat.sh
```

  * Delete the demo applications from Tomcat (webapps) such as ROOT or docs. etc.

## Time Server: ##
```
sudo vim /etc/openntpd/ntpd.conf 
```
add _tik.cesnet.cz_ and _tak.cesnet.cz_ and restart the service
```
sudo /etc/init.d/openntpd restart
```

## Mounting the Drives: ##

In _/etc/fstab_ define the disks which are going to be mounted. In order to mount disks via sshfs protocol add the following to _/etc/rc.local_ and _/home/meditor/.bashrc_
```
sudo -u meditor sshfs -o ssh_command="ssh -i /home/meditor/.ssh/id_rsa",nonempty imageserver@imageserver:/var/www/imageserver.mzk.cz/mzk03 /home/meditor/imageserver/known/mzk03
sudo -u meditor sshfs -o ssh_command="ssh -i /home/meditor/.ssh/id_rsa",nonempty imageserver@imageserver:/var/www/imageserver.mzk.cz/mzk01 /home/meditor/imageserver/known/mzk01
sudo -u meditor sshfs -o ssh_command="ssh -i /home/meditor/.ssh/id_rsa",nonempty imageserver@imageserver:/var/www/imageserver.mzk.cz/meditor /home/meditor/imageserver/unknown
sudo -u meditor sshfs -o ssh_command="ssh -i /home/meditor/.ssh/id_rsa",nonempty meditor@hades:/data/meditor/editor/import/serial /home/meditor/input/periodical
sudo -u meditor sshfs -o ssh_command="ssh -i /home/meditor/.ssh/id_rsa",nonempty meditor@hades:/data/meditor/editor/import/monograph /home/meditor/input/monograph
```

## Firewall: ##

Into the file
```
/etc/init.d/firewall
```
add lines:
```
       iptables -t filter -A INPUT -p tcp --dport 80 -j ACCEPT
       # Tomcat
       iptables -t filter -A INPUT -p tcp --dport 8000 -j ACCEPT
       # PostgreSLQ
       iptables -t filter -A INPUT -p tcp --dport 5432 -j ACCEPT
```
Port 8000 is neccesary for external debug. In other words if debug is not needed, comment the line with port 8000, otherwise take the debug mode into account also in the parametres for JVM when Tomcat is started.

Port for postgres will be opened for allowing the connection to the database in debug mode (GWT hosted mode for instance) from public IP.

For accesing the H from public IP via ssh, it is necesary to add the public IP address into the group FRIENDS:
```
   # home laptop
   iptables -t filter -A FRIENDS -s 84.42.234.102 -j ACCEPT
```

## Security: ##

Into the file _/etc/hosts.allow_
add:
```
ALL: localhost
ALL: 195.113.155.
sshd: 147.251.
```

When someone wants to break in the machine (records in _/var/log/auth_ or in a log files from Apache), he can be banned in the file _/etc.hosts.deny_

For even more security it is possible to change the _/etc/ssh/sshd\_config_ element _`PasswordAuthentication`_ from **_yes_** to **_no_** and allow for loging in only via the certificate.

## Running after Restart: ##

Postgres and Apache should be launched automatically after the restart, but Tomcat don't. Therefore, it is necessary to do:

create script _/etc/init.d/tomcat_ and copy the content from the same location in the computer, where the editor is already deployed (E), alter the paths for the correct ones and run:
```
update-rc.d tomcat defaults
```

or on debian 6 and higher:
```
insserv tomcat
```

## Updates: ##

As the root launch a command
```
crontab -e
```
and add the line
```
3 14 * * * (aptitude update && aptitude safe-upgrade -y) > /dev/null
```