package model;

/**
 * Represent a specific semester, such as Spring 2013.
 */
public class Semester implements Comparable<Semester> {
	private static final int SEMESTER_CODE_BASE_YEAR = 1900;
	private static final int SEMESTER_CODE_OFFSET = 10;

	public static final int SEMESTER_NUMBER_SPRING = 1;
	public static final int SEMESTER_NUMBER_SUMMER = 4;
	public static final int SEMESTER_NUMBER_FALL = 7;
	
	private int year;
	private int semesterNumber; 
	
	public Semester(int semesterCode) {
		semesterNumber = semesterCode % SEMESTER_CODE_OFFSET;
		year = semesterCode / SEMESTER_CODE_OFFSET + SEMESTER_CODE_BASE_YEAR;
	}
	
	public Semester(int semesterNumber, int year) {
		this.year = year;
		this.semesterNumber = semesterNumber;
	}
	
	public Semester(String semesterStr) {
		this(Integer.parseInt(semesterStr));
	}
	
	public int getSemesterCode() {
		return (year - SEMESTER_CODE_BASE_YEAR) * SEMESTER_CODE_OFFSET + semesterNumber;
	}
	
	public int getYear() {
		return year;
	}
	public int getSemesterNumber() {
		return semesterNumber;
	}
	
	public Semester getNextSemester() {
		int newSemester = semesterNumber;
		int newYear = year;
		switch (semesterNumber) {
		case SEMESTER_NUMBER_SPRING:	
			newSemester = SEMESTER_NUMBER_SUMMER; 
			break;
		case SEMESTER_NUMBER_SUMMER:	
			newSemester = SEMESTER_NUMBER_FALL; 
			break;
		case SEMESTER_NUMBER_FALL:	
			newSemester = SEMESTER_NUMBER_SPRING; 
			newYear++;
			break;
		default:
			assert false;
		}
		return new Semester(newSemester, newYear);
	}
	
	public boolean isSpring() {
		return getSemesterNumber() == SEMESTER_NUMBER_SPRING;
	}
	public boolean isSummer() {
		return getSemesterNumber() == SEMESTER_NUMBER_SUMMER;
	}
	public boolean isFall() {
		return getSemesterNumber() == SEMESTER_NUMBER_FALL;
	}

	@Override
	public int compareTo(Semester other) {
		final int BEFORE = -1;
		if (other == null) {
			return BEFORE;
		}
		if (year != other.getYear()) {
			return year - other.getYear();
		} else {
			return semesterNumber - other.getSemesterNumber();
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		
		if (!(other instanceof Semester)) {
			return false;
		}
		
		return compareTo((Semester)other) == 0;
	}
	
	@Override
	public String toString() {
		return String.valueOf(getSemesterCode());
	}
}
