package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;

public class LayerFrame extends JInternalFrame {
	private final Map<String, ImageIcon> imageMap;
	
	public LayerFrame() {
		setResizable(true);
		
        String[] nameList = new String[5];
        for (int i = 0; i < nameList.length; i++) {
        	nameList[i] = "Layer " + (i + 1);
        }
        imageMap = createImageMap(nameList);
        JList list = new JList(nameList);
        list.setCellRenderer(new MarioListRenderer());
        list.setSelectedIndex(0);

        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(200, 200));

        add(scroll);
        pack();
        setVisible(true);
    }

    public class MarioListRenderer extends DefaultListCellRenderer {

        //Font font = new Font("helvitica", Font.BOLD, 24);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            label.setIcon(imageMap.get((String) value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            //label.setFont(font);
            return label;
        }
    }

    private Map<String, ImageIcon> createImageMap(String[] list) {
        Map<String, ImageIcon> map = new HashMap<>();
        try {
        	for(int i = 1; i <= 5; i++) {
        		map.put("Layer " + i, new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }
}