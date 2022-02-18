import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImagePicture implements ActionListener {

    static JFrame frame;
    private int EstaMuerto = 0,Hambre = 0, Aburrimiento = 0, Cansado = 0, SpamButton = 0;

    private Icon Feliz_ConEnergia_Alimentado, Feliz_ConEnergia_Hambriento,Triste_ConEnergia_Alimentado,TamagotchiMuerto;
    private Icon Feliz_SinEnergia_Alimentado, Feliz_SinEnergia_Hambriento, Triste_SinEnergia_Alimentado, Triste_SinEnergia_Hambriento;
    private Icon Triste_ConEnergia_Hambriento,TamagotchiComiendo,TamagotchiJugando, TamagotchiDurmiendo;
    private ImageIcon Slime;

    private JLabel label1,label2,label3;
    private JButton Alimentar,Jugar, Dormir;

    private Timer timer,timerlabel2;
    private int countH = 0 , countF = 0,countE = 0, count2 = 0;
    private Color fondo = new Color(253,254,254);
    private GridBagConstraints gbc = new GridBagConstraints();

    public ImagePicture() {

        Feliz_ConEnergia_Alimentado = new ImageIcon("src/Feliz,ConEnergia,Alimentado.gif");
        Feliz_ConEnergia_Hambriento = new ImageIcon("src/Feliz,ConEnergia,Hambriento.gif");

        Triste_ConEnergia_Alimentado = new ImageIcon("src/Triste,ConEnergia,Alimentado.gif");
        Triste_ConEnergia_Hambriento = new ImageIcon("src/Triste,ConEnergia,Hambriento.gif");

        Feliz_SinEnergia_Alimentado = new ImageIcon("src/Feliz,SinEnergia,Alimentado.gif");
        Feliz_SinEnergia_Hambriento = new ImageIcon("src/Feliz,SinEnergia,Hambriento.gif");

        Triste_SinEnergia_Alimentado = new ImageIcon("src/Triste,SinEnergia,Alimentado.gif");
        Triste_SinEnergia_Hambriento = new ImageIcon("src/Triste,SinEnergia,Hambriento.gif");

        TamagotchiMuerto = new ImageIcon("src/SlimeMuerto.png");
        TamagotchiComiendo = new ImageIcon("src/Tazon.gif");
        TamagotchiJugando = new ImageIcon("src/Corazon.gif");
        TamagotchiDurmiendo = new ImageIcon("src/ZZZ.gif");


        Slime = new ImageIcon("src/Slime.png");

        label1 = new JLabel(Feliz_ConEnergia_Alimentado); 
        label1.setLayout(new FlowLayout());
        label1.add(new JLabel("Estoy Feliz"));

        label2 = new JLabel(TamagotchiComiendo);
        label2.setVisible(false);

        label3 = new JLabel(new ImageIcon("src/Vacio.png"));
        label3.setLayout(new FlowLayout());
        label3.setVisible(false);

        Alimentar = new JButton("Darle de Comer");
        Jugar = new JButton("Jugar con el");
        Dormir = new JButton("Dormir");

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Alimentar.setBounds(125,40,85,50);
        Alimentar.addActionListener(this);

        Jugar.setBounds(125,40,85,50);
        Jugar.addActionListener(this);

        Dormir.setBounds(125,40,85,50);
        Dormir.addActionListener(this);

        label3.add(Alimentar);
        label3.add(Jugar);
        label3.add(Dormir);
        label3.setVisible(true);

        frame = new JFrame("Tamagotchi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(new Dimension(800,600));
        frame.setIconImage(Slime.getImage());
        frame.getContentPane().setBackground(fondo);
        frame.add(label3,gbc);
        frame.setLocationRelativeTo(null);
        //frame.add(Alimentar);
        //frame.add(Jugar);
        //frame.add(Dormir);
        frame.add(label1,gbc);
        frame.add(label2);
        frame.setVisible(true);

        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                countH ++;
                countF ++;
                countE ++;
                ChecarEstado();
                
                if (countH > 30 && EstaMuerto == 0 ) {
                    Hambre = 1;
                    ChecarEstado();
                } 
                if(countF > 45 && EstaMuerto == 0) {
                    Aburrimiento = 1;
                    ChecarEstado();
                }
                if(countE > 60 && EstaMuerto == 0) {
                    Cansado = 1;
                    ChecarEstado();
                }
                if (countH >= 60) {
                    label1.setVisible(false);
                    label1 = new JLabel(TamagotchiMuerto);
                    frame.add(label1,gbc);
                    label1.setVisible(true);
                    EstaMuerto = 1;
                    Hambre = 0;
                    Aburrimiento = 0;
                    Cansado = 0;
                    timer.stop();
                }
                if (countF >= 90 && EstaMuerto == 0) {
                    label1.setVisible(false);
                    label1 = new JLabel("        Se Escapo");
                    label1.setFont(new Font("Agency FB",Font.BOLD,120));
                    frame.add(label1,gbc);
                    label1.setVisible(true);
                    EstaMuerto = 1;
                    Hambre = 0;
                    Aburrimiento = 0;
                    Cansado = 0;
                    timer.stop();
                }
                if (countE >= 120) {
                    label1.setVisible(false);
                    label1 = new JLabel(TamagotchiMuerto);
                    frame.add(label1,gbc);
                    label1.setVisible(true);
                    EstaMuerto = 1;
                    Hambre = 0;
                    Aburrimiento = 0;
                    Cansado = 0;
                    timer.stop();
                }
            }
        });

        timer.start();

    timerlabel2 = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            count2++;

            if(count2 == 1){

                timerlabel2.restart();
                timerlabel2.stop();
                label2.setVisible(false);
                count2 = 0;
                SpamButton = 0;

                } else {
                    ChecarEstado();
                }

            }

    });

}
    public void ChecarEstado() {

        if (EstaMuerto == 1) {

            label1.setVisible(false);
            label1 = new JLabel(TamagotchiMuerto);
            frame.add(label1,gbc);
            label1.setVisible(true);
            EstaMuerto = 1;
            Hambre = 0;
            Aburrimiento = 0;

        } else if(Hambre == 0 && Aburrimiento == 0 && Cansado == 0) {

            label1.setVisible(false);
            label1 = new JLabel(Feliz_ConEnergia_Alimentado);
            label1.setLayout(new FlowLayout());
            label1.add(new JLabel("Estoy Feliz"));
            frame.add(label1,gbc);
            this.label1.setVisible(true);

        } else if (Hambre == 1 && Aburrimiento == 0 && Cansado == 0) {

            label1.setVisible(false);
            label1 = new JLabel(Feliz_ConEnergia_Hambriento);
            label1.setLayout(new FlowLayout());
            label1.add(new JLabel("Tengo Hambre"));
            frame.add(label1,gbc);
            this.label1.setVisible(true);

        } else if (Hambre == 0 && Aburrimiento == 1 && Cansado == 0) {

            label1.setVisible(false);
            label1 = new JLabel(Triste_ConEnergia_Alimentado);
            label1.setLayout(new FlowLayout());
            label1.add(new JLabel("Estoy Triste"));
            frame.add(label1,gbc);
            this.label1.setVisible(true);

        } else if (Hambre == 1 && Aburrimiento == 1 && Cansado == 0) {

            label1.setVisible(false);
            label1 = new JLabel(Triste_ConEnergia_Hambriento);
            label1.setLayout(new FlowLayout());
            label1.add(new JLabel("Tengo Hambre Y Estoy Triste"));
            frame.add(label1,gbc);
            this.label1.setVisible(true);

        } else if (Hambre == 0 && Aburrimiento == 0 && Cansado == 1) {

            label1.setVisible(false);
            label1 = new JLabel(Feliz_SinEnergia_Alimentado);
            label1.setLayout(new FlowLayout());
            label1.add(new JLabel("Estoy Cansado"));
            frame.add(label1,gbc);
            this.label1.setVisible(true);

        } else if (Hambre == 1 && Aburrimiento == 0 && Cansado == 1) {

            label1.setVisible(false);
            label1 = new JLabel(Feliz_SinEnergia_Hambriento);
            label1.setLayout(new FlowLayout());
            label1.add(new JLabel("Tengo Hambre Y Estoy Cansado"));
            frame.add(label1,gbc);
            this.label1.setVisible(true);

        } else if (Hambre == 0 && Aburrimiento == 1 && Cansado == 1) {

            label1.setVisible(false);
            label1 = new JLabel(Triste_SinEnergia_Alimentado);
            label1.setLayout(new FlowLayout());
            label1.add(new JLabel("Estoy Triste Y Estoy Cansado"));
            frame.add(label1,gbc);
            this.label1.setVisible(true);

        } else if (Hambre == 1 && Aburrimiento == 1 && Cansado == 1) {

            label1.setVisible(false);
            label1 = new JLabel(Triste_SinEnergia_Hambriento);
            label1.setLayout(new FlowLayout());
            label1.add(new JLabel("Tengo Hambre Y Estoy Triste Y Estoy Cansado"));
            frame.add(label1,gbc);
            this.label1.setVisible(true);

        }
    }
    
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == Alimentar && EstaMuerto == 0 && SpamButton == 0) {

            label2 = new JLabel(TamagotchiComiendo);
            frame.add(label2);
            label2.setLayout(new FlowLayout());
            label2.add(new JLabel("Comiendo"));
            SpamButton = 1;
            timerlabel2.start();

            label1.setVisible(false);
            this.label1.setVisible(true);

            timer.restart();

            countH = 0;
            Hambre = 0;

        } else if (e.getSource() == Jugar && EstaMuerto == 0 && SpamButton == 0) {

                label2 = new JLabel(TamagotchiJugando);
                frame.add(label2);
                label2.setLayout(new FlowLayout());
                label2.add(new JLabel("Jugando"));
                label2.setVisible(true);
                SpamButton = 1;
                timerlabel2.start();

                this.label1.setVisible(false);
                this.label1.setVisible(true);

                timer.restart();

                Aburrimiento = 0;
                countF = 0;

        } else if (e.getSource() == Dormir && EstaMuerto == 0 && SpamButton == 0) {

                label2 = new JLabel(TamagotchiDurmiendo);
                frame.add(label2);
                label2.setLayout(new FlowLayout());
                label2.add(new JLabel("Durmiendo"));
                label2.setVisible(true);
                SpamButton = 1;
                timerlabel2.start();

                this.label1.setVisible(false);
                this.label1.setVisible(true);

                timer.restart();

                Cansado = 0;
                countE = 0;
                
        }
    }
}