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

import java.util.NoSuchElementException;

/**
 * This class tests the LoopStation class, and by extension, the Track class
 */
public class LoopStationTester {
  
  /**
   * Checks the correctness of the createPod() method. This method should:
   * - create a Pod with the given capacity and podClass
   * - add it to the correct end of the correct Track in the LoopStation
   * - return a reference (shallow copy) to that Pod
   * Note that the tracks in LoopStation are protected, so you may access them directly for testing
   * purposes
   * @return true if createPod() is functioning correctly, false otherwise
   */
  public static boolean testCreatePod() {
    LoopStation ls = new LoopStation();
    Pod pod = ls.createPod(50, false);

    // check if the Pod instance's attributes are correct, and make sure it doesn't throw an unnecessary exception
    try {
      if (pod.getCapacity() != 50)
        return false;
      if (pod.getPodClass() != Pod.ECONOMY)
        return false;
    } catch (MalfunctioningPodException e) {
      // method should not be throwing an exception
      return false;
    }
    if (ls.waitingEconomy.get(ls.waitingEconomy.size() - 1) != pod)
      return false;
    return true;
  }
  
  /**
   * Checks the correctness of the launchPod() method. This method should:
   * - throw a NoSuchElementException if no pods are waiting to launch
   * - launch first class pods from the END of the waitingFirst track
   * - launch economy class pods from the BEGINNING of the waitingEconomy track
   * - launch ALL first class pods before launching ANY economy class pods
   * Note that the tracks in LoopStation are protected, so you may access them directly for testing
   * purposes
   * @return true if launchPod() is functioning correctly, false otherwise
   */
  public static boolean testLaunchPod() {

    // check if launchPad() method throws the correct exception
    LoopStation ls1 = new LoopStation();
    try {
      ls1.launchPod();
      return false;
    }
    catch (NoSuchElementException n) {}
    catch (Exception e) {
      return false;
    }

    // check if first class pods are being launched properly
    LoopStation ls2 = new LoopStation();
    Pod podFirstClass1 = ls2.createPod(50, true);
    Pod podFirstClass2 = ls2.createPod(50, true);
    ls2.launchPod();
    if (ls2.waitingFirst.get(0) != podFirstClass2)
      return false;

    // check if economy class pods are being launched properly
    LoopStation ls3 = new LoopStation();
    Pod podEconClass1 = ls3.createPod(50, false);
    Pod podEconClass2 = ls3.createPod(50, false);
    ls3.launchPod();
    if (ls3.waitingEconomy.get(0) != podEconClass2)
    return false;
    
    // check if first class pods are being launched before economy class pods
    LoopStation ls4 = new LoopStation();
    Pod podFirstClass = ls4.createPod(50, true);
    Pod podEconClass = ls4.createPod(50, false);
    ls4.launchPod();
    if (ls4.waitingFirst.isEmpty() && !ls4.waitingEconomy.isEmpty())
      return false;

    return true;
  }
  
  /**
   * Checks the correctness of the clearMalfunctioning() method. This method should:
   * - repeatedly check the launched track for malfunctioning pods
   * - remove those pods correctly
   * - report the number of pods it removed once there are no longer any malfunctioning pods
   * 
   * Things to consider when you are testing:
   * 
   * - there is a protected setNonFunctional() method you may use for testing purposes to ensure
   *   that at least one pod is non-functional
   *   
   * - calling isFunctional() on a Pod may cause it to malfunction! You should come up with an
   *   alternate way to check whether a Pod is functional, if you have not already.
   *   
   * - verify that the difference in number of pods from before the method was called and after
   *   the method was called is equal to the number that it reported
   *   
   * @return true if clearMalfunctioning() is functioning correctly, false otherwise
   */
  public static boolean testClearMalfunctioning() {
    LoopStation ls = new LoopStation();
    Pod pod1 = ls.createPod(50, false);
    Pod pod2 = ls.createPod(50, false);
    Pod pod3 = ls.createPod(50, false);

    pod2.setNonFunctional();
    pod3.setNonFunctional();

    // get number of Pods before the nonfunctional ones are cleared
    int numBeforeClear = ls.getNumWaiting() + ls.getNumLaunched();
    
    ls.launchPod();
    ls.launchPod();
    
    // clear the nonfunctional Pods and return the number that have been cleared
    int numCleared = ls.clearMalfunctioning();
    
    // get number of Pods after the nonfunctional ones are cleared
    int numAfterClear = ls.getNumWaiting() + ls.getNumLaunched();

    // verify that the expected number of cleared nonfunctional Pods is equal to the actual result from the clear method
    if (numCleared != numBeforeClear - numAfterClear)
      return false;

    return true;
  }
  
  /**
   * Checks the correctness of the three getNumXXX() methods from LoopStation. This will require
   * adding Pods of various types, loading them with passengers, and launching them.
   * @return true if the getNumXXX() methods are all functioning correctly, false otherwise
   */
  public static boolean testGetNums() {

    // verify the behavior of getNumWaiting()
    LoopStation ls1 = new LoopStation();
    Pod getNumWaitingPod1 = ls1.createPod(50, false);
    Pod getNumWaitingPod2 = ls1.createPod(50, true);
    Pod getNumWaitingPod3 = ls1.createPod(50, false);
    int expectedNumWaiting = 3;

    if (ls1.getNumWaiting() != expectedNumWaiting)
      return false;
    
    // verify the behavior of getNumLaunched()
    LoopStation ls2 = new LoopStation();
    Pod getNumWaitingLaunched1 = ls2.createPod(50, false);
    Pod getNumWaitingLaunched2 = ls2.createPod(50, true);
    Pod getNumWaitingLaunched3 = ls2.createPod(50, false);

    ls2.launchPod();
    ls2.launchPod();
    int expectedNumLaunched = 2;

    if (ls2.getNumLaunched() != expectedNumLaunched)
      return false;
    
    // verify the behavior of getNumPassengers()
    LoopStation ls3 = new LoopStation();
    Pod getNumPassengersLaunched1 = ls3.createPod(50, false);
    Pod getNumPassengersLaunched2 = ls3.createPod(50, true);
    Pod getNumPassengersLaunched3 = ls3.createPod(50, false);

    getNumPassengersLaunched1.setNonFunctional();
    
    ls3.waitingEconomy.addPassenger("Harry", true);
    ls3.waitingEconomy.addPassenger("Tubby", true);
    ls3.waitingFirst.addPassenger("Filmore", false);

    int expectedNumPassengers = 2;

    if (ls3.getNumPassengers() != expectedNumPassengers)
      return false;

    // if all above sub-tests pass, return true
    return true;
  }

  public static void main(String[] args) {
    boolean test1 = testCreatePod();
    System.out.println("testCreatePod: "+(test1?"PASS":"FAIL"));
    
    boolean test2 = testLaunchPod();
    System.out.println("testLaunchPod: "+(test2?"PASS":"FAIL"));
    
    boolean test3 = testClearMalfunctioning();
    System.out.println("testClearMalfunctioning: "+(test3?"PASS":"FAIL"));
    
    boolean test4 = testGetNums();
    System.out.println("testGetNums: "+(test4?"PASS":"FAIL"));
    
    System.out.println("ALL TESTS: "+((test1&&test2&&test3&&test4)?"PASS":"FAIL"));
  }

}
