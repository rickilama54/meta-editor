



---



# General Info #
This wiki page should provide the basic information about AKKA conversion framework and the way to install the agent application on the nodes.

## Why ##
Metadata Editor takes the images from filesystem (mounted remotely) and converts them into JPEG2000 format. The conversion itself is quite time-consuming. For one average image it takes about 3 second to convert.

## How ##
The editor runs the bash script for each image and this script runs subsequently convert (from imagemagick package), tiffcp (from libtiff-tools package) and compress (kakadu). This combination guarantees that the format of the input images can be various as long as the convert utility can take it to tiff format.

The editor provides the way to paralelize the conversion tasks across many worker nodes. Default behavior is the serial execution of conversion tasks described above. For paralel execution the following steps should be done:

1) add following to configuration.properties of the deployes Metadata Editor:
```
# AKKA (for image conversion)
akkaOn=Yes
akkaConvertWorkers=192.168.100.1:2552, 192.168.100.2:2552, 192.168.100.3:2552, 192.168.100.4:2552
```
where `akkaConvertWorkers` is the comma separated list of IP addresses of worker nodes and the port, where akka microkernel is running (2552 is default)

2) install the, akka framework, deploy the agent/worker application into akka microkernel and ensure the remote drives are mounted

## Installation of worker node ##
If you are not familiar with unix administration, you can use easier approach - deploy prepared VMware image of editor-worker and change only the network interface related stuff. The image can be obtained from the administrator of Moravian Library in Brno.

1) add the following packages:
```
libtiff-tools imagemagick sshfs
```
2) create user meditor and in his home dir create the following directory structure:
```
meditor@editor-worker1:~$ pwd
/home/meditor
meditor@editor-worker1:~$ tree -L 2
.
|-- akkaLog -> /home/meditor/install/akka-2.0/log/conversion.log
|-- input
|   |-- monograph
|   \-- periodical
|-- install
|   |-- akka-2.0
|   |-- djatoka
|   \-- jdk1.6.0_31
|-- output
|   |-- editor-devel.mzk.cz
|   \-- editor.mzk.cz
\-- runConversionNode.sh
```
Note that sub-directories in output directory should correspond with the hostname of the master akka node (the node distributing the work and hosting the metadata editor webapp). You can use symlinks.

3) download and install java jdk/jre and akka microkernel v 2.0 and djatoka (with convert script)

4) set the environment variables such as $JAVA\_HOME and $CONVERT\_HOME. $CONVERT\_HOME whould point to ~/install/djatoka (this dir https://github.com/moravianlibrary/MEditor/tree/master/resources/djatoka)

5) Ensure, the setup will survive the restart. I.e. add mount points to fstab, if using sshfs, mount them in /etc/rc.local for example and make the bash script in /etc/init.d/ called akka starting the akka microkernel after start. It can look like following:
```
### BEGIN INIT INFO
# Provides:          akka
# Required-Start:    $local_fs $network
# Required-Stop:     $local_fs $network
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# X-Interactive:     true
# Short-Description: Start/stop Akka microkernel
### END INIT INFO


# AKKA auto-start
# author:      Jiri Kremser
# description: Auto-starts akka microkernel
# processname: akka

export LC_ALL=cs_CZ.utf8
export CONVERT_HOME=/home/meditor/install/djatoka/
export JAVA_HOME=/home/meditor/install/jdk1.6.0_31/
export PATH=$JAVA_HOME/bin/:$PATH

case $1 in
start)
       sudo -u meditor /home/meditor/install/akka-2.0/bin/akka
cz.mzk.editor.server.convert.Client >>
/home/meditor/install/akka-2.0/log/conversion.log&
       ;;
stop)
       kill `ps aux | grep akka\.kernel\.Main | grep -v grep | awk '{
print $2 }' | head -1`
       ;;
restart)
       echo "Stopping akka.."
       kill `ps aux | grep akka\.kernel\.Main | grep -v grep | awk '{
print $2 }' | head -1`
   sleep 5
       echo "Starting akka.."
       sudo -u meditor /home/meditor/install/akka-2.0/bin/akka
cz.mzk.editor.server.convert.Client >>
/home/meditor/install/akka-2.0/log/conversion.log&
       echo "done"
       ;;
*)
       echo "Usage $0 start|stop|restart"
       ;;
esac
exit 0
```