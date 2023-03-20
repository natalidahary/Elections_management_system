package elections.model;

public class Soldier extends Citizen implements CarryWeaponable {

	public Soldier(String name, String id, int yearOfBirth, int daysOfSickness, boolean isInQuarantine,
			boolean protectiveSuit) throws Exception {
		super(name, id, yearOfBirth, daysOfSickness, isInQuarantine, protectiveSuit);
		// TODO Auto-generated constructor stub
		carryWeapon();
	}

	@Override
	public void carryWeapon() {
		// TODO Auto-generated method stub
		
	}
	

}
