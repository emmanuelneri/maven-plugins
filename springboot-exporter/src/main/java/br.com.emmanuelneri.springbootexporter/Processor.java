package br.com.emmanuelneri.springbootexporter;

import br.com.emmanuelneri.core.JsonExporter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.reflections.Reflections;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Spring Boot processor
 *
 * process compiled code to export Spring Boot information
 */
@Mojo(name = "export", defaultPhase = LifecyclePhase.COMPILE)
public class Processor extends AbstractMojo {

    private static final String FILENAME = "/springboot-info.json";

    @Parameter(property = "project", readonly = true)
    private MavenProject project;

    @Override
    public void execute() {
        getLog().info("starting springboot exporter");
        getLog().info("project: " + project.getArtifactId());

        try {
            final URLClassLoader classLoader = getClassLoader(project.getBuild().getOutputDirectory());
            final Reflections reflections = new Reflections(classLoader);

            final ReportBuilder reportBuilder = new ReportBuilder(project, reflections);
            reportBuilder.populateMainClass();
            reportBuilder.populateController();

            final String outputDirectory = project.getBuild().getOutputDirectory();
            new JsonExporter<Report>(getLog(), outputDirectory, FILENAME).export(reportBuilder.getReport());

        } catch (Exception ex) {
            getLog().error("fail to create file", ex);
        }

        getLog().info("finish springboot exporter");
    }

    private URLClassLoader getClassLoader(final String outputDirectory) throws MalformedURLException {
        final File classesDirectory = new File(outputDirectory);
        final URL classesUrl = classesDirectory.toURI().toURL();
        final URL[] classesUrls = new URL[]{classesUrl};
        return URLClassLoader.newInstance(classesUrls, getClass().getClassLoader());
    }
}