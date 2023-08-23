package com.kiweysblog.backend.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer id;
    @NotEmpty
    @Size(min = 4, message = "Title name must be greater than 3 characters")
    private String title;
    @NotEmpty
    @Size(min = 4, message = "Content name must be greater than 3 characters")
    private String content;
    private String imageName;
    private Date createdDate;
    private Date modifiedDate;
    private CategoryDto category;
    private UserDto author;
    private Set<CommentDto> comments = new HashSet<>();
}
