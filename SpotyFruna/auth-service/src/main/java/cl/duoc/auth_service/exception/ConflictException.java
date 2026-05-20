package cl.duoc.auth_service.exception;

public class ConflictException extends RuntimeException {

    public ConflictException(String mensaje) {
        super(mensaje);
    }

}
