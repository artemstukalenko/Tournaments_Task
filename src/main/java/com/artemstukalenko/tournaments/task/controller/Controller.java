package com.artemstukalenko.tournaments.task.controller;

import java.util.Scanner;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.WRONG_INPUT;

public abstract class Controller implements UserInputMatcher {

    protected Scanner scanner = new Scanner(System.in);
    protected UserChoice userCommand;

    public abstract void processUser();

    protected abstract void responseToCommand();

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

    protected int listenToInputForID() {
        int desiredId = 0;

        while (scanner.hasNext()) {

            try {
                desiredId = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println(WRONG_INPUT);
                continue;
            }
        }

        return desiredId;
    }

    protected abstract void setUserCommand(String input);

}
