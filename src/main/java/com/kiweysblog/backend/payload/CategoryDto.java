package com.kiweysblog.backend.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    @NotEmpty
    @Size(min = 5, message = "Title must be greater than 5 characters")
        private String categoryTitle;
    @NotEmpty
    @Size(min = 10, message = "Description must be greater than 10 characters")
    private String categoryDescription;
}
