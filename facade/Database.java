package facade;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;

import javax.xml.bind.DatatypeConverter;




public class Database {
	
	private Connection conn;
    private Statement s;
    private static Database database ;

    private Database(String server, String uName, String pass) throws ClassNotFoundException {
        try {
        	Class.forName("com.mysql.jdbc.Driver");  
            String connStr = "jdbc:mysql://" + server;
            conn = DriverManager.getConnection(connStr, uName, pass);
            s = conn.createStatement();
        	
        	
        } catch (SQLException e) {
            System.out.println("Connection Error " +e.toString() );
            System.exit(0);
        }
        System.out.println("Connected");
    }
    
    private Database() throws ClassNotFoundException {
        this("localhost:3306/SWE311", "root", "root");
    }
    
    protected static Database getDatabase() throws ClassNotFoundException{
    	if (database == null){
    		database = new Database();
    	}
    	return database;
    }
    
    protected ResultSet executeQuery(Statement s, String query) {
        ResultSet r;

        try {
            r = s.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error: Query couldn't be excuted" );
            return null;
        }
        return r;
    }
    
    protected boolean executeUpdate(Statement s, String update) {
        try {
            s.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println("Error: Update cannot be done " );
            return false;
        }
        return true;
    }
    
    protected  String register( String uName, String pass,String email)throws SQLException {
    	String query = String.format("SELECT uID FROM USER" +
                "WHERE uName = %s ",uName);
    	ResultSet r = executeQuery (s, query);
    	if(r.next())
    		return "The user name is already registered";
    	
    	query = String.format("SELECT uID FROM USER" +
                "WHERE email = %s ",email);
    	r = executeQuery (s, query);
    	if(r.next())
    		return "The email is already registered";
    	
    	String update =String.format("INSERT INTO USER (uID, uName, hashedPass, email) " +
                "VALUES( %s, %s, %s)", uName, sha1(pass), email);
    	if(executeUpdate(this.s, update))
    		return "done";
    	return "Error: couldn't register";
    }
    
    private String sha1(String input){
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            System.out.println("Error: hash cannot be done");
        }
        return sha1;
    }
    
    protected  int login( String uName, String pass)throws SQLException {
    	String query = String.format("SELECT uID FROM USER" +
                "WHERE uName = %s and pass = %s",uName, sha1(pass));
    	ResultSet r = executeQuery (s, query);
    	if(r.next())
    		return r.getInt(0);
    	else return 0;
    }
    
    protected  String addList(int uID, String name, String discription, String location,
    	String type, int nRooms, int nBathrooms, int area, String offer, double price, List<File> pics)throws SQLException {
    		String query = String.format("SELECT uID FROM USER" +
                "WHERE uID = %d and name = %s ",uID, name);
    	ResultSet r = executeQuery (s, query);
    	if(r.next())
    		return "You already have "+ name;
    	
    	String update =String.format("INSERT INTO PROPERTY (uID, name, discription, location, type, nRooms, nBathrooms, area, offer, price) " +
                "VALUES( %d, %s, %s, %s, %s, %d, %d, %d, %s, %lf)", uID, name, discription, location, type, nRooms, nBathrooms, area, offer, price);
    	if(!executeUpdate(this.s, update))
    		return "Error: couldn't add your List";
    	r = executeQuery (s, query);
    	if(r.next()){
	    	if(!setImage(r.getInt(0), pics))
	    		return "Error: couldn't uploade your pictures";
	    	return "Done";
    	}
    	return "Error: couldn't add your List";
    }
    
    protected Boolean deleteList(int uID,int  pID){
    	
    	return true;
    }
    
    protected ResultSet getImage (int pID){
    	String query = String.format("SELECT * FROM PROPERTY "+
    			"WHERE pID = %d",pID);
        ResultSet r =executeQuery (s, query);
    	return r;
    }
    
    private Boolean setImage(int pID, List<File> pics){
    	if(pics.isEmpty())
    		return true;
    	String update =String.format("INSERT INTO REQUESTS (uID, pID, status) ");
    	for(File pic : pics){
    		String value = String.format("VALUES( %d, LOAD_FILE('%s'))", pID, pic.toString());
    		if(!executeUpdate(this.s, update+value)){
    			return false;
    		}
    	}
    	return true;
    		
    }
    
    protected ResultSet viewList(int pNum, int pSize){
    	String query = String.format("SELECT * FROM PROPERTY");
        ResultSet r =executeQuery (s, query);
    	return r;
    }
    
    protected ResultSet getDitails(int pID){
    	String query = String.format("SELECT * FROM PROPERTY "+
    			"WHERE pID = %d",pID);
        ResultSet r =executeQuery (s, query);
    	return r;
    }
    
    protected Boolean request(int uID, int pID) throws SQLException{
    	String query = String.format("SELECT uID FROM REQUESTS " +
                "WHERE uID = %d and pID = %d ",uID, pID);
    	ResultSet r = executeQuery (s, query);
    	if(r.next())
    		return false;
    	
    	String update =String.format("INSERT INTO REQUESTS (uID, pID, status) " +
                "VALUES( %d, %d, %s)", uID, pID, "Pending");
    	if(!executeUpdate(this.s, update))
    		return false;
    	return true;
    }
    
    protected Boolean deleteRequest(int uID, int pID){
    	return true;
    }
    
    protected ResultSet requestStatus(int uID){
    	String query = String.format("SELECT * FROM REQUESTS "+
    			"WHERE uID = %d",uID);
        ResultSet r =executeQuery (s, query);
    	return r;
    }
    
    protected ResultSet viewRequests(int uID){
    	String query = String.format("SELECT REQUESTS.uID, REQUESTS.pID, REQUESTS.status  FROM REQUESTS join PROPERTY on REQUESTS.pID = PROPERTY.uID "+
    			"WHERE PROPERTY.uID = %d",uID);
        ResultSet r =executeQuery (s, query);
    	return r;
    }
    
    protected Boolean pinProperty(int uID, int pID){
    	return true;
    }
    
    protected Boolean unpinProperty(int uID, int pID){
    	return true;
    }
    
    protected ResultSet pinnedProperty(int uID){
    	ResultSet r =null;
    	return r;
    }
}
