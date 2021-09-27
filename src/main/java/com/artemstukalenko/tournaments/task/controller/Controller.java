package com.artemstukalenko.tournaments.task.controller;

import java.util.Scanner;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.WRONG_INPUT;

public abstract class Controller implements UserInputMatcher {

    protected Scanner scanner = new Scanner(System.in);
    protected UserChoice userCommand;

    public abstract void processUser();

    protected void listenToInput() {

        while (scanner.hasNext()) {
            String currentInput = scanner.next();

            if(inputMatchesCommand(currentInput)) {
                setUserCommand(currentInput.toUpperCase());
                break;
            } else {
                System.out.println(WRONG_INPUT);
            }
        }

    }

    protected abstract void setUserCommand(String input);

    protected abstract void responseToCommand();
}
