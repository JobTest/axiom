//package example.testtask.cities_roads;
//
///**
// * Created by alexandr on 16.01.16.
// * ********************************
// * {@link http://javatalks.ru/topics/11768?page=2}
// */
//public     class kmBitvinESR
//{
//    public static void main(String args[])
//    {
//        int[][] graf =
//                {
//                        {-1, 2, 3, 2,-1},
//                        {2,-1, 1, 3, 1},
//                        { 3, 1,-1, 1, 3},
//                        { 2, 3, 1,-1, 2},
//                        {-1, 1, 3, 2,-1}
//                };
//        seachPath sp;
//        for (int i = 0; i < graf.length; i++)
//        {
//            for (int j = i+1; j < graf.length; j++)
//            {
//                sp = new seachPath();
//                sp.tempKm = new int[graf.length];
//                sp.kmStop = 0;
//                sp.graf = graf;
//                sp.start = i;
//                sp.stop = j;
//                sp.fSeachPath(-1,0,""+sp.start);
//            }
//        }
//    }
//}
//class seachPath
//{
//    int kmStop;
//    int[] tempKm;
//    static int[][] graf;
//    static int start,stop;
//    int fSeachPath (int curent,int km, String path)
//    {
//        if (kmStop != 0 && kmStop < km) return kmStop;
//        if (curent == stop && (kmStop == 0 || kmStop >= km))
//        {
//            kmStop = km;
//            tempKm[curent] = km;
//            System.out.println(start+";"+stop+";"+km+";"+path);
//            return kmStop;
//        }
//        if (curent == -1) { curent = start; tempKm[curent] = km;}
//        else if (curent != start && (tempKm[curent] == 0 || tempKm[curent] > km))
//
//            tempKm[curent] = km;
//        else return kmStop;
//        for (int i = 0; i < graf.length; i++) if (graf[i][curent] != -1)
//
//            fSeachPath(i,km+graf[curent][i],path+","+i);
//        return kmStop;
//    }
//
//}
