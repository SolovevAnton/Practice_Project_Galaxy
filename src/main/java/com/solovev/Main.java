package com.solovev;

import com.solovev.model.Galaxy;
import com.solovev.model.Planet;
import com.solovev.model.Universe;
import com.solovev.repository.GalaxyRepo;
import com.solovev.repository.PlanetRepo;
import com.solovev.repository.UniverseRepo;
import com.solovev.util.RandomGenerator;
import com.solovev.util.Util;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;


public class Main {
    static int testCounter = 1;

    /**
     * Method to test code
     * Prints if test have passed or failed
     * if match test prints match, if not prints fails and show both results
     *
     * @param expected expected result
     * @param result   real outcome
     */
    static <T> void test(T expected, T result) {
        if (expected == null && result == null || expected.equals(result)) {
            System.out.println("Test#" + testCounter++ + " success");
        } else {
            System.err.printf("Test#%d failed; Expected:%s Got: %s\n", testCounter++, expected, result);
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Universe u = new Universe("Random");
        int numGenerated = 3;
        int maxRadOrb = 100;
        int maxOrb = 10;
        char GalaxyName = 'G';
        char PlanetName = 'P';
        IntStream
                .range(0, numGenerated)
                .forEach(i -> u.addGalaxy(RandomGenerator.generateGalaxy(GalaxyName,
                        PlanetName, numGenerated, maxRadOrb, maxOrb)));
        Galaxy g = new Galaxy("Random Gal");
        IntStream
                .range(0, numGenerated)
                .forEach(i -> g.addPlanet(RandomGenerator.generatePlanet(PlanetName, maxRadOrb, maxOrb)));
        Planet p = RandomGenerator.generatePlanet(PlanetName, maxRadOrb, maxOrb);
        Planet toBeDeleted = RandomGenerator.generatePlanet(PlanetName, maxRadOrb, maxOrb);
        Planet notExistent = new Planet("NotAPlanet", 1, 1);

        System.out.println(p.behavior());
        System.out.println(g.behavior());
        //Tests
        test(true, g.addPlanet(p));
        test(false, g.addPlanet(p));

        test(p, g.searchPlanet(p.getName()));
        test(null, g.searchPlanet(notExistent.getName()));

        test(g.size() - 1, g.searchPlanet(p));
        test(-1, g.searchPlanet(notExistent));

        g.addPlanet(toBeDeleted);
        test(toBeDeleted, g.deletePlanet(toBeDeleted.getName()));
        test(null, g.deletePlanet(toBeDeleted.getName()));

        g.addPlanet(toBeDeleted);
        test(true, g.deletePlanet(toBeDeleted));
        test(false, g.deletePlanet(toBeDeleted));

        test(true, u.addGalaxy(g));
        test(false, u.addGalaxy(g));

        test(g, u.searchGalaxy(g.getName()));
        test(null, u.searchGalaxy("Earth"));

        test(p, u.searchPlanet(p.getName()));
        test(null, u.searchPlanet(notExistent.getName()));

        test(u.size() - 1, u.searchGalaxy(g));
        test(-1, u.searchGalaxy(new Galaxy()));

        //tests for arrays both should return true
        System.out.println(
                Arrays.equals(new int[]{u.size() - 1, g.size() - 1}, u.searchPlanet(p)));
        System.out.println(
                Arrays.equals(new int[0], u.searchPlanet(notExistent)));

//        u.behavior();
        File dir = new File("src\\main\\resources");

        File universeFileIn = new File(dir,"UniverseInput.xml");
        File fOut = new File(dir,"UniverseMyOut.xml");

        File planetFileIn = new File(dir,"PlanetInput.xml");
        File galaxyFileIn = new File(dir,"GalaxyInput.xml");

        File planetFileOut = new File(dir,"PlanetOut.xml");
        File galaxyFileOut = new File(dir,"GalaxyOut.xml");
        File universeFileOut = new File(dir,"UniverseOut.xml");

        Util.transform(universeFileIn,fOut);
        PlanetRepo planetRepo = new PlanetRepo(planetFileIn);
        planetRepo.save(planetFileOut);
        GalaxyRepo galaxyRepo = new GalaxyRepo(galaxyFileIn);
        galaxyRepo.save(galaxyFileOut);
        UniverseRepo universeRepo = new UniverseRepo(universeFileIn);
        universeRepo.save(universeFileOut);
    }
}
