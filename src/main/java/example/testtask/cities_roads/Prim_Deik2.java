package example.testtask.cities_roads;

//1. Подключение пакетов
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/**
 * Алгоритм Дейкстры
 * *****************
 * {@link http://sampo.ru/~infmod/data/model2/Deikstra_stranica.html}
 * {@link http://sampo.ru/~infmod/data/model2/Text/Prim_Deik.txt
 */
public class Prim_Deik2 extends Applet {

    public int res[], flag = 1;
    public int x[] = {440,110,330,550,220}, y[] = {330,420,240,150,510};

    public int n = 5;
    public int a[][] = new int[n][n];

    /**
     * 1. Инициализация элементов управления
     */
    @Override
    public void init() {
        init_matr();
        flag = 1;
        repaint();

        dijkstraAlgorithm();
        flag = 0;
        repaint();
    }

    /**
     * 2. Инициализация матрицы стоимости
     */
    public void init_matr() {
        // Сначала предполагается, что рёбер нет
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                a[i][j] = 10000;
            }
        }

        // Случайным образом выбирается: будет ребро между парой вершин или нет
        for (int j=0; j<n; j++) {
            for (int kr=0; kr<n; kr++) {
                if (j!=kr) {
//                    lucky = (int) Math.floor(Math.random()*100);
//                    if (lucky>50) {
                    //a[j][kr] = 10; //a[j][kr] = (int)Math.sqrt(Math.pow(x[j]-x[kr],2)+Math.pow(y[j]-y[kr],2));
                    a[kr][j] = (int)Math.sqrt(Math.pow(x[j]-x[kr],2)+Math.pow(y[j]-y[kr],2));
//                    }
                }
            }
        }
    }

    /**
     * 3. Cам алгоритм Дейкстры-Прима
     */
    public void dijkstraAlgorithm() {
        int[] p   = new int[n];
        int[] d   = new int[n];
        int[] v   = new int[n];
        res = new int[n];
        // Двумерная матрица res-хранит в себе рёбра остова.
        // Если ребро между вершинами i и j входит в остов, то в матрице значение ячейки res[i][j]=1, иначе 0.
        // сначала в остове рёбер нет
        for (int i=0; i<n; i++) {
            res[i] = -1;
        }

        for (int i=0; i<n; i++) {
            v[i] = 0; // массив с информ. о посещении вершин
            p[i] = 0; // массив c вершинами предками
            d[i] = 0; // массив c вершинами потомками
        }

        int k    = 0; // корень дерева вершина с номером 0
        v[0] = 1;

        for (int i=1; i<n; i++) {
            d[i] = a[0][i];
            p[i] = 0;
        }

        for (int i=0; i<n-1; i++) {
            int min = 10000;
            for (int j=0; j<n; j++) {
                if (v[j]==0 && d[j]<min) {
                    min = d[j];
                    k   = j;
                }
            }

            res[p[k]] = k; // между верш к и p[k] есть ребро остова
            v[k]      = 1;

            for (int j=0; j<n; j++) {
                if(v[j]==0 && d[j]>a[k][j]) {
                    p[j] = k;
                    d[j] = a[k][j];
                }
            }
        }
    }

    /**
     * 4. Рисование графа
     * @param g
     */
    public void paint(Graphics g) {
        String r;
        g.setColor(Color.black);
        g.fillRect(0, 0, getSize().width, getSize().height); // цвет апплета
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                g.setColor(Color.yellow);
                g.drawOval(x[i],y[i],20,20);
                r = String.valueOf(i);
                g.drawString(r,x[i]+7,y[i]+15);
                g.drawOval(x[j],y[j],20,20);
                r = String.valueOf(j);
                g.drawString(r,x[j]+7,y[j]+15);
                if (a[i][j]!=10000 && a[j][i]!=10000) {
                    g.setColor(Color.blue);
                    g.drawLine(x[i]+9,y[i]+12,x[j]+9,y[j]+12);
                    g.setColor(Color.white);
                    g.drawString(""+a[i][j],(x[i]+x[j])/2+3,(y[i]+y[j])/2+3);
                }
            }
        }

        // Нажата кнопка-построить остов.
        // Дерево строится по матрице res
        if (flag==0) {
            for(int i=0; i<n; i++) {
                if(res[i]!=-1) {
                    g.setColor(Color.red);
                    g.drawLine(x[i]+9,y[i]+10,x[res[i]]+9,y[res[i]]+10);
                }
            }
        }
    }
}
