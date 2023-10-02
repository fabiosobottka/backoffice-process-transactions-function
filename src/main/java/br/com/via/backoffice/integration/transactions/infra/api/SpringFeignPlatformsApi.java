package br.com.via.backoffice.integration.transactions.infra.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="platform-api", url="${poc.platform.api}")
public interface SpringFeignPlatformsApi {

    @PutMapping(value = "/v1/transaction/{id}")
    void updateStatus(@PathVariable("id") final String id,
                                                 @RequestBody TransactionUpdateRequest request);

}
