package com.solovev.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class Galaxy {
    @JsonProperty("Name")
    private String name;
    @JacksonXmlElementWrapper(localName = "Planets")
    @JacksonXmlProperty(localName = "Planet")
    private final LinkedHashSet<Planet> planets = new LinkedHashSet<>();

    public Galaxy() {
    }

    public Galaxy(String name) {
        this.name = name;
    }

    /**
     * Method that describes behavior of the galaxy by describing behavior of every planet in galaxy
     *
     * @return String with behavior description of all planets, null planets consider to have null behavior
     */
    public String behavior() {
        return planets.stream().map(Planet::behavior).collect(Collectors.joining(", "));
    }

    /**
     * Method adds planet in Galaxy, if it doesn't already exist in it
     *
     * @return true if the planet was successfully added, false otherwise
     */
    public boolean addPlanet(Planet planet) {
        return planets.add(planet);
    }

    /**
     * Searches planet in galaxy by name
     *
     * @return first found Planet with the searched name, null otherwise
     */
    public Planet searchPlanet(String planetName) {
        return planets.stream()
                .filter(p -> planetName.equals(p.getName()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches planet in galaxy by Planet object
     *
     * @return Planet index in galaxy or -1 if planet not found
     */
    public int searchPlanet(Planet planet) {
        return new ArrayList<>(planets).indexOf(planet);
    }

    /**
     * Deletes first found Planet with matching name
     *
     * @return Planet that was deleted, null if planet not found
     */
    public Planet deletePlanet(String planetName) {
        Planet searchedPlanet = searchPlanet(planetName);
        if (searchedPlanet == null)
            return null;
        deletePlanet(searchedPlanet);
        return searchedPlanet;
    }

    /**
     * Deletes Planet from Galaxy
     *
     * @return true if planet was successfully deleted, false otherwise
     */
    public boolean deletePlanet(Planet planet) {
        return planets.remove(planet);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method used to find Planet by its index in Galaxy
     *
     * @param index - index of the planet. Cannot be < 0 or more than Galaxy size
     * @return Planet with given index
     * @throws IndexOutOfBoundsException if index is < 0 or more than GalaxySize
     */
    public Planet get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index" + index + "is out of bounds for Galaxy of size " + this.planets.size());
        }
        return new ArrayList<>(this.planets).get(index);
    }

    /**
     * Method returns size of the Galaxy
     *
     * @return size
     */
    public int size() {
        return this.planets.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Galaxy galaxy = (Galaxy) o;

        if (!Objects.equals(name, galaxy.name)) return false;
        return Objects.equals(planets, galaxy.planets);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (planets != null ? planets.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "name='" + name + '\'' +
                ", planets=" + planets +
                '}';
    }
}
