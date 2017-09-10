package example.testtask.townsandroads4;

import java.awt.*;
import java.util.*;
import java.util.List;

/*
 * 1. Попытаемся отделить логику
 *    --------------------------
 *    Обычный список элементов представляет собой - просто хранилище для объектов (и больше ничего...потому-что логика, или изменение логики, никак недолжно влиять на наличие элементоа в списке)
 *    Логика может быть разной и она всегда может меняет свое поведение...конкретно для этой цели я отдельно реализую специальный тип списка (который будет удовлетвотрять по требованию)
 */

public class Service implements IService {
    /*
     * Есть обыкновенный список городов (который просто список и все...)
     * основная цель этого списка - просто хранить объекты
     */
    /**
     * Обращаться к элементам списка можно (только) по 'ID' и возвращает уже объект...
     */
    private List<City> cities;
    private List<Road>  roads;
    /*
     * Присутствует некая логика, например: специфический поиск элементов в списке
     * Дело в том что часть логики является независимым модулем от списка...поэтому будет правильно реализовать ее отдельно
     */
    /**
     * Обращаться к элементам списка можно по 'имени' (согласно условию логики) и возвращает уже искомые 'ID'...
     */
    private Map<KeyCity, Integer> byNameCities;
    private Map<KeyRoad, Integer>  byCityRoads;

    public Service(){
        cities = new Cities();
        roads  = new Roads();
    }

    @Override
    public boolean addCity(String name, int latitude, int longitude){
        if (name != null){
            City c = new City(cities.size(), name, new Point(latitude, longitude));
            if (!cities.contains(c))
                return cities.add(c);
        }
        return false;
    }
    @Override
    public boolean addRoad(String name, City city1, City city2){
        if (name != null
                && city1 != null
                && city2 != null
                && !city1.equals(city2)){
            Road r = new Road(roads.size(), name, city1.getId(), city2.getId());
            if (!roads.contains(r)){
                if (getCitiesWay(city1.getId(), city2.getId())){
                    return roads.add(r);
                }
            }
        }
        return false;
    }

    public void initDijkstra() {
        isUsedCity = new boolean[cities.size()];
        pred       = new int[cities.size()];
        ways       = new int[cities.size()];
        adj       = new ArrayList[cities.size()];
        for (City city:cities)
            adj[cities.indexOf(city)] = new ArrayList(); // инициализируем списка смежности графа размерности n
    }

    public List<City> getCities() {
        return cities;
    }
    @Override
    public List<City> getCities(String byName) {
        KeyCity     key = new KeyCity(byName);
        List<City> copy = new ArrayList();
        while (byNameCities.containsKey(key)) {
            copy.add(cities.get(byNameCities.get(key)));
            key.incId();
        }
        return copy;
    }
    public City getCity(int byId) {
        try {
            return cities.get(byId);
        } catch (IndexOutOfBoundsException iobe) {}
        return null;
    }

    public List<Road> getRoads() {
        return roads;
    }
    @Override
    public List<Road> getRoads(int byCityId) {
        KeyRoad     key = new KeyRoad(byCityId);
        List<Road> copy = new ArrayList();
        while (byCityRoads.containsKey(key)) {
            copy.add(roads.get(byCityRoads.get(key)));
            key.incId();
        }
        return copy;
    }

    public boolean getCitiesWay(int firstCityId, int lastCityId) {
        indexesDijkstra(firstCityId);
        int distanceWay = 0, countCitiesWay = 0;
        for (City city:cities) {
            if (cities.indexOf(city)==lastCityId) {
                countCitiesWay = countCitiesWay(cities.indexOf(city))-1;
                distanceWay    = distanceWay(cities.indexOf(city));
            }
        }
        return (0==countCitiesWay && INF<=distanceWay) ? true : false;
    }
//    public void getCitiesWay1(int firstCityId, int lastCityId) {
//        indexesDijkstra(firstCityId);
//        for (City city:cities) {
//            if (firstCityId<=cities.indexOf(city) && cities.indexOf(city)<=lastCityId){
//                System.out.print("Города " + firstCityId + "-" + cities.indexOf(city) + ": ");
//                if (ways[cities.indexOf(city)] != INF) printCitiesWay(cities.indexOf(city));
//                System.out.println();
//            }
//        }
//        System.out.print("Путь: ( ");
//        for (City city:cities) {
//            if (cities.indexOf(city)==lastCityId) {
//                if (ways[cities.indexOf(city)] != INF) printCitiesWay(cities.indexOf(city));
//                System.out.println(") " + countCitiesWay(cities.indexOf(city)) + "-cities / " + distanceWay(cities.indexOf(city)) + "km.");
//            }
//        }
//    }
//    public void printData(int startCityId) {
//        for (int v=0; v<cities.size(); v++) {
//            System.out.print("Вершина " + startCityId + "-" + v + ": ");
//            if (ways[v] != INF) {
//                printCitiesWay(v);
//            }
//            System.out.println();
//        }
//        System.out.print("Дистанция: ");
//        for (int v=0; v<cities.size(); ++v) {
//            if (ways[v] != INF) {
//                System.out.print("(" + startCityId + "-" + v + ")" + ways[v] + " ");
//            } else {
//                System.out.print("- ");
//            }
//        }
//    }

