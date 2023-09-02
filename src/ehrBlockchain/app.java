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
//			String tranx1 = "CS0001|BD/5lZqGUxOQcYEnbMrrMg==|-|-|-|-|-|-|-|-|-|-|2023-04-21|-|-|-|NA";
//			String tranx2 = "CS0002|BD/5lZqGUxOQcYEnbMrrMg==|-|-|-|-|-|-|-|-|-|-|2022-02-22|-|-|-|NA";
//			String tranx3 = "CS0002|BD/5lZqGUxOQcYEnbMrrMg==|-|-|-|-|-|-|-|-|-|A0001|2022-08-30|-|-|-|NA";
//			String tranx4 = "CS0001|BD/5lZqGUxOQcYEnbMrrMg==|-|-|-|-|-|-|-|-|-|A0002|2023-06-30|-|-|-|NA";
//			
			String tranx1 = "CS0003|BD/5lZqGUxOQcYEnbMrrMg==|-|-|-|-|-|-|-|-|-|-|2023-05-21|-|-|-|NA";
			String tranx2 = "CS0004|BD/5lZqGUxOQcYEnbMrrMg==|-|-|-|-|-|-|-|-|-|-|2022-07-22|-|-|-|NA";
			String tranx3 = "CS0003|BD/5lZqGUxOQcYEnbMrrMg==|-|-|-|-|-|-|-|-|-|A0003|2023-08-31|-|-|-|Healthy";
			String tranx4 = "CS0004|BD/5lZqGUxOQcYEnbMrrMg==|-|-|-|-|-|-|-|-|-|A0004|2023-09-01|-|-|-|Death";
			
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
