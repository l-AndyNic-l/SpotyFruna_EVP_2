package cl.duoc.playlists_service.controller;

import cl.duoc.playlists_service.dto.ErrorResponse;
import cl.duoc.playlists_service.dto.PlaylistDTO;
import cl.duoc.playlists_service.model.Playlist;
import cl.duoc.playlists_service.service.PlaylistService;
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
@RequestMapping( "/api/v1/playlists" )
@Tag(name = "PLAYLISTS", description = "API PARA LA GESTIÓN DE PLAYLISTS")
public class PlaylistsController {

    @Autowired
    private PlaylistService playlistService;


    @Operation(
            summary = "Listar playlists",
            description = """
                          Retorna la lista completa de playlists registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Playlists encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = PlaylistDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen playlists registrados",
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
    public ResponseEntity<List<PlaylistDTO>> findAll() {
        List<PlaylistDTO> playlistsDTO = playlistService.findAll();

        if (playlistsDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playlistsDTO);
    }



    @Operation(
            summary = "Listar playlists por usuario",
            description = """
                          Retorna según el usuario indicado una lista de playlists registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Playlists encontradas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = PlaylistDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen playlists registradas",
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
                                              "mensaje": "idPrivacidad no puede ser nulo ni menor de 1"
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
    @GetMapping("/usuario")
    public ResponseEntity<List<PlaylistDTO>> findAllByUsuario(
            @Parameter(
                    name = "idUsuario",
                    description = "Identificador único del usuario",
                    required = true,
                    example = "1"
            )
            @RequestParam(name = "id-usuario") Long idUsuario
    ) {
        List<PlaylistDTO> playlistsDTO = playlistService.findAllByUsuario(idUsuario);

        if (playlistsDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playlistsDTO);
    }



    @Operation(
            summary = "Listar playlists por privacidad",
            description = """
                          Retorna según la privacidad indicado una lista de playlists registradas en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Playlists encontradas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = PlaylistDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen playlists registradas",
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
                                              "mensaje": "idPrivacidad no puede ser nulo ni menor de 1"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Privacidad no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Privacidad inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Privacidad no encontrada"
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
    @GetMapping("/privacidad")
    public ResponseEntity<List<PlaylistDTO>> findAllByPrivacidad(
            @Parameter(
                    name = "idPrivacidad",
                    description = "Identificador único de la privacidad de la playlist",
                    required = true,
                    example = "1"
            )
            @RequestParam(name = "id-privacidad") Long idPrivacidad
    ) {
        List<PlaylistDTO> playlistsDTO = playlistService.findAllByPrivacidad(idPrivacidad);

        if (playlistsDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playlistsDTO);
    }



    @Operation(
            summary = "Listar playlists registradas entre fechas",
            description = """
                          Lista todas las playlists registradas dentro de un rango de fechas determinado.
                          Si no se registraron usuarios en el rango indicado, la respuesta no contiene información.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Playlists encontradas en el rango de fechas indicado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = PlaylistDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen playlists registradas en el rango de fechas indicado",
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
    public ResponseEntity<List<PlaylistDTO>> findAllBetweenDates(
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
        List<PlaylistDTO> playlistsDTO = playlistService.findAllBetweenDates(fechaMin, fechaMax);

        if (playlistsDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playlistsDTO);
    }



    @Operation(
            summary = "Obtener playlist por identificador",
            description = """
                    Retorna la información de una playlist a partir de su identificador.
                    La operación responde exitosamente cuando la playlist es encontrada.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Playlist encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlaylistDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Playlist no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Playlist inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Playlist no encontrada"
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
    @GetMapping( "/{idPlaylist}" )
    public ResponseEntity<PlaylistDTO> findById(
            @Parameter(
                    name = "idPlaylist",
                    description = "Identificador único de la playlist",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idPlaylist
    ) {
        PlaylistDTO playlist = playlistService.findById(idPlaylist);

        return ResponseEntity.ok(playlist);
    }



    @Operation(
            summary = "Crear una playlist",
            description = """
                          Permite crear una nueva playlist en el sistema.
                          La operación retorna la playlist creada junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Playlist creada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlaylistDTO.class),
                            examples = @ExampleObject(
                                    name = "Playlist creada",
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Rock Clásico",
                                                "descripcion": "Los mejores temas de rock de los 70s y 80s",
                                                "fechaCreacion": "15/01/2024",
                                                "privacidad": "Pública",
                                                "usuario": "sabrina@artist.com",
                                                "canciones": [
                                                  {
                                                    "id": 1,
                                                    "autor": "Queen",
                                                    "titulo": "Bohemian Rhapsody",
                                                    "duracion": "5:54",
                                                    "fechaLanzamiento": "31/10/1975",
                                                    "genero": "Rock",
                                                    "album": "Bohemian Rhapsody"
                                                  },
                                                  {
                                                    "id": 6,
                                                    "autor": "Michael Jackson",
                                                    "titulo": "Billie Jean",
                                                    "duracion": "4:54",
                                                    "fechaLanzamiento": "30/11/1982",
                                                    "genero": "Pop",
                                                    "album": "Thriller"
                                                  }
                                                ]
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
    public ResponseEntity<Playlist> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la playlist que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlaylistDTO.class),
                            examples = @ExampleObject(
                                    name = "Nueva playlist",
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Rock Clásico",
                                                "descripcion": "Los mejores temas de rock de los 70s y 80s",
                                                "fechaCreacion": "15/01/2024",
                                                "privacidad": "Pública",
                                                "usuario": "sabrina@artist.com",
                                                "canciones": [
                                                  {
                                                    "id": 1,
                                                    "autor": "Queen",
                                                    "titulo": "Bohemian Rhapsody",
                                                    "duracion": "5:54",
                                                    "fechaLanzamiento": "31/10/1975",
                                                    "genero": "Rock",
                                                    "album": "Bohemian Rhapsody"
                                                  },
                                                  {
                                                    "id": 6,
                                                    "autor": "Michael Jackson",
                                                    "titulo": "Billie Jean",
                                                    "duracion": "4:54",
                                                    "fechaLanzamiento": "30/11/1982",
                                                    "genero": "Pop",
                                                    "album": "Thriller"
                                                  }
                                                ]
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Playlist playlist
    ) {
        Playlist playlistInstanciada = playlistService.save(playlist);
        return new ResponseEntity<>(playlistInstanciada, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar una playlist",
            description = """
                    Permite actualizar la información de una playlist existente a partir de su identificador.
                    La operación retorna la playlist actualizado cuando la actualización es exitosa.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Playlist actualizada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlaylistDTO.class),
                            examples = @ExampleObject(
                                    name = "Playlist actualizada",
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Rock Clásico",
                                                "descripcion": "Los mejores temas de rock de los 70s y 80s",
                                                "fechaCreacion": "15/01/2024",
                                                "privacidad": "Pública",
                                                "usuario": "sabrina@artist.com",
                                                "canciones": [
                                                  {
                                                    "id": 1,
                                                    "autor": "Queen",
                                                    "titulo": "Bohemian Rhapsody",
                                                    "duracion": "5:54",
                                                    "fechaLanzamiento": "31/10/1975",
                                                    "genero": "Rock",
                                                    "album": "Bohemian Rhapsody"
                                                  },
                                                  {
                                                    "id": 6,
                                                    "autor": "Michael Jackson",
                                                    "titulo": "Billie Jean",
                                                    "duracion": "4:54",
                                                    "fechaLanzamiento": "30/11/1982",
                                                    "genero": "Pop",
                                                    "album": "Thriller"
                                                  }
                                                ]
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
                    description = "Playlist no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Playlist inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Playlist no encontrada"
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
    @PutMapping( "/{idPlaylist}" )
    public ResponseEntity<Playlist> update(
            @Parameter(
                    name = "idPlaylist",
                    description = "Identificador único de la playlist",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idPlaylist,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la playlist que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlaylistDTO.class),
                            examples = @ExampleObject(
                                    name = "Nueva playlist",
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Rock Clásico",
                                                "descripcion": "Los mejores temas de rock de los 70s y 80s",
                                                "fechaCreacion": "15/01/2024",
                                                "privacidad": "Pública",
                                                "usuario": "sabrina@artist.com",
                                                "canciones": [
                                                  {
                                                    "id": 1,
                                                    "autor": "Queen",
                                                    "titulo": "Bohemian Rhapsody",
                                                    "duracion": "5:54",
                                                    "fechaLanzamiento": "31/10/1975",
                                                    "genero": "Rock",
                                                    "album": "Bohemian Rhapsody"
                                                  },
                                                  {
                                                    "id": 6,
                                                    "autor": "Michael Jackson",
                                                    "titulo": "Billie Jean",
                                                    "duracion": "4:54",
                                                    "fechaLanzamiento": "30/11/1982",
                                                    "genero": "Pop",
                                                    "album": "Thriller"
                                                  }
                                                ]
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Playlist playlist) {
        Playlist playlistInstanciada = playlistService.update(idPlaylist, playlist);
        return ResponseEntity.ok(playlistInstanciada);
    }



    @Operation(
            summary = "Eliminar una playlist",
            description = """
                          Permite eliminar una playlist del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Playlist eliminada correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningúna playlist en el sistema relacionada a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Playlist inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Playlist no encontrada"
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
    @DeleteMapping("/{idPlaylist}")
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "idPlaylist",
                    description = "Identificador único de la playlist",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idPlaylist) {
        playlistService.deleteById(idPlaylist);
        return ResponseEntity.noContent().build();
    }


}
