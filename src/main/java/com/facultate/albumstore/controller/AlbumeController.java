package com.facultate.albumstore.controller;

import com.facultate.albumstore.entity.Albume;
import com.facultate.albumstore.entity.Artist;
import com.facultate.albumstore.repository.AlbumeRepository;
import com.facultate.albumstore.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class AlbumeController {

    @Autowired
    private AlbumeRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping("/album")
    public String list(Model model) {
        model.addAttribute("albume", albumRepository.findAll());
        return "album";
    }

    @RequestMapping("/album/add")
    public String addGet(Model model) {
        model.addAttribute("album", new Albume());
        model.addAttribute("artisti", artistRepository.findAll());
        return "new_album";
    }

    @PostMapping("/album")
    public String add(Albume album) {
        albumRepository.save(album);
        return "redirect:/album";
    }

    @RequestMapping("/album/delete/{albumId}")
    public String delete(@PathVariable Integer albumId) {
        albumRepository.deleteById(albumId);
        return "redirect:/album";
    }

    @RequestMapping("/album/change/{albumId}")
    public String addGet(Model model, @PathVariable Integer albumId) {
        Albume album = albumRepository.getById(albumId);
        model.addAttribute("album", album);
        model.addAttribute("artisti", artistRepository.findAll());
        return "new_album";
    }

    @RequestMapping("/album/edit/{albumId}")
    public String editGet(@PathVariable Integer albumId, Model model) {
        Optional<Albume> album = albumRepository.findById(albumId);
        if (album.isPresent()) {
            model.addAttribute("album", album.get());
            model.addAttribute("artisti", artistRepository.findAll());
            return "edit_album";
        } else {
            return "redirect:/album";
        }
    }

    @PostMapping("/album/edit")
    public String editPost(Albume album) {
        albumRepository.save(album);
        return "redirect:/album";
    }

}
