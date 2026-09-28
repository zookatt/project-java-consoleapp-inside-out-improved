package org.factoriaf5.project_inside_out;

import org.factoriaf5.project_inside_out.config.AppContainer;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        AppContainer.getConsoleView().start();
    }
}
