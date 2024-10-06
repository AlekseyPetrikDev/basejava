import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int sizeResume;

    public void clear() {
        for (int i = 0; i < sizeResume; i++) {
            storage[i] = null;
        }
        sizeResume = 0;
    }

    public void save(Resume r) {
        int index = getIndexByUUID(r.uuid);
        if (index > -1) {
            storage[index] = r;
        } else {
            storage[sizeResume] = r;
            sizeResume++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndexByUUID(uuid);
        return index > -1 ? storage[index] : null;
    }

    public void delete(String uuid) {
        int index = getIndexByUUID(uuid);
        if (index > -1) {
            storage[index] = storage[sizeResume - 1];
            sizeResume--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeResume);
    }

    public int size() {
        return sizeResume;
    }

    private int getIndexByUUID(String uuid) {
        for (int i = 0; i < sizeResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
