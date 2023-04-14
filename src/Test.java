import java.util.Arrays;
import java.util.Collection;

/*
*Class with main method to test other classes
 */
public class Test {
    public static void main(String args[]){
    Planet[] planets = {
            null,
        new Planet(),
        new Planet("corrupted", 1,1),
        new Planet("Earth", 6_378,23.933),
        new Planet("Earth", 100,10)
    };

    Galaxy[] galaxies = {
            new Galaxy(),
            new Galaxy("nullPlanet", null,null),
            new Galaxy("empty"),
            new Galaxy("1 plan",new Planet()),
            new Galaxy("2 plan",new Planet(),new Planet("otherPlan", 6_378,23.933)),
            new Galaxy("array",planets),
            new Galaxy("collection", Arrays.asList(planets)),
    };

    for(Galaxy g : galaxies){
        System.out.println(g.getName());
        System.out.println(g.behavior());
        System.out.println(g.addPlanet(null));
        System.out.println(g.deletePlanet((String)null));
        System.out.println(g.deletePlanet((Planet)null));
        System.out.println(g);
        System.out.println();
    }

    }
}
