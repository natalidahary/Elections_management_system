package mvc;


import java.util.Vector;

import javax.swing.*;

import elections.model.Candidate;
import elections.model.CardBox;
import elections.model.CardBox.eCardBoxType;
import elections.model.Party.PelegType;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.elections.listeners.ElectionsViewListenable;


public class View {
	private Vector <ElectionsViewListenable> listeners;
	private BorderPane br;
	private VBox vb;

	// Texts and TextFields:
	private TextField tf1 = new TextField();
	private TextField tf2 = new TextField();
	private TextField tf3 = new TextField();
	private TextField tf4 = new TextField();
	private TextField tf5 = new TextField();
	private TextArea textArea = new TextArea();
	private Text text1 = new Text();
	private Text text2 = new Text();
	private Text print = new Text();
	private ScrollBar sc = new ScrollBar();

	public View(Stage theStage) {

		theStage.setTitle("Elections");
		this.listeners=new Vector<ElectionsViewListenable>();

		br=new BorderPane();
		Label lblMenu=new Label();
		lblMenu.setText("Election's Menu");
		lblMenu.setFont(new Font("Times",30));

		Button btn1=new Button("Add a card box        ");
		Button btn2=new Button("Add a citizen         ");
		Button btn3=new Button("Add a party           ");
		Button btn4=new Button("Add a candidate       ");
		Button btn5=new Button("View entire card boxes");
		Button btn6=new Button("View entire citizens  ");
		Button btn7=new Button("View entire parties   ");
		Button btn8=new Button("Elections             ");
		Button btn9=new Button("View elections results");
		Button btn0=new Button("Exit menu             ");

		vb=new VBox();
		vb.setAlignment(Pos.TOP_CENTER);
		vb.setSpacing(5);
		vb.setPadding(new Insets(10));
		vb.getChildren().add(lblMenu);
		vb.getChildren().addAll(btn1,
				btn2,
				btn3,
				btn4,
				btn5,
				btn6,
				btn7,
				btn8,
				btn9,
				btn0);

		br.setCenter(vb);

		//cardBox add
		GridPane bp1=new GridPane();
		bp1.setPadding(new Insets(10));
		Label lblEnterAddress = new Label("Enter address");
		TextField tfAddress = new TextField();
		ComboBox<String> cbCardBox = new ComboBox<String>();
		Label lbleCardBox = new Label("Select Type");
		cbCardBox.getItems().addAll("REGULAR", "CORONA", "MILITARY", "CORONAMILITARY");
		Button btnAddCardBox = new Button("Add");
		Button btnCancelCardBox = new Button("Cancel");
		bp1.setPadding(new Insets(10));
		bp1.setHgap(10);
		bp1.setVgap(10);
		bp1.add(lblEnterAddress, 0, 0);
		bp1.add(tfAddress, 1, 0);
		bp1.add(lbleCardBox, 0, 1);
		bp1.add(cbCardBox, 1, 1);
		bp1.add(btnAddCardBox, 0, 4);
		bp1.add(btnCancelCardBox, 1, 4);

		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg) {
				tfAddress.clear();
				cbCardBox.setValue(null);
				br.setCenter(bp1);
			}

		});

		btnAddCardBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg1) {
				if(cbCardBox.getValue()==null || tfAddress.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "One or more of the fields is empty");
				}
				else {
					for (ElectionsViewListenable l : listeners) {
						eCardBoxType eCardBox = eCardBoxType.valueOf(cbCardBox.getValue());
						String address=tfAddress.getText();
						l.viewAddCardBox(address, eCardBox);
						//JOptionPane.showMessageDialog(null, "CardBox was added successfuly");
					}
					br.setCenter(vb);
				}

			}

		});

		btnCancelCardBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				tfAddress.clear();
				cbCardBox.setValue(null);
				//cancel button
				br.setCenter(vb);
			}

		});

		//citizen add 
		GridPane bp2=new GridPane();
		bp2.setPadding(new Insets(10));
		Label lblEnterName = new Label("Enter name");
		TextField tfName = new TextField();
		Label lblID = new Label("ID");
		TextField tfID = new TextField();
		Label lblYear = new Label("Year of birth");
		TextField tfYear = new TextField();
		Label lbleQuarantine = new Label("In quarantine ?");
		ComboBox<String> cbQuarantine = new ComboBox<String>();
		cbQuarantine.getItems().addAll("Yes","No");
		Label lblDaysSickness = new Label("Enter days of sickness");
		TextField tfSickness = new TextField();
		Label lblProtective = new Label("Do you have a protective suit ?");
		ComboBox<String> cbProtective = new ComboBox<String>();
		cbProtective.getItems().addAll("Yes","No");
		Button btnAddCitizen = new Button("Add");
		Button btnCancelCitizen = new Button("Cancel");
		VBox address = new VBox();
		bp2.setPadding(new Insets(10));
		bp2.setHgap(10);
		bp2.setVgap(10);
		bp2.add(lblEnterName, 0, 0);
		bp2.add(tfName, 1, 0);
		bp2.add(lblID, 0, 1);
		bp2.add(tfID, 1, 1);
		bp2.add(lblYear, 0, 2);
		bp2.add(tfYear, 1, 2);
		bp2.add(lbleQuarantine, 0, 3);
		bp2.add(cbQuarantine, 1, 3);
		bp2.add(lblDaysSickness, 0, 4);
		bp2.add(tfSickness, 1, 4);
		bp2.add(lblProtective, 0, 5);
		bp2.add(cbProtective, 1, 5);
		bp2.add(btnAddCitizen, 0, 8);
		bp2.add(btnCancelCitizen, 1, 8);



		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg02) {
				tfName.clear();
				tfID.clear();
				tfYear.clear();
				cbQuarantine.setValue(null);
				tfSickness.clear();
				cbProtective.setValue(null);

				lblDaysSickness.setVisible(false);
				lblProtective.setVisible(false);
				tfSickness.setVisible(false);
				cbProtective.setVisible(false);

				cbQuarantine.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if(cbQuarantine.getValue()==("Yes")) {
							lblDaysSickness.setVisible(true);
							lblProtective.setVisible(true);
							tfSickness.setVisible(true);
							cbProtective.setVisible(true);
							br.setCenter(bp2);
						}
						else {
							lblDaysSickness.setVisible(false);
							lblProtective.setVisible(false);
							tfSickness.setVisible(false);
							cbProtective.setVisible(false);
							br.setCenter(bp2);
						}

					}
				});

				br.setCenter(bp2);
			}

		});

		btnAddCitizen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg2) {
				if(cbQuarantine.getValue()==null || tfName.getText().isEmpty() || tfYear.getText().isEmpty() || tfID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "One or more of the fields is empty");
				}
				else {
					for (ElectionsViewListenable l : listeners) {
						String name=tfName.getText();
						String iD=tfID.getText();
						int year=Integer.parseInt(tfYear.getText());
						String quarantine=cbQuarantine.getValue();
						int sickness=0;
						String protection="";
						if(quarantine=="Yes") {
							if(protection=="Yes") {
								try {
									protection=cbProtective.getValue();
									sickness=Integer.parseInt(tfSickness.getText());
									l.viewAddCitizens(name, iD, year, sickness,true, true);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else
								try {
									protection=cbProtective.getValue();
									sickness=Integer.parseInt(tfSickness.getText());
									l.viewAddCitizens(name, iD, year, sickness,true, false);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						} else
							try {
								l.viewAddCitizens(name, iD, year, sickness,false, false);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
				//JOptionPane.showMessageDialog(null, "Citizen was added successfuly");

				br.setCenter(vb);
			}

		});

		btnCancelCitizen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				tfName.clear();
				tfID.clear();
				tfYear.clear();
				cbQuarantine.setValue(null);
				tfSickness.clear();
				cbProtective.setValue(null);
				//cancel button
				br.setCenter(vb);
			}

		});

		//party add
		GridPane bp3=new GridPane();
		bp3.setPadding(new Insets(10));
		Label lblPartyName = new Label("Party name");
		TextField tfPartyName = new TextField();
		Label lblPartyYear = new Label("Established year");
		TextField tfPartyYear = new TextField();
		ComboBox<String> cbParty = new ComboBox<String>();
		Label lbleParty = new Label("Select Type");
		cbParty.getItems().addAll("RIGHTHAND", "LEFTHAND", "CENTER");
		Button btnAddParty = new Button("Add");
		Button btnCancelParty = new Button("Cancel");
		bp3.setPadding(new Insets(10));
		bp3.setHgap(10);
		bp3.setVgap(10);
		bp3.add(lblPartyName, 0, 0);
		bp3.add(tfPartyName, 1, 0);
		bp3.add(lblPartyYear, 0, 1);
		bp3.add(tfPartyYear, 1, 1);
		bp3.add(lbleParty, 0, 2);
		bp3.add(cbParty, 1, 2);
		bp3.add(btnAddParty, 0, 4);
		bp3.add(btnCancelParty, 1, 4);

		btn3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg) {
				tfPartyName.clear();
				tfPartyYear.clear();
				cbParty.setValue(null);
				br.setCenter(bp3);
			}

		});

		btnAddParty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg1) {
				if(cbParty.getValue()==null || tfPartyName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "One or more of the fields is empty");
				}
				else {
					for (ElectionsViewListenable l : listeners) {
						PelegType eParty = PelegType.valueOf(cbParty.getValue());
						String partyName=tfPartyName.getText();
						int EstYear=Integer.parseInt(tfPartyYear.getText());
						Vector <Candidate> candidates = new Vector <Candidate>();
						l.viewAddParty(partyName,EstYear,eParty,candidates,0);
						//JOptionPane.showMessageDialog(null, "Party was added successfuly");
					}
					br.setCenter(vb);
				}

			}

		});

		btnCancelParty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				tfPartyName.clear();
				tfPartyYear.clear();
				cbParty.setValue(null);
				//cancel button
				br.setCenter(vb);
			}

		});
		//candidate add
		GridPane bp4=new GridPane();
		bp4.setPadding(new Insets(10));
		Label lblCandidateName = new Label("Enter name");
		TextField tfCandidateName = new TextField();
		Label lblCandidateID = new Label("ID");
		TextField tfCandidateID = new TextField();
		Label lblCandidateYear = new Label("Year of birth");
		TextField tfCandidateYear = new TextField();
		Label lblCandidateParty = new Label("Party");
		ComboBox<String> cbCandidateParty = new ComboBox<String>();
		Label lbleCandidateQuarantine = new Label("In quarantine ?");
		ComboBox<String> cbCandidateQuarantine = new ComboBox<String>();
		cbCandidateQuarantine.getItems().addAll("Yes","No");
		Label lblCandidateDaysSickness = new Label("Enter days of sickness");
		TextField tfCandidateSickness = new TextField();
		Label lblCandidateProtective = new Label("Do you have a protective suit ?");
		ComboBox<String> cbCandidateProtective = new ComboBox<String>();
		cbCandidateProtective.getItems().addAll("Yes","No");
		Button btnAddCandidate = new Button("Add");
		Button btnCancelCandidate = new Button("Cancel");
		bp4.setPadding(new Insets(10));
		bp4.setHgap(10);
		bp4.setVgap(10);
		bp4.add(lblCandidateName, 0, 0);
		bp4.add(tfCandidateName, 1, 0);
		bp4.add(lblCandidateID, 0, 1);
		bp4.add(tfCandidateID, 1, 1);
		bp4.add(lblCandidateYear, 0, 2);
		bp4.add(tfCandidateYear, 1, 2);
		bp4.add(lblCandidateParty, 0, 3);
		bp4.add(cbCandidateParty, 1, 3);
		bp4.add(lbleCandidateQuarantine, 0, 4);
		bp4.add(cbCandidateQuarantine, 1, 4);
		bp4.add(lblCandidateDaysSickness, 0, 5);
		bp4.add(tfCandidateSickness, 1, 5);
		bp4.add(lblCandidateProtective, 0, 6);
		bp4.add(cbCandidateProtective, 1, 6);
		bp4.add(btnAddCandidate, 0, 8);
		bp4.add(btnCancelCandidate, 1, 8);


		btn4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg02) {
				tfCandidateName.clear();
				tfCandidateID.clear();
				tfCandidateYear.clear();
				cbCandidateQuarantine.setValue(null);
				tfCandidateSickness.clear();
				cbCandidateProtective.setValue(null);
				cbCandidateParty.setValue(null);


				lblCandidateDaysSickness.setVisible(false);
				lblCandidateProtective.setVisible(false);
				tfCandidateSickness.setVisible(false);
				cbCandidateProtective.setVisible(false);

				cbCandidateQuarantine.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if(cbCandidateQuarantine.getValue()==("Yes")) {
							lblCandidateDaysSickness.setVisible(true);
							lblCandidateProtective.setVisible(true);
							tfCandidateSickness.setVisible(true);
							cbCandidateProtective.setVisible(true);
							br.setCenter(bp4);
						}
						else {
							lblCandidateDaysSickness.setVisible(false);
							lblCandidateProtective.setVisible(false);
							tfCandidateSickness.setVisible(false);
							cbCandidateProtective.setVisible(false);
							br.setCenter(bp4);
						}

					}
				});

				for (ElectionsViewListenable l : listeners) {
					for(int i=0; i<l.viewPartyNames().size();i++) {
						cbCandidateParty.getItems().add(l.viewPartyNames().elementAt(i));
					}
				}
				br.setCenter(bp4);
			}

		});

		btnAddCandidate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg2) {
				if(cbCandidateQuarantine.getValue()==null || tfCandidateName.getText().isEmpty() || tfCandidateYear.getText().isEmpty() || tfCandidateID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "One or more of the fields is empty");
				}
				else {
					for (ElectionsViewListenable l : listeners) {
						String name=tfCandidateName.getText();
						String iD=tfCandidateID.getText();
						int year=Integer.parseInt(tfCandidateYear.getText());
						String quarantine=cbCandidateQuarantine.getValue();
						String party=cbCandidateParty.getValue();
						int sickness=0;
						String protection="";
						if(quarantine=="Yes") {
							if(protection=="Yes") {
								try {
									protection=cbProtective.getValue();
									sickness=Integer.parseInt(tfCandidateSickness.getText());
									l.viewAddCandidate(name, iD, year, sickness,true, true, party);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else
								try {
									protection=cbProtective.getValue();
									sickness=Integer.parseInt(tfCandidateSickness.getText());
									l.viewAddCandidate(name, iD, year, sickness,true, false, party);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						} else
							try {
								l.viewAddCandidate(name, iD, year, sickness,false, false, party);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
				//JOptionPane.showMessageDialog(null, "Citizen was added successfuly");

				br.setCenter(vb);
			}

		});

		btnCancelCandidate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				tfCandidateName.clear();
				tfCandidateID.clear();
				tfCandidateYear.clear();
				cbCandidateQuarantine.setValue(null);
				tfCandidateSickness.clear();
				cbCandidateProtective.setValue(null);
				cbCandidateParty.setValue(null);
				//cancel button
				br.setCenter(vb);
			}

		});



		//cardBox view
		ScrollPane sp5 = new ScrollPane();
		sp5.setPrefSize(500,500);
		GridPane bp5=new GridPane();
		Label txtCardBox=new Label();
		Button btnBack = new Button("Back");
		bp5.setPadding(new Insets(10));
		bp5.setHgap(10);
		bp5.setVgap(10);
		bp5.add(btnBack, 0, 1);

		btn5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg5) {
				for (ElectionsViewListenable l : listeners) {
					txtCardBox.setText(l.printCardBoxes());
					txtCardBox.setFont(new Font("Times", 14));
					sp5.setContent(txtCardBox);
					bp5.add(sp5, 0, 0);
				}
				br.setCenter(bp5);
			}

		});

		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg50) {
				//back button
				br.setCenter(vb);
				bp5.getChildren().remove(sp5);
			}

		});

		//citizen view
		ScrollPane sp6 = new ScrollPane();
		sp6.setPrefSize(500,500);
		GridPane bp6=new GridPane();
		Label txtCitizen=new Label();
		Button btnBackCitizen = new Button("Back");
		bp6.setPadding(new Insets(10));
		bp6.setHgap(10);
		bp6.setVgap(10);
		bp6.add(btnBackCitizen, 0, 1);

		btn6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg5) {
				for (ElectionsViewListenable l : listeners) {
					txtCitizen.setText(l.printCitizen());
					txtCitizen.setFont(new Font("Times", 14));
					sp6.setContent(txtCitizen);
					bp6.add(sp6, 0, 0);
				}
				br.setCenter(bp6);
			}

		});

		btnBackCitizen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg50) {
				//back button
				br.setCenter(vb);
				bp6.getChildren().remove(sp6);
			}

		});

		//party view
		ScrollPane sp7 = new ScrollPane();
		sp7.setPrefSize(500,500);
		GridPane bp7=new GridPane();
		Label txtParty=new Label();
		Button btnBackParty = new Button("Back");
		bp7.setPadding(new Insets(10));
		bp7.setHgap(10);
		bp7.setVgap(10);
		bp7.add(btnBackParty, 0, 1);

		btn7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg5) {
				for (ElectionsViewListenable l : listeners) {
					txtParty.setText(l.printParty());
					txtParty.setFont(new Font("Times", 14));
					sp7.setContent(txtParty);
					bp7.add(sp7, 0, 0);
				}
				br.setCenter(bp7);
			}

		});

		btnBackParty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg50) {
				//back button
				br.setCenter(vb);
				bp7.getChildren().remove(sp7);
			}

		});

		//Elections
		GridPane bp8=new GridPane();
		bp8.setPadding(new Insets(10));
		Label lblHello = new Label("Random elections was made successfuly!");
		Button btnFinish = new Button("Finish");
		bp8.setPadding(new Insets(10));
		bp8.setHgap(10);
		bp8.setVgap(10);
		bp8.add(lblHello, 0, 0);
		bp8.add(btnFinish, 0, 1);

		btn8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg) {
				br.setCenter(bp8);
			}

		});

		btnFinish.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg1) {
				
				for (ElectionsViewListenable l : listeners) {
					l.randElections();
				}
				br.setCenter(vb);
			}

		});



		//election results
		ScrollPane sp9 = new ScrollPane();
		sp9.setPrefSize(500,500);
		GridPane bp9=new GridPane();
		Label txtResults=new Label();
		Button btnBackResults = new Button("Back");
		bp9.setPadding(new Insets(10));
		bp9.setHgap(10);
		bp9.setVgap(10);
		bp9.add(btnBackResults, 0, 1);

		btn9.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg5) {
				for (ElectionsViewListenable l : listeners) {
					txtResults.setText(l.printResults());
					txtResults.setFont(new Font("Times", 14));
					sp9.setContent(txtResults);
					bp9.add(sp9, 0, 0);
				}
				br.setCenter(bp9);
			}

		});

		btnBackResults.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg50) {
				//back button
				br.setCenter(vb);
				bp9.getChildren().remove(sp9);
			}

		});


		//exit+file save
		btn0.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				//save to file is needed*********!!!!!!!!!!
				theStage.close();
			}

		});

		Scene scene = new Scene(br, 300, 400);
		theStage.setScene(scene);
		theStage.show();


	}


	public void clearPrints() {
		textArea.clear();
		tf1.clear();
		tf2.clear();
		tf3.clear();
		tf4.clear();
		tf5.clear();

	}
	public void setListener(ElectionsViewListenable l) {
		listeners.add(l);
	}

}
