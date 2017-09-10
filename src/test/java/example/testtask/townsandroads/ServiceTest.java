package example.testtask.townsandroads;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by alexandr on 28.01.16.
 */
public class ServiceTest {

    private Service townsroads;

    @Before
    public void setUp() {
        townsroads = new Service();
    }

    /**
     * Full test #1
     */
//    @Test
    public void testFull() {
        System.out.println(townsroads.addTown("City-1", new Town(10.0, 10.0)));
        System.out.println(townsroads.addTown("City-2", new Town(11.0, 9.0)));
        System.out.println(townsroads.addTown("City-3", new Town(12.0, 8.0)));
        System.out.println(townsroads.addTown("City-4", new Town(13.0, 7.0)));
        System.out.println(townsroads.addTown("City-5", new Town(14.0, 6.0)));
        System.out.println(townsroads.addTown("City-6", new Town(13.0, 7.0)));
        System.out.println(townsroads.addTown(null, new Town(13.0, 7.0)));
        System.out.println(townsroads.addTown("City-6", new Town(13.0, 17.0)));
        System.out.println(townsroads.addTown("City-7", new Town(23.0, 17.0)));
        System.out.println(townsroads.addTown("City-7", new Town(23.0, 18.0)));

        System.out.println();
        System.out.println(townsroads.addRoad("Road-1", new Road(townsroads.getTown("City-1").hashCode(), townsroads.getTown("City-2").hashCode())));
        System.out.println(townsroads.addRoad("Road-2", new Road(0, 0)));
        System.out.println(townsroads.addRoad("Road-3", new Road(0, 100)));
        System.out.println(townsroads.addRoad(null, new Road(100, 200)));
        System.out.println(townsroads.addRoad("Road-4", new Road(townsroads.getTown("City-1").hashCode(), townsroads.getTown("City-2").hashCode())));
        System.out.println(townsroads.addRoad("Road-5", new Road(townsroads.getTown("City-2").hashCode(), townsroads.getTown("City-3").hashCode())));
        System.out.println(townsroads.addRoad("Road-6", new Road(townsroads.getTown("City-3").hashCode(), townsroads.getTown("City-4").hashCode())));
        System.out.println(townsroads.addRoad("Road-7", new Road(townsroads.getTown("City-4").hashCode(), townsroads.getTown("City-5").hashCode())));
        System.out.println(townsroads.addRoad("Road-8", new Road(townsroads.getTown("City-5").hashCode(), townsroads.getTown("City-6").hashCode())));
        System.out.println(townsroads.addRoad("Road-8", new Road(townsroads.getTown("City-6").hashCode(), townsroads.getTown("City-7").hashCode())));
        System.out.println(townsroads.addRoad("Road-9", new Road(townsroads.getTown("City-5").hashCode(), townsroads.getTown("City-6").hashCode())));
        System.out.println(townsroads.addRoad("Road-10", new Road(townsroads.getTown("City-1").hashCode(), townsroads.getTown("City-6").hashCode())));
        System.out.println(townsroads.addRoad("Road-11", new Road(townsroads.getTown("City-1").hashCode(), townsroads.getTown("City-5").hashCode())));
        System.out.println(townsroads.addRoad("Road-12", new Road(townsroads.getTown("City-1").hashCode(), townsroads.getTown("City-4").hashCode())));

        System.out.println();
        String nameTown = "City-1";
        for (Map.Entry<Road, String> ro:townsroads.getRoads(nameTown).entrySet()){
            System.out.println(ro.getValue() + " >> " + nameTown);
        }

//        System.out.println();
//        for (Map.Entry<Road, String> ro:townsroads.getRoads(checkTown).entrySet()){
//            System.out.println(ro.getValue() + " >> " + checkTown);
//        }
    }






