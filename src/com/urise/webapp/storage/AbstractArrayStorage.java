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

    public void update(Resume r) {
        int i = getIndexByUUID(r.getUuid());
        if (i < 0) {
            System.out.println("Error: resume not found");
        } else {
            storage[i] = r;
        }
    }

    public Resume get(String uuid) {
        int i = getIndexByUUID(uuid);
        if (i < 0) {
            System.out.println("Error: resume not found");
        }
        return i < 0 ? null : storage[i];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeResume);
    }

    protected abstract int getIndexByUUID(String uuid);
}
