package com.kirillkotov;

import com.kirillkotov.model.Galaxy;
import com.kirillkotov.model.Planet;
import com.kirillkotov.model.Universe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Planet[] planets = {
                null,
                new Planet(),
                new Planet("corrupted", 1, 1),
                new Planet("Earth", 6_378, 23.933),
                new Planet("Earth", 100, 10)
        };

        Galaxy[] galaxies = {
                null,
                new Galaxy(),
                new Galaxy("nullPlanet", (Planet) null),
                new Galaxy("empty"),
                new Galaxy("1 plan", new Planet()),
                new Galaxy("2 plan", new Planet(), new Planet("otherPlan", 6_378, 23.933)),
                new Galaxy("array", planets),
                new Galaxy("collection", Arrays.asList(planets)),
        };
        int galaxyToAdd = 5;
        Universe[] universes = {
                new Universe(),
                new Universe("nullGalaxy", (Galaxy) null),
                new Universe("empty"),
                new Universe("1 Gal", new Galaxy()),
                new Universe("2 Gal", new Galaxy(), galaxies[galaxyToAdd]),
                new Universe("array", galaxies),
                new Universe("collection", Arrays.asList(galaxies))
        };

        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        if(name.isEmpty()){
            System.out.println("Erro");
            return;
        }

//    for(com.kirillkotov.model.Universe u : universes){
////        u.setGalaxies(null);
//        System.out.println(u);
//        System.out.println(u.searchPlanet((String) null));
//        System.out.println(u.searchGalaxy((String)null));
//        System.out.println(u.searchGalaxy((com.kirillkotov.model.Galaxy)null));
//        System.out.println(u.addGalaxy(null));
//        System.out.println(Arrays.toString(u.searchPlanet((com.kirillkotov.model.Planet) null)));
//        System.out.println(u.searchPlanet("otherPlan"));
//        System.out.println(u.searchGalaxy(new com.kirillkotov.model.Galaxy()));
//        System.out.println(Arrays.toString(u.searchPlanet(new com.kirillkotov.model.Planet("otherPlan", 6_378,23.933))));
//        System.out.println(u.addGalaxy(galaxies[galaxyToAdd]));
//        System.out.println(u);
//        System.out.println();
//    }
        Universe randomUni = new Universe();
        randomUni.behavior();

    }
}
