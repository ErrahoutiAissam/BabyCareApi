package com.errahouti.BabyCareApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Child child;

    @ElementCollection
    @CollectionTable(name = "child_growth", joinColumns = @JoinColumn(name = "child_id"))
    @MapKeyColumn(name = "year")
    @Column(name = "growth_value")
    private Map<Integer, Double> growthData;

}
