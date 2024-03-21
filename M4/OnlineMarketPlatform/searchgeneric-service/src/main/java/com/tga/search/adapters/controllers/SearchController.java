package com.tga.search.adapters.controllers;

import com.tga.search.adapters.dto.ProductDTO;
import com.tga.search.adapters.dto.PublicKeyDTO;
import com.tga.search.adapters.service.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api/search")
@Tag(name = "Search API", description = "Ex. Handling the Products search or Customer order search")
public class SearchController {
    private static final Logger log = getLogger(lookup().lookupClass());
    @Autowired
    private ProductServiceImpl serviceImpl;


    @Operation(summary = "Get the Product By Product UUID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
                    description = "Product Retrieved for status check",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid Product ID.",
                    content = @Content)
    })
    @GetMapping("/user")
    public ResponseEntity getUserDetails(BearerTokenAuthentication authentication, @RequestParam String query) {
        List<PublicKeyDTO> list=serviceImpl.getUserDetailsLogged(query,authentication.getToken().getTokenValue());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/product")
    public ResponseEntity getProduct(BearerTokenAuthentication authentication, @RequestParam String query) {
        List<ProductDTO> list=serviceImpl.getProductsList(query);
        return ResponseEntity.ok(list);
    }
}
