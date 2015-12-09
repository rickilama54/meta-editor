
<a href='Hidden comment: 
#Describes the coding standards such as indentation, order of imports, templates, etc
'></a>

# To set up Eclipse (Helios, Galileo and Ganymede) #
It is recommended that you use to Eclipse Helios or Galileo (this section has been tested for Helios and Galileo - some small differences will be encountered). You must install Eclipse and a compatible Java JDK prior to beginning this configuration. Also you need to obtain a set of configuration files which are located in the repository source.

**You need to add "process-resources" goal to the Window / Preferences / Maven / Goals to run on project Import.**
Then the generated resources (by Maven) will be added on classpath of eclipse.


> ## Plugins ##
  * Google Plugin for Eclipse [GPE](http://code.google.com/intl/cs-CZ/appengine/docs/java/tools/eclipse.html)
  * [m2eclipse](http://www.sonatype.org/m2eclipse)

> ## Path of the configuration files: ##
```
trunk/MEditor/resources/dev/eclipse
```

> ## Use the Eclipse Menu: Window -> Preferences -> Java -> Code Style ##
Under the top-level Code Style section:
|![![](http://meta-editor.googlecode.com/svn/wiki/img/CleanUp_sm.png)](http://meta-editor.googlecode.com/svn/wiki/img/CleanUp.png) | ![![](http://meta-editor.googlecode.com/svn/wiki/img/Templates_sm.png)](http://meta-editor.googlecode.com/svn/wiki/img/Templates.png) |
|:---------------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------|
| ![![](http://meta-editor.googlecode.com/svn/wiki/img/Formatter_sm.png)](http://meta-editor.googlecode.com/svn/wiki/img/Formatter.png) | ![![](http://meta-editor.googlecode.com/svn/wiki/img/Imports_sm.png)](http://meta-editor.googlecode.com/svn/wiki/img/Imports.png)     |

  1. Under the Clean-up section import meditor-cleanup.xml and press Apply
  1. Under the Code Templates section import meditor-codetemplates.xml, check the "Automatically add comments for new methods and types" checkbox and press Apply
  1. Also under the Code Templates section in Comments -> Types edit Types to replace "Firstname Lastname" with your own name and press Apply
  1. Under the Formatter section import meditor-formatter.xml and press Apply
  1. Under the Organize Imports section import meditor.importorder and press Apply
> ## Use the Eclipse Menu: Window -> Preferences -> Java -> Editor -> Save Actions ##

  1. Select "Perform the selected actions on save"
  1. Make sure "Additional Actions" is selected
  1. Click "Configure...", and go to the "Code Organizing" tab, and make sure "Remove trailing whitespace" and "All lines" are selected

| ![![](http://meta-editor.googlecode.com/svn/wiki/img/Save_sm.png)](http://meta-editor.googlecode.com/svn/wiki/img/Save.png) | ![![](http://meta-editor.googlecode.com/svn/wiki/img/AdditionalSaveActions_sm.png)](http://meta-editor.googlecode.com/svn/wiki/img/AdditionalSaveActions.png) |
|:----------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------|

# Optional Settings #
Meditor use code generation tools for generating Java classes. Without these, Eclipse will complain loudly. There are problems with refreshing in Eclipse that make generated code a source of some difficulties.
## Use the Eclipse Menu: Window -> Preferences -> Team -> Ignored Resources ##

Add
| target | .gwt | generated |
|:-------|:-----|:----------|
| .settings | .classpath |.project   |

...and press the Apply button.

![![](http://meta-editor.googlecode.com/svn/wiki/img/Ignore_sm.png)](http://meta-editor.googlecode.com/svn/wiki/img/Ignore.png)

