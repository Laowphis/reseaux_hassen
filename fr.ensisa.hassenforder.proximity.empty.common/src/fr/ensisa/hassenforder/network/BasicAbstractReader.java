package fr.ensisa.hassenforder.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import fr.ensisa.hassenforder.proximity.model.Preference;

public class BasicAbstractReader {

	protected DataInputStream inputStream;
	protected int type;

	public BasicAbstractReader(InputStream inputStream2) {
		this.inputStream = new DataInputStream (inputStream2);
	}

	protected boolean readBoolean() {
		try {
			int x = inputStream.readInt();
			if (x != 0) return true;
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	protected short readShort () {
		try {
			return inputStream.readShort();
		} catch (IOException e) {
			return 0;
		}
	}

	protected int readInt() {
		try {
			return inputStream.readInt();
		} catch (IOException e) {
			return 0;
		}
	}

	protected long readLong() {
		try {
			return inputStream.readLong();
		} catch (IOException e) {
			return 0;
		}
	}

	protected String readString() {
		try {
			return inputStream.readUTF();
		} catch (IOException e) {
			return "";
		}
	}
/*
	protected Preference readPref(){
		String refname=readString();
		int reflvl=readInt();
		Boolean refbool=readBoolean();
		Preference p=null;
		p.setName(refname);
		p.setLevel(reflvl);
		p.setVisibility(refbool);
		return p;
	}
	*/
	public int getType() {
		return type;
	}

}