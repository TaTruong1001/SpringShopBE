package com.example.springshopbe.dto;

import com.example.springshopbe.domain.CategoryStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.io.Serializable;

/**
 * DTO for {@link com.example.springshopbe.domain.Category}
 */
@Data
public class CategoryDto implements Serializable {
    private Long id;
    @NotEmpty(message = "Category name is required")
    private String name;
    private CategoryStatus status;
}