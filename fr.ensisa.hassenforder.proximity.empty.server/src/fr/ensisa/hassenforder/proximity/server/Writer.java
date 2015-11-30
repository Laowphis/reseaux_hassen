package fr.ensisa.hassenforder.proximity.server;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

public class Writer extends BasicAbstractWriter implements Protocol{

	private Document document;
	
	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void isOK(User user){
		int mode=-1;
		writeInt(CONNECTION);
		writeBoolean(true);
		writeString(user.getName());
		writeInt(user.getX());
		writeInt(user.getY());
		writeInt(user.getRadius());
			if (user.getMode()==Mode.VISIBLE) mode =0;
			else if (user.getMode()==Mode.HIDDEN) mode =1;
			else if (user.getMode()==Mode.OCCUPIED) mode=2;
			else System.out.println("erreur int mode");
		writeInt(mode);
		int nb = user.getPreferences().size();
		writeInt(nb);
		for(int i=0;i<nb;i++){
		writeString(user.getPreferenceByPosition(i).getName());
		writeInt(user.getPreferenceByPosition(i).getLevel());
		writeBoolean(user.getPreferenceByPosition(i).isVisibility());
		}
		
	}	

	public void isKO() {
			writeInt(CONNECTION_FAIL);
			writeBoolean(false);
			
	}

	
}
