package com.netzmy.blog.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {
    //validation checking for category name
    @NotBlank(message = "Category name required")
    @Size(min = 2, max = 50, message = "Category name must be betweein min & max characters")
    @Pattern(regexp = "^[\\w\\s-]+$", message = "Category name can only contain letters numbers spaces etc.")
    private String name;

}
