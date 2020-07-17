package pt.unl.fct.di.novalincs.nohr.plugin.rdfmapping;

import org.protege.editor.core.ui.list.MListSectionHeader;
import pt.unl.fct.di.novalincs.nohr.model.RDFMapping;
import pt.unl.fct.di.novalincs.nohr.model.RDFMappingSet;

import javax.swing.*;
import java.util.List;

public class RDFMappingListModel extends AbstractListModel<Object> {

    private static final MListSectionHeader HEADER = new MListSectionHeader() {
        @Override
        public String getName() {
            return "RDF Mappings";
        }

        @Override
        public boolean canAdd() {
            return true;
        }
    };

    private static final long serialVersionUID = 1L;

    private final RDFMappingSet rdfMappingSet;

    private final RDFMappingEditor rdfMappingEditor;

    private final List<Object> rdfMappingItems;

    private final RDFMappingSetPersistenceManager rdfMappingSetPersistenceManager;

    public RDFMappingListModel(RDFMappingSet rdfMappingSet) {
        this.rdfMappingSet = rdfMappingSet;
    }


    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Object getElementAt(int index) {
        return null;
    }
}
