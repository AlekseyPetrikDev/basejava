package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super(String.format("Error: resume UUID = %s not found", uuid));
    }
}
