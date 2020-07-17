package pt.unl.fct.di.novalincs.nohr.plugin;

import org.protege.editor.core.ui.util.UIUtil;
import pt.unl.fct.di.novalincs.nohr.plugin.dbmapping.DBMappingEditor;
import pt.unl.fct.di.novalincs.nohr.plugin.dbmapping.DBMappingList;
import pt.unl.fct.di.novalincs.nohr.plugin.dbmapping.DBMappingListModel;
import pt.unl.fct.di.novalincs.nohr.plugin.rdfmapping.RDFMappingEditor;
import pt.unl.fct.di.novalincs.nohr.plugin.rdfmapping.RDFMappingListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class RDFMappingViewComponent extends AbstractNoHRViewComponent {

    private static final long serialVersionUID = 1L;

    private DBMappingList dbMappingList;


    private RDFMappingEditor rdfMappingEditor;

    @Override
    protected void initialiseOWLView() throws Exception {
        setLayout(new BorderLayout(10, 10));


        rdfMappingEditor = new RDFMappingEditor( getVocabulary(),this);

        final RDFMappingListModel rdfMappingListModel = getRDFMappingListModel();

        /*
//        define the object that represents the tab
        final DBMappingListModel dbMappingListModel = getDBMappingListModel();
//        reset();
        dbMappingList = new DBMappingList(dbMappingEditor, dbMappingListModel);
        dbMappingList.setFont(new Font(this.getFont().getFontName(), Font.BOLD, 14));
        final JScrollPane jScrollPane = new JScrollPane(dbMappingList);
        add(jScrollPane, BorderLayout.CENTER);
        //final JPanel buttonHolder = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final Box buttonHolder = new Box(BoxLayout.X_AXIS);

        final JButton openButton = new JButton(new AbstractAction("Open") {

            private static final long serialVersionUID = -2176187025244957420L;

            @Override
            public void actionPerformed(ActionEvent arg0) {
                final JFileChooser fc = new JFileChooser(UIUtil.getCurrentFileDirectory());
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                final int returnVal = fc.showOpenDialog(RDFMappingViewComponent.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        final File file = fc.getSelectedFile();
                        RDFMappingViewComponent.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        dbMappingListModel.load(file);
                    } catch (final Exception e) {
                        Messages.invalidmappingFile(RDFMappingViewComponent.this, e);
                    } finally {
                        RDFMappingViewComponent.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                }
            }
        });

        final JButton saveButton = new JButton(new AbstractAction("Save") {

            private static final long serialVersionUID = -2176187025244957420L;

            @Override
            public void actionPerformed(ActionEvent arg0) {
                final JFileChooser fc = new JFileChooser(UIUtil.getCurrentFileDirectory());
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                final int returnVal = fc.showSaveDialog(RDFMappingViewComponent.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        final File file = fc.getSelectedFile();
                        RDFMappingViewComponent.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        dbMappingListModel.save(file);
                    } catch (final IOException e) {
                        Messages.unsucceccfulSave(RDFMappingViewComponent.this, e);
                    } finally {
                        RDFMappingViewComponent.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                }

            }
        });

        final JButton clearButton = new JButton(new AbstractAction("Clear") {

            private static final long serialVersionUID = -2176187025244957420L;

            @Override
            public void actionPerformed(ActionEvent arg0) {
                dbMappingListModel.clear();
            }
        });

//        final JCheckBox showIRIsCheckBox = new JCheckBox("Show IRIs");
//        showIRIsCheckBox.setSelected(showIRI);
//
//        showIRIsCheckBox.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//            	dbMappingList.setShowIRIs(showIRIsCheckBox.isSelected());
//            }
//        });

        buttonHolder.add(Box.createHorizontalStrut(5));
        buttonHolder.add(openButton);
        buttonHolder.add(Box.createHorizontalStrut(5));
        buttonHolder.add(saveButton);
        buttonHolder.add(Box.createHorizontalStrut(5));
        buttonHolder.add(clearButton);
        buttonHolder.add(Box.createHorizontalStrut(5));
        buttonHolder.add(Box.createHorizontalStrut(5));
        add(buttonHolder, BorderLayout.SOUTH);
*/
    }

    @Override
    protected void disposeOWLView() {

    }
}
