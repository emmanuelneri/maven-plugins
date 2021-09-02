package br.com.emmanuelneri.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.maven.plugin.logging.Log;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Exporter implementation any object (T) to Json file
 * @param <T> : any object param
 */
public class JsonExporter<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Log log;
    private final String fileName;
    private final String outputDirectory;

    /**
     * Create a new instance for JsonExporter
     * @param log : Maven plugin Log instance
     * @param outputDirectory : output directory to create exported file
     * @param fileName : exported file name
     */
    public JsonExporter(final Log log, final String outputDirectory, final String fileName) {
        this.log = log;
        this.outputDirectory = outputDirectory;
        this.fileName = fileName;
    }

    /**
     * Export object to file
     * @param report : object to be exported
     * @throws Exception : any error
     */
    public void export(final T report) throws Exception {
        final byte[] content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsBytes(report);

        final String file = outputDirectory + fileName;
        try (final FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(content);

            log.info(file + " generated.");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
