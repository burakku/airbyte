/*
 * Copyright (c) 2024 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.cdk.load.data

import io.airbyte.cdk.load.data.json.toJson
import io.airbyte.cdk.load.util.serializeToString

class SchemalessTypesToJson : AirbyteSchemaIdentityMapper {
    override fun mapObjectWithoutSchema(schema: ObjectTypeWithoutSchema): AirbyteType = StringType
    override fun mapObjectWithEmptySchema(schema: ObjectTypeWithEmptySchema): AirbyteType =
        StringType
    override fun mapArrayWithoutSchema(schema: ArrayTypeWithoutSchema): AirbyteType = StringType
    override fun mapUnknown(schema: UnknownType): AirbyteType = StringType
}

class SchemalessValuesToJson : AirbyteValueIdentityMapper() {
    override fun mapObjectWithoutSchema(
        value: ObjectValue,
        schema: ObjectTypeWithoutSchema,
        path: List<String>
    ): AirbyteValue = value.toJson().serializeToString().let(::StringValue)
    override fun mapObjectWithEmptySchema(
        value: ObjectValue,
        schema: ObjectTypeWithEmptySchema,
        path: List<String>
    ): AirbyteValue = value.toJson().serializeToString().let(::StringValue)
    override fun mapArrayWithoutSchema(
        value: ArrayValue,
        schema: ArrayTypeWithoutSchema,
        path: List<String>
    ): AirbyteValue = value.toJson().serializeToString().let(::StringValue)
    override fun mapUnknown(value: UnknownValue, path: List<String>): AirbyteValue =
        value.value.serializeToString().let(::StringValue)
}
