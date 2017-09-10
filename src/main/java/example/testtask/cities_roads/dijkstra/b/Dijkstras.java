package example.testtask.cities_roads.dijkstra.b;

import java.util.*;

/**
 * https://github.com/mburst/dijkstras-algorithm/blob/master/Dijkstras.java
 * ************************************************************************
 * https://ru.wikipedia.org/wiki/Алгоритм_Дейкстры
 * http://habrahabr.ru/post/111361/
 * http://comp-science.narod.ru/KPG/Deikstr.htm
 * http://school29.smoladmin.ru/arbuzov/deikstra.html
 * http://neerc.secna.ru/Algor/algo_base_graph_spath.html
 * http://codeforces.com/blog/entry/5558
 * http://e-maxx.ru/algo/mst_kruskal_with_dsu
 * http://ru.stackoverflow.com/questions/84196/Алгоритм-оптимизации-маршрута
 * https://books.google.com.ua/books?id=FpueE_bteEcC&pg=PA639&lpg=PA639&dq=%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC+%D0%B4%D0%B5%D0%B9%D0%BA%D1%81%D1%82%D1%80%D1%8B+java&source=bl&ots=bhR-EwhKFx&sig=YHdIStDLsz6CSDGGKj16HlKkiP0&hl=ru&sa=X&ved=0ahUKEwiRnc-zwbjKAhUpv3IKHVfJDXU4ChDoAQgsMAM#v=onepage&q=%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%20%D0%B4%D0%B5%D0%B9%D0%BA%D1%81%D1%82%D1%80%D1%8B%20java&f=false
 * https://books.google.com.ua/books?id=OEG6AgAAQBAJ&pg=PA252&lpg=PA252&dq=%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC+%D0%B4%D0%B5%D0%B9%D0%BA%D1%81%D1%82%D1%80%D1%8B+java&source=bl&ots=cJuRGyysji&sig=clN85Mnh3KZjCrshL6pOyhFjt8E&hl=ru&sa=X&ved=0ahUKEwiRnc-zwbjKAhUpv3IKHVfJDXU4ChDoAQhQMAk#v=onepage&q=%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%20%D0%B4%D0%B5%D0%B9%D0%BA%D1%81%D1%82%D1%80%D1%8B%20java&f=false
 ** https://sites.google.com/site/indy256/algo/dijkstra_heap
 * https://github.com/akoubensky/algo/tree/master/Lecture22/src/floyd
 ** https://github.com/akoubensky/algo/blob/master/Lecture21/src/dijkstra/Dijkstra.java
 * http://foxford.ru/wiki/informatika/algoritm-deykstry
 ** https://github.com/mburst/dijkstras-algorithm/blob/master/Dijkstras.java
 * http://astralax.ru/articles/pathway
 * http://habrahabr.ru/post/119158/
 * http://www.gamedev.ru/code/forum/?id=200950
 * https://www.weblancer.net/projects/162246.html
 */
public class Dijkstras {

    public static void main(String[] args) {
        Graph g = new Graph();

//        g.addVertex('A', Arrays.asList(new Vertex('B', 7), new Vertex('C', 8)));
//        g.addVertex('B', Arrays.asList(new Vertex('A', 7), new Vertex('F', 2)));
//        g.addVertex('C', Arrays.asList(new Vertex('A', 8), new Vertex('F', 6), new Vertex('G', 4)));
//        g.addVertex('D', Arrays.asList(new Vertex('F', 8)));
//        g.addVertex('E', Arrays.asList(new Vertex('H', 1)));
//        g.addVertex('F', Arrays.asList(new Vertex('B', 2), new Vertex('C', 6), new Vertex('D', 8), new Vertex('G', 9), new Vertex('H', 3)));
//        g.addVertex('G', Arrays.asList(new Vertex('C', 4), new Vertex('F', 9)));
//        g.addVertex('H', Arrays.asList(new Vertex('E', 1), new Vertex('F', 3)));
//
//        System.out.println(g.getShortestPath('A', 'H'));

        g.addVertex('a', Arrays.asList(new Vertex('1', 1), new Vertex('A', 1)));
        g.addVertex('b', Arrays.asList(new Vertex('2', 2), new Vertex('B', 2)));
        g.addVertex('c', Arrays.asList(new Vertex('3', 3), new Vertex('C', 3), new Vertex('G', 1)));
        g.addVertex('d', Arrays.asList(new Vertex('4', 4)));
        g.addVertex('e', Arrays.asList(new Vertex('5', 5)));
        g.addVertex('f', Arrays.asList(new Vertex('6', 6), new Vertex('D', 4), new Vertex('D', 2), new Vertex('G', 3), new Vertex('H', 4)));
        g.addVertex('g', Arrays.asList(new Vertex('7', 7), new Vertex('E', 5)));
        g.addVertex('h', Arrays.asList(new Vertex('8', 8), new Vertex('F', 6)));

        System.out.println(g.getShortestPath('8', 'c'));
    }

}

class Vertex implements Comparable<Vertex> {

    private Character id;
    private Integer distance;

    public Vertex(Character id, Integer distance) {
        super();
        this.id = id;
        this.distance = distance;
    }

    public Character getId() {
        return id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setId(Character id) {
        this.id = id;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((distance == null) ? 0 : distance.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (distance == null) {
            if (other.distance != null)
                return false;
        } else if (!distance.equals(other.distance))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vertex [id=" + id + ", distance=" + distance + "]";
    }

    @Override
    public int compareTo(Vertex o) {
        return this.distance < o.distance ? -1 : this.distance == o.distance ? 0 : 1;
    }

}

class Graph {

    private final Map<Character, List<Vertex>> vertices;

    public Graph() {
        this.vertices = new LinkedHashMap<Character, List<Vertex>>();
    }

    public void addVertex(Character character, List<Vertex> vertex) {
        this.vertices.put(character, vertex);
    }

    public List<Character> getShortestPath(Character start, Character finish) {
        Map<Character, Integer> distances = new LinkedHashMap<Character, Integer>();
        PriorityQueue<Vertex> nodes = new PriorityQueue<Vertex>();
        Map<Character, Vertex> previous = new LinkedHashMap<Character, Vertex>();
        List<Character> path = new LinkedList<Character>();

        for(Character vertex : vertices.keySet()) {
            if (vertex == start) {
                distances.put(vertex, 0);
                nodes.add(new Vertex(vertex, 0));
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
                nodes.add(new Vertex(vertex, Integer.MAX_VALUE));
            }
            previous.put(vertex, null);
        }

        while (!nodes.isEmpty()) {
            Vertex smallest = nodes.poll();
            if (smallest.getId() == finish) {
                path = new LinkedList<Character>();
                while (previous.get(smallest.getId()) != null) {
                    path.add(smallest.getId());
                    smallest = previous.get(smallest.getId());
                }
                return path;
            }

            if (distances.get(smallest.getId()) == Integer.MAX_VALUE) {
                break;
            }

            for (Vertex neighbor : vertices.get(smallest.getId())) {
                Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
                if (alt < distances.get(neighbor.getId())) {
                    distances.put(neighbor.getId(), alt);
                    previous.put(neighbor.getId(), smallest);

                    forloop:
                    for(Vertex n : nodes) {
                        if (n.getId() == neighbor.getId()) {
                            n.setDistance(alt);
                            break forloop;
                        }
                    }
                }
            }
        }

        return new ArrayList<Character>(distances.keySet());
    }

}
