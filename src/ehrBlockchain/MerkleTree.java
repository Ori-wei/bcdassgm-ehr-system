package ehrBlockchain;

import java.util.ArrayList;
import java.util.List;
import Hashing.Hasher;

public class MerkleTree {
	private List<String> ehrList;
	private String root = "0";
	private static MerkleTree instance;
	
	public String getRoot() {
		return root;
	}
 
	public MerkleTree(List<String> ehrList) {
		super();
		this.ehrList = ehrList;
	}

	public static MerkleTree getInstance(List<String> ehrList) {
		if (instance == null) {
			return new MerkleTree(ehrList);
		}
		return instance;
	}
	
	public void build() {
		List<String> tempList = new ArrayList<>();
		
		// add the EHR records into a list
		for (String ehr : this.ehrList) {
			tempList.add(ehr);
		}
		
		// build a Merkle root
		List<String> EhrHashList = generateEhrHashList(tempList);
		while(EhrHashList.size() != 1) {
			EhrHashList = generateEhrHashList(EhrHashList);
		}
		
		// Merkle Root
		this.root = EhrHashList.get(0);
	}
	
	private List<String> generateEhrHashList(List<String> ehrList){
		List<String> EhrHashes = new ArrayList<>();
		
		int i = 0;
		while(i < ehrList.size()) {
			
			// get a value from the left side
			String left = ehrList.get(i);
			i++;
			
			// get a value from the right side
			String right = "";
			if( i != ehrList.size() ) right = ehrList.get(i);
			
			// combine both and return a hash value
			String hash = Hasher.sha256(left.concat(right));
			EhrHashes.add(hash);
			i++;
		}
		
		return EhrHashes;
	}
}
