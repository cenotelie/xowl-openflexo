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
package org.xowl.openflexo.xowlconnector.model;

import org.openflexo.foundation.ontology.IFlexoOntologyClass;
import org.openflexo.foundation.ontology.IFlexoOntologyIndividual;
import org.openflexo.foundation.ontology.IFlexoOntologyPropertyValue;
import org.openflexo.foundation.ontology.IFlexoOntologyStructuralProperty;
import org.xowl.openflexo.xowlconnector.XOWLTechnologyAdapter;
import org.xowl.store.ProxyObject;
import org.xowl.utils.collections.Couple;

import java.util.*;

/**
 * Represents an individual role for an entity in a xOWL ontology
 *
 * @author Laurent Wouters
 */
public class XOWLIndividual extends XOWLEntity implements IFlexoOntologyIndividual<XOWLTechnologyAdapter> {
    /**
     * Initializes this individual role for an entity
     *
     * @param entity The represented entity
     */
    public XOWLIndividual(XOWLEntity entity) {
        super(entity.getTechnologyAdapter(), entity.getOntology(), entity.entity);
    }

    @Override
    public List<XOWLClass> getTypes() {
        List<XOWLClass> result = new ArrayList<>();
        for (ProxyObject proxy : entity.getClassifiers()) {
            result.add(ontology.getClass(proxy.getIRIString()));
        }
        return result;
    }

    @Override
    public boolean isIndividualOf(IFlexoOntologyClass<XOWLTechnologyAdapter> aClass) {
        if (!(aClass instanceof XOWLClass))
            return false;
        return getTypes().contains(aClass);
    }

    @Override
    public List<XOWLPropertyValue> getPropertyValues() {
        Collection<Couple<String, Object>> originals = entity.getValues();
        List<XOWLPropertyValue> result = new ArrayList<>();
        Map<String, XOWLPropertyValue> mapObjectProperties = new HashMap<>();
        Map<String, XOWLPropertyValue> mapDataProperties = new HashMap<>();
        for (Couple<String, Object> original : originals) {
            if (original.y instanceof ProxyObject) {
                XOWLPropertyValue mapping = mapObjectProperties.get(original.x);
                if (mapping == null) {
                    mapping = new XOWLPropertyValue(this, ontology.getObjectProperty(original.x));
                    mapObjectProperties.put(original.x, mapping);
                }
                mapping.addValue(ontology.resolve((ProxyObject) original.y));
            } else {
                XOWLPropertyValue mapping = mapDataProperties.get(original.x);
                if (mapping == null) {
                    mapping = new XOWLPropertyValue(this, ontology.getDataProperty(original.x));
                    mapDataProperties.put(original.x, mapping);
                }
                mapping.addValue(original.y);
            }
        }
        return result;
    }

    @Override
    public IFlexoOntologyPropertyValue<XOWLTechnologyAdapter> getPropertyValue(IFlexoOntologyStructuralProperty<XOWLTechnologyAdapter> property) {
        XOWLPropertyValue mapping = new XOWLPropertyValue(this, (XOWLProperty) property);
        if (property instanceof XOWLObjectProperty) {
            Collection<ProxyObject> proxies = entity.getObjectValues(((XOWLProperty) property).entity.getIRIString());
            for (ProxyObject proxy : proxies) {
                mapping.addValue(ontology.resolve(proxy));
            }
        } else {
            Collection<Object> values = entity.getDataValues(((XOWLProperty) property).entity.getIRIString());
            for (Object value : values) {
                mapping.addValue(value);
            }
        }
        return mapping;
    }

    @Override
    public IFlexoOntologyPropertyValue<XOWLTechnologyAdapter> addToPropertyValue(IFlexoOntologyStructuralProperty<XOWLTechnologyAdapter> property, Object newValue) {
        if (property instanceof XOWLObjectProperty) {
            entity.setValue(((XOWLProperty) property).entity.getIRIString(), ((XOWLEntity) newValue).entity);
        } else {
            entity.setValue(((XOWLProperty) property).entity.getIRIString(), newValue);
        }
        return getPropertyValue(property);
    }

    @Override
    public IFlexoOntologyPropertyValue<XOWLTechnologyAdapter> removeFromPropertyValue(IFlexoOntologyStructuralProperty<XOWLTechnologyAdapter> property, Object valueToRemove) {
        if (property instanceof XOWLObjectProperty) {
            entity.unset(((XOWLProperty) property).entity.getIRIString(), ((XOWLEntity) valueToRemove).entity);
        } else {
            entity.unset(((XOWLProperty) property).entity.getIRIString(), valueToRemove);
        }
        return getPropertyValue(property);
    }
}