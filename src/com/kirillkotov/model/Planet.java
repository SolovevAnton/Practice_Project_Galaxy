package com.kirillkotov.model;

public class Planet {
    private String name;
    private double radius;
    private double orbitalPeriod;

    public Planet() {
        this.name = "";
        this.orbitalPeriod = 1;
    }

    public Planet(String name, double radius, double orbitalPeriod) {
        this.name = name;
        this.radius = radius;
        this.orbitalPeriod = orbitalPeriod;
    }

    /**
     * calculates and returns speed in km/h of planet rotation
     *
     * @return
     */
    private double speedCalc() {
        return 2 * Math.PI * radius / orbitalPeriod;
    }

    /*
     * describes behavior of the planet
     * @return String with name of the planet and its rotation speed in km/h
     */
    public String behavior() {
        return String.format("%s rotation speed %.2f km/h", name, speedCalc());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Planet planet = (Planet) o;

        if (Double.compare(planet.getRadius(), getRadius()) != 0) return false;
        if (Double.compare(planet.getOrbitalPeriod(), getOrbitalPeriod()) != 0) return false;
        return getName() != null ? getName().equals(planet.getName()) : planet.getName() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        temp = Double.doubleToLongBits(getRadius());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getOrbitalPeriod());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", radius=" + radius +
                ", orbitalPeriod=" + orbitalPeriod +
                '}';
    }
}
