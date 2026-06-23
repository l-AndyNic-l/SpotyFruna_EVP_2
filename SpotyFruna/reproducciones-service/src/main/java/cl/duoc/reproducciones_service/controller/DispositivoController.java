package cl.duoc.reproducciones_service.controller;

import cl.duoc.reproducciones_service.dto.ErrorResponse;
import cl.duoc.reproducciones_service.dto.ReproduccionDTO;
import cl.duoc.reproducciones_service.model.Dispositivo;
import cl.duoc.reproducciones_service.service.DispositivoService;
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
@RequestMapping("/api/v1/dispositivos_reproducciones")
@Tag(name = "DISPOSITIVOS", description = "API PARA LA GESTIÓN DE DISPOSITIVOS")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;


    @Operation(
            summary = "Listar tipos de dispositivo",
            description = """
                          Retorna la lista completa de tipos de dispositivo registrados en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipos de dispositivo encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ReproduccionDTO.class)
                            ),
                            examples = @ExampleObject(
                                    name = "Tipo de dispositivo",
                                    value = """
                                            {
                                              "id": "1",
                                              "nombre": "Móvil"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen tipos de dispositivo registrados",
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
    public ResponseEntity<List<Dispositivo>> findAll() {
        List<Dispositivo> dispositivos = dispositivoService.findAll();

        if (dispositivos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dispositivos);
    }



    @Operation(
            summary = "Obtener un tipo de dispositivo por su nombre",
            description = """
                          Retorna la información de un tipo de dispositivo a partir de su identificador.
                          La operación responde exitosamente cuando el tipo de dispositivo es encontrado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipo de dispositivo encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class),
                            examples = @ExampleObject(
                                    name = "Tipo de dispositivo",
                                    value = """
                                            {
                                              "id": "1",
                                              "nombre": "Móvil"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Nombre del tipo de dispositivo ingresado inválido",
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
                    description = "No se encontró ningun tipo de dispositivo en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Tipo de dispositivo inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Tipo de dispositivo no encontrado"
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
    @GetMapping( "/{nombre}" )
    public ResponseEntity<Dispositivo> findByNombre(
            @Parameter(
                    name = "nombre",
                    description = "Nombre único del tipo de dispositivo",
                    required = true,
                    example = "Móvil"
            )
            @PathVariable String nombre
    ) {
        Dispositivo dispositivo = dispositivoService.findByNombre(nombre);

        return ResponseEntity.ok(dispositivo);
    }



    @Operation(
            summary = "Registrar un tipo de dispositivo",
            description = """
                          Permite registrar un nuevo tipo de dispositivo en el sistema.
                          La operación retorna el tipo de dispositivo creado junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Tipo de dispositivo registrado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class),
                            examples = @ExampleObject(
                                    name = "Tipo de dispositivo registrado",
                                    value = """
                                            {
                                              "id": "1",
                                              "nombre": "Móvil"
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
    public ResponseEntity<Dispositivo> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del tipo de reproducción que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo tipo de reproducción",
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Móvil"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Dispositivo dispositivo
    ) {
        Dispositivo dispositivoInstanciado = dispositivoService.save(dispositivo);
        return new ResponseEntity<>(dispositivoInstanciado, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar un tipo de dispositivo",
            description = """
                    Permite actualizar la información de un tipo de dispositivo existente a partir de su identificador.
                    La operación retorna el tipo de dispositivo actualizado cuando la actualización es exitosa.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipo de dispositivo actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class),
                            examples = @ExampleObject(
                                    name = "Tipo de dispositivo actualizado",
                                    value = """
                                            {
                                              "id": "1",
                                              "nombre": "Móvil"
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
                    description = "Tipo de dispositivo no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Tipo de dispositivo inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Tipo de dispositivo no encontrado"
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
    @PutMapping( "/{idDispositivo}" )
    public ResponseEntity<Dispositivo> update(
            @Parameter(
                    name = "idDispositivo",
                    description = "Identificador único del tipo de dispositivo",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idDispositivo,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del tipo de reproducción que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReproduccionDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo tipo de reproducción",
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Móvil"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Dispositivo dispositivo
    ) {
        Dispositivo dispositivoInstanciado = dispositivoService.update(idDispositivo, dispositivo);
        return ResponseEntity.ok(dispositivoInstanciado);
    }



    @Operation(
            summary = "Eliminar un tipo de dispositivo",
            description = """
                          Permite eliminar un tipo de dispositivo del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Tipo de dispositivo eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningun tipo de dispositivo en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Tipo de dispositivo inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Tipo de dispositivo no encontrado"
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
    @DeleteMapping( "/{idDispositivo}" )
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "idDispositivo",
                    description = "Identificador único del tipo de dispositivo",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idDispositivo
    ) {
        dispositivoService.delete(idDispositivo);
        return ResponseEntity.noContent().build();
    }


}
