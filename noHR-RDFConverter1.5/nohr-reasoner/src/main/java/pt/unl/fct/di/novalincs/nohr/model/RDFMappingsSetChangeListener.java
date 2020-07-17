package pt.unl.fct.di.novalincs.nohr.model;

//TODO Comment this
public interface RDFMappingsSetChangeListener {


    void added(RDFMapping rdfMapping);

    void cleared();

    void update(RDFMapping oldRDFMapping, RDFMapping newDBMapping);

}
