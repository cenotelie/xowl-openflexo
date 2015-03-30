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

package org.xowl.openflexo.xowlconnector.fml;

import org.openflexo.foundation.fml.ObjectPropertyRole;
import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.model.annotations.ImplementationClass;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.XMLElement;
import org.xowl.openflexo.xowlconnector.model.XOWLObjectProperty;

/**
 * Represents the role of an OWL object property
 *
 * @author Laurent Wouters
 */
@ModelEntity
@ImplementationClass(XOWLObjectPropertyRole.XOWLObjectPropertyRoleImpl.class)
@XMLElement
@FML("XOWLObjectPropertyRole")
public interface XOWLObjectPropertyRole extends ObjectPropertyRole<XOWLObjectProperty> {
    /**
     * The default implementation
     */
    abstract class XOWLObjectPropertyRoleImpl extends ObjectPropertyRoleImpl<XOWLObjectProperty> implements XOWLObjectPropertyRole {
        /**
         * Initializes this instance
         */
        public XOWLObjectPropertyRoleImpl() {
            super();
        }
    }
}
