package cl.duoc.canciones_service.controller;

import cl.duoc.canciones_service.dto.CancionDTO;
import cl.duoc.canciones_service.dto.ErrorResponse;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.model.Genero;
import cl.duoc.canciones_service.service.GeneroService;
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
@RequestMapping("/api/v1/generos_canciones")
@Tag(name = "GÉNEROS", description = "API PARA LA GESTIÓN DE GÉNEROS")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @Operation(
            summary = "Listar géneros",
            description = """
                          Retorna la lista completa de tipos de géneros registrados en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Géneros encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CancionDTO.class)
                            ),
                            examples = @ExampleObject(
                                    name = "Género",
                                    value = """
                                            {
                                              "id": "3",
                                              "nombre": "Rock"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen géneros registrados",
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
    public ResponseEntity<List<Genero>> findAll() {
        List<Genero> generos = generoService.findAll();

        if (generos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(generos);
    }



    @Operation(
            summary = "Obtener un género por identificador",
            description = """
                          Retorna la información de un género a partir de su identificador.
                          La operación responde exitosamente cuando el género es encontrado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Género encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Género",
                                    value = """
                                            {
                                              "id": "3",
                                              "nombre": "Rock"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningun género en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Género inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
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
            )
    })
    @GetMapping("/{idGenero}")
    public ResponseEntity<Genero> findById(
            @Parameter(
                    name = "idGenero",
                    description = "Identificador único de un género",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idGenero
    ) {
        Genero genero = generoService.findById(idGenero);

        return ResponseEntity.ok(genero);
    }



    @Operation(
            summary = "Crear un género",
            description = """
                          Permite registrar un nuevo género en el sistema.
                          La operación retorna el género creado junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Género creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Género creado",
                                    value = """
                                            {
                                              "id": "3",
                                              "nombre": "Rock"
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
                    description = "Género ya existente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Datos ingresados repetidos",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": "409",
                                              "error": "CONFLICT",
                                              "mensaje": "Ya existe ese género"
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
    public ResponseEntity<Genero> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del género que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo género",
                                    value = """
                                            {
                                              "id": 3,
                                              "nombre": "Rock"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Genero genero
    ) {
        Genero generoInstanciado = generoService.save(genero);
        return new ResponseEntity<>(generoInstanciado, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar un género",
            description = """
                    Permite actualizar la información de un género existente a partir de su identificador.
                    La operación retorna el género actualizado cuando la actualización es exitosa.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Género actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Género actualizado",
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
                    responseCode = "409",
                    description = "Género ya existente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Datos ingresados repetidos",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": "409",
                                              "error": "CONFLICT",
                                              "mensaje": "Ya existe ese género"
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
    @PutMapping("/{idGenero}")
    public ResponseEntity<Genero> update(
            @Parameter(
                    name = "idGenero",
                    description = "Identificador único de un género",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idGenero,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del género que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CancionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo género",
                                    value = """
                                            {
                                              "id": 3,
                                              "nombre": "Rock"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Genero genero
    ) {
        Genero generoInstanciado = generoService.update(idGenero, genero);
        return ResponseEntity.ok(generoInstanciado);
    }



    @Operation(
            summary = "Eliminar un género",
            description = """
                          Permite eliminar un género del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Género eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningun género en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Género inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
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
            )
    })
    @DeleteMapping("/{idGenero}")
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "idGenero",
                    description = "Identificador único de un género",
                    required = true,
                    example = "1"
            )
            @PathVariable  Long idGenero
    ) {
        generoService.deleteById(idGenero);
        return ResponseEntity.noContent().build();
    }

}