    ///////////////////////////////////////////////////////////////////////
    // isEmpty ......... делает тоже самое что и for - если список пустой, тогда условие невыполнится (то есть 'isEmpty' и 'for' дважды выполняют одну и ту-же операцию)
    //                   по сути весь список перебирать ненужно, достаточно чтобы в списке присутствовал хотябы один элемент
    // null ............ неочень хорошо применять потому-что может привести к исключению (если при непустом списке элементов ненайдено - это НЕзначит что список null)
    //                   на null тратиться больше времени и ресурсов
    // containsValue ... перебирает список в поисках ЗНАЧЕНИЯ (неключа) и при первом-же найденном элементе (на случай если они дублируются) вернет TRUE
    // get ............. очень быстро выполняет поиск по хеш-коду (НЕперебирает список)
    //
    //                   Для быстрого поиска элемента по хеш-таблице можно добавить специальное поле-хеша, которе будет находить элементы в сочетании с именем (значением) и хеш-кодом ключа...
    ///////////////////////////////////////////////////////////////////////
    Map<Town, String> towns = new HashMap<Town, String>();
    Map<Road, String> roads = new HashMap<Road, String>();
    ///////////////////////////////////////////////////////////////////////
    Map<Town, String> getTowns(String byName){
        Map<Town, String> copy = new HashMap<Town, String>();
        if (towns != null && byName != null){
//                if (towns.containsValue(byName)){
                    for (Map.Entry<Town, String> town : towns.entrySet()){
                        if (town.getValue().equals(byName)){
                            copy.put(town.getKey(), town.getValue());
                        }
                    }
//            }
        }
        return copy;
    }
    Town getTown(String byName, int count){
        if (towns!=null && byName!=null && 0<=count){
//            if (!towns.isEmpty()){
//                if (towns.containsValue(byName)){
                    int copys = 0;
                    for (Map.Entry<Town, String> town : towns.entrySet()){
                        if (town.getValue().equals(byName)){
                            if (copys==count){
                                return town.getKey();
                            }
                            copys++;
                        }
                    }
//                }
//            }
        }
        return null;
    }
    Town getFirstTown(String byName){
        if (towns!=null && byName!=null){
//            if (!towns.isEmpty()){
                if (towns.containsValue(byName)){
                    for (Map.Entry<Town, String> town : towns.entrySet()){
                        if (town.getValue().equals(byName)){
                            return town.getKey();
                        }
                    }
                }
//            }
        }
        return null;
    }
    Town getTown(int byHash){
        if (towns != null && byHash != 0){
//            if (!towns.isEmpty()){
                for (Map.Entry<Town, String> town : towns.entrySet()){
                    if (town.getKey().hashCode()==byHash){
                        return town.getKey();
                    }
                }
//            }
        }
        return null;
    }
    String getName(Town byTown){
        if (towns != null && byTown != null) {
//            if (!towns.isEmpty()) {
                return towns.get(byTown);
//            }
        }
        return null;
    }
    ///////////////////////////////////////////////////////////////////////
    Map<Road, String> getRoads(int byHashTown){
        Map<Road, String> copy = null;
        if (byHashTown!=0){
            if (!towns.isEmpty() && !roads.isEmpty()){
                copy = new HashMap<Road, String>();
                for (Map.Entry<Road, String> road : roads.entrySet()){
                    if (road.getKey().getHashTown1()==byHashTown || road.getKey().getHashTown2()==byHashTown) {
                        copy.put(road.getKey(), road.getValue());
                    }
                }
            }
        }
        return copy;
    }


    @Test
    public void testMy1(){
        towns.put(new Town(100.0, 100.0), "Town-1");
        towns.put(new Town(200.0, 100.0), "Town-2");
        towns.put(new Town(300.0, 100.0), "Town-3");
        towns.put(new Town(400.0, 100.0), "Town-3");
        towns.put(new Town(500.0, 100.0), "Town-3");

        System.out.println("=================");
        for (Map.Entry<Town, String> town : towns.entrySet()){
            System.out.println("["+town.getValue()+"] "+town.getKey());
        }

        System.out.println("-----------------");
        System.out.println(getName(new Town(500.0, 100.0)));

        System.out.println("-----------------");
        for (Map.Entry<Town, String> town : getTowns("Town-3").entrySet()){
            System.out.println("["+town.getValue()+"] "+town.getKey());
        }

        System.out.println("-----------------");
        System.out.println("[Town-3] "+getFirstTown("Town-3"));
        System.out.println("[Town-3] "+getTown("Town-3",2));
    }

