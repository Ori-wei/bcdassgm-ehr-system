package ehrBlockchain;

import java.io.Serializable;
import java.sql.Timestamp;
import Hashing.Hasher;

public class Block implements Serializable{
	private static final long serialVersionUID = 1L;
	public RecordCollection ehrContainer;
	public Header header;
	private static int count = -1;
	
	public Block(String previousHash) {
		count++;
		this.ehrContainer = new RecordCollection();
		header = new Header();
		header.setIndex(count);
		header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
		header.setPreviousHash(previousHash);
		String info = String.join("+", Integer.toString(header.getIndex()),
				Long.toString(header.getTimestamp()), header.getPreviousHash());
		String blockHash = Hasher.sha256(info);
		header.setCurrentHash(blockHash);
	}

	public RecordCollection getEhrContainer() {
		return ehrContainer;
	}

	public void setEhrContainer(RecordCollection ehrContainer) {
		this.ehrContainer = ehrContainer;
	}

	public Header getHeader() {
		return header;
	}

	@Override
	public String toString() {
		return "Block [header=" + header + ", ehrContainer=" + ehrContainer + "]";
	}

		
}
