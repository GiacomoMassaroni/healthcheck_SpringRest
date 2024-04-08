package it.contrader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrineTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "color")
    private Float color;

    @NotNull
    @Column(name = "ph")
    private Float ph;

    @NotNull
    @Column(name = "protein")
    private Float protein;

    @NotNull
    @Column(name = "hemoglobine")
    private Float hemoglobine;

    @NotNull
    @OneToOne
    @JoinColumn(referencedColumnName  = "id", name = "testFk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Test testFk;
}
