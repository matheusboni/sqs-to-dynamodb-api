package com.sqstodynamodbapi.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@DynamoDBTable(tableName = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = -5478630478258232599L;

    @DynamoDBHashKey
    private String id;

    private Long purchaseId;

    @DynamoDBTypeConvertedEnum
    private Status status;

    private String date;

}
