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
import org.openflexo.foundation.ontology.IndividualOfClass;
import org.xowl.openflexo.xowlconnector.model.XOWLEntityRoleObjectProperty;

import java.lang.reflect.Type;

/**
 * Represents a path element for an object property
 *
 * @author Laurent Wouters
 */
public class ObjectPropertyStatementPathElement extends PropertyStatementPathElement {
    /**
     * Initializes this path element
     *
     * @param parent   The parent path element
     * @param property The represented property
     */
    ObjectPropertyStatementPathElement(BindingPathElement parent, XOWLEntityRoleObjectProperty property) {
        super(parent, property);
    }

    @Override
    public XOWLEntityRoleObjectProperty getProperty() {
        return (XOWLEntityRoleObjectProperty) super.getProperty();
    }

    @Override
    public Type getType() {
        return IndividualOfClass.getIndividualOfClass(getProperty().getRange());
    }
}
