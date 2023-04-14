import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Arrays;

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

    @Override
    public String toString() {
        return "Galaxy{" +
                "name='" + name + '\'' +
                ", planets=" + planets +
                '}';
    }
}
