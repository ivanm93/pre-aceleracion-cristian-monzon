package AppDisney.peliculas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "gender")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE gender SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class GenderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "gender")
    private List<MovieEntity> movies;

}
