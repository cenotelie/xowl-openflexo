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

package org.xowl.openflexo.connector.model.action;

import org.openflexo.foundation.FlexoEditor;
import org.openflexo.foundation.FlexoObject.FlexoObjectImpl;
import org.openflexo.foundation.action.FlexoAction;
import org.openflexo.foundation.action.FlexoActionType;
import org.xowl.openflexo.connector.model.XOWLEntity;

import java.util.Vector;

/**
 * Represents an action of deleting entities in an ontology
 *
 * @author Laurent Wouters
 */
public class DeleteOntologyObjects extends FlexoAction<DeleteOntologyObjects, XOWLEntity, XOWLEntity> {
    /**
     * Represents the power type of this action
     */
    public static final FlexoActionType<DeleteOntologyObjects, XOWLEntity, XOWLEntity> ACTION_TYPE = new FlexoActionType<DeleteOntologyObjects, XOWLEntity, XOWLEntity>("delete", FlexoActionType.editGroup, FlexoActionType.DELETE_ACTION_TYPE) {
        @Override
        public DeleteOntologyObjects makeNewAction(XOWLEntity focusedObject, Vector<XOWLEntity> globalSelection, FlexoEditor editor) {
            return new DeleteOntologyObjects(focusedObject, globalSelection, editor);
        }

        @Override
        public boolean isVisibleForSelection(XOWLEntity object, Vector<XOWLEntity> globalSelection) {
            return true;
        }

        @Override
        public boolean isEnabledForSelection(XOWLEntity focusedObject, Vector<XOWLEntity> globalSelection) {
            return (focusedObject != null || (globalSelection != null && !globalSelection.isEmpty()));
        }
    };

    static {
        FlexoObjectImpl.addActionForClass(DeleteOntologyObjects.ACTION_TYPE, XOWLEntity.class);
    }

    /**
     * The objects to delete
     */
    private final Vector<XOWLEntity> toDelete;

    /**
     * Initializes this action
     *
     * @param focusedObject   The object holding the focus
     * @param globalSelection The selected objects
     * @param editor          The editor requesting the action
     */
    private DeleteOntologyObjects(XOWLEntity focusedObject, Vector<XOWLEntity> globalSelection, FlexoEditor editor) {
        super(ACTION_TYPE, focusedObject, globalSelection, editor);
        toDelete = new Vector<>();
        if (globalSelection == null || !globalSelection.contains(focusedObject)) {
            toDelete.add(focusedObject);
        }
        if (globalSelection != null) {
            toDelete.addAll(globalSelection);
        }
    }

    @Override
    protected void doAction(Object context) {
        for (XOWLEntity entity : toDelete) {
            entity.delete();
        }
    }
}
