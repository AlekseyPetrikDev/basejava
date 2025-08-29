package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.SIZE_STORAGE;

class AbstractArrayStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    protected void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    protected void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    protected void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    protected void save() {
        storage.save(new Resume(UUID_4));
        Assertions.assertArrayEquals(storage.getAll(), new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3), new Resume(UUID_4)});
    }

    @Test
    protected void saveExist() {
        ExistStorageException exception = Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(new Resume(UUID_1));
        });
        Assertions.assertEquals(String.format("Error: resume UUID = %s already exists", UUID_1), exception.getMessage());
    }

    @Test
    protected void saveOverFlow() {
        try {
            for (int i = storage.size(); i < SIZE_STORAGE; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }

        StorageException exception = Assertions.assertThrows(StorageException.class, () -> {
            storage.save(new Resume());
        });
        Assertions.assertEquals("Error: stack over flow", exception.getMessage());
    }

    @Test
    protected void delete() {
        storage.delete(UUID_3);
        Assertions.assertEquals(2, storage.size());
    }

    @Test
    protected void deleteNotExist() {
        NotExistStorageException exception = Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
        Assertions.assertEquals(String.format("Error: resume UUID = %s not found", "dummy"), exception.getMessage());
    }

    @Test
    protected void update() {
        Resume r = new Resume(UUID_2);
        storage.update(r);
        Assertions.assertEquals(r, storage.get(UUID_2));
    }

    @Test
    protected void updateNotExist() {
        Resume r = new Resume(UUID_4);
        NotExistStorageException exception = Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(r);
        });
        Assertions.assertEquals(String.format("Error: resume UUID = %s not found", UUID_4), exception.getMessage());
    }

    @Test
    protected void get() {
        Assertions.assertEquals(UUID_2, storage.get(UUID_2).getUuid());
    }

    @Test
    protected void getAll() {
        Assertions.assertArrayEquals(storage.getAll(), new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)});
    }

    @Test
    protected void getNotExist() {
        NotExistStorageException exception = Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
        Assertions.assertEquals(String.format("Error: resume UUID = %s not found", "dummy"), exception.getMessage());
    }
}