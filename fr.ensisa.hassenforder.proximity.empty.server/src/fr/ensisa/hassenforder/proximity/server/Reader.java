package fr.ensisa.hassenforder.proximity.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;

public class Reader extends BasicAbstractReader implements Protocol {
	private String name;

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
System.out.println("receive");
		type = readInt ();
		switch (type) {
		case 0 : 
			break;
		case CONNECTION:
			rConnect();
			break;
		default:
			System.out.println("défault");
			break;
		}
	}
	
	public void rConnect(){
		name = readString();
		System.out.println(name);
	}
	
	public String getName() {
		return this.name;
	}
}
