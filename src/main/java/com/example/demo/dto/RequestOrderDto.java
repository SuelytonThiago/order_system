package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestOrderDto {

    @NotBlank(message = "the username cannot be empty or null")
    private String username;
    @NotNull(message = "the product id cannot be null")
    private Long productId;
}
