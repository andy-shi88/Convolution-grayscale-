package convolution;

import java.io.IOException;
import javax.swing.JFrame;
import views.main_view;

public class Convolution {

    public static void main(String[] args) throws IOException
    {
        JFrame jf = new JFrame();
        main_view mv = new main_view();
       jf.add(mv);
        jf.setBounds(0, 0, 1200, 400);
        jf.setVisible(true);
    }
}
