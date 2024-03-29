package ehrBlockchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecordCollection implements Serializable, Iterable<String>{
	private static final long serialVersionUID = 1L;
	private int size = 0;
	private final int maxSize = 4;
	transient private String merkleRoot; 
	public List<String> ehrList;
	public Header header;
	
	public RecordCollection() {
		ehrList = new ArrayList<>(size);
	}
	
	// ensure the maximum size of record is 4
	public void add(String ehr) {
        if (size < maxSize) {
            ehrList.add(ehr);
            size++;
        }

        if (size == maxSize) {
            calculateMerkleRoot();
        }
    }
	
	public void calculateMerkleRoot() {
        MerkleTree tree = new MerkleTree(ehrList);
        tree.build();
        this.merkleRoot = tree.getRoot();
    }
	
	public String getMerkleRoot() {
		return this.merkleRoot;
	}

	public List<String> getEhrList() {
		return this.ehrList;
	}

	public void setEhrList(List<String> ehrList) {
		this.ehrList = ehrList;
	}

	public void setMerkleRoot(String merkleRoot) {
		this.merkleRoot = merkleRoot;
	}

	@Override
	public String toString() {
		return "RecordCollection [size=" + size + ", ehrList=" + ehrList + "]";
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return ehrList.iterator();
	}
}
