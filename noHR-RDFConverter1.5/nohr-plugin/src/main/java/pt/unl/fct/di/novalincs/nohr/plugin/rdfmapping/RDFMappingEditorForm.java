package pt.unl.fct.di.novalincs.nohr.plugin.rdfmapping;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import pt.unl.fct.di.novalincs.nohr.model.RDFMapping;
import pt.unl.fct.di.novalincs.nohr.model.vocabulary.Vocabulary;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

//TODO finish this
public class RDFMappingEditorForm extends JPanel {

    private final DefaultTableModel predicatesModel;
    private final Vocabulary vocabulary;
    private File rdfFile;
    private JLabel lbl_Intro_label;
    private JLabel lbl_RDF_label;
    private JTextField rdfFileTextField;
    private JButton btnOpenFile;
    private JLabel lbl_predicateColumns;
    private JButton btnAddPredicate;
    private SortedMap<String, Set<String>> entries;
    private SortedMap<String, Set<String>> tableValues;


    public RDFMappingEditorForm(Vocabulary vocabulary) {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxHeight = (int) screensize.getHeight();
        int maxWidth = (int) screensize.getWidth();
        int height = 500;
        int width = 690;
        setPreferredSize(new Dimension(width, height));
        setLocation(new Point(((maxWidth - width) / 3), (maxHeight - height) / 3));
        setAutoscrolls(true);
        this.vocabulary = vocabulary;

        predicatesModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        predicatesModel.addColumn("Namespace");
        predicatesModel.addColumn("Localname");

        load();
    }

