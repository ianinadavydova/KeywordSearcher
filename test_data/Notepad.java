public class Notepad {
    private static final int INITIAL_SIZE = 1000;
    private Record[] records;
    private int recordsCount;

    public Notepad() {
        records = new Record[INITIAL_SIZE];
        recordsCount = 0;
    }

    public void addRecord(Record newRecord) {
        if (recordsCount == records.length) {
            extendRecors();
        }
        records[recordsCount++] = newRecord;
    }

    public void removeRecord(Record record) {
        int index = indexOf(record);
        if (index == -1) {
            System.err.println("Record not found");
            return;
        }
        for (int j = index + 1; j < recordsCount; j++) {
            records[j - 1] = records[j];
        }
        recordsCount--;
        records[recordsCount] = null;
        if (recordsCount > 0 && recordsCount == records.length - INITIAL_SIZE) {
            shrinkRecords();
        }
    }

    public Record findByTitle (String title) {
        for (int i = 0; i < recordsCount; i++) {
            if (records[i].getTitle().equals(title)) {
                return records[i];
            }
        }
        return null;
    }

    public Record[] getAllRecords () {
        return copyRecordsToArray(recordsCount);
    }

    private int indexOf(Record record) {
        for (int i = 0; i < recordsCount; i++) {
            if (records[i] == record) {
                return i;
            }
        }
        return -1;
    }

    private Record[] copyRecordsToArray(int arrayLength) {
        Record[] result = new Record[arrayLength];
        System.arraycopy(records, 0, result, 0, recordsCount);
        return result;
    }

    private void extendRecors() {
        records = copyRecordsToArray(records.length + INITIAL_SIZE);
    }

    private void shrinkRecords() {
        records = copyRecordsToArray(records.length - INITIAL_SIZE);
    }
}
