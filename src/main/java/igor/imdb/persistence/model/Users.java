package igor.imdb.persistence.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
}
