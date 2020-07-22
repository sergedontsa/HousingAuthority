package com.housing.authority.Exception;

public class EmployeeDocumentStorageException extends RuntimeException {
    public EmployeeDocumentStorageException(String message) {
       super(message);
    }

   public EmployeeDocumentStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
