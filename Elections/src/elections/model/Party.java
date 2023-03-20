package elections.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;

public class Party implements Serializable{
	private String partyName;
	public enum PelegType{RIGHTHAND, LEFTHAND, CENTER};
	private int establishedYear;
	private PelegType pelType;
	private Vector <Candidate> candidates;
	protected int numOfCandidate;
	protected int numOfVotes;

	public Party(String partyName,int establishedYear, PelegType pelType,Vector <Candidate> candidates ,int numOfCandidate) {
		this.partyName = partyName;
		this.establishedYear = establishedYear;
		this.pelType=pelType;
		this.candidates=candidates;
		this.numOfCandidate=numOfCandidate;
		numOfVotes=0;
	}

	public String getPartyName() {
		return partyName;
	}

	public int getEstablishedYear() {
		return establishedYear;
	}

	public PelegType getPelType() {
		return pelType;
	}

	public Vector<Candidate> getCandidates() {
		return candidates;
	}

	public int getNumOfCandidates() {
		return numOfCandidate;
	}

	public void addCandidate(Candidate can, Elections e) {
		if(e.checkCandidate(can)==true){
			this.candidates.add(can);
			this.numOfCandidate++;
			System.out.println("Candidate was added successfully");
		}
		else
			System.out.println("Candidate could not be added");
	}

	public void setPrimaries() {
		if(numOfCandidate>0) {
			for(int i=numOfCandidate-1; i>0;i--) {
				for(int j=0;j<i;j++) {
					if(candidates.elementAt(j).getVoteRate()<candidates.elementAt(j+1).getVoteRate()) {
						Candidate temp=candidates.elementAt(j);
						candidates.setElementAt(candidates.elementAt(j+1), j);
						candidates.setElementAt(temp, j+1);
					}
				}
			}
		}
	}

	public void setNumOfVotes() {
		this.numOfVotes++;
	}

	public int getNumOfVotes() {
		return numOfVotes;
	}
	public void resetVotes() {
		this.numOfVotes=0;
	}
	public boolean equals(Object other) {
		if(!(other instanceof Party))
			return false;

		Party p = (Party)other;
		return p.partyName.equals(partyName) && p.numOfVotes== numOfVotes;
	}


	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append(partyName +"\n"+ "---------------------" +"\n"+ "Established year: " + establishedYear+ "\n"
				+  "Peleg type: "+ pelType + "\n"+ "Candidates:" + "\n");
		if(!candidates.isEmpty()) {
			for (int i = 0; i <candidates.size(); i++) { 
				if(candidates.elementAt(i)!=null)
					stringBuffer.append((i+1)+". "+candidates.elementAt(i).toString() + "\n");
			}
		}
		stringBuffer.append("Num of votes: " +numOfVotes);
		return stringBuffer.toString();
	}
}
