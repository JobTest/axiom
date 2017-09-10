//package example.testtask.cities_roads;
//
//import java.util.Vector;
//
///**
// * Created by alexandr on 16.01.16.
// */
//public class MyDijkstra {
//
//    private int nr_points=0;
//    private int[][]Cost;
//    private int []mask;
//
//    private void dijkstraTSP()
//    {
//        if(nr_points==0)return;
//        //algorithm=new String("Dijkstra");
//
//        Vector nod1=new Vector();
//        Vector nod2=new Vector();
//        Vector weight=new Vector();
//        mask=new int[nr_points];
//        //initialise mask with zeros (mask[x]=1 means the vertex is marked as used)
//        for(int i=0;i<nr_points;i++)mask[i]=0;
//        //Dijkstra:
//        int []dd=new int[nr_points];
//        int []pre=new int[nr_points];
//        int []path=new int[nr_points+1];
//        int init_vert=0,pos_in_path=0,new_vert=0;
//
//        //initialise the vectors
//        for(int i=0;i<nr_points;i++)
//        {
//            dd[i]=Cost[init_vert][i];
//            pre[i]=init_vert;
//            path[i]=-1;
//        }
//        pre[init_vert]=0;
//        path[0]=init_vert;
//        pos_in_path++;
//        mask[init_vert]=1;
//
//        for(int k=0;k<nr_points-1;k++)
//        {
//            //find min. cost in dd
//            for(int j=0;j<nr_points;j++)
//                if(dd[j]!=0 && mask[j]==0){new_vert=j; break;}
//
//            for(int j=0;j<nr_points;j++)
//                if(dd[j]<dd[new_vert] && mask[j]==0 && dd[j]!=0)new_vert=j;
//
//            mask[new_vert]=1;
//            path[pos_in_path]=new_vert;
//            pos_in_path++;
//            for(int j=0;j<nr_points;j++)
//            {
//                if(mask[j]==0)
//                {
//                    if(dd[j]>dd[new_vert]+Cost[new_vert][j])
//                    {
//                        dd[j]=dd[new_vert]+Cost[new_vert][j];
//                    }
//                }
//            }
//        }
//        //Close the cycle
//        path[nr_points]=init_vert;
//
//        //Save the solution in 3 vectors (for graphical purposes)
//        for(int i=0;i<nr_points;i++)
//        {
//            nod1.addElement(path[i]);
//            nod2.addElement(path[i+1]);
//            weight.addElement(Cost[path[i]][path[i+1]]);
//        }
//    }
//
//}
