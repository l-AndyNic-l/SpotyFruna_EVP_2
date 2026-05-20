package cl.duoc.reportes_service.exception;

public class FeignServiceException extends RuntimeException {

    public FeignServiceException(String mensaje) {
        super(mensaje);
    }

}
