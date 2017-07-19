package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Read a CSV and allow access to each line of the file (a CSVFileLine object).
 * Note: Pre-reads entire file when created so may affect performance.
 */
public class CSVFileReader implements Iterable<CSVFileLine> {
	List<CSVFileLine> lines = new LinkedList<CSVFileLine>();

	public CSVFileReader(File file) throws FileNotFoundException {
		FileReader reader = new FileReader(file);
		Scanner scanner = new Scanner(reader);
		
		skipHeader(scanner);
		readAllLines(scanner);
		
		scanner.close();
	}
	private void skipHeader(Scanner scanner) {
		if (scanner.hasNextLine()) {
			scanner.nextLine();
		}
	}
	private void readAllLines(Scanner scanner) {
		while (scanner.hasNextLine()) {
			String text = scanner.nextLine();
			CSVFileLine fileLine = new CSVFileLine(text);
			lines.add(fileLine);
		}
	}
	
	public Iterator<CSVFileLine> iterator() {
		return Collections.unmodifiableList(lines).iterator();
	}

}
