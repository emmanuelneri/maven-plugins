package br.com.emmanuelneri.projectexporter;

import br.com.emmanuelneri.core.JsonExporter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "export", defaultPhase = LifecyclePhase.COMPILE)
public class Processor extends AbstractMojo {

    private static final String FILENAME = "/project-info.json";

    @Parameter(property = "project", readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("starting project exporter");
        getLog().info("project: " + project.getArtifactId());

        try {
            final Report report = new Report(project.getArtifactId(), project.getVersion());
            project.getDependencies().forEach(report::addDependency);
            final String outputDirectory = project.getBuild().getOutputDirectory();
            new JsonExporter<Report>(getLog(), outputDirectory, FILENAME).export(report);
        } catch (Exception ex) {
            getLog().error("fail to create file", ex);
        }

        getLog().info("finish project exporter");
    }
}
