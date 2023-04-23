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
}