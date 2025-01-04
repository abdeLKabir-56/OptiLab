package com.optilab.utilisateur.client;

import com.optilab.utilisateur.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "laboratoire-service", url = "http://localhost:8080/api/v1/laboratoires", configuration = FeignClientConfig.class)
public interface LaboratoireClient {

    @GetMapping("/{id}/is-exist")
    Boolean isLaboExist(@PathVariable Long id);

}
