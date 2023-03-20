package mvc;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

import elections.model.Candidate;
import elections.model.CardBox;
import elections.model.Citizen;
import elections.model.Elections;
import elections.model.IdException;
import elections.model.InfectedWithCorona;
import elections.model.Party;
import elections.model.RegisteredCitizenException;
import elections.model.Set;
import elections.model.Soldier;
import mvc.elections.listeners.ElectionsEventsListenable;
import mvc.elections.listeners.ElectionsViewListenable;
import elections.model.CardBox.eCardBoxType;
import elections.model.Party.PelegType;



public class Model {

	private Vector<Elections> allElections;
	public static Vector <ElectionsEventsListenable> listeners;
	private static int countElections=0;
	private static Elections e1;

	public Model() throws IdException{
		this.allElections=new Vector<Elections>();

	}

	public void setElections(Elections e) {
		e1=e;
		allElections.add(e1);
	}

	public static void registerListener(ElectionsEventsListenable l) {
		listeners.add(l);
	}

	public Elections getElection() {
		return e1;
	}
	public static boolean addCardBox(String adress, eCardBoxType type) {
		CardBox<?> box;
		box=new CardBox<>(adress,0,type);
		return e1.addCardBox(box);
	}

	public boolean addCandidate(String name,String id, int yearOfBirth,int days, boolean quarantine, boolean protectiveSuit, String party) throws Exception {
			Candidate c = new Candidate(name, id, yearOfBirth, days, quarantine, protectiveSuit,party);
		
		boolean flag=false;
		do{
			try {
				Candidate.setId(id);
				flag=true;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			} 

		}while(flag==false);
		if(e1.checkIfIdEx(id)) {
			System.out.println("Id is already registered in system");
		}

		try {
			Candidate.checkAge(yearOfBirth);
			flag=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		if(quarantine==true) {
			if(protectiveSuit==true) {
				try {
					c=new Candidate(name, id, yearOfBirth,days, true, true,party);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else
				try {
					c=new Candidate(name, id, yearOfBirth,days, true, false,party);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} else
			try {
				c=new Candidate(name, id, yearOfBirth,0, false, false,party);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		int rand= (int)(120 * Math.random());
		c.setVoteRate(rand);


		for(int i=0; i<e1.partyList.size();i++) {
			if(e1.partyList.elementAt(i).getPartyName().equals(party)) {
				e1.getPartyList().elementAt(i).addCandidate(c, e1);
				e1.getPartyList().elementAt(i).setPrimaries();
			}
		}

		return e1.addCitizen(c);

	}

	public boolean addCitizen(String name,String id, int yearOfBirth,int days, boolean quarantine, boolean protectiveSuit) throws RegisteredCitizenException{
		Citizen c= null;
		Soldier s= null;
		InfectedWithCorona i = null;

		boolean flag=false;
		do{
			try {
				Citizen.setId(id);
				flag=true;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

		}while(flag==false);

		if(e1.checkIfIdEx(id)) {
			System.out.println("iD already registered");
		}
		try {
			Citizen.checkAge(yearOfBirth);
			flag=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
		}

		//check if citizen is a soldier
		if(checkIfSoldier(yearOfBirth)==true) {
			if(quarantine==true) {
				if(protectiveSuit==true) {
					try {
						s=new Soldier(name, id, yearOfBirth,days,true, true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else
					try {
						s=new Soldier(name, id, yearOfBirth,days, true, false);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else
				try {
					s=new Soldier(name, id, yearOfBirth,0, false, false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			return e1.addCitizen(s); 
		} 
		else {
			if(quarantine==true) {
				if(protectiveSuit==true) {
					try {
						i=new InfectedWithCorona(name, id, yearOfBirth,days, true, true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else
					try {
						i=new InfectedWithCorona(name, id, yearOfBirth,days, true, false);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				return e1.addCitizen(i); 

			}
			else {
				try {
					c=new Citizen(name, id, yearOfBirth,0, false, false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return e1.addCitizen(c); 
			}		
		}
	}

	public boolean addParty(Party p) {
		return e1.addParty(p);
	}
	public static void setListener(ElectionsEventsListenable l) {
		listeners.add(l);
	}

	public static boolean checkIfSoldier(int yearOfBirth) {
		if(((LocalDate.now().getYear())-(yearOfBirth))>=18 && 
				((LocalDate.now().getYear())-(yearOfBirth))<=21) {
			return true;
			//System.out.println("citizen is soldier");
		}
		else {
			return false;
			//	System.out.println("citizen is not a soldier");
		}
	}
	public Vector <String> getPartyName() {
		Vector <String> partyNames=new Vector<String>();
		if(!e1.partyList.isEmpty()) {
			for(int i=0;i<e1.getPartyList().size();i++) {
				partyNames.add(e1.getPartyList().elementAt(i).getPartyName());
			}
		}
		return partyNames;
	}

	public int amountChoicers() {
		return e1.getRegisteredCitizens().size();
	}

	public String citizenName(int i) {
		return e1.getRegisteredCitizens().getAllCitizens().elementAt(i).getName();
	}
	
	public boolean addChoice(int p, String choose) {
		int vote=e1.indexPartyName(choose);
		for(int i=0; i<e1.allCardBoxes.size(); i++) {
			for(int j=0; j<e1.getAllCardBoxes().elementAt(i).getLocalCitizens().size(); j++) {
				if(e1.getAllCardBoxes().elementAt(i).getLocalCitizens().elementAt(j).getId()==e1.getRegisteredCitizens().getAllCitizens().elementAt(p).getId()) {
					return e1.voteKeeper(vote+1, i);
				}					
			}
		}
		return false;
	}
	
	public boolean elections() {
		e1.RandomElections();
		return true;
	}
	public String printCardBoxes(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("\n------------------------------------------\n"+"All card boxes:\n=============== ");
		for (int i = 0; i <e1.allCardBoxes.size(); i++) { 
			stringBuffer.append("\n\n"+e1.allCardBoxes.elementAt(i).getType()+ " card box\n-----------------\n"+"Serial number: "+e1.allCardBoxes.elementAt(i).getSerialNumber()+"\nAddress: "+e1.allCardBoxes.elementAt(i).getAdress()+ "\n"+"Percentage of voters: "+ e1.allCardBoxes.elementAt(i).getPercentageOfVoters() +"%\n");
			for(int j=0; j<e1.allCardBoxes.elementAt(i).getNumOfParties(); j++) {
				stringBuffer.append(e1.partyList.elementAt(j).getPartyName() + ": " + e1.allCardBoxes.elementAt(i).getVotesArray().elementAt(j)+ " votes \n" );
			}
		}
		return stringBuffer.toString();

	}

	public String printCitizens() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("\n------------------------------------------\n"+"All citizens:\n=============== ");
		for(int i=0; i<e1.getRegisteredCitizens().getAllCitizens().size();i++) {
			stringBuffer.append("\n\n"+e1.getRegisteredCitizens().getAllCitizens().elementAt(i).getName()+":\nID"+ e1.getRegisteredCitizens().getAllCitizens().elementAt(i).getId()+ "\nyearOfBirth: " + e1.getRegisteredCitizens().getAllCitizens().elementAt(i).getYearOfBirth() + "\nballotBox: " + e1.getRegisteredCitizens().getAllCitizens().elementAt(i).getBallotBox()
					+ "\nisInQuarantine: " + e1.getRegisteredCitizens().getAllCitizens().elementAt(i).isInQuarantine() + "\nisSoldier: "
					+ e1.getRegisteredCitizens().getAllCitizens().elementAt(i).isSoldier());
		}
		return stringBuffer.toString();
	}

	public String printParty() {
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=0; i<e1.partyList.size(); i++) {
			stringBuffer.append(e1.partyList.elementAt(i).getPartyName() +"\n"+ "---------------------" +"\n"+ "Established year: " + e1.partyList.elementAt(i).getEstablishedYear()+ "\n"
					+  "Peleg type: "+ e1.partyList.elementAt(i).getPelType() + "\n"+ "Candidates:" + "\n");
			if(!e1.partyList.elementAt(i).getCandidates().isEmpty()) {
				for (int j=0; j<e1.partyList.elementAt(i).getCandidates().size(); j++) {
					if(!(e1.partyList.elementAt(i).getCandidates().elementAt(j)==null))
					stringBuffer.append((j+1)+". "+e1.partyList.elementAt(i).getCandidates().elementAt(j).toString() + "\n");
				}
				stringBuffer.append("Num of votes: " +e1.partyList.elementAt(i).getNumOfVotes()+"\n\n");
			}
		}
		return stringBuffer.toString();
	}

	public String printResults() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("FINAL RESULTS ELECTIONS MONTH " + e1.getMonth()+" "+(e1.getYear()) +"! :" +"\n"+ "-------------------------------------");
		int winner=e1.checkWinner(e1.countVotes()); //recieve the one with highest amount of votes
		//check if there is a tie
		stringBuffer.append(e1.WinnerAnounce(e1.countVotes(), winner));
		//
		stringBuffer.append(e1.toString());

		return stringBuffer.toString();
	}

}


