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

import org.openflexo.connie.BindingEvaluationContext;
import org.openflexo.connie.binding.BindingPathElement;
import org.openflexo.connie.binding.SimplePathElement;
import org.openflexo.connie.exception.NullReferenceException;
import org.openflexo.connie.exception.TypeMismatchException;
import org.xowl.openflexo.xowlconnector.model.XOWLEntityRoleDataProperty;
import org.xowl.openflexo.xowlconnector.model.XOWLEntityRoleIndividual;
import org.xowl.openflexo.xowlconnector.model.XOWLEntityRoleObjectProperty;
import org.xowl.openflexo.xowlconnector.model.XOWLEntityRoleProperty;

import java.lang.reflect.Type;

/**
 * Represents a path element for a property
 *
 * @author Laurent Wouters
 */
public abstract class PropertyStatementPathElement extends SimplePathElement {
    /**
     * The property represented by this path element
     */
    private final XOWLEntityRoleProperty property;

    /**
     * Creates a path element from the specified parent element and an property
     *
     * @param parent   The parent path element
     * @param property The applied OWL property
     * @return The path element
     */
    public static PropertyStatementPathElement makePropertyStatementPathElement(BindingPathElement parent, XOWLEntityRoleProperty property) {
        if (property instanceof XOWLEntityRoleDataProperty) {
            return new DataPropertyStatementPathElement(parent, (XOWLEntityRoleDataProperty) property);
        } else {
            return new ObjectPropertyStatementPathElement(parent, (XOWLEntityRoleObjectProperty) property);
        }
    }

    /**
     * Initializes this path element
     *
     * @param parent   The parent path element
     * @param property The represented property
     */
    PropertyStatementPathElement(BindingPathElement parent, XOWLEntityRoleProperty property) {
        super(parent, property.getName(), XOWLEntityRoleIndividual.class);
        this.property = property;
    }

    public XOWLEntityRoleProperty getProperty() {
        return property;
    }

    @Override
    public String getLabel() {
        return getPropertyName();
    }

    @Override
    public String getTooltipText(Type resultingType) {
        return property.getDisplayableDescription();
    }

    @Override
    public Object getBindingValue(Object target, BindingEvaluationContext context) throws TypeMismatchException, NullReferenceException {
        return ((XOWLEntityRoleIndividual) target).getPropertyValue(getProperty());
    }

    @Override
    public void setBindingValue(Object value, Object target, BindingEvaluationContext context) throws TypeMismatchException, NullReferenceException {
        ((XOWLEntityRoleIndividual) target).addToPropertyValue(getProperty(), value);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getLabel() + "[" + property + "]";
    }
}
