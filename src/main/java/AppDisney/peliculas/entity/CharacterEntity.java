package AppDisney.peliculas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE character SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CharacterEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private Long age;
    private Float weight;
    private String history;

    @ManyToMany(mappedBy = "characters")
    private List<MovieEntity> movies = new ArrayList<>();
    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof CharacterEntity)){
            return false;
        } else {
            CharacterEntity characterEntity = (CharacterEntity) obj;
            if (this.getId()!=null){
                return this.getId().equals(characterEntity.getId());
            } else {
                return false;
            }
        }
    }
}
