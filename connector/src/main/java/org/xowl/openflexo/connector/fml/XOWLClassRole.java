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

package org.xowl.openflexo.connector.fml;

import org.openflexo.foundation.fml.ClassRole;
import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.model.annotations.ImplementationClass;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.XMLElement;
import org.xowl.openflexo.connector.model.XOWLClass;

/**
 * Represents the role of an OWL class
 *
 * @author Laurent Wouters
 */
@ModelEntity
@ImplementationClass(XOWLClassRole.XOWLClassRoleImpl.class)
@XMLElement
@FML("XOWLClassRole")
public interface XOWLClassRole extends ClassRole<XOWLClass> {
    /**
     * The default implementation
     */
    abstract class XOWLClassRoleImpl extends ClassRoleImpl<XOWLClass> implements XOWLClassRole {
        /**
         * Initializes this instance
         */
        public XOWLClassRoleImpl() {
            super();
        }
    }
}
