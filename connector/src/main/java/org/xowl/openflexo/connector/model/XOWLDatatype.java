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
package org.xowl.openflexo.connector.model;

import org.openflexo.foundation.ontology.BuiltInDataType;
import org.openflexo.foundation.ontology.IFlexoOntologyDataType;
import org.xowl.openflexo.connector.XOWLTechnologyAdapter;
import org.xowl.store.Vocabulary;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a datatype from an xOWL ontology
 *
 * @author Laurent Wouters
 */
public class XOWLDatatype extends XOWLEntity implements IFlexoOntologyDataType<XOWLTechnologyAdapter> {

    /**
     * The register of known implementations
     */
    private static final Map<String, BuiltInDataType> REGISTER = new HashMap<>();

    static {
        REGISTER.put(Vocabulary.xsdByte, BuiltInDataType.Byte);
        REGISTER.put(Vocabulary.xsdUnsigedByte, BuiltInDataType.Byte);
        REGISTER.put(Vocabulary.xsdShort, BuiltInDataType.Short);
        REGISTER.put(Vocabulary.xsdUnsignedShort, BuiltInDataType.Short);
        REGISTER.put(Vocabulary.xsdInt, BuiltInDataType.Int);
        REGISTER.put(Vocabulary.xsdInteger, BuiltInDataType.Integer);
        REGISTER.put(Vocabulary.xsdUnsignedInteger, BuiltInDataType.Integer);
        REGISTER.put(Vocabulary.xsdNonNegativeInteger, BuiltInDataType.Integer);
        REGISTER.put(Vocabulary.xsdNonPositiveinteger, BuiltInDataType.Integer);
        REGISTER.put(Vocabulary.xsdPositiveInteger, BuiltInDataType.Integer);
        REGISTER.put(Vocabulary.xsdNegativeInteger, BuiltInDataType.Integer);
        REGISTER.put(Vocabulary.xsdLong, BuiltInDataType.Long);
        REGISTER.put(Vocabulary.xsdUnsignedLong, BuiltInDataType.Long);
        REGISTER.put(Vocabulary.xsdDouble, BuiltInDataType.Double);
        REGISTER.put(Vocabulary.xsdFloat, BuiltInDataType.Float);
        REGISTER.put(Vocabulary.xsdDecimal, BuiltInDataType.Double);
        REGISTER.put(Vocabulary.xsdBoolean, BuiltInDataType.Boolean);
        REGISTER.put(Vocabulary.xsdString, BuiltInDataType.String);
        REGISTER.put(Vocabulary.xsdDate, BuiltInDataType.String);
        REGISTER.put(Vocabulary.xsdDateTime, BuiltInDataType.String);
        REGISTER.put(Vocabulary.xsdDuration, BuiltInDataType.String);
        REGISTER.put(Vocabulary.xsdTime, BuiltInDataType.String);
        REGISTER.put(Vocabulary.rdfPlainLiteral, BuiltInDataType.String);
        REGISTER.put(Vocabulary.owl + "Real", BuiltInDataType.Double);
        REGISTER.put(Vocabulary.owl + "Rational", BuiltInDataType.Double);
    }

    /**
     * Initializes this datatype role for an entity
     *
     * @param entity The represented entity
     */
    public XOWLDatatype(XOWLEntity entity) {
        super(entity.getTechnologyAdapter(), entity.getOntology(), entity.entity);
    }

    @Override
    public Class<?> getAccessedType() {
        return getBuiltInDataType().getAccessedType();
    }

    @Override
    public BuiltInDataType getBuiltInDataType() {
        BuiltInDataType result = REGISTER.get(entity.getIRIString());
        if (result != null)
            return result;
        return BuiltInDataType.String;
    }
}
