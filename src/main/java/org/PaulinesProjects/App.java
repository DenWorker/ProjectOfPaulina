package org.PaulinesProjects;

import org.PaulinesProjects.projectPlanner.executers.ExecuterOfProjectPlanner;
import org.PaulinesProjects.projectPlanner.executers.SimpleExecuterOfProjectPlannerImpl;
import org.PaulinesProjects.projectPlanner.planners.SimplePlannerImpl;

public class App {
    public static void main(String[] args) {
        ExecuterOfProjectPlanner executerOfProjectPlanner = new SimpleExecuterOfProjectPlannerImpl(new SimplePlannerImpl());
        executerOfProjectPlanner.test();
        executerOfProjectPlanner.run();
    }
}
