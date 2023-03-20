
package elections.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Vector;

import elections.model.CardBox.eCardBoxType;

public class Elections implements Serializable{
	protected int month;
	protected int year; 
	private Set <Citizen> registeredCitizens;
	protected int numOfCitizens;
	public Vector <Party >partyList;
	protected int numOfPartyList;
	public  Vector<CardBox<?>> allCardBoxes;
	protected int numOfCardBoxes;


	public Elections(Set <Citizen> registeredCitizens,int numOfCitizens, Vector <Party> partyList,
			int numOfPartyList, Vector<CardBox<?>> b, int numOfCardBoxes) {
		this.month=LocalDate.now().getMonthValue();
		this.year=LocalDate.now().getYear();
		this.registeredCitizens= registeredCitizens;
		this.numOfCitizens=numOfCitizens;
		this.partyList = partyList;
		this.numOfPartyList=numOfPartyList;
		this.allCardBoxes=b;
		this.numOfCardBoxes=numOfCardBoxes;

	}

	public Vector<Party> getPartyList() {
		return partyList;
	}

	public boolean addParty(Party p) {
		if(!partyList.isEmpty()) {
			for(int i=0; i<partyList.size(); i++) {
				if(p.getPartyName().equals(partyList.elementAt(i).getPartyName())) {
					System.out.println("Party is already registered!");
					return false;
				}
			}
		}

		partyList.add(p);
		updateVotesArray();
		numOfPartyList++;
		System.out.println("Party was added successfully");
		return true;
	}

	public void initialize() {
		for(int p=0;p<partyList.size();p++) {
			for(int v=0; v<partyList.elementAt(p).getCandidates().size();v++) {
				int rand= (int)(120 * Math.random());
				partyList.elementAt(p).getCandidates().elementAt(v).setVoteRate(rand);
			}
			partyList.elementAt(p).setPrimaries();
		}
		for(int i=0;i<registeredCitizens.size();i++) {
			setBallotForCitizen(registeredCitizens.get(i));
		}
		updateVotesArray();
	}


