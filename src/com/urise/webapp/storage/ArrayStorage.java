package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (sizeResume < SIZE_STORAGE) {
            int i = getIndexByUUID(r.getUuid());
            if (i > -1) {
                System.out.println("Error: resume already exists");
            } else {
                storage[sizeResume] = r;
                sizeResume++;
            }
        } else {
            System.out.println("stack over flow");
        }
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
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    protected int getIndexByUUID(String uuid) {
        for (int i = 0; i < sizeResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
