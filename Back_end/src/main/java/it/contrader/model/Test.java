package it.contrader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Test {

    public enum TestType{
        URINETEST,
        BLOODTEST
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id" , name = "doctor")
    private Registry doctor;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "patient")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Registry patient;

    @NotNull
    @Column(name = "isChecked")
    private Boolean isChecked;

    @NotNull
    @Column(name = "date")
    private String date;

    @NotNull
    @Column(name = "type")
    private TestType type;


}
