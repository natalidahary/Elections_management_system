package main.GUI;


import java.util.Vector;

import elections.model.Candidate;
import elections.model.CardBox;
import elections.model.CardBox.eCardBoxType;
import elections.model.Citizen;
import elections.model.Elections;
import elections.model.InfectedWithCorona;
import elections.model.Party;
import elections.model.Party.PelegType;
import elections.model.Set;
import elections.model.Soldier;
import javafx.application.Application;
import javafx.stage.Stage;
import mvc.Controller;
import mvc.Model;
import mvc.View;


public class Main extends Application{


	public Elections getSomeInfo(Elections e1) {
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

				e1=new Elections(ci,6,pa,4,b,4);
				e1.initialize();


			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			return e1;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage newStage) throws Exception {
		Elections e1 = null;
		e1=getSomeInfo(e1);
		Model model = new Model();
		model.setElections(e1);
		View view = new View(newStage);
		Controller controller = new Controller(model, view);
	}

}
