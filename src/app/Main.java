package app;

import persistence.FileManager;
import service.EventSystem;
import ui.Menu;

public class Main {
    public static void main(String[] args) {

        EventSystem system = new EventSystem();
        FileManager fileManager = new FileManager();

        fileManager.loadAll(system);

        try {
            new Menu(system).run();
        }finally {
            fileManager.saveAll(system);
        }
    }
}