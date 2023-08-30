package ehrBlockchain;

import java.io.File;

public class app {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String masterFolder = "masterEHR";
		final String fileName = masterFolder + "/chain.bin";
		
		Blockchain EHRchain = Blockchain.get_instance(fileName);
		if (!new File(masterFolder).exists()) {
			System.err.println("> creating Blockchain binary !");
			new File(masterFolder).mkdir();
			/* create genesis block */
			EHRchain.genesis();
			EHRchain.distribute();
			
		} else {
			// dummy record
			String tranx1 = "alice|bob|debit|rm|10";
			String tranx2 = "helen|bob|debit|rm|20";
			String tranx3 = "helen|bob|debit|rm|30";
			String tranx4 = "helen|bob|debit|rm|40";
			
			RecordCollection tranxLst = new RecordCollection();
			tranxLst.add(tranx1);
			tranxLst.add(tranx2);
			tranxLst.add(tranx3);
			tranxLst.add(tranx4);
			
			String previousHash = EHRchain.get().getLast().getHeader().getCurrentHash();
			
			//block-1
			Block b1 = new Block(previousHash);
			b1.setEhrContainer(tranxLst);
			
			//add block into chain
			EHRchain.nextBlock(b1);
			EHRchain.distribute();
			
			System.out.println(b1);
		}
	}
}
