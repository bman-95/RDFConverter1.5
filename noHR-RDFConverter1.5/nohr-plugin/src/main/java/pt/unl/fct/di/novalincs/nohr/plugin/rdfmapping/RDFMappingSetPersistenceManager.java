package pt.unl.fct.di.novalincs.nohr.plugin.rdfmapping;

import pt.unl.fct.di.novalincs.nohr.model.vocabulary.Vocabulary;
import pt.unl.fct.di.novalincs.nohr.parsing.NoHRParser;
import pt.unl.fct.di.novalincs.nohr.parsing.NoHRRecursiveDescentParser;


//TODO commment
public class RDFMappingSetPersistenceManager {

    private final NoHRParser parser;

    private Vocabulary vocabulary;

    public RDFMappingSetPersistenceManager(Vocabulary vocabulary){
        this.vocabulary = vocabulary;

        parser = new NoHRRecursiveDescentParser(this.vocabulary);
    }


}
