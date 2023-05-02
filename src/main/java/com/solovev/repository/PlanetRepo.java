package com.solovev.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.solovev.model.Planet;

import java.io.File;
import java.io.IOException;

/**
 * Class to build planet from XML File
 */
public class PlanetRepo {
    private Planet planet;
    private XmlMapper mapper = new XmlMapper();

    public PlanetRepo() {
    }

    public PlanetRepo(Planet planet) { // is it ok to have this type of constructor in repo?
        this.planet = planet;
    }

    /**
     * Constructor to get planet object form XML
     * @param file path to file with Planet
     * @throws IOException
     */
    public PlanetRepo (File file) throws IOException {
        this.planet = mapper.readValue(file, Planet.class);
    }

    public Planet getPlanet() { //is it OK to have getters and setters in repo?
        return planet;
    }

    /**
     * Method to save object to XML file
     * @param file - file to save the object
     * @throws IOException
     */
    public void save(File file) throws IOException {
        mapper.writeValue(file,planet);
    }
}
