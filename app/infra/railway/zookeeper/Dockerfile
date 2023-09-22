# Use a imagem base do ZooKeeper da Confluent
FROM confluentinc/cp-zookeeper:5.4.0

# Configurações do ZooKeeper
ENV ZOOKEEPER_CLIENT_PORT 2181
ENV ZOOKEEPER_TICK_TIME 2000

# Porta que será exposta
EXPOSE 2181

# Comando para iniciar o ZooKeeper
CMD ["sh", "-c", "/etc/confluent/docker/run"]