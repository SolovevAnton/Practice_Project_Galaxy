package com.solovev.util;

import com.solovev.model.Galaxy;
import com.solovev.model.Planet;

import java.util.LinkedHashSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * The Class is designed to generate random number of galaxies with random number of planets
 */
public class RandomGenerator {
    /**
     * Method for random planet generation
     *
     * @param planetName       the char Planet name will start with. Name will be (Char # + random number from 0 to Short.Max_VALUE)
     * @param maxOrbitalPeriod Exclusive max orbital period of generated planet. Must be > 0
     * @param maxRadius        Exclusive max orbital radius of generated planet. Must be > 0
     * @return random planet with MaxRadius and MaxOrbitalPeriod
     */
    public static Planet generatePlanet(char planetName, int maxRadius, int maxOrbitalPeriod) {
        int rad = ThreadLocalRandom.current().nextInt(1, maxRadius);
        int orbPeriod = ThreadLocalRandom.current().nextInt(1, maxOrbitalPeriod);
        int name = ThreadLocalRandom.current().nextInt(Short.MAX_VALUE);
        return new Planet(planetName + "#" + name, rad, orbPeriod);
    }

    /**
     * Method to generate random Galaxy
     *
     * @param galaxyName       the char Galaxy name will start with. Name will be (Char # + random number from 0 to Short.Max_VALUE)
     * @param planetName       the char Planet name will start with. Name will be (Char # + random number from 0 to Short.Max_VALUE)
     * @param numOfPlanets     max number of generated planets Exclusive
     * @param maxOrbitalPeriod Exclusive max orbital period of generated planet. Must be > 0
     * @param maxRadius        Exclusive max orbital radius of generated planet. Must be > 0
     * @return random galaxy
     */
    public static Galaxy generateGalaxy(char galaxyName, char planetName, int numOfPlanets, int maxRadius, int maxOrbitalPeriod) {
        int numberOfPlanets = ThreadLocalRandom.current().nextInt(numOfPlanets);
        Galaxy generated = new Galaxy(galaxyName + "#" + ThreadLocalRandom.current().nextInt(Short.MAX_VALUE));
        IntStream
                .range(0, numberOfPlanets)
                .forEach(i -> generated.addPlanet(generatePlanet(planetName, maxRadius, maxOrbitalPeriod)));
        return generated;
    }

    /**
     * Method generates random number (from 0 to specified) of galaxies, with random number of planets;
     * All names will contain 1 letter and a number form 0 to Short.MAX_VAlUE;
     * radius and orbitalPeriod for planets can be any integer > 0
     *
     * @param galaxyName       the letter galaxies will start with
     * @param planetName       the letter planets will start with
     * @param numOfGalaxies    Exclusive max number of possible galaxies
     * @param numOfPlanets     Exclusive max number of possible planets
     * @param maxRadius        Exclusive max possible radius of the planet
     * @param maxOrbitalPeriod Exclusive max possible orbital period
     * @return LinkedHashSet of Galaxies
     */
    public static LinkedHashSet<Galaxy> generateGalaxies(char galaxyName, char planetName, int numOfGalaxies, int numOfPlanets, int maxRadius, int maxOrbitalPeriod) {
        int numberOfGalaxies = ThreadLocalRandom.current().nextInt(numOfGalaxies);
        LinkedHashSet<Galaxy> generated = new LinkedHashSet<>();
        IntStream
                .range(0, numberOfGalaxies)
                .forEach(i -> generated.add(generateGalaxy(galaxyName, planetName, numOfPlanets, maxRadius, maxOrbitalPeriod)));
        return generated;
    }

}
