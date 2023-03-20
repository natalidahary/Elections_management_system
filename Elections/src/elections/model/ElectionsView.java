package elections.model;

public interface ElectionsView {
	void read();
	void addCardBox();
	void addCitizen() throws Exception;
	void addParty();
	void addCandidate() throws Exception;
	void getAllCardBoxes();
	void getRegisteredCitizens();
	void getPartyList();
	void electionsVoting();
	void electionsResults();
	void save();

	

}
