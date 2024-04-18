
import javax.swing.*;
        import javax.swing.text.*;
        import java.awt.*;
        import java.awt.event.*;

public class EDITOR_TEXTO_FLC extends JFrame {
    private JTextPane panelTexto;
    private JButton botonNegrita;
    private JButton botonCursiva;
    private JButton botonSubrayado;
    private JButton botonColor;
    private JButton botonTamano;
    private JComboBox<String> comboBoxTipos; // Nuevo ComboBox para cambiar el tipo de letra

    public EDITOR_TEXTO_FLC() {
        setTitle("Editor de Texto");
        setSize(700, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelTexto = new JTextPane();
        botonNegrita = new JButton("Negrita");
        botonNegrita.addActionListener(new BoldButtonListener());

        botonCursiva = new JButton("Cursiva");
        botonCursiva.addActionListener(new ItalicButtonListener());

        botonSubrayado = new JButton("Subrayado");
        botonSubrayado.addActionListener(new UnderlineButtonListener());

        botonColor = new JButton("Color");
        botonColor.addActionListener(new ColorButtonListener());

        botonTamano = new JButton("Tamaño");
        botonTamano.addActionListener(new SizeButtonListener());

        String[] tiposLetras = {"Arial", "Times New Roman", "Verdana", "Tahoma", "Courier New"};
        comboBoxTipos = new JComboBox<>(tiposLetras);
        comboBoxTipos.addActionListener(new FontTypeListener()); // Agregar el listener para cambiar el tipo de letra

        JScrollPane scrolTexto = new JScrollPane(panelTexto);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Panel para los botones en la parte superior

        panelBotones.add(botonNegrita);
        panelBotones.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio rígido entre los botones
        panelBotones.add(botonCursiva);
        panelBotones.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio rígido entre los botones
        panelBotones.add(botonSubrayado);
        panelBotones.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio rígido entre los botones
        panelBotones.add(botonColor);
        panelBotones.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio rígido entre los botones
        panelBotones.add(botonTamano);
        panelBotones.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio rígido entre los botones
        panelBotones.add(comboBoxTipos);// Agregar el ComboBox al panel

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelBotones, BorderLayout.NORTH); // Agregar el panel de botones en la parte superior
        getContentPane().add(scrolTexto, BorderLayout.CENTER);
    }

    private class BoldButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int start = panelTexto.getSelectionStart();
            int end = panelTexto.getSelectionEnd();

            StyledDocument doc = panelTexto.getStyledDocument();
            Style style = doc.addStyle("Negrita", null);

            AttributeSet attributes = doc.getCharacterElement(start).getAttributes();
            boolean esNegrita = StyleConstants.isBold(attributes);

            if (esNegrita) {
                StyleConstants.setBold(style, false);
            } else {
                StyleConstants.setBold(style, true);
            }

            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class ItalicButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int start = panelTexto.getSelectionStart();
            int end = panelTexto.getSelectionEnd();

            StyledDocument doc = panelTexto.getStyledDocument();
            Style style = doc.addStyle("Cursiva", null);

            AttributeSet attributes = doc.getCharacterElement(start).getAttributes();
            boolean esCursiva = StyleConstants.isItalic(attributes);

            if (esCursiva) {
                StyleConstants.setItalic(style, false);
            } else {
                StyleConstants.setItalic(style, true);
            }

            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class UnderlineButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int start = panelTexto.getSelectionStart();
            int end = panelTexto.getSelectionEnd();

            StyledDocument doc = panelTexto.getStyledDocument();
            Style style = doc.addStyle("Subrayado", null);

            AttributeSet attributes = doc.getCharacterElement(start).getAttributes();
            boolean esSubrayado = StyleConstants.isUnderline(attributes);

            if (esSubrayado) {
                StyleConstants.setUnderline(style, false);
            } else {
                StyleConstants.setUnderline(style, true);
            }

            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class ColorButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] colores = {"Negro", "Rojo", "Azul", "Verde"};
            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un color:", "Color", JOptionPane.PLAIN_MESSAGE, null, colores, colores[0]);

            if (seleccion != null) {
                Color color;
                switch (seleccion) {
                    case "Negro":
                        color = Color.BLACK;
                        break;
                    case "Rojo":
                        color = Color.RED;
                        break;
                    case "Azul":
                        color = Color.BLUE;
                        break;
                    case "Verde":
                        color = Color.GREEN;
                        break;
                    default:
                        color = Color.BLACK;
                }

                int start = panelTexto.getSelectionStart();
                int end = panelTexto.getSelectionEnd();

                StyledDocument doc = panelTexto.getStyledDocument();
                Style style = doc.addStyle("Color", null);

                StyleConstants.setForeground(style, color);

                doc.setCharacterAttributes(start, end - start, style, false);
            }
        }
    }

    private class SizeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] tamanos = {"10", "12", "14", "16"};
            String input = (String) JOptionPane.showInputDialog(null, "Seleccione el tamaño de la letra:", "Tamaño de Letra", JOptionPane.PLAIN_MESSAGE, null, tamanos, tamanos[0]);

            if (input != null) {
                try {
                    int size = Integer.parseInt(input);
                    if (size > 0) {
                        int start = panelTexto.getSelectionStart();
                        int end = panelTexto.getSelectionEnd();

                        StyledDocument doc = panelTexto.getStyledDocument();
                        Style style = doc.addStyle("Tamaño", null);

                        StyleConstants.setFontSize(style, size);

                        doc.setCharacterAttributes(start, end - start, style, false);
                    } else {
                        JOptionPane.showMessageDialog(null, "El tamaño de la letra debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class FontTypeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String selectedFont = (String) comboBoxTipos.getSelectedItem();
            int start = panelTexto.getSelectionStart();
            int end = panelTexto.getSelectionEnd();

            StyledDocument doc = panelTexto.getStyledDocument();
            Style style = doc.addStyle("Tipo de Letra", null);

            StyleConstants.setFontFamily(style, selectedFont);

            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    public static void main(String[] args) {
        EDITOR_TEXTO_FLC editor = new EDITOR_TEXTO_FLC();
        editor.setVisible(true);
    }
}

