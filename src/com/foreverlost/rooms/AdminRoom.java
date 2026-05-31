package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * Small office west of the Scientists Lab. The lead scientist quizzes the player before allowing a search.
 */
public class AdminRoom extends Room {
    private final String name = "Admin Room";
    private final String description = """
            You open the doors and find yourself in a small office room. In front of you a scientist
            sits at his desk, typing away on his laptop. He looks up at you. His face shifts from
            annoyance to suspicion.
            """;

    public AdminRoom(HashMap<Directions, Room> adjacentRooms) {
        super(adjacentRooms);
    }

    @Override
    public boolean canEnterRoom() {
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Starts the scientist's quiz when the player enters the admin room.
     */
    @Override
    protected void startDialogue() {
        System.out.println("""
                
                "I don't recognise you, are you sure you are from this facility? Hmmph, okay then
                answer these questions so I can tell for sure that you are indeed one of our scientists."
                """);
        askQuestionOne();
    }

    /**
     * Asks the first quiz question and handles the player's response.
     */
    private void askQuestionOne() {
        System.out.println("\"1. What is the name for the central part of an atom?\"");
        System.out.println("(A): The Electron Cloud");
        System.out.println("(B): The Nucleus");
        System.out.println("(C): The proton");
        System.out.println("(D): The neutron");

        char response = getOptionFromUser();

        switch (response) {
            case 'B' -> askQuestionTwo();
            case 'A', 'C', 'D' -> failQuiz();
            default -> {
                System.out.println("Invalid option.");
                askQuestionOne();
            }
        }
    }

    /**
     * Asks the second quiz question and handles the player's response.
     */
    private void askQuestionTwo() {
        System.out.println("\"2. What is the process by which plants convert sunlight into energy?\"");
        System.out.println("(A): Respiration");
        System.out.println("(B): Transpiration");
        System.out.println("(C): Photosynthesis");
        System.out.println("(D): Fermentation");

        char response = getOptionFromUser();

        switch (response) {
            case 'C' -> askQuestionThree();
            case 'A', 'B', 'D' -> failQuiz();
            default -> {
                System.out.println("Invalid option.");
                askQuestionTwo();
            }
        }
    }

    /**
     * Asks the third quiz question and handles the player's response.
     */
    private void askQuestionThree() {
        System.out.println("\"3. What is an alpha particle primarily composed of?\"");
        System.out.println("(A): A high-speed electron");
        System.out.println("(B): A helium nucleus");
        System.out.println("(C): A high-energy proton");
        System.out.println("(D): A single proton");

        char response = getOptionFromUser();

        switch (response) {
            case 'B' -> passQuiz();
            case 'A', 'C', 'D' -> failQuiz();
            default -> {
                System.out.println("Invalid option.");
                askQuestionThree();
            }
        }
    }

    /**
     * Called when the player answers a quiz question incorrectly.
     * Prints the capture ending and exits the game.
     */
    private void failQuiz() {
        System.out.println("""
                
                The scientist's head falls back into a laughing fit, the sound echoing off the walls
                of the small office. He continues to ask you questions; you don't know if you're getting
                them right or not, but after a few minutes the door behind you bursts open and half a
                dozen guards rush inside.
                
                Before you can react, four of them jump on you, pinning you to the ground. You struggle
                and squirm but it's no use. James Bond is captured and when brought face to face with
                Jane Sever, she greets him with a bullet in the skull.
                """);
        System.exit(0);
    }

    /**
     * Called when the player passes all three quiz questions.
     * Shows the search office options.
     */
    private void passQuiz() {
        System.out.println("""
                
                The scientist gives a nod of approval. "My apologies for doubting you, but it's better
                to be safe than sorry, y'know?" He gets up from his desk and leaves the room. You can
                hear him barking orders to the other scientists. He'll be back any minute.
                """);
        searchOffice();
    }

    /**
     * Shows navigation and search options after the player passes the quiz.
     * Awards the L2 keycard on first search.
     */
    private void searchOffice() {
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.EAST));
        System.out.println("(B): Search the room");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.EAST);
            case 'B' -> {
                if (!isSearched()) {
                    System.out.println("You search the office and find a level 2 access keycard in a drawer.");
                    getPlayer().setL2KeycardTrue();
                    setSearchedTrue();
                } else {
                    System.out.println("You have already searched this room.");
                }
                searchOffice();
            }
            default -> {
                System.out.println("Invalid option.");
                searchOffice();
            }
        }
    }
}
