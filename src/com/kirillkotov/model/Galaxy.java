package com.kirillkotov.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class Galaxy {
    private String name;
    /*contains all the planets in this galaxy*/
    private LinkedHashSet<Planet> planets = new LinkedHashSet<>();

    public Galaxy() {
    }

    /*Method that describes behavior of the galaxy by describing behavior of every planet in galaxy
     * @return String with behavior description of all planets, null planets consider to have null behavior
     * */
    public String behavior() {
        return planets.stream().map(Planet::behavior).collect(Collectors.joining(", "));
    }

    /*Method adds planet, if it doesn't already exist in it
     *@return true if the planet was successfully added, false otherwise*/
    public boolean addPlanet(Planet planet) {
        return planets.add(planet);
    }

    /*Searches planet in galaxy by name
     * @return first found com.kirillkotov.model.Planet with the searched name, null otherwise*/
    public Planet searchPlanet(String planetName) {
        return planets.stream()
                .filter(p -> planetName.equals(p.getName()))
                .findFirst()
                .orElse(null);
    }

    /*Searches planet in galaxy by com.kirillkotov.model.Planet object
     * @return com.kirillkotov.model.Planet index in galaxy or -1 if planet not found*/
    public int searchPlanet(Planet planet) {
        return new ArrayList<>(planets).indexOf(planet);
    }

    /*deletes first found com.kirillkotov.model.Planet with matching name
     * @return com.kirillkotov.model.Planet that was deleted, null if planet not found
     * */
    public Planet deletePlanet(String planetName) {
        Planet searchedPlanet = searchPlanet(planetName);
        if(searchedPlanet == null)
            return null;
        deletePlanet(searchedPlanet);
        return searchedPlanet;
    }

    /*deletes com.kirillkotov.model.Planet from galaxy
     * @return true if planet was successfully deleted, false otherwise*/
    public boolean deletePlanet(Planet planet) {
        return planets.remove(planet);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Planet get(int index){
        if(index < 0 || index >= this.planets.size()){
            throw
        }
        this.planets.
    }*/

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
