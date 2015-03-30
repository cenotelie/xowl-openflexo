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

import org.openflexo.foundation.fml.IndividualRole;
import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.model.annotations.ImplementationClass;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.XMLElement;
import org.xowl.openflexo.xowlconnector.model.XOWLEntityRoleIndividual;

/**
 * Represents the role of an OWL individual
 *
 * @author Laurent Wouters
 */
@ModelEntity
@ImplementationClass(XOWLIndividualRole.XOWLIndividualRoleImpl.class)
@XMLElement
@FML("XOWLIndividualRole")
public interface XOWLIndividualRole extends IndividualRole<XOWLEntityRoleIndividual> {
    /**
     * The default implementation
     */
    abstract class XOWLIndividualRoleImpl extends IndividualRoleImpl<XOWLEntityRoleIndividual> implements XOWLIndividualRole {
        /**
         * Initializes this instance
         */
        public XOWLIndividualRoleImpl() {
            super();
        }
    }
}
