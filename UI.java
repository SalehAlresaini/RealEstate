
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class UI extends Application {
	private int uID;
	List<File> picList;
	GridPane picGrid;
	Button nivigationPar[];
	GridPane grid;
	GridPane aboutPane;
	GridPane homePane;
	GridPane loginPane;
	GridPane registerPan;
	GridPane addListPane;
	GridPane servicesPane;
	GridPane browsPane;
	GridPane profilePane;
	
	@Override
	public void start(final Stage primaryStage) {//throws Exception {
		
		// Header Panel
		GridPane header = new GridPane();
		header.setAlignment(Pos.TOP_CENTER);
		header.setHgap(10);
		header.setVgap(10);
		header.setPadding(new Insets(25,25,25,25));
		
		
		nivigationPar = new Button[8];
		nivigationPar[0] =new Button("Group FOUR\nREAL ESTATE");
		nivigationPar[1] =new Button("Home");
		nivigationPar[2] =new Button("Login");
		nivigationPar[3] =new Button("Add Listing");
		nivigationPar[4] =new Button("Services");
		nivigationPar[5] =new Button("Browse");
		nivigationPar[6] =new Button("Profile");
		nivigationPar[7] =new Button("Logout");		
		
		for(int i=0; i< nivigationPar.length -1; i++){
			header.add(nivigationPar[i], i, 0);
			nivigationPar[i].setOnAction(new ParActionListener(nivigationPar[i].getText()));
		}
		
		//about & contact
		aboutPane = new GridPane();
		aboutPane.setAlignment(Pos.CENTER);
		aboutPane.setHgap(10);
		aboutPane.setVgap(10);
		aboutPane.setPadding(new Insets(25,25,25,25));
		
		Text aboutTitle = new Text("sorry page not found");
		aboutPane.add(aboutTitle, 0, 0);
		
		//home Page
		homePane = new GridPane();
		homePane.setAlignment(Pos.CENTER);
		homePane.setHgap(10);
		homePane.setVgap(10);
		homePane.setPadding(new Insets(25,25,25,25));
		
		Text homeTitle = new Text("sorry page not found");
		homePane.add(homeTitle, 0, 0);
		
		//Login Page
		loginPane = new GridPane();
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setHgap(10);
		loginPane.setVgap(10);
		loginPane.setPadding(new Insets(25,25,25,25));
		
		Text loginTitle = new Text("Login ");
		loginTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Label userName = new Label("User Name:");
		final TextField userField = new TextField();
		Label password = new Label("Password: ");
		final PasswordField passField = new PasswordField();
		
		final Text loginStatus =new Text();
		
		loginPane.add(loginTitle, 0, 0);
		loginPane.add(loginStatus, 0, 1);
		loginPane.add(userName, 0, 2);
		loginPane.add(userField, 0, 3);
		loginPane.add(password, 0, 4);
		loginPane.add(passField, 0, 5);
		
		Button restPassBtn = new Button("Forgot your password?");
		Button newAccountBtn = new Button("Don't have an account? register here");
		Button loginBtn = new Button("Login");
		HBox loginBox = new HBox(10);
		loginBox.setAlignment(Pos.CENTER);
		loginBox.getChildren().add(loginBtn);
		loginPane.add(restPassBtn, 0, 6);
		loginPane.add(newAccountBtn, 0, 7);
		loginPane.add(loginBox, 0, 8);
		
		
		loginBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event){
				
				//uID = Controller.getInstance().login(userField.getText(), passField.getText())
				if( uID == 0){
					loginStatus.setFill(Color.DARKBLUE);
					loginStatus.setText("user name/ password compination is not correct!");
				}
				else{
					loginStatus.setFill(Color.DARKBLUE);
					loginStatus.setText("you have loged in!\n you can brows from the nivigation bar");
				}
			}
			
		});
		newAccountBtn.setOnAction(new ParActionListener("Register"));
		
		
		//register
		registerPan = new GridPane();
		registerPan.setAlignment(Pos.CENTER);
		registerPan.setHgap(10);
		registerPan.setVgap(10);
		registerPan.setPadding(new Insets(25,25,25,25));
		
				
		Text registerTitle = new Text("Register ");
		registerTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Label userNameR = new Label("User Name:");
		final TextField userFieldR = new TextField();
		Label passwordR = new Label("Password: ");
		final PasswordField passFieldR = new PasswordField();
		Label passConf = new Label("Password conformation: ");
		final PasswordField passConfField = new PasswordField();
		Label email = new Label("Email:");
		final TextField emailField = new TextField();
		
		final Text registrationStatus =new Text();
		
		registerPan.add(registerTitle, 0, 0);
		registerPan.add(registrationStatus, 0, 1);
		registerPan.add(userNameR, 0, 2);
		registerPan.add(userFieldR, 0, 3);
		registerPan.add(passwordR, 0, 4);
		registerPan.add(passFieldR, 0, 5);
		registerPan.add(passConf, 0, 6);
		registerPan.add(passConfField, 0, 7);
		registerPan.add(email, 0, 8);
		registerPan.add(emailField, 0, 9);
		
		
		Button registerBtn = new Button("Register");
		HBox registerBox = new HBox(10);
		registerBox.setAlignment(Pos.CENTER);
		registerBox.getChildren().add(registerBtn);
		registerPan.add(registerBox, 0, 10);
		
		registerBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event){
				if(		userFieldR.getText().trim().isEmpty() ||
						passFieldR.getText().trim().isEmpty() ||
						emailField.getText().trim().isEmpty()){
					registrationStatus.setFill(Color.DARKBLUE);
					registrationStatus.setText("complete the empty fields");
				}
				else if(passFieldR.getText().equals(passConfField.getText())){
					String registerOutput = "Nothing yet";
						//Controller.getInstance().register(userFieldR.getText(), passFieldR.getText(), emailField.getText());
					if(registerOutput.equals("done")){
						loginStatus.setFill(Color.DARKBLUE);
						loginStatus.setText("Registration complete, Now login");
						nivigationPar[2].fire();
					}
					else{
						registrationStatus.setFill(Color.DARKBLUE);
						registrationStatus.setText(registerOutput);
					}
				}
				else{
					registrationStatus.setFill(Color.DARKBLUE);
					registrationStatus.setText("The two passwords does not match!!");
				}
				
			}
			
		});
		
		//add List
		addListPane = new GridPane();
		addListPane.setAlignment(Pos.CENTER);
		addListPane.setHgap(10);
		addListPane.setVgap(10);
		addListPane.setPadding(new Insets(25,25,25,25));
		
		Text addListTitle = new Text("Add List ");
		addListTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Label propName = new Label("Name:");
		final TextField propField = new TextField();
		Label propDisc = new Label("Discription: ");
		final TextField propDiscField = new TextField();
		Label propLoc = new Label("Location: ");
		final TextField propLocField = new TextField();
		Label propType = new Label("Type:");
		Label propNRoom = new Label("Number of Rooms:");
		final TextField propNRoomsField = new TextField();
		Label propNBaths = new Label("Number of Bathrooms:");
		final TextField propNBathsField = new TextField();
		Label propArea = new Label("Area:");
		final TextField propAreaField = new TextField();
		Label propOfferType = new Label("Offer Type:");
		Label propOfferPrice = new Label("Offer Price:");
		final TextField propOfferPriceField = new TextField();
		
		final ToggleGroup propTypeField = new ToggleGroup();
		RadioButton houseRadio = new RadioButton("House");
		houseRadio.setToggleGroup(propTypeField);
		houseRadio.setSelected(true);
		houseRadio.setUserData("House");
		RadioButton ApartmentRadio = new RadioButton("Apartment");
		ApartmentRadio.setToggleGroup(propTypeField);
		ApartmentRadio.setUserData("Apartment");
		
		final ToggleGroup propOfferTypeField = new ToggleGroup();
		RadioButton rentRadio = new RadioButton("Rent");
		rentRadio.setToggleGroup(propOfferTypeField);
		rentRadio.setSelected(true);
		rentRadio.setUserData("Rent");
		RadioButton sellRadio = new RadioButton("Sell");
		sellRadio.setToggleGroup(propOfferTypeField);
		sellRadio.setUserData("Sell");
		
		final Text addStatus =new Text();
		
		addListPane.add(addListTitle, 0, 0, 2, 1);
		addListPane.add(addStatus, 0, 1, 2, 1);
		addListPane.add(propName, 0, 2, 2, 1);
		addListPane.add(propField, 0, 3, 2, 1);
		addListPane.add(propDisc, 0, 4, 2, 1);
		addListPane.add(propDiscField, 0, 5, 2, 1);
		addListPane.add(propLoc, 0, 6, 2, 1);
		addListPane.add(propLocField, 0, 7, 2, 1);
		addListPane.add(propType, 0, 8, 2, 1);
		addListPane.add(houseRadio, 0, 9, 1, 1);
		addListPane.add(ApartmentRadio, 1, 9, 1, 1);
		addListPane.add(propNRoom, 0, 10, 2, 1);
		addListPane.add(propNRoomsField, 0, 11, 2, 1);
		addListPane.add(propNBaths, 0, 12, 2, 1);
		addListPane.add(propNBathsField, 0, 13, 2, 1);
		addListPane.add(propArea, 0, 14, 2, 1);
		addListPane.add(propAreaField, 0, 15, 2, 1);
		addListPane.add(propOfferType, 0, 16, 2, 1);
		addListPane.add(rentRadio, 0, 17, 1, 1);
		addListPane.add(sellRadio, 1, 17, 1, 1);
		addListPane.add(propOfferPrice, 0, 18, 2, 1);
		addListPane.add(propOfferPriceField, 0, 19, 2, 1);
		
		picGrid = new GridPane();
		picGrid.setAlignment(Pos.CENTER);
		picGrid.setHgap(10);
		picGrid.setVgap(10);
		picGrid.setPadding(new Insets(10,10,10,10));
		picGrid.setMaxWidth(200);
		
		final Button openPicsButton = new Button("Select Pictures...");
		HBox openPicsBox = new HBox(10);
		openPicsBox.setAlignment(Pos.CENTER);
		openPicsBox.getChildren().add(openPicsButton);
		Button addListBtn = new Button("Add List");
		HBox addListBox = new HBox(10);
		addListBox.setAlignment(Pos.CENTER);
		addListBox.getChildren().add(addListBtn);
		addListPane.add(picGrid, 0, 20);
		addListPane.add(openPicsBox, 1, 20);
		addListPane.add(addListBox, 0, 21, 2, 1);
		
		final FileChooser fileChooser = new FileChooser();
		final List<Button> uploadedPics = new ArrayList<Button>();
		openPicsButton.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                	configureFileChooser(fileChooser);
	                    picList = new ArrayList<File>(
	                        fileChooser.showOpenMultipleDialog(primaryStage));
	                    int i=0;
	                    uploadedPics.clear();
	                    picGrid.getChildren().clear();
	                    if (picList != null) {
	                        for (File file : picList) {
	                        	uploadedPics.add(new Button(file.toString()));
	                        	picGrid.add(uploadedPics.get(i), 0, i);
	                        	uploadedPics.get(i).setOnAction(new PictureRemoval(uploadedPics.get(i)));
	                        	i++;
	                        }
	                    }
	                }
	            });
		addListBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event){
				if(		propField.getText().trim().isEmpty() ||
						propDiscField.getText().trim().isEmpty() ||
						propLocField.getText().trim().isEmpty() ||
						propNRoomsField.getText().trim().isEmpty() ||
						propNBathsField.getText().trim().isEmpty() ||
						propAreaField.getText().trim().isEmpty() ||
						propOfferPriceField.getText().trim().isEmpty()){
					addStatus.setFill(Color.DARKBLUE);
					addStatus.setText("complete the empty fields");
				}
				else{
					addStatus.setFill(Color.DARKBLUE);
					if(/*Controler.addList(propField.getText(), propDiscField.getText(), propLocField.getText(),
							propOfferTypeField.getSelectedToggle().getUserData().toString(), propNRoomsField.getText(),
							propNBathsField.getText(), propAreaField.getText(),
							propOfferTypeField.getSelectedToggle().getUserData().toString(), propOfferPriceField.getText(), list)*/true)
						addStatus.setText("Your Property has been added succesfully");
					else
						addStatus.setText("Error:your property couldn't be added");
				}
				
			}
			
		});
		
		
		//services
		servicesPane = new GridPane();
		servicesPane.setAlignment(Pos.CENTER);
		servicesPane.setHgap(10);
		servicesPane.setVgap(10);
		servicesPane.setPadding(new Insets(25,25,25,25));
		
		Text servicesTitle = new Text("sorry page not found");
		servicesPane.add(servicesTitle, 0, 0);
		
		//Brows
		browsPane = new GridPane();
		browsPane.setAlignment(Pos.CENTER);
		browsPane.setHgap(10);
		browsPane.setVgap(10);
		browsPane.setPadding(new Insets(25,25,25,25));
		
		Text browsTitle = new Text("sorry page not found");
		browsPane.add(browsTitle, 0, 0);
		
		//profile (requests)
		profilePane = new GridPane();
		profilePane.setAlignment(Pos.CENTER);
		profilePane.setHgap(10);
		profilePane.setVgap(10);
		profilePane.setPadding(new Insets(25,25,25,25));
		
		Text profileTitle = new Text("sorry page not found");
		profilePane.add(profileTitle, 0, 0);
		
		//Combine
		grid = new GridPane();
		//grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		
		grid.add(header, 0, 0);
		grid.add(homePane, 0, 1);
		Scene scene =new Scene(grid, 700, 900);
		
		
		scene.getStylesheets().add("UI.css");
		primaryStage.setTitle("Real Estate");
		primaryStage.setScene(scene);
		primaryStage.show();
		nivigationPar[1].fire();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	public class ParActionListener implements EventHandler <ActionEvent>{
		String state;
		public ParActionListener(String txt){
			state =txt;
		}
		@Override
		public void handle(ActionEvent arg0) {
			discolorAll();
			switch(state){
				case("Home"):
					nivigationPar[1].setTextFill(Color.rgb(225, 210, 189, 1));
					grid.getChildren().remove(1);
					grid.add(homePane, 0, 1);
					break;
				case("Login"):
					nivigationPar[2].setTextFill(Color.rgb(225, 210, 189, 1));
					grid.getChildren().remove(1);
					grid.add(loginPane, 0, 1);
					break;
				case("Add Listing"):
					nivigationPar[3].setTextFill(Color.rgb(225, 210, 189, 1));
					grid.getChildren().remove(1);
					grid.add(addListPane, 0, 1);
					break;
				case("Services"):
					nivigationPar[4].setTextFill(Color.rgb(225, 210, 189, 1));
					grid.getChildren().remove(1);
					grid.add(servicesPane, 0, 1);
					break;
				case("Browse"):
					nivigationPar[5].setTextFill(Color.rgb(225, 210, 189, 1));
					grid.getChildren().remove(1);
					grid.add(browsPane, 0, 1);
					break;
				case("Profile"):
					nivigationPar[6].setTextFill(Color.rgb(225, 210, 189, 1));
					grid.getChildren().remove(1);
					grid.add(profilePane, 0, 1);
					break;
				case("Register"):
					nivigationPar[1].setTextFill(Color.rgb(225, 210, 189, 1));
					grid.getChildren().remove(1);
					grid.add(registerPan, 0, 1);
					break;
				case("Group FOUR\nREAL ESTATE"):
					nivigationPar[2].setTextFill(Color.rgb(225, 210, 189, 1));
					grid.getChildren().remove(1);
					grid.add(aboutPane, 0, 1);
					break;
				
			}
			
		}
		
		
		private void discolorAll() {
			for(int i=0; i<nivigationPar.length; i++){
				nivigationPar[i].setTextFill(Color.rgb(45, 130, 208, 1));
			}
			
		}
	}
	public class PictureRemoval implements EventHandler <ActionEvent>{

		private Button picBtn;
		public PictureRemoval( Button path){
			picBtn = path;
		}
		@Override
		public void handle(ActionEvent arg0) {
			picGrid.getChildren().remove(picBtn);
			picList.remove(picBtn);
		}
	}
	private static void configureFileChooser(final FileChooser fileChooser) {      
        fileChooser.setTitle("Import Pictures");
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        );                 
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png"),
            new FileChooser.ExtensionFilter("GIF", "*.gif")
        );
	}
}
