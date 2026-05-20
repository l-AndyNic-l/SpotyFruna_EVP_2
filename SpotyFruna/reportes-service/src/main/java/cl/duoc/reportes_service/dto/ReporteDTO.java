package cl.duoc.reportes_service.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {

    private Long id;
    private String administrador;
    private String usuario;
    private String descripcion;
    private String fechaEnviado;
    private String fechaResuelto;
    private String tipoReporte;
    private String estado;

}
