package elections.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Citizen implements Serializable {

	protected String name;
	protected String id;
	protected int yearOfBirth;
	protected int ballotBox;
	protected int daysOfSickness;
	private boolean isInQuarantine;
	private boolean protectiveSuit;
	private boolean isSoldier;
	
	
	public Citizen(String name, String id, int yearOfBirth,int daysOfSickness, boolean isInQuarantine, boolean protectiveSuit) throws Exception {
		this.name = name;
		setId(id);
		this.id=id;
		this.yearOfBirth = yearOfBirth;
		this.daysOfSickness=daysOfSickness;
		this.isInQuarantine = isInQuarantine;
		this.protectiveSuit=protectiveSuit;
		setSoldier(isSoldier);
		this.ballotBox=0;
	}

	public static void setId(String id) throws Exception {
		if(id.length()<9 ||id.length()>9 || isIdNumbers(id)==false) {
			throw new Exception("ID has to contain 9 digits");
		}
	}
	public static boolean isIdNumbers(String num) {
			for(int i=0; i<num.length(); i++) {
				if(num.charAt(i)< '0' || num.charAt(i) > '9')
					return false;
			}
			return true;
		}
	

	public void setBallotBox(int ballotBox) {
		this.ballotBox = ballotBox;
	}

	public void setInQuarantine(boolean isInQuarantine) {
		this.isInQuarantine=isInQuarantine;
	}

	public void setProtectiveSuit(boolean protectiveSuit) {
		if(!isInQuarantine) {
			protectiveSuit=false;
		}
		else
			this.protectiveSuit=true;		
	}

	public boolean isProtectiveSuit() {
		return protectiveSuit;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public int getBallotBox() {
		return ballotBox;
	}

	public boolean isInQuarantine() {
		return isInQuarantine;
	}

	public static void checkAge( int yearOfBirth) throws Exception{
		if(((LocalDate.now().getYear())-(yearOfBirth))<18) {
			throw new Exception("Must be at least 18 years old in order to vote");
		}
	}

	public boolean isSoldier() {
		return isSoldier;
	}

	public void setSoldier(boolean isSoldier) {
		if(((LocalDate.now().getYear())-(yearOfBirth))>=18 && 
				((LocalDate.now().getYear())-(yearOfBirth))<=21) {
			this.isSoldier=true;
			//System.out.println("citizen is soldier");
		}
		else {
			this.isSoldier=false;
		//	System.out.println("citizen is not a soldier");
		}
	}

	public boolean equals(Object other) {
		if(!(other instanceof Citizen))
			return false;
		
		Citizen ct = (Citizen)other;
		return ct.name.equals(name) && ct.id.equals(id) ;
	}
	@Override
	public String toString() {
		return  name + ":\nID: " + id + "\nyearOfBirth: " + yearOfBirth + "\nballotBox: " + ballotBox
				+ "\nisInQuarantine: " + isInQuarantine + "\nisSoldier: "
				+ isSoldier ;
	}
}
