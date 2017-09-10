package example.testtask.java8.meeting2;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 ** {@link https://eyalsch.wordpress.com/2009/11/23/comparators/}
 *  {@link http://spec-zone.ru/RU/Java/Tutorials/collections/interfaces/sorted-set.html}
 *  {@link http://users.cis.fiu.edu/~weiss/dsj2/code/weiss/util/SortedSet.java}
 *  {@link http://developer.alexanderklimov.ru/android/java/set.php}
 *  {@link http://www.programcreek.com/java-api-examples/java.util.SortedSet}
 *  ***********************************
 *  В дополнение к нормальному Set операции, SortedSet интерфейс обеспечивает операции для следующего:
 *  > Range view — позволяет произвольные операции диапазона на сортированном наборе
 *  > Endpoints — возвращает первый или последний элемент в сортированном наборе
 *  > Comparator access — возвраты Comparator, если таковые вообще имеются, используемый, чтобы сортировать набор
 */
public class RoadThreeMap {

    public static void main(String[] args) {
        OrderedRoadSet orderedRoad = new OrderedRoadSet(new Road(0,0));

        orderedRoad.add(new Road(20,5));
        orderedRoad.add(new Road(1,5));
        orderedRoad.add(new Road(10,10));
        orderedRoad.add(new Road(5,20));
        orderedRoad.add(new Road(5,5));
        orderedRoad.add(new Road(5,10));
        orderedRoad.add(new Road(10,5));

        System.out.println( orderedRoad );
    }

}


class Road {
    private int city1;
    private int city2;

    public Road(int city1, int city2) {
        this.city1 = city1;
        this.city2 = city2;
    }

    public int getCity1() {
        return city1;
    }
    public int getCity2() {
        return city2;
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Road))
            return false;
        Road otherRoad = (Road) other;
        return city1==otherRoad.city1 && city2==otherRoad.city2;
    }

    @Override
    public int hashCode(){
        return city1 ^ city2;
    }

    public double distanceFrom(Road otherRoad){
        int deltaCity1 = city1 - otherRoad.city1;
        int deltaCity2 = city2 - otherRoad.city2;
        return Math.sqrt( deltaCity1 * deltaCity1 + deltaCity2 * deltaCity2 );
    }

    @Override
    public String toString(){
        return "("+city1+","+city2+")";
    }
}

class OrderedRoadSet {
    private SortedSet<Road> roads;
    private Road             road;

    public OrderedRoadSet(final Road road){
        this.road = road;
        roads     = new TreeSet<Road>(new Comparator<Road>(){
            public int compare(Road r1, Road r2) {
                double dist1 = road.distanceFrom(r1);
                double dist2 = road.distanceFrom(r2);
                if (dist1 > dist2)
                    return 1;
                if (dist2 > dist1)
                    return -1;
                return 0;
            }
        });
    }

    /**
     * returns the reference point followed by a list of points ordered in ascending distance from it
     * @param r
     */
    public void add(Road r){
        roads.add(r);
    }

    @Override
    public String toString(){
        return road+" : "+roads.toString();
    }
}