    private void load() {
        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.25, 0.0, 0.0, 0.0,};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
                0.0};
        setLayout(gridBagLayout);

        lbl_Intro_label = new JLabel("Define the RDF Mapping: ");
        lbl_Intro_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lbl_Intro_label.setToolTipText("Define the mapping for the RDF file");
        lbl_Intro_label.setBackground(Color.WHITE);
        GridBagConstraints gbc_lbl_Intro_label = new GridBagConstraints();
        gbc_lbl_Intro_label.anchor = GridBagConstraints.WEST;
        gbc_lbl_Intro_label.gridwidth = 3;
        gbc_lbl_Intro_label.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_Intro_label.gridx = 1;
        gbc_lbl_Intro_label.gridy = 1;
        add(lbl_Intro_label, gbc_lbl_Intro_label);

        lbl_RDF_label = new JLabel("1 - RDF File");
        lbl_RDF_label.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lbl_RDF_label = new GridBagConstraints();
        gbc_lbl_RDF_label.anchor = GridBagConstraints.EAST;
        gbc_lbl_RDF_label.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_RDF_label.gridx = 1;
        gbc_lbl_RDF_label.gridy = 3;
        add(lbl_RDF_label, gbc_lbl_RDF_label);

        rdfFileTextField = new JTextField();
        GridBagConstraints gbc_rdfFileTextField = new GridBagConstraints();
        gbc_rdfFileTextField.gridwidth = 2;
        gbc_rdfFileTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_rdfFileTextField.insets = new Insets(0, 0, 5, 5);
        gbc_rdfFileTextField.gridx = 2;
        gbc_rdfFileTextField.gridy = 3;
        add(rdfFileTextField, gbc_rdfFileTextField);
        rdfFileTextField.setColumns(20);
        rdfFileTextField.setEditable(false);

        btnOpenFile = new JButton("Open");
        btnOpenFile.setPreferredSize(new Dimension(35, 23));
        btnOpenFile.setToolTipText("Add rdf file ");
        btnOpenFile.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();

                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("RDF files", "rdf", "ttl", "nt", "n3", "nq");

                fc.setFileFilter(filter);

                final int returnVal = fc.showOpenDialog(btnOpenFile);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    rdfFile = fc.getSelectedFile();
                    rdfFileTextField.setText(rdfFile.getPath());
                    setRDFModel();
                }
            }
        });

        GridBagConstraints gbc_btnOpenFile = new GridBagConstraints();
        gbc_btnOpenFile.anchor = GridBagConstraints.NORTH;
        gbc_btnOpenFile.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnOpenFile.insets = new Insets(0, 0, 5, 5);
        gbc_btnOpenFile.gridx = 4;
        gbc_btnOpenFile.gridy = 3;
        add(btnOpenFile, gbc_btnOpenFile);

        lbl_predicateColumns = new JLabel("2 - SELECT Predicates: ");
        lbl_predicateColumns.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_predicateColumns.setToolTipText("Every predicate is made of two parts, the namespace prefix and the specific localname.");
        GridBagConstraints gbc_lbl_predicateColumns = new GridBagConstraints();
        gbc_lbl_predicateColumns.anchor = GridBagConstraints.NORTHEAST;
        gbc_lbl_predicateColumns.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_predicateColumns.gridx = 1;
        gbc_lbl_predicateColumns.gridy = 4;
        add(lbl_predicateColumns, gbc_lbl_predicateColumns);

        final JTable tbpredicateColumns = new JTable(predicatesModel);
        tbpredicateColumns.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JScrollPane scrollPaneCol = new JScrollPane(tbpredicateColumns);
        scrollPaneCol.setPreferredSize(new Dimension(380, 83));
        scrollPaneCol.setMinimumSize(new Dimension(23, 83));
        scrollPaneCol.setMaximumSize(new Dimension(32767, 83));
        GridBagConstraints gbc_scrollPaneCol = new GridBagConstraints();
        gbc_scrollPaneCol.gridheight = 2;
        gbc_scrollPaneCol.gridwidth = 3;
        gbc_scrollPaneCol.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPaneCol.fill = GridBagConstraints.BOTH;
        gbc_scrollPaneCol.gridx = 2;
        gbc_scrollPaneCol.gridy = 4;
        add(scrollPaneCol, gbc_scrollPaneCol);

        btnAddPredicate = new JButton("+");
        btnAddPredicate.setPreferredSize(new Dimension(35, 23));
        btnAddPredicate.setToolTipText("Add predicate");
        btnAddPredicate.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAddPredicate.addActionListener(e -> {
            Object[] objects = entries.keySet().toArray();
            if (objects.length == 0)
                JOptionPane.showMessageDialog(btnAddPredicate, "All predicates chosen");
            else {
                String result = (String) JOptionPane.showInputDialog(btnAddPredicate, "Choose a predicate", "Test", JOptionPane.QUESTION_MESSAGE, null, objects, objects[0]);

                System.out.println("The result " + result);

                if (result != null) {
                    Object[] options = entries.get(result).toArray();

                    String result_2 = (String) JOptionPane.showInputDialog(btnAddPredicate, "Choose the localname of the predicate", "Test2", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                    Object[] finalResult = {result, result_2};
                    entries.get(result).remove(result_2);
                    System.out.println(entries.get(result));
                    if (entries.get(result).isEmpty())
                        entries.remove(result);
                    if (result_2 != null) {
                        predicatesModel.addRow(finalResult);
                        System.out.println("This is the final result to add" + finalResult);
                        tableValues.putIfAbsent(result, new HashSet<>());
                        tableValues.get(result).add(result_2);
                    }
                    System.out.println(result_2);
                }
            }
        });
        GridBagConstraints gbc_btnAddCol = new GridBagConstraints();
        gbc_btnAddCol.anchor = GridBagConstraints.NORTH;
        gbc_btnAddCol.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnAddCol.insets = new Insets(0, 0, 5, 5);
        gbc_btnAddCol.gridx = 4;
        gbc_btnAddCol.gridy = 4;
        add(btnAddPredicate, gbc_btnAddCol);
    }

    private void setRDFModel() {
        Model model = ModelFactory.createDefaultModel();

        model.read(rdfFile.getPath());
        StmtIterator it = model.listStatements();
        entries = new TreeMap<>();
        tableValues = new TreeMap<>();
        while (it.hasNext()) {
            Statement s = it.nextStatement();

            String namespace = s.getModel().getNsURIPrefix(s.getPredicate().getNameSpace());
            String localName = s.getPredicate().getLocalName();

            entries.putIfAbsent(namespace, new HashSet<>());
            entries.get(namespace).add(localName);
        }
    }

    public void setMapping(RDFMapping rdfMapping) {
    }

    public RDFMapping getRDFMapping() {

        return null;
    }
}
