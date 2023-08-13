package com.proyecto.daw.proyectodaw.handler;

public class CursoException extends RuntimeException{
    
    public CursoException(String message) {
        super(message);
    }

    public static class CursoNoGuardadoException extends CursoException {
        public CursoNoGuardadoException(String message) {
            super(message);
        }
    }

    public static class CursoNoActualizadoException extends CursoException {
        public CursoNoActualizadoException(String message) {
            super(message);
        }
    }

    public static class CursoNoEliminadoException extends CursoException {
        public CursoNoEliminadoException(String message) {
            super(message);
        }
    }
}
