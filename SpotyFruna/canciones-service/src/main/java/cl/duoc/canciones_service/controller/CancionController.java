package cl.duoc.canciones_service.controller;

import cl.duoc.canciones_service.dto.AlbumCancionDTO;
import cl.duoc.canciones_service.dto.ErrorResponse;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.dto.CancionDTO;
import cl.duoc.canciones_service.service.CancionService;
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
@RequestMapping("/api/v1/canciones")
@Tag(name = "CANCIONES", description = "API PARA LA GESTIÓN DE CANCIONES")
public class CancionController {

    @Autowired
    private CancionService cancionService;


    @Operation(
            summary = "Listar canciones",
            description = """
                    Retorna la lista completa de canciones registrados en el sistema.
                    Si no existen registros, retorna una respuesta sin contenido.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Canciones encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CancionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen canciones registradas",
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
    public ResponseEntity<List<CancionDTO>> findAll() {
        List<CancionDTO> canciones = cancionService.findAll();

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }



    @Operation(
            summary = "Obtener canción por identificador",
            description = """
                    Retorna la información de un canción a partir de su identificador.
                    La operación responde exitosamente cuando el álbum es encontrado.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Canción encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CancionDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Canción no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Canción inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Canción no encontrada"
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
    @GetMapping( "/{idCancion}" )
    public ResponseEntity<CancionDTO> findById(
            @Parameter(
                    name = "idCancion",
                    description = "Identificador único de una canción",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idCancion
    ) {
        CancionDTO cancion = cancionService.findById(idCancion);

        return ResponseEntity.ok(cancion);
    }



    @Operation(
            summary = "Listar canciones por álbum",
            description = """
                          Retorna según el álbum indicado una lista de canciones registrados en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Canciones encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CancionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen canciones registradas",
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
    @GetMapping("/album/{idAlbum}")
    public ResponseEntity<List<AlbumCancionDTO>> findAllByIdAlbum(
            @Parameter(
                    name = "idAlbum",
                    description = "Identificador único de un álbum",
                    required = true,
                    example = "7"
            )
            @PathVariable Long idAlbum
    ) {
        List<AlbumCancionDTO> cancionesAlbum = cancionService.findAllByIdAlbum(idAlbum);

        if (cancionesAlbum.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cancionesAlbum);
    }



    @Operation(
            summary = "Listar canciones por autor",
            description = """
                    Retorna según el autor indicado una lista de canciones registrados en el sistema.
                    Si no existen registros, retorna una respuesta sin contenido.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Canciones encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CancionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen canciones registradas",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Autor no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Autor inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Autor no encontrado"
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
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<CancionDTO>> findAllByAutor(
            @Parameter(
                    name = "autor",
                    description = "Nombre de un autor/artista",
                    required = true,
                    example = "Daft Punk"
            )
            @PathVariable String autor
    ) {
        List<CancionDTO> canciones = cancionService.findAllByAutor(autor);

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }



    @Operation(
            summary = "Listar canciones por género",
            description = """
                    Retorna según el género indicado una lista de canciones registrados en el sistema.
                    Si no existen registros, retorna una respuesta sin contenido.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Canciones encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CancionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen canciones registradas",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Género no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Género inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Género no encontrado"
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
    @GetMapping("/genero/{idGenero}")
    public ResponseEntity<List<CancionDTO>> findAllByGenero(
            @Parameter(
                    name = "idGenero",
                    description = "Identificador único de género musical",
                    required = true,
                    example = "7"
            )
            @PathVariable Long idGenero
    ) {
        List<CancionDTO> canciones = cancionService.findAllByGenero(idGenero);

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }



    @Operation(
            summary = "Listar canciones entre fechas",
            description = """
                    Lista todas las canciones registradas dentro de un rango de fechas determinado.
                    Si no existen canciones en el rango indicado, la respuesta no contiene información.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Canciones encontradas en el rango de fechas indicado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CancionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen canciones registradas en el rango de fechas indicado",
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
    public ResponseEntity<List<CancionDTO>> findAllBetweenDates(
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
        List<CancionDTO> canciones = cancionService.findAllBetweenDates(fechaMin, fechaMax);

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }



    @Operation(
            summary = "Listar canciones entre duraciones",
            description = """
                    Lista todas las canciones registradas dentro de un rango de duración determinado.
                    Si no existen canciones en el rango indicado, la respuesta no contiene información.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Canciones encontradas en el rango de duración",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CancionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen canciones registradas en el rango de duración",
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
    @GetMapping("/between-durations")
    public ResponseEntity<List<CancionDTO>> findAllBetweenDuration(
            @Parameter(
                    name = "duracion-min",
                    description = "Duración mínima del rango de búsqueda (en segundos)",
                    required = true,
                    example = "60"
            )
            @RequestParam(name = "duracion-min") Long duracionMin,

            @Parameter(
                    name = "duracion-max",
                    description = "Duración máxima del rango de búsqueda (en segundos)",
                    required = true,
                    example = "600"
            )
            @RequestParam(name = "duracion-max") Long duracionMax) {
        List<CancionDTO> canciones = cancionService.findAllBetweenDuration(duracionMin, duracionMax);

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }



    @Operation(
            summary = "Crear una canción",
            description = """
                          Permite registrar una nueva canción en el sistema.
                          La operación retorna la canción creada junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Canción creada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Canción creada",
                                    value = """
                                            {
                                              "id": 1,
                                              "autor": "Queen",
                                              "titulo": "Bohemian Rhapsody",
                                              "duracion": "5:54",
                                              "fechaLanzamiento": "31/10/1975",
                                              "genero": "Rock",
                                              "album": "Bohemian Rhapsody"
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
                                              "autor": "Queen",
                                              "titulo": "Bohemian Rhapsody",
                                              "duracion": "5:54",
                                              "fechaLanzamiento": "31/10/1975",
                                              "genero": "Rock",
                                              "album": "Bohemian Rhapsody"
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Cancion> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la canción que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nueva canción",
                                    value = """
                                            {
                                              "id": 1,
                                              "autor": "Queen",
                                              "titulo": "Bohemian Rhapsody",
                                              "duracion": "5:54",
                                              "fechaLanzamiento": "31/10/1975",
                                              "genero": "Rock",
                                              "album": "Bohemian Rhapsody"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Cancion cancion
    ) {
        Cancion cancionInstanciada = cancionService.save(cancion);
        return new ResponseEntity<>(cancionInstanciada, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar una canción",
            description = """
                    Permite actualizar la información de una canción existente a partir de su identificador.
                    La operación retorna la canción actualizada cuando la actualización es exitosa.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Canción actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Canción actualizado",
                                    value = """
                                            {
                                              "id": 1,
                                              "autor": "Queen",
                                              "titulo": "Bohemian Rhapsody",
                                              "duracion": "5:54",
                                              "fechaLanzamiento": "31/10/1975",
                                              "genero": "Rock",
                                              "album": "Bohemian Rhapsody"
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
                    description = "Canción no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Canción inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Canción no encontrada"
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
    @PutMapping( "/{idCancion}" )
    public ResponseEntity<Cancion> update(
            @Parameter(
                    name = "idCancion",
                    description = "Identificador único de un canción",
                    required = true,
                    example = "1"
            )
            @Valid @PathVariable Long idCancion,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la canción que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nueva canción",
                                    value = """
                                            {
                                              "id": 1,
                                              "autor": "Queen",
                                              "titulo": "Bohemian Rhapsody",
                                              "duracion": "5:54",
                                              "fechaLanzamiento": "31/10/1975",
                                              "genero": "Rock",
                                              "album": "Bohemian Rhapsody"
                                            }
                                            """
                            )
                    )
            )
            @RequestBody Cancion cancion
    ) {
        Cancion cancionInstanciada = cancionService.update(idCancion, cancion);
        return ResponseEntity.ok(cancionInstanciada);
    }



    @Operation(
            summary = "Eliminar una canción",
            description = """
                          Permite eliminar una canción del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Canción eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ninguna canción en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Canción inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Canción no encontrada"
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
    @DeleteMapping( "/{idCancion}" )
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "idCancion",
                    description = "Identificador único de un canción",
                    required = true,
                    example = "1"
            )
            @PathVariable  Long idCancion
    ) {
        cancionService.deleteById(idCancion);
        return ResponseEntity.noContent().build();
    }

}
