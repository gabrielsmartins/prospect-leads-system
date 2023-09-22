# Use a imagem base do Schema Registry da Confluent
FROM confluentinc/cp-schema-registry:5.4.0

# Configurações do Schema Registry
ENV SCHEMA_REGISTRY_HOST_NAME localhost
ENV SCHEMA_REGISTRY_KAFKASTORE_CONNECTION_URL "zookeeper:2181"

# Porta que será exposta
EXPOSE 8081

# Comando para iniciar o Schema Registry
CMD ["sh", "-c", "/etc/confluent/docker/run"]
