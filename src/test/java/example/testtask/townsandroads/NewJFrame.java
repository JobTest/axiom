package example.testtask.townsandroads;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Станислав
 */

class  PFields{ //класс, описывающий поле
    int rx,ry; //координаты квадрата
    float znach; //значение
    Color col; //цвет
}

class Fields{ //поля
    static boolean visib=false; //флаг, если true то разрешена отрисовка диаграммы
    static PFields[] a = new PFields[20];
    static PFields[] b = new PFields[20];
    static int count=0,countb=0;
}



class TurnPoint{ //класс, поворачивающий точку относительно центра диаграммы
    int px,py,npx,npy; //старые и новые координаты точки
    float alpha; //угол поворота

    void Point(int px,int py){  //записывает точку
        this.px=px;
        this.py=py;
    }

    void Turn(float alpha){ //поворачивает точку на угол альфа

        npx=(int)((px-200)*Math.cos(Math.toRadians(alpha)))+py;
        npy=(int)(-(px-200)*Math.sin(Math.toRadians(alpha)))+py;

    }

}

class MCanvas extends Canvas { //расширяем класс Canvas

    public MCanvas () {
        super();
        //super.setSize(300, 300);
    }


    @Override
    public void paint(Graphics g) { //Перегружаем метод Paint
        Graphics2D g2 = (Graphics2D) g;  //включаем 2д графику
        g2.setStroke(new BasicStroke(2.0f)); //устанавливаем размер линии
        g.setColor(Color.white);
        g.fillRect(10, 10, 400, 400); //рисуем фон
        if (Fields.visib) { //если разрешена отрисовка диграммы
            int alpha;
            if (Fields.count==0) alpha=360; else
                alpha=360/Fields.count; //считаем угол, который соответствует одному полю
            int a=alpha;
            float sum=0,max=-1;
            for (int i=0;i<Fields.count;i++){ //считаем сумму значений, и находим максимальное из них
                sum+=Fields.a[i]. znach;
                max=Math.max(max, Fields.a[i]. znach);
            }
            TurnPoint p = new TurnPoint();
            int nnpx=0,npx=0,npy=0;
            int nnpy=0,fpx=0,fpy=0;
            for (int i=0;i<Fields.count;i++){ //идем по всем полям
                int px=200+(int)(150*Fields.a[i]. znach/max); //точка нашего отрезка на нулевом градусе
                int py=200;
                nnpx=npx;
                nnpy=npy;
                p.Point(px, py);  //записываем точку
                p.Turn(alpha); //поворачиваем на угол
                npx=p.npx; //сохранем значения
                npy=p.npy;
                if (fpx==0 && fpy==0) { //если это первое поле, то сохраняем координаты
                    fpx=p.npx; fpy=p.npy;
                }
                if (nnpx!=0 && nnpy!=0 && Fields.count>2)  //если поле не первое, и полей больше 2, тогда соединяем их отрезком
                    g.drawLine(nnpx, nnpy, p.npx, p.npy);
                Color c;
                c = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)); //генерируем цвет
                if (Fields.a[i].col==null)  //если рисуем впервые, то сохраняем цвет
                    Fields.a[i].col=c;
                g.setColor(Fields.a[i].col);
                g.drawLine(200, 200, p.npx, p.npy); //рисуем отрезок из центра до точки
                p.Point(px, py-2); //координата квадрата, на 0 градусе
                p.Turn(alpha);  //поворачиваем на угол
                Fields.a[i].rx=p.npx; //сохраняем координаты квадрата
                Fields.a[i].ry=p.npy;
                g.setColor(Color.BLACK);
                p.Point(px, py); //точка на 0 градусе
                p.Turn(alpha); //поворачиваем ее на угол
                if ((alpha>=0 && alpha<90) || (alpha>270 && alpha<=360)) p.npx+=15; //здесь вычисляем координаты надписи
                if (alpha>90 && alpha<270) p.npx-=30;
                if (alpha>0 && alpha<180) p.npy-=15;
                if (alpha>180 && alpha<360) p.npy+=15;

                g.drawString(Integer.toString(Math.round(100*Fields.a[i]. znach/sum))+'%', p.npx, p.npy);//выводим проценты
                alpha+=a; //увеличиваем угол

            }
            if ( Fields.count>2) g.drawLine(fpx, fpy, npx, npy); //если полей больше чем 2, тогда соединяем первое и последнее отрезком