    @Override
    public boolean delRoad(String name){
        return delItem(roads, new Road(name, 0, 0));
    }
    @Override
    public boolean delCity(String byName) {
        boolean isDel = false;
        KeyCity key = new KeyCity(byName);
        int  idCity = byNameCities.get(key);
        for (Road road : getRoads(idCity)){ delRoad(road.getName()); }
        while (byNameCities.containsKey(key)) {
            if (delItem(cities,idCity) != null)
                isDel = true;
//            isDel = delItem(cities,idCity);
            key.incId();
        }
        return isDel;
    }

    public void indexesCities() {
        if (byNameCities == null){ byNameCities = new Hashtable<KeyCity, Integer>(cities.size()); } //if (byNameCities == null){ byNameCities = new HashMap(cities.size()); }
        for (int id = 0; id < cities.size(); id++){
            /*
             * обычная проверка (здесь постоянный порядок)
             */
            KeyCity key = new KeyCity(cities.get(id).getName());
            while (byNameCities.containsKey(key)) { key.incId(); }
            byNameCities.put(key, id);
        }
    }
    public void indexesRoads() {
        if (byCityRoads == null){ byCityRoads = new Hashtable<KeyRoad, Integer>(roads.size()); } //if (byCityRoads == null){ byCityRoads = new HashMap(roads.size()); }
        for (int id = 0; id < roads.size(); id++){
            /*
             * двойная проверка (если ID-города существует НО было добавлено в другом порядке)
             */
            KeyRoad key1 = new KeyRoad( roads.get(id).getIdCity1() );
            while (byCityRoads.containsKey(key1)) { key1.incId(); }
            byCityRoads.put(key1, id);
            KeyRoad key2 = new KeyRoad( roads.get(id).getIdCity2() );
            while (byCityRoads.containsKey(key2)) { key2.incId(); }
            byCityRoads.put(key2, id);
        }
    }
    public synchronized void indexesDijkstra(int startCityId) {
        Arrays.fill(isUsedCity, false);
        Arrays.fill(pred, -1);
        Arrays.fill(ways, INF);
        ways[startCityId] = 0; // кратчайшее расстояние до стартовой вершины равно 0
        // поскольку о отрицательную сторону алгоритм дейкстры НЕработает, поэтому переворачиваем местами вершины (внутри массива) в такой способ чтобы проход выполнялся только в прямом порядке
        for (int currentCityId=0; currentCityId<=startCityId; currentCityId++) {
            for (Road road:roads) {
                road.setWeight(Math.sqrt(Math.abs(getCity(road.getIdCity2()).getCoordinate().getX() - getCity(road.getIdCity1()).getCoordinate().getX())) + Math.abs(getCity(road.getIdCity2()).getCoordinate().getY() - getCity(road.getIdCity1()).getCoordinate().getY()));
                if(road.getIdCity2()==currentCityId){
                    int tmpCityId = road.getIdCity1();
                    road.setIdCity1(road.getIdCity2());
                    road.setIdCity2(tmpCityId);
                }
                // считываем граф, заданный списком ребер
                adj[road.getIdCity1()].add(road.getIdCity2());
                getCity(road.getIdCity1()).setWeight(road.getWeight());
            }
        }

        for (City c:cities) {
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            int cityId         = -1;
            int distanceToCity = INF;
            for (City city:cities) {
                if (isUsedCity[cities.indexOf(city)])
                    continue;
                if (distanceToCity < ways[cities.indexOf(city)])
                    continue;
                cityId          = cities.indexOf(city);
                distanceToCity  = ways[cities.indexOf(city)];
            }
            // рассматриваем все дуги, исходящие из найденной вершины
            for (int id=0; id<adj[cityId].size(); id++) {
                int cityWay = adj[cityId].get(id);
                int weightCityWay = (int)getCity(cityId).getWeight();
                // релаксация вершины
                if (ways[cityId]+weightCityWay < ways[cityWay]) {
                    ways[cityWay] = ways[cityId] + weightCityWay;
                    pred[cityWay] = cityId;
                }
            }
            // помечаем вершину v просмотренной, до нее найдено кратчайшее расстояние
            isUsedCity[cityId] = true;
        }
    }

