package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contain information from a single line from the input CSV file.
 */
public class CSVFileLine {

	public static final int COLUMN_SEMESTER = 0;
	public static final int COLUMN_SUBJECT = 1;
	public static final int COLUMN_CATALOG_NUMBER = 2;
	public static final int COLUMN_LOCATION = 3;
	public static final int COLUMN_ENROLLMENT_CAP = 4;
	public static final int COLUMN_ENROLLMENT_TOTAL = 5;
	public static final int COLUMN_INSTRUCTOR = 6;
	public static final int COLUMN_COMPONENT = 7;
	public static final int COLUMN_TITLE = 8;
	public static final int COLUMN_DESCRIPTION = 9;

	
	private String[] data;
	 
	public CSVFileLine(String line) {
		String[] splitOnComma = line.split(",");
		
		// Merge cells over regions which were quoted strings.
		List<String> merged = new ArrayList<String>();
		boolean merging = false;
		for (int i = 0; i < splitOnComma.length; i++) {
			if (merging) {
				String last = merged.get(merged.size()-1);
				last += ",";
				last += splitOnComma[i];
				last = last.replace("\"",  "");
				merged.set(merged.size() - 1, last);
				if (splitOnComma[i].endsWith("\"")) {
					merging = false;
				}
			} else {
				String str = splitOnComma[i].replace("\"",  "");
				merged.add(str);
			}

			if (splitOnComma[i].startsWith("\"")) {
				merging = true;
			}
		}
		
		// Dump back into array:
		data = new String[merged.size()];
		merged.toArray(data);
		
	}
	
	public String get(int column) {
		if (column < data.length) {
			return getColumnData(column);
		} else { 
			return "";
		}
	}

	public int getInt(int column) {
		return asInt(get(column));
	}

	private String getColumnData(int column) {
		String value = data[column];
		if (column == COLUMN_INSTRUCTOR) {
			if (value.contains("(null)")) {
				value = "";
			}
		}
		return value;
	}

	private int asInt(String str) {
		try { 
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			System.out.println("Number conversion problem for string '" + str + "'");
			return 0;
		}
	}

}
