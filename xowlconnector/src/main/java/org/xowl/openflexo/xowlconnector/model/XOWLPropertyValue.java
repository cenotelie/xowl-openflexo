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

import org.openflexo.foundation.ontology.IFlexoOntologyPropertyValue;
import org.xowl.openflexo.xowlconnector.XOWLTechnologyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a couple of a property and its value
 *
 * @author Laurent Wouters
 */
public class XOWLPropertyValue extends XOWLObject implements IFlexoOntologyPropertyValue<XOWLTechnologyAdapter> {
    /**
     * The subject
     */
    private XOWLEntity subject;
    /**
     * The property
     */
    private XOWLEntityRoleProperty property;
    /**
     * The value
     */
    private List<Object> values;

    /**
     * Initializes this property value
     *
     * @param subject  The subject
     * @param property The property
     */
    public XOWLPropertyValue(XOWLEntity subject, XOWLEntityRoleProperty property) {
        super(subject.getTechnologyAdapter());
        this.subject = subject;
        this.property = property;
        this.values = new ArrayList<>(2);
    }

    /**
     * Adds a value to this property
     *
     * @param value The value to add
     */
    protected void addValue(Object value) {
        values.add(value);
    }

    @Override
    public XOWLEntityRoleProperty getProperty() {
        return property;
    }

    @Override
    public List<?> getValues() {
        return values;
    }

    @Override
    public String getName() {
        return property.getName();
    }

    @Override
    public void setName(String name) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getURI() {
        return property.getURI();
    }

    @Override
    public XOWLOntology getFlexoOntology() {
        return subject.getFlexoOntology();
    }

    @Override
    public String getDisplayableDescription() {
        return subject.getDisplayableDescription() + " " + property.getDisplayableDescription() + " => " + values.toString();
    }
}
