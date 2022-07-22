package igor.imdb.persistence.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(name = "avg_rating")
    private Double avgRating = 0d;
}
