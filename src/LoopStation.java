import java.util.NoSuchElementException;

public class LoopStation {
    /**
     * A track containing all launched Pods. This track should be periodically swept for malfunctioning Pods, which will need to be removed.
     */
    protected Track launched;
    
    /**
     * A track containing all newly-created, unlaunched first-class Pods. New Pods will be added to the FRONT of this track, so Pods to be launched should be removed from the END of this track.
     */
    protected Track waitingFirst;

    /**
     * A track containing all newly-created, unlaunched economy-class Pods. New Pods will be added to the END of this track, so Pods to be launched should be removed from the FRONT of this track.
     */
    protected Track waitingEconomy;


    /**
    * Creates a new LoopStation with empty tracks
    */
    public LoopStation() {}

    /**
     * Creates a new Pod of the appropriate class and loads it onto the correct waiting track. This method also returns a reference to this newly-created Pod so that passengers may board
     * 
     * @param capacity - the capacity of the new Pod to create
     * @param isFirstClass - whether the new Pod is a first class Pod
     * @return a reference to the newly-created Pod
     */
    public Pod createPod(int capacity, boolean isFirstClass) {
        // create Pod object
        int podClass = (isFirstClass)? Pod.FIRST : Pod.ECONOMY;
        Pod pod = new Pod(capacity, podClass);
        // add the Pod to the correct waiting Track
        if (isFirstClass) {
            waitingFirst.add(pod);
        }
        else {
            waitingEconomy.add(pod);
        }
        return pod;
    }

    /**
     * Launches the highest-priority, least-recently-created Pod that is currently waiting:
     * FIRST class Pods are launched first
     * Then ECONOMY class Pods are launched
     * Remember that new FIRST class Pods are added to the beginning of the FIRST class, while new ECONOMY class Pods are added to the end. The least-recently created Pod of the correct class should always be launched first.
     * 
     * @throws NoSuchElementException - if no Pods are waiting to launch
     */
    public void launchPod() throws NoSuchElementException {
        // if there are first-class pods waiting to launch, launch one
        if (waitingFirst.size() > 0) {
        launched.add(waitingFirst.get(waitingFirst.size() - 1));
        waitingFirst.remove(waitingFirst.size() - 1);
        }
        // if there are economy-class pods waiting to launch, launch one
        else if (waitingEconomy.size() > 0) {
        launched.add(waitingEconomy.get(0));
        waitingEconomy.remove(0);
        }
        // if there are no pods waiting to launch, throw an exception
        else {
            throw new NoSuchElementException("no Pods are waiting to launch");
        }
    }

    /**
     * Finds and removes any malfunctioning Pods from the launched track
     * 
     * @return the total number of pods which were removed
     */
    public int clearMalfunctioning() {
        // if there are malfunctioning pods in the launched track, remove them and count how many are removed
        int numRemoved = 0;
        while (launched.findFirstNonFunctional() != -1) {
            launched.remove(launched.findFirstNonFunctional);
            numRemoved++;
        }
        return numRemoved;
    }

    /**
     * Reports the total number of first and economy class Pods which are waiting to be launched
     * 
     * @return the total number of unlaunched pods at this LoopStation
     */
    public int getNumWaiting() {
        return waitingFirst.size() + waitingEconomy.size();
    }

    /**
     * Reports the total number of Pods, functional or non-functional, which are currently running on the launched track
     * 
     * @return the total number of Pods on the launched track
     */
    public int getNumLaunched() {
        return launched.size();
    }

    /**
     * Reports the total number of passengers in functional Pods across all tracks, waiting and launched
     * 
     * @return the total number of passengers in functional Pods currently being served by this LoopStation
     */
    public int getNumPassengers() {
        int total = 0;
        // add the total number of passengers across all waiting first-class pods
        for (int i = 0; i < waitingFirst.size(); i++) {
            if (waitingFirst.get(i).isFunctional()) {
                total += waitingFirst.get(i).getNumPassengers();
            }
        }
        // add the total number of passengers across all waiting economy-class pods
        for (int i = 0; i < waitingEconomy.size(); i++) {
            if (waitingEconomy.get(i).isFunctional()) {
                total += waitingEconomy.get(i).getNumPassengers();
            }
        }
        // add the total number of passengers across all launched pods
        for (int i = 0; i < launched.size(); i++) {
            if (launched.get(i).isFunctional()) {
                total += launched.get(i).getNumPassengers();
            }
        }
        return total;
    }
}
