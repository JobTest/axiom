//package example.testtask;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.powermock.reflect.Whitebox;
//
//import static org.mockito.Mockito.times;
//
///**
// * {@link http://www.johnmullins.co/blog/2015/02/15/beginners-guide-to-using-mockito-and-powermockito-to-unit-test-java/}
// * {@link https://gist.github.com/jrmullins/e9924a07af819ca42ed5#file-human-java}
// * {@link https://gist.github.com/jrmullins/54ad3e13c89c6f1b77b6#file-puppy-java}
// * {@link https://gist.github.com/jrmullins/e99a290421afc4385bbd#file-puppytest-java}
// */
//@PrepareForTest({Puppy.class})
//@RunWith(PowerMockRunner.class)
//public class PuppyTest { //public class PuppyTest extends PowerMockTestCase {
//
//    @Test
//    public void testCreatePuppy() throws Exception {
//        //Mocking
//        Human human = Mockito.mock(Human.class);
//        Puppy puppy = Puppy.createPuppy("Gatsby", human);
//        assert(puppy != null);
//        assert(puppy.getName().equals("Gatsby"));
//        //Verifying
//        Mockito.verify(human, times(1)).isSoHappy();
//
//    }
//
////    @Test
////    public void testChaseTail() throws Exception {
////        Human human = Mockito.mock(Human.class);
////        //Spying
////        Puppy puppy = Mockito.spy(new Puppy("Gatsby", human));
////        Mockito.doNothing().when(puppy).bark();
////        puppy.chaseTail();
////        Mockito.verify(puppy, times(1)).bark();
////    }
//
//    @Test
//    public void testGoOnWalk() throws Exception {
//        Human human = Mockito.mock(Human.class);
//        //Power Spying
//        Puppy puppy = PowerMockito.spy(new Puppy("Gatsby", human));
//        PowerMockito.doNothing().when(puppy, "performBusiness");
//        //Can combine regular and power
//        Mockito.doNothing().when(puppy).wipeOffFeet();
//        puppy.goOnWalk(15);
//        Mockito.verify(puppy, times(1)).wipeOffFeet();
//    }
//
//    @Test
//    public void testBuyPuppy() throws Exception {
//        //Mocking static
//        PowerMockito.mockStatic(Puppy.class);
//        Human human = new Human("John");
//        Puppy puppy = Mockito.mock(Puppy.class);
//        //Static mocking and matchers
//        PowerMockito.when(Puppy.createPuppy(Mockito.eq("Gatsby"), Mockito.any(Human.class))).thenReturn(puppy);
//        human.buyPuppy("Gatsby");
//        assert(human.puppy != null);
//    }
//
//    @Test
//    public void testEat() throws Exception {
//        Human human = Mockito.mock(Human.class);
//        Puppy puppy = PowerMockito.spy(new Puppy("Gatsby",human));
//        //Get private variables
//        int energy = Whitebox.getInternalState(puppy, "energyLevel");
//        //Call private methods
//        Whitebox.invokeMethod(puppy, "eat");
//        int energyAfterwards = Whitebox.getInternalState(puppy, "energyLevel");
//        System.out.println(energy + " > " + energyAfterwards);
//        assert(energy <= energyAfterwards);
//    }
//}