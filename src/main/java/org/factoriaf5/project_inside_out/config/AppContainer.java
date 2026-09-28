package org.factoriaf5.project_inside_out.config;

import org.factoriaf5.project_inside_out.controller.MomentController;
import org.factoriaf5.project_inside_out.repository.MapMomentRepository;
import org.factoriaf5.project_inside_out.repository.MomentRepository;
import org.factoriaf5.project_inside_out.service.MomentService;
import org.factoriaf5.project_inside_out.view.ConsoleView;

public final class AppContainer {
    private static final MomentRepository REPOSITORY = new MapMomentRepository();
    private static final MomentService SERVICE = new MomentService(REPOSITORY);
    private static final MomentController CONTROLLER = new MomentController(SERVICE);
    private static final ConsoleView VIEW = new ConsoleView(CONTROLLER);

    private AppContainer() {
    }

    public static MomentController getMomentController() {
        return CONTROLLER;
    }

    public static ConsoleView getConsoleView() {
        return VIEW;
    }
}