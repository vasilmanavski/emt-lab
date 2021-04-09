package finki.ukim.mk.emtlab.model.dto;

import finki.ukim.mk.emtlab.model.Author;
import finki.ukim.mk.emtlab.model.enums.Category;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
public class BookDto {
    private String name;

    @Enumerated(EnumType.STRING)
    private String category;

    private Long author;

    private Integer availableCopies;

    public BookDto(String name, String category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public BookDto() {
    }
}
