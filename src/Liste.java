import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Liste extends Abstract {
    JFrame frame = new JFrame("Takvim");
    private JPanel panel1;
    DefaultListModel liste = new DefaultListModel();
    private JButton sınavEkleButton;
    private JList list1;
    private JButton sınavlarıGörüntüleButton;
    SınavTakvimi takvim = new SınavTakvimi(); //Takvim Listesi Oluşturuldu

    public void frameAc() { //Panelde çalıştırılan işlemler metodu
        frame.add(panel1);
        frame.setVisible(true);
        frame.setBounds(0, 0, 400, 400);
        sınavEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takvim.frameAc();
             //   frame.setVisible(false);
            }
        });

        //Sınavları görüntüleme butonu oluşturuldu
        sınavlarıGörüntüleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                degerleriCek();
            }
        });


    }
    public void degerleriCek(){

        //Metin belgesinden sınavlar, tarih ve saatleri çekme metodu.

        BufferedReader br= null;
        try{
            br = new BufferedReader(new FileReader("SınavListesi.txt"));

            //metin belgesi javaya aktarıldı

            for(int i = 0; i <15;i++){
              String ss= br.readLine();
              //Metin belgesindeki, her satır başı metin belgesinden listeye eklendi
                liste.addElement(ss);
            }
            list1.setModel(liste);
        }catch (Exception e){
            System.out.println("" + e);
            //Sorun çıkmaması için try catch yazıldı
        }finally {
            try {
                br.close();
            }
            catch (Exception e)  {
                //Sorun çıkmaması için try catch yazıldı
                System.out.println(""+e);
            }
        }


    }
}