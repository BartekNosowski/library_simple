package pl.akademiakodu.ksiazki.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Book {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;


    private String title;

    @JsonProperty("autor")
    private String author;

    @JsonIgnore
    private String isbn;




}
