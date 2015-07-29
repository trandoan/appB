package kms.techcon;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ControllerB {

    private static class App {
        private String name;

        public App(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class Dependency {
        private String appName;
        private List<Map<String, Object>> deps;

        public Dependency() {
            deps = new ArrayList<>();
        }

        public List<Map<String, Object>>  getDeps() {
            return deps;
        }

        public void setDeps(List<Map<String, Object>>  deps) {
            this.deps = deps;
        }

        public void addDep(Map<String, Object> dep)  {
            deps.add(dep);
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }
    }

    @RequestMapping("/check")
    public App check() {
        return new App("Application B");
    }

    @RequestMapping("/dependencies")
    public Dependency getDependency() throws Exception {
        Dependency dependency = new Dependency();

        dependency.setAppName("Application B");

        URL url = new URL("http://appC.techcon.com:8080/dependencies");
        String data = IOUtils.toString(url, Charsets.UTF_8);
        JsonParser parser = new BasicJsonParser();
        dependency.addDep(parser.parseMap(data));

        return dependency;
    }

}
