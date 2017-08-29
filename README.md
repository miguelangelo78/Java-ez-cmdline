# Java-ez-cmdline

This is a really simple 'single file library' written in Java for parsing command line arguments.

# How to use

First, in the main method, execute the function ```CMDLINE.addFlags(...)```:
```java
import com.cmd.*;

public static void main(String[] args) {
	/* Setup supported Command Line flags */
	CMDLine.addFlags(
		"flag1", "This is the first flag.",
		"flag2", "Another flag.",
		'd',     "A single character flag.",
		'c',     "<val1>;You can specify what values this flag supports."
	);
...
```
(This function takes any number of arguments, which mainly consist of strings.)

The format for creating command line flags is: ```<flagname> <flag description>```. Also, it is possible to describe the flag as a character, as described in the example above.

You may also set up a condition that causes the help() method inside the library to trigger, for instance:
```java
/* Execute help method with the following settings */
CMDLine.setHelpCondition(
	CMDLine.HelpCondition.WHEN_NOARGS,        /* Call help() when user does not provide arguments */
	CMDLine.HelpResponse.HELP_AND_QUIT,       /* When help() is called, also quit the program     */
	CMDLine.HelpResponse.HELP_WARNONLY_NOHELP /* On an invalid flag, only warn the user           */
);
```

In addition to this, you can set up the usage message:
```java
/* Set usage message */
CMDLine.setUsage("java -cp bin main.Main [options]");
```

After setting things up, simply run:
```java
/* Parse Command Line arguments */
CMDLine.parse(args);
```

Finally, in order to consume the parsing results, you may follow the example below:
```java
/* Check flags */
System.out.println("Flag 'flag1': " + CMDLine.hasOption("flag1"));
System.out.println("Flag 'flag2': " + CMDLine.hasOption("flag2"));
System.out.println("Flag 'd': "     + CMDLine.hasOption('d'));
System.out.println("Flag 'c': "     + CMDLine.hasOption('c'));
```

(The ```hasOption(String|Character)``` returns boolean type.)

You can also query the library and get the value associated with a certain flag:
```java
CMDLine.Option opt = CMDLine.query('c');
if(opt != null)
	System.out.println("Flag 'c' value: " + opt.value);
```

Whenever you wish to see the whole data stored and parsed by the library, simply run these dump methods:
```java
/* Display the flags the library supports */
System.out.println("\nSupported flags: ");
CMDLine.dumpSupported();

/* Dump parse results */
System.out.println("\nParsed flags: ");
CMDLine.dumpParsed();
```

# Flag formats
The formats supported by the library are:

* ```-c``` (This is a single character flag)
* ```-c arg1``` (This is a single character flag with an argument)
* ```-c=arg1``` (The same but with an '=' token between the flag and argument associated with it)
* ```--flag``` (This is a string flag)
* ```--flag arg1``` (String flag with an argument)
* ```--flag=arg1``` (Self explanatory)

These are the only supported formats. If you pass -c flag and try to query a 'string flag' rather than a 'character flag', then the library will not return any valid result.

In short, ```-c != --c```.
