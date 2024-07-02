package com.goldeng.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Commission tracker API",
        description = "Our APP provides endpoints to manipulate commission data",
        contact = @Contact(
            name = "Nicolas Ortiz",
            email = "nicolasortizsd@gmail.com"
        )
    )
)
public class SwaggerConfig {
    
}
