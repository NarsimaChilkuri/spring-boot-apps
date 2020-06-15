# build jar file
mvn clean install -DskipTests

# Start by docker-compose
docker-compose up -d

# Start by deploying into Stack
docker stack deploy -c docker-compose-spring-rest-api-stack.yml spring-rest-stack

# Deploying in Kubernetes
kubectl apply -f k8s-resources/.

# Deploying by helm chart
helm install -g helm-chart/.

-----------------------------------
# The app will start running at http://localhost:8080.

# Explore Rest APIs
# The app defines following CRUD APIs.

GET /api/notes

POST /api/notes

GET /api/notes/{noteId}

PUT /api/notes/{noteId}

DELETE /api/notes/{noteId}

-----------------------------------
# Flyway DB Migration

# dependency changes in pom.xml

<dependency>
   <groupId>org.flywaydb</groupId>
   <artifactId>flyway-core</artifactId>
</dependency>
<dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId> 
   <scope>runtime</scope>
</dependency>


# adding properties of DB using application.properties

spring.datasource.url = jdbc:mysql://REMOTE_HOST:3306/DB_NAME
spring.datasource.username = username
spring.datasource.password = password


spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

spring.jpa.hibernate.ddl-auto = update


# Adding Migrations in src/main/resources/db/migration

V1__init.sql 

-----------------------------------


# Build custom sonar image using dockerfile-custom-sonar

docker build -t <custom_sonar_qube_image_name> -f dockerfile-custom-sonar .



# Start SonarQube Container using custom image of SonarQube you Created

docker run -d -p 9000:9000 <custom_sonar_qube_image_name>



# Create a webhook in SonarQube pointing to JENKINS HOST

To create the webhook form the SonarQube interface, to do so you can perform the following steps

1. Login to SonarQube with admin user
2. Navigate to the webhooks page : Administration->Configurations->webhooks
3. Create a new webhook: Click on create new webhook and then fill the below form and hit create button. The webhook URL should follow this form https://${jenkins_domain}/sonarqube-webhook/. The / at the end of the user is very important and without it, you may experience some errors triggering the webhooks.


----------------------------------

Ignore test cases while running maven project with sonar

mvn sonar:sonar -Dmaven.test.skip=true

Note : But make sure that your unit tests ran before to have results for unit test coverage in sonar

-----------------------------------


# Start nexus repo manager

docker run -d -p 8081:8081 -p 8083:8083 --name nexus  sonatype/nexus3

Create a docker private repo in nexus repo manager



# Steps for docker to allowing insecure registry

Create an HTTP connector at specified port (another port where nexus container will be listening). Normally used if the server is behind a secure proxy.

Check this option "Allow clients to use the V1 API to interact with this repository"

Add insecure registry server host in docker config (/etc/docker/daemon.json)

{
  "insecure-registries" : ["HOST_IP:ANOTHER_PORT_FOR_NEXUS_LISTENING"]
}

Restart docker service

"service docker restart"





