package com.kirillkotov.model;

import com.kirillkotov.util.RandomGenerator;

import java.util.*;

public class Universe {
    /*
     * number of seconds between each generation in behavior method*/
    private static final int SECONDS = 30;
    /*Maximum number for all randoms in the behavior method*/
    private static final int MAX_RANDOM = Integer.MAX_VALUE / 1_000_000;
    private String name;
    /*contains all the galaxies in this universe*/
    private LinkedHashSet<Galaxy> galaxies = new LinkedHashSet<>();

    public Universe() {
    }

    public Universe(String name) {
        this.name = name;
    }

    public boolean addGalaxy(Galaxy galaxy) {
        return galaxies.add(galaxy);
    }

    /*Method to find planet by name
     * @return First found com.kirillkotov.model.Planet if it has been found, null otherwise
     **/
    public Planet searchPlanet(String name) {
        return galaxies
                .stream()
                .map(g -> g.searchPlanet(name))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    /*Method to find planet by object com.kirillkotov.model.Planet
     * @return array of index of the galaxy where the planet was Firstly found and index of that planet in the galaxy, null otherwise
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

    /*Method to find galaxy by name
     * @return First found com.kirillkotov.model.Galaxy if it has been found, null otherwise
     **/
    public Galaxy searchGalaxy(String galaxyName) {
        return galaxies
                .stream()
                .filter(g -> g.getName().equals(galaxyName))
                .findFirst()
                .orElse(null);
    }

    /*Searches galaxy in universe by com.kirillkotov.model.Galaxy object
     * @return com.kirillkotov.model.Galaxy index in galaxy or -1 if planet not found*/
    public int searchGalaxy(Galaxy galaxy) {
        return new ArrayList<>(galaxies).indexOf(galaxy);
    }

    /*
     * fills universe with randomly generated galaxies
     * this method runs infinitely!
     * universe list of galaxies is reassigned every time
     * the generation happens once in SECONDS time
     */
    public void behavior() {
        try {
            while (true) {
                LinkedHashSet<Galaxy> generated = RandomGenerator.generateGalaxies('G', 'P',
                        MAX_RANDOM, MAX_RANDOM, MAX_RANDOM, MAX_RANDOM);
                galaxies.addAll(generated);
                Thread.sleep(SECONDS);
            }
        } catch (InterruptedException e) {}

    }

    @Override
    public String toString() {
        return "com.kirillkotov.model.Universe{" +
                "name='" + name + '\'' +
                ", galaxies=" + galaxies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Universe universe = (Universe) o;

        if (getName() != null ? !getName().equals(universe.getName()) : universe.getName() != null) return false;
        return getGalaxies() != null ? getGalaxies().equals(universe.getGalaxies()) : universe.getGalaxies() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getGalaxies() != null ? getGalaxies().hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashSet<Galaxy> getGalaxies() {
        return new LinkedHashSet<>(galaxies);
    }

    public void setGalaxies(LinkedHashSet<Galaxy> galaxies) {
        this.galaxies = galaxies;
    }
}
