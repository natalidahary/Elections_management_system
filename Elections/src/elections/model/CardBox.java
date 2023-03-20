package elections.model;

import java.io.Serializable;
import java.util.Vector;

public class CardBox <T extends Citizen> implements Serializable {
	public enum eCardBoxType {REGULAR, CORONA, MILITARY, CORONAMILITARY} 
	public eCardBoxType type;
	protected int serialNumber;
	protected static int serialGenerator=1000;
	protected String address;
	protected double percentageOfVoters=0;
	private Vector <T> localCitizens;
	protected int numOfLocalCitizens;
	private Vector <Integer> votesPerParty;
	protected int numOfParties;


	public CardBox(String adress, int numOfLocalCitizens, eCardBoxType type) {
		this.address = adress;
		percentageOfVoters=0;
		this.localCitizens=new Vector <T>();
		this.numOfLocalCitizens=numOfLocalCitizens;
		serialNumber=(serialGenerator++);
		this.type=type;
		this.votesPerParty=new Vector<Integer>();
	}

	public CardBox() {
		percentageOfVoters=0;
		this.localCitizens=new Vector <T>();
		this.numOfLocalCitizens=0;
		serialNumber=(serialGenerator++);
	}

	public void addLocalCitizen(Citizen newCitizen) {
		localCitizens.addElement((T)newCitizen);
		numOfLocalCitizens++;
	}

	public void SetPercentage() {
		if(!(localCitizens==null)) {
			int voted=0;
			if(votesPerParty!=null) {
				for(int i=0; i<votesPerParty.size();i++) {
					voted+=votesPerParty.elementAt(i);
				}
				if(numOfLocalCitizens!=0)
				this.percentageOfVoters=(voted*100)/(numOfLocalCitizens);
			}
		}
	}

	public String getAdress() {
		return address;
	}

	public void setAdress(String adress) {
		this.address = adress;
	}

	public int getNumOfLocalCitizens() {
		return numOfLocalCitizens;
	}

	public void setNumOfLocalCitizens(int numOfLocalCitizens) {
		this.numOfLocalCitizens = numOfLocalCitizens;
	}
	public int getSerialNumber() {
		return serialNumber;
	}

	public Vector<T> getLocalCitizens() {
		return localCitizens;
	}

	public void setVotesArray(int numOfParties) {
		this.numOfParties=numOfParties;
		for(int i=0;i<numOfParties;i++) {
			votesPerParty.add(0);
		}
	}
	
	public eCardBoxType getType() {
		return type;
	}

	public void setType(eCardBoxType type) {
		this.type = type;
	}
	
	public void setSingleVote(int v) {
		if(votesPerParty.elementAt(v)==null) {
			votesPerParty.setElementAt(1, v);
		}
		else {
		int res=votesPerParty.elementAt(v);
		res++;
		votesPerParty.setElementAt(res, v);
		}
	}
	

	public Vector<Integer> getVotesArray() {
		return votesPerParty;
	}
	public boolean equals(Object other) {
		if(!(other instanceof CardBox))
			return false;

		CardBox<Citizen> cb = (CardBox)other;
		return cb.address.equals(address);
	}
	
	
	
	public int getNumOfParties() {
		return numOfParties;
	}



	public String getAddress() {
		return address;
	}

	public Vector<Integer> getVotesPerParty() {
		return votesPerParty;
	}

	public double getPercentageOfVoters() {
		return percentageOfVoters;
	}

	@Override
	public String toString() {
		//return "Card box address: " + address + "Percentage  of voters: " + percentageOfVoters +"Serial number: " +serialNumber+ "\n"+ "Local citizens"+"\n" ;

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Card box number: " +serialNumber+ "\n" + "---------------------" +"\n"+ "Type: " +type+ "\n"+"Address: " + address + " , "+ "Percentage of voters: " + percentageOfVoters +  "%\n" + "Citizens:"+"\n");
		for (int i = 0; i <localCitizens.size(); i++) { 
			if(localCitizens.elementAt(i)!=null)
				stringBuffer.append("\n"+(i+1)+ ". " +localCitizens.elementAt(i).toString() + "\n");
		}
		return stringBuffer.toString();
	}

}
