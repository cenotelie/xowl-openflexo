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


package org.xowl.openflexo.connector.fml.binding;

import org.openflexo.connie.BindingEvaluationContext;
import org.openflexo.connie.binding.BindingPathElement;
import org.openflexo.connie.binding.SimplePathElement;
import org.openflexo.connie.exception.NullReferenceException;
import org.openflexo.connie.exception.TypeMismatchException;
import org.xowl.openflexo.connector.model.XOWLEntity;

import java.lang.reflect.Type;

/**
 * Represents a path element for the URI of an entity
 *
 * @author Laurent Wouters
 */
public class URIPathElement extends SimplePathElement {
    /**
     * Initializes this path element
     *
     * @param parent The parent element
     */
    public URIPathElement(BindingPathElement parent) {
        super(parent, "uri", String.class);
    }

    @Override
    public String getLabel() {
        return getPropertyName();
    }

    @Override
    public String getTooltipText(Type resultingType) {
        return "uri";
    }

    @Override
    public Object getBindingValue(Object target, BindingEvaluationContext context) throws TypeMismatchException, NullReferenceException {
        if (target instanceof XOWLEntity) {
            return ((XOWLEntity) target).getURI();
        }
        return null;
    }

    @Override
    public void setBindingValue(Object value, Object target, BindingEvaluationContext context) throws TypeMismatchException, NullReferenceException {
    }
}
