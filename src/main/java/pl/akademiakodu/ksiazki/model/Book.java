package pl.akademiakodu.ksiazki.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @JsonProperty("autor")
    private String author;

    @JsonIgnore
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "cat_id",updatable = false,insertable = false)
    private Category category;

    //@JsonIgnore
    @Column (name = "cat_id")
    private Integer categoryId;


}
