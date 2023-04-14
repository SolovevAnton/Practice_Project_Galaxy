import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

public class Universe {
    private String name;
    /*contains all the galaxies in this universe*/
    private LinkedHashSet<Galaxy> galaxies = new LinkedHashSet<>();
    public Universe(){}
    public Universe(String name, Collection<Galaxy> galaxies){
        this.name = name;
        this.galaxies.addAll(galaxies);
    }
    /*Constructor to add galaxies in the universe with the array of galaxies*/
    public Universe(String name, Galaxy...galaxies){
        this(name, Arrays.asList(galaxies));
    }
    /*Method adds Galaxy, if it doesn't already exist in this universe
     *@return true if the galaxy was successfully added, false otherwise
     * */
    public boolean addGalaxy(Galaxy galaxy){
        return galaxies!=null && galaxies.add(galaxy);
    }
    /*Method to find planet by name
    * @return First found Planet if it has been found, null otherwise*/
    public Planet searchPlanet(String name){
            if(galaxies == null || galaxies.isEmpty()) {return null;}
            LinkedHashSet<Galaxy> nonNullGalaxies = getGalaxies();
            nonNullGalaxies.remove(null);
            return nonNullGalaxies.isEmpty() ? null
                   :nonNullGalaxies
                    .stream()
                    .map(g -> g.searchPlanet(name))
                    .findFirst()
                    .orElse(null);
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
