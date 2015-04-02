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

import org.openflexo.foundation.ontology.IFlexoOntology;
import org.openflexo.foundation.ontology.IFlexoOntologyConcept;
import org.openflexo.foundation.ontology.IFlexoOntologyFeatureAssociation;
import org.openflexo.foundation.ontology.IFlexoOntologyStructuralProperty;
import org.xowl.openflexo.connector.XOWLTechnologyAdapter;
import org.xowl.store.ProxyObject;
import org.xowl.store.Vocabulary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a property role for an entity in a xOWL ontology
 *
 * @author Laurent Wouters
 */
public abstract class XOWLProperty extends XOWLEntity implements IFlexoOntologyStructuralProperty<XOWLTechnologyAdapter> {
    /**
     * Initializes this property role for an entity
     *
     * @param entity The represented entity
     */
    public XOWLProperty(XOWLEntity entity) {
        super(entity.getTechnologyAdapter(), entity.getOntology(), entity.entity);
    }

    @Override
    public IFlexoOntologyConcept<XOWLTechnologyAdapter> getDomain() {
        return ontology.resolve(entity.getObjectValue(Vocabulary.rdfsDomain));
    }

    @Override
    public boolean isAnnotationProperty() {
        // TODO: handle annotation properties
        return false;
    }

    @Override
    public List<XOWLProperty> getSuperProperties() {
        Collection<ProxyObject> supers = entity.getObjectValues(Vocabulary.rdfsSubPropertyOf);
        List<XOWLProperty> result = new ArrayList<>();
        for (ProxyObject proxy : supers)
            result.add(ontology.getProperty(proxy.getIRIString()));
        return result;
    }

    @Override
    public List<? extends IFlexoOntologyStructuralProperty<XOWLTechnologyAdapter>> getSubProperties(IFlexoOntology<XOWLTechnologyAdapter> context) {
        Collection<ProxyObject> supers = entity.getObjectsFrom(Vocabulary.rdfsSubPropertyOf);
        List<XOWLProperty> result = new ArrayList<>();
        for (ProxyObject proxy : supers)
            result.add(ontology.getProperty(proxy.getIRIString()));
        return result;
    }

    @Override
    public List<? extends IFlexoOntologyFeatureAssociation<XOWLTechnologyAdapter>> getReferencingFeatureAssociations() {
        // TODO: implement this ?
        return new ArrayList<>(0);
    }
}
