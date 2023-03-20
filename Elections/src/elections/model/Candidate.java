package elections.model;

public class Candidate extends Citizen{

	protected String party;
	protected int voteRate;



	public Candidate(String name, String id, int yearOfBirth, int daysOfSickness, boolean isInQuarantine,
			boolean protectiveSuit, String party) throws Exception {
		super(name, id, yearOfBirth, daysOfSickness, isInQuarantine, protectiveSuit);
		this.party = party;
		voteRate = 0;
	}

	public String getP() {
		return party;
	}

	public void setP(String party) {
		this.party = party;
	}

	public int getVoteRate() {
		return voteRate;
	}

	public void setVoteRate(int voteRate) {
		this.voteRate = voteRate;
	}
	public boolean equals(Object other) {
		if(!(other instanceof Candidate))
			return false;
		
		if(!(super.equals(other)))
			return false;
		
		Candidate c = (Candidate)other;
			return c.party.equals(party) && voteRate== c.voteRate;
	}
	@Override
	public String toString() {
		return  name + "-" + "\nparty: " + party + "\nvoteRate: " + voteRate  + "\nid: " + id
				+ "\nyearOfBirth: " + yearOfBirth + "\nballotBox: " + ballotBox;
	}
}