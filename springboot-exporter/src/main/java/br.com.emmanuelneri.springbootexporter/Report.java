package br.com.emmanuelneri.springbootexporter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@ToString
@Getter
class Report {

    private final String name;
    private final String version;

    @Setter
    private String mainClass;

    private final List<Controller> controllers = new LinkedList<>();

    public Report(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public void addController(final Controller controller) {
        this.controllers.add(controller);
    }

    @AllArgsConstructor
    @ToString
    @Getter
    static class Controller {
        private String name;
        private String[] mapping;
    }
}