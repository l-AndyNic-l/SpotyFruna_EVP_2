package cl.duoc.suscripciones_service.controller;

import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.exception.ErrorResponse;
import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.service.SuscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/suscripciones")
@Tag(name = "SUSCRIPCIONES", description = "API PARA LA GESTIÓN DE SUSCRIPCIONES")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;


    @Operation(
            summary = "Listar suscripciones",
            description = """
                          Retorna la lista completa de suscripciones registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Suscripciones encontradas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = SuscripcionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen suscripciones registradas",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error interno",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 500,
                                              "error": "INTERNAL_SERVER_ERROR",
                                              "mensaje": "Ocurrió un error inesperado en el servidor"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Error de comunicación con servicios externos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Servicio externo no disponible",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:06:15.450",
                                              "codigo": 503,
                                              "error": "SERVICE_UNAVAILABLE",
                                              "mensaje": "Error al conectar con el otro microservicio"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<SuscripcionDTO>> findAll() {
        List<SuscripcionDTO> suscripciones = suscripcionService.findAll();

        if (suscripciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(suscripciones);
    }



    @Operation(
            summary = "Listar suscripciones registradas entre fechas",
            description = """
                          Lista todas las suscripciones registradas dentro de un rango de fechas determinado.
                          Si no se registraron suscripciones en el rango indicado, la respuesta no contiene información.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Suscripciones encontradas en el rango de fechas indicado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = SuscripcionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen suscripciones registradas en el rango de fechas indicado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Las fechas ingresadas son inválidas",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Fechas inválidas",
                                    value = """                           
                                        {
                                          "fecha": "2026-06-21T03:51:53.233",
                                          "codigo": 400,
                                          "error": "BAD_REQUEST",
                                          "mensaje": "La solicitud contiene datos inválidos"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error interno",
                                    value = """
                                        {
                                          "fecha": "2026-06-21T04:05:10.000",
                                          "codigo": 500,
                                          "error": "INTERNAL_SERVER_ERROR",
                                          "mensaje": "Ocurrió un error inesperado en el servidor"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Error de comunicación con servicios externos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Servicio externo no disponible",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:06:15.450",
                                              "codigo": 503,
                                              "error": "SERVICE_UNAVAILABLE",
                                              "mensaje": "Error al conectar con el otro microservicio"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/between-dates")
    public ResponseEntity<List<SuscripcionDTO>> findAllBetweenDates(
            @Parameter(
                    name = "fecha-min",
                    description = "Fecha mínima del rango de búsqueda",
                    required = true,
                    example = "2020-01-01"
            )
            @RequestParam(name = "fecha-min") LocalDate fechaMin,

            @Parameter(
                    name = "fecha-max",
                    description = "Fecha máxima del rango de búsqueda",
                    required = true,
                    example = "2023-12-31"
            )
            @RequestParam(name = "fecha-max") LocalDate fechaMax
    ) {
        List<SuscripcionDTO> suscripciones = suscripcionService.findAllBetweenDates(fechaMin, fechaMax);

        if (suscripciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(suscripciones);
    }



    @Operation(
            summary = "Listar suscripciones por plan",
            description = """
                          Retorna según el plan indicado una lista de suscripciones registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Suscripciones encontradas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = SuscripcionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen suscripciones registradas",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Validación fallida",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 400,
                                              "error": "BAD_REQUEST",
                                              "mensaje": "idPlan no puede ser menor a 1 ni nulo"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Plan no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Plan inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Plan no encontrado"
                                }
                                """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error interno",
                                    value = """
                                        {
                                          "fecha": "2026-06-21T04:05:10.000",
                                          "codigo": 500,
                                          "error": "INTERNAL_SERVER_ERROR",
                                          "mensaje": "Ocurrió un error inesperado en el servidor"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Error de comunicación con servicios externos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Servicio externo no disponible",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:06:15.450",
                                              "codigo": 503,
                                              "error": "SERVICE_UNAVAILABLE",
                                              "mensaje": "Error al conectar con el otro microservicio"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/plan/{idPlan}")
    public ResponseEntity<List<SuscripcionDTO>> findAllByPlan(
            @Parameter(
                    name = "idPlan",
                    description = "Identificador único del plan",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idPlan
    ) {
        List<SuscripcionDTO> suscripciones = suscripcionService.findAllByPlan(idPlan);

        if (suscripciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(suscripciones);
    }



    @Operation(
            summary = "Listar suscripciones por activado/desactivado",
            description = """
                          Retorna según si está activada/desactivada una lista de suscripciones registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Suscripciones encontradas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = SuscripcionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen suscripciones registradas",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Validación fallida",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 400,
                                              "error": "BAD_REQUEST",
                                              "mensaje": "Activado no puede ser nulo"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error interno",
                                    value = """
                                        {
                                          "fecha": "2026-06-21T04:05:10.000",
                                          "codigo": 500,
                                          "error": "INTERNAL_SERVER_ERROR",
                                          "mensaje": "Ocurrió un error inesperado en el servidor"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Error de comunicación con servicios externos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Servicio externo no disponible",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:06:15.450",
                                              "codigo": 503,
                                              "error": "SERVICE_UNAVAILABLE",
                                              "mensaje": "Error al conectar con el otro microservicio"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/activado")
    public ResponseEntity<List<SuscripcionDTO>> findAllByActivado(
            @Parameter(
                    name = "activado",
                    description = "Suscripción activada/desactivada",
                    required = true,
                    example = "true"
            )
            @RequestParam(name = "activado") Boolean activado
    ) {
        List<SuscripcionDTO> suscripciones = suscripcionService.findAllByActivado(activado);

        if (suscripciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(suscripciones);
    }



    @Operation(
            summary = "Obtener suscripción por identificador",
            description = """
                    Retorna la información de una suscripción a partir de su identificador.
                    La operación responde exitosamente cuando la suscripción es encontrada.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Suscripción encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuscripcionDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Suscripción no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Suscripción inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Suscripción no encontrada"
                                }
                                """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error interno",
                                    value = """
                                        {
                                          "fecha": "2026-06-21T04:05:10.000",
                                          "codigo": 500,
                                          "error": "INTERNAL_SERVER_ERROR",
                                          "mensaje": "Ocurrió un error inesperado en el servidor"
                                        }
                                        """
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuscripcionDTO> findById(
            @Parameter(
                    name = "idSuscripcion",
                    description = "Identificador única de la suscripción",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idSuscripcion
    ) {
        SuscripcionDTO suscripcion = suscripcionService.findById(idSuscripcion);

        return ResponseEntity.ok(suscripcion);
    }



    @Operation(
            summary = "Registrar una suscripción",
            description = """
                          Permite registrar una nueva suscripción en el sistema.
                          La operación retorna la suscripción registrada junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Suscripción registrada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuscripcionDTO.class),
                            examples = @ExampleObject(
                                    name = "Suscripción registrada",
                                    value = """
                                            {
                                                "id": 1,
                                                "fechaInicio": "01/01/2024",
                                                "fechaTermino": "01/01/2025",
                                                "activado": "Si",
                                                "plan": "Premium",
                                                "usuario": "sabrina@artist.com"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Validación fallida",
                                    value = """
                                        {
                                          "fecha": "2026-06-21T05:20:00",
                                          "codigo": 400,
                                          "error": "BAD_REQUEST",
                                          "mensaje": "La solicitud contiene datos inválidos"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error interno",
                                    value = """
                                            {
                                              "id": 1,
                                              "nombreCompleto": "Sabrina Carpenter",
                                              "nickname": "sabrinacarpenter",
                                              "email": "sabrina@artist.com",
                                              "edad": 27,
                                              "celular": 311234567,
                                              "tipoUsuario": "Artista"
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Suscripcion> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la suscripción que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuscripcionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nueva suscripción",
                                    value = """
                                            {
                                                "id": 1,
                                                "fechaInicio": "01/01/2024",
                                                "fechaTermino": "01/01/2025",
                                                "activado": "Si",
                                                "plan": "Premium",
                                                "usuario": "sabrina@artist.com"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Suscripcion suscripcion
    ) {
        Suscripcion suscripcionInstanciada = suscripcionService.save(suscripcion);
        return new ResponseEntity<>(suscripcionInstanciada, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar una suscripción",
            description = """
                    Permite actualizar la información de una suscripción existente a partir de su identificador.
                    La operación retorna la suscripción actualizada cuando la actualización es exitosa.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Suscripción actualizada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuscripcionDTO.class),
                            examples = @ExampleObject(
                                    name = "Suscripción actualizada",
                                    value = """
                                            {
                                                "id": 1,
                                                "fechaInicio": "01/01/2024",
                                                "fechaTermino": "01/01/2025",
                                                "activado": "Si",
                                                "plan": "Premium",
                                                "usuario": "sabrina@artist.com"
                                            }
                                            """

                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Validación fallida",
                                    value = """
                                        {
                                          "fecha": "2026-06-21T05:20:00",
                                          "codigo": 400,
                                          "error": "BAD_REQUEST",
                                          "mensaje": "La solicitud contiene datos inválidos"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Suscripción no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Suscripción inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Suscripción no encontrada"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error interno",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 500,
                                              "error": "INTERNAL_SERVER_ERROR",
                                              "mensaje": "Ocurrió un error inesperado en el servidor"
                                            }
                                            """
                            )
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Suscripcion> update(
            @Parameter(
                    name = "idSuscripcion",
                    description = "Identificador única de la suscripción",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idSuscripcion,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la reproducción que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuscripcionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nueva reproducción",
                                    value = """
                                            {
                                                "id": 1,
                                                "fechaInicio": "01/01/2024",
                                                "fechaTermino": "01/01/2025",
                                                "activado": "Si",
                                                "plan": "Premium",
                                                "usuario": "sabrina@artist.com"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Suscripcion suscripcion
    ) {
        Suscripcion suscripcionInstanciada = suscripcionService.update(idSuscripcion, suscripcion);
        return ResponseEntity.ok(suscripcionInstanciada);
    }



    @Operation(
            summary = "Eliminar una suscripción",
            description = """
                          Permite eliminar una suscripción del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Suscripción eliminada correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningúna suscripción en el sistema relacionada a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Suscripción inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Suscripción no encontrada"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error interno",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 500,
                                              "error": "INTERNAL_SERVER_ERROR",
                                              "mensaje": "Ocurrió un error inesperado en el servidor"
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "idSuscripcion",
                    description = "Identificador única de la suscripción",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idSuscripcion
    ) {
        suscripcionService.deleteById(idSuscripcion);
        return ResponseEntity.noContent().build();
    }

}
