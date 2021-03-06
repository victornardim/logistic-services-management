package boa.entrega.registration.information.model.dto

import java.io.Serializable
import java.util.UUID

data class FornecedorDto(
    val id: UUID,
    val razaoSocial: String,
    val nomeFantasia: String,
    val cnpj: String,
    val endereco: EnderecoDto
) : Serializable
