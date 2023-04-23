package com.facultate.albumstore;
import com.facultate.albumstore.controller.AlbumeController;
import com.facultate.albumstore.controller.ArtistController;
import com.facultate.albumstore.entity.Albume;
import com.facultate.albumstore.entity.Artist;
import com.facultate.albumstore.repository.AlbumeRepository;
import com.facultate.albumstore.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumeController.class)
class AlbumControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumeRepository albumRepository;

    @MockBean
    private ArtistRepository artistRepository;

    @Mock
    private Model model;

    private AlbumeController controller;

    @Test
    public void testList() throws Exception {
        List<Albume> albume = Arrays.asList(
                new Albume(1,"album1", 15F, 3 ),
                new Albume(2,"album2", 25F, 31 )
        );

        when(albumRepository.findAll()).thenReturn(albume);

        mockMvc.perform(get("/album"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("album"))
                .andExpect(model().attribute("albume", albume));
    }

    @Test
    public void testAlbumForm() throws Exception {
        mockMvc.perform(get("/album/add"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("new_album"))
                .andExpect(model().attributeExists("album"));
    }

    @Test
    public void testAddAlbum() throws Exception {
        Albume album = new Albume(1,"album1", 15F, 3 );

        mockMvc.perform(post("/album")
                        .param("titlu", album.getTitlu()))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/album"));

        Mockito.verify(albumRepository, times(1)).save(Mockito.any(Albume.class));
    }
    @Test
    public void testDeleteAlbum() throws Exception {
        Integer albumId = 1;

        mockMvc.perform(get("/album/delete/" + albumId))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/album"));

        Mockito.verify(albumRepository, times(1)).deleteById(albumId);
    }

}