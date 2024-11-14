// TODO complete file header

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

    // check if the pod instance's attributes are correct
    if (pod.getCapacity() != 50)
      return false;
    if (pod.getPodClass() != Pod.ECONOMY)
      return false;
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
    if (ls2.waitingEconomy.get(0) != podEconClass2)
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
    return false;
  }
  
  /**
   * Checks the correctness of the three getNumXXX() methods from LoopStation. This will require
   * adding Pods of various types, loading them with passengers, and launching them.
   * @return true if the getNumXXX() methods are all functioning correctly, false otherwise
   */
  public static boolean testGetNums() {
    return false;
  }

  public static void main(String[] args) {
    boolean test1 = testCreatePod();
    System.out.println("testCreatePod: "+(test1?"PASS":"fail"));
    
    boolean test2 = testLaunchPod();
    System.out.println("testLaunchPod: "+(test2?"PASS":"fail"));
    
    boolean test3 = testClearMalfunctioning();
    System.out.println("testClearMalfunctioning: "+(test3?"PASS":"fail"));
    
    boolean test4 = testGetNums();
    System.out.println("testGetNums: "+(test4?"PASS":"fail"));
    
    System.out.println("ALL TESTS: "+((test1&&test2&&test3&&test4)?"PASS":"fail"));
  }

}
