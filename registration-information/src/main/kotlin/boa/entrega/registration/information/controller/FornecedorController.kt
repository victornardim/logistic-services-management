package boa.entrega.registration.information.controller

import boa.entrega.registration.information.model.dto.FornecedorDto
import boa.entrega.registration.information.service.FornecedorService
import io.micrometer.core.annotation.Timed
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(
    name = "Fornecedor controller",
    description = "API responsável por manter fornecedores"
)
@RestController("Fornecedor controller")
@RequestMapping("/v1/fornecedor")
class FornecedorController(
    private val service: FornecedorService
) {
    @GetMapping()
    @Operation(summary = "Devolve todos os fornecedores")
    @Timed(histogram = true)
    fun list(
        @RequestParam(required = false, defaultValue = "0") offset: Int,
        @RequestParam(required = false, defaultValue = "50") limit: Int
    ): Collection<FornecedorDto> =
        service.list(offset, limit).map { it.toDto() }

    @GetMapping("/{id}")
    @Operation(summary = "Devolve um fornecedor")
    @Timed(histogram = true)
    @Cacheable(key = "{#id}", cacheNames = ["fornecedor"], cacheManager = "fornecedorCacheManager")
    fun get(
        @PathVariable("id") id: UUID
    ): FornecedorDto =
        service.get(id).toDto()
}
