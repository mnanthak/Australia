
//////////////// FILE HEADER //////////////////////////
//
// Title:    The Hyperloop program models the logistical software of Hyperloop pods, Hyperloop tracks, and Hyperloop stations
// Course:   CS 300 Fall 2024
//
// Author:   Mohnish Nanthakumar
// Email:    mnanthakumar@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    Harsh Singh
// Partner Email:   hvsingh@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models Track objects as a doubly-linked list for the CS300 Hyperloop project.
 */
public class Track {

    /**
     * A reference to the node containing the first Pod on the track, may be null if the track is empty. Protected for testing purposes only
     */
    protected LinkedNode head;

    /**
     * A reference to the node containing the last Pod on the track, may be null if the track is empty. Protected for testing purposes only
     */
    protected LinkedNode tail;

    /**
     * The number of Pods currently on the track
     */
    private int size;

    /**
     * Adds a passenger to the first available seat in a Pod which matches their class designation.
     * 
     * @param name - the name of the passenger to add
     * @param isFirstClass - whether this passenger is first class
     * @return true if they were successfully added to an available seat of their corresponding class, false if there were no seats or Pods available for their class
     */
    public boolean addPassenger(String name, boolean isFirstClass) {

    }

    /**
     * Searches all Pods in the track to find the given passenger
     * 
     * @param name - the name of the passenger to find
     * @return the index of the Pod this passenger was located in, or -1 if they were not found (or the Track is currently empty)
     */
    public int findPassenger(String name) {

    }

    /**
     * Finds the index of the first non-functional Pod on the track. If all Pods are functioning, returns -1
     * 
     * @return the lowest index of a non-functional Pod on the track, or -1 if all Pods are currently functioning (or the Track is currently empty)
     */
    public int findFirstNonFunctional() {
        if (isEmpty()) {
            return -1;
        }
        else {
            LinkedNode lastNode = head;
            int index = 0;
            try {
                // podClass is declared inside try-catch block because its declaration might throw an error
                int podClass = lastNode.getNext().getPod().getPodClass();
                
                // for every node, if the corresponding Pod is nonfunctional, throw an error
                while (lastNode.getNext() != null) {
                        // if the node's corresponding Pod throws an error, when attempting to access its Pod-class, that means it is nonfunctional
                        podClass = lastNode.getNext().getPod().getPodClass();
                        index++;
                        lastNode = lastNode.getNext();
                }
                // if an error is thrown, return the last index of the pod that was traversed through
            } catch (MalfunctioningPodException e) {
                    return index;
            }
        }
        return -1;
    }

    /**
     * Reports whether the track is currently empty
     * Specified by: isEmpty in interface ListADT<Pod>
     * 
     * @return true if the track is currently empty, false otherwise
     */
    public boolean isEmpty() {

    }

    /**
     * Reports the current number of Pods currently on this track. This number includes both functional and non-functional Pods.
     * Specified by: size in interface ListADT<Pod>
     * 
     * @return the number of Pods on this track
     */
    public int size() {

    }

    /**
     * Removes ALL Pods from this track
     * Specified by: clear in interface ListADT<Pod>
     */
    public void clear() {

    }

    /**
     * Adds a Pod to the track in the correct location. FIRST class Pods should be added to the front of the list; ECONOMY class Pods should be added to the back of the list.
     * If the Pod is not functional, do NOT add it to the track, but also do NOT allow any exception to be thrown. Attempting to add a non-functional Pod should simply not cause the list to change.
     * Specified by: add in interface ListADT<Pod>
     * 
     * @param newElement - the Pod to add to this track
     */
    public void add(Pod newElement) {

    }

    /**
     * Accesses the Pod at a given index
     * Specified by: get in interface ListADT<Pod>
     * 
     * @param index - the index of the Pod to access
     * @return a reference to the Pod at a given index in the track
     * @throws IndexOutOfBoundsException - if the given index is invalid
     */
    public Pod get(int index) {

    }

    /**
     * Determines whether a particular Pod is contained in the track
     * Specified by: contains in interface ListADT<Pod>
     * 
     * @param toFind - the Pod to search for in the track
     * @return true if the Pod is contained in the track, false otherwise
     */
    public boolean contains(Pod toFind) {

    }

    /**
     * Removes a Pod at a given index from the track
     * Specified by: remove in interface ListADT<Pod>
     * 
     * 
     * @param index - the index of the Pod to remove
     * @return a reference to the Pod removed from the track
     * @throws IndexOutOfBoundsException - if the given index is invalid
     */
    public Pod remove(int index) {

    }

    /**
     * Returns a String representation of the entire contents of the track (OUTPUT NOT GRADED). This method traverses the entire track and includes a String representation of each Pod, which you may wish to use for testing purposes.
     * REMEMBER: there is no standard Pod toString() other than Object's implementation. However, each Pod's toString() should be on a separate line so you can count the lines, rather than expect a specific output.
     *  
     * @return a String representation of all Pods currently on the track
     */
    public String toString() {

    }
}