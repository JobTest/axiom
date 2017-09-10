package example.testtask;

import org.apache.log4j.Logger;

import java.util.*;

public abstract class ServiceSome implements ServiceHand {
    private static final Logger log = Logger.getLogger(ServiceSome.class);
    private List<City> cities;
    private List<Road>  roads;
    private Map<KeyCity, Integer> byNameCities;
    private Map<KeyRoad, Integer>  byCityRoads;
    private final int               INF = 1000000;
    private boolean[]      isUsedCities;
    private int[]            parentWays;
    private int[]                  ways;
    private List<Integer>[] adjacencies;

    public ServiceSome() {
        cities = new Cities();
        roads  = new Roads();
    }

    public synchronized Items items(){
        indexCities();
        indexRoads();
        return new ItemsHand();
    }
    private class ItemsHand implements Items {
        @Override
        public List<City> searchCities(String name) throws NullPointerException, IllegalArgumentException {
            log.warn("'" + name + "';");
            if (name==null)
                throw new NullPointerException("City-Name is Null");
            else if (name.isEmpty())
                throw new IllegalArgumentException("City-Name is Empty");

            return srchCities(name);
        }

        @Override
        public List<Road> searchRoads(City city) throws NullPointerException {
            log.warn("'" + city + "';");
            if (city==null)
                throw new IllegalArgumentException("City is Null");

            return srchRoads(city.getId());
        }

        @Override
        public boolean deleteCities(String name) throws NullPointerException, IllegalArgumentException {
            log.warn("'" + name + "';");
            if (name==null)
                throw new NullPointerException("City-Name is Null");
            else if (name.isEmpty())
                throw new IllegalArgumentException("City-Name is Empty");

            return delCities(name);
        }

        @Override
        public boolean deleteRoad(String name) throws NullPointerException, IllegalArgumentException {
            log.warn("'" + name + "';");
            if (name==null)
                throw new NullPointerException("Road-Name is Null");
            else if (name.isEmpty())
                throw new IllegalArgumentException("Road-Name is Empty");

            return delRoad(name);
        }
    }

    List<City> getCities() {
        return cities;
    }
    City getCity(int id) throws IllegalArgumentException {
        if (id < 0)
            throw new IllegalArgumentException("Illegal City-ID");
        return (cities.size()<=id) ? null : cities.get(id);
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
    private int distanceWay(int startCityId, int distance) {
        return (startCityId==-1) ? distance : ways[startCityId] + distanceWay(parentWays[startCityId],ways[startCityId]);
    }
    private int citiesWay(int startCityId, int count) {
        return (startCityId==-1) ? count : citiesWay(parentWays[startCityId], count + 1);
    }

    private boolean delCities(String name) {
        boolean isDel = false;
        KeyCity key = new KeyCity(name);
        int idCity = byNameCities.get(key);
        for (Road road : srchRoads(idCity)) { delRoad(road.getName()); }
        while (byNameCities.containsKey(key)) {
            isDel = delItem(cities,idCity);
            key.incId();
        }
        if (isDel) initDijkstra(false);
        return isDel;
    }
    private boolean delRoad(String name) {
        return delItem(roads, new Road(name, 0, 0));
    }
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

    private List<City> srchCities(String name) {
        KeyCity     key = new KeyCity(name);
        List<City> copy = new Vector<City>();
        while (byNameCities.containsKey(key)) {
            if (byNameCities.get(key)<cities.size()) {
                copy.add(cities.get(byNameCities.get(key)));
                key.incId();
            } else {
                break;
            }
        }
        return copy;
    }
    private List<Road> srchRoads(int cityId) {
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
    private void indexDijkstra(final int maxCities, final int startCityId) {
        initDijkstra(true);

        Arrays.fill(isUsedCities, false);
        Arrays.fill(parentWays, -1);
        Arrays.fill(ways, INF);
        ways[startCityId] = 0;
        synchronized (this) {
            for (int id = 0; id <= startCityId; id++) {
                for (Road road : roads) {
                    road.setWeight(Math.sqrt(Math.abs(getCity(road.getIdCity2()).getCoordinate().getX() - getCity(road.getIdCity1()).getCoordinate().getX())) + Math.abs(getCity(road.getIdCity2()).getCoordinate().getY() - getCity(road.getIdCity1()).getCoordinate().getY()));
                    if (road.getIdCity2() == id) {
                        int tmpId = road.getIdCity1();
                        road.setIdCity1(road.getIdCity2());
                        road.setIdCity2(tmpId);
                    }
                    adjacencies[road.getIdCity1()].add(road.getIdCity2());
                    getCity(road.getIdCity1()).setWeight(road.getWeight());
                }
            }

            for (int c = 0; c < maxCities; c++) {
                int cityId = -1;
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
                for (int id = 0; id < adjacencies[cityId].size(); id++) {
                    int cityWay = adjacencies[cityId].get(id);
                    int weightCityWay = (int) getCity(cityId).getWeight();
                    if ((ways[cityId] + weightCityWay) < ways[cityWay]) {
                        ways[cityWay] = ways[cityId] + weightCityWay;
                        parentWays[cityWay] = cityId;
                    }
                }
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
                for (int c = 0; c < cities.size(); c++) adjacencies[c] = new ArrayList();
            }
        }
    }

    private class Cities extends Vector<City> {
        @Override
        public synchronized boolean add(City city){
            city.setId(cities.size());
            return super.add(city);
        }

        @Override
        public synchronized City remove(int cityId) {
            byNameCities.remove(new KeyCity(cities.get(cityId).getName()));
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
}