
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import facade.Controller;
import javafx.scene.image.Image;

public class Listing {
	int pID;
	int uID;
	String name;
	String discription;
	String location;
	String type;
	int nRooms;
	int nBathrooms;
	int area;
	String offer;
	double price;
	List<Image> pic = new ArrayList<Image>();
	
	public Listing(int u, String n, String d, String l, String t, int nR, int nB,
			int a, String o, double p){
		uID =u;
		name =n;
		discription =d;
		location =l;
		type =t;
		nRooms =nR;
		nBathrooms =nB;
		area =a;
		offer =o;
		price =p;
	}
	public Listing(int pid, int u, String n, String d, String l, String t, int nR, int nB,
			int a, String o, double p) throws SQLException{
		pID =pid;
		uID =u;
		name =n;
		discription =d;
		location =l;
		type =t;
		nRooms =nR;
		nBathrooms =nB;
		area =a;
		offer =o;
		price =p;
		retrievePic();
	}
	public void retrievePic() throws SQLException{
		ResultSet r = Controller.getController().getImage(pID);
		while(r.next()){
			pic.add((Image) r.getBlob(1));
		}
	}
}
