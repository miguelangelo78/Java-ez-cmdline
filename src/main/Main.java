package main;

import com.cmd.*;

public class Main {

	public static void main(String[] args) {
		/* Setup supported Command Line flags */
		CMDLine.addFlags(
			"flag1", "This is the first flag.",
			"flag2", "Another flag.",
			'd',     "A single character flag.",
			'c',     "<val1>;You can specify what values this flag supports."
		);
		
		/* Execute help method with the following settings */
		CMDLine.setHelpCondition(
			CMDLine.HelpCondition.WHEN_NOARGS,        /* Call help() when user does not provide arguments */
			CMDLine.HelpResponse.HELP_AND_QUIT,       /* When help() is called, also quit the program     */
			CMDLine.HelpResponse.HELP_WARNONLY_NOHELP /* On an invalid flag, only warn the user           */
		);

		/* Set usage message */
		CMDLine.setUsage("java -cp bin main.Main [options]");

		/* Parse Command Line arguments */
		CMDLine.parse(args);

		/* Check flags */
		System.out.println("Flag 'flag1': " + CMDLine.hasOption("flag1"));
		System.out.println("Flag 'flag2': " + CMDLine.hasOption("flag2"));
		System.out.println("Flag 'd': " + CMDLine.hasOption('d'));
		System.out.println("Flag 'c': " + CMDLine.hasOption('c'));
		
		CMDLine.Option opt = CMDLine.query('c');
		if(opt != null)
			System.out.println("Flag 'c' value: " + opt.value);
		
		/* Display the flags the library supports */
		System.out.println("\nSupported flags: ");
		CMDLine.dumpSupported();
		
		/* Dump parse results */
		System.out.println("\nParsed flags: ");
		CMDLine.dumpParsed();
	}
}
