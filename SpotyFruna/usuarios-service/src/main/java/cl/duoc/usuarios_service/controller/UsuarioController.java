package cl.duoc.usuarios_service.controller;

import cl.duoc.usuarios_service.dto.ErrorResponse;
import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.service.UsuarioService;
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
@RequestMapping( "/api/v1/usuarios" )
@Tag(name = "USUARIOS", description = "API PARA LA GESTIÓN DE USUARIOS")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @Operation(
            summary = "Listar usuarios",
            description = """
                    Retorna la lista completa de usuarios registrados en el sistema.
                    Si no existen registros, retorna una respuesta sin contenido.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuarios encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = UsuarioDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen usuarios registrados",
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
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }



    @Operation(
            summary = "Listar usuarios por tipo de usuario",
            description = """
                          Retorna según el tipo de usuario indicado una lista de usuarios registrados en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuarios encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = UsuarioDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen usuarios registrados",
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
                                              "mensaje": "idTipoUsuario no puede ser menor a 1 ni nulo"
                                            }
                                            """
                            )
                    )
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
    @GetMapping("/tipo-usuario/{idTipoUsuario}")
    public ResponseEntity<List<UsuarioDTO>> findAllByTipoUsuario(
            @Parameter(
                    name = "idTipoUsuario",
                    description = "Identificador único del tipo de usuario",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idTipoUsuario
    ) {
        List<UsuarioDTO> usuarios = usuarioService.findAllByTipoUsuario(idTipoUsuario);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }



    @Operation(
            summary = "Listar usuarios por nickname",
            description = """
                          Retorna según el nickname indicado una lista de usuarios registrados en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuarios encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = UsuarioDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen usuarios registrados",
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
                                              "mensaje": "Nickname no puede ser nulo o vacío"
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
    @GetMapping("/nickname")
    public ResponseEntity<List<UsuarioDTO>> findAllByNickname(
            @Parameter(
                    name = "nickname",
                    description = "Apodo/Alias asociado a un usuario",
                    required = true,
                    example = "tumorenito29"
            )
            @RequestParam(name = "nickname") String nickname
    ) {
        List<UsuarioDTO> usuarios = usuarioService.findAllByNickname(nickname);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }



    @Operation(
            summary = "Listar usuarios registrados entre fechas",
            description = """
                    Lista todas las usuarios registrados dentro de un rango de fechas determinado.
                    Si no se registraron usuarios en el rango indicado, la respuesta no contiene información.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuarios encontrados en el rango de fechas indicado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = UsuarioDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen usuarios registrados en el rango de fechas indicado",
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
    ResponseEntity<List<UsuarioDTO>> findAllBetweenDates(
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
        List<UsuarioDTO> usuarios = usuarioService.findAllBetweenDates(fechaMin, fechaMax);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }



    @Operation(
            summary = "Obtener usuario por identificador",
            description = """
                    Retorna la información de un usuario a partir de su identificador.
                    La operación responde exitosamente cuando el usuario es encontrado.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class)
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
                                          "mensaje": "idTipoUsuario no puede ser menor a 1 ni nulo"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Usuario inexistente",
                                    value = """
                                {
                                  "fecha": "2026-06-21T04:46:10",
                                  "codigo": 404,
                                  "error": "NOT_FOUND",
                                  "mensaje": "Usuario no encontrado"
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
    @GetMapping( "/{idUsuario}" )
    public ResponseEntity<UsuarioDTO> findById(
            @Parameter(
                    name = "idUsuario",
                    description = "Identificador único de un usuario",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idUsuario
    ) {
        UsuarioDTO usuario = usuarioService.findById(idUsuario);

        return ResponseEntity.ok(usuario);
    }



    @Operation(
            summary = "Registrar un usuario",
            description = """
                          Permite registrar un nuevo usuario en el sistema.
                          La operación retorna el usuario creado junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    name = "Usuario creado",
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
                    responseCode = "409",
                    description = "Email ya registrado como usuario",
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
                                              "mensaje": "Ya existe un usuario con ese email"
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
    public ResponseEntity<Usuario> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del usuario que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo usuario",
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
            @Valid @RequestBody Usuario usuario
    ) {
        Usuario usuarioInstanciado = usuarioService.save(usuario);
        return new ResponseEntity<>(usuarioInstanciado, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar un usuario",
            description = """
                    Permite actualizar la información de un usuario existente a partir de su identificador.
                    La operación retorna el usuario actualizado cuando la actualización es exitosa.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    name = "Usuario actualizado",
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
                    description = "Usuario no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Usuario inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Usuario no encontrado"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email ya registrado como usuario",
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
                                              "mensaje": "Ya existe un usuario con ese email"
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
    @PutMapping( "/{idUsuario}" )
    public ResponseEntity<Usuario> update(
            @Parameter(
                    name = "idUsuario",
                    description = "Identificador único de un usuario",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idUsuario,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del usuario que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo usuario",
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
            @Valid @RequestBody Usuario usuario
    ) {
        Usuario usuarioInstanciado = usuarioService.update(idUsuario, usuario);
        return ResponseEntity.ok(usuarioInstanciado);
    }



    @Operation(
            summary = "Eliminar un usuario",
            description = """
                          Permite eliminar un usuario del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuario eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningún usuario en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Usuario inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Usuario no encontrada"
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
    @DeleteMapping( "/{idUsuario}" )
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "idUsuario",
                    description = "Identificador único de un usuario",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idUsuario
    ) {
        usuarioService.deleteById(idUsuario);
        return ResponseEntity.noContent().build();
    }

}
