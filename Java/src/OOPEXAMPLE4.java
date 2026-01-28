import java.util.Random;

// 1. RECORDS (Modern Java Feature)
// Instead of writing a class with 'private fields', 'getters', 'constructors', and 'toString',
// we just write 'record'. Java generates all that code for us automatically.
// This one line replaces ~20 lines of standard code!
record Stock(String symbol, double price) {}

// 2. RUNNABLE (The "Task")
// This class defines a job that can run in the BACKGROUND.
class MarketTicker implements Runnable {
    
    // Volatile ensures all threads see the latest value immediately
    private volatile boolean running = true; 

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        Random rand = new Random();
        String[] symbols = {"AAPL", "GOOGL", "AMZN", "MSFT"};

        System.out.println(">> Ticker started... (Background Thread)");

        // Keep updating prices until told to stop
        while (running) {
            
            // Pick a random stock
            String symbol = symbols[rand.nextInt(symbols.length)];
            double price = 100 + (rand.nextDouble() * 50); // Random price 100-150
            
            // Create our Record
            Stock s = new Stock(symbol, price);
            
            // 3. FORMATTED PRINTING (printf)
            // %.2f means "decimal number with 2 digits".
            System.out.printf("[LIVE FEED] %s: $%.2f%n", s.symbol(), s.price());

            // 4. THREAD SLEEP
            // Pause this specific thread for 500ms (half a second)
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                System.out.println("Ticker interrupted!");
            }
        }
        System.out.println(">> Ticker stopped.");
    }
}

// MAIN CLASS
public class OOPEXAMPLE4 {

    public static void main(String[] args) {
        System.out.println("--- TRADING SYSTEM BOOT ---");

        // Create the task
        MarketTicker task = new MarketTicker();

        // 5. THREADS (The "Worker")
        // We wrap the task in a Thread object and press 'start'.
        // This splits the program execution into two parallel lines.
        Thread backgroundWorker = new Thread(task);
        backgroundWorker.start();

        // --- THE MAIN PROGRAM CONTINUES ---
        // Notice that this loop runs AT THE SAME TIME as the [LIVE FEED] above.
        
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main System analyzing data... " + i);
                
                // The main system is slower (waiting 1 second)
                Thread.sleep(1000); 
            }
            
            System.out.println("Analysis Complete. Stopping feed.");
            task.stop(); // Tell the background thread to finish
            
            // Wait for the worker to officially finish
            backgroundWorker.join(); 

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- SYSTEM SHUTDOWN ---");
    }
}