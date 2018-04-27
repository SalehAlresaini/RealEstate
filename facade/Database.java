package facade;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.xml.bind.DatatypeConverter;




public class Database {
	
	private Connection conn;
    private Statement s;

    protected Database(String server, String uName, String pass) {
        try {
            String connStr = "jdbc:oracle:thin:@" + server;
            conn = DriverManager.getConnection(connStr, uName, pass );
            s = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Error" );
            System.exit(0);
        }
    }
    
    protected Database() {
        this("0.0.0.0:xe", "root", "root");
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
}
