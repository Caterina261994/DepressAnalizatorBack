package depress_analizator.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "depressresult")
public class DepressResult {
    @Id
    @SequenceGenerator(name = "depressresult_id_seq", sequenceName = "depressresult_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="depressresult_id_seq")
    private int id;
    @Column(name = "create_date")
    private LocalDate createDate;
    @Column(name = "depress_procent")
    private int depressPercent;
    @JsonManagedReference
    @OneToMany(mappedBy = "depressResult", cascade = CascadeType.ALL)
    private List<Photo> fotos;
}
