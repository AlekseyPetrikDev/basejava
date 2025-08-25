package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int SIZE_STORAGE = 10000;
    protected Resume[] storage = new Resume[SIZE_STORAGE];
    protected int sizeResume;

    public int size() {
        return sizeResume;
    }

    public void clear() {
        Arrays.fill(storage, 0, sizeResume - 1, null);
        sizeResume = 0;
    }

    public void save(Resume r) {
        if (sizeResume == SIZE_STORAGE) {
            System.out.println("stack over flow");
            return;
        }
        int ind = getIndexByUUID(r.getUuid());
        if (ind > -1) {
            System.out.println("Error: resume already exists");
            return;
        }
        insertResume(ind, r);
        sizeResume++;
    }

    public void delete(String uuid) {
        int ind = getIndexByUUID(uuid);
        if (ind < 0) {
            System.out.println("Error: resume not found");
            return;
        }
        removeResume(ind);
        sizeResume--;
    }

    public void update(Resume r) {
        int ind = getIndexByUUID(r.getUuid());
        if (ind < 0) {
            System.out.println("Error: resume not found");
            return;
        }
        storage[ind] = r;
    }

    public Resume get(String uuid) {
        int ind = getIndexByUUID(uuid);
        if (ind < 0) {
            System.out.println("Error: resume not found");
            return null;
        }
        return storage[ind];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeResume);
    }

    protected abstract void removeResume(int ind);

    protected abstract void insertResume(int ind, Resume r);

    protected abstract int getIndexByUUID(String uuid);
}
