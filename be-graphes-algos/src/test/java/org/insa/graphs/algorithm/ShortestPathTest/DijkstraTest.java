package org.insa.graphs.algorithm.ShortestPathTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.lang.model.util.Elements.Origin;

import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.*;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.algorithm.AbstractSolution;
import org.insa.graphs.model.io.PathReader;
import org.insa.graphs.model.RoadInformation.RoadType;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class DijkstraTest {
    private static ShortestPathData dataT, dataT2, dataT3, dataC, dataH, dataB;

    private static Graph Tls, car, hg, bre;

    @BeforeClass
    public static void initAll() throws Exception {

        Node Origine, Destination, Origine2, Destination2, Origine3, Destination3, Origine4, Destination4;

        // Cas Toulouse

        final String Toulouse = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/toulouse.mapgr";

        final GraphReader readerT = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(Toulouse))));
        Tls = readerT.read();

        Origine = Tls.getNodes().get(3605);
        Destination = Tls.getNodes().get(7781);

        dataT = new ShortestPathData(Tls, Origine, Destination, ArcInspectorFactory.getAllFilters().get(0));

        dataT2 = new ShortestPathData(Tls, Tls.getNodes().get(7174), Tls.getNodes().get(16557),
                ArcInspectorFactory.getAllFilters().get(0));
        dataT3 = new ShortestPathData(Tls, Origine, Origine, ArcInspectorFactory.getAllFilters().get(0));

        readerT.close();
        // Cas Carre

        final String Carre = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre-dense.mapgr";

        final GraphReader readerC = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(Carre))));
        car = readerC.read();

        Origine2 = car.getNodes().get(315979);
        Destination2 = car.getNodes().get(78657);

        dataC = new ShortestPathData(car, Origine2, Destination2, ArcInspectorFactory.getAllFilters().get(0));

        readerC.close();
        // Cas Haute Garonne

        final String Haute_Garonne = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";

        final GraphReader readerH = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(Haute_Garonne))));
        hg = readerH.read();

        Origine3 = hg.getNodes().get(135068);
        Destination3 = hg.getNodes().get(134807);

        dataH = new ShortestPathData(hg, Origine3, Destination3, ArcInspectorFactory.getAllFilters().get(0));

        readerH.close();
        // Cas bretagne

        final String Bretagne = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr";

        final GraphReader readerB = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(Bretagne))));
        bre = readerB.read();

        Origine4 = bre.getNodes().get(657101);
        Destination4 = bre.getNodes().get(56068);

        dataB = new ShortestPathData(bre, Origine4, Destination4, ArcInspectorFactory.getAllFilters().get(0));
        readerB.close();
    }

    // Pb à tester chemin inexistant, chemin de longueur nulle, trajet court,trajet
    // long

    @Test

    public void TestToulouse() {
        // Est-ce que la solution est valide?
        assertTrue(new DijkstraAlgorithm(dataT).run().getPath().isValid());
        // Dijkstra renvoie le même resultat que Bellman
        assertEquals(new BellmanFordAlgorithm(dataT).run().getPath().getLength(),
                new DijkstraAlgorithm(dataT).run().getPath().getLength(), 0);

        // Est ce que Dijkstra renvoie un chemin different avec des données différentes
        assertNotEquals(new DijkstraAlgorithm(dataT), new DijkstraAlgorithm((dataT2)));

        // Chemin de longueur nulle
        assertTrue(new DijkstraAlgorithm(dataT3).run().getPath().isValid());

        // Meme distance calculée par Dijkstra et Path?
        // assertEquals(new DijkstraAlgorithm(dataT3).run().getPath().getLength(),new
        // DijkstraAlgorithm(dataT3).cost,0);

    }

    @Test

    public void TestCheminInexistant() {
        // Est-ce que l'algo est réalisable? Ici non car il n'y a pas de chemin entre
        // les deux sommets.
        assertFalse(new DijkstraAlgorithm(dataB).run().isFeasible());

    }

    @Test

    public void TestCarre() {
        // Est-ce que la solution est valide?
        assertTrue(new DijkstraAlgorithm(dataT).run().getPath().isValid());
        // Dijkstra renvoie le même resultat que Bellman
        assertEquals(new BellmanFordAlgorithm(dataT).run().getPath().getLength(),
                new DijkstraAlgorithm(dataT).run().getPath().getLength(), 0);
    }

    @Test

    public void TestCheminLong() {
        // Est-ce que le chemin est realisable?
        assertTrue(new DijkstraAlgorithm(dataH).run().isFeasible());

        // Est-ce que le chemin est optimal?
        assertEquals(Status.OPTIMAL, new DijkstraAlgorithm(dataH).run().getStatus());
        // a tester avec les filtres 
        assertTrue(new DijkstraAlgorithm(dataH).run().getPath().getMinimumTravelTime()<new DijkstraAlgorithm(dataH).run().getPath().getLength());
    }
}
