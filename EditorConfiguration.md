



---


# Configuration File #
## example ##
```
inputQueue=/home/meditor/import
z39.50Profile=mzk


#fedora
fedoraHost=http://fedora.fsv.cuni.cz/fedora
fedoraLogin=fedoraAdmin
fedoraPassword=********
fedoraVersion=3.5


#kramerius
krameriusHost=http://kramerius.fsv.cuni.cz/search
krameriusLogin=meditor
krameriusPassword=********

createIngestInfoXmlFile=yes

# access (list of ip addresses or regular expressions separated by "||")
accessUserPatterns=.*
accessAdminPatterns=localhost||127.*

# top level models. In fedora there has to be corresponding counterpart (model:X), where X is document type.
documentTypes=monograph, periodical

#imageserver
imageServer.url=http://imageserver.mzk.cz
imageServer.known=/home/meditor/imageserver/known
imageServer.unknown=/home/meditor/imageserver/unknown
imageExtension=jpg, png, tiff, tif, jpeg, jp2

hostname=http://meditor.vsf.cuni.cz
genImagesLifetime=20

akkaOn=Yes
#akkaConvertWorkers=195.113.155.44:2552, 195.113.155.61:2552, 195.113.155.62:2552, 195.113.155.63:2552

```