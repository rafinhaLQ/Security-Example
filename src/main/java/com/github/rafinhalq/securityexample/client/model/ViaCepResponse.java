package com.github.rafinhalq.securityexample.client.model;

public record ViaCepResponse(
    String cep,

    String logradouro,

    String complemento,

    String bairro,

    String localidade,

    String uf
) {}

