package cl.duoc.usuarios_service.exception;

public class FeignServiceException extends RuntimeException {

    public FeignServiceException(String mensaje) {
        super(mensaje);
    }

}
