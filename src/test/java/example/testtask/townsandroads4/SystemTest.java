//package example.testtask.townsandroads4;
//
///**
// * {@link https://habrahabr.ru/post/274811/}
// * {@link how-to-convert-nanoseconds-to-seconds-using-the-timeunit-enum}
// * {@link http://ru.stackoverflow.com/questions/1271/%D0%9A%D0%BB%D1%8E%D1%87%D0%B5%D0%B2%D0%BE%D0%B5-%D1%81%D0%BB%D0%BE%D0%B2%D0%BE-volatile-%D0%B2-java}
// * {@link https://habrahabr.ru/post/121234/}
// * {@link http://korzh.net/2010-11-proizvoditelnost-osnovnyx-kollekcij-java-sravnenie-s-analogami-net.html}
// *
// * {@link http://stackoverflow.com/questions/25552/get-os-level-system-information}
// * {@link http://www.coderanch.com/t/427452/sockets/java/system-information-ram-size-OS}
// * {@link https://github.com/dblock/oshi}
// * {@link http://java-course.ru/student/book1/unit-test/}
// * {@link fastest-way-to-check-if-a-liststring-contains-a-unique-string}
// * {@link https://www.cs.cmu.edu/~pattis/15-1XX/15-200/lectures/collectionsiii/lecture.html}
// */
//public class SystemTest {
//
//    public static void main(String[] args) {
////        Properties p = System.getProperties();
////        p.list(System.out);
//
////        /* Total number of processors or cores available to the JVM */
////        System.out.println("         Available processors (cores): " + Runtime.getRuntime().availableProcessors());
////
////        /* Total amount of free memory available to the JVM */
////        System.out.println();
////        System.out.println("                  Free memory (bytes): " + Runtime.getRuntime().freeMemory());
////
////        /* This will return Long.MAX_VALUE if there is no preset limit */
////        System.out.println();
////        long maxMemory = Runtime.getRuntime().maxMemory();
////        /* Maximum amount of memory the JVM will attempt to use */
////        System.out.println("               Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
////
////        /* Total memory currently available to the JVM */
////        System.out.println();
////        System.out.println("Total memory available to JVM (bytes): " + Runtime.getRuntime().totalMemory());
////
////        /* Get a list of all filesystem roots on this system */
////        File[] roots = File.listRoots();
////
////        /* For each filesystem root, print some info */
////        System.out.println();
////        for (File root : roots) {
////            System.out.println("                 File system root: " + root.getAbsolutePath());
////            System.out.println("              Total space (bytes): " + root.getTotalSpace());
////            System.out.println("               Free space (bytes): " + root.getFreeSpace());
////            System.out.println("             Usable space (bytes): " + root.getUsableSpace());
////        }
//
////        System.out.println(System.getenv("PROCESSOR_IDENTIFIER"));
////        System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
////        System.out.println(System.getenv("PROCESSOR_ARCHITEW6432"));
////        System.out.println(System.getenv("NUMBER_OF_PROCESSORS"));
////        System.out.println();
////        Properties properties = System.getProperties();
////        Set<Object> keys = properties.keySet();
////        for(Object key : keys){
////            System.out.println(key + ": " + properties.get(key));
////        }
//
//        ///////////////////////////////////
////        System.out.println(System.currentTimeMillis() + " - " + System.nanoTime() + " - " + System.nanoTime());
////        System.out.println(System.currentTimeMillis() + " - " + System.nanoTime());
//
//
//        long start = 0, finish = 0, finishTest = 0, elapsed = 0, elapsedTest = 0, startTotalMemory = 0, finishTotalMemory = 0;
//        startTotalMemory = Runtime.getRuntime().totalMemory();
//
//        int[] arr = {1,2,3,4,5,6,7,8,9,10,20,30,40,50,60,70,80,90,100,200,300,400,500,600,700,800,900,1000};
//        for (short i=0; i<arr.length; i++)
//            System.out.print(arr[i]+" ");
//        System.out.println();
//
//        start = System.nanoTime();
//        finishTest = System.nanoTime();
//
//
//        // -------------- 2566
////        for (short i=0; i<arr.length/2; i++){
////            int tmp             = arr[i];
////            arr[i]              = arr[arr.length-i-1];
////            arr[arr.length-i-1] = tmp;
////        }
//
//        // -------------- 5773
////        for (short i=0; i<arr.length/2; i++){
////            arr[i]              = arr[i] + arr[arr.length-i-1];
////            arr[arr.length-i-1] = arr[i] - arr[arr.length-i-1];
////            arr[i]              = arr[i] - arr[arr.length-i-1];
////        }
//
//        // -------------- 13470
////        for (int i=0; i<arr.length; i++){
////            for (int j=arr.length-1; i<j; j--){
////                if (arr[i]<arr[j]){
////                    int tmp = arr[j];
////                    arr[j]  = arr[i];
////                    arr[i]  = tmp;
////                }
////            }
////        }
//
//        // -------------- 28865-39128
////        for (int i=0; i<arr.length; i++){
////            for (int j=0; j<arr.length; j++){
////                if (arr[i]>arr[j]){ //if (arr[i]<arr[j]){
////                    int tmp = arr[j];
////                    arr[j]  = arr[i];
////                    arr[i]  = tmp;
////                }
////            }
////        }
//
//        // -------------- 382297
////        Arrays.sort(arr);
//
//        // -------------- 0
////        int a;
////        a = 1000;
//
//        // -------------- 4491
////        int[] arr;
////        arr = new int[100];
//
//        // -------------- 4490
////        short[] arr;
////        arr = new short[100];
//
//        // -------------- 4490
////        float[] arr;
////        arr = new float[100];
////        for (short i=0; i<arr.length; i++)
////            arr[i]=1000;
//
//        // -------------- 50032
////        int[] arr;
////        arr = new int[100];
////        Arrays.fill(arr, 1000);
//
//        // -------------- 7697
////        String[] arr;
////        arr = new String[100];
////        for (short i=0; i<arr.length; i++)
////            arr[i]="1000";
//
//        // -------------- 35279
////        Integer[] arr;
////        arr = new Integer[100];
////        for (short i=0; i<arr.length; i++)
////            arr[i]=1000;
//
//        // -------------- 62861
////        Integer[] arr;
////        arr = new Integer[100];
////        Arrays.fill(arr, 1000);
//
//        // -------------- 100706
////        List<String> arr;
////        arr = new ArrayList<String>();
////        for (short i=0; i<100; i++)
////            arr.add("1000");
//
//        // -------------- 126364
////        List<Integer> arr;
////        arr = new ArrayList<Integer>();
////        for (short i=0; i<100; i++)
////            arr.add(1000);
//
//        // -------------- 168699
////        List<Float> arr;
////        arr = new ArrayList<Float>();
////        for (short i=0; i<100; i++)
////            arr.add(1000f);
//
//        // -------------- 307191689 + 47464448
////        List<Integer> list = new ArrayList<Integer>();
////        for (int i=0; i<1000000; i++)
////            list.add(1000);
//
//        // -------------- 494587535 + 59207680
////        List<City> list = new ArrayList<City>();
////        for (int i=0; i<1000000; i++)
////            list.add(new City(1, "City-1", new Point(100, 100)));
//
//        finish = System.nanoTime();
//        elapsedTest = finishTest-start;
//        finishTotalMemory = Runtime.getRuntime().totalMemory();
//        elapsed = finish-start;
//
//        System.out.println("              start: " + start);
//        System.out.println("             finish: " + finish);
////        System.out.println("      (test) finish: " + finishTest);
//        System.out.println("       elapsed time: " + (elapsed-elapsedTest) + " ("+elapsed+")");
////        System.out.println("(test) elapsed time: " + elapsedTest);
//        //System.out.println(TimeUnit.SECONDS.convert(start, TimeUnit.NANOSECONDS)); //System.out.println(TimeUnit.SECONDS.convert((elapsed-elapsedTest), TimeUnit.NANOSECONDS)); //System.out.println("      second: " + (double)((finish-start) / 1000000000.0));
//
//        for (short i=0; i<arr.length; i++)
//            System.out.print(arr[i]+" ");
//        System.out.println("\nTotal memory available to JVM: " + (finishTotalMemory-startTotalMemory) + " (" + startTotalMemory + " > " + finishTotalMemory + ")");
//    }
//
//}
