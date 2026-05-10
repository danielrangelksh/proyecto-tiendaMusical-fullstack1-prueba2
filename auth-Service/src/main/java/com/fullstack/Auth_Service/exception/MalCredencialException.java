package com.fullstack.Auth_Service.exception;

public class MalCredencialException extends RuntimeException {
    public MalCredencialException(String mensaje) {
        super(mensaje);
    }
}