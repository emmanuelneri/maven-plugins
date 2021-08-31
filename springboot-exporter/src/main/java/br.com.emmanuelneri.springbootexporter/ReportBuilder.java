package br.com.emmanuelneri.springbootexporter;

import lombok.Getter;
import org.apache.maven.project.MavenProject;
import org.reflections.Reflections;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportBuilder {

    @Getter
    private final Report report;
    private final Reflections reflections;

    public ReportBuilder(final MavenProject project, final Reflections reflections) {
        this.report = new Report(project.getArtifactId(), project.getVersion());
        this.reflections = reflections;
    }

    public void populateMainClass() {
        this.report.setMainClass(getClass(this.reflections, SpringBootApplication.class)
                .map(typesAnnotatedWith -> typesAnnotatedWith.stream()
                        .findFirst()
                        .map(Class::getCanonicalName)
                        .orElseThrow(() -> new RuntimeException("spring application must be a class with @SpringBootApplication")))
                .orElse(""));

    }

    public void populateController() {
        getClass(this.reflections, Controller.class)
                .map(typesAnnotatedWith -> typesAnnotatedWith.stream()
                        .filter(restController -> !restController.getPackageName().startsWith("org.springframework"))
                        .map(controller -> {
                            final RequestMapping requestMapping = controller.getAnnotation(RequestMapping.class);
                            return new Report.Controller(controller.getName(), requestMapping.value());
                        })
                        .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList())
                .forEach(report::addController);
    }

    private Optional<Set<Class<?>>> getClass(final Reflections reflections, final Class<? extends Annotation> annotation) {
        final Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(annotation);
        if (typesAnnotatedWith == null || typesAnnotatedWith.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(typesAnnotatedWith);
    }
}