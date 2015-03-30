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

package org.xowl.openflexo.xowlconnector.fml.editionaction;

import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.foundation.fml.editionaction.AddClass;
import org.openflexo.foundation.fml.rt.TypeAwareModelSlotInstance;
import org.openflexo.foundation.fml.rt.action.FlexoBehaviourAction;
import org.openflexo.model.annotations.ImplementationClass;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.XMLElement;
import org.xowl.openflexo.xowlconnector.XOWLModelSlot;
import org.xowl.openflexo.xowlconnector.model.XOWLClass;
import org.xowl.openflexo.xowlconnector.model.XOWLOntology;

/**
 * Represents the action of adding a class to a xOWL ontology
 *
 * @author Laurent Wouters
 */
@ModelEntity
@ImplementationClass(AddXOWLClass.AddXOWLClassImpl.class)
@XMLElement
@FML("AddXOWLClass")
public interface AddXOWLClass extends AddClass<XOWLModelSlot, XOWLClass>, XOWLAction<XOWLClass> {
    /**
     * The default implementation
     */
    abstract class AddXOWLClassImpl extends AddClassImpl<XOWLModelSlot, XOWLClass> implements AddXOWLClass {
        @Override
        public XOWLClass getOntologyClass() {
            return (XOWLClass) super.getOntologyClass();
        }

        @Override
        public Class<XOWLClass> getOntologyClassClass() {
            return XOWLClass.class;
        }

        @Override
        public XOWLClass execute(FlexoBehaviourAction action) {
            return getModelSlotInstance(action).getAccessedResourceData().newClass();
        }

        @Override
        public TypeAwareModelSlotInstance<XOWLOntology, XOWLOntology, XOWLModelSlot> getModelSlotInstance(FlexoBehaviourAction action) {
            return (TypeAwareModelSlotInstance<XOWLOntology, XOWLOntology, XOWLModelSlot>) super.getModelSlotInstance(action);
        }
    }
}
