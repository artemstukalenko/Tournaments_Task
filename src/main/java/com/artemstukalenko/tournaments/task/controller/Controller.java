package com.artemstukalenko.tournaments.task.controller;

import java.util.Scanner;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.WRONG_INPUT;
import static com.artemstukalenko.tournaments.task.controller.RegexContainer.*;

public abstract class Controller implements UserInputMatcher {

    protected Scanner scanner = new Scanner(System.in);
    protected UserChoice userCommand;

    public abstract void processUser();

    protected void listenToInputCommand() {

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

    protected String listenToInputForString() {

        while (scanner.hasNext()) {
            String currentInput = scanner.next();

            if(currentInput.matches(ONLY_LETTERS)) {
                return currentInput;
            } else {
                System.out.println(WRONG_INPUT);
            }
        }

        return "";
    }

    protected String listenToInput() {

        while (scanner.hasNext()) {
            String currentInput = scanner.next();

            if(currentInput.length() > 4) {
                return currentInput;
            } else {
                System.out.println(WRONG_INPUT);
            }
        }

        return "";
    }

    protected abstract void setUserCommand(String input);

    protected abstract void responseToCommand();
}
