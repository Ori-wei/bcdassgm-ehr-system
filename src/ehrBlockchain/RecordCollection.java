package ehrBlockchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import MerkleTree.*;

public class RecordCollection implements Serializable{
	private static final long serialVersionUID = 1L;
	private final int size = 4;
	public String merkleRoot;
	public List<String> ehrList;
	//private MerkleTree merkleTree;
	
	public RecordCollection() {
		ehrList = new ArrayList<>(size);
	}
	
	public void add(String ehr) {
		ehrList.add(ehr);
	}

	public String getMerkleRoot() {
		return this.merkleRoot;
	}

	public List<String> getEhrList() {
		return this.ehrList;
	}

	@Override
	public String toString() {
		return "RecordCollection [ehrList=" + ehrList + "]";
	}

}
