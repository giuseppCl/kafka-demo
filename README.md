# Sample Spring Boot + Kafka + Docker Compose Project

This is a sample Spring Boot project demonstrating how to run a local **Kafka** and **Zookeeper** setup using **Docker Compose**, and how to integrate Kafka consumers and Kafka Streams in a simple, working Spring Boot application.

It accompanies a blog post that explains the setup step by step and shows how Kafka partitioning and stream processing work in practice.



> 🛑 Not intended for production use — this is a minimal setup for learning.

---

## 📖 Related Blog Post

You can read the full walkthrough and explanation here: **[A beginner's introduction to Kafka and how to use it in Spring Boot](#)**

---

## 🚀 How to Run

1. Make sure **Docker** is installed and running.
2. Clone this repository:
```bash
git clone https://github.com/giuseppCl/kafka-demo.git
cd kafka-demo
```
3. Start the environment using Docker Compose:
```bash
docker-compose up -d
```
4. Run the Spring Boot application using your IDE or:
```bash
./mvnw spring-boot:run
```

## 💬 What's Included
- Kafka + Zookeeper via Docker Compose 
- user-activity topic with 2 partitions 
- Two consumers (with the same groupId) demonstrating Kafka's partition assignment 
- Kafka Streams pipeline to filter "LIKE" events and write them to a new topic

--- 

Feel free to fork, explore, and adapt this project to your needs.
