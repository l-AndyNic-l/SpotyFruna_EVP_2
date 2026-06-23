package cl.duoc.reproducciones_service.controller;

import cl.duoc.reproducciones_service.dto.ErrorResponse;
import cl.duoc.reproducciones_service.model.Reproduccion;
import cl.duoc.reproducciones_service.dto.ReproduccionDTO;
import cl.duoc.reproducciones_service.service.ReproduccionService;
import feign.Response;
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
@RequestMapping( "/api/v1/reproducciones" )
@Tag(name = "REPRODUCCIONES", description = "API PARA LA GESTIÓN DE REPRODUCCIONES")
public class ReproduccionController {

    @Autowired
    private ReproduccionService reproduccionService;


    @Operation(
            summary = "Listar reproducciones",
            description = """
                          Retorna la lista completa de reproducciones registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Reproducciones encontradas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ReproduccionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen reproducciones registradas",
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
            )
    })
    @GetMapping
    public ResponseEntity<List<ReproduccionDTO>> findAll() {
        List<ReproduccionDTO> reproducciones = reproduccionService.findAll();

        if (reproducciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reproducciones);
    }



    @Operation(
            summary = "Obtener reproducción por identificador",
            description = """
                          Retorna la información de una reproducción a partir de su identificador.
                          La operación responde exitosamente cuando la reproducción es encontrado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Reproducción encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class)
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
                                          "mensaje": "idReproduccion no puede ser menor a 1 ni nulo"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reproducción no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Reproducción inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Reproducción no encontrada"
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
    @GetMapping("/{idReproduccion}")
    public ResponseEntity<ReproduccionDTO> findById(
            @Parameter(
                    name = "idReproduccion",
                    description = "Identificador único de la reproducción",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idReproduccion
    ) {
        ReproduccionDTO reproduccion = reproduccionService.findById(idReproduccion);

        return ResponseEntity.ok(reproduccion);
    }



    @Operation(
            summary = "Listar reproducciónes por tipo de dispositivo",
            description = """
                          Retorna según el tipo de dispositivo indicado una lista de reproducciones registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Reproducción encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class)
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
                                          "mensaje": "idDispositivo no puede ser menor a 1 ni nulo"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dispositivo no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Reproducción inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Dispositivo no encontrada"
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
    @GetMapping("/dispositivo/{idDispositivo}")
    public ResponseEntity<List<ReproduccionDTO>> findAllByDispositivo(
            @Parameter(
                    name = "idDispositivo",
                    description = "Identificador único del tipo de dispositivo",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idDispositivo
    ) {
        List<ReproduccionDTO> reproducciones = reproduccionService.findAllByDispositivo(idDispositivo);

        if (reproducciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reproducciones);
    }



    @Operation(
            summary = "Listar reproducciónes por canción",
            description = """
                          Retorna según la canción indicada una lista de reproducciones registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Reproducción encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class)
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
                                          "mensaje": "idCancion no puede ser menor a 1 ni nulo"
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
    @GetMapping("/cancion")
    public ResponseEntity<List<ReproduccionDTO>> findAllByCancion(
            @Parameter(
                    name = "idCancion",
                    description = "Identificador único de la canción",
                    required = true,
                    example = "1"
            )
            @RequestParam Long idCancion
    ) {
        List<ReproduccionDTO> reproducciones = reproduccionService.findAllByCancion(idCancion);

        if (reproducciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reproducciones);
    }



    @Operation(
            summary = "Listar reproducciónes por usuario",
            description = """
                          Retorna según el usuario indicada una lista de reproducciones registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Reproducción encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class)
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
                                          "mensaje": "idUsuario no puede ser menor a 1 ni nulo"
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
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReproduccionDTO>> findAllByUsuario(
            @Parameter(
                    name = "idUsuario",
                    description = "Identificador único del tipo de usuario",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idUsuario
    ) {
        List<ReproduccionDTO> reproducciones = reproduccionService.findAllByUsuario(idUsuario);

        if (reproducciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reproducciones);
    }




    @Operation(
            summary = "Listar reproducciones registradas entre fechas",
            description = """
                          Lista todas las reproducciones registradas dentro de un rango de fechas determinado.
                          Si no se registraron reproducciones en el rango indicado, la respuesta no contiene información.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Reproducciones encontradas en el rango de fechas indicado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ReproduccionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen reproducciones registradas en el rango de fechas indicado",
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
            )
    })
    @GetMapping("/between-dates")
    public ResponseEntity<List<ReproduccionDTO>> findAllBetweenDates(
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
        List<ReproduccionDTO> reproducciones = reproduccionService.findAllBetweenDates(fechaMin, fechaMax);

        if (reproducciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reproducciones);
    }



    @Operation(
            summary = "Registrar una reproducción",
            description = """
                          Permite registrar una nueva reproducción en el sistema.
                          La operación retorna la reproducción registrada junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Reproducción registrada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class),
                            examples = @ExampleObject(
                                    name = "Reproducción registrada",
                                    value = """
                                            {
                                                "id": 1,
                                                "fechaReproduccion": "15/01/2024",
                                                "tiempoEscuchado": "3:00",
                                                "dispositivo": "Móvil",
                                                "usuario": "sabrina@artist.com",
                                                "cancion": {
                                                  "id": 1,
                                                  "autor": "Queen",
                                                  "titulo": "Bohemian Rhapsody",
                                                  "duracion": "5:54",
                                                  "fechaLanzamiento": "31/10/1975",
                                                  "genero": "Rock",
                                                  "album": "Bohemian Rhapsody"
                                                }
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
    @PostMapping
    public ResponseEntity<Reproduccion> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la reproducción que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nueva reproducción",
                                    value = """
                                            {
                                                "id": 1,
                                                "fechaReproduccion": "15/01/2024",
                                                "tiempoEscuchado": "3:00",
                                                "dispositivo": "Móvil",
                                                "usuario": "sabrina@artist.com",
                                                "cancion": {
                                                  "id": 1,
                                                  "autor": "Queen",
                                                  "titulo": "Bohemian Rhapsody",
                                                  "duracion": "5:54",
                                                  "fechaLanzamiento": "31/10/1975",
                                                  "genero": "Rock",
                                                  "album": "Bohemian Rhapsody"
                                                }
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Reproduccion reproduccion
    ) {
        Reproduccion reproducionInstanciada = reproduccionService.save(reproduccion);
        return new ResponseEntity<>(reproducionInstanciada, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar una reproducción",
            description = """
                    Permite actualizar la información de una reproducción existente a partir de su identificador.
                    La operación retorna la reproducción actualizada cuando la actualización es exitosa.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Reproducción actualizada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class),
                            examples = @ExampleObject(
                                    name = "Reproducción actualizada",
                                    value = """
                                            {
                                                "id": 1,
                                                "fechaReproduccion": "15/01/2024",
                                                "tiempoEscuchado": "3:00",
                                                "dispositivo": "Móvil",
                                                "usuario": "sabrina@artist.com",
                                                "cancion": {
                                                  "id": 1,
                                                  "autor": "Queen",
                                                  "titulo": "Bohemian Rhapsody",
                                                  "duracion": "5:54",
                                                  "fechaLanzamiento": "31/10/1975",
                                                  "genero": "Rock",
                                                  "album": "Bohemian Rhapsody"
                                                }
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
                    description = "Reproducción no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Reproducción inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Reproducción no encontrada"
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
    @PutMapping("/{idReproduccion}")
    public ResponseEntity<Reproduccion> update(
            @Parameter(
                    name = "idReproduccion",
                    description = "Identificador único de la reproducción",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la reproducción que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nueva reproducción",
                                    value = """
                                            {
                                                "id": 1,
                                                "fechaReproduccion": "15/01/2024",
                                                "tiempoEscuchado": "3:00",
                                                "dispositivo": "Móvil",
                                                "usuario": "sabrina@artist.com",
                                                "cancion": {
                                                  "id": 1,
                                                  "autor": "Queen",
                                                  "titulo": "Bohemian Rhapsody",
                                                  "duracion": "5:54",
                                                  "fechaLanzamiento": "31/10/1975",
                                                  "genero": "Rock",
                                                  "album": "Bohemian Rhapsody"
                                                }
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Reproduccion r
    ) {
        Reproduccion reproduccion = reproduccionService.update(id, r);
        return ResponseEntity.ok(reproduccion);
    }



    @Operation(
            summary = "Eliminar una reproducción",
            description = """
                          Permite eliminar una reproducción del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Reproducción eliminada correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningúna reproducción en el sistema relacionada a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Reproducción inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Reproducción no encontrada"
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
    @DeleteMapping("/{idReproduccion}")
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "idReproduccion",
                    description = "Identificador único de la reproducción",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idReproduccion
    ) {
        reproduccionService.delete(idReproduccion);
        return ResponseEntity.noContent().build();
    }

}
