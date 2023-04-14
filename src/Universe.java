import java.util.*;
import java.util.function.Predicate;
public class Universe {
    /*
    * number of seconds between each generation in behavior method*/
    private static final int SECONDS = 30;
    /*Maximum number for all randoms in the behavior method*/
    private static final int MAX_RANDOM = Integer.MAX_VALUE/1_000_000;
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
     **/
    public boolean addGalaxy(Galaxy galaxy){
        return galaxies!=null && galaxies.add(galaxy);
    }
    /*Method to find planet by name
    * @return First found Planet if it has been found, null otherwise
    **/
    public Planet searchPlanet(String name){
            return galaxies == null ? null
                    :galaxies
                    .stream()
                    .filter(Objects::nonNull)
                    .map(g -> g.searchPlanet(name))
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);
    }
    /*Method to find planet by object Planet
     * @return array of index of the galaxy where the planet was Firstly found and index of that planet in the galaxy, null otherwise
     **/
    public int[] searchPlanet(Planet planet){
        if(galaxies == null || galaxies.isEmpty()) {return null;}
        int planetIndex = -1;
        int galaxyIndex = -1;
        ArrayList<Galaxy> listOfGalaxies = new ArrayList<>(galaxies);
        listOfGalaxies.remove(null);
        while(planetIndex == -1 && ++galaxyIndex < listOfGalaxies.size()){
            planetIndex = listOfGalaxies.get(galaxyIndex).searchPlanet(planet);
        }

        return planetIndex == -1 ? null
                :new int[]{galaxyIndex,planetIndex};
    }
    /*Method to find galaxy by name
     * @return First found Galaxy if it has been found, null otherwise
     **/
    public Galaxy searchGalaxy(String galaxyName){
        Predicate<Galaxy> checkName = g -> g!=null && (
                g.getName() != null ? g.getName().equals(galaxyName)
                        : galaxyName == null
                );
        return galaxies == null ? null
                :galaxies
                .stream()
                .filter(checkName)
                .findFirst()
                .orElse(null);
    }
    /*Searches galaxy in universe by Galaxy object
     * @return Galaxy index in galaxy or -1 if planet not found*/
    public int searchGalaxy(Galaxy galaxy){
        if(galaxies == null) {return -1;}
        ArrayList<Galaxy> helpingArray = new ArrayList<>(galaxies);
        return helpingArray.indexOf(galaxy);
    }
    /*
     * fills universe with randomly generated galaxies
     * this method runs infinitely!
     * universe list of galaxies is reassigned every time
     * the generation happens once in SECONDS time
     */
    public void behavior() throws InterruptedException{
        while(true) {
            LinkedHashSet<Galaxy> generated = RandomGalaxiesAndPlanetsGenerator.generate('G', 'P', MAX_RANDOM, MAX_RANDOM,MAX_RANDOM,MAX_RANDOM);
            galaxies = new LinkedHashSet<>(generated);
            Thread.sleep(SECONDS);
        }

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
