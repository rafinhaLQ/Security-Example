package com.github.rafinhalq.securityexample.client;

import com.github.rafinhalq.securityexample.client.model.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viacep", url = "viacep.com.br/ws")
public interface ViaCepClient {
    @GetMapping(path = "/{cep}/json")
    ViaCepResponse getAddressByCep(@PathVariable(value = "cep") String cep);
}