	public boolean checkCardBox(CardBox<?> box) {
		for(int i=0; i<allCardBoxes.size();i++) {
			if(allCardBoxes.elementAt(i).getAdress().equalsIgnoreCase(box.getAdress())) {
				if(allCardBoxes.elementAt(i).getType().equals(box.getType())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean addCardBox(CardBox<?> box) {
		if(checkCardBox(box)==false) {
			allCardBoxes.add(box);
			numOfCardBoxes++;
			allCardBoxes.elementAt(numOfCardBoxes-1).setVotesArray(numOfPartyList);
			System.out.println("Card box was added successfully");
			return true;
		}
		else {
			System.out.println("Cardbox is already registered in system!");
			return false;
		}

	}

	public boolean addCitizen(Citizen c) throws RegisteredCitizenException {
		for(int i=0; i<registeredCitizens.size(); i++) {
			if(registeredCitizens.get(i).id==c.getId()) {
				System.out.println("Citizen is already registered!");
				return false;
			}
		}
		registeredCitizens.addCitizen(c);
		setBallotForCitizen(c);
		numOfCitizens++;
		System.out.println("Citizen was added successfully");
		return true;
	}

	public boolean checkCandidate(Candidate newCandidate) {
		if(partyList!=null) {
			for(int i=0; i<partyList.size(); i++) {
				if(!(partyList.elementAt(i).getCandidates()==null)) {
					for(int j=0;j<partyList.elementAt(i).getCandidates().size();j++) {
						if(partyList.elementAt(i)!=null) {
							if(newCandidate.getId()==partyList.elementAt(i).getCandidates().elementAt(j).getId()) {
								System.out.println("Candidate is already registered at " + partyList.elementAt(i).getPartyName() + " party" );
								return false;
							}
						}
					}
				}
			}
		}
		return true;

	}
	public void setBallotForCitizen(Citizen c) {
		if(allCardBoxes!=null) {
			int	min = 0, max = numOfCardBoxes-1,range = max - min + 1;
			boolean temp=false;

			while(!temp) {
				int rand= (int)(range * Math.random() + min);
				c.setBallotBox(allCardBoxes.elementAt(rand).serialNumber);

				if(c.isSoldier()){
					if(c.isInQuarantine()) {
						if(c.isProtectiveSuit()) {
							if(allCardBoxes.elementAt(rand).getType().equals(eCardBoxType.CORONAMILITARY)) {
								allCardBoxes.elementAt(rand).addLocalCitizen(c);
								temp=true;

							}
						}
						else
							break;
					}
					else if(allCardBoxes.elementAt(rand).getType().equals(eCardBoxType.MILITARY)) {
						allCardBoxes.elementAt(rand).addLocalCitizen(c);
						temp=true;	
					}

				}
				else if(!(c.isSoldier())) {
					if(c.isInQuarantine()) {
						if(c.isProtectiveSuit()) {
							if((allCardBoxes.elementAt(rand).getType().equals(eCardBoxType.CORONA))) {
								allCardBoxes.elementAt(rand).addLocalCitizen(c);
								temp=true;	
							}
						}
						else
							break;
					}
					else if((allCardBoxes.elementAt(rand).getType().equals(eCardBoxType.REGULAR))) {
						allCardBoxes.elementAt(rand).addLocalCitizen(c);
						temp=true;	
					}
				}
			}
		}
	}


	public boolean checkIfIdEx(String id) {
		for(int i=0; i<registeredCitizens.size(); i++) {
			if(registeredCitizens.get(i).getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public void updateVotesArray() {
		for(int i=0; i<allCardBoxes.size(); i++) {
			if(allCardBoxes.elementAt(i)!=null) {
				allCardBoxes.elementAt(i).setVotesArray(numOfPartyList);
			}
		}

	}

	public boolean checkIfNoCandidates(int vote) {
		if(partyList.elementAt(vote-1).getCandidates().isEmpty()) {
			return true;
		}
		return false;
	}

	public int getNumOfPartyList() {
		return numOfPartyList;
	}
	public boolean voteKeeper(int vote, int i) {
		partyList.elementAt(vote-1).setNumOfVotes();
		allCardBoxes.elementAt(i).setSingleVote(vote-1);
		return true;
	}

	public int indexPartyName(String par) {
		for(int i=0; i<partyList.size(); i++) {
			if(partyList.elementAt(i).getPartyName()==par)
				return i;
		}
		return 0;
	}
	public Vector<Integer> countVotes() {
		Vector<Integer> finalResults=new Vector<Integer>();

		for(int i=0;i<partyList.size();i++){
			finalResults.add(0);
		}

		for(int v=0; v<allCardBoxes.size(); v++) {
			for(int j=0; j<partyList.size(); j++) {
				int res=finalResults.elementAt(j);
				res+=allCardBoxes.elementAt(v).getVotesArray().elementAt(j);
				finalResults.setElementAt((res), j);
			}
		}
		return finalResults;
	}
	public int checkWinner(Vector <Integer> finalResults) {

		int temp=0, winner=0;
		for(int k=0;k<finalResults.size();k++) {
			if(finalResults.elementAt(k)>temp) {
				temp=finalResults.elementAt(k);
				winner=k;
			}
		}
		return winner;

	}

	public boolean RandomElections() {
		for(int i=0; i<numOfCardBoxes; i++) {
			for(int m=0;m<getAllCardBoxes().elementAt(i).getLocalCitizens().size();m++) {
				int vote = (int) (getPartyList().size() * Math.random()+1);
				while(checkIfNoCandidates(vote)) {
					vote = (int) (getPartyList().size() * Math.random()+1);
				}
				if(vote<1 || vote >getPartyList().size()+1) {
					
				}
				else {
					voteKeeper(vote, i);
				}

			}
			getAllCardBoxes().elementAt(i).SetPercentage();
			
		}
		int winner=checkWinner(countVotes());
		WinnerAnounce(countVotes(), winner);
		return true;
	}


	//check if there is a tie
	public StringBuffer WinnerAnounce(Vector <Integer> results, int winner) {
		StringBuffer sb = new StringBuffer();
		for(int j=0; j<partyList.size(); j++) {
			results.setElementAt(0,j);
		}
		int counter=0;
		for(int t=0;t<results.size();t++) {
			if(results.elementAt(t).equals(results.elementAt(winner))) {
				counter++;
			}
		}
		if(counter>1) {
			sb.append("\nThere is a tie between " + counter + " partys: ");
			for(int i=0;i<results.size();i++) {
				if(results.elementAt(i).equals(results.elementAt(winner))) {
					sb.append("\n"+partyList.elementAt(i).getPartyName() + "    ");
				}
			}
			sb.append("\nEach of the partys recieved " + results.elementAt(winner) + " votes");
		}
		else
			sb.append("\nThe winning party is " + partyList.elementAt(winner).getPartyName() + " !!! with " + results.elementAt(winner) + " votes, congratulation!"); 

		return sb;
	}

	public boolean checkParty(String name) {
		for(int i=0; i<partyList.size();i++) {
			if(partyList.elementAt(i).getPartyName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public Set<Citizen> getRegisteredCitizens() {
		return registeredCitizens;
	}

	public void setRegisteredCitizens(Set<Citizen> registeredCitizens) {
		this.registeredCitizens = registeredCitizens;
	}


	public Vector<CardBox<?>> getAllCardBoxes() {
		return allCardBoxes;
	}


	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}


	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("\n------------------------------------------\n"+"Party list: "+ "\n===========" );
		for (int i = 0; i <partyList.size(); i++) { 
			stringBuffer.append("\n\n"+partyList.elementAt(i).toString() + "\n");
		}
		stringBuffer.append("\n------------------------------------------\n"+"All card boxes:\n=============== ");
		for (int i = 0; i <allCardBoxes.size(); i++) { 
			stringBuffer.append("\n\n"+allCardBoxes.elementAt(i).getType()+ " card box\n-----------------\n"+"Serial number: "+allCardBoxes.elementAt(i).getSerialNumber()+"\nAddress: "+allCardBoxes.elementAt(i).address+ "\n"+"Percentage of voters: "+ allCardBoxes.elementAt(i).percentageOfVoters +"%\n");
			for(int j=0; j<allCardBoxes.elementAt(i).numOfParties; j++) {
				stringBuffer.append(partyList.elementAt(j).getPartyName() + ": " + allCardBoxes.elementAt(i).getVotesArray().elementAt(j)+ " votes \n" );
			}
		}

		return stringBuffer.toString();
	}

	//write to file
	public void save() throws FileNotFoundException, IOException{
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("elections.date"));
		outFile.writeObject(this);
		outFile.close();
	}
	//file reading
	public void read() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream inFile= new ObjectInputStream(new FileInputStream("elections.date"));
		Elections e = (Elections)inFile.readObject();
		int winner=e.checkWinner(e.countVotes());
		e.WinnerAnounce(e.countVotes(), winner);
		System.out.println(e);
		inFile.close();
	}

}
