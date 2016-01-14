

cluster config:

build:

    gradle makeClusterConfig

deploy:

    cf restart-gemfire my-gemfire-service --cluster-config ./build/distributions/cluster_config.zip

assemble the code:

    gradle assemble

push app (no start):

    cf push --no-start


