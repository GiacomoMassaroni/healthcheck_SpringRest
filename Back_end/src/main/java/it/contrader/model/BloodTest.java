package it.contrader.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BloodTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "redBloodCell")
    private Float redBloodCell;

    @Column(name = "whiteBloodCell")
    private Float whiteBloodCell;

    @Column(name = "platelets")
    private Float platelets;

    @Column(name = "hemoglobin")
    private Float hemoglobin;

    @OneToOne
    @JoinColumn(referencedColumnName  = "id", name = "testFk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Test testFk;
}
