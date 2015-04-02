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

package org.xowl.openflexo.connector.fml.editionaction;

import org.openflexo.foundation.fml.editionaction.TechnologySpecificAction;
import org.openflexo.model.annotations.ModelEntity;
import org.xowl.openflexo.connector.XOWLModelSlot;
import org.xowl.openflexo.connector.model.XOWLObject;

/**
 * Represents an action on a xOWL ontology
 *
 * @param <T> The type of object interacted with
 * @author Laurent Wouters
 */
@ModelEntity(isAbstract = true)
public interface XOWLAction<T extends XOWLObject> extends TechnologySpecificAction<XOWLModelSlot, T> {

}
