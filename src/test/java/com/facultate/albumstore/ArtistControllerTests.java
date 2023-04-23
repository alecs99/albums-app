package com.facultate.albumstore;

import com.facultate.albumstore.controller.ArtistController;
import com.facultate.albumstore.entity.Artist;
import com.facultate.albumstore.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
@WebMvcTest(ArtistController.class)
public class ArtistControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistRepository artistRepository;

    @Test
    public void testListArtists() throws Exception {
        List<Artist> artists = Arrays.asList(
                new Artist(1, "Artist 1"),
                new Artist(2, "Artist 2")
        );

        when(artistRepository.findAll()).thenReturn(artists);

        mockMvc.perform(get("/artist"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("artist"))
                .andExpect(model().attribute("artists", artists));
    }

    @Test
    public void testAddArtistForm() throws Exception {
        mockMvc.perform(get("/artist/add-form"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("new_artist"))
                .andExpect(model().attributeExists("artist"));
    }

    @Test
    public void testAddArtist() throws Exception {
        Artist artist = new Artist(1, "Artist 1");

        mockMvc.perform(post("/artist/add-new")
                .param("nume", artist.getNume()))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/artist"));

        Mockito.verify(artistRepository, times(1)).save(Mockito.any(Artist.class));
    }

    @Test
    public void testDeleteArtist() throws Exception {
        Integer artistId = 1;

        mockMvc.perform(get("/artist/delete/" + artistId))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/artist"));

        Mockito.verify(artistRepository, times(1)).deleteById(artistId);
    }
}
