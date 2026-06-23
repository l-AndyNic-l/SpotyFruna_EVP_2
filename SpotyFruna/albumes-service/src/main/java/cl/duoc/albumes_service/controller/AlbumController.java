package cl.duoc.albumes_service.controller;

import cl.duoc.albumes_service.dto.AlbumDTO;
import cl.duoc.albumes_service.dto.ErrorResponse;
import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.service.AlbumService;
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
@RequestMapping("/api/v1/albumes")
@Tag(name = "ÁLBUMES", description = "API PARA LA GESTIÓN DE ÁLBUMES")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Operation(
            summary = "Listar álbumes",
            description = """
                          Retorna la lista completa de álbumes registrados en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Álbumes encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = AlbumDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen álbumes registrados",
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
    public ResponseEntity<List<AlbumDTO>> findAll() {
        List<AlbumDTO> albums = albumService.findAll();

        if (albums.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(albums);
    }



    @Operation(
            summary = "Listar álbumes por tipo de álbum",
            description = """
                          Lista todos los álbumes asociados a un tipo de álbum específico.
                          Si no existen álbumes para el tipo indicado, la respuesta no contiene información.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Álbumes encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = AlbumDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen álbumes registrados para el tipo de álbum indicado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "El id del tipo de álbum es inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "idTipoAlbum inválido",
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
                    responseCode = "404",
                    description = "El tipo de álbum indicado no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Tipo de álbum no encontrado",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T03:52:10.120",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Tipo álbum no encontrado"
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
    @GetMapping("/tipo-album/{idTipoAlbum}")
    public ResponseEntity<List<AlbumDTO>> findAllByTipoAlbum(
            @Parameter(
                    name = "idTipoAlbum",
                    description = "Identificador único de un tipo de álbum",
                    required = true,
                    example = "2"
            )
            @PathVariable Long idTipoAlbum
    ) {
        List<AlbumDTO> albums = albumService.findAllByTipoAlbum(idTipoAlbum);

        if (albums.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(albums);
    }



    @Operation(
            summary = "Listar álbumes entre fechas",
            description = """
                          Lista todos los álbumes registrados dentro de un rango de fechas determinado.
                          Si no existen álbumes en el rango indicado, la respuesta no contiene información.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Álbumes encontrados en el rango de fechas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = AlbumDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen álbumes registrados en el rango de fechas indicado",
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
    public ResponseEntity<List<AlbumDTO>> findAllBetweenDates(
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
        List<AlbumDTO> albums = albumService.findAllBetweenDates(fechaMin, fechaMax);

        if (albums.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(albums);
    }



    @Operation(
            summary = "Listar álbumes por artista",
            description = """
                          Lista todos los álbumes asociados a un artista específico.
                          Si el artista no tiene álbumes registrados, la respuesta no contiene información.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Álbumes encontrados para el artista indicado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = AlbumDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen álbumes registrados para el artista indicado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "El id del artista es inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Id inválido",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:30:00",
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
                    description = "Artista no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Artista inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:31:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Artista no encontrado"
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
    @GetMapping("/artista/{idArtista}")
    public ResponseEntity<List<AlbumDTO>> findAllByArtist(
            @Parameter(
                    name = "idArtista",
                    description = "Identificador único de un artista",
                    required = true,
                    example = "16"
            )
            @PathVariable Long idArtista
    ) {
        List<AlbumDTO> albums = albumService.findAllByArtist(idArtista);

        if (albums.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(albums);
    }



    @Operation(
            summary = "Obtener álbum por identificador",
            description = """
                          Retorna la información de un álbum a partir de su identificador.
                          La operación responde exitosamente cuando el álbum es encontrado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Álbum encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Álbum no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Álbum inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Álbum no encontrado"
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
    @GetMapping("/{idAlbum}")
    public ResponseEntity<AlbumDTO> findById(
            @Parameter(
                    name = "idAlbum",
                    description = "Identificador único de un álbum",
                    required = true,
                    example = "7"
            )
            @PathVariable Long idAlbum
    ) {
        AlbumDTO album = albumService.findById(idAlbum);
        return ResponseEntity.ok(album);
    }



    @Operation(
            summary = "Crear un álbum",
            description = """
                          Permite registrar un nuevo álbum en el sistema.
                          La operación retorna el álbum creado junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Álbum creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class),
                            examples = @ExampleObject(
                                    name = "Álbum creado",
                                    value = """
                                              {
                                                "id": 9,
                                                "artista": "Adele",
                                                "nombre": "Hello",
                                                "descripcion": "Sencillo de Adele que conquistó las listas mundiales",
                                                "fechaLanzamiento": "23/10/2015",
                                                "tipoAlbum": "Sencillo",
                                                "canciones": [
                                                  {
                                                    "id": 8,
                                                    "titulo": "Hello",
                                                    "duracion": "4:55"
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
    public ResponseEntity<Album> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del álbum que se desean registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo álbum",
                                    value = """
                                            {
                                              "id": 9,
                                              "artista": "Adele",
                                              "nombre": "Hello",
                                              "descripcion": "Sencillo de Adele que conquistó las listas mundiales",
                                              "fechaLanzamiento": "23/10/2015",
                                              "tipoAlbum": "Sencillo",
                                              "canciones": [
                                                 {
                                                   "id": 8,
                                                   "titulo": "Hello",
                                                   "duracion": "4:55"
                                                 }
                                              ]
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Album album
    ) {
        Album albumInstanciado = albumService.save(album);
        return new ResponseEntity<>(albumInstanciado, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar un álbum",
            description = """
                          Permite actualizar la información de un álbum existente a partir de su identificador.
                          La operación retorna el álbum actualizado cuando la actualización es exitosa.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Álbum actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class),
                            examples = @ExampleObject(
                                    name = "Álbum actualizado",
                                    value = """
                                            {
                                              "id": 9,
                                              "artista": "Adele",
                                              "nombre": "Hello",
                                              "descripcion": "Sencillo de Adele que conquistó las listas mundiales",
                                              "fechaLanzamiento": "23/10/2015",
                                              "tipoAlbum": "Sencillo",
                                              "canciones": [
                                                 {
                                                   "id": 8,
                                                   "titulo": "Hello",
                                                   "duracion": "4:55"
                                                 }
                                              ]
                                            }
                                            """

                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Álbum no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Álbum inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Álbum no encontrado"
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
    @PutMapping("/{idAlbum}")
    public ResponseEntity<Album> update(
            @Parameter(
                    name = "idAlbum",
                    description = "Identificador único de un álbum",
                    required = true,
                    example = "7"
            )
            @PathVariable Long idAlbum,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del álbum que se desean registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class),
                            examples = @ExampleObject(
                                    name = "Álbum actualizado",
                                    value = """
                                            {
                                              "id": 9,
                                              "artista": "Adele",
                                              "nombre": "Hello",
                                              "descripcion": "Sencillo de Adele que conquistó las listas mundiales",
                                              "fechaLanzamiento": "23/10/2015",
                                              "tipoAlbum": "Sencillo",
                                              "canciones": [
                                                 {
                                                   "id": 8,
                                                   "titulo": "Hello",
                                                   "duracion": "4:55"
                                                 }
                                              ]
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Album album
    ) {
        Album albumInstanciado = albumService.update(idAlbum, album);
        return ResponseEntity.ok(albumInstanciado);
    }



    @Operation(
            summary = "Eliminar un álbum",
            description = """
                          Permite eliminar un álbum del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Álbum eliminado correctamente",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Álbum no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Álbum inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Álbum no encontrado"
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
    @DeleteMapping("/{idAlbum}")
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "idAlbum",
                    description = "Identificador único de un álbum",
                    required = true,
                    example = "7"
            )
            @PathVariable Long idAlbum
    ) {
        albumService.deleteById(idAlbum);
        return ResponseEntity.noContent().build();
    }

}
