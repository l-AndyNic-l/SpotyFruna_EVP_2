package cl.duoc.playlists_service.controller;

import cl.duoc.playlists_service.dto.ErrorResponse;
import cl.duoc.playlists_service.dto.GuardarCancionDTO;
import cl.duoc.playlists_service.model.GuardarCancion;
import cl.duoc.playlists_service.service.GuardarCancionService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/guardar_canciones_playlists")
@Tag(name = "GUARDADO DE CANCIONES", description = "API PARA LA GESTIÓN DE GUARDADO DE CANCIONES")
public class GuardarCancionController {

    @Autowired
    private GuardarCancionService guardarCancionService;


    @Operation(
            summary = "Listar guardado de canciones",
            description = """
                          Retorna la lista completa de guardados de canciones registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Guardado de canciones encontradas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = GuardarCancionDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen guardado de canciones registradas",
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
    public ResponseEntity<List<GuardarCancionDTO>> findAll() {
        List<GuardarCancionDTO> cancionesGuardadas = guardarCancionService.findAll();

        if (cancionesGuardadas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cancionesGuardadas);
    }



    @Operation(
            summary = "Obtener guardado de canciones por identificador",
            description = """
                          Retorna la información de un guardado de canciones a partir de su identificador.
                          La operación responde exitosamente cuando el guardado de canciones es encontrado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Guardado de canciones encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GuardarCancionDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Guardado de canciones no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Guardado de canciones inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Guardado de canciones no encontrada"
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
    @GetMapping( "/{idGuardarCancion}" )
    public ResponseEntity<GuardarCancionDTO> findById(
            @Parameter(
                    name = "idGuardarCancion",
                    description = "Identificador único del guardado de la canción",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idGuardarCancion
    ) {
        GuardarCancionDTO cancionGuardada = guardarCancionService.findById(idGuardarCancion);

        return ResponseEntity.ok(cancionGuardada);
    }



    @Operation(
            summary = "Registrar un guardado de canción",
            description = """
                          Permite registrar un nuevo guardado de canción en el sistema.
                          La operación retorna el guardado de canción junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Guardado de canción registrada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GuardarCancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Guardado de canción registrada",
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
    public ResponseEntity<GuardarCancion> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del guardado de canción que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GuardarCancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo guardado de canción",
                                    value = """
                                            {
                                                "id": 1,
                                                "usuario": "sabrina@artist.com",
                                                "playlist": "Rock Clásico",
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
            @Valid @RequestBody GuardarCancion guardarCancion
    ) {
        GuardarCancion cancionGuardada = guardarCancionService.save(guardarCancion);
        return new ResponseEntity<>(cancionGuardada, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar un guardado de canción",
            description = """
                          Permite actualizar la información de un guardado de canción existente a partir de su identificador.
                          La operación retorna el guardado de canción actualizada cuando la actualización es exitosa.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Guardado de canción actualizada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GuardarCancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Guardado de canción actualizada",
                                    value = """
                                            {
                                                "id": 1,
                                                "usuario": "sabrina@artist.com",
                                                "playlist": "Rock Clásico",
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
    @PutMapping( "/{idGuardarCancion}" )
    public ResponseEntity<GuardarCancion> update(
            @Parameter(
                    name = "idGuardarCancion",
                    description = "Identificador único del guardado de la canción",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idGuardarCancion,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del guardado de canción que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GuardarCancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo guardado de canción",
                                    value = """
                                            {
                                                "id": 1,
                                                "usuario": "sabrina@artist.com",
                                                "playlist": "Rock Clásico",
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
            @Valid @RequestBody GuardarCancion guardarCancion
    ) {
        GuardarCancion cancionGuardada = guardarCancionService.update(idGuardarCancion, guardarCancion);
        return ResponseEntity.ok(cancionGuardada);
    }



    @Operation(
            summary = "Eliminar un guardado de canción",
            description = """
                          Permite eliminar un guardado de canción del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Guardado de canción eliminada correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningún guardado de canción en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Guardado de canción inexistente",
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
    @DeleteMapping("/{idGuardarCancion}")
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "idGuardarCancion",
                    description = "Identificador único del guardado de la canción",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idGuardarCancion
    ) {
        guardarCancionService.deleteById(idGuardarCancion);
        return ResponseEntity.noContent().build();
    }
}
