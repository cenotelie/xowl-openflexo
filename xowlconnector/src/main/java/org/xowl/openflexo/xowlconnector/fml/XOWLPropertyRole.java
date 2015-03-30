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

import org.openflexo.foundation.fml.PropertyRole;
import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.model.annotations.ImplementationClass;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.XMLElement;
import org.xowl.openflexo.xowlconnector.model.XOWLEntityRoleProperty;

/**
 * Represents the role of an OWL property
 *
 * @author Laurent Wouters
 */
@ModelEntity
@ImplementationClass(XOWLPropertyRole.XOWLPropertyRoleImpl.class)
@XMLElement
@FML("XOWLPropertyRole")
public interface XOWLPropertyRole extends PropertyRole<XOWLEntityRoleProperty> {
    /**
     * The default implementation
     */
    abstract class XOWLPropertyRoleImpl extends PropertyRoleImpl<XOWLEntityRoleProperty> implements XOWLPropertyRole {
        /**
         * Initializes this instance
         */
        public XOWLPropertyRoleImpl() {
            super();
        }
    }
}
