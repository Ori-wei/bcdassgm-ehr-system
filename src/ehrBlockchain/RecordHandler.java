package ehrBlockchain;

import java.io.*;

public class RecordHandler {
	
	// insert a new record
	public static void serializeRecords(RecordCollection records) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accumulatedRecords.bin"))) {
            oos.writeObject(records);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	// retrieve and read records
    public static RecordCollection deserializeRecords() {
        RecordCollection records = new RecordCollection();
        File file = new File("accumulatedRecords.bin");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                records = (RecordCollection) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return records;
    } 
}