

cluster config:

build:

    gradle makeClusterConfig

deploy:

    cf restart-gemfire my-gemfire-service --cluster-config ./build/distributions/cluster_config.zip

assemble the code:

    gradle assemble

push app (no start):

    cf push --no-start


to connect to cloud gemfire via gfsh:

    cf show-gfsh my-gemfire-service

then use output of command to connect from inside a gfsh shell

    $ gfsh
    gfsh> connect --user-http url=http://.....


