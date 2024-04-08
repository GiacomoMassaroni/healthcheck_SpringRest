package it.contrader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registry {

    public enum Gender {
        MASCHIO,
        FEMMINA;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "gender")
    private Gender gender;

    @NotNull
    @Column(name = "birthDate")
    private String birthDate;

    @NotNull
    @Column(name = "brithPlace")
    private String birthPlace;

    @NotNull
    @Column(length = 2, name = "nationality")
    @Size(min = 2, max = 2)
    private String nationality;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(length = 3, name = "bg")
    @Size(min = 2, max = 3)
    private String bg;

    @NotNull
    @Column(length = 16, unique = true, name = "cf")
    @Size(max =  16, min = 16)
    private String cf;


    @NotNull
    @OneToOne
    @JoinColumn(referencedColumnName  = "id", name = "userFk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User userFk;

}