    @Test
    public void testMy2(){
        towns.put(new Town(100.0, 100.0), "Town-1");
        towns.put(new Town(200.0, 100.0), "Town-2");
        towns.put(new Town(300.0, 100.0), "Town-3");
        towns.put(new Town(400.0, 100.0), "Town-3");
        towns.put(new Town(500.0, 100.0), "Town-3");
        towns.put(new Town(100.0, 200.0), "Town-4");
        towns.put(new Town(200.0, 300.0), "Town-5");

        System.out.println("=================");
        for (Map.Entry<Town, String> town : towns.entrySet()){
            System.out.println("["+town.getValue()+"] "+town.getKey());
        }

        System.out.println("-----------------");
        for (Map.Entry<Town, String> town : getTowns("Town-2").entrySet()){
            System.out.println("["+town.getValue()+"] "+town.getKey()+" ("+town.getKey().hashCode()+")");
            System.out.println(getTown( town.getKey().hashCode() ));
        }
        System.out.println(getTown(219152384));

//        roads.put(new Road(getTowns("Town-1").get(0).hashCode(), getTowns("Town-2").get(0).hashCode()), "Road-1");
//        roads.put(new Road(getTowns("Town-2").get(0).hashCode(), getTowns("Town-3").get(0).hashCode()), "Road-2");
//        roads.put(new Road(getTowns("Town-3").get(0).hashCode(), getTowns("Town-4").get(0).hashCode()), "Road-3");
//        roads.put(new Road(getTowns("Town-4").get(0).hashCode(), getTowns("Town-3").get(0).hashCode()), "Road-4");
//
//        System.out.println("-----------------");
//        for (Map.Entry<Road, String> road : getRoads(getTowns("Town-2").get(0).hashCode()).entrySet()){
//            System.out.println("["+road.getValue()+"] >> Town-2");
//        }
//
//        System.out.println("-----------------");
//        for (Map.Entry<Road, String> road : roads.entrySet()){
//            System.out.println("["+road.getValue()+"] "+road.getKey());
//        }
    }


//    @Test
//    public void testTemp1(){
//        Map<Town, String> towns2 = new HashMap<Town, String>();
//        towns2.put(new Town(100,100), "Town-1");
//        towns2.put(new Town(200,100), "Town-2");
//        towns2.put(new Town(300,100), "Town-3");
//        towns2.put(new Town(400,100), "Town-4");
//        towns2.put(new Town(400,100), "Town-5");
//        towns2.put(new Town(400,100), "Town-6");
//
//        System.out.println("||||||||||||||||||||||||||||||");
//        System.out.println(towns2.containsKey(new Town(200, 100)));
//        System.out.println(towns2.get(new Town(200, 100)));
//        System.out.println(towns2.values());
//        System.out.println(towns2.containsValue("Town-2"));
//        System.out.println("------------------------------");
//        System.out.println(towns2.containsValue("Town-5"));
//        System.out.println(towns2.containsKey(new Town(400, 100)));
//        System.out.println(towns2.get(new Town(400, 100)));
//    }

    @Test
    public void testTemp2(){
        Map<String, Town> towns2 = new HashMap<String, Town>();
        towns2.put("Town-1", new Town(100,100));
        towns2.put("Town-2", new Town(200,100));
        towns2.put("Town-3", new Town(300,100));
        towns2.put("Town-4", new Town(400,100));
        towns2.put("Town-4", new Town(500,100));
        towns2.put("Town-4", new Town(600,100));

        System.out.println("||||||||||||||||||||||||||||||");
        System.out.println(towns2.values());
        System.out.println("------------------------------");
        System.out.println(towns2.containsKey("Town-4"));
        System.out.println(towns2.containsValue(new Town(500, 100)));
        System.out.println(towns2.get("Town-4"));
    }
}
