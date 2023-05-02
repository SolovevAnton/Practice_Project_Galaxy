package com.solovev.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.solovev.model.Universe;

import java.io.File;
import java.io.IOException;

/**
 * Class to deserialize or serialize Universe
 * Unknown fields are ignored
 */
public class UniverseRepo {
    private Universe universe;
    private XmlMapper mapper = new XmlMapper();

    public UniverseRepo(File file) throws IOException {
        this.universe = mapper
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .readValue(file, Universe.class);
    }

    public Universe getUniverse() {
        return universe;
    }
    /**
     * Method to save object to XML file
     *
     * @param file - file to save the object
     * @throws IOException
     */
    public void save(File file) throws IOException {
        mapper.writeValue(file, universe);
    }
}
