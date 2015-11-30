package fr.ensisa.hassenforder.proximity.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.User;

public class SessionServer implements Protocol {

	private Socket connection;
	private Document document;
	public String name;
	
	public SessionServer (Document document, Socket connection) {
		this.document = document;
		this.connection = connection;
	}

	public boolean operate() {
		try {
			Writer writer = new Writer (connection.getOutputStream());
			Reader reader = new Reader (connection.getInputStream());
			reader.receive ();
			switch (reader.getType ()) {
			case 0 : return false; // socket closed
			case CONNECTION : 
				User usercurrent = document.doConnect(reader.getName());
				if (usercurrent == null)
					writer.isKO();
				else{
					writer.isOK(usercurrent);
				}
				break;
			case -1:
				break;
			default: return false; // connection jammed
			}
			writer.send ();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
