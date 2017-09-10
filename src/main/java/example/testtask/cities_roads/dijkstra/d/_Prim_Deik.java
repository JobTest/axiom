//package example.testtask.cities_roads.dijkstra.d;
//
////1. Подключение пакетов
//import java.awt.*;
//import java.awt.event.*;
//import java.applet.*;
//
//public class Prim_Deik  extends Applet implements ActionListener
//{  Panel panel1  ;
//    Panel panel2 ;
//    Button buttons[]= new Button[2];// Кнопки
//    Label nadpis=new Label("Введите количество вершин");
//    TextField tf=new TextField(); //текстовое поле
//    String names[]={"Построить граф","Построить остов"};
//    int a[][],res[],x [],y[],n,i,j,lucky,k,p[],d[],min=10000,v[],flag1=1,kr;
//    String r,h;
//    // 2 Инициализация элементов управления
//    public void init()
//    {setLayout (new BorderLayout());
//        panel1=new Panel(new GridLayout(1,2));
//        panel2= new Panel(new GridLayout(1,2));
//        panel2.setBackground(new Color(190,190,160));
//        for (i=0;i<2;i++)
//        { buttons[i]=new Button(names[i]);
//            panel1.add(buttons[i]);
//            buttons[i].addActionListener(this);
//        }
//        nadpis.setBackground(new Color(190,190,160));
//        panel2.add(nadpis);
//        panel2.add(tf);
//        add("North",panel2);
//        add("South",panel1);
//    }
//    //3. Инициализация матрицы стоимости
//    public void init_matr()
//    { x=new int[n];
//        y=new int[n];
//        a=new int [n][n];
//        // Случайным образом выбираются координаты вершин графа
//        for (i=0;i<n;i++)
//        { x[i]=(int)Math.floor(Math.random()*425+25);
//            y[i]=(int)Math.floor(Math.random()*350+100);
//        }
//
//        //Сначала предполагается, что рёбер нет
//        for (i=0;i<n;i++)
//        { for (j=0;j<n;j++)
//        { a[i][j]=10000;}
//        }
//// Случайным образом выбирается: будет ребро между
////парой вершин или нет
//        for(j=0;j<n;j++)
//        { for(kr=0;kr<n;kr++)
//        {if(j!=kr)
//        {lucky=(int)Math.floor(Math.random()*100);
//            if (lucky>50)
//            { a[j][kr]=(int)Math.sqrt(Math.pow(x[j]-x[kr],2)+Math.pow(y[j]-y[kr],2));
//                a[kr][j]=(int)Math.sqrt(Math.pow(x[j]-x[kr],2)+Math.pow(y[j]-y[kr],2));
//            }
//        }
//        }
//        }
//    }
//    //4. Cам алгоритм Дейкстры -Прима
//    public void d_p()
//    { p=new int[n];
//        d=new int[n];
//        v=new int[n];
//        res=new int[n];
//        // Двумерная матрица res-хранит в себе рёбра остова.
//        // Если ребро между вершинами i и j входит в остов,
//        // то в матрице значение ячейки res[i][j]=1, иначе 0.
//        // сначала в остове рёбер нет
//        for (i=0;i<n;i++)
//        {res[i]=-1;}
//
//        for (i=0;i<n;i++)
//        { v[i]=0;// массив с информ. о посещении вершин
//            p[i]=0;// массив c вершинами предками
//            d[i]=0;//массив c вершинами потомками
//        }
//        k=0;// корень дерева вершина с номером 0
//        v[0]=1;
//        for (i=1;i<n;i++)
//        { d[i]=a[0][i];
//            p[i]=0;
//        }
//        for (i=0;i<n-1;i++)
//        { min=10000;
//            for (j=0;j<n;j++)
//            { if (v[j]==0 && d[j]< min)
//            { min=d[j];
//                k=j;
//            }
//            }
//
//            res[p[k]]=k;// между верш к и p[k] есть ребро остова
//            v[k]=1;
//            for (j=0;j<n;j++)
//            { if(v[j]==0 && d[j]>a[k][j])
//            { p[j]=k;
//                d[j]=a[k][j];
//            }
//            }
//        }
//    }
//    // 5. Рисование графа
//    public void paint(Graphics g)
//    { g.setColor(Color.black);
//        g.fillRect(0, 0, getSize().width, getSize().height);//цвет апплета
//        for (i=0;i<n;i++)
//        {  for (j=0;j<n;j++)
//        { g.setColor(Color.yellow);
//            g.drawOval(x[i],y[i],20,20);
//            r=String.valueOf(i);
//            g.drawString(r,x[i]+7,y[i]+15);
//            g.drawOval(x[j],y[j],20,20);
//            r=String.valueOf(j);
//            g.drawString(r,x[j]+7,y[j]+15);
//            if (a[i][j]!=10000&&a[j][i]!=10000)
//            { g.setColor(Color.blue);
//                g.drawLine(x[i]+9,y[i]+12,x[j]+9,y[j]+12);
//                g.setColor(Color.white);
//                g.drawString(""+a[i][j],(x[i]+x[j])/2+3,(y[i]+y[j])/2+3);
//            }
//        }
//        }
//        //Нажата кнопка -построить остов.
//        // Дерево строится по матрице res
//        if (flag1==0)
//        {for(i=0;i<n;i++)
//        {if(res[i]!=-1)
//        {g.setColor(Color.red);
//            g.drawLine(x[i]+9,y[i]+10,x[res[i]]+9,y[res[i]]+10);
//        }
//        }
//        }
//
//    }
//    //6. Обработка событий кнопок
//    public void actionPerformed(ActionEvent e)
//    { if (e.getSource()==buttons[0])
//    { h=tf.getText();
//        n=Integer.parseInt(h);//считывается количество вершин
//        init_matr();
//        flag1=1;
//        repaint();
//    }
//    else
//    { d_p();
//        flag1=0;
//        repaint();
//    }
//    }
//}
