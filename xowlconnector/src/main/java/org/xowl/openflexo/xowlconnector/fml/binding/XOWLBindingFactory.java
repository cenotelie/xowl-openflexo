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


package org.xowl.openflexo.xowlconnector.fml.binding;

import org.openflexo.connie.binding.BindingPathElement;
import org.openflexo.connie.binding.SimplePathElement;
import org.openflexo.foundation.fml.TechnologySpecificCustomType;
import org.openflexo.foundation.ontology.IndividualOfClass;
import org.openflexo.foundation.technologyadapter.TechnologyAdapterBindingFactory;
import org.xowl.openflexo.xowlconnector.model.XOWLClass;
import org.xowl.openflexo.xowlconnector.model.XOWLProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory of binding elements between the FML and object in the xOWL connector
 *
 * @author Laurent Wouters
 */
public class XOWLBindingFactory extends TechnologyAdapterBindingFactory {
    @Override
    protected SimplePathElement makeSimplePathElement(Object object, BindingPathElement parent) {
        if ((parent.getType() instanceof IndividualOfClass) && (object instanceof XOWLProperty)) {
            return PropertyStatementPathElement.makePropertyStatementPathElement(parent, (XOWLProperty) object);
        }
        return null;
    }

    @Override
    public boolean handleType(TechnologySpecificCustomType technologySpecificType) {
        return ((technologySpecificType instanceof IndividualOfClass) && ((IndividualOfClass) technologySpecificType).getOntologyClass() instanceof XOWLClass);
    }

    @Override
    public List<? extends SimplePathElement> getAccessibleSimplePathElements(BindingPathElement element) {
        if (element.getType() instanceof IndividualOfClass) {
            IndividualOfClass parentType = (IndividualOfClass) element.getType();
            List<SimplePathElement> returned = new ArrayList<>();
            returned.add(new URIPathElement(element));
            if (parentType.getOntologyClass() instanceof XOWLClass) {
                for (XOWLProperty property : ((XOWLClass) parentType.getOntologyClass()).getProperties()) {
                    returned.add(getSimplePathElement(property, element));
                }
            }
            return returned;
        }
        return super.getAccessibleSimplePathElements(element);

    }

    @Override
    public SimplePathElement makeSimplePathElement(BindingPathElement parent, String propertyName) {
        for (SimplePathElement e : getAccessibleSimplePathElements(parent)) {
            if (e.getLabel().equals(propertyName)) {
                return e;
            }
        }
        return super.makeSimplePathElement(parent, propertyName);
    }
}
