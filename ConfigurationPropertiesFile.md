# Introduction #

How to set your configuration.properties file

syntax: parameter=value1, value2, ...


# Parameters #

|Parameter|Description|Default values|
|:--------|:----------|:-------------|
|<b>inputQueue</b>|The path to the scanned images with subdirectories named same as documentTypes|              |
|<b>imageServer.internal</b>|Whether the internal imageserver is used (values: false, true)|false         |
|<b>imageServer.url</b>|The URL of a imageserver (if is any imageserver used)|              |
|<b>imageServer.known</b>|The path to the image server directory where data with known sysno is stored (if is any imageserver used)|              |
|<b>imageServer.unknown</b>|The path to image server directory where data with unknown sysno is stored (if is any imageserver used)|              |
|<b>djatoka.home</b>|The scan Djatoka home path|              |
|<b>editor.home</b>|The editor home path|              |
|<b>imageExtension</b>|The allowed image extensions (values: jpg, JPG, png, tiff, tif, jpeg, jp2, pdf) |jpg, png, tiff, tif, jpeg|
|<b>documentTypes</b>|Types of objects being digitalized (values: monograph, periodical, map, manuscript) |periodical, monograph|
|<b>guishowInputQueue</b>|Whether the input queue has to be shown(values: false, true) |true          |
|<b>recentlyModifiedNumber</b>|The number of shown recently modified item |10            |
|<b>z39.50Profile</b>|The z39.50 profile |mzk           |
|<b>z39.50Host</b>|The host(s) of z39.50 providers |aleph.mzk.cz, aleph.muni.cz, sigma.nkp.cz, sigma.nkp.cz|
|<b>z39.50Port</b>|The used port(s) of z39.50 |9991, 9991, 9909, 9909|
|<b>z39.50Base</b>|The z39.50 base(s) |MZK01-UTF, MUB01, SKC, NKC|
|<b>barcodeLength</b>|The lenght(s) of barcode |10, 10, 10, 10|
|<b>oaiUrls</b>|The URL(s) of OAI provider |http://oai.mzk.cz|
|<b>oaiIdPrefixes</b>|The ID-prefixes of OAI |oai:aleph.mzk.cz|
|<b>oaiBases</b>|The OAI base(s) |MZK01, MZK03  |
|<b>accessUserPatterns</b>|           |`*`           |
|<b>accessAdminPatterns</b>|           |127.`*``|``|`localhost|
|<b>krameriusHost</b>|The kramerius host|              |
|<b>krameriusLogin</b>|The kramerius login|              |
|<b>krameriusPassword</b>|The kramerius password|              |
|<b>fedoraHost</b>|The fedora host|              |
|<b>fedoraLogin</b>|The fedora login |fedoraAdmin   |
|<b>fedoraPassword</b>|The fedora password|              |
|<b>fedoraVersion</b>|The fedora version |3.3           |
|<b>httpBasicPass</b>|NOT SUPPORTED|              |
|<b>dbHost</b>|The database host |localhost     |
|<b>dbPort</b>|The database port |5432          |
|<b>dbLogin</b>|The database login |meditor       |
|<b>dbPassword</b>|The database password|              |
|<b>dbName</b>|The database name |meditor       |
|<b>sshUser</b>|The user for accessing the machine via ssh |meditor       |
|<b>openIdApiKey</b>|The openId API key (of Janrain service) |775a3d3ec29deeeaf39e506ff514f39fcb5e434d|
|<b>openIdApiUrl</b>|The openId API URL (of Janrain service) |https://rpxnow.com|
|<b>localhost</b>|Whether is this machine for developing (when the application is ran via hosted mode) |false         |
|<b>userDirectories</b>|The path to the stored user copies of FOXML or other user things|              |
|<b>genImagesLifetime</b>|The number of days after which will be the generated (converted) images removed|never         |
|<b>akkaConvertWorkers</b>|NOT SUPPORTED|              |
|<b>akkaOn</b>|NOT SUPPORTED |false         |
|<b>createIngestInfoXmlFile</b>|NOT SUPPORTED|              |
|<b>imagesDir</b>|The path to the directory where converted images will be stored |`~/output/`   |
|<b>identities</b>|NOT SUPPORTED YET|openid        |