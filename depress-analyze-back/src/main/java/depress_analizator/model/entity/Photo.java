package depress_analizator.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fotodepressresult")
public class Photo {
    @Id
    @SequenceGenerator(name = "fotodepressresult_id_seq", sequenceName = "fotodepressresult_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fotodepressresult_id_seq")
    private int id;
    @Column(name = "foto")
    private String foto;
    @Column(name = "is_depress")
    private boolean isDepress;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "depressresult_id")
    private DepressResult depressResult;
}
