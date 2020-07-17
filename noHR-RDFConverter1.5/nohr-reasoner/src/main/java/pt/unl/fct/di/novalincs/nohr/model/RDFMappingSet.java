package pt.unl.fct.di.novalincs.nohr.model;

import java.util.Set;

public interface RDFMappingSet extends Set<RDFMapping> {

    void addListener(RDFMappingsSetChangeListener listener);
}
