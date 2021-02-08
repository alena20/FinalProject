package com.epam.finalproject.util;

import com.epam.finalproject.exception.PropertyReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final Logger LOGGER = LogManager.getLogger(PropertiesReader.class);

    private PropertiesReader() { }

    public static Properties readProperties(String path) throws PropertyReaderException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            LOGGER.info("Properties were successfully read form '{}'", path);
            return properties;
        } catch (IOException e) {
            throw new PropertyReaderException("Impossible to read properties!");
        }
    }
}
