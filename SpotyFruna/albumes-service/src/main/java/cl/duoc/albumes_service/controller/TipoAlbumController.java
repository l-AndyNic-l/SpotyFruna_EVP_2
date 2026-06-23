package cl.duoc.albumes_service.controller;

import cl.duoc.albumes_service.dto.AlbumDTO;
import cl.duoc.albumes_service.dto.ErrorResponse;
import cl.duoc.albumes_service.model.TipoAlbum;
import cl.duoc.albumes_service.service.TipoAlbumService;
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
@RequestMapping("/api/v1/tipos_albumes")
@Tag(name = "TIPO DE ÁLBUMES", description = "API PARA LA GESTIÓN DE TIPO DE ÁLBUMES")
public class TipoAlbumController {

    @Autowired
    private TipoAlbumService tipoAlbumService;

    @Operation(
            summary = "Listar tipos de álbumes",
            description = """
                          Retorna la lista completa de tipos de álbumes registrados en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipos de álbumes encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = AlbumDTO.class)
                            ),
                            examples = @ExampleObject(
                                    name = "Tipo de álbum",
                                    value = """
                                            {
                                                "id": 3,
                                                "nombre": "Sencillo"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen tipos de álbumes registrados",
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
    public ResponseEntity<List<TipoAlbum>> findAll() {
        List<TipoAlbum> tiposAlbum = tipoAlbumService.findAll();

        if (tiposAlbum.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tiposAlbum);
    }



    @Operation(
            summary = "Obtener tipo de álbum por identificador",
            description = """
                          Retorna la información de un tipo de álbum a partir de su identificador.
                          La operación responde exitosamente cuando el tipo de álbum es encontrado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipo de álbum encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class),
                            examples = @ExampleObject(
                                    name = "Tipo de álbum",
                                    value = """
                                            {
                                                "id": 3,
                                                "nombre": "Sencillo"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningun tipo de álbum en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Tipo de álbum inexistente",
                                    value = """
                                            {
                                                "fecha": "2026-06-21T04:05:10.000",
                                                "codigo": 404,
                                                "error": "NOT_FOUND",
                                                "mensaje": "Tipo de álbum no encontrado"
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
    public ResponseEntity<TipoAlbum> findById(
            @Parameter(
                    name = "idTipoAlbum",
                    description = "Identificador único de un género",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id
    ) {
        TipoAlbum tipoAlbum = tipoAlbumService.findById(id);
        return ResponseEntity.ok(tipoAlbum);
    }



    @Operation(
            summary = "Crear un tipo de álbum",
            description = """
                          Permite registrar un nuevo tipo de álbum en el sistema.
                          La operación retorna el tipo de álbum creado junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Tipo de álbum creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class),
                            examples = @ExampleObject(
                                    name = "Tipo de álbum creado",
                                    value = """
                                            {
                                                "id": 3,
                                                "nombre": "Sencillo"
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
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 400,
                                              "error": "BAD_REQUEST",
                                              "mensaje": "La solicitud contiene datos inválidos"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Tipo de álbum ya existente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Datos ingresados repetidos",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 409,
                                              "error": "CONFLICT",
                                              "mensaje": "Ya existe ese tipo de álbum"
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
    public ResponseEntity<TipoAlbum> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del tipo de álbum que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo tipo de álbum",
                                    value = """
                                            {
                                              "id": 3,
                                              "nombre": "Sencillo",
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody TipoAlbum a
    ) {
        TipoAlbum tipoAlbum = tipoAlbumService.save(a);
        return new ResponseEntity<>(tipoAlbum, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar un tipo de álbum",
            description = """
                          Permite actualizar la información de un tipo de álbum existente a partir de su identificador.
                          La operación retorna el tipo de álbum actualizado cuando la actualización es exitosa.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipo de álbum actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class),
                            examples = @ExampleObject(
                                    name = "Tipo de álbum actualizado",
                                    value = """
                                    {
                                        "id": 3,
                                        "nombre": "Sencillo"
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
                                              "fecha": "2026-06-21T04:05:10.000",
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
                    description = "No se encontró ningun tipo de álbum en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Tipo de álbum inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 400,
                                              "error": "BAD_REQUEST",
                                              "mensaje": "Tipo de álbum no encontrado"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Tipo de álbum ya existente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Datos ingresados repetidos",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 409,
                                              "error": "CONFLICT",
                                              "mensaje": "Ya existe ese tipo de álbum"
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
    public ResponseEntity<TipoAlbum> update(
            @Parameter(
                    name = "idTipoAlbum",
                    description = "Identificador único de un género",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del tipo de álbum que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo tipo de álbum",
                                    value = """
                                            {
                                              "id": 3,
                                              "nombre": "Sencillo",
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody TipoAlbum ta
    ) {
        TipoAlbum tipoAlbum = tipoAlbumService.update(id, ta);
        return ResponseEntity.ok(tipoAlbum);
    }



    @Operation(
            summary = "Eliminar un tipo de álbum",
            description = """
                          Permite eliminar un tipo de álbum del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Tipo de álbum eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningun tipo de álbum en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Tipo de álbum inexistente",
                                    value = """
                                            {
                                                "fecha": "2026-06-21T04:05:10.000",
                                                "codigo": 404,
                                                "error": "NOT_FOUND",
                                                "mensaje": "Tipo de álbum no encontrado"
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
                    name = "idTipoAlbum",
                    description = "Identificador único de un género",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id
    ) {
        tipoAlbumService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

