package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
/*● Phase 3 : Création d’un test unitaire pour chaque fonctionnalité. OK */

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {


    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        //assert that our list contains all neighbours
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        //assert that our neighbour has been delete from our list
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbour() {
        Neighbour neighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.createNeighbour(neighbour);
        //assert that our neighbour has been added to the neighbour list
        assertTrue(service.getNeighbours().contains(neighbour));
    }

    @Test
    public void getFavoriteNeighboursWithSuccess() {
        //works with getNeighbours but not with getFavoriteNeighbours
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addFavorite(neighbour);
        neighbour.setFavorite(true);
        //assert that our favorite list contains all favorite neighbours
        assertFalse(service.getFavoriteNeighbours().isEmpty());
    }

    @Test
    public void addNeighbourFavorite() {
        Neighbour neighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.addFavorite(neighbour);
        //assert that our favorite list size counts 1 item
        assertEquals(1, service.getFavoriteNeighbours().size());
    }

    @Test
    public void deleteNeighbourFavorite() {
        Neighbour neighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.addFavorite(neighbour);
        Neighbour favNeighbourToDelete = service.getFavoriteNeighbours().get(0);
        service.deleteNeighbour(favNeighbourToDelete);
        //assert that our favorite neighbour has been deleted from our favorite list
        assertFalse(service.getFavoriteNeighbours().contains(favNeighbourToDelete));
    }
}
