

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class UI extends Application {
	private int uID;
	@Override
	public void start(Stage primaryStage) {//throws Exception {
		
		// Header Panel
		GridPane header = new GridPane();
		header.setAlignment(Pos.TOP_CENTER);
		header.setHgap(10);
		header.setVgap(10);
		header.setPadding(new Insets(25,25,25,25));
		
		Text team =new Text("Group FOUR");
		Text project =new Text("REAL ESTATE");
		final Button nivigationPar[] = new Button[5];
		nivigationPar[0] =new Button("Home");
		nivigationPar[1] =new Button("Login");
		nivigationPar[2] =new Button("Add Listing");
		nivigationPar[3] =new Button("Services");
		nivigationPar[4] =new Button("Browse");
		
		header.add(team, 0, 0, 5, 1);
		header.add(project, 0, 1, 5, 1);
		for(int i=0; i< nivigationPar.length; i++){
			header.add(nivigationPar[i], i+5, 0, 1, 2);
			nivigationPar[i].setOnAction(new ParActionListener(nivigationPar[i].getText()));
		}
		nivigationPar[0].setTextFill(Color.rgb(225, 210, 189, 1));
		
		//home Page
		
		
		
		//Login Page
		GridPane loginPane = new GridPane();
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
		
		final Text actionTarget =new Text();
		
		loginPane.add(loginTitle, 0, 0);
		loginPane.add(actionTarget, 0, 1);
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
					actionTarget.setFill(Color.DARKBLUE);
					actionTarget.setText("user name/ password compination is not correct!");
				}
				else{
					actionTarget.setFill(Color.DARKBLUE);
					actionTarget.setText("you have loged in!\n you can brows from the nivigation bar");
				}
			}
			
		});
		
		
		//register
		GridPane registerPan = new GridPane();
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
		registerPan.add(actionTarget, 0, 1);
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
				if(passField.getText().equals(passConfField.getText())){
					String registerOutput = "";
						//Controller.getInstance().register(userFieldR.getText(), passFieldR.getText(), emailField.getText());
					if(registerOutput.equals("done"))
						nivigationPar[1].arm();
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
		//profile (requests)
		
		//Brows
		
		//add List
		
		//Combine
		GridPane grid = new GridPane();
		//grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		
		grid.add(header, 0, 0);
		grid.add(loginPane, 0, 1);
		Scene scene =new Scene(grid);//, 1000, 2300);
		
		primaryStage.setTitle("Real Estate");
		primaryStage.setScene(scene);
		primaryStage.show();
		
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
			// TODO Auto-generated method stub
			
		}
	}
}
