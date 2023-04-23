package com.facultate.albumstore.controller;

import com.facultate.albumstore.entity.Artist;
import com.facultate.albumstore.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping("/artist")
    public String list(Model model) {
        model.addAttribute("artists", artistRepository.findAll());
        return "artist";
    }

    @RequestMapping("/artist/add-form")
    public String addGet(Model model) {
        model.addAttribute("artist", new Artist());
        return "new_artist";
    }

    @PostMapping("/artist/add-new")
    public String add(Artist artist) {
        artistRepository.save(artist);
        return "redirect:/artist";
    }
}