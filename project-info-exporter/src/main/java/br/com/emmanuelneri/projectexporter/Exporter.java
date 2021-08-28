package br.com.emmanuelneri.projectexporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.maven.plugin.logging.Log;

import java.io.FileOutputStream;
import java.io.IOException;

public class Exporter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Log log;
    private final String outputDirectory;

    public Exporter(final Log log, final String outputDirectory) {
        this.log = log;
        this.outputDirectory = outputDirectory;
    }

    public void export(final Report report) throws Exception {
        final byte[] content = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsBytes(report);

        final String file = outputDirectory + "/project-info.json";

        try (final FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(content);

            log.info(file + "generated.");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
