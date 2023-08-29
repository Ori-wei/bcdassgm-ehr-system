package ehrBlockchain;

import java.io.Serializable;

public class Header implements Serializable{
	private static final long serialVersionUID = 1L;
	public int index;
	public String currentHash;
	public String previousHash;
	public long timestamp;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getCurrentHash() {
		return currentHash;
	}

	public void setCurrentHash(String currentHash) {
		this.currentHash = currentHash;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Header [index=" + index + ", currentHash=" + currentHash + ", previousHash=" + previousHash
				+ ", timestamp=" + timestamp + "]";
	}
}
