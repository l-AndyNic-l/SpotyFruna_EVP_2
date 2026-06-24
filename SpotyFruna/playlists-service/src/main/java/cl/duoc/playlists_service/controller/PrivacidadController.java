package cl.duoc.playlists_service.controller;

import cl.duoc.playlists_service.dto.ErrorResponse;
import cl.duoc.playlists_service.dto.PlaylistDTO;
import cl.duoc.playlists_service.model.Privacidad;
import cl.duoc.playlists_service.service.PrivacidadService;
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
@RequestMapping( "/api/v1/privacidades_playlists" )
@Tag(name = "PRIVACIDADES", description = "API PARA LA GESTIÓN DE PRIVACIDADES")
public class PrivacidadController {

    @Autowired
    private PrivacidadService privacidadService;


    @Operation(
            summary = "Listar privacidades de playlist",
            description = """
                          Retorna la lista completa de privacidades de playlist en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Privacidades de playlist encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = PlaylistDTO.class)
                            ),
                            examples = @ExampleObject(
                                    name = "Privacidades de playlist",
                                    value = """
                                            [
                                              {
                                                "id": 2,
                                                "nombre": "Privada"
                                              },
                                              {
                                                "id": 1,
                                                "nombre": "Pública"
                                              }
                                            ]
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen tipos de usuario registrados",
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
    public ResponseEntity<List<Privacidad>> findAll() {
        List<Privacidad> privacidades = privacidadService.findAll();

        if (privacidades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(privacidades);
    }



    @Operation(
            summary = "Obtener una privacidad de playlist por nombre",
            description = """
                          Retorna la información de una privacidad de playlist a partir de su nombre.
                          La operación responde exitosamente cuando una privacidad de playlist es encontrada.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Privacidad de playlist encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlaylistDTO.class),
                            examples = @ExampleObject(
                                    name = "Privacidad de playlist",
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Privada"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ninguna privacidad de playlist en el sistema relacionado al nombre ingresado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Privacidad de playlist inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Privacidad de playlist no encontrado"
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
    @GetMapping( "/{idPrivacidad}" )
    public ResponseEntity<Privacidad> findById(
            @Parameter(
                    name = "idPrivacidad",
                    description = "Identificador único de la privacidad",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idPrivacidad
    ) {
        Privacidad privacidad = privacidadService.findById(idPrivacidad);

        return ResponseEntity.ok(privacidad);
    }



    @Operation(
            summary = "Crear una privacidad de playlist",
            description = """
                          Permite registrar una privacidad de playlist en el sistema.
                          La operación retorna la privacidad de playlist creada junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Privacidad de playlist creada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlaylistDTO.class),
                            examples = @ExampleObject(
                                    name = "Privacidad de playlist creada",
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Privada"
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
                    description = "Privacidad de playlist ya existente",
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
                                              "mensaje": "Ya existe una privacidad de playlist con ese nombre"
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
    public ResponseEntity<Privacidad> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la privacidad de playlist que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlaylistDTO.class),
                            examples = @ExampleObject(
                                    name = "Nueva privacidad de playlist",
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Privada"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Privacidad privacidad
    ) {
        Privacidad privacidadInstanciada = privacidadService.save(privacidad);
        return new ResponseEntity<>(privacidadInstanciada, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar una privacidad de playlist",
            description = """
                    Permite actualizar la información de una privacidad de playlist existente a partir de su identificador.
                    La operación retorna la privacidad de playlist actualizada cuando la actualización es exitosa.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Privacidad de playlist actualizada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlaylistDTO.class),
                            examples = @ExampleObject(
                                    name = "Privacidad de playlist actualizada",
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Privada"
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
                    description = "Privacidad de playlist no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Privacidad de playlist inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Privacidad de playlist no encontrada"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Privacidad de playlist ya existente",
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
                                              "mensaje": "Ya existe una privacidad de playlist con ese nombre"
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
    @PutMapping( "/{idPrivacidad}" )
    public ResponseEntity<Privacidad> update(
            @Parameter(
                    name = "idPrivacidad",
                    description = "Identificador único de la privacidad",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idPrivacidad,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la privacidad de playlist que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlaylistDTO.class),
                            examples = @ExampleObject(
                                    name = "Nueva privacidad de playlist",
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Privada"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Privacidad privacidad
    ) {
        Privacidad privacidadInstanciada = privacidadService.update(idPrivacidad, privacidad);
        return ResponseEntity.ok(privacidadInstanciada);
    }



    @Operation(
            summary = "Eliminar una privacidad de playlist",
            description = """
                          Permite eliminar una privacidad de playlist del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Privacidad de playlist eliminada correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ninguna privacidad de playlist en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Privacidad de playlist inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Privacidad de playlist no encontrada"
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
    @DeleteMapping("/{idPrivacidad}")
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "idPrivacidad",
                    description = "Identificador único de la privacidad",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idPrivacidad
    ) {
        privacidadService.deleteById(idPrivacidad);
        return ResponseEntity.noContent().build();
    }

}
