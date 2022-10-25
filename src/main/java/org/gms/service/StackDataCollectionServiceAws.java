package org.gms.service;

import jakarta.inject.Singleton;
import org.gms.dto.ResourceDto;
import org.gms.dto.StackDto;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.ListStacksResponse;
import software.amazon.awssdk.services.cloudformation.model.StackResourceSummary;
import software.amazon.awssdk.services.cloudformation.model.StackSummary;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Singleton
public class StackDataCollectionServiceAws implements StackDataCollectionService {

    @Override
    public Collection<StackDto> explore() {
        try(CloudFormationClient client = CloudFormationClient.builder().build()) {
            ListStacksResponse listStacksResponse = client.listStacks();
            Stream<String> stackSummaries = listStacksResponse.stackSummaries().stream()
                    .filter(StackDataCollectionServiceAws::isPresent)  // TODO should probably explore just existing stacks ???
                    .map(StackSummary::stackId);

            Map<String, List<StackResourceSummary>> stacksResources = stackSummaries.collect(toMap(
                    Function.identity(),
                    id -> getStackResourceSummaries(client, id)));

            return stacksResources.entrySet().stream()
                    .map(StackDataCollectionServiceAws::toStack)
                    .collect(Collectors.toList());
        }
    }

    private static StackDto toStack(Map.Entry<String, List<StackResourceSummary>> entry) {
        List<ResourceDto> resources = entry.getValue().stream()
                .map(resource -> ResourceDto.builder()
                        .name(resource.physicalResourceId() == null ? "MISSING" : resource.physicalResourceId())
                        .resourceType(resource.resourceType())
                        .status(resource.resourceStatusAsString())
                        .build())
                .collect(Collectors.toList());

        return StackDto.builder()
                .name(entry.getKey())
                .resources(resources)
                .region("eu-north-1")  // TODO: get it dynamically
                .build();
    }

    private static List<StackResourceSummary> getStackResourceSummaries(CloudFormationClient client, String name) {
        return client
                .listStackResources(builder -> builder.stackName(name))
                .stackResourceSummaries();
    }

    private static boolean isPresent(StackSummary summary) {
        return summary.deletionTime() == null;
    }
}
