package com.example.Besucher.Client;

import com.example.Besucher.Model.CompanyData;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="firmenservice")
public interface FirmenverwaltungServiceClient {

  @GetMapping("/allCompanies")
    public List<CompanyData> allCompanies();

}
