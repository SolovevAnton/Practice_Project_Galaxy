import java.util.Arrays;

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

    Galaxy[] galaxies = {
            new Galaxy(),
            new Galaxy("empty"),
            new Galaxy("1 plan",new Planet()),
            new Galaxy("2 plan",new Planet(),new Planet("Earth", 6_378,23.933)),
            new Galaxy("array",planets),
            new Galaxy("collection", Arrays.asList(planets))
    };

    for(Galaxy g : galaxies){
        System.out.println(g);
    }

    }
}
