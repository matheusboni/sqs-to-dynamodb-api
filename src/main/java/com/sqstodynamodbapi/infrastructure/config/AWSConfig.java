package com.sqstodynamodbapi.infrastructure.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = {"com.sqstodynamodbapi.infrastructure.repository"})
public class AWSConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.dynamodb.region}")
    private Regions dynamoRegion;

    @Value("${amazon.sqs.host}")
    private String sqsHost;

    @Value("${amazon.sqs.port}")
    private Integer sqsPort;

    @Value("${amazon.sqs.region}")
    private Regions sqsRegion;

    @Value("${amazon.accessKey}")
    private String accessKey;

    @Value("${amazon.secretKey}")
    private String secretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AwsClientBuilder.EndpointConfiguration conf = new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint,
                dynamoRegion.getName());
        return AmazonDynamoDBClient.builder().withEndpointConfiguration(conf)
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .build();

    }

    @Bean("sqsClient")
    public AmazonSQS amazonSQS() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProxyHost(sqsHost);
        clientConfiguration.setProxyPort(sqsPort);
        clientConfiguration.setProtocol(Protocol.HTTP);

        return AmazonSQSClientBuilder.standard().withRegion(sqsRegion)
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withClientConfiguration(clientConfiguration).build();
    }

    private BasicAWSCredentials credentials() {
        return new BasicAWSCredentials(this.accessKey, this.secretKey);
    }

}
