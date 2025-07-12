package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int SIZE_STORAGE = 10000;
    private Resume[] storage = new Resume[SIZE_STORAGE];
    private int sizeResume;

    public void clear() {
        for (int i = 0; i < sizeResume; i++) {
            storage[i] = null;
        }
        sizeResume = 0;
    }

    public void save(Resume r) {
        if (sizeResume < SIZE_STORAGE) {
            int i = getIndexByUUID(r.getUuid());
            if (i > -1) {
                System.out.println("Error: resume exists");
            } else {
                storage[sizeResume] = r;
                sizeResume++;
            }
        } else {
            System.out.println("stack over flow");
        }
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

    public void delete(String uuid) {
        int i = getIndexByUUID(uuid);
        if (i < 0) {
            System.out.println("Error: resume not found");
        } else {
            storage[i] = storage[sizeResume - 1];
            storage[sizeResume - 1] = null;
            sizeResume--;
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
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
