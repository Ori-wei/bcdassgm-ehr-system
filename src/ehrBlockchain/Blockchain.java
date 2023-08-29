package ehrBlockchain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import com.google.gson.GsonBuilder;
import java.io.Serializable;

public class Blockchain implements Serializable {
	private static final long serialVersionUID = 1L;
	private static LinkedList<Block> EHRchain = new LinkedList<>();
	private static Blockchain _instance;
	public String chainFile;

	public static Blockchain get_instance(String chainFile) {
		if(_instance==null) {
			_instance = new Blockchain(chainFile);
		}
		return _instance;
	}

	public Blockchain(String chainFile) {
		super();
		this.chainFile = chainFile;
	}

	public void genesis() {
		Block genesis = new Block("0");
		EHRchain.add(genesis);
		persist();
	}
	
	public void nextBlock(Block newblock) {
	    EHRchain = get();
	    newblock.getHeader().setIndex(EHRchain.size());
	    EHRchain.add(newblock);
	    persist();
	}
	
	public LinkedList<Block> get(){
		try (
			FileInputStream fin = new FileInputStream(this.chainFile);
			ObjectInputStream oin = new ObjectInputStream(fin);
				){
			return (LinkedList<Block>)oin.readObject();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	private void persist(){
		try (
			FileOutputStream fout = new FileOutputStream(this.chainFile);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
				){
			oout.writeObject(EHRchain);
			System.out.println("Blockchain master file is updated!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// print the binary file into text file
	public void distribute() {
		String chain = new GsonBuilder().setPrettyPrinting().create().toJson(EHRchain);
		System.out.println(chain);
	}
}
