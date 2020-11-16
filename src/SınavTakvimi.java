import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;


public class SınavTakvimi {
    DefaultListModel liste = new DefaultListModel();
    JFrame frame = new JFrame("Takvim");
    Hata hata = new Hata();
    Boolean kontrol1;
    Boolean kontrol2;
    ArrayList<String> tarihler = new ArrayList<>();
    ArrayList<String> saatler = new ArrayList<>();
    private JPanel panel1;
    String tarih;
    String saat;
    private JButton ekleButton;
    private JTextField textField1;
    private JTextField textField2;
    private JList list1;
    private JButton güncelleButton;
    private JButton çıkartButton;
    private JTextField textField3;
    private JButton tarihVeSaatiOtomatikButton;
    private JButton textDosyasınaAktarButton;

    public void frameAc() {
        frame.add(panel1);
        frame.setVisible(true);
        frame.setBounds(0, 0, 400, 400);
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ekle();
            }
        });
        çıkartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cikart();
            }
        });
        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                güncelle();
            }
        });
        tarihVeSaatiOtomatikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otoAta();
            }
        });
        textDosyasınaAktarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAktar();
            }
        });
    }


    public void cikart() {
        int index = list1.getSelectedIndex();
        liste.removeElementAt(index);
    }

    public void ekle() {
        //Tarih saat ve ders ismi kontrol edip ekleme metodu
        tarih = textField2.getText();
        saat = textField3.getText();
        kontrol2 = tarihler.contains(tarih);
        kontrol1 = saatler.contains(saat);
        if (textField1.getText().length() == 0) {
            //Ders ismi boşsa hata verme mesajı
            JOptionPane.showMessageDialog(null, "Dersin ismini giriniz.");
        } else if (textField2.getText().length() == 0 && textField3.getText().length() == 0) {
            //Tarih ve saat boşsa hata verme mesajı
            JOptionPane.showMessageDialog(null, "Tarih ve saat girdisi bulunmuyor.");
        } else if (textField2.getText().length() == 0) {
            //Saat boşsa hata verme mesajı
            JOptionPane.showMessageDialog(null, "Saat girdisi bulunmuyor.");
        } else if (textField3.getText().length() == 0) {
            //Tarih boşsa hata verme mesajı
            JOptionPane.showMessageDialog(null, "Tarih girdisi bulunmuyor.");
        } else if (kontrol1 == true && kontrol2 == true) {
            //O tarih ve saat doluysa hata işlemi basıldı
            JOptionPane.showMessageDialog(null, "Bu saat ve tarihler doludur.");
        } else {
            //Herşey yolundaysa sınav eklendi
            list1.setModel(liste);
            liste.addElement(textField1.getText() + " Dersinin sınav tarihi: " + textField2.getText() + " Saati: " + textField3.getText());
            tarihler.add(tarih);
            saatler.add(saat);
        }
    }

    public void güncelle() {
        try {
            //Seçilen sınavda güncelleme kodu.
            int index = list1.getSelectedIndex();
            liste.setElementAt(textField1.getText() + " Dersinin sınav tarihi: " + textField2.getText() + " Saati: " + textField3.getText(), index);

        } catch (Exception e) {
            //hata çıkarsa hata ekranıyla karşılaşılacak
            hata.hata();
        }
    }

    public void otoAta() {


        //Sadece ismi girilen Dersin saat ve tarihini atayacak

        Random r = new Random();
        int ay = r.nextInt(12);
        //12ye kadar Rastgele değer oluşuruldu
        int gün = r.nextInt(30);
        //30a kadar Rastgele değer oluşuruldu
        String otoTarih = gün + "." + ay + "." + 2020;
        //gün ve ay olarak tarihler eklendi
        int saat = r.nextInt(17);
        String otoSaat = (saat) + "." + 30;
        tarihler.add(otoTarih);
        //tarihler adında arrayliste, tarih eklendi (Kontrol edilmek için)
        System.out.println(textField1 + " Dersinin sınav tarihi: " + otoTarih + " Saati: " + otoSaat);
        saatler.add(otoSaat);
        //Saatler adında arrayliste, saat eklendi (Kontrol edilmek için)
        if (textField2.getText().length() == 0 && textField3.getText().length() == 0) {
            liste.addElement(textField1.getText() + " Dersinin sınav tarihi: " + otoTarih + " Saati: " + otoSaat);
            list1.setModel(liste);
            //Ders otomatik atandı
        } else if (textField1.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Dersin ismi bulunmuyor.");
        } else if (textField2.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Tarih girdisi bulunuyor.");
        } else if (textField3.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Saat girdisi bulunuyor.");
        }

    }

    public void textAktar() {
        int val = list1.getModel().getSize();
        PrintWriter yazdır = null;
        try {
            yazdır = new PrintWriter("SınavListesi.txt");
            yazdır.println();
            for (int i = 0; i < val; i++) {
                yazdır.println(list1.getModel().getElementAt(i));
            }
        } catch (Exception e) {
            System.out.println("Hata: " + e);
        } finally {
            yazdır.close();
        }
    }


}


