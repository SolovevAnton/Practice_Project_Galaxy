import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Galaxy {
    private String name;
    /*contains all the planets in this galaxy*/
    private LinkedHashSet<Planet> planets= new LinkedHashSet<>();
    public Galaxy(){}

    public Galaxy(String name,Collection<Planet> planets){
        this.name = name;
        this.planets.addAll(planets);
    }
    /*Constructor to add planets in the galaxy with the array of planets*/
    public Galaxy(String name, Planet...planets) {
       this(name,Arrays.asList(planets));
    }
    /*Method that describes behavior of the galaxy by describing behavior of every planet in galaxy
    * @return String with behavior description of all planets, null planets consider to have null behavior
    * */
    public String behavior(){
        Function<Planet,String> planetBehavior= p-> p == null ? null : p.behavior();
        return planets == null ? null
                : planets.stream().map(planetBehavior).collect(Collectors.joining(", "));}
    /*Method adds planet, if it doesn't already exist in it
    *@return true if the planet was successfully added, false otherwise*/
    public boolean addPlanet(Planet planet){
        return planets != null && planets.add(planet);
    }
    /*Searches planet in galaxy by name
    * @return first found Planet with the searched name, null otherwise*/
    public Planet searchPlanet(String planetName){
        Predicate<Planet> checkName = p -> p != null && (planetName != null ?
                planetName.equals(p.getName()) : planetName == p.getName());
        return planets == null ? null
                :planets.stream()
                .filter(checkName)
                .findFirst()
                .orElse(null);
    }
    /*Searches planet in galaxy by Planet object
     * @return Planet index in galaxy or -1 if planet not found*/
    public int searchPlanet(Planet planet){
        if(planets == null) {return -1;}
        ArrayList<Planet> helpingArray = new ArrayList<>(planets);
        return helpingArray.indexOf(planet);
    }

    /*deletes first found Planet with matching name
    * @return Planet that was deleted, null if planet not found
    * */
    public Planet deletePlanet(String planetName){
        Planet searchedPlanet = searchPlanet(planetName);
        return deletePlanet(searchedPlanet) ? searchedPlanet : null;
    }
    /*deletes Planet from galaxy
    * @return true if planet was successfully deleted, false otherwise*/
    public boolean deletePlanet(Planet planet){
        return planets != null && planets.remove(planet);
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "name='" + name + '\'' +
                ", planets=" + planets +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashSet<Planet> getPlanets() {
        return new LinkedHashSet<>(planets);
    }

    public void setPlanets(LinkedHashSet<Planet> planets) {
        this.planets = planets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Galaxy galaxy = (Galaxy) o;

        if (getName() != null ? !getName().equals(galaxy.getName()) : galaxy.getName() != null) return false;
        return getPlanets() != null ? getPlanets().equals(galaxy.getPlanets()) : galaxy.getPlanets() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPlanets() != null ? getPlanets().hashCode() : 0);
        return result;
    }
}
