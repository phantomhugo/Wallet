To build just;

mvn install

To run:
java -Dexec.mainClass=com.hugo.wallet.WalletApplication -Dexec.classpathScope=runtime -Dexec.appArgs= org.codehaus.mojo:exec-maven-plugin:3.0.0:exec


To build the docker image:

docker build -t hugo/wallet .
or
buildah bud -t hugo/wallet .

To run the docker image:

docker run -p 8080:8080 hugo/wallet

or 

podman run -p 8080:8080 hugo/wallet
