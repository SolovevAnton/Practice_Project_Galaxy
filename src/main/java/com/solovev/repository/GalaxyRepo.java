package com.solovev.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.solovev.model.Galaxy;

import java.io.File;
import java.io.IOException;

/**
 * Class to serialize or deserialize galaxies
 */
public class GalaxyRepo {
    private Galaxy galaxy;
    private XmlMapper mapper = new XmlMapper();

    public GalaxyRepo() {
    }

    /**
     * Method to serialize Galaxy From XML
     *
     * @param file path to file
     * @throws IOException
     */
    public GalaxyRepo(File file) throws IOException {
        this.galaxy = mapper.readValue(file, Galaxy.class);
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    /**
     * Method to save object to XML file
     *
     * @param file - file to save the object
     * @throws IOException
     */
    public void save(File file) throws IOException {
        mapper.writeValue(file, galaxy);
    }
}
