Absolutely! I can create a **complete Markdown (`.md`) file** that consolidates everything we discussed — **issues, root causes, fixes, and best practices for Jenkins + Maven + Selenium on a low-resource Linux server** — in a clean, structured way. This will include:

* Maven compilation issues
* Low RAM / swap issues
* ChromeDriver + Selenium headless fixes
* Jenkins pipeline configuration
* Recommendations

Here’s the `.md` content:

---

# Jenkins + Maven + Selenium on Linux Server: Issues & Solutions

## Table of Contents

1. [Maven Build Failure (Java Version)](#maven-build-failure-java-version)
2. [Server Freezing During Maven Dependency Download](#server-freezing-during-maven-dependency-download)
3. [ChromeDriver / Selenium SessionNotCreatedException](#chromedriver--selenium-sessionnotcreatedexception)
4. [Jenkins Pipeline Best Practices](#jenkins-pipeline-best-practices)
5. [Recommendations for Low-Resource Servers](#recommendations-for-low-resource-servers)

---

## 1. Maven Build Failure (Java Version)

### Error observed:

```
[ERROR] Source option 5 is no longer supported. Use 7 or later.
[ERROR] Target option 5 is no longer supported. Use 7 or later.
```

### Cause:

* Jenkins is using **Java 17**
* Maven compiler defaults to **Java 5** in old versions (`maven-compiler-plugin:3.1`)
* Incompatible → **build fails**

### Resolution:

#### Option 1: Use `<properties>` in `pom.xml`:

```xml
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>
```

#### Option 2: Configure Maven compiler plugin explicitly:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.11.0</version>
      <configuration>
        <source>11</source>
        <target>11</target>
      </configuration>
    </plugin>
  </plugins>
</build>
```

> You can use `17` if your code is compatible.

---

## 2. Server Freezing During Maven Dependency Download

### Observed:

```
Downloaded ... commons-lang3-3.1.jar (316 kB at 225 kB/s)
```

* Server became unresponsive
* SSH terminal froze
* Jenkins job failed

### Cause:

* Server RAM = 914 MB
* Swap = 0
* Maven + Jenkins + Java = memory intensive
* First-time Maven dependency download consumes CPU + RAM → freeze

### Resolution:

#### Step 1: Add swap

```bash
sudo fallocate -l 2G /swapfile
sudo chmod 600 /swapfile
sudo mkswap /swapfile
sudo swapon /swapfile
```

Check:

```bash
free -h
```

#### Step 2: Limit Jenkins memory

Edit `/etc/default/jenkins`:

```bash
JAVA_ARGS="-Xms128m -Xmx384m"
```

Restart Jenkins:

```bash
sudo systemctl restart jenkins
```

#### Step 3: Limit Maven memory

Create `/var/lib/jenkins/.mvn/maven.config`:

```
-Xmx256m
```

#### Step 4: Clean Jenkins workspace before rerun

```bash
sudo rm -rf /var/lib/jenkins/workspace/*
sudo rm -rf /var/lib/jenkins/.m2
```

#### Step 5: Run Maven in **batch and quiet mode** in Jenkinsfile

```groovy
sh 'mvn -B -q -Dmaven.repo.local=.m2repo clean test'
```

---

## 3. ChromeDriver / Selenium `SessionNotCreatedException`

### Error observed:

```
org.openqa.selenium.SessionNotCreatedException: Could not start a new session.
Chrome instance exited.
```

### Cause:

* Running **Chrome on a headless Linux server**
* RAM too low to start Chrome normally
* ChromeDriver version mismatch or missing Chrome binary

### Resolution:

#### Step 1: Install Chrome & ChromeDriver

```bash
sudo apt install -y wget unzip
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo apt install -y ./google-chrome-stable_current_amd64.deb
# Download matching ChromeDriver version
wget https://chromedriver.storage.googleapis.com/<CHROME_MAJOR_VERSION>/chromedriver_linux64.zip
unzip chromedriver_linux64.zip
sudo mv chromedriver /usr/local/bin/
sudo chmod +x /usr/local/bin/chromedriver
```

#### Step 2: Configure ChromeDriver in headless mode

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless"); // run without GUI
options.addArguments("--no-sandbox"); // Linux required
options.addArguments("--disable-dev-shm-usage"); // low /dev/shm space
options.addArguments("--disable-gpu"); // optional
options.addArguments("--window-size=1920,1080"); // optional

WebDriver driver = new ChromeDriver(options);
```

> Required on Jenkins/Linux servers to prevent memory crashes.

---

## 4. Jenkins Pipeline Best Practices

* Use **headless Chrome** for Linux CI
* Use **Maven batch mode** (`-B -q`) for memory efficiency
* Limit **Jenkins JVM memory**
* Preload Maven dependencies if possible
* Keep **workspace and .m2 repo clean** before first run
* Post-build: use `junit 'target/surefire-reports/*.xml'` to collect test reports

### Example Jenkinsfile (Safe for low RAM):

```groovy
pipeline {
    agent any

    stages {
        stage('Run Maven Tests') {
            steps {
                sh '''
                export MAVEN_OPTS="-Xmx256m"
                mvn -B -q -Dmaven.repo.local=.m2repo clean test
                '''
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
```

---

## 5. Recommendations for Low-Resource Servers

| Issue                  | Recommendation                      |
| ---------------------- | ----------------------------------- |
| Low RAM (<1GB)         | Add at least 2GB swap               |
| Jenkins + Maven        | Limit JVM memory (`-Xmx`)           |
| Selenium               | Always use headless Chrome          |
| First-time Maven build | Preload `.m2` repository manually   |
| ChromeDriver           | Match Chrome binary version exactly |
| Logs                   | Use `-B -q` to reduce memory usage  |

> Optional: Upgrade server to **≥ 2GB RAM** to run Selenium + Jenkins smoothly.

---

## ✅ Summary of All Issues & Fixes

| Issue                    | Root Cause                                  | Fix                                                                                           |
| ------------------------ | ------------------------------------------- | --------------------------------------------------------------------------------------------- |
| Maven build failure      | Old compiler defaults (Java 5)              | Set `<maven.compiler.source>` and `<target>` to 11+                                           |
| Jenkins freezes          | Low RAM + no swap during Maven download     | Add swap, limit JVM, run Maven in batch                                                       |
| ChromeDriver error       | Headless server / memory / version mismatch | Install Chrome + matching driver, use `--headless`, `--no-sandbox`, `--disable-dev-shm-usage` |
| Jenkinsfile test reports | Missing JUnit reports                       | Add `post { always { junit 'target/surefire-reports/*.xml' } }`                               |

---

✅ After applying **all fixes**, your Jenkins pipeline will:

* Build Maven project successfully
* Run Selenium tests headlessly
* Collect JUnit reports
* Work on low-memory Linux server

---

I can also **attach images/diagrams** showing **memory usage, Maven dependency flow, and Chrome headless setup** if you want this `.md` to be fully **visual and beginner-friendly**.

Do you want me to do that next?
