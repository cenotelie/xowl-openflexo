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
package org.xowl.openflexo.xowlconnector.model;

import org.openflexo.foundation.ontology.IFlexoOntologyDataProperty;
import org.xowl.openflexo.xowlconnector.XOWLTechnologyAdapter;
import org.xowl.store.Vocabulary;

/**
 * Represents a data property role for an entity in a xOWL ontology
 *
 * @author Laurent Wouters
 */
public class XOWLEntityRoleDataProperty extends XOWLEntityRoleProperty implements IFlexoOntologyDataProperty<XOWLTechnologyAdapter> {
    /**
     * Initializes this data property role for an entity
     *
     * @param entity The represented entity
     */
    public XOWLEntityRoleDataProperty(XOWLEntity entity) {
        super(entity);
    }

    @Override
    public XOWLDatatype getRange() {
        return new XOWLDatatype(ontology.resolve(entity.getObjectValue(Vocabulary.rdfsRange)));
    }
}