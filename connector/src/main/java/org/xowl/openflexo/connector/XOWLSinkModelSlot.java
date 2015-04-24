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

package org.xowl.openflexo.connector;

import org.openflexo.foundation.fml.annotations.DeclareEditionActions;
import org.openflexo.foundation.fml.annotations.DeclareFlexoRoles;
import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.model.annotations.ImplementationClass;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.XMLElement;
import org.xowl.openflexo.connector.fml.XOWLClassRole;
import org.xowl.openflexo.connector.fml.XOWLDataPropertyRole;
import org.xowl.openflexo.connector.fml.XOWLIndividualRole;
import org.xowl.openflexo.connector.fml.XOWLObjectPropertyRole;
import org.xowl.openflexo.connector.fml.editionaction.AddXOWLClass;
import org.xowl.openflexo.connector.fml.editionaction.AddXOWLIndividual;

/**
 * Represents a model slot for the xOWL technology that does not provide data to be federated but observe the federated data
 *
 * @author Laurent Wouters
 */
@DeclareFlexoRoles({XOWLIndividualRole.class, XOWLClassRole.class, XOWLDataPropertyRole.class, XOWLObjectPropertyRole.class})
@DeclareEditionActions({AddXOWLIndividual.class, AddXOWLClass.class})
@ModelEntity
@ImplementationClass(XOWLSinkModelSlotImpl.class)
@XMLElement
@FML("XOWLSinkModelSlot")
public interface XOWLSinkModelSlot extends XOWLModelSlot {

}
