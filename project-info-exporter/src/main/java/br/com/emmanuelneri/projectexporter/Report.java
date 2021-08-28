package br.com.emmanuelneri.projectexporter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@ToString
@Getter
class Report {

    private final String name;
    private final String version;

    private final List<Dependency> dependencies = new LinkedList<>();

    public Report(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public void addDependency(org.apache.maven.model.Dependency dependency) {
        this.dependencies.add(new Dependency(
                dependency.getGroupId(),
                dependency.getArtifactId(),
                dependency.getVersion()
        ));
    }

    @AllArgsConstructor
    @ToString
    @Getter
    static class Dependency {
        private String groupId;
        private String artifactId;
        private String version;
    }
}
