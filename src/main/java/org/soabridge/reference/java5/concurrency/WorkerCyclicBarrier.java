package org.soabridge.reference.java5.concurrency;

import org.soabridge.reference.general.threads.LoggingRunnable;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class WorkerCyclicBarrier implements LoggingRunnable {

    private long patience;
    private CyclicBarrier barrier;
    private String name;

    public WorkerCyclicBarrier(String name, long patience, CyclicBarrier barrier) {
        this.name = name;
        this.patience = patience;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            for(int i=1; i<=10; i++) {
                threadMessage(i + ". \"" + name + "\" Cycle");
                Thread.sleep(patience);
            }
            threadMessage("\"" + name + "\" Waiting for others to arrive");
            barrier.await();
            threadMessage("\"" + name + "\" Done!");
        }
        catch (InterruptedException e) {
            threadMessage("\"" + name + "\" Received Interrupt!");
        }
        catch (BrokenBarrierException e) {
            threadMessage("\"" + name + "\" Barrier was broken!");
        }
    }
}
