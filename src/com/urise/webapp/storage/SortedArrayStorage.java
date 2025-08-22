package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

import static java.lang.Math.abs;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndexByUUID(String uuid) {
        Resume r = new Resume();
        r.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, sizeResume, r);
    }

    @Override
    public void save(Resume r) {
        int ind = getIndexByUUID(r.getUuid());
        if (ind > -1) {
            System.out.println("Error: resume already exists");
            return;
        }
        ind = abs(ind) - 1;
        System.arraycopy(storage, ind, storage, ind + 1, sizeResume - ind);
        storage[ind] = r;
        sizeResume++;
    }

    @Override
    public void delete(String uuid) {
        int ind = getIndexByUUID(uuid);
        if (ind < 0) {
            System.out.println("Error: resume not found");
            return;
        }
        System.arraycopy(storage, ind + 1, storage, ind, sizeResume - ind);
        sizeResume--;
    }
}
