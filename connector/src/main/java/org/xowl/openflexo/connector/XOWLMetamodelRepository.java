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

import org.openflexo.foundation.resource.FlexoResourceCenter;
import org.openflexo.foundation.technologyadapter.MetaModelRepository;
import org.xowl.openflexo.connector.model.XOWLOntology;

/**
 * Repository of metamodel for xOWL
 *
 * @author Laurent Wouters
 */
public class XOWLMetamodelRepository extends MetaModelRepository<XOWLResource, XOWLOntology, XOWLOntology, XOWLTechnologyAdapter> {
    /**
     * Initializes this repository
     *
     * @param technologyAdapter The xOWL technology adapter
     * @param resourceCenter    The parent resource center
     */
    public XOWLMetamodelRepository(XOWLTechnologyAdapter technologyAdapter, FlexoResourceCenter<?> resourceCenter) {
        super(technologyAdapter, resourceCenter);
    }

    @Override
    public String getDefaultBaseURI() {
        return XOWLModelRepository.BASE_URI;
    }
}
