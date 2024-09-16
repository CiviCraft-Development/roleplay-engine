plugins {
    id("java")
}

group = "net.civicraft"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.minestom:minestom-snapshots:6c5cd6544e")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("net.dv8tion:JDA:5.1.0")
}

tasks.test {
    useJUnitPlatform()
}