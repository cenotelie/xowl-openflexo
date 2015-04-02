/*******************************************************************************
 * Copyright (c) 2015 Laurent Wouters
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Laurent Wouters - lwouters@xowl.org
 ******************************************************************************/

package org.xowl.openflexo.connector.model;

import org.openflexo.foundation.ontology.*;
import org.xowl.openflexo.connector.XOWLTechnologyAdapter;
import org.xowl.store.ProxyObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an entity in a xOWL ontology
 *
 * @author Laurent Wouters
 */
public class XOWLEntity extends XOWLObject implements IFlexoOntologyConcept<XOWLTechnologyAdapter> {
    /**
     * The represented entity
     */
    protected final ProxyObject entity;
    /**
     * The containing ontology
     */
    protected final XOWLOntology ontology;

    /**
     * Initializes this entity
     *
     * @param adapter  The parent technology adapter
     * @param ontology The containing ontology
     * @param entity   The represented entity
     */
    public XOWLEntity(XOWLTechnologyAdapter adapter, XOWLOntology ontology, ProxyObject entity) {
        super(adapter);
        this.ontology = ontology;
        this.entity = entity;
    }

    @Override
    public String getName() {
        return getURI();
    }

    @Override
    public void setName(String name) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getURI() {
        return entity.getIRIString();
    }

    @Override
    public boolean delete(Object... context) {
        if (!super.delete(context))
            return false;
        entity.delete();
        return true;
    }

    @Override
    public XOWLOntology getFlexoOntology() {
        return ontology;
    }

    @Override
    public XOWLOntology getOntology() {
        return getFlexoOntology();
    }

    @Override
    public String getDisplayableDescription() {
        return entity.getIRIString();
    }

    @Override
    public List<? extends IFlexoOntologyAnnotation> getAnnotations() {
        // TODO: retrieve the annotations here
        return new ArrayList<>(0);
    }

    @Override
    public IFlexoOntologyConceptContainer<XOWLTechnologyAdapter> getContainer() {
        return ontology;
    }

    @Override
    public List<? extends IFlexoOntologyFeatureAssociation<XOWLTechnologyAdapter>> getStructuralFeatureAssociations() {
        // TODO: retrieve the object and data properties here
        return new ArrayList<>(0);
    }

    @Override
    public List<? extends IFlexoOntologyFeatureAssociation<XOWLTechnologyAdapter>> getBehaviouralFeatureAssociations() {
        return new ArrayList<>(0);
    }

    @Override
    public boolean isSuperConceptOf(IFlexoOntologyConcept<XOWLTechnologyAdapter> concept) {
        return false;
    }

    @Override
    public boolean isSubConceptOf(IFlexoOntologyConcept<XOWLTechnologyAdapter> concept) {
        return false;
    }

    @Override
    public <T> T accept(IFlexoOntologyConceptVisitor<T> visitor) {
        return null;
    }

    @Override
    public boolean equalsToConcept(IFlexoOntologyConcept<XOWLTechnologyAdapter> concept) {
        return false;
    }

    @Override
    public List<? extends IFlexoOntologyStructuralProperty<XOWLTechnologyAdapter>> getPropertiesTakingMySelfAsRange() {
        return null;
    }

    @Override
    public List<? extends IFlexoOntologyFeature<XOWLTechnologyAdapter>> getPropertiesTakingMySelfAsDomain() {
        return null;
    }
}
