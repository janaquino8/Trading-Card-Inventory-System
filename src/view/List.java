package src.view;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class List {
    JList<String> list;

    public List(ArrayList<String> incomingList) {
        list = new JList<>(incomingList.toArray(new String[0]));

        list.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        list.setForeground(Color.decode("#1E1F22"));
        list.setBackground(Color.decode("#BCBEC4"));
        list.setEnabled(true);
    }
}
