package mvc;

import java.util.Vector;

import elections.model.AgeException;
import elections.model.Candidate;
import elections.model.CardBox;
import elections.model.CardBox.eCardBoxType;
import elections.model.Citizen;
import elections.model.ElectionsView;
import elections.model.IdException;
import elections.model.Party;
import elections.model.Party.PelegType;
import elections.model.RegisteredCitizenException;
import mvc.elections.listeners.ElectionsEventsListenable;
import mvc.elections.listeners.ElectionsViewListenable;


public class Controller implements ElectionsEventsListenable, ElectionsViewListenable {
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model=model;
		this.view=view;

		//	model.setListener(this);
		view.setListener(this);

	}

	@Override
	public boolean viewAddCardBox(String adress, eCardBoxType type) {
		return model.addCardBox(adress, type);

	}

	@Override
	public boolean viewAddCitizens(String name, String id, int yearOfBirth, int daysOfSickness, boolean isInQuarantine,
			boolean protectiveSuit) throws RegisteredCitizenException, AgeException, IdException{
		return model.addCitizen(name,id, yearOfBirth,daysOfSickness, isInQuarantine, protectiveSuit);
	}


	@Override
	public boolean viewAddParty(String partyName, int establishedYear, PelegType pelType, Vector<Candidate> candidates,
			int numOfCandidate) {
		Party p=new Party(partyName,establishedYear, pelType,candidates,numOfCandidate);
		return model.addParty(p);
	}

	@Override
	public boolean viewAddCandidate(String name, String id, int yearOfBirth, int daysOfSickness, boolean isInQuarantine,
			boolean protectiveSuit, String party)throws Exception {
		return model.addCandidate(name,id, yearOfBirth,daysOfSickness, isInQuarantine, protectiveSuit, party);

	}

	@Override
	public Vector<String> viewPartyNames() {
		return model.getPartyName();
	}
	
	@Override
	public String printCardBoxes() {
		return model.printCardBoxes();
	}
	@Override
	public String printCitizen() {
		return model.printCitizens();
	}

	@Override
	public String printParty() {
		return model.printParty();
	}

	@Override
	public String printResults() {
		return model.printResults();
	}

	@Override
	public int viewAmountOfCitizens() {
		return model.amountChoicers();
	}

	@Override
	public String viewCitizenName(int i) {
		return model.citizenName(i);
	}

	@Override
	public boolean returnChoice(int j, String choice) {
		return model.addChoice(j, choice);
	}

	@Override
	public boolean randElections() {
		return model.elections();
	}


}
