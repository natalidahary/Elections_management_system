//SHALEV SHARABI 313287823
//NATALI DAHARI  205871049

package elections.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import elections.model.CardBox.eCardBoxType;
import elections.model.Party.PelegType;

public class Program implements ElectionsView {
	int lastMonth=0, currentMonth;
	private Elections e1;

	public Program() {
		try {
			Set<Citizen> ci = new Set<Citizen>();
			Citizen c0=new Citizen ("Shalev", "313287823", 1995,0,false,false);
			Citizen c1=new Citizen ("Natali", "205871049", 1995,0,false,false);
			Soldier c2=new Soldier("Daniel", "311111123", 2001,0,false,false);
			Citizen c3=new Citizen ("Melani", "122468321", 1984,2,true,false);
			InfectedWithCorona c4=new InfectedWithCorona ("Yarden", "324677892", 1998,5,true,true);
			Soldier c5=new Soldier ("Shai"  , "345342215", 2003,7,true,true);

			ci.addCitizen(c0);
			ci.addCitizen(c1);
			ci.addCitizen(c2);
			ci.addCitizen(c3);
			ci.addCitizen(c4);
			ci.addCitizen(c5);

			Vector <Candidate> d0 = new  Vector<Candidate>();
			Candidate d00= new Candidate("Yael", "313256823", 1994,0, false, false,"Likud" );
			Candidate d01= new Candidate("Malka","205871049", 1996,12, true, true, "Likud" );

			d0.add(d00);
			d0.add(d01);

			Vector <Candidate> d1 = new  Vector<Candidate>();
			Candidate d10= new Candidate("Refahel", "311111123", 2002,0, false, false,"Yesh Atid" );
			Candidate d11= new Candidate("Dana",    "122468321", 1985,9, true, false,"Yesh Atid" );

			d1.add(d10);
			d1.add(d11);

			Vector <Candidate> d2 = new  Vector<Candidate>();
			Candidate d20= new Candidate("Vladi",  "321178923", 2008,0, false, false,"Hareshima Hameshutefet" );
			Candidate d21= new Candidate("Simona", "233468378", 1965,1, true, false,"Hareshima Hameshutefet" );

			d2.add(d20);
			d2.add(d21);

			Vector <Candidate> d3 = new  Vector<Candidate>();
			Candidate d30= new Candidate("Efrat", "321145632", 2003,0, false, false,"Meretz" );
			Candidate d31= new Candidate("Maxim", "235777988", 1976,5, true, false,"Meretz" );

			d3.add(d30);
			d3.add(d31);

			Vector <Party> pa= new Vector <Party>();

			Party p10=new Party ("Likud", 1973, PelegType.RIGHTHAND,d0,2);
			Party p11=new Party ("Yesh Atid", 2012, PelegType.CENTER,d1,2);
			Party p12=new Party ("Hareshima Hameshutefet", 2015, PelegType.LEFTHAND,d2,2);
			Party p13=new Party ("Meretz", 1992, PelegType.LEFTHAND,d3,2);

			pa.add(p10);
			pa.add(p11);
			pa.add(p12);
			pa.add(p13);

			Vector<CardBox<?>> b= new Vector <CardBox<?>>();

			CardBox<Citizen> b1=new CardBox<>("Tel Aviv",0,eCardBoxType.REGULAR);
			CardBox<InfectedWithCorona> b2=new CardBox<>("Rehovot",0,eCardBoxType.CORONA);
			CardBox<Soldier> b3=new CardBox<>("Ashdod",0,eCardBoxType.MILITARY);
			CardBox<Soldier> b4=new CardBox<>("Eilat",0,eCardBoxType.CORONAMILITARY);

			b.add(b1);
			b.add(b2);
			b.add(b3);
			b.add(b4);

			this.e1=new Elections(ci,6,pa,4,b,4);
			e1.initialize();

		}catch(Exception  e) {
			System.out.println(e.getMessage());
		}

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
	public static boolean checkMonthsDifference(int last,int current) {
		if(current>=last+3) {
			return true;
		}
		else if(last>9) {
			if(3-(12-last)<=current) {
				return true;
			}
		}
		return false;	
	}

	public static void main(String[] args) throws Exception {
		Program p1 = new Program();
		Scanner input = new Scanner(System.in);
		p1.read();
		int result=0;

		while (result != 10)  {
			System.out.println("\n"+"please select an option: ");
			System.out.println("1 - Add a card box");
			System.out.println("2 - Add a citizen");
			System.out.println("3 - Add a party");
			System.out.println("4 - Add a candidate");
			System.out.println("5 - View entire card boxes");
			System.out.println("6 - View entire citizens ");
			System.out.println("7 - View entire parties ");
			System.out.println("8 – Elections");
			System.out.println("9 – View elections results");
			System.out.println("10 - Exit menu"+"\n");

			result = input.nextInt();	

			switch (result) {

			case 1: { 
				p1.addCardBox();
				break;
			}

			case 2: { 
				p1.addCitizen();
				break;
			}

			case 3: { 
				p1.addParty();
				break;
			}

			case 4:	{ 
				p1.addCandidate();
				break;
			}

			case 5: { 
				p1.getAllCardBoxes();
				break;
			}

			case 6: { 
				p1.getRegisteredCitizens();
				break;
			}

			case 7: { 
				p1.getPartyList();
				break;
			}

			case 8: {
				p1.electionsVoting();
				break;
			}	
			case 9:{
				p1.electionsResults();
				break;
			}
			case 10:{
				p1.save();
				break;
			}
			default:
				System.out.println("error - must be between 1 to 10");
			}
		}
		input.close();
	}

	@Override
	public void read() {
		Scanner input =new Scanner(System.in) ;
		boolean temp1=false;
		while(temp1==false) {
			System.out.println("Woulds you like to uplaod election information from a file? --> YES- press 1, NO- press 2");
			int result= input.nextInt();
			System.out.println("");
			switch(result){
			case 1:
				try {
					e1.read();
				}
				catch(ClassNotFoundException | IOException e) {
					System.out.println("No data was found");
					System.out.println("");
				}
				temp1= true;
				break;
			case 2:
				temp1= true;
				break;

			default:
				System.out.println("Please choose YES- press 1, NO- press 2");
				result= input.nextInt();
				break;
			}
		}
		temp1= false;
	}

	@Override
	public void addCardBox() {
		Scanner input =new Scanner(System.in) ;
		System.out.println("Enter type of card box:");
		System.out.println("Regular-------- press 1");
		System.out.println("Corona--------- press 2");
		System.out.println("Military------- press 3");
		System.out.println("MilitaryCorona- press 4");
		int select=input.nextInt();
		if(select==1) {
			CardBox<Citizen> box;
			System.out.println("Enter card box address:");
			input.nextLine();
			String address=input.nextLine();
			box=new CardBox<>(address,0,eCardBoxType.REGULAR);

			e1.addCardBox(box);
		}
		else if(select==2) {
			CardBox<InfectedWithCorona> box;
			System.out.println("Enter card box address:");
			input.nextLine();
			String address=input.nextLine();
			box=new CardBox<>(address,0,eCardBoxType.CORONA);

			e1.addCardBox(box);
		}
		else if(select==3) {
			CardBox<Soldier> box;
			System.out.println("Enter card box address:");
			input.nextLine();
			String address=input.nextLine();
			box=new CardBox<>(address,0,eCardBoxType.MILITARY);

			e1.addCardBox(box);

		}
		else if(select==4) {
			CardBox<Soldier> box;
			System.out.println("Enter card box address:");
			input.nextLine();
			String address=input.nextLine();
			box=new CardBox<Soldier>(address,0,eCardBoxType.CORONAMILITARY);

			e1.addCardBox(box);
		}
		else
			System.out.println("Type: " + select + " ,not found");
	}

	@Override
	public void addCitizen() throws Exception {
		Scanner input =new Scanner(System.in) ;
		Citizen c= null;
		Soldier s= null;
		InfectedWithCorona i = null;
		System.out.println("Enter citizen's name:");

		String name=input.nextLine();

		int yearOfBirth;
		String id;
		boolean flag=false;
		do{
			System.out.println("Enter citizen's ID:");
			id=input.nextLine();
			try {
				Citizen.setId(id);
				flag=true;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

		}while(flag==false);

		if(e1.checkIfIdEx(id)) {
			System.out.println("Id is already registered in system");
			return;
		}
		System.out.println("Enter citizen's year of birth:");
		yearOfBirth=input.nextInt();
		try {
			Citizen.checkAge(yearOfBirth);
			flag=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
			return;
		}

		//check if citizen is a soldier
		if(checkIfSoldier(yearOfBirth)==true) {
			System.out.println("If in quarantine press 'Y', else press any other key");
			input.nextLine();
			char quarantine=input.next().charAt(0);


			if(quarantine=='Y' || quarantine=='y') {

				System.out.println("Enter days of sickness");
				int days=input.nextInt();

				System.out.println("If has protective suit press 'Y', else press any other key");
				input.nextLine();
				char protectiveSuit=input.next().charAt(0);

				if(protectiveSuit=='Y' || protectiveSuit=='y') {

					s=new Soldier(name, id, yearOfBirth,days, true, true);

				}
				else 
					s=new Soldier(name, id, yearOfBirth,days, true, false);


			}
			else 
				s=new Soldier(name, id, yearOfBirth,0, false, false);


			e1.addCitizen(s); 
		}
		else {
			System.out.println("If in quarantine press 'Y', else press any other key");
			input.nextLine();
			char quarantine=input.next().charAt(0);


			if(quarantine=='Y' || quarantine=='y') {

				System.out.println("Enter days of sickness");
				int days=input.nextInt();

				System.out.println("If has protective suit press 'Y', else press any other key");
				input.nextLine();
				char protectiveSuit=input.next().charAt(0);

				if(protectiveSuit=='Y' || protectiveSuit=='y') {
					i=new InfectedWithCorona(name, id, yearOfBirth,days, true, true);

				}
				else 
					i=new InfectedWithCorona(name, id, yearOfBirth,days, true, false);

				e1.addCitizen(i); 

			}
			else {
				c=new Citizen(name, id, yearOfBirth,0, false, false);
				e1.addCitizen(c); 
			}		
		}
	}

	@Override
	public void addParty() {
		Scanner input =new Scanner(System.in) ;
		Party p;
		System.out.println("Enter party's name:");
		String name=input.nextLine();
		if(e1.checkParty(name)) {
			System.out.println("Party is already exists");
			return;
		}

		System.out.println("Enter party's established year:");
		int establishedYear=input.nextInt();
		System.out.println("Press the right number according to the political state:"
				+"\n"+ "Right winged press  1"
				+"\n"+ "Left winged press   2"
				+"\n"+ "Centered press      3");
		int temp=input.nextInt();

		Vector <Candidate> cnd=new Vector <Candidate>();
		if(temp==PelegType.RIGHTHAND.ordinal() || temp==PelegType.LEFTHAND.ordinal() || temp==PelegType.CENTER.ordinal()) {
			p=new Party(name,establishedYear, PelegType.RIGHTHAND, cnd, 0);
			e1.addParty(p);
		}
		else if(temp==2) {
			p=new Party(name,establishedYear, PelegType.LEFTHAND,cnd, 0);
			e1.addParty(p);
		}

		else if(temp==3) {
			p=new Party(name,establishedYear, PelegType.CENTER, cnd, 0);
			e1.addParty(p);
		}
		else
			System.out.println("Type: " + temp + " ,not found");

	}

	@Override
	public void addCandidate() throws Exception {
		Scanner input =new Scanner(System.in) ;
		Candidate c;
		System.out.println("Choose the party");
		System.out.println("Press a number according to the list:");
		if(e1.getPartyList()!=null) {
			for(int i=0; i<e1.getPartyList().size() ;i++) {
				if(e1.getPartyList().elementAt(i)!=null) {
					System.out.print(i+1 +  "- " + e1.getPartyList().elementAt(i).getPartyName());
					System.out.println();
				}
			}
		}
		else{
			System.out.println("No party was found");
			return;
		}
		int temp=input.nextInt();
		if(temp<1 || temp >e1.getNumOfPartyList()+1) {
			System.out.println("Type: " + temp + " ,not found");
			return;

		}

		System.out.println("Enter candidate's name:");
		input.nextLine();
		String name=input.nextLine();
		String id;

		boolean flag=false;
		do{
			System.out.println("Enter candidate's ID:");
			id=input.nextLine();
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
			return;
		}

		System.out.println("Enter candidate's year of birth:");
		int yearOfBirth=input.nextInt();
		try {
			Candidate.checkAge(yearOfBirth);
			flag=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("Enter amount of votes recieved last primaries");
		int primariesVotes=input.nextInt();

		System.out.println("If in quarantine press 'Y', else press any other key");
		char quarantine=input.next().charAt(0);

		if(quarantine=='Y' || quarantine=='y') {
			System.out.println("If has protective suit press 'Y', else press any other key");
			input.nextLine();
			char protectiveSuit=input.next().charAt(0);

			System.out.println("Enter days of sickness");
			int days=input.nextInt();

			if(protectiveSuit=='Y' || protectiveSuit=='y') {
				c=new Candidate(name, id, yearOfBirth,days, true, true,e1.getPartyList().elementAt(temp-1).getPartyName());

			}
			else
				c=new Candidate(name, id, yearOfBirth,days, true, false,e1.getPartyList().elementAt(temp-1).getPartyName());
		}
		else
			c=new Candidate(name, id, yearOfBirth,0, false, false,e1.getPartyList().elementAt(temp-1).getPartyName());

		c.setVoteRate(primariesVotes);
		e1.addCitizen(c);
		e1.getPartyList().elementAt(temp-1).addCandidate(c, e1);
		e1.getPartyList().elementAt(temp-1).setPrimaries();
	}

	@Override
	public void getAllCardBoxes() {
		if(e1.getAllCardBoxes()!=null) { 
			for(int i=0; i<e1.getAllCardBoxes().size();i++) {
				if(e1.getAllCardBoxes().elementAt(i)!=null)
					System.out.println(e1.getAllCardBoxes().elementAt(i) + "\n");
			}
		}
	}

	@Override
	public void getRegisteredCitizens() {
		if(e1.getRegisteredCitizens()!=null) {
			for(int i=0; i<e1.getRegisteredCitizens().size();i++) {
				if(e1.getRegisteredCitizens().get(i)!=null)
					System.out.println(e1.getRegisteredCitizens().get(i) + "\n");
			}
		}
	}

	@Override
	public void getPartyList() {
		if(e1.getPartyList()!=null) {
			for(int i=0; i<e1.getPartyList().size(); i++) {
				if(e1.getPartyList().elementAt(i)!=null)
					System.out.println(e1.getPartyList().elementAt(i) + "\n");
			}
		}
	}

	@Override
	public void electionsVoting() {
		Scanner input =new Scanner(System.in) ;
		currentMonth=LocalDate.now().getMonthValue();
		if(checkMonthsDifference(lastMonth,currentMonth)) {
			lastMonth=currentMonth;
			for(int z=0;z<e1.getPartyList().size();z++) {
				e1.getPartyList().elementAt(z).resetVotes();;
			}
			e1.updateVotesArray();

			///Reset last elections votes
			System.out.println("WELCOME TO ELECTIONS " + LocalDate.now().getMonth()+" "+(LocalDate.now().getYear()) +"!" + "\n" + "---------------------------------");
			for(int i=0; i<e1.numOfCardBoxes; i++) {
				for(int m=0;m<e1.getAllCardBoxes().elementAt(i).getLocalCitizens().size();m++) {
					System.out.println("Hello " + ((Citizen) e1.getAllCardBoxes().elementAt(i).getLocalCitizens().elementAt(m)).getName() + " would you like to vote?");
					System.out.println("If so, press 'Y', else press any other key");
					char ans=input.next().charAt(0);

					if(ans=='Y' || ans=='y') {
						System.out.println("Press a number acourding to the list:");
						for(int j=0; j<e1.getPartyList().size(); j++) {
							System.out.println(j+1 +  "- " + e1.getPartyList().elementAt(j).getPartyName());
						}

						int vote=input.nextInt();

						while(e1.checkIfNoCandidates(vote)) {
							System.out.println("Cannot vote to a none candidates party, choose different party:");
							for(int j=0; j<e1.numOfPartyList; j++) {
								System.out.println(j+1 +  "- " + e1.getPartyList().elementAt(j).getPartyName());
							}
							vote=input.nextInt();

						}

						if(vote<1 || vote >e1.getPartyList().size()+1) {
							System.out.println("Type: " + vote + "selection ,not found");
							i=1;
						}
						else {
							e1.voteKeeper(vote, i);
						}

					}
					else
						System.out.println("Oh, Your vote could have make a different.. you better come next time"  + "\n");
				}
				e1.getAllCardBoxes().elementAt(i).SetPercentage();
			}
		}
		else System.out.println("Elections can occur at list every 3 months, last elections took place in " + lastMonth + " month");
	}

	@Override
	public void electionsResults() {
		System.out.println("FINAL RESULTS ELECTIONS MONTH " + lastMonth+" "+(LocalDate.now().getYear()) +"! :" +"\n"+ "-------------------------------------");
		int winner=e1.checkWinner(e1.countVotes()); //recieve the one with highest amount of votes
		//check if there is a tie
		e1.WinnerAnounce(e1.countVotes(), winner);
		System.out.println(e1);
	}

	@Override
	public void save() {
		System.out.println("good bye:-)");
		try {
			e1.save();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}


