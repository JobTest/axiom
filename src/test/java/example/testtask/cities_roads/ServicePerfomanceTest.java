package example.testtask.cities_roads;

import java.util.Collection;
import java.util.List;

public class ServicePerfomanceTest {
    private static final Runtime s_runtime = Runtime.getRuntime();
    private static long beforeHeap=0, afterHeap=0, startTime=0, finishTime=0, finishTimeTest=0, elapsedTime=0, elapsedTimeTest=0;
    private static final int count = 100;

    public static void main (String [] args) throws Exception {
        runGC();
        usedMemory();
//        startTime = System.nanoTime();
//        finishTimeTest = System.nanoTime();

//        Object[] objects = new Object[count];
//        for (int i=-1; i<count; ++i){
//            Object object = null;
////            object = new Object();
////            object = new Integer(i);
////            object = new Long(i);
//            object = new String(""+i);
////            object = new byte[128][1]
//
//            if (0 <= i) {
//                objects[i] = object;
//            } else {
//                object = null;
//                runGC ();
//                beforeHeap = usedMemory();
//            }
//        }
        /////////////////////////////////////////////
////        Set<Object> objects2 = new HashSet<Object>();
////        Set<Object> objects2 = new TreeSet<Object>();
////        Vector<Object> objects2 = new Vector<Object>();
////        List<Object> objects2 = new LinkedList<Object>();
//        List<Object> objects2 = new ArrayList<Object>();
//        for (int i=-1; i<count; i++) {
//            Object object = null;
//
//            // Иннициализировать свои данные здесь и назначить его на объект
////            object = new Object();
////            object = new Integer(i);
////            object = new Long(i);
//            object = new String(""+i);
////            object = new byte[128][1];
//
//            if (0 <= i) {
//                objects2.add(object);
//            } else {
//                object = null; // Отказаться от разогрева объекта
//                runGC();
//                beforeHeap = usedMemory(); // Взять перед снепшотом heap(а)
//            }
//        }
        /////////////////////////////////////////////
        runGC ();
        beforeHeap = usedMemory();
        startTime = System.nanoTime();
        finishTimeTest = System.nanoTime();

        Service service = new Service();                                   // 3.480 (bytes) / 3.051 (nano-sec)  |||  3.480 (bytes)  / 3.051.546 (nano-sec)
        service.addCity("City-A", 10, 20);
        service.addCity("City-B", 20, 30);
        service.addCity("City-C", 30, 30);
        service.addCity("City-D", 40, 20);
        service.addCity("City-E", 30, 10);
        service.addCity("City-F", 20, 10);
        service.addCity("City-G", 10, 10);                                 // 1,352 (bytes) / 0,885 (nano-sec)  |||  4.832 (bytes)  / 3.936.296 (nano-sec)
//        service.initDijkstra(true);                                        // 1,904 (bytes) / 0,019 (nano-sec)  |||  6.736 (bytes)  / 3.955.117 (nano-sec)
        service.addRoad("Road-1", service.getCity(0), service.getCity(1));
        service.addRoad("Road-2", service.getCity(1), service.getCity(2));
        service.addRoad("Road-3", service.getCity(1), service.getCity(3));
        service.addRoad("Road-4", service.getCity(1), service.getCity(4));
        service.addRoad("Road-5", service.getCity(1), service.getCity(5));
        service.addRoad("Road-6", service.getCity(2), service.getCity(3));
        service.addRoad("Road-7", service.getCity(2), service.getCity(4));
        service.addRoad("Road-8", service.getCity(3), service.getCity(4));
        service.addRoad("Road-9", service.getCity(3), service.getCity(5)); // 1,912 (bytes) / 1,849 (nano-sec)  |||  8.648 (bytes)  / 5.804.368 (nano-sec)
//        service.indexingCities(true);                                      // 0,952 (bytes) / 0,951 (nano-sec)  |||  9.600 (bytes)  / 6.755.709 (nano-sec)
//        service.indexingRoads(true);                                       // 1,368 (bytes) / 0,759 (nano-sec)  |||  10.968 (bytes) / 7.514 732 (nano-sec)
//        service.indexingDijkstra(service.getCities().size(), 2);           // 0,28 (bytes)  / 0,245 (nano-sec)  |||  11.248 (bytes) / 7.759.948 (nano-sec)
        service.isRoad(service.getCity(3).getId(), service.getCity(5).getId());
        service.isRoad(service.getCity(3).getId(), service.getCity(4).getId());
        service.isRoad(service.getCity(3).getId(), service.getCity(3).getId());
        service.isRoad(service.getCity(3).getId(), service.getCity(6).getId());
        service.isRoad(service.getCity(0).getId(), service.getCity(6).getId()); // 0,632 (bytes) / 1,203 (nano-sec)  |||  11.880 (bytes) / 8.962.773 (nano-sec)  |||  8.744 (bytes) / 6.840.501 (nano-sec)    4.774.142

        finishTime = System.nanoTime();
        runGC();
        afterHeap = usedMemory();
        elapsedTimeTest = finishTimeTest-startTime;
        elapsedTime = finishTime-startTime;

//        printResourse(objects);
        /////////////////////////////////////////////
//        printResourse(objects2);
        /////////////////////////////////////////////
        printResourse();
//        printResourse(service2.getCities().size(), service2.getCities());
        System.out.print("                 elapsed time: '"); System.out.format("%,d", (elapsedTime-elapsedTimeTest-1375)); System.out.println("' nano-sec");
    }

    public static void printResourse(Object[] objects){
        final int size = Math.round(((float)(afterHeap-beforeHeap))/count);
        System.out.print("            Heap используется: '"); System.out.format("%,d", (afterHeap-beforeHeap)); System.out.println("' bytes");
        System.out.print(objects[0].getClass() + " размер: '"); System.out.format("%,d", size); System.out.println("' bytes");
    }
    public static void  printResourse(Collection<Object> objects){
        final int size = Math.round(((float)(afterHeap-beforeHeap))/count);
        System.out.print("            Heap используется: '"); System.out.format("%,d", (afterHeap-beforeHeap)); System.out.println("' bytes");
        System.out.print(objects.getClass() + " размер: '"); System.out.format("%,d", size); System.out.println("' bytes");
    }
    public static void  printResourse(){
        System.out.print("            Heap используется: '"); System.out.format("%,d", (afterHeap-beforeHeap)); System.out.println("' bytes");
    }
    public static void  printResourse(int count, List<City> objects){
        final int size = Math.round(((float)(afterHeap-beforeHeap))/count);
        System.out.print("            Heap используется: '"); System.out.format("%,d", (afterHeap-beforeHeap)); System.out.println("' bytes");
        System.out.print(objects.getClass() + " размер: '"); System.out.format("%,d", size); System.out.println("' bytes");
    }

    private static void runGC() throws Exception {
        for (int r=0; r<4; ++r) _runGC ();
    }
    private static void _runGC() throws Exception {
        long usedMem1=usedMemory(), usedMem2=Long.MAX_VALUE;
        for (int i=0; (usedMem1<usedMem2)&&(i<500); ++i) {
            s_runtime.runFinalization();
            s_runtime.gc ();
            Thread.currentThread().yield ();
            usedMem2 = usedMem1;
            usedMem1 = usedMemory();
        }
    }

    private static long usedMemory() {
        return s_runtime.totalMemory() - s_runtime.freeMemory();
    }
}
