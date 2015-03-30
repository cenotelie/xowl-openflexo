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

package org.xowl.openflexo.xowlconnector.model.action;

import org.openflexo.foundation.FlexoEditor;
import org.openflexo.foundation.FlexoObject.FlexoObjectImpl;
import org.openflexo.foundation.action.FlexoAction;
import org.openflexo.foundation.action.FlexoActionType;
import org.openflexo.foundation.ontology.DuplicateURIException;
import org.openflexo.toolbox.StringUtils;
import org.xowl.openflexo.xowlconnector.model.*;
import org.xowl.store.Vocabulary;

import java.util.Vector;

/**
 * Represents an action for the creation of an individual
 *
 * @author Laurent Wouters
 */
public class CreateOntologyIndividual extends FlexoAction<CreateOntologyIndividual, XOWLObject, XOWLEntity> {
    /**
     * Represents the power type of this action
     */
    public static final FlexoActionType<CreateOntologyIndividual, XOWLObject, XOWLEntity> ACTION_TYPE = new FlexoActionType<CreateOntologyIndividual, XOWLObject, XOWLEntity>("create_individual", FlexoActionType.newMenu, FlexoActionType.defaultGroup, FlexoActionType.ADD_ACTION_TYPE) {
        @Override
        public CreateOntologyIndividual makeNewAction(XOWLObject focusedObject, Vector<XOWLEntity> globalSelection, FlexoEditor editor) {
            return new CreateOntologyIndividual(focusedObject, globalSelection, editor);
        }

        @Override
        public boolean isVisibleForSelection(XOWLObject object, Vector<XOWLEntity> globalSelection) {
            return object != null;
        }

        @Override
        public boolean isEnabledForSelection(XOWLObject object, Vector<XOWLEntity> globalSelection) {
            return object != null && !object.getOntology().isReadOnly();
        }
    };

    static {
        FlexoObjectImpl.addActionForClass(CreateOntologyIndividual.ACTION_TYPE, XOWLOntology.class);
        FlexoObjectImpl.addActionForClass(CreateOntologyIndividual.ACTION_TYPE, XOWLClass.class);
    }

    /**
     * The name of the new individual
     */
    private final String name;
    /**
     * The instantiated class
     */
    private final XOWLClass clazz;
    /**
     * The new individual
     */
    private XOWLIndividual individual;

    /**
     * Initializes this action
     *
     * @param focusedObject   The object holding the focus
     * @param globalSelection The selected objects
     * @param editor          The editor requesting the action
     */
    private CreateOntologyIndividual(XOWLObject focusedObject, Vector<XOWLEntity> globalSelection, FlexoEditor editor) {
        super(ACTION_TYPE, focusedObject, globalSelection, editor);
        this.name = "NewIndividual";
        this.clazz = focusedObject instanceof XOWLClass ? (XOWLClass) focusedObject : focusedObject.getOntology().getClass(Vocabulary.owlThing);
    }

    @Override
    protected void doAction(Object context) throws DuplicateURIException {
        individual = getFocusedObject().getFlexoOntology().newIndividual(clazz);
    }

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(name);
    }
}
