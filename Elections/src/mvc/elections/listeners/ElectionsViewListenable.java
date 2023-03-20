package mvc.elections.listeners;

import java.util.Vector;

import elections.model.AgeException;
import elections.model.Candidate;
import elections.model.CardBox;
import elections.model.CardBox.eCardBoxType;
import elections.model.Citizen;
import elections.model.IdException;
import elections.model.RegisteredCitizenException;
import elections.model.Party.PelegType;

public interface ElectionsViewListenable {

	boolean viewAddCardBox(String adress, eCardBoxType type);
	boolean viewAddCitizens(String name, String id, int yearOfBirth,int daysOfSickness, boolean isInQuarantine, boolean protectiveSuit) throws RegisteredCitizenException, AgeException, IdException;
	boolean viewAddParty(String partyName,int establishedYear, PelegType pelType,Vector <Candidate> candidates ,int numOfCandidate);
	boolean viewAddCandidate(String name, String id, int yearOfBirth, int daysOfSickness, boolean isInQuarantine,
			boolean protectiveSuit, String party) throws RegisteredCitizenException, AgeException, IdException, Exception;
	Vector <String> viewPartyNames();
	String printCardBoxes();
	String printCitizen();
	String printParty();
	String printResults();
	int viewAmountOfCitizens();
	String viewCitizenName(int i);
	boolean returnChoice(int j, String choice);
	boolean randElections();
	
	

}
