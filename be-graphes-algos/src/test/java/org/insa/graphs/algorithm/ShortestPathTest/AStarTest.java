package org.insa.graphs.algorithm.ShortestPathTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.*;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class AStarTest {
    private static ShortestPathData dataT, dataT2, dataT3, dataC, dataH,dataHtps,dataHlg, dataB;

    private static Graph Tls, car, hg, bre;

    @BeforeClass
    public static void initAll() throws Exception {

        Node Origine, Destination, Origine2, Destination2, Origine3, Destination3, Origine4, Destination4;

        // Cas Toulouse (Cas "normal")

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
        // Cas Carre Dense(non routier)

        final String Carre = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre-dense.mapgr";

        final GraphReader readerC = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(Carre))));
        car = readerC.read();

        Origine2 = car.getNodes().get(315979);
        Destination2 = car.getNodes().get(78657);

        dataC = new ShortestPathData(car, Origine2, Destination2, ArcInspectorFactory.getAllFilters().get(0));

        readerC.close();
        // Cas Haute Garonne (carte longue )

        final String Haute_Garonne = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";

        final GraphReader readerH = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(Haute_Garonne))));
        hg = readerH.read();

        Origine3 = hg.getNodes().get(135068);
        Destination3 = hg.getNodes().get(134807);

        dataH = new ShortestPathData(hg, Origine3, Destination3, ArcInspectorFactory.getAllFilters().get(0));
        dataHlg = new ShortestPathData(hg, Origine3, Destination3, ArcInspectorFactory.getAllFilters().get(1));
        dataHtps = new ShortestPathData(hg, Origine3, Destination3, ArcInspectorFactory.getAllFilters().get(2));
        
        readerH.close();
        // Cas bretagne (Chemin impossible)

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
        assertTrue(new AStarAlgorithm(dataT).run().getPath().isValid());
        // AStar renvoie le même resultat que Dijkstra
        assertEquals(new DijkstraAlgorithm(dataT).run().getPath().getLength(),
                new AStarAlgorithm(dataT).run().getPath().getLength(), 0);

        // Est ce que AStar renvoie un chemin different avec des données différentes
        assertNotEquals(new AStarAlgorithm(dataT), new AStarAlgorithm((dataT2)));

        // Chemin de longueur nulle
        assertEquals(null,new AStarAlgorithm(dataT3).run().getPath().getLength(),0,0);
        assertTrue(new AStarAlgorithm(dataT3).run().getPath().isValid());

    }

    @Test

    public void TestCheminInexistant() { //Carte Bretagne
        // Est-ce que l'algo est réalisable? Ici non car il n'y a pas de chemin entre
        // les deux sommets.
        assertFalse(new AStarAlgorithm(dataB).run().isFeasible());

    }

    @Test

    public void TestCarre() {
        // Est-ce que la solution est valide?
        assertTrue(new AStarAlgorithm(dataC).run().getPath().isValid());
        // AStar renvoie le même resultat que Dijkstra
        assertEquals(new DijkstraAlgorithm(dataC).run().getPath().getLength(),new AStarAlgorithm(dataC).run().getPath().getLength(), 0);
    }

    @Test

    public void TestCheminLong() {
        // Est-ce que le chemin est realisable?
        assertTrue(new AStarAlgorithm(dataH).run().isFeasible());

        //On teste si les parcours en temps et en longueur sont bien optimaux dans leur donnée éponyme
        assertTrue(new AStarAlgorithm(dataHlg).run().getPath().getLength()<= new AStarAlgorithm(dataHtps).run().getPath().getLength());
        //L'optimisation A* de Dijkstra en longueur montre bien qu'il est meilleur en longueur et en temps
        assertFalse(new AStarAlgorithm(dataHtps).run().getPath().getMinimumTravelTime()<= new AStarAlgorithm(dataHlg).run().getPath().getMinimumTravelTime());
    }  
}

