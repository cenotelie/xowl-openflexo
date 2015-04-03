/**
 * ****************************************************************************
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
 * Laurent Wouters - lwouters@xowl.org
 * ****************************************************************************
 */

package org.xowl.openflexo.connector.gui;

import org.openflexo.components.widget.OntologyView;
import org.openflexo.foundation.ontology.IFlexoOntology;
import org.openflexo.view.controller.FlexoController;
import org.openflexo.view.controller.model.FlexoPerspective;
import org.xowl.openflexo.connector.model.XOWLOntology;

import javax.swing.*;

/**
 * Represents a view of a xOWL ontology
 *
 * @author Laurent Wouters
 */
public class XOWLOntologyView extends OntologyView<XOWLOntology> {
    /**
     * Initializes this view
     * @param object The represented ontology
     * @param controller The parent controller
     * @param perspective The perspective
     */
    public XOWLOntologyView(XOWLOntology object, FlexoController controller, FlexoPerspective perspective) {
        super(object, controller, perspective);
    }

    @Override
    protected XOWLOntologyBrowserModel performBuildOntologyBrowserModel(IFlexoOntology ontology) {
        return new XOWLOntologyBrowserModel((XOWLOntology) ontology);
    }

    @Override
    public ImageIcon getOntologyClassIcon() {
        return XOWLIconLibrary.CLASS_ICON;
    }

    @Override
    public ImageIcon getOntologyIndividualIcon() {
        return XOWLIconLibrary.INDIVIDUAL_ICON;
    }

    @Override
    public ImageIcon getOntologyDataPropertyIcon() {
        return XOWLIconLibrary.DATA_PROPERTY_ICON;
    }

    @Override
    public ImageIcon getOntologyObjectPropertyIcon() {
        return XOWLIconLibrary.OBJECT_PROPERTY_ICON;
    }

    @Override
    public ImageIcon getOntologyAnnotationIcon() {
        return XOWLIconLibrary.ANNOTATATION_ICON;
    }

    @Override
    public boolean supportTechnologySpecificHiddenConcepts() {
        return true;
    }

    @Override
    public String technologySpecificHiddenConceptsLabel() {
        return "Show all concepts";
    }
}
