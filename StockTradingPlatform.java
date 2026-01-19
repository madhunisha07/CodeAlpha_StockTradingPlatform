import java.util.ArrayList;
import java.util.Scanner;
class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}


class PortfolioItem {
    String symbol;
    int quantity;

    PortfolioItem(String symbol, int quantity) {
        this.symbol = symbol;
        this.quantity = quantity;
    }
}


public class StockTradingPlatform {

    static ArrayList<Stock> marketStocks = new ArrayList<>();
    static ArrayList<PortfolioItem> portfolio = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        marketStocks.add(new Stock("AAPL", 150.0));
        marketStocks.add(new Stock("GOOG", 2800.0));
        marketStocks.add(new Stock("TSLA", 700.0));

        int choice;

        do {
            System.out.println("\n==== STOCK TRADING PLATFORM ====");
            System.out.println("1. View Market Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewMarketStocks();
                    break;
                case 2:
                    buyStock(sc);
                    break;
                case 3:
                    sellStock(sc);
                    break;
                case 4:
                    viewPortfolio();
                    break;
                case 5:
                    System.out.println("Thank you for using Stock Trading Platform!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
    static void viewMarketStocks() {
        System.out.println("\nAvailable Stocks:");
        for (Stock stock : marketStocks) {
            System.out.println(stock.symbol + " - ₹" + stock.price);
        }
    }
    static void buyStock(Scanner sc) {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = sc.next().toUpperCase();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        for (Stock stock : marketStocks) {
            if (stock.symbol.equals(symbol)) {
                for (PortfolioItem item : portfolio) {
                    if (item.symbol.equals(symbol)) {
                        item.quantity += qty;
                        System.out.println("Stock purchased successfully!");
                        return;
                    }
                }
                portfolio.add(new PortfolioItem(symbol, qty));
                System.out.println("Stock purchased successfully!");
                return;
            }
        }
        System.out.println("Stock not found!");
    }

    static void sellStock(Scanner sc) {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = sc.next().toUpperCase();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        for (PortfolioItem item : portfolio) {
            if (item.symbol.equals(symbol)) {
                if (item.quantity >= qty) {
                    item.quantity -= qty;
                    System.out.println("Stock sold successfully!");
                    if (item.quantity == 0) {
                        portfolio.remove(item);
                    }
                } else {
                    System.out.println("Not enough stocks to sell!");
                }
                return;
            }
        }
        System.out.println("You do not own this stock!");
    }

    static void viewPortfolio() {
        if (portfolio.isEmpty()) {
            System.out.println("Your portfolio is empty.");
            return;
        }

        System.out.println("\nYour Portfolio:");
        for (PortfolioItem item : portfolio) {
            System.out.println(item.symbol + " - Quantity: " + item.quantity);
        }
    }
}
