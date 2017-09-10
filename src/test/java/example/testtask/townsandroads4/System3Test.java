//package example.testtask.townsandroads4;
//
//import java.util.*;
//
///**
// * {@link http://www.javaworld.com/article/2077496/testing-debugging/java-tip-130--do-you-know-your-data-size-.html}
// */
//public class System3Test extends JMeter3 {
//    public System3Test() throws Exception {
//        super();
//    }
//
//    public static void main(String[] args) throws Exception {
//        new System3Test();
//    }
//
//    @Override
//    void run() throws Exception {
////        Object[] objects = new Object[count];
////        for (int i=-1; i<count; i++) {
////            Object object = null;
////
////            // Иннициализировать свои данные здесь и назначить его на объект
//////            object = new Object();
//////            object = new Integer(i);
//////            object = new Long(i);
////            object = new String();
//////            object = new byte[128][1];
////
////            if (0 <= i) {
////                objects[i] = object;
////            } else {
////                object = null; // Отказаться от разогрева объекта
////                runGC();
////                beforeHeap = usedMemory(); // Взять снепшот heap(а) в начале
////            }
////        }
//        /////////////////////////////////////////////
////        Set<Object> objects2 = new HashSet<Object>();
//        Set<Object> objects2 = new TreeSet<Object>();
////        Vector<Object> objects2 = new Vector<Object>();
////        List<Object> objects2 = new LinkedList<Object>();
////        List<Object> objects2 = new ArrayList<Object>();
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
//
//        runGC();
//        afterHeap = usedMemory(); // Взять снепшот heap(а) в конце
//
////        printResourse(count, objects);
//        /////////////////////////////////////////////
//        printResourse(count, objects2);
//    }
//
//}
//
//abstract class JMeter3 {
//    long  beforeHeap = 0, afterHeap = 0;
//    // Массив, чтобы сохранить прочные ссылки на выделенных объектов
//    final int  count = 100000;
//
//    public JMeter3() throws Exception {
//        // Разогреть все классы/методы, которые мы будем использовать
//        runGC();
//        usedMemory();
//        run();
//    }
//
//    abstract void run() throws Exception;
//
//    void printResourse(int count, Object[] objects){
//        final int size = Math.round(((float)(afterHeap-beforeHeap))/count);
//        System.out.print("            Heap используется: '"); System.out.format("%,d", (afterHeap-beforeHeap)); System.out.println("' bytes");
//        System.out.print(objects[0].getClass() + " размер: '"); System.out.format("%,d", size); System.out.println("' bytes");
//    }
//    void printResourse(int count, Collection<Object> objects){
//        final int size = Math.round(((float)(afterHeap-beforeHeap))/count);
//        System.out.print("            Heap используется: '"); System.out.format("%,d", (afterHeap-beforeHeap)); System.out.println("' bytes");
//        System.out.print(objects.getClass() + " размер: '"); System.out.format("%,d", size); System.out.println("' bytes");
//    }
//
//    static void runGC () throws Exception {
//        // Это помогает вызвать Runtime.gc() с помощью нескольких вызовов методов:
//        for (int r=0; r<4; ++r)
//            _runGC();
//    }
//    static void _runGC () throws Exception {
//        long usedMem1 = usedMemory(), usedMem2 = Long.MAX_VALUE;
//        for (int i=0; (usedMem1<usedMem2) && (i<500); ++i) {
//            s_runtime.runFinalization();
//            s_runtime.gc();
//            Thread.currentThread().yield ();
//
//            usedMem2 = usedMem1;
//            usedMem1 = usedMemory ();
//        }
//    }
//
//    static long usedMemory() {
//        return s_runtime.totalMemory() - s_runtime.freeMemory();
//    }
//
//    static final Runtime s_runtime = Runtime.getRuntime();
//}