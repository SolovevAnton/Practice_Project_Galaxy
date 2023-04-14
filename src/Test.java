/*
*Class with main method to test other classes
 */
public class Test {
    public static void main(String args[]){
    Planet[] planets = {
        new Planet(),
        new Planet("corrupted", 1,1),
        new Planet("Earth", 6_378,23.933),
        new Planet("Earth", 6_378,23.933)
    };
        System.out.println(planets[0].equals(planets[1]));
        System.out.println(planets[2].equals(planets[3]));
    for(Planet p: planets){
        System.out.println(p);
        System.out.println(p.behavior());
    }

    }
}