            for (int i=0;i<Fields.count;i++){  //идем по полям и отрисовываем квадраты
                g.setColor(Fields.a[i].col);
                g.fillRect(Fields.a[i].rx,Fields.a[i].ry, 5, 5);
            }

            g.setColor(Color.black); //рисуем квадрат в центре диаграммы
            g.fillRect(200-2,200-2,5,5);
        }

    }

}


public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */

    public NewJFrame() {
        initComponents();
        //this.setSize(400, 300);




        this.buttonGroup1.add(jRadioButton1);
        this.buttonGroup1.add(jRadioButton2);
        this.canvas1=new MCanvas();


        this.canvas1.setBounds(250, 50, 500, 500);
        this.add(this.canvas1);
        //   this.add(this.canvas2);


    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")



    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        canvas1 = new java.awt.Canvas();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        textField1 = new java.awt.TextField();
        jButton3 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Лепестковая процентная диаграмма");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton1.setText("Ввод");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        textField1.setEnabled(false);

        jButton3.setText("Загрузить");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Ручной ввод");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Ввод из файла");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Ручной ввод");

        jLabel2.setText("Файловый ввод");
        jLabel2.setEnabled(false);

        jButton5.setText("Конец ввода");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Построить");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Панель управления");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Панель графики");

        jLabel5.setText("Имя файла");
        jLabel5.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jButton6)
                                                        .addComponent(jRadioButton2)
                                                        .addComponent(jRadioButton1)
                                                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton3)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jButton5)
                                                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jButton1)))
                                                .addContainerGap(483, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel4)
                                                .addGap(165, 165, 165))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton2)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(3, 3, 3)
                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton3)
                                .addGap(42, 42, 42)
                                .addComponent(jButton6)
                                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        Fields.b[Fields.countb]=new PFields(); //создаем новое поле
        Fields.b[Fields.countb].znach=Float.valueOf(jTextField1.getText()); //записываем значение
        jTextField1.setText(""); //очищаем поле ввода

        Fields.countb++; //увеличиваем размер массива
        jTextField1.requestFocus(); //устанавливаем фокус на поле ввода




    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:




    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        try {

            BufferedReader in = new BufferedReader(new FileReader(textField1.getText())); //открываем файл
            if (in.ready()) JOptionPane.showMessageDialog(this,"Файл загружен");
            String str = "";
            while (((str = in.readLine())) != null) //идем по файлу
            {

                Fields.b[Fields.countb]=new PFields(); //записываем каждое значение
                Fields.b[Fields.countb].znach=Float.valueOf(str);
                Fields.countb++;



            }

        } catch (IOException e) {
        }

        textField1.setText(""); //очищаем поле имени файла
    }

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (jRadioButton1.isSelected()){ //если выбран ручной ввод, блокируем все, что связано с файловым вводом, и активируем все, что связано с ручным вводом
            jLabel1.setEnabled(true);
            jTextField1.setEnabled(true);
            jButton1.setEnabled(true);
            jButton5.setEnabled(true);
            jLabel2.setEnabled(false);
            jLabel5.setEnabled(false);
            textField1.setEnabled(false);
            jButton3.setEnabled(false);

        }
    }

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (jRadioButton2.isSelected()){ //тоже самое, только для файлового ввода
            jButton5.setEnabled(false);
            jTextField1.setEnabled(false);
            jLabel1.setEnabled(false);
            jButton1.setEnabled(false);
            textField1.setEnabled(true);
            jLabel2.setEnabled(true);
            jButton3.setEnabled(true);
            jLabel5.setEnabled(true);
        }
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        for (int i=0;i<Fields.countb;i++) //перезаписываем массив
            Fields.a[i]=Fields.b[i];
        Fields.count=Fields.countb;
        for (int i=0;i<Fields.countb;i++) //очищаем массив b
            Fields.b[i]=null;
        Fields.countb=0;
        Fields.visib=true; //разрешаем отрисовку диаграммы
        canvas1.repaint(); //рисуем
    }

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if   (evt.getKeyCode()==10) jButton1.doClick(); //если нажат Enter нажимаем на кнопку ввод
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:
        for (int i=0;i<20;i++) //выделяем память под массив для полей
            Fields.a[i] = new PFields();
    }





    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {



        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see [url]http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html[/url]
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.ButtonGroup buttonGroup1;
    private java.awt.Canvas canvas1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private java.awt.TextField textField1;
    // End of variables declaration
}
