package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvFileController {
	Scanner inputStream;

	public CsvFileController(String fName) {
		String fileNameDefined = fName;
		File file = new File(fileNameDefined);

		try {
			// -read from filePooped with Scanner class
			inputStream = new Scanner(file);
			// hashNext() loops line-by-line
			/**
			 * while (inputStream.hasNext()) { // read single line, put in
			 * string String data = inputStream.next();
			 * System.out.println(data); }
			 **/
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public Scanner getScanner() {
		return inputStream;
	}
}
