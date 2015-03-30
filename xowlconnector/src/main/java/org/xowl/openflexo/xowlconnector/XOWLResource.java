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

package org.xowl.openflexo.xowlconnector;

import org.openflexo.foundation.technologyadapter.FlexoMetaModelResource;
import org.openflexo.foundation.technologyadapter.FlexoModelResource;
import org.openflexo.model.annotations.ImplementationClass;
import org.openflexo.model.annotations.ModelEntity;
import org.xowl.openflexo.xowlconnector.model.XOWLOntology;

/**
 * Represents a xOWL resource
 *
 * @author Laurent Wouters
 */
@ModelEntity
@ImplementationClass(XOWLResourceImpl.class)
public interface XOWLResource extends FlexoModelResource<XOWLOntology, XOWLOntology, XOWLTechnologyAdapter, XOWLTechnologyAdapter>, FlexoMetaModelResource<XOWLOntology, XOWLOntology, XOWLTechnologyAdapter> {

}
