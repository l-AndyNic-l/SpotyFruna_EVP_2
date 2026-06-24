package cl.duoc.usuarios_service.controller;

import cl.duoc.usuarios_service.dto.ErrorResponse;
import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.model.TipoUsuario;
import cl.duoc.usuarios_service.service.TipoUsuarioService;
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
@RequestMapping( "/api/v1/tipos_usuarios" )
@Tag(name = "TIPOS DE USUARIO", description = "API PARA LA GESTIÓN DE TIPOS DE USUARIO")
public class TipoUsuarioController {

    @Autowired
    TipoUsuarioService tipoUsuarioService;


    @Operation(
            summary = "Listar tipos de usuario",
            description = """
                          Retorna la lista completa de tipos de usuario registrados en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipos de usuario encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = UsuarioDTO.class)
                            ),
                            examples = @ExampleObject(
                                    name = "Tipo de usuario",
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Administrador"
                                            }
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
    public ResponseEntity<List<TipoUsuario>> findAll() {
        List<TipoUsuario> tiposUsuario = tipoUsuarioService.findAll();

        if (tiposUsuario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tiposUsuario);
    }



    @Operation(
            summary = "Obtener un tipo de usuario por nombre",
            description = """
                          Retorna la información de un tipo de usuario a partir de su nombre.
                          La operación responde exitosamente cuando el tipo de usuario es encontrado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipo de usuario encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    name = "Tipo de usuario",
                                    value = """
                                            {
                                              "id": 2,
                                              "nombre": "Soporte"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Nombre del tipo de usuario ingresado inválido",
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
                                              "mensaje": "Nombre no puede ser nulo ni vacío"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningun tipo de usuario en el sistema relacionado al nombre ingresado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Tipo de usuario inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Tipo de usuario no encontrado"
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
    @GetMapping( "/{tipo}" )
    public ResponseEntity<TipoUsuario> findOne(
            @Parameter(
                    name = "nombreTipoUsuario",
                    description = "Nombre del tipo de usuario",
                    required = true,
                    example = "Artista"
            )
            @PathVariable String tipo
    ) {
        TipoUsuario tipoUsuario = tipoUsuarioService.findByNombre(tipo);

        return ResponseEntity.ok(tipoUsuario);
    }



    @Operation(
            summary = "Crear un tipo de usuario",
            description = """
                          Permite registrar un nuevo tipo de usuario en el sistema.
                          La operación retorna el tipo de usuario creado junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Tipo de usuario creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    name = "Tipo de usuario creado",
                                    value = """
                                            {
                                              "id": 4,
                                              "nombre": "Artista"
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
                    description = "Tipo de usuario ya existente",
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
                                              "mensaje": "Ya existe un tipo de usuario con ese nombre"
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
    public ResponseEntity<TipoUsuario> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del tipo de usuario que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo tipo de usuario",
                                    value = """
                                            {
                                              "id": 4,
                                              "nombre": "Autor"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody TipoUsuario tipoUsuario
    ) {
        TipoUsuario tipoUsuarioInstanciado = tipoUsuarioService.save(tipoUsuario);
        return new ResponseEntity<>(tipoUsuarioInstanciado, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar un tipo de usuario",
            description = """
                    Permite actualizar la información de un tipo de usuario existente a partir de su identificador.
                    La operación retorna el tipo de usuario actualizado cuando la actualización es exitosa.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipo de usuario actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    name = "Tipo de usuario actualizado",
                                    value = """
                                            {
                                              "id": 4,
                                              "nombre": "Autor"
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
                    description = "Tipo de usuario no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Tipo de usuario inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Tipo de usuario no encontrado"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Tipo de usuario ya existente",
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
                                              "mensaje": "Ya existe un tipo de usuario con ese nombre"
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
    @PutMapping( "/{idTipoUsuario}" )
    public ResponseEntity<TipoUsuario> update(
            @Parameter(
                    name = "idTipoUsuario",
                    description = "Identificador único del tipo de usuario",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idTipoUsuario,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del tipo de usuario que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo tipo de usuario",
                                    value = """
                                            {
                                              "id": 4,
                                              "nombre": "Autor"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody TipoUsuario tipoUsuario
    ) {
        TipoUsuario tipoUsuarioInstanciado = tipoUsuarioService.update(idTipoUsuario, tipoUsuario);
        return ResponseEntity.ok(tipoUsuarioInstanciado);
    }



    @Operation(
            summary = "Eliminar un tipo de usuario",
            description = """
                          Permite eliminar un tipo de usuario del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Tipo de usuario eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningun tipo de usuario en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Tipo de usuario inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Tipo de usuario no encontrado"
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
    @DeleteMapping( "/{idTipoUsuario}" )
    public ResponseEntity<Void> deleteById(
            @Parameter(
                    name = "idTipoUsuario",
                    description = "Identificador único del tipo de usuario",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idTipoUsuario
    ){
        tipoUsuarioService.deleteById(idTipoUsuario);
        return ResponseEntity.noContent().build();
    }

}
