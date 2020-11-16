import javax.swing.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static Hata hata= new Hata();
    private static JFrame frame;
    private static JPanel panel;
    private static JPasswordField sifreField;
    private static JLabel sifreLabel;
    private static JTextField kullaniciText;
    private static JLabel kullaniciLabel;
    private static JButton button;

    public static void main(String[] args) {


        frame = new JFrame("Giriş"); //Kullanıcı Giriş Çerçevesi oluşturuldu

        panel = new JPanel(); //Kullanıcı giriş paneli oluşturuldu
        frame.setSize(300, 160); //Giriş panel boyutları ayarlandı
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.add(panel);
        panel.setLayout(null);
        kullaniciLabel = new JLabel("Kullanıcı adı: "); //Kullanıcı adı yazan kısım oluşturuldu isim atandı
        kullaniciLabel.setBounds(10, 20, 80, 25);
        panel.add(kullaniciLabel);

        kullaniciText = new JTextField(); //Kullanıcı adı girilen yer oluşturuldu
        kullaniciText.setBounds(100, 20, 165, 25);
        panel.add(kullaniciText);

        sifreLabel = new JLabel("Şifre:"); //Kullanıcı şifresi yazan yer oluşturuldu.
        sifreLabel.setBounds(10, 50, 80, 25);
        panel.add(sifreLabel);

        sifreField = new JPasswordField(); //Kullanıcı şifresi girilen yer oluşturuldu
        sifreField.setBounds(100, 50, 165, 25);
        panel.add(sifreField);

        button = new JButton("Giriş"); //Giriş butonu oluşturuldu
        button.setBounds(185, 80, 80, 25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //Butona tıklanıldığında başlatılacak işlemler
            //Kullanıcı adı ve şifresi kontrolü yapılıyor doğruysa diğer 2. panele yönlendirildi
                String kullanici = kullaniciText.getText();
                String sifresi = sifreField.getText();
                if (kullanici.equals("gir") && sifresi.equals(("1234"))) {
                    System.out.println("Giriş başarılı");
                    Liste liste = new Liste();
                    liste.frameAc();
                    frame.setVisible(false);
                }
                else{ //Yanlışsa hata ekranı basıldı.
                    hata.hata();
                }

            }
        });
        panel.add(button);


        frame.setVisible(true);//Çerçeve görüntülendi.
    }

}
