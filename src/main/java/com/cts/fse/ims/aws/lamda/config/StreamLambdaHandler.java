package com.cts.fse.ims.aws.lamda.config;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class StreamLambdaHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent,APIGatewayProxyResponseEvent> {
    
}
