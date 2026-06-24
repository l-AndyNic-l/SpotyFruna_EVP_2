package cl.duoc.suscripciones_service.controller;

import cl.duoc.suscripciones_service.dto.PlanDTO;
import cl.duoc.suscripciones_service.exception.ErrorResponse;
import cl.duoc.suscripciones_service.model.Plan;
import cl.duoc.suscripciones_service.service.PlanService;
import feign.Response;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/planes")
@Tag(name = "PLANES", description = "API PARA LA GESTIÓN DE ESTADOS DE PLANES")
public class PlanController {

    @Autowired
    private PlanService planService;


    @Operation(
            summary = "Listar planes",
            description = """
                          Retorna la lista completa de planes registrados en el sistema.
                          Si no existen registros, retorna una respuesta sin contenido.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Planes encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = PlanDTO.class)
                            ),
                            examples = @ExampleObject(
                                    name = "Planes",
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Gratuito",
                                                "precio": "$0",
                                                "anuncios": "Con Anuncios",
                                                "tamanioDescargas": "0.0 MB"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No existen planes registrados",
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
    public ResponseEntity<List<PlanDTO>> findAll() {
        List<PlanDTO> planes = planService.findAll();

        if (planes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(planes);
    }



    @Operation(
            summary = "Obtener un plan por identificador",
            description = """
                          Retorna la información de un plan a partir de su identificador.
                          La operación responde exitosamente cuando el plan es encontrado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Plan encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanDTO.class),
                            examples = @ExampleObject(
                                    name = "Plan",
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Gratuito",
                                                "precio": "$0",
                                                "anuncios": "Con Anuncios",
                                                "tamanioDescargas": "0.0 MB"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID del plan ingresado inválido",
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
                                              "mensaje": "idPlan no puede ser menor a 1 ni nulo"
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
    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> findById(
            @Parameter(
                    name = "idPlan",
                    description = "Identificador único del plan",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idPlan
    ) {
        PlanDTO plan = planService.findById(idPlan);

        return ResponseEntity.ok(plan);
    }



    @Operation(
            summary = "Crear un plan",
            description = """
                          Permite registrar un nuevo plan en el sistema.
                          La operación retorna el plan creado junto con su identificador asignado.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Plan creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanDTO.class),
                            examples = @ExampleObject(
                                    name = "Plan creado",
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Gratuito",
                                                "precio": "$0",
                                                "anuncios": "Con Anuncios",
                                                "tamanioDescargas": "0.0 MB"
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
                    description = "Plan ya existente",
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
                                              "mensaje": "Ya existe un plan con ese nombre"
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
    public ResponseEntity<Plan> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del plan que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo plan",
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Gratuito",
                                                "precio": "$0",
                                                "anuncios": "Con Anuncios",
                                                "tamanioDescargas": "0.0 MB"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Plan plan
    ) {
        Plan planInstanciado = planService.save(plan);
        return new ResponseEntity<>(planInstanciado, HttpStatus.CREATED);
    }



    @Operation(
            summary = "Actualizar un plan",
            description = """
                    Permite actualizar la información de un plan existente a partir de su identificador.
                    La operación retorna el plan actualizado cuando la actualización es exitosa.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Plan actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanDTO.class),
                            examples = @ExampleObject(
                                    name = "Plan",
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Gratuito",
                                                "precio": "$0",
                                                "anuncios": "Con Anuncios",
                                                "tamanioDescargas": "0.0 MB"
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
                    description = "Plan no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Plan inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:46:10",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Plan no encontrado"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Plan ya existente",
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
                                              "mensaje": "Ya existe un plan con ese nombre"
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
    public ResponseEntity<Plan> update(
            @Parameter(
                    name = "idPlan",
                    description = "Identificador único del plan",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idPlan,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del plan que se desean/deben registrar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanDTO.class),
                            examples = @ExampleObject(
                                    name = "Nuevo plan",
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Gratuito",
                                                "precio": "$0",
                                                "anuncios": "Con Anuncios",
                                                "tamanioDescargas": "0.0 MB"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Plan plan
    ) {
        Plan planInstanciado = planService.update(idPlan, plan);
        return ResponseEntity.ok(planInstanciado);
    }



    @Operation(
            summary = "Eliminar un plan",
            description = """
                          Permite eliminar un plan del sistema a partir de su identificador.
                          La operación se completa exitosamente sin retornar información en el cuerpo de la respuesta.
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Plan eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró ningun plan en el sistema relacionado a la ID ingresada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Plan inexistente",
                                    value = """
                                            {
                                              "fecha": "2026-06-21T04:05:10.000",
                                              "codigo": 404,
                                              "error": "NOT_FOUND",
                                              "mensaje": "Plan no encontrado"
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
                    name = "idPlan",
                    description = "Identificador único del plan",
                    required = true,
                    example = "1"
            )
            @PathVariable Long idPlan
    ) {
        planService.deleteById(idPlan);
        return ResponseEntity.noContent().build();
    }

}
