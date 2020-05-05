package com.housing.authority.Exception;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String id){
        super("Could not locate your request with the id: "+ id);
    }
}