    /**
     * Это объекты-ключи которые применяются для логики поиска элементов в списке по специальным критериям
     */
    private class KeyCity {
        private String name;
        private byte     id;

        public KeyCity(String name){
            this.name = name;
            id        = (byte) name.hashCode();
        }

        public void incId(){
            id = (byte) (id + 1);
        }

        @Override
        public boolean equals(Object key) {
            KeyCity that = (KeyCity) key;
            if (id == that.id && name.equals(that.name))
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }
    private class KeyRoad {
        private int idCity;
        private byte    id;

        public KeyRoad(int idCity){
            this.idCity = idCity;
            id          = (byte) idCity;
        }

        public void incId(){
            id = (byte) (id + 1);
        }

        @Override
        public boolean equals(Object key) {
            KeyRoad that = (KeyRoad) key;
            if (id == that.id && idCity == that.idCity)
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return idCity;
        }
    }
    /**
     * Это список который применяется для логики удаления элементов в списке по специальным критериям
     */
    private class Cities extends Vector<City> { //private class Cities extends ArrayList {
        @Override
        public boolean remove(Object o) {
            City c = (City) o;
            byNameCities.remove(new KeyRoad(c.getId()));
            return super.remove(c);
        }
    }
    private class Roads extends Vector<Road> { //private class Roads extends ArrayList {
        @Override
        public boolean remove(Object o) {
            Road r = (Road) o;
            byCityRoads.remove(new KeyRoad(roads.get(indexOf(r)).getIdCity1()));
            byCityRoads.remove(new KeyRoad(roads.get(indexOf(r)).getIdCity2()));
            return super.remove(r);
        }
//        @Override
//        public boolean contains(Object o){
//            Road r = (Road) o;
//            isCitiesRoad = false;
//            Collections.sort(roads, new Comparator<Road>() {
//                @Override
//                public int compare(Road r1, Road r2) {
////                        if ((r1.getIdCity1() == r2.getIdCity1() && r1.getIdCity2() == r2.getIdCity2())
////                                || (r1.getIdCity1() == r2.getIdCity2() && r1.getIdCity1() == r2.getIdCity2())) {
//                    if (r1.getIdCity1() == r2.getIdCity1()
//                            && r1.getIdCity2() == r2.getIdCity2()) {
//                        isCitiesRoad = true;
//                        return 0;
//                    }
//                    return 1;
//                }
//            });
//            if (!isCitiesRoad)
//                return super.contains(r);
//            return true;
//        }
    }
//    private boolean isCitiesRoad;
    /**
     * (это просто вспомогательные методы для безопасеоно удаления элементов из списка)
     */
    private <T> boolean delItem(List<T> items, T item){
        Iterator<T> iItems = items.iterator();
        while (iItems.hasNext()){
            if(iItems.next().equals(item)) {
                iItems.remove();
                return true;
            }
        }
        return false;
    }
    private <T> Object delItem(List<T> items, int idItem){
        if (items.size()<idItem)
            new IndexOutOfBoundsException();
        Iterator<T> iItems = items.iterator();
        for (int id=0; id<items.size(); id++){
            T delItem = (T)iItems.next();
            if (id==idItem){
                iItems.remove();
                return delItem;
            }
        }
        return null;
    }
//    private <T> boolean delItem(List<T> items, int idItem) {
//        if (items.size()<idItem)
//            new IndexOutOfBoundsException();
//        Iterator<T> iItems = items.iterator();
//        for (int id=0; id<items.size(); id++){
//            if (id==idItem){
//                iItems.remove();
//                return true;
//            }
//        }
//        return false;
//    }
    /**
     * процедура восстановления кратчайшего пути по массиву предком
     * @param startCityId
     */
    private int distanceWay(int startCityId) {
        return distanceWay(startCityId, 0);
    }
    private int distanceWay(int startCityId, int distance) {
        return (startCityId==-1) ? distance : ways[startCityId] + distanceWay(pred[startCityId],ways[startCityId]);
    }
    private int countCitiesWay(int startCityId) {
        return countCitiesWay(startCityId, 0);
    }
    private int countCitiesWay(int startCityId, int count) {
        return (startCityId==-1) ? count : countCitiesWay(pred[startCityId], count+1);
    }
    private static final int INF = 1000000;
    private boolean[] isUsedCity; // массив для хранения информации о пройденных и не пройденных вершинах
    private int[]           pred; // массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
    private int[]           ways; // массив для хранения расстояния от стартовой вершины
    private List<Integer>[]  adj; // список смежности
//    private void printCitiesWay(int startCityId) {
//        if (startCityId==-1)
//            return;
//        printCitiesWay(pred[startCityId]);
//        System.out.print(startCityId+" ");
//    }
}
