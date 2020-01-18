package guru.springframework.errorhandling.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    private String address;

    @OneToOne(mappedBy = "publisher")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Book book;

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
