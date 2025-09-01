package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertResume(int ind, Resume r) {
        storage[sizeResume] = r;
    }

    @Override
    protected void removeResume(int ind) {
        storage[ind] = storage[sizeResume - 1];
        storage[sizeResume - 1] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    @Override
    protected int getIndexByUUID(String uuid) {
        for (int i = 0; i < sizeResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
