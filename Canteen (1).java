import java.util.Scanner;

public class Canteen {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] itemNames = {"Rice", "Burger", "Fried Chicken", "Spaghetti", "Iced Tea"};
        double[] itemPrices = {25.00, 65.00, 75.00, 55.00, 20.00};

        int totalQuantity = 0;
        double totalBeforeDeduction = 0.0;
        double totalDeduction = 0.0;
        double totalFinalAmount = 0.0;

        char orderAgain = 'Y';

        System.out.println("=======================================");
        System.out.println("           CANTEEN MENU");
        System.out.println("=======================================");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.println((i + 1) + ". " + itemNames[i] + " - $" + itemPrices[i]);
        }
        System.out.println("=======================================");

        while (orderAgain == 'Y' || orderAgain == 'y') {

            System.out.println();
            System.out.print("Enter item number: ");
            int itemNumber = input.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            String studentAnswer = input.next();
            boolean isStudent = studentAnswer.equalsIgnoreCase("Y");

            boolean validItem = (itemNumber >= 1 && itemNumber <= itemNames.length);
            boolean validQuantity = (quantity >= 1 && quantity <= 10);

            if (!validItem || !validQuantity) {
                System.out.println(">> Invalid order. Please check the item number and quantity.");
            } else {
                double price = itemPrices[itemNumber - 1];
                double purchaseAmount = price * quantity;

                double deductionRate;
                if (isStudent && purchaseAmount >= 500) {
                    deductionRate = 0.15;
                } else if (purchaseAmount >= 500) {
                    deductionRate = 0.05;
                } else if (isStudent) {
                    deductionRate = 0.10;
                } else {
                    deductionRate = 0.0;
                }

                double deductionAmount = purchaseAmount * deductionRate;
                double finalAmount = purchaseAmount - deductionAmount;

                System.out.println(">> " + quantity + " x " + itemNames[itemNumber - 1]
                        + " = $" + purchaseAmount);
                System.out.println(">> Deduction: " + (int) (deductionRate * 100) + "% (-$" + deductionAmount + ")");
                System.out.println(">> Amount to pay for this order: $" + finalAmount);

                totalQuantity += quantity;
                totalBeforeDeduction += purchaseAmount;
                totalDeduction += deductionAmount;
                totalFinalAmount += finalAmount;
            }

            System.out.print("\nDo you want to order again? (Y/N): ");
            orderAgain = input.next().charAt(0);
        }

        System.out.println();
        System.out.println("=======================================");
        System.out.println("           ORDER SUMMARY");
        System.out.println("=======================================");
        System.out.println("Total quantity of items purchased : " + totalQuantity);
        System.out.println("Total amount before deductions     : $" + totalBeforeDeduction);
        System.out.println("Total deduction                    : $" + totalDeduction);
        System.out.println("Final amount to pay                : $" + totalFinalAmount);
        System.out.println("=======================================");

        input.close();
    }
}
