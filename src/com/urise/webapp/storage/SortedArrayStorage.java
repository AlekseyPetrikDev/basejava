package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndexByUUID(String uuid) {
        Resume r = new Resume();
        r.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, sizeResume, r);
    }

    @Override
    protected void insertResume(int ind, Resume r) {
        ind = -ind - 1;
        System.arraycopy(storage, ind, storage, ind + 1, sizeResume - ind);
        storage[ind] = r;
    }

    @Override
    protected void removeResume(int ind) {
        System.arraycopy(storage, ind + 1, storage, ind, sizeResume - ind);
    }
}
