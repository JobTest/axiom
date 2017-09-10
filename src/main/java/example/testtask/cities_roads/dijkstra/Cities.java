package example.testtask.cities_roads.dijkstra;

/**
 * Задача о городах и дорогах
 * *************************
 * Дорога может соединять только два разных города, дорога не может проходить через город и соединять третий город (при таком примере, будет две разных дороги).
 * * * * * * * * * * * * * *
 * Согласно условию задачи:
 * + города могут соединяться между собой только прямой дорогой...(прямая линия)...независимо от дальности расположения города - такая прамая линия между городами может быть только одна!
 * + если между городами распологается третий город (который пересекается на это прямой дороге)...- тогда это исключает дорогу между такими городами
 * + (город может иметь много дорог НО дорога всегда имеет только два города)
 */
public class Cities {

    double[][] cities = {{2,2},{5,3},{8,4},{11,5},{3,15},{1,1},{1,2},{1,3},{1,4},{0,0},{0,0},{0,1},{0,3},{0,10},{2,1},{2,3},{2,10}};

    public static void main(String[] args) {
        Cities c = new Cities();
        System.out.println( c.addRoad(c.cities, c.cities[5], c.cities[0]) );
        System.out.println( c.addRoad(c.cities, c.cities[14], c.cities[16]) );
    }

    boolean addRoad(double[][] cities, double[] city1, double[] city2){
        for (int city3=0; city3<cities.length; city3++) {
            double deltaX2 = 0, deltaY2 = 0, deltaX3 = 0, deltaY3 = 0;
            if(city1[0]<=cities[city3][0] && cities[city3][0]<=city2[0]
                    && city1[1]<=cities[city3][1] && cities[city3][1]<=city2[1]){
//                System.out.println("YES");
                deltaX2 = city2[0]-city1[0];
                deltaY2 = city2[1]-city1[1];
                deltaX3 = cities[city3][0]-city1[0];
                deltaY3 = cities[city3][1]-city1[1];
//                System.out.println("("+deltaX2+"/"+deltaY2+")" + (deltaX2/deltaY2) + " == " + "("+deltaX3+"/"+deltaY3+")" + (deltaX3/deltaY3));
                if(deltaX2==0 && deltaX3==0){
                    return false;
                } else if(deltaY2==0 && deltaY3==0){
                    return false;
                } else if((deltaX2/deltaY2) == (deltaX3/deltaY3)) {
                    return false;
                }
            }
        }
        return true;
    }

}
