package example.testtask.townsandroads4;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class ServiceTest {

    private static String LOG_PROPERTIES_FILE = "src/test/resources/log4j.properties";
    private static Logger log = Logger.getLogger(ServiceTest.class);
    private Config conf;
    private Service service;

    @Before
    public void setUp() {
        service = new Service();
        log.warn(service.addCity("City-A", 100, 100));
        log.warn(service.addCity("City-B", 200, 200));
        log.warn(service.addCity("City-C", 300, 300));
        log.warn(service.addCity("City-D", 400, 400));
        log.warn(service.addCity("City-E", 500, 500));
        log.warn(service.addCity("City-E", 501, 501));
        log.warn(service.addCity("City-E", 502, 502));
        log.warn(service.addCity("City-E", 503, 503));
        log.warn(service.addCity("City-F", 100, 100));
        log.warn(service.addCity(null, 600, 600));
//        log.warn(service.addCity("City-E", 503, 503));
//        log.warn(service.addCity("City-A", 100, 100));
//        log.warn(service.addCity("City-B", 200, 200));
//        log.warn(service.addCity("City-E", 501, 501));
//        log.warn(service.addCity("City-C", 300, 300));
//        log.warn(service.addCity("City-D", 400, 400));
//        log.warn(service.addCity("City-E", 500, 500));
//        log.warn(service.addCity("City-E", 502, 502));
//        log.warn(service.addCity("City-F", 100, 100));
//        log.warn(service.addCity(null, 600, 600));

        service.initDijkstra();
        log.warn(service.addRoad("Road-0", service.getCity(0), service.getCity(2)));
        log.warn(service.addRoad("Road-1", service.getCity(0), service.getCity(4)));
        log.warn(service.addRoad("Road-2", service.getCity(4), service.getCity(5)));
        log.warn(service.addRoad("Road-3", service.getCity(4), service.getCity(7)));
        log.warn(service.addRoad("Road-4", service.getCity(1), service.getCity(1)));
        log.warn(service.addRoad("Road-5", null, service.getCity(7)));
        log.warn(service.addRoad("Road-6", null, null));
        log.warn(service.addRoad(null, service.getCity(5), service.getCity(7)));
        log.warn(service.addRoad("Road-3", service.getCity(5), service.getCity(7)));
        log.warn(service.addRoad("Road-7", service.getCity(555), service.getCity(777)));
        log.warn(service.addRoad("Road-8", service.getCity(4), service.getCity(7)));
        log.warn(service.addRoad("Road-9", service.getCity(0), service.getCity(4)));
        log.warn(service.addRoad("Road-10", service.getCity(4), service.getCity(7)));
        log.warn(service.addRoad("Road-11", service.getCity(4), service.getCity(7)));
        log.warn(service.addRoad("Road-12", service.getCity(4), service.getCity(7)));
    }

    @Test
    public void testCity(){
        service.indexesCities();

        log.info("--------------------[ Cities ]");
        for (City city : service.getCities()){
            log.warn(city);
        }
        log.info("--------------------[ City ]");
        for (City city : service.getCities("City")){
            log.warn(city);
        }
        log.info("--------------------[ City-C ]");
        for (City city : service.getCities("City-C")){
            log.warn(city);
        }
        log.info("--------------------[ City-E ]");
        for (City city : service.getCities("City-E")){
            log.warn(city);
        }
    }

    @Test
    public void testRoad(){
        service.indexesRoads();

        log.info("--------------------[ Roads ]");
        for (Road road : service.getRoads()){
            log.warn(road + " | " + service.getCity(road.getIdCity1()).getCoordinate() + " " + service.getCity(road.getIdCity2()).getCoordinate());
        }
        log.info("--------------------[ #4 >> Roads ]");
        for (Road road : service.getRoads(4)){
            log.warn(road + " | " + service.getCity(road.getIdCity1()).getCoordinate() + " " + service.getCity(road.getIdCity2()).getCoordinate());
        }
        log.info("--------------------[ #5 >> Roads ]");
        for (Road road : service.getRoads(5)) {
            log.warn(road + " | " + service.getCity(road.getIdCity1()).getCoordinate() + " " + service.getCity(road.getIdCity2()).getCoordinate());
        }
        log.info("--------------------[ #7 >> Roads ]");
        for (Road road : service.getRoads(7)) {
            log.warn(road + " | " + service.getCity(road.getIdCity1()).getCoordinate() + " " + service.getCity(road.getIdCity2()).getCoordinate());
        }
        log.info("--------------------[ #555 >> Roads ]");
        for (Road road : service.getRoads(555)) {
            log.warn(road + " | " + service.getCity(road.getIdCity1()).getCoordinate() + " " + service.getCity(road.getIdCity2()).getCoordinate());
        }
    }

    @Test
    public void testDelRoad(){
        service.indexesRoads();

        log.info("--------------------[ 'Road-3' >> Road-Del ]");
        log.warn(service.delRoad("Road-3"));

        log.info("--------------------[ Roads-Del ]");
        for (Road road : service.getRoads()){
            log.warn(road + " | " + service.getCity(road.getIdCity1()).getCoordinate() + " " + service.getCity(road.getIdCity2()).getCoordinate());
        }
    }

    @Test
    public void testDelCity(){
        service.indexesCities();
        service.indexesRoads();

        log.info("--------------------[ (Pre) Cities-Del ]");
        for (City city : service.getCities()){
            log.warn(city);
        }
        log.info("--------------------[ (Pre) Roads-Del ]");
        for (Road road : service.getRoads()){
            log.warn(road + " | " + service.getCity(road.getIdCity1()).getCoordinate() + " " + service.getCity(road.getIdCity2()).getCoordinate());
        }
        log.info("--------------------[ 'City-E' >> City-Del ]");
        log.warn(service.delCity("City-E"));

        log.info("--------------------[ (Post) Cities-Del ]");
        for (City city : service.getCities()){
            log.warn(city);
        }
        log.info("--------------------[ (Post) Roads-Del ]");
        for (Road road : service.getRoads()){
            log.warn(road + " | " + service.getCity(road.getIdCity1()).getCoordinate() + " " + service.getCity(road.getIdCity2()).getCoordinate());
        }
    }

    @Test
    public void testDijkstra() {
        Service service2 = new Service();
        service2.addCity("City-A", 10, 20);
        service2.addCity("City-B", 20, 30);
        service2.addCity("City-C", 30, 30);
        service2.addCity("City-D", 40, 20);
        service2.addCity("City-E", 30, 10);
        service2.addCity("City-F", 20, 10);
        service2.addCity("City-G", 10, 10);

        service2.initDijkstra();

        service2.addRoad("Road-1", service2.getCity(0), service2.getCity(1));
        service2.addRoad("Road-2", service2.getCity(1), service2.getCity(2));
        service2.addRoad("Road-3", service2.getCity(1), service2.getCity(3));
        service2.addRoad("Road-4", service2.getCity(1), service2.getCity(4));
        service2.addRoad("Road-5", service2.getCity(1), service2.getCity(5));
        service2.addRoad("Road-6", service2.getCity(2), service2.getCity(3));
        service2.addRoad("Road-7", service2.getCity(2), service2.getCity(4));
        service2.addRoad("Road-8", service2.getCity(3), service2.getCity(4));
        service2.addRoad("Road-9", service2.getCity(3), service2.getCity(5));

//        service2.indexesDijkstra(2);
//        service2.printData(2);
//        System.out.println("-----------------------[ get Cities-Way(3, 5) ]");
//        service2.getCitiesWay1(2, 5);
//        service2.getCitiesWay1(2, 4);
//        service2.getCitiesWay1(2, 3);
//        System.out.println("-----------------------[ (2) get Cities-Way(3, 5) ]");
//        service2.getCitiesWay(3, 5);
//        service2.getCitiesWay(3, 4);
//        service2.getCitiesWay(3, 3);
        System.out.println("-----------------------[ (2) get Cities-Way(0, 6) ]");
        log.warn(service2.getCitiesWay(3, 5));
        log.warn(service2.getCitiesWay(3, 4));
        log.warn(service2.getCitiesWay(3, 3));
        log.warn(service2.getCitiesWay(3, 6));
        log.warn(service2.getCitiesWay(0, 6));
        log.warn(service2.getCitiesWay(2, 5));
    }

}