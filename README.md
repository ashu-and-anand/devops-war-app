# 🚀 DevOps WAR Deployment Pipeline

### End-to-End Spring Boot Deployment on AWS, Docker & Kubernetes

![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge\&logo=docker\&logoColor=white)
![Kubernetes](https://img.shields.io/badge/kubernetes-%23326ce5.svg?style=for-the-badge\&logo=kubernetes\&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge\&logo=amazon-aws\&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge\&logo=spring-boot\&logoColor=white)

---

## 📌 Project Overview

This project demonstrates a **complete real-world DevOps pipeline** where a Spring Boot (WAR) application is:

* Built using Maven
* Containerized using Docker (Tomcat-based)
* Stored in DockerHub
* Deployed on a manually configured Kubernetes cluster (AWS EC2)
* Exposed to the internet using NodePort

---

## 🧠 Architecture

```
Developer → GitHub → EC2 (Build Server)
        → Docker Image → DockerHub
        → Kubernetes Cluster (Master + Worker)
        → Service (NodePort)
        → Browser Access
```

---

## ⚙️ Tech Stack

| Layer            | Technology                  |
| ---------------- | --------------------------- |
| Backend          | Spring Boot (WAR)           |
| Build Tool       | Maven                       |
| Containerization | Docker (Tomcat 10 + JDK 17) |
| Orchestration    | Kubernetes (Manual Setup)   |
| Infrastructure   | AWS EC2                     |
| Registry         | DockerHub                   |

---

## 🏗️ Infrastructure Setup

### 🔹 EC2 Instances

| Role              | Instance  |
| ----------------- | --------- |
| Docker Server     | t3.small  |
| Kubernetes Master | t3.small  |
| Kubernetes Worker | t3.small  |

---

### 🔹 Kubernetes Bootstrap

#### Master Node

```bash
curl -sL https://tinyurl.com/mt346aen | bash
```

#### Worker Node

```bash
curl -sL https://tinyurl.com/yvmvxmzb | bash
```

---

### 🌐 Networking (CNI)

If worker node shows **NotReady**, apply Calico:

```bash
kubectl apply -f https://docs.projectcalico.org/manifests/calico.yaml
```

✔️ Ensures:

* Pod-to-pod communication
* Cluster networking stability

---

## 📂 Project Structure

```
.
├── src/
├── pom.xml
├── Dockerfile
├── deployment.yaml
├── README.md
```

---

## 🐳 Docker Configuration

```dockerfile
FROM tomcat:10-jdk17

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/demo-war-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
```

---

## 🚀 Deployment Lifecycle

### 1️⃣ Build WAR

```bash
mvn clean package
```

---

### 2️⃣ Build & Push Docker Image

```bash
docker build -t demo-war-app .
docker tag demo-war-app ashuanand/demo-war-app:v1
docker push ashuanand/demo-war-app:v1
```

---

### 3️⃣ Kubernetes Deployment

## 📄 deployment.yaml

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: demo-war-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: demo-war
  template:
    metadata:
      labels:
        app: demo-war
    spec:
      containers:
        - name: demo-war-container
          image: ashuanand/demo-war-app:v1
          ports:
            - containerPort: 8080
```

---

## 📄 service.yaml

```yaml
apiVersion: v1
kind: Service
metadata:
  name: demo-war-service
spec:
  type: NodePort
  selector:
    app: demo-war
  ports:
    - port: 80
      targetPort: 8080
      nodePort: 30080
```

---

### Apply Deployment

```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

---

## 🌐 Access Application

```
http://<WORKER-PUBLIC-IP>:30080
```

---

## 🔐 AWS Security Group Rules

| Port        | Purpose            |
| ----------- | ------------------ |
| 22          | SSH                |
| 30080       | Application Access |
| 6443        | Kubernetes API     |
| 30000–32767 | NodePort Range     |

---

## ⚠️ Challenges Faced

* WAR not generating due to incorrect Maven dependencies
* Running Maven commands from wrong directory
* Dockerfile not detected (naming/location issue)
* Kubernetes worker node stuck in `NotReady` state
* Understanding correct flow between build → container → orchestration

---

## 📚 Key Learnings

* Real-world DevOps pipeline execution
* WAR vs JAR deployment differences
* Docker image lifecycle
* Kubernetes Deployment & Service
* Debugging infrastructure-level issues
* Importance of directory awareness in Linux

---

## 🔮 Future Enhancements

* 🔥 Add Nginx (Frontend Layer)
* 🌐 Implement Ingress Controller
* ⚙️ CI/CD with GitHub Actions / Jenkins
* 🔄 Convert WAR → JAR (modern architecture)

---

## 👤 Author

**Ashu Anand**
DevOps & Java Backend Learner 🚀

---

## ⭐ Final Outcome

✔️ Application successfully deployed on Kubernetes
✔️ Accessible via public IP
✔️ Full DevOps pipeline implemented end-to-end

---

> This project reflects hands-on, real-world DevOps understanding — not just theoretical knowledge.
