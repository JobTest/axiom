package example.testtask.cities_roads;

import java.util.*;
import java.util.List;

/**
 * {@link http://developer.alexanderklimov.ru/android/java/break.php}
 *
 ** {@link http://www.mkyong.com/spring-mvc/spring-mvc-hello-world-example/}
 ** {@link http://mp3indirio.net/indir/l7gBzoiX6Eo/45-spring-spring-mvc/}
 * {@link http://mp3indirio.net/mp3/Батыршинов-Тимур-spring/}
 ** {@link http://www.seostella.com/ru/article/2012/04/23/spring-3-i-controller-chast-1.html}
 * {@link http://www.seostella.com/ru/article/2012/04/23/spring-3-i-controller-chast-2.html}
 * {@link http://www.seostella.com/ru/article/2012/04/27/spring-3-i-controller-chast-3-cookievalue-i-requestheader.html}
 * {@link http://www.dataart.ua/blog/2014/02/sozdanie-custom-scope-v-jee-i-spring/}
 * {@link http://www.finecosoft.ru/SpringDI}
 */
/*
 * 1. Попытаемся отделить логику
 *    --------------------------
 *    Обычный список элементов представляет собой - просто хранилище для объектов (и больше ничего...потому-что логика, или изменение логики, никак недолжно влиять на наличие элементоа в списке)
 *    Логика может быть разной и она всегда может меняет свое поведение...конкретно для этой цели я отдельно реализую специальный тип списка (который будет удовлетвотрять по требованию)
 */

public abstract class ServiceSome implements ServiceHand {
    /*
     * Вся логика реализована в этом абстрактном классе
     */

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

    private final int               INF = 1000000;
    private boolean[]      isUsedCities; // массив для хранения информации о пройденных и не пройденных вершинах
    private int[]            parentWays; // массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
    private int[]                  ways; // массив для хранения расстояния от стартовой вершины
    private List<Integer>[] adjacencies; // список смежности

    public ServiceSome() {
        cities = new Cities();
        roads  = new Roads();
    }

    /**
     * Этот метод будет предварительно выполнять индексацию списков (хеш-карта реализует логику-поиска) для поиска... и последующего удаления
     */
    public synchronized Search search(){
        indexCities();
        indexRoads();
        return new Srch();
    }

