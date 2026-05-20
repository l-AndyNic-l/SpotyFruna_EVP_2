package cl.duoc.playlists_service.exception;

public class FeignServiceException extends RuntimeException {

    public FeignServiceException(String mensaje) {
        super(mensaje);
    }

}
