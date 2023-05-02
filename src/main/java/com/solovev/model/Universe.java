package com.solovev.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.solovev.util.RandomGenerator;

import java.util.*;

public class Universe {
    /**
     * Number of seconds between each generation in behavior method
     */
    private static final int SECONDS = 30;
    /**
     * Maximum number for all randoms in the behavior method
     */
    private static final int MAX_RANDOM = Integer.MAX_VALUE / 1_000_000;
    @JsonAlias("Name")
    private String name;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Galaxy")
    private final LinkedHashSet<Galaxy> galaxies = new LinkedHashSet<>();

    public Universe() {
    }

    public Universe(String name) {
        this.name = name;
    }

    /**
     * Method to add Galaxy to universe
     *
     * @param galaxy - galaxy to be added. Only unique galaxies can be added to universe
     * @return true if galaxy was successfully  added, false otherwise
     */
    public boolean addGalaxy(Galaxy galaxy) {
        return galaxies.add(galaxy);
    }

    /**
     * Method to find planet by name
     *
     * @return First found Planet if it has been found, null otherwise
     */
    public Planet searchPlanet(String name) {
        return galaxies
                .stream()
                .map(g -> g.searchPlanet(name))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    /**
     * Method to find planet by object Planet
     *
     * @return array of index of the galaxy where the planet was Firstly found and index of that planet in the galaxy, empty array otherwise
     **/
    public int[] searchPlanet(Planet planet) {
        int planetIndex = -1;
        int galaxyIndex = -1;
        ArrayList<Galaxy> listOfGalaxies = new ArrayList<>(galaxies);
        while (planetIndex == -1 && ++galaxyIndex < listOfGalaxies.size()) {
            planetIndex = listOfGalaxies.get(galaxyIndex).searchPlanet(planet);
        }

        return planetIndex == -1 ? new int[0] : new int[]{galaxyIndex, planetIndex};
    }

    /**
     * Method to find galaxy by name
     *
     * @return First Galaxy if it has been found, null otherwise
     **/
    public Galaxy searchGalaxy(String galaxyName) {
        return galaxies
                .stream()
                .filter(g -> g.getName().equals(galaxyName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches galaxy in Universe by Galaxy object
     *
     * @return Galaxy index in galaxy or -1 if planet not found
     */
    public int searchGalaxy(Galaxy galaxy) {
        return new ArrayList<>(galaxies).indexOf(galaxy);
    }

    /**
     * Randomly generated galaxies for universe;
     * All galaxies will be added to universe
     * This method runs infinitely!
     * the generation happens once in SECONDS time
     */
    public void behavior() {
        try {
            while (true) {
                LinkedHashSet<Galaxy> generated = RandomGenerator.generateGalaxies('G', 'P',
                        MAX_RANDOM, MAX_RANDOM, MAX_RANDOM, MAX_RANDOM);
                galaxies.addAll(generated);
                // System.out.println(this); //added just for testing
                Thread.sleep(SECONDS);
            }
        } catch (InterruptedException e) {
        }

    }

    /**
     * Returns universe size
     *
     * @return size
     */
    public int size() {
        return galaxies.size();
    }

    @Override
    public String toString() {
        return "Universe{" +
                "name='" + name + '\'' +
                ", galaxies=" + galaxies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Universe universe = (Universe) o;

        if (!Objects.equals(name, universe.name)) return false;
        return Objects.equals(galaxies, universe.galaxies);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (galaxies != null ? galaxies.hashCode() : 0);
        return result;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
