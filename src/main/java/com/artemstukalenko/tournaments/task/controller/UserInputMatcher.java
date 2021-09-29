package com.artemstukalenko.tournaments.task.controller;

public interface UserInputMatcher {

    default boolean inputMatchesCommand(String input) {
        return input.equalsIgnoreCase("R") || input.equalsIgnoreCase("U")
                || input.equalsIgnoreCase("A") || input.equalsIgnoreCase("D")
                || input.equalsIgnoreCase("P") || input.equalsIgnoreCase("T")
                || input.equalsIgnoreCase("TP") || input.equalsIgnoreCase("TR")
                ||input.equalsIgnoreCase("S");
    }

}
