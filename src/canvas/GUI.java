package canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private Header header,header2;
    private JTextField texto;
    private JTextArea areaTexto;
    private FileManager fileManager;
    private Escucha escucha;
    private int palabrasaMandar;
    private Canvas canvas;
    private Timer timer, iniciar,selecionarConteo;
    public static String nombreUsario;
    public static int nivel;
    private Font font;
    private JPanel palabras,botones;
    private Diccionario palabra;
    private JLabel fondo;
    private ImageIcon fondoimagen;
    private ModelWord niveles;
    private JButton jugar,ayuda,salir,si,no;
    private  int seleccionar=0;
    private int counter3;
    /**
     * Constructor of GUI class
     */
    public GUI() throws IOException {
        initGUI();

        //Default JFrame configuration
        this.setTitle("I Know That Word");
        this.setSize(500,400);
       /* fondoimagen=new ImageIcon(getClass().getResource("/resources/fondo1.jpg"));
        fondo=new JLabel(fondoimagen);
        *///this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
      /*  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );*/
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() throws IOException {


        fileManager =new FileManager();
        niveles=new ModelWord();
        nombreUsario=JOptionPane.showInputDialog("Ingrese su usuario");
        setNombreUsuario(nombreUsario);
        //  fileManager.subirNivel();
        fileManager.escribirUsuario(nombreUsario);
        nivel=fileManager.buscarNivelUsuario(nombreUsario);
        niveles.setNivelActual(nivel);

        header= new Header("¿ Que tan buena es tu memoria ?",Color.black);
        this.add(header,BorderLayout.NORTH);

        header2 = new Header("Recuerda las siguientes " + ModelWord.palabrasPorNivel / 2 + " Palabras", Color.DARK_GRAY);
        header2.setEnabled(false);
        header2.setVisible(false);



        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        escucha = new Escucha();
        fileManager = new FileManager();
        palabra= new Diccionario();
        //Set up JComponents


        palabras = new JPanel();

        canvas = new Canvas();

        canvas.setFocusable(true);
        add(canvas,BorderLayout.CENTER);

        botones=new JPanel();
        botones.setBackground(Color.BLUE);
        jugar=new JButton("JUGAR");
        si=new JButton("Si");
        no=new JButton("No");
        ayuda=new JButton("AYUDA");

        botones.add(si);
        botones.add(jugar);
        botones.add(ayuda);
        botones.add(no);

        this.si.setVisible(false);
        this.no.setVisible(false);
        this.add(botones, BorderLayout.SOUTH);

        jugar.addActionListener(escucha);
        si.addActionListener(escucha);
        no.addActionListener(escucha);
        //initTimer.setVisible(false);

        iniciar = new Timer(5000,escucha);
        timer = new Timer(5000,escucha);
        selecionarConteo=new Timer(2000,escucha);
        //timer.start();



    }



    private String setNombreUsuario(String nombreUsario1){
        nombreUsario=nombreUsario1;
        return  nombreUsario;
    }
    public static String getNombreUsario(){
        return nombreUsario;
    }
    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            try {
                GUI miProjectGUI = new GUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        private Random random;
        private int counter,counter2;
        public Escucha(){
            random = new Random();
            counter=0;

            counter2=0;
            counter3=0;
        }


        @Override
        public void actionPerformed(ActionEvent e) {

            // areaTexto.setText(palabra.getFrase());

            if(e.getSource()==jugar){

                //iniciar.start();

                JOptionPane.showMessageDialog(null,"El juego iniciará");
                //header.setVisible(false);
                header.setText("Recuerda las SIguientes " +ModelWord.palabrasPorNivel/2 + "Palabras");

                timer.start();


            }else if(e.getSource()==si){
                ModelWord.setUsuarioCorreto(true);

                ModelWord.getAciertos();
                System.out.println(ModelWord.getAciertos());
                timer.restart();


            }else if(e.getSource()==no){
                ModelWord.setUsuarioCorreto(false);
                ModelWord.getAciertos();
                System.out.println(ModelWord.getAciertos());
                timer.restart();

            }

/*
            if(e.getSource()==iniciar){
                counter2++;
                canvas.conteo(getGraphics(),counter2);
                if(counter2<=4){

                }else{
                    timer.start();
                    iniciar.stop();

                }
            }else{

            }
*/
            switch (nivel)
            {
                case 1:
                    palabrasaMandar=0;
                    if(e.getSource()==timer){

                        counter++;
                        canvas.setStep(1);
                        canvas.paintComponent(getGraphics());

                        if(counter<=9){

                        }else{
                            timer.stop();
                            JOptionPane.showMessageDialog(null,"AHORA VAMOS A COMPROBAR LAS PALABRAS");
                            selecionarConteo.start();
                            seleccionar=1;
                            si.setVisible(true);
                            no.setVisible(true);
                            jugar.setVisible(false);
                            ayuda.setVisible(false);
                        }
                    }else{
                        counter=0;

                    }
                    if(e.getSource()==selecionarConteo){
                        canvas.setI(counter3);
                        counter3++;
                        canvas.setStep(4);

                        canvas.paintComponent(getGraphics());

                        if(counter3<=19){

                        }else{
                            selecionarConteo.stop();
                        }
                    }else{
                        counter3=0;
                    }

                    break;
                case 2:
                    if(e.getSource()==timer){

                        counter++;
                        canvas.setStep(1);
                        canvas.paintComponent(getGraphics());
                        if(counter<=19){

                        }else{
                            timer.stop();
                            JOptionPane.showMessageDialog(null,"AHORA VAMOS A COMPROBAR LAS PALABRAS");
                            selecionarConteo.start();

                            si.setVisible(true);
                            no.setVisible(true);
                            jugar.setVisible(false);
                            ayuda.setVisible(false);
                        }
                    }else{

                        counter=0;
                    }
                    if(e.getSource()==selecionarConteo){
                        canvas.setI(counter3);
                        counter3++;
                        canvas.setStep(4);

                        canvas.paintComponent(getGraphics());

                        if(counter3<=39){

                        }else{
                            selecionarConteo.stop();
                        }
                    }else{
                        counter3=0;
                    }

                    break;
                case 3:
                    if(e.getSource()==timer){

                        counter++;
                        canvas.setStep(1);
                        canvas.paintComponent(getGraphics());
                        if(counter<=24){

                        }else{
                            timer.stop();
                            si.setVisible(true);
                            no.setVisible(true);
                            jugar.setVisible(false);
                            ayuda.setVisible(false);
                        }
                    }else{
                        counter=0;

                    }
                    if(e.getSource()==selecionarConteo){
                        canvas.setI(counter3);
                        counter3++;
                        canvas.setStep(4);

                        canvas.paintComponent(getGraphics());

                        if(counter3<=49){

                        }else{
                            selecionarConteo.stop();
                        }
                    }else{
                        counter3=0;
                    }

                    break;
                case 4:
                    if(e.getSource()==timer){

                        counter++;
                        canvas.setStep(1);
                        canvas.paintComponent(getGraphics());
                        if(counter<=29){

                        }else{
                            timer.stop();
                            si.setVisible(true);
                            no.setVisible(true);
                            jugar.setVisible(false);
                            ayuda.setVisible(false);
                        }
                    }else{

                        counter=0;

                    }if(e.getSource()==selecionarConteo){
                    canvas.setI(counter3);
                    counter3++;
                    canvas.setStep(4);

                    canvas.paintComponent(getGraphics());

                    if(counter3<=59){

                    }else{
                        selecionarConteo.stop();
                    }
                }else{
                    counter3=0;
                }

                    break;
                case 5:
                    if(e.getSource()==timer){

                        counter++;
                        canvas.setStep(1);
                        canvas.paintComponent(getGraphics());
                        if(counter<=34){

                        }else{
                            timer.stop();
                            si.setVisible(true);
                            no.setVisible(true);
                            jugar.setVisible(false);
                            ayuda.setVisible(false);
                        }
                    }else{

                        counter=0;

                    }
                    if(e.getSource()==selecionarConteo){
                        canvas.setI(counter3);
                        counter3++;
                        canvas.setStep(4);

                        canvas.paintComponent(getGraphics());

                        if(counter3<=69){

                        }else{
                            selecionarConteo.stop();
                        }
                    }else{
                        counter3=0;
                    }

                    break;
                case 6:
                    if(e.getSource()==timer){

                        counter++;
                        canvas.setStep(1);
                        canvas.paintComponent(getGraphics());
                        if(counter<=39){

                        }else{
                            timer.stop();
                            si.setVisible(true);
                            no.setVisible(true);
                            jugar.setVisible(false);
                            ayuda.setVisible(false);
                        }
                    }else{
                        counter=0;

                    }
                    if(e.getSource()==selecionarConteo){
                        canvas.setI(counter3);
                        counter3++;
                        canvas.setStep(4);

                        canvas.paintComponent(getGraphics());

                        if(counter3<=79){

                        }else{
                            selecionarConteo.stop();
                        }
                    }else{
                        counter3=0;
                    }
                    break;
                case 7:
                    if(e.getSource()==timer){

                        counter++;
                        canvas.setStep(1);
                        canvas.paintComponent(getGraphics());
                        if(counter<=49){

                        }else{
                            timer.stop();
                            si.setVisible(true);
                            no.setVisible(true);
                            jugar.setVisible(false);
                            ayuda.setVisible(false);
                        }
                    }else{

                        counter=0;

                    } if(e.getSource()==selecionarConteo){
                    canvas.setI(counter3);
                    counter3++;
                    canvas.setStep(4);

                    canvas.paintComponent(getGraphics());

                    if(counter3<=99){

                    }else{
                        selecionarConteo.stop();
                    }
                }else{
                    counter3=0;
                }

                    break;
                case 8:
                    if(e.getSource()==timer){

                        counter++;
                        canvas.setStep(1);
                        canvas.paintComponent(getGraphics());
                        if(counter<=59){

                        }else{
                            timer.stop();
                            si.setVisible(true);
                            no.setVisible(true);
                            jugar.setVisible(false);
                            ayuda.setVisible(false);
                        }
                    }else{

                        counter=0;

                    }
                    if(e.getSource()==selecionarConteo){
                        canvas.setI(counter3);
                        counter3++;
                        canvas.setStep(4);

                        canvas.paintComponent(getGraphics());

                        if(counter3<=119){

                        }else{
                            selecionarConteo.stop();
                        }
                    }else{
                        counter3=0;
                    }
                    break;
                case 9:
                    if(e.getSource()==timer){

                        counter++;
                        canvas.setStep(1);
                        canvas.paintComponent(getGraphics());
                        if(counter<=69){

                        }else{
                            timer.stop();
                            si.setVisible(true);
                            no.setVisible(true);
                            jugar.setVisible(false);
                            ayuda.setVisible(false);
                        }
                    }else{

                        counter=0;

                    }
                    if(e.getSource()==selecionarConteo){
                        canvas.setI(counter3);
                        counter3++;
                        canvas.setStep(4);

                        canvas.paintComponent(getGraphics());

                        if(counter3<=139){

                        }else{
                            selecionarConteo.stop();
                        }
                    }else{
                        counter3=0;
                    }break;
                case 10:
                    if(e.getSource()==timer){

                        counter++;
                        canvas.setStep(1);
                        canvas.paintComponent(getGraphics());
                        if(counter<=99){

                        }else{
                            timer.stop();
                            si.setVisible(true);
                            no.setVisible(true);
                            jugar.setVisible(false);
                            ayuda.setVisible(false);
                        }
                    }else{

                        counter=0;

                    } if(e.getSource()==selecionarConteo){
                    canvas.setI(counter3);
                    counter3++;
                    canvas.setStep(4);

                    canvas.paintComponent(getGraphics());

                    if(counter3<=199){

                    }else{
                        selecionarConteo.stop();
                    }
                }else{
                    counter3=0;
                }
                    break;

            }

        }
    }
}