    List<City> getCities() {
        return cities;
    }
    City getCity(int id) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (id<0) new IllegalArgumentException("Illegal City-ID");
        try {
            return cities.get(id);
        } catch (IndexOutOfBoundsException iobe) { new IndexOutOfBoundsException("Not Found City-ID"); }
        return null;
    }

    List<Road> getRoads() {
        return roads;
    }
    boolean isRoad(int firstCityId, int lastCityId) {
        indexDijkstra(cities.size(), firstCityId);

        int distanceWay = 0, citiesWay = 0;
        Iterator<City> iCities = cities.iterator();
        for (int id=0; id<cities.size(); id++) {
            iCities.next();
            if (id==lastCityId) {
                citiesWay = citiesWay(id, 0);
                distanceWay = distanceWay(id, 0);
            }
        }
        return (1==citiesWay && INF<=distanceWay) ? true : false;
    }


    private boolean deleteCity(String name) {
        boolean isDel = false;
        KeyCity key = new KeyCity(name);
        int idCity = byNameCities.get(key);
        for (Road road : searchRoads(idCity)) { deleteRoad(road.getName()); }
        while (byNameCities.containsKey(key)) {
            isDel = delItem(cities,idCity);
            key.incId();
        }
        if (isDel) initDijkstra(false);
        return isDel;
    }
    private boolean deleteRoad(String name) {
        return delItem(roads, new Road(name, 0, 0));
    }

    private List<Road> searchRoads(int cityId) {
        KeyRoad     key = new KeyRoad(cityId);
        List<Road> copy = new Vector<Road>();
        while (byCityRoads.containsKey(key)) {
            if (byCityRoads.get(key) < roads.size()) {
                copy.add(roads.get(byCityRoads.get(key)));
                key.incId();
            } else {
                break;
            }
        }
        return copy;
    }

    private int indexCities() {
        int indexes = 0;
        synchronized (this) {
                if (byNameCities == null){ byNameCities = new Hashtable<KeyCity, Integer>(cities.size()); }
            while (indexes < cities.size()) {
                    /*
                     * обычная проверка (здесь постоянный порядок)
                     */
                KeyCity key = new KeyCity(cities.get(indexes).getName());
                while (byNameCities.containsKey(key)) { key.incId(); }
                byNameCities.put(key, indexes);
                ++indexes;
            }
        }
        return indexes;
    }
    private int indexRoads() {
        int indexes = 0;
        synchronized (this) {
            if (byCityRoads == null){ byCityRoads = new Hashtable<KeyRoad, Integer>(roads.size()); }
            while (indexes < roads.size()) {
                /*
                 * двойная проверка (если ID-города существует НО было добавлено в другом порядке)
                 */
                KeyRoad key1 = new KeyRoad(roads.get(indexes).getIdCity1());
                while (byCityRoads.containsKey(key1)) { key1.incId(); }
                byCityRoads.put(key1, indexes);
                KeyRoad key2 = new KeyRoad(roads.get(indexes).getIdCity2());
                while (byCityRoads.containsKey(key2)) { key2.incId(); }
                byCityRoads.put(key2, indexes);
                ++indexes;
            }
        }
        return indexes;
    }
    private void indexDijkstra(int maxCities, int startCityId) {
        initDijkstra(true);

        Arrays.fill(isUsedCities, false);
        Arrays.fill(parentWays, -1);
        Arrays.fill(ways, INF);
        ways[startCityId] = 0; // кратчайшее расстояние до стартовой вершины равно 0
        synchronized (this) {
            // поскольку о отрицательную сторону алгоритм дейкстры НЕработает, поэтому переворачиваем местами вершины (внутри массива) в такой способ чтобы проход выполнялся только в прямом порядке
            for (int id = 0; id <= startCityId; id++) {
                for (Road road : roads) {
                    road.setWeight(Math.sqrt(Math.abs(getCity(road.getIdCity2()).getCoordinate().getX() - getCity(road.getIdCity1()).getCoordinate().getX())) + Math.abs(getCity(road.getIdCity2()).getCoordinate().getY() - getCity(road.getIdCity1()).getCoordinate().getY()));
                    if (road.getIdCity2() == id) {
                        int tmpId = road.getIdCity1();
                        road.setIdCity1(road.getIdCity2());
                        road.setIdCity2(tmpId);
                    }
                    // считываем граф, заданный списком ребер
                    adjacencies[road.getIdCity1()].add(road.getIdCity2());
                    getCity(road.getIdCity1()).setWeight(road.getWeight());
                }
            }

            for (int c = 0; c < maxCities; c++) {
                int cityId = -1;
                //выбираем вершину, кратчайшее расстояние до которого еще не найдено
                Iterator<City> iCities = cities.iterator();
                int distance = INF;
                for (int id = 0; id < maxCities; id++) {
                    iCities.next();
                    if (isUsedCities[id])
                        continue;
                    if (distance < ways[id])
                        continue;
                    cityId = id;
                    distance = ways[id];
                }
                // рассматриваем все дуги, исходящие из найденной вершины
                for (int id = 0; id < adjacencies[cityId].size(); id++) {
                    int cityWay = adjacencies[cityId].get(id);
                    int weightCityWay = (int) getCity(cityId).getWeight();
                    // релаксация вершины
                    if ((ways[cityId] + weightCityWay) < ways[cityWay]) {
                        ways[cityWay] = ways[cityId] + weightCityWay;
                        parentWays[cityWay] = cityId;
                    }
                }
                // помечаем вершину v просмотренной, до нее найдено кратчайшее расстояние
                isUsedCities[cityId] = true;
            }
        }
    }
    private void initDijkstra(boolean isInit) {
        if (isInit){
            synchronized (this) {
                isUsedCities = new boolean[cities.size()];
                parentWays   = new int[cities.size()];
                ways         = new int[cities.size()];
                adjacencies  = new ArrayList[cities.size()];
                for (int c = 0; c < cities.size(); c++) { adjacencies[c] = new ArrayList(); } // инициализируем списка смежности графа размерности n
            }
        }
    }

    /**
     * (вспомогательные методы для безопасного удаления элементов из списка)
     */
    private <T> boolean delItem(List<T> items, T item) {
        if (items==null) return false;
        Iterator<T> iItems = items.iterator();
        while (iItems.hasNext()){
            if(iItems.next().equals(item)) {
                iItems.remove();
                return true;
            }
        }
        return false;
    }
    private <T> boolean delItem(List<T> items, int idItem) {
        if (items==null) return false;
        Iterator<T> iItems = items.iterator();
        for (int id=0; id<items.size(); id++){
            iItems.next();
            if (id==idItem){
                iItems.remove();
                return true;
            }
        }
        return false;
    }
    /**
     * процедура восстановления кратчайшего пути по массиву предком
     * @param startCityId
     */
    private int distanceWay(int startCityId, int distance) {
        return (startCityId==-1) ? distance : ways[startCityId] + distanceWay(parentWays[startCityId],ways[startCityId]);
    }
    private int citiesWay(int startCityId, int count) {
        return (startCityId==-1) ? count : citiesWay(parentWays[startCityId], count + 1);
    }

    /**
     * Это список который применяется для логики удаления элементов в списке по специальным критериям
     */
    private class Cities extends Vector<City> {
        @Override
        public synchronized boolean add(City city){
            city.setId(cities.size());
            return super.add(city);
        }

        @Override
        public synchronized City remove(int cityId) {
            byNameCities.remove(new KeyRoad(cityId));
            return super.remove(cityId);
        }
    }
    private class Roads extends Vector<Road> {
        @Override
        public synchronized boolean add(Road road){
            road.setId(roads.size());
            return super.add(road);
        }

        @Override
        public synchronized Road remove(int roadId) {
            byCityRoads.remove(new KeyRoad(roads.get(roadId).getIdCity1()));
            byCityRoads.remove(new KeyRoad(roads.get(roadId).getIdCity2()));
            return super.remove(roadId);
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
     * Это отдельная реализация интерфейса-поиска...
     */
    private class Srch implements Search {
        @Override
        public List<City> cities(String name) {
            KeyCity     key = new KeyCity(name);
            List<City> copy = new Vector<City>();
            while (byNameCities.containsKey(key)) {
                if (byNameCities.get(key) < cities.size()) {
                    copy.add(cities.get(byNameCities.get(key)));
                    key.incId();
                } else {
                    break;
                }
            }
            return copy;
        }

        @Override
        public List<Road> roads(int cityId) {
            KeyRoad     key = new KeyRoad(cityId);
            List<Road> copy = new Vector<Road>();
            while (byCityRoads.containsKey(key)) {
                if (byCityRoads.get(key) < roads.size()) {
                    copy.add(roads.get(byCityRoads.get(key)));
                    key.incId();
                } else {
                    break;
                }
            }
            return copy;
        }

        @Override
        public boolean delCities(String name) throws NullPointerException, IllegalArgumentException {
            if (name==null)
                throw new NullPointerException("City-Name is Null");
            else if (name.isEmpty())
                throw new IllegalArgumentException("City-Name is Empty");

            return deleteCity(name);
        }

        @Override
        public boolean delRoads(String name) throws NullPointerException, IllegalArgumentException {
            if (name==null)
                throw new NullPointerException("Road-Name is Null");
            else if (name.isEmpty())
                throw new IllegalArgumentException("Road-Name is Empty");

            return deleteRoad(name);
        }
    }
}
