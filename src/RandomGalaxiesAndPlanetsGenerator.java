import java.util.LinkedHashSet;
import java.util.concurrent.ThreadLocalRandom;

/*
* The Class is designed to generate random number of galaxies with random number of planets
* */
public class RandomGalaxiesAndPlanetsGenerator {
    /*
    * Method generates random number (from 0 to specified) of galaxies, with random number of planets
    * All names will contain 1 letter and a number form 0 to Integer.MAX_VAlUE
    * radius and orbitalPeriod for planets can be any integer
    * @param galaxyNames the letter galaxies will start with
    * @param planetNames the letter planets will start with
    * @param numOfGalaxies max number of possible galaxies exclusive
    * @param numOfPlanets max number of possible planets exclusive
    * @param maxRadius max possible radius of the planet
    * @param maxOrbitalPeriod max possible orbital period
    * @return LinkedHashSet of Galaxies
    */
    public static LinkedHashSet<Galaxy> generate(char galaxyNames, char planetNames, int numOfGalaxies, int numOfPlanets,int maxRadius, int maxOrbitalPeriod){
       int numberOfGalaxies = ThreadLocalRandom.current().nextInt(numOfGalaxies);
       LinkedHashSet<Galaxy> generated = new LinkedHashSet<>();
       for(int i = 0; i < numberOfGalaxies; i++)
       {
           generated.add(new Galaxy(galaxyNames+"#"+i, generatePlanets(planetNames,numOfPlanets,maxRadius,maxOrbitalPeriod)));
       }
       return generated;
    }
    /*Method to generate a planets
    * @param planetName letter planets will start with
    * @param numOfPlanets max number of possible planets exclusive
    * @param maxRadius max possible radius of the planet
    * @param maxOrbitalPeriod max possible orbital period
    * @return array of generated planets
    *
     */
    public static Planet[] generatePlanets(char planetName, int numOfPlanets,int maxRadius, int maxOrbitalPeriod ) {
        int numberOfPlanets = ThreadLocalRandom.current().nextInt(numOfPlanets);
        Planet[] generated = new Planet[numberOfPlanets];
        for(int i = 0; i < generated.length; i++){
            int rad =ThreadLocalRandom.current().nextInt(1,maxRadius);
            int orbPeriod = ThreadLocalRandom.current().nextInt(1,maxOrbitalPeriod);
            generated[i] = new Planet(planetName+"#" + i, rad,orbPeriod);
        }
        return generated;
    }
}
