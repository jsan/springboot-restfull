package com.estudo.springboot.exception;

public class EmailAlreadyExistsException extends RuntimeException
{
    private String resourceName;
    private String fieldValue;

    public EmailAlreadyExistsException(String resourceName, String fieldValue)
    {
        super(String.format("%s already exists: %s", resourceName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}
