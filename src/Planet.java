import java.util.Objects;

public class Planet {
    private String name;
    /*
    * Radius of the planet in km;
    */
    private double radius;
    /*
    * period of planet rotation in hours;
    * default value 1
    */
    private double orbitalPeriod = 1;

    public Planet(){}
    /*
    * Constructor for a Planet
    * @param name - a name of the Planet
    * @param radius - radius of a planet in KM, should be lager than 0
    * @param orbitalPeriod - period of planet rotation in hours, should be lager than 0
    * @throws IllegalArgumentException if radius or orbital period >= 0*/
    public Planet(String name, double radius, double orbitalPeriod) throws IllegalArgumentException{
        this.name = name;
        setRadius(radius);
        setOrbitalPeriod(orbitalPeriod);
    }
    /*
    * calculates and returns speed in km/h of planet rotation
    */
    private double speedCalc(){
        return 2*Math.PI*radius/orbitalPeriod;
    }

    /*
    * describes behavior of the planet
    * @return String with name of the planet and its rotation speed in km/h
     */
    public String behavior(){
        return String.format("%s rotation speed %.2f km/h", name, speedCalc());
    }


    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", radius=" + radius +
                ", orbitalPeriod=" + orbitalPeriod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Planet planet = (Planet) o;

        if (Double.compare(planet.radius, radius) != 0) return false;
        if (Double.compare(planet.orbitalPeriod, orbitalPeriod) != 0) return false;
        return Objects.equals(name, planet.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(orbitalPeriod);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius(){
        return radius;
    }

    public void setRadius(double radius) throws IllegalArgumentException{
        if(radius <= 0) {throw new IllegalArgumentException("radius should be > 0");}
        this.radius = radius;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) throws IllegalArgumentException{
        if(orbitalPeriod <= 0) {throw new IllegalArgumentException("orbitalPeriod should be > 0");}
        this.orbitalPeriod = orbitalPeriod;
    }
}
