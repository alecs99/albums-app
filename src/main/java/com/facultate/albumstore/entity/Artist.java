package com.facultate.albumstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "artist")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_generator")
    @SequenceGenerator(name= "artist_generator", sequenceName = "artist_generator", initialValue = 1)
    @Column(name = "ArtistID")
    private Integer artistId;

    @Column(name = "nume")
    private String nume;
}