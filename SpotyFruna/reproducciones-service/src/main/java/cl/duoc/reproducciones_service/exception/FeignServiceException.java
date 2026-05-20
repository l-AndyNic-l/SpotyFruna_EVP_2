package cl.duoc.reproducciones_service.exception;

public class FeignServiceException extends RuntimeException {

    public FeignServiceException(String mensaje) {
        super(mensaje);
    }

}
