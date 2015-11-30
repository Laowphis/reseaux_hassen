package fr.ensisa.hassenforder.proximity.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;
import fr.ensisa.hassenforder.proximity.model.Mode;


public class Reader extends BasicAbstractReader implements Protocol {
	private boolean connected;
	private int CONNECT;
	String username;
	int userx;
	int usery;
	int userradius;
	int usernb;
	Map<String, Preference> userpref;
	Mode modetemp;
	public User me=null;
	public Document document;
	public int userid;
	
	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0: break;
		case CONNECTION: 
			rConnect();
		break;
		case CONNECTION_FAIL:
			break;
		default:
			break;
		}
	}

	public void rConnect(){
		connected = readBoolean();
		
		username=readString();
		userx=readInt();
		usery=readInt();
		userradius=readInt();
		int usermode=readInt();
		if (usermode ==0) modetemp=Mode.VISIBLE;
		else if (usermode ==1) modetemp=Mode.HIDDEN;
		else if (usermode==2) modetemp=Mode.OCCUPIED;
		else System.out.println("erreur int mode");
		
		usernb=readInt();
		int[] lvl = new int[usernb];
		String[] nom = new String[usernb];
		Boolean[] visi = new Boolean[usernb];
		
		for(int i=0 ; i<usernb;i++){
			nom[i]=readString();
			lvl[i]=readInt();
			visi[i]=readBoolean();
		}
		
		Preference pref = new Preference(nom[0],lvl[0],visi[0]);
		userpref.put(username,pref);
		/*
		for(int j=0 ; j<usernb;j++){
			pref.setName(nom[0]);
			pref.setLevel(lvl[0]);
			pref.setVisibility(visi[0]);
			userpref.put(username,pref);
			pref=null;
		}
		*/
	}
	
	
	public boolean getConnectedState(){
		return this.connected;
	}
	
	public void readerConnectFailed(){
		connected = readBoolean();
	}

	public int getUserID() {
		return this.userid;
	}

	public String setName(String username2) {
		
		return username;
	}

	public int setX(int userx2) {
		return userx;
	}

	public int setY(int usery2) {
		return usery;
	}

	public int setRadius(int userradius2) {
		return userradius;
	}
}
