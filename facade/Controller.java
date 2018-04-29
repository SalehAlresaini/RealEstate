package facade;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.omg.CORBA.Request;

public class Controller {
	private int uID;
	private static Controller controller = new Controller();
	
	private Controller(){
		uID =0;
	}
	
	public static Controller getController() throws ClassNotFoundException{
		//Database.getDatabase();
		return controller;
	}
	
	public  String register( String uName, String pass,String email) throws SQLException, ClassNotFoundException {
    	return Database.getDatabase().register(uName, pass, email);
    }
    
    public int login( String uName, String pass) throws SQLException, ClassNotFoundException{
    	uID = Database.getDatabase().login(uName, pass);
    	return uID;
    }
    
    public  String addList(String name, String discription, String location,
    		String type, int nRooms, int nBathrooms, int area, String offer, double price, List<File> pics) throws SQLException, ClassNotFoundException{
    	if(uID ==0){
    		return "you must login to add list";
    	}
    	return Database.getDatabase().addList
    			(uID, name, discription, location, type, nRooms, nBathrooms, area, offer, price, pics);
    }
    
    public Boolean deleteList(int  pID) throws ClassNotFoundException{
    	return Database.getDatabase().deleteList(uID, pID);
    }
    
    public ResultSet getImage (int pID) throws ClassNotFoundException{
    	return Database.getDatabase().getImage(pID);
    }
    
    /*public List<Listing> viewList(int pNum, int pSize){
    	ResultSet r =Database.getDatabase().viewList(pNum, pSize);
    	List<Listing> view = new ArrayList<Listing>;
    	while(r.next()){
    		view.add(new Listing(r.getInt(0), r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getInt(6), 
    				r.getInt(7), r.getInt(8), r.getString(9), r.getDouble(10)));
    	}
    	return view;
    }*/
    
    /*public Listing getDitails(int pID){
    	ResultSet r =Database.getDatabase().getDitails(pID);
    	if(r.next())
    		return new Listing(r.getInt(0), r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getInt(6), 
    				r.getInt(7), r.getInt(8), r.getString(9), r.getDouble(10));
    	return null;
    }*/
    
    public Boolean request(int pID) throws SQLException, ClassNotFoundException{
    	if(uID==0)
    		return false;
    	return Database.getDatabase().request(uID, pID);
    }
    
    public Boolean deleteRequest( int pID) throws ClassNotFoundException{
    	if(uID==0)
    		return false;
    	return Database.getDatabase().deleteRequest(uID, pID);
    }
    
    /*public List<Request> requestStatus(){
    	ResultSet r =Database.getDatabase().requestStatus(uID);
    	List<Request> requests= new List<Request>;
    	while(r.next())
    		requests.add(Request(r.getInt(0), r.getInt(1), r.getString(2)));
    	return requests;
    }*/
    
    /*public ResultSet viewRequests(){
    	ResultSet r =Database.getDatabase().viewRequests(uID);
    	List<Request> requests= new List<Request>;
    	while(r.next())
    		requests.add(Request(r.getInt(0), r.getInt(1), r.getString(2)));
    	return requests;
    }*/
    
    public Boolean pinProperty( int pID) throws ClassNotFoundException{
    	return Database.getDatabase().pinProperty(uID, pID);
    }
    
    public Boolean unpinProperty(int pID) throws ClassNotFoundException{
    	return Database.getDatabase().unpinProperty(uID, pID);
    }
    
    /*public List<Listing> pinnedProperty(){
    	ResultSet r =Database.getDatabase().pinnedProperty(uID);
    	List<Listing> pinned = new ArrayList<Listing>;
    	while(r.next()){
    		pinned.add(new Listing(r.getInt(0), r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getInt(6), 
    				r.getInt(7), r.getInt(8), r.getString(9), r.getDouble(10)));
    	}
    	return pinned;
    }*/
}
