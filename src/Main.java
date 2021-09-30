import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {

        Thread producerThread = new Thread(() -> {
            produce();
        });
        Thread consumerThread = new Thread(() -> {
            consume();
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }

    private static void produce() {
        Random random = new Random();

        while (true) {
            try {
                //put Belirtilen öğeyi bu kuyruğun kuyruğuna ekler ve sıra doluysa boş yerin açılmasını bekler.
                queue.put(random.nextInt(100));
            } catch (InterruptedException e) {
            }
        }
    }

    private static void consume() {
        while (true) {
            try {
                Thread.sleep(100);
                //take() Gerekirse bir öğe kullanılabilir hale gelene kadar bekleyerek bu kuyruğun başını alır ve kaldırır.
                Integer value = queue.take();
                System.out.println("Alınan sayı: " + value + ",Kuyrugun boyutu: " + queue.size());
            } catch (InterruptedException e) {
            }
        }
    }

}
