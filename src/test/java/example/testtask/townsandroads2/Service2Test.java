package example.testtask.townsandroads2;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class Service2Test extends AService2 {

    List<City2> cities;
    List<Road2> roads;

    @Before
    public void setUp(){
        cities = new ArrayList(1000);
        roads = new ArrayList(); //roads = new ArrayList(1000);
    }

    @Test
    public void testAddCity(){
        cities.add(new City2("City-A", 100.0, 100.0));
        cities.add(new City2("City-B", 200.0, 200.0));
        cities.add(new City2("City-C", 300.0, 300.0));
        cities.add(new City2("City-D", 400.0, 400.0));
        cities.add(new City2("City-E", 500.0, 500.0));
        cities.add(new City2("City-E", 501.1, 501.1));
        cities.add(new City2("City-E", 502.2, 502.2));
        cities.add(new City2("City-E", 503.3, 503.3));

        addCity(cities);

        try {
            System.out.println( "--------------------[ City ]" );
            for (Map.Entry<City2Name, Integer> city : getCities(new City2Name("City")).entrySet()){
                System.out.println("["+city.getKey()+"] "+cities.get(city.getValue()));
            }
            System.out.println( "--------------------[ City-C ]" );
            for (Map.Entry<City2Name, Integer> city : getCities(new City2Name("City-C")).entrySet()){
                System.out.println("["+city.getKey()+"] "+cities.get(city.getValue()));
            }
            System.out.println( "--------------------[ City-E ]" );
            for (Map.Entry<City2Name, Integer> city : getCities(new City2Name("City-E")).entrySet()){
                System.out.println("["+city.getKey()+"] "+cities.get(city.getValue()));
            }
            System.out.println( "====================[ City-C ]" );
            System.out.println("[City-C (first)] "+cities.get(getFirstCity(new City2Name("City-C"))));
            System.out.println( "====================[ City-E ]" );
            System.out.println("[City-E (first)] "+cities.get(getFirstCity(new City2Name("City-E"))));
            System.out.println( "====================[ City-C ]" );
            System.out.println("[City-C (last)] "+cities.get(getLastCity(new City2Name("City-C"))));
            System.out.println( "====================[ City-E ]" );
            System.out.println("[City-E (last)] "+cities.get(getLastCity(new City2Name("City-E"))));
            System.out.println( "====================[ City-E ]" );
            System.out.println("[City-E (2)] "+cities.get(getCity(new City2Name("City-E"), (byte) 2)));
        } catch (CloneNotSupportedException e) { System.err.println(e.getMessage()); }
    }

    @Test
    public void testAddRoad(){
        cities.add(new City2("City-A", 100.0, 100.0));
        cities.add(new City2("City-B", 200.0, 200.0));
        cities.add(new City2("City-C", 300.0, 300.0));
        cities.add(new City2("City-D", 400.0, 400.0));
        cities.add(new City2("City-E", 500.0, 500.0));
        cities.add(new City2("City-E", 501.1, 501.1));
        cities.add(new City2("City-E", 502.2, 502.2));
        cities.add(new City2("City-E", 503.3, 503.3));

        addCity(cities);

        roads.add(new Road2("Road-0", getFirstCity(new City2Name("City-A")), getFirstCity(new City2Name("City-B"))));
        roads.add(new Road2("Road-1", getFirstCity(new City2Name("City-B")), getFirstCity(new City2Name("City-C"))));
        roads.add(new Road2("Road-2", getFirstCity(new City2Name("City-C")), getFirstCity(new City2Name("City-D"))));
        roads.add(new Road2("Road-3", getFirstCity(new City2Name("City-D")), getFirstCity(new City2Name("City-E"))));

        addRoad(roads);

        System.out.println("\n=========================");
        System.out.println( getRoad(new City2Name("City-C")));
        System.out.println("-------------------------");
        for (Road2Name roadName : getRoad(new City2Name("City-C")).keySet()){
            System.out.println("("+roadName.getNumRoad() + ") " + (roadName.getNumRoad()/1000) + " > " + (roadName.getNumRoad()%1000) + " = " + getFirstCity(new City2Name("City-C")));
        }
        System.out.println("-------------------------");
        /*
         * (TreeMapDemo)
         * treemap.headMap(7);
         * *******************
         * - перечислять элементы по порядковому номеру будет плохо потому-что может быть много пуустых итераций
         * + лучше перебирать элементы по итерациям Iterator...
         * * * * * * * * * * *
         * 1. Пускай номер первого города соответствует номеру ячейки массива (по которому формируется хеш-код)
         * 2. Тогда номер второго города будет соответствовать объекту (по которому формируется equals)
         * 3.1 При таком расскладе можно получить список всех городов в бакете (которые принадлежат дороге)
         * 3.2 Тогда автоматически (перебором), по совпадению второго номера, хеш-мапа вернет объект нужной дороги
         * + в такой способ - за два прохода!
         * 4.0 Также нужно помнить что города (направление первый/второй) могут меняться местами...поэтому такую процедуру следует повторить (только наоборот)
         * 4.1 Или-же такую процедуру следует повторять на момент добавления новой дороги...если такая дорога будет найденна (этим можно секономить размер спеиска для хранения дорог ИСКЛЮЧИВ обратные-дубликаты)
         *
         * Дело в том, что в любом случае прийдется перебирать все элементы списка И проверять наличие сперва одной а потом второй дороги
         * * * * * * * * * * *
         * Этим самым я получаю 2-а интерфейса: для города (список поиска); для дороги (список поиска)
         */
        System.out.println( getRoad(new City2Name("City-C")).get(new Road2Name(2003)) );
        System.out.println( roads.get(getRoad(new City2Name("City-C")).get(new Road2Name(2003))) );
//        System.out.println("+++++++++++++++++++++++++");
//        System.out.println( roads.get(getRoad(new City2Name("City-C")).get(new Road2Name(2003))) );
//        System.out.println( roads.get(getRoad(new City2Name("City-C")).get(new Road2Name(3002))) ); // NullPointerException
    }


    public void addCity(List<City2> cities){
        for (int numCity=0; numCity<cities.size(); numCity++){
            addCity(new City2Name(cities.get(numCity).getName()), numCity);
        }
    }

    public void addRoad(List<Road2> roads){
        for (int numRoad=0; numRoad<roads.size(); numRoad++){
            addRoad(roads.get(numRoad), numRoad);
        }
    }
}
