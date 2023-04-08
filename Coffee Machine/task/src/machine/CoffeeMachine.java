package machine;

import java.util.Objects;
import java.util.Scanner;

/**
 * This is a simulation of CoffeeMachine.
 * At the start coffee machine has some resources: water, milk, coffee, cups and money.
 * Then user can choose point from menu:
 * - buy - can buy the coffee with choosing type of coffee (cappuccino, latte, americano);
 * - fill - add resources;
 * - take - take money;
 * - remaining - show available resources (water, milk, coffee, cups, money);
 * - exit - finishes the program.
 *
 * @author Kamilla Burmistrova
 */

public class CoffeeMachine {

    /**
     * scanner object and specified data source for it (string with text)
     */
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * available resource - coffee
     */
    private static int coffee;
    /**
     * available resource in coffee machine - cups
     */
    private static int cups;
    /**
     * available resource in coffee machine - milk
     */
    private static int milk;
    /**
     * available resource in coffee machine - money
     */
    private static int money;
    /**
     * available resource in coffee machine - water
     */
    private static int water;
    /**
     * selected point of menu - action
     */
    private static String userChoiceAction = "";
    /**
     * selected point of menu - coffee type
     */
    private static String userChoiceCoffeeType = "";

    /**
     * main method
     */
    public static void main(String[] args) {
        /**
         * set the number of resources in coffee machine at the start
         */
        water = 400;
        milk = 540;
        coffee = 120;
        money = 550;
        cups = 9;

        runCoffeeMachine();
    }

    private static void runCoffeeMachine() {
        do {
            getUserChoice();
            switch (userChoiceAction) {
                case "buy" -> {
                    askCoffeeType();
                    buyCoffee();
                }
                case "fill" -> addResources();
                case "take" -> showMoneyValue();
                case "remaining" -> showResources(water, milk, coffee, cups, money);
                case "exit" -> System.exit(0);
            }
            System.out.println();
        } while (!Objects.equals(userChoiceAction, "exit"));
    }

    /**
     * method shows available resources
     *
     * @param water - number water in coffee machine
     * @param milk - number water in coffee machine
     * @param coffee - number water in coffee machine
     * @param cups - number water in coffee machine
     * @param money - number water in coffee machine
     */
    private static void showResources(int water, int milk, int coffee, int cups, int money) {
        System.out.println("\nThe coffee machine has:\n" +
                water + " ml of water\n" +
                milk + " ml of milk\n" +
                coffee + " g of coffee beans\n" +
                cups + " disposable cups\n" +
                "$" + money + " of money");
    }

    /**
     * method get the user choice from action menu
     */
    private static void getUserChoice() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        userChoiceAction = scanner.next();
    }

    /**
     * method get the user choice from coffee menu
     */
    private static void askCoffeeType() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        userChoiceCoffeeType = scanner.next();
    }

    /**
     * method updates resources in coffee machine after buying of certain coffee type
     */
    private static void buyCoffee() {
        switch (userChoiceCoffeeType) {
            case "1" -> checkResources(250, 0, 16, 1, 4);
            case "2" -> checkResources(350, 75, 20, 1, 7);
            case "3" -> checkResources(200, 100, 12, 1, 6);
            case "back" -> runCoffeeMachine();
        }
    }

    /**
     * method updates resources in coffee machine after buying of coffee
     *
     * @param waterPerCoffee - water needed per one beverage
     * @param milkPerCoffee - milk needed per one beverage
     * @param coffeePerCoffee - coffee needed per one beverage
     * @param cupsPerCoffee - cups needed per one beverage
     * @param moneyPerCoffee - money received per one beverage
     */
    private static void checkResources(int waterPerCoffee, int milkPerCoffee, int coffeePerCoffee, int cupsPerCoffee,
                                       int moneyPerCoffee) {
        if (cups >= 1 && water >= waterPerCoffee && coffee >= coffeePerCoffee) {
            System.out.println("I have enough resources, making you a coffee!");
            water -= waterPerCoffee;
            milk -= milkPerCoffee;
            coffee -= coffeePerCoffee;
            cups -= 1;
            money += moneyPerCoffee;
        } else if (cups < 1) {
            showNotEnoughResources("cups");
        } else if (water < waterPerCoffee) {
            showNotEnoughResources("water");
        } else if (coffee < coffeePerCoffee) {
            showNotEnoughResources("coffee");
        } else {
            showNotEnoughResources("milk");
        }
    }

    /**
     * method shows not available resource
     */
    private static void showNotEnoughResources(String resource) {
        System.out.println("Sorry, not enough " + resource + "!");
    }

    /**
     * method shows how much money you got from coffee machine
     */
    private static void showMoneyValue() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    /**
     * method asks how much resources to add
     */
    private static void askHowMuchToAdd(String resource) {
        System.out.println("Write how many ml of " + resource + " you want to add:");
    }

    /**
     * method requests and updated resources after adding
     */
    private static void addResources() {
        askHowMuchToAdd("water");
        water += scanner.nextInt();
        askHowMuchToAdd("milk");
        milk += scanner.nextInt();
        askHowMuchToAdd("coffee");
        coffee += scanner.nextInt();
        askHowMuchToAdd("cups");
        cups += scanner.nextInt();
    }
}