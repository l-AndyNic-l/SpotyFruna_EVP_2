package cl.duoc.reproducciones_service.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String mensaje) {
        super(mensaje);
    }

}
