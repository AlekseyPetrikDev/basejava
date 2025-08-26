package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super(String.format("Error: resume UUID = %s already exists", uuid));
    }
}
