package com.asseco.ce.jtsr_digi.product_catalogue.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductCatalogueController implements ProductCatalogueApiApi {

    private final ProductCatalogueService productCatalogueService;

    public ProductCatalogueController(@org.springframework.beans.factory.annotation.Autowired(required = false) ProductCatalogueService productCatalogueService) {
        this.productCatalogueService = Optional.ofNullable(productCatalogueService).orElse(new ProductCatalogueService() {});
    }

}
