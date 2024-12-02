package lelang.resources.interfaces.users;

import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class HomeScreens extends JFrame {

    public HomeScreens() {
        // Inisiasi JFrame HomeScreens
        setTitle("Aplikasi Lelang");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Register FontAwesome untuk ikon
        IconFontSwing.register(FontAwesome.getIconFont());

        // ================== HEADER CONTAINER ===================
        JPanel headerContainer = new JPanel();
        headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.Y_AXIS));
        headerContainer.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel textHeader = new JLabel("PrimeBid");
        textHeader.setFont(new Font("Funnel Display", Font.BOLD, 25));
        textHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel secondText = new JLabel("Your Online Auction Application");
        secondText.setFont(new Font("Funnel Display", Font.PLAIN, 15));
        secondText.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerContainer.add(textHeader);
        headerContainer.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
        headerContainer.add(secondText);

        // ================== NAVIGATION CONTAINER ===================
        JPanel navContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        // navContainer.setBackground(Color.LIGHT_GRAY);

        Icon homeIcon = IconFontSwing.buildIcon(FontAwesome.HOME, 18);
        JButton home = new JButton("Home", homeIcon);
        home.setBorder(null);
        home.setBorderPainted(false);
        home.setContentAreaFilled(false);

        Icon icon = IconFontSwing.buildIcon(FontAwesome.SHOPPING_BASKET, 18);
        JButton barang = new JButton("Barang Lelang", icon);
        barang.setBorder(null);
        barang.setBorderPainted(false);
        barang.setContentAreaFilled(false);

        Icon icon2 = IconFontSwing.buildIcon(FontAwesome.SHOPPING_CART, 18);
        JButton keranjang = new JButton("Keranjang", icon2);
        keranjang.setBorder(null);
        keranjang.setBorderPainted(false);
        keranjang.setContentAreaFilled(false);

        Icon icon3 = IconFontSwing.buildIcon(FontAwesome.FILE_TEXT_O, 18);
        JButton Penawaran = new JButton("Daftar Tawaran", icon3);
        Penawaran.setBorder(null);
        Penawaran.setBorderPainted(false);
        Penawaran.setContentAreaFilled(false);

        Icon icon4 = IconFontSwing.buildIcon(FontAwesome.USER_CIRCLE_O, 18);
        JButton profile = new JButton("Profile", icon4);
        profile.setBorder(null);
        profile.setBorderPainted(false);
        profile.setContentAreaFilled(false);

        navContainer.add(home);
        navContainer.add(barang);
        navContainer.add(keranjang);
        navContainer.add(Penawaran);
        navContainer.add(profile);

        // ================== BODY CONTAINER ===================
        // JPanel bodyContainer = new JPanel();

        // ================== TAMBAHKAN KE JFrame ===================
        add(headerContainer, BorderLayout.NORTH);
        add(navContainer, BorderLayout.CENTER);
        // add(bodyContainer, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

}
