# Basic function with minimal dependencies (Java)

# Requirements
- [Java 8 runtime environment (SE JRE)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3](https://maven.apache.org/docs/history.html)
- The Bash shell. For Linux and macOS, this is included by default. In Windows 10, you can install the [Windows Subsystem for Linux](https://docs.microsoft.com/en-us/windows/wsl/install-win10) to get a Windows-integrated version of Ubuntu and Bash.
- [The AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html) v1.17 or newer.

If you use the AWS CLI v2, add the following to your [configuration file](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-files.html) (`~/.aws/config`):

```
cli_binary_format=raw-in-base64-out
```

This setting enables the AWS CLI v2 to load JSON events from a file, matching the v1 behavior.

# Setup
Download or clone this repository.

    $ git clone XXX
    $ cd yyy

To create a new bucket for deployment artifacts, run `1-create-bucket.sh`.

    lambdaxper$ ./1-create-bucket.sh
    make_bucket: lambda-artifacts-a5e4xmplb5b22e0d

# Deploy
To deploy the application, run `2-deploy.sh`.

    lambdaxper$ ./2-deploy.sh
    BUILD SUCCESSFUL in 1s
    Successfully packaged artifacts and wrote output template to file out.yml.
    Waiting for changeset to be created..
    Successfully created/updated stack - lambdaxper

This script uses AWS CloudFormation to deploy the Lambda functions and an IAM role. If the AWS CloudFormation stack that contains the resources already exists, the script updates it with any changes to the template or function code.

You can also build the application with Maven. To use maven, add `mvn` to the command.

    java-basic$ ./2-deploy.sh mvn
    [INFO] Scanning for projects...
    [INFO] -----------------------< com.example:java-basic >-----------------------
    [INFO] Building lambdaxper-function 1.0-SNAPSHOT
    [INFO] --------------------------------[ jar ]---------------------------------
    ...

# Test
To invoke the function, run `3-invoke.sh`.

    lambdaxper$ ./3-invoke.sh
    {
        "StatusCode": 200,
        "ExecutedVersion": "$LATEST"
    }
    "200 OK"

Let the script invoke the function a few times and then press `CRTL+C` to exit.


# Configure Handler Class

# Cleanup
To delete the application, run `4-cleanup.sh`.

    java-basic$ ./4-cleanup.sh
