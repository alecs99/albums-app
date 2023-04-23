package com.facultate.albumstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "albume")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Albume {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name= "albume_generator", sequenceName = "albume_generator", initialValue = 1)
    @Column(name = "AlbumID")
    private Integer albumId;

    @Column(name = "Titlu")
    private String titlu;

    public Albume(Integer albumId, String titlu, Float pret, Integer inStock) {
        this.albumId = albumId;
        this.titlu = titlu;
        this.pret = pret;
        this.inStock = inStock;
    }

    @Column(name = "Pret")
    private Float pret;

    @Column(name = "InStock")
    private Integer inStock;

    @ManyToOne
    @JoinColumn(name = "ArtistID")
    private Artist artist;
}