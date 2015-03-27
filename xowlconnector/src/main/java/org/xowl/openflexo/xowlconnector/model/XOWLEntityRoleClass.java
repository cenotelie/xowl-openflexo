/**********************************************************************
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
 **********************************************************************/
package org.xowl.openflexo.xowlconnector.model;

import org.openflexo.foundation.ontology.IFlexoOntology;
import org.openflexo.foundation.ontology.IFlexoOntologyClass;
import org.xowl.openflexo.xowlconnector.XOWLTechnologyAdapter;
import org.xowl.store.ProxyObject;
import org.xowl.store.Vocabulary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a class role for an entity in a xOWL ontology
 *
 * @author Laurent Wouters
 */
public class XOWLEntityRoleClass extends XOWLEntity implements IFlexoOntologyClass<XOWLTechnologyAdapter> {
    /**
     * Initializes this class role for an entity
     *
     * @param entity The represented entity
     */
    public XOWLEntityRoleClass(XOWLEntity entity) {
        super(entity.getTechnologyAdapter(), entity.getOntology(), entity.entity);
    }

    @Override
    public List<XOWLEntityRoleClass> getSuperClasses() {
        Collection<ProxyObject> supers = entity.getObjectValues(Vocabulary.rdfsSubClassOf);
        List<XOWLEntityRoleClass> result = new ArrayList<>();
        for (ProxyObject proxy : supers)
            result.add(ontology.getClass(proxy.getIRIString()));
        return result;
    }

    @Override
    public List<XOWLEntityRoleClass> getSubClasses(IFlexoOntology<XOWLTechnologyAdapter> context) {
        Collection<ProxyObject> supers = entity.getObjectsFrom(Vocabulary.rdfsSubClassOf);
        List<XOWLEntityRoleClass> result = new ArrayList<>();
        for (ProxyObject proxy : supers)
            result.add(ontology.getClass(proxy.getIRIString()));
        return result;
    }

    @Override
    public boolean isSuperClassOf(IFlexoOntologyClass<XOWLTechnologyAdapter> aClass) {
        if (!(aClass instanceof XOWLEntityRoleClass))
            return false;
        return getSubClasses(null).contains(aClass);
    }

    @Override
    public boolean isNamedClass() {
        // all classes here are named classes because they are accessed through a proxy object
        return true;
    }

    @Override
    public boolean isRootConcept() {
        return entity.getIRIString().equals(Vocabulary.owl + "Thing");
    }
}
