package com.example.demo.exception;

public class EmptyLineException extends RuntimeException{
    public EmptyLineException(String message){
        super(message);
    }
}
