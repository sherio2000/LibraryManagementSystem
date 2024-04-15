package com.maids.LibraryManagmentSystem.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    private Integer publicationYear;
    @NotBlank(message = "ISBN is required")
    private String isbn;
}
