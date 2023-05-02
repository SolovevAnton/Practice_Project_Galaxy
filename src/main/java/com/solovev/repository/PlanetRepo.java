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

    /**
     * Constructor to get planet object form XML
     * @param file path to file with Planet
     * @throws IOException
     */
    public PlanetRepo (File file) throws IOException {
        this.planet = mapper.readValue(file, Planet.class);
    }
}